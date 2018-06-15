package vision.kids;

import java.io.UnsupportedEncodingException;
import java.util.Observable;
import java.util.Observer;

import Services.KidPergServices;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import model.Pergunta;
import sources.ScreenConstants;
import util.RegressiveCronometer;
import util.Round;
import util.ScreenLibrary;
import util.SharedInfo;

public class ContraOTempo_ScreenController extends Observable implements Observer {

	@FXML
	private AnchorPane screen = new AnchorPane();

	@FXML
	private Button op1 = new Button("Opção 1");

	@FXML
	private Button op2 = new Button("Opção 2");

	@FXML
	private Button op3 = new Button("Opção 3");

	@FXML
	private Button op4 = new Button("Opção 4");

	@FXML
	private Button comecarButton = new Button("Começar");

	@FXML
	private Label extra;

	@FXML
	private Label relogio;

	@FXML
	private Label pergunta;
	
	@FXML
	private Label acrt;
	
	@FXML
	private Label errs;

	private int acertos = 0;
	private int erros = 0;

	private RegressiveCronometer time = new RegressiveCronometer(this);
	private Thread control = new Thread(time);
	private Pergunta atual = new Pergunta();
	private KidPergServices ps = new KidPergServices();

	@SuppressWarnings("unused")
	private Observable obs;

	public void assinar(Observable obs) {
		this.obs = obs;
		obs.addObserver(this);
	}

	@FXML
	public void initialize() {
		assinar(time);
		op1.setVisible(false);
		op2.setVisible(false);
		op3.setVisible(false);
		op4.setVisible(false);
		pergunta.setVisible(false);
		extra.setVisible(false);
		relogio.setVisible(false);
		acertos = 0; 
		erros = 0;
		
		acrt.setTextFill(Color.GREEN);
		errs.setTextFill(Color.RED);
		
		SharedInfo.setDirection(false);
	}

	@FXML
	public void handlerComecar() {

		loadPergunta();
		op1.setVisible(true);
		op2.setVisible(true);
		op3.setVisible(true);
		op4.setVisible(true);
		pergunta.setVisible(true);
		extra.setVisible(true);
		relogio.setVisible(true);
		comecarButton.setDisable(true);
		time.reloadRelogio();
		control.start();
		gameStart();

	}

	private void loadPergunta() {
		atual = ps.randomPerg();
		pergunta.setText(atual.getPergunta());

		op1.setText(atual.getAlternativa1());
		op2.setText(atual.getAlternativa2());
		op3.setText(atual.getAlternativa3());
		op4.setText(atual.getAlternativa4());
		gameStart();
	}

	public void gameStart() {

		acrt.setText("+ " + acertos);
		errs.setText("- " +erros);
		
		setChanged();
		op1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (atual.getCorreta().equals("alternativa1")) {
					acertos += 1;
					notifyObservers(true);
				} else {
					erros += 1;
					notifyObservers(false);
				}
				loadPergunta();
			}
		});

		op2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (atual.getCorreta().equals("alternativa2")){
					acertos += 1;
					notifyObservers(true);
				} else {
					erros += 1;
					notifyObservers(false);
				}
				loadPergunta();
			}
		});

		op3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (atual.getCorreta().equals("alternativa3")){
					acertos += 1;
					notifyObservers(true);
				} else {
					erros += 1;
					notifyObservers(false);
				}
				loadPergunta();
			}
		});

		op4.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (atual.getCorreta().equals("alternativa4")){
					acertos += 1;
					notifyObservers(true);
				} else {
					erros += 1;
					notifyObservers(false);
				}
				loadPergunta();
			}
		});

	}

	public void gameStop() {
		op1.setVisible(false);
		op2.setVisible(false);
		op3.setVisible(false);
		op4.setVisible(false);
		pergunta.setVisible(false);
		relogio.setVisible(false);
		extra.setVisible(false);
		comecarButton.setDisable(false);
		acertos = 0;
		erros = 0;
		control.interrupt();
	}

	@FXML
	public void handlerVoltar() {
		gameStop();
		try {
			ScreenLibrary.LoadTela(ScreenConstants.IDHOME, SharedInfo.getInstance());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public void update(Observable o, final Object arg) {

		if (arg instanceof Double) {
			Platform.runLater(new Runnable() {
				public void run() {
					if ((Double) arg > 30)
						relogio.setTextFill(Color.GREEN);
					if ((Double) arg <= 30 && (Double) arg >= 10)
						relogio.setTextFill(Color.ORANGE);
					if ((Double) arg < 10)
						relogio.setTextFill(Color.RED);

					relogio.setText("" + Round.round((Double) arg, 2));
				}
			});
		}

		if (arg instanceof String) {
			if ((Double.parseDouble((String) arg) == 5)) {
				extra.setTextFill(Color.GREEN);
				extra.setText("+5s ");
			}
			if ((Double.parseDouble((String) arg) == 3)) {
				extra.setTextFill(Color.GREEN);
				extra.setText("+3s ");
			}
			if ((Double.parseDouble((String) arg) == 1)) {
				extra.setTextFill(Color.GREEN);
				extra.setText("+1s ");
			}

			if ((Double.parseDouble((String) arg) == -3)) {
				extra.setTextFill(Color.RED);
				extra.setText("-3s ");
			}
		}

		if (arg instanceof Boolean) {
			if ((Boolean) arg == false)
				gameStop();
		}
	}
}
