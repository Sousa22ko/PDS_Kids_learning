package vision.milhao;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import Services.MilhaoPergServices;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.MilhaoPergunta;
import sources.ScreenConstants;
import util.MilhaoRegressiveCronometer;
import util.Round;
import util.ScreenLibrary;
import util.SharedInfo;

public class ContraOTempo_ScreenController extends Observable implements Observer {

	@FXML
	private AnchorPane screen = new AnchorPane();
	
	@FXML
	private Pane painelValores = new Pane();

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
	private Button voltarButton = new Button("Voltar");
	
	@FXML
	private Button pedDica = new Button("Quero uma dica");
	
	@FXML
	private Button pararButton = new Button("PARAR");

	@FXML
	private Label dica;

	@FXML
	private Label extra;
	
	@FXML
	private Label fimDeJogo;

	@FXML
	private Label relogio;
	
	@FXML
	private Label errarLabel;
	
	@FXML
	private Label pararLabel;
	
	@FXML
	private Label acertarLabel;
	
	@FXML
	private Label dinheiroParar; //dinheiro ganho se parar
	
	@FXML
	private Label dinheiroAcertar;//dinheiro ganho se parar
	
	@FXML
	private Label dinheiroErrar;//dinheiro ganho se parar

	@FXML
	private Label pergunta;
	
	private int acertos = 0;
	private int erros = 0;
	
	private ArrayList<String> acertosValor = new ArrayList<String>();
	private ArrayList<String> errosValor = new ArrayList<String>();
	private ArrayList<String> pararValor = new ArrayList<String>();

	private MilhaoRegressiveCronometer time = new MilhaoRegressiveCronometer(this);
	private Thread control = new Thread(time);
	private MilhaoPergunta atual = new MilhaoPergunta();
	private MilhaoPergServices ps = new MilhaoPergServices();

	@SuppressWarnings("unused")
	private Observable obs;

	public void assinar(Observable obs) {
		this.obs = obs;
		obs.addObserver(this);
	}

	@FXML
	public void initialize() {

		preencheArrayAcertos();
		preencheArrayParar();
		preencheArrayErros();
		
		assinar(time);
		op1.setVisible(false);
		op2.setVisible(false);
		op3.setVisible(false);
		op4.setVisible(false);
		pararButton.setVisible(false);
		fimDeJogo.setVisible(false);
		painelValores.setVisible(false);
		pedDica.setVisible(false);
		dica.setVisible(false);
		pergunta.setVisible(false);
		extra.setVisible(false);
		relogio.setVisible(false);
		acertos = 0; 
		erros = 0;
		
		
		SharedInfo.setDirection(false);
	}

	@FXML
	public void handlerComecar() {

		loadPergunta();
		op1.setVisible(true);
		op2.setVisible(true);
		op3.setVisible(true);
		op4.setVisible(true);
		pararButton.setVisible(true);
		painelValores.setVisible(true);
		pergunta.setVisible(true);
		pedDica.setVisible(true);
		extra.setVisible(true);
		//relogio.setVisible(true);
		comecarButton.setDisable(true);
		time.reloadRelogio();
		control.start();
		gameStart();

	}

	@FXML
	public void handlerDica() {
		int correta = 0;
		if(atual.getCorreta().equals("alternativa1")) {
			correta = 1;
		}else if(atual.getCorreta().equals("alternativa2")) {
			correta = 2;
		}else if(atual.getCorreta().equals("alternativa3")) {
			correta = 3;
		}else if(atual.getCorreta().equals("alternativa4")) {
			correta = 4;
		}
		
		
		int eliminaQ = new Random().nextInt(4) + 1;
		int eliminaQ2 = new Random().nextInt(4) + 1;
		while(eliminaQ == correta) {
			eliminaQ = new Random().nextInt(4) + 1;
		}
		while(eliminaQ2 == eliminaQ || eliminaQ2 == correta) {
			eliminaQ2 = new Random().nextInt(4) + 1;
		}
		
		if(eliminaQ == 1 || eliminaQ2 == 1) {
			op1.setVisible(false);
			System.out.println(atual.getCorreta());
		}
		if(eliminaQ == 2 || eliminaQ2 == 2) {
			op2.setVisible(false);
			System.out.println(atual.getCorreta());
		}
		if(eliminaQ == 3 || eliminaQ2 == 3) {
			op3.setVisible(false);
			System.out.println(atual.getCorreta());
		}
		if(eliminaQ == 4 || eliminaQ2 == 4) {
			op4.setVisible(false);
			System.out.println(atual.getCorreta());
		}
		pedDica.setVisible(false);
		//dica.setVisible(true);
		
	}

