package vision.enem;

import java.io.UnsupportedEncodingException;
import java.util.Observable;
import java.util.Observer;

import Services.EnemPergServices;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import model.EnemPergunta;
import sources.ScreenConstants;
import util.EnemProgressiveCronometer;
import util.Round;
import util.ScreenLibrary;
import util.SharedInfo;

public class Classico_ScreenController extends Observable implements Observer {

	@FXML
	private AnchorPane screen = new AnchorPane();

	@FXML
	private Button op1 = new Button("Op��o 1");

	@FXML
	private Button op2 = new Button("Op��o 2");

	@FXML
	private Button op3 = new Button("Op��o 3");

	@FXML
	private Button op4 = new Button("Op��o 4");
	
	@FXML
	private Button op5 = new Button("Op��o 5");

	@FXML
	private Button comecar = new Button("Comecar");

	@FXML
	private Label extra;
	
	@FXML
	private Label categoria;

	@FXML
	private Label relogio;

	@FXML
	private Label pontuacao; // quantidade de quest�es certas
	
	@FXML
	private Label qtdErro; //quantidade de quest�es erradas

	@FXML
	private Label vidas;
	
	@FXML
	private Label textoAcerto;
	
	@FXML
	private Label textoErro;

	@FXML
	private Label pergunta;

	private EnemProgressiveCronometer time = new EnemProgressiveCronometer(this);
	private Thread control = new Thread(time);
	private EnemPergunta atual = new EnemPergunta();
	private EnemPergServices ps = new EnemPergServices();

	@SuppressWarnings("unused")
	private Observable obs;

	public void assinar(Observable obs) {
		this.obs = obs;
		obs.addObserver(this);
	}

	@FXML
	public void initialize() {
		ps.randonPergunta(); // gerando a lista completa de perguntas aleat�rias
		ps.imprimePerguntas();
		assinar(time);
		op1.setVisible(false);
		op2.setVisible(false);
		op3.setVisible(false);
		op4.setVisible(false);
		op5.setVisible(false);
		categoria.setVisible(false);
		textoAcerto.setVisible(false);
		qtdErro.setVisible(false);
		textoErro.setVisible(false);
		pergunta.setVisible(false);
		pontuacao.setVisible(false);
		extra.setVisible(false);
		vidas.setText("3");
		vidas.setVisible(false);
		relogio.setVisible(false);
		SharedInfo.setDirection(true);
		time.reloadRelogio();
		
		//ps.populandoPergunta(100);
		
		
	}

	@FXML
	public void handlerComecar() {

		loadPergunta();
		op1.setVisible(true);
		op2.setVisible(true);
		op3.setVisible(true);
		op4.setVisible(true);
		op5.setVisible(true);
		categoria.setVisible(true);
		pergunta.setVisible(true);
		textoAcerto.setVisible(false);
		//qtdErro.setVisible(false);
		textoErro.setVisible(false);
		//extra.setVisible(true);
		//vidas.setVisible(true);
		relogio.setVisible(true);
		comecar.setDisable(true);
		//pontuacao.setVisible(true);
		pontuacao.setText("0,00");
		qtdErro.setText("180");

		control.start();
		gameStart();

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

	private void loadPergunta() {
		atual = ps.getPergunta(Integer.parseInt(pontuacao.getText())+Integer.parseInt(qtdErro.getText()));
		pergunta.setText(atual.getPergunta());

		op1.setText(atual.getAlternativa1());
		op2.setText(atual.getAlternativa2());
		op3.setText(atual.getAlternativa3());
		op4.setText(atual.getAlternativa4());
		op5.setText(atual.getAlternativa5());
		categoria.setText(atual.getCategoria());
		gameStart();
	}

	public void gameStart() {

		setChanged();
		op1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (atual.getCorreta().equals("A")) {
					notifyObservers(true);
				} else {
					notifyObservers(false);
				}
				loadPergunta();
			}
		});

		op2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (atual.getCorreta().equals("B")) {
					notifyObservers(true);
				} else {
					notifyObservers(false);
				}
				loadPergunta();
			}
		});

		op3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (atual.getCorreta().equals("C")) {
					notifyObservers(true);
				} else {
					notifyObservers(false);
				}
				loadPergunta();
			}
		});

		op4.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (atual.getCorreta().equals("D")) {
					notifyObservers(true);
				} else {
					notifyObservers(false);

				}
				loadPergunta();
			}
		});
		
		op5.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (atual.getCorreta().equals("E")) {
					notifyObservers(true);
				} else {
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
		op5.setVisible(false);
		categoria.setVisible(false);
		pergunta.setVisible(false);
		relogio.setVisible(true);
		extra.setVisible(false);
		pontuacao.setVisible(true);
		qtdErro.setVisible(true);
		textoAcerto.setVisible(true);
		textoErro.setVisible(true);
		vidas.setVisible(false);
		comecar.setDisable(false);
		
		
		
		
		//vidas.setText("3");
		//pontuacao.setText("0.0");
		control.interrupt();

	}

	public void gameBreak() {
		op1.setVisible(false);
		op2.setVisible(false);
		op3.setVisible(false);
		op4.setVisible(false);
		op5.setVisible(false);
		categoria.setVisible(false);
		pergunta.setVisible(false);
		control.interrupt();
		relogio.setVisible(false);
	}

	public void update(Observable o, final Object arg) {

		if (arg instanceof Double) {
			Platform.runLater(new Runnable() {
				public void run() {
					if ((Double) arg < 10)
						relogio.setTextFill(Color.GREEN);
					if ((Double) arg <= 30 && (Double) arg >= 10)
						relogio.setTextFill(Color.ORANGE);
					if ((Double) arg > 20)
						relogio.setTextFill(Color.RED);

					relogio.setText("" + Round.round((Double) arg, 2));
				}
			});
		}

		if (arg instanceof String) {
			final String compair[] = ((String) arg).split(" "); // dividindo a
																// string em
																// (alternativa
																// correta/errada)
																// e pontua��o

			if (compair[0].equals("CERTO")) {

				Platform.runLater(new Runnable() {
					public void run() {
						extra.setTextFill(Color.GREEN);
						extra.setText("ACERTOU");
						pontuacao.setText(compair[1]);
						qtdErro.setText(compair[2]);
					}
				});
			}

			if (compair[0].equals("ERRADO")) {

				Platform.runLater(new Runnable() {
					public void run() {
						extra.setTextFill(Color.RED);
						extra.setText("ERROU");
						int qtdVidas = Integer.parseInt(vidas.getText());
						vidas.setText(Integer.toString(qtdVidas - 1));
						pontuacao.setText(compair[1]);
						qtdErro.setText(compair[2]);
					}
				});
			}
		}

		if (arg instanceof Boolean) {
			if ((Boolean) arg == false)
				gameStop();
		}
	}

}
