package vision;

import java.io.UnsupportedEncodingException;
import java.util.Observable;
import java.util.Observer;

import Services.PergServices;
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
import util.Relogio;
import util.Round;
import util.ScreenLibrary;

public class Classico_ScreenController extends Observable implements Observer {

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
	private Button comecar = new Button("Comecar");

	@FXML
	private Label extra;

	@FXML
	private Label relogio;
	
	@FXML
	private Label pontuacao;
	
	@FXML
	private Label vidas;

	@FXML
	private Label pergunta;

	private Relogio time = new Relogio(this);
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
		pontuacao.setVisible(false);
		extra.setVisible(false);
		vidas.setText("3");
		vidas.setVisible(false);
		relogio.setVisible(false);
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
		vidas.setVisible(true);
		relogio.setVisible(true);
		comecar.setVisible(false);
		pontuacao.setVisible(true);
		pontuacao.setText("0,00");
		
		control.start();
		gameStart();

		

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

	private void loadPergunta() {
		atual = PergServices.randomPerg();
		pergunta.setText(atual.getPergunta());

		op1.setText(atual.getAlternativa1());
		op2.setText(atual.getAlternativa2());
		op3.setText(atual.getAlternativa3());
		op4.setText(atual.getAlternativa4());
		gameStart();
	}

	public void gameStart() {

		setChanged();
		op1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (atual.getCorreta().equals("alternativa1")){
					notifyObservers("true "+vidas.getText());
				}
				else{
					notifyObservers("false "+vidas.getText());
				}
				checaVidas();
				loadPergunta();
			}
		});

		op2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (atual.getCorreta().equals("alternativa2")){
					notifyObservers("true "+vidas.getText());
				}
				else{
					notifyObservers("false "+vidas.getText());
				}
				checaVidas();
				loadPergunta();
			}
		});

		op3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (atual.getCorreta().equals("alternativa3")){
					notifyObservers("true "+vidas.getText());
				}
				else{
					notifyObservers("false "+vidas.getText());
				}
				checaVidas();
				loadPergunta();
			}
		});

		op4.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (atual.getCorreta().equals("alternativa4")){
					notifyObservers("true "+vidas.getText());
				}
				else{
					notifyObservers("false "+vidas.getText());
					
				}
				checaVidas();
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
		vidas.setVisible(false);
		vidas.setText("3");
		pontuacao.setText("0.0");
		control.interrupt();
		
	}
	
	public void gameBreak() {
		op1.setVisible(false);
		op2.setVisible(false);
		op3.setVisible(false);
		op4.setVisible(false);
		pergunta.setVisible(false);
		control.interrupt();
		relogio.setVisible(false);
	}
	
	public void checaVidas(){
		if(Integer.parseInt(vidas.getText()) == 0)
			gameBreak();
		else
			loadPergunta();
	}
	
	public void update(Observable o, final Object arg) {
		
		if (arg instanceof Double) {
			Platform.runLater(new Runnable() {
				public void run() {
					if((Double)arg > 30)
						relogio.setTextFill(Color.GREEN);
					if((Double)arg <= 30 && (Double)arg >= 10)
						relogio.setTextFill(Color.ORANGE);
					if((Double)arg < 10)
						relogio.setTextFill(Color.RED);
					
					relogio.setText(""+ Round.round((Double)arg, 2));
				}
			});
		}
		
		if (arg instanceof String) {
			String compair[] = ((String) arg).split(" "); //dividindo a string em (alternativa correta/errada) e pontuação
			if (compair[0].equals("CERTO")){
				extra.setTextFill(Color.GREEN);
				extra.setText("ACERTOU");
				pontuacao.setText(compair[1]);
			}

			if (compair[0].equals("ERRADO")) {
				extra.setTextFill(Color.RED);
				extra.setText("ERROU");
				int qtdVidas = Integer.parseInt(vidas.getText());
				vidas.setText(Integer.toString(qtdVidas-1)); //alterando o valor das vidas
				pontuacao.setText(compair[1]);
			}
		}
	}

}