	private void loadPergunta() {
		atual = ps.absRandomPerg();
		pergunta.setText(atual.getPergunta());

		relogio.setVisible(false);
		
		op1.setText(atual.getAlternativa1());
		op2.setText(atual.getAlternativa2());
		op3.setText(atual.getAlternativa3());
		op4.setText(atual.getAlternativa4());
		dica.setText(atual.getDica());
		gameStart();
	}

	public void gameStart() {
		
		setChanged();
		op1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (atual.getCorreta().equals("alternativa1")) {
					acertos += 1;
					atualizaPane(acertos);
					op1.setVisible(true);
					op2.setVisible(true);
					op3.setVisible(true);
					op4.setVisible(true);
					notifyObservers(true);
				} else {
					erros += 1;
					fimDeJogo.setText("Voce ganhou "+errosValor.get(acertos));
					notifyObservers(false);
				}
				loadPergunta();
			}
		});

		op2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (atual.getCorreta().equals("alternativa2")){
					acertos += 1;
					atualizaPane(acertos);
					op1.setVisible(true);
					op2.setVisible(true);
					op3.setVisible(true);
					op4.setVisible(true);
					notifyObservers(true);
				} else {
					erros += 1;
					fimDeJogo.setText("Voce ganhou "+errosValor.get(acertos));
					notifyObservers(false);
				}
				loadPergunta();
			}
		});

		op3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (atual.getCorreta().equals("alternativa3")){
					acertos += 1;
					atualizaPane(acertos);
					op1.setVisible(true);
					op2.setVisible(true);
					op3.setVisible(true);
					op4.setVisible(true);
					notifyObservers(true);
				} else {
					erros += 1;
					fimDeJogo.setText("Voce ganhou "+errosValor.get(acertos));
					notifyObservers(false);
				}
				loadPergunta();
			}
		});

		op4.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (atual.getCorreta().equals("alternativa4")){
					acertos += 1;
					atualizaPane(acertos);
					notifyObservers(true);
					op1.setVisible(true);
					op2.setVisible(true);
					op3.setVisible(true);
					op4.setVisible(true);
				} else {
					erros += 1;
					fimDeJogo.setText("Voce ganhou "+errosValor.get(acertos));
					notifyObservers(false);
				}
				loadPergunta();
			}
		});

	}
	
	public void atualizaPane(int fase) {
		dinheiroErrar.setText(errosValor.get(fase));
		dinheiroParar.setText(pararValor.get(fase));
		dinheiroAcertar.setText(acertosValor.get(fase));
	}

	public void gameStop() {
		op1.setVisible(false);
		op2.setVisible(false);
		op3.setVisible(false);
		op4.setVisible(false);
		pararButton.setVisible(false);
		painelValores.setVisible(false);
		pergunta.setVisible(false);
		fimDeJogo.setVisible(true);
		pedDica.setVisible(false);
		dica.setVisible(false);
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
	
	@FXML
	public void handlerParar() {
		if(acertos != 0)
			fimDeJogo.setText("Voce ganhou "+pararValor.get(acertos));
		else
			fimDeJogo.setText("Voce ganhou "+pararValor.get(23));
		fimDeJogo.setVisible(true);
		gameStop();
	}
	
	public void preencheArrayErros() {
		errosValor.add("0 Reais");
		errosValor.add("250 Reais");
		errosValor.add("300 Reais");
		errosValor.add("350 Reais");
		errosValor.add("400 Reais");
		errosValor.add("450 Reais");
		errosValor.add("500 Reais");
		errosValor.add("1 mil");
		errosValor.add("1.500 mil");
		errosValor.add("2 mil");
		errosValor.add("2.500 mil");
		errosValor.add("3 mil");
		errosValor.add("5 mil");
		errosValor.add("10 mil");
		errosValor.add("15 mil");
		errosValor.add("20 mil");
		errosValor.add("25 mil");
		errosValor.add("30 mil");
		errosValor.add("50 mil");
		errosValor.add("100 mil");
		errosValor.add("150 mil");
		errosValor.add("200 mil");
		errosValor.add("250 mil");
		errosValor.add("PERDE TUDO");
		errosValor.add("PERDEU TUDO");
	}
	
	public void preencheArrayParar() {
		pararValor.add("0 Reais");
		pararValor.add("500 Reais");
		pararValor.add("600 Reais");
		pararValor.add("700 Reais");
		pararValor.add("800 Reais");
		pararValor.add("900 Reais");
		pararValor.add("1 mil");
		pararValor.add("2 mil");
		pararValor.add("3 mil");
		pararValor.add("4 mil");
		pararValor.add("5 mil");
		pararValor.add("6 mil");
		pararValor.add("10 mil");
		pararValor.add("20 mil");
		pararValor.add("30 mil");
		pararValor.add("40 mil");
		pararValor.add("50 mil");
		pararValor.add("60 mil");
		pararValor.add("100 mil");
		pararValor.add("200 mil");
		pararValor.add("300 mil");
		pararValor.add("400 mil");
		pararValor.add("500 mil");
		pararValor.add("600 mil");
		pararValor.add("600 mil");
	}
	
	public void preencheArrayAcertos() {
		acertosValor.add("500 Reais");
		acertosValor.add("600 Reais");
		acertosValor.add("700 Reais");
		acertosValor.add("800 Reais");
		acertosValor.add("900 Reais");
		acertosValor.add("1 mil");
		acertosValor.add("2 mil");
		acertosValor.add("3 mil");
		acertosValor.add("4 mil");
		acertosValor.add("5 mil");
		acertosValor.add("6 mil");
		acertosValor.add("10 mil");
		acertosValor.add("20 mil");
		acertosValor.add("30 mil");
		acertosValor.add("40 mil");
		acertosValor.add("50 mil");
		acertosValor.add("60 mil");
		acertosValor.add("100 mil");
		acertosValor.add("200 mil");
		acertosValor.add("300 mil");
		acertosValor.add("400 mil");
		acertosValor.add("500 mil");
		acertosValor.add("600 mil");
		acertosValor.add("1 MILHÃO");
		acertosValor.add("1 MILHÃO");
	}

	public void update(Observable o, final Object arg) {

		if (arg instanceof Double) {
			Platform.runLater(new Runnable() {
				public void run() {
					if ((Double) arg > 30)
						relogio.setTextFill(Color.GREEN);
					if ((Double) arg <= 30 && (Double) arg >= 10)
						relogio.setTextFill(Color.ORANGE);
					if ((Double) arg < 5) {
						relogio.setVisible(true);
						relogio.setTextFill(Color.RED);
					}
						

					relogio.setText("" + Round.round((Double) arg, 2));
				}
			});
		}
		
		if (arg instanceof String) {
			if (arg.equals("CAMPEAO"))
				System.out.println("voce foi campeao");
				fimDeJogo.setText("Voce ganhou 1 milhao");
				fimDeJogo.setVisible(true);
				gameStop();
		}

		if (arg instanceof Boolean) {
			if ((Boolean) arg == false)
				gameStop();
		}
	}
}
