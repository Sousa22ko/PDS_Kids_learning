package vision;

import java.io.UnsupportedEncodingException;
import java.util.Observable;
import java.util.Observer;

import Services.PergServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.Pergunta;
import sources.ScreenConstants;
import util.Cronometro;
import util.ScreenLibrary;

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
	private Label extra;

	@FXML
	private Label relogio;
	
	@FXML
	private Label pergunta;

	private Cronometro time = new Cronometro(this);
	private Thread control = new Thread(time);
	private Pergunta atual = new Pergunta();

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
	}

	@FXML
	public void handlerComecar() {

		atual = PergServices.randomPerg(); 
		Pane back = new Pane();

		back.setStyle("-fx-background-color: whitesmoke; -fx-border-color: lightgrey;");

		op1.setVisible(true);
		op2.setVisible(true);
		op3.setVisible(true);
		op4.setVisible(true);
		pergunta.setVisible(true);

		control.start();
		gameStart();

	}

	public void gameStart() {

			op1.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					if (atual.getCorreta().equals("Alternativa 1"))
						notifyObservers(true);
					else
						notifyObservers(false);
				}
			});

			op2.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					if (atual.getCorreta().equals("Alternativa 2"))
						notifyObservers(true);
					else
						notifyObservers(false);
				}
			});

			op3.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					if (atual.getCorreta().equals("Alternativa 3"))
						notifyObservers(true);
					else
						notifyObservers(false);
				}
			});

			op4.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					if (atual.getCorreta().equals("Alternativa 4"))
						notifyObservers(true);
					else
						notifyObservers(false);
				}
			});
		
	}

	public void gameStop() {
		op1.setVisible(false);
		op2.setVisible(false);
		op3.setVisible(false);
		op4.setVisible(false);
		pergunta.setVisible(false);
		control.interrupt();
	}

	@FXML
	public void handlerVoltar() {
		gameStop();
		try {
			ScreenLibrary.LoadTela(ScreenConstants.IDHOME);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public void update(Observable o, Object arg) {
		String s = (String) arg;
		extra.setTextFill(Color.GREEN);

		if (s.equals("5"))
			extra.setText("+5");

		if (s.equals("2"))
			extra.setText("+2");

		if (s.equals("1"))
			extra.setText("+1");

		if (s.equals("0"))
			gameStop();

		if (s.equals("-1")) {
			extra.setTextFill(Color.RED);
			extra.setText("-1");
		}
	}

}
