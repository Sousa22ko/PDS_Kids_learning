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
import util.Cronometro;
import util.Round;
import util.ScreenLibrary;

public class Classico_ScreenController{

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

	//@FXML
	//private Label relogio;
	
	@FXML
	private Label vidas;

	@FXML
	private Label pergunta;

	private Pergunta atual = new Pergunta();

	@FXML
	public void initialize() {
		op1.setVisible(false);
		op2.setVisible(false);
		op3.setVisible(false);
		op4.setVisible(false);
		pergunta.setVisible(false);
		extra.setVisible(false);
		vidas.setText("3");
		vidas.setVisible(false);
		//relogio.setVisible(false);
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
		//relogio.setVisible(true);
		comecar.setVisible(false);
		
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

		op1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (atual.getCorreta().equals("alternativa1")){
					extra.setTextFill(Color.GREEN);
					extra.setText("ACERTOU");
				}
				else{
					extra.setTextFill(Color.RED);
					extra.setText("ERROU");
					int qtdVidas = Integer.parseInt(vidas.getText());
					vidas.setText(Integer.toString(qtdVidas-1)); //alterando o valor das vidas
				}
				checaVidas();
				loadPergunta();
			}
		});

		op2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (atual.getCorreta().equals("alternativa2")){
					extra.setTextFill(Color.GREEN);
					extra.setText("ACERTOU");
				}
				else{
					extra.setTextFill(Color.RED);
					extra.setText("ERROU");
					int qtdVidas = Integer.parseInt(vidas.getText());
					vidas.setText(Integer.toString(qtdVidas-1)); //alterando o valor das vidas
				}
				checaVidas();
				loadPergunta();
			}
		});

		op3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (atual.getCorreta().equals("alternativa3")){
					extra.setTextFill(Color.GREEN);
					extra.setText("ACERTOU");
				}
				else{
					extra.setTextFill(Color.RED);
					extra.setText("ERROU");
					int qtdVidas = Integer.parseInt(vidas.getText());
					vidas.setText(Integer.toString(qtdVidas-1)); //alterando o valor das vidas
				}
				checaVidas();
				loadPergunta();
			}
		});

		op4.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (atual.getCorreta().equals("alternativa4")){
					extra.setTextFill(Color.GREEN);
					extra.setText("ACERTOU");
				}
				else{
					extra.setTextFill(Color.RED);
					extra.setText("ERROU");
					int qtdVidas = Integer.parseInt(vidas.getText());
					vidas.setText(Integer.toString(qtdVidas-1)); //alterando o valor das vidas
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
		//relogio.setVisible(false);
		extra.setVisible(false);
		vidas.setVisible(false);
		vidas.setText("3");
		
	}
	
	public void gameBreak() {
		op1.setVisible(false);
		op2.setVisible(false);
		op3.setVisible(false);
		op4.setVisible(false);
		pergunta.setVisible(false);
		//relogio.setVisible(false);
	}
	
	/*
	 * Método para checar as vidas do usuário
	 * Caso ela esteja em zero, retorna falso para continuação do jogo
	 */
	public void checaVidas(){
		if(Integer.parseInt(vidas.getText()) == 0)
			gameBreak();
		else
			loadPergunta();
	}

}
