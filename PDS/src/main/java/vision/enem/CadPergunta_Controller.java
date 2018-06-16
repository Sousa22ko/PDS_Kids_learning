package vision.enem;

import java.io.UnsupportedEncodingException;

import Services.EnemPergServices;
import dao.EnemPerguntaDao;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.EnemPergunta;
import sources.ScreenConstants;
import util.LoggedUser;
import util.ScreenLibrary;
import util.SharedInfo;

public class CadPergunta_Controller {

	
	@FXML
	private Label error;
	
	@FXML
	private TextField pergunta;
	
	@FXML
	private TextField alt1;
	
	@FXML 
	private TextField alt2;
	
	@FXML
	private TextField alt3;
	
	@FXML
	private TextField alt4;
	
	@FXML
	private TextField alt5; // -------------------------------------- adicionar fxml
	
	@FXML
	private ChoiceBox<String> correta = new ChoiceBox<String>();

	@FXML
	private Pane background;
	
	@FXML
	private Pane logo;
	
	private long idPerguntaRecebida = 0;
	
	private boolean update = false;
	private EnemPergServices ps = new EnemPergServices();
	
	@FXML
	public void initialize() {
		correta.getItems().addAll("Alternativa 1", "Alternativa 2", "Alternativa 3", "Alternativa 4");
		idPerguntaRecebida = SharedInfo.getId();
		
		System.out.println(idPerguntaRecebida + " vo te tacar o piru");
		if(idPerguntaRecebida != -1){
			update = true;
			
			EnemPergunta recebida = ps.getPerguntaById(idPerguntaRecebida);
			
			pergunta.setText(recebida.getPergunta());
			alt1.setText(recebida.getAlternativa1());
			alt2.setText(recebida.getAlternativa2());
			alt3.setText(recebida.getAlternativa3());
			alt4.setText(recebida.getAlternativa4());
			
		}
	}
	
	@FXML
	public void handlerPreencheCB(){
	}
	
	@FXML
	public void handlerCadastrarPergunta(){
		
		EnemPergunta nova = new EnemPergunta();
		
		nova.setPergunta(pergunta.getText());
		nova.setAlternativa1(alt1.getText());
		nova.setAlternativa2(alt2.getText());
		nova.setAlternativa3(alt3.getText());
		nova.setAlternativa4(alt4.getText());
		nova.setAlternativa5(alt5.getText());
		nova.setCorreta(correta.getValue());
		nova.setIdUser(LoggedUser.getLoggedUser().getId());

		
		if(update == false)
			try {
				ps.adicionar(nova);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		else
			ps.atualizar(nova);
		
		try {
			ScreenLibrary.LoadTela(ScreenConstants.IDHOME, SharedInfo.getInstance());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
	}
	
	@FXML
	public void handlerAtualizarPergunta(){
		
		EnemPergunta nova = new EnemPergunta();
		EnemPerguntaDao pd = new EnemPerguntaDao();
		
		nova.setPergunta(pergunta.getText());
		nova.setAlternativa1(alt1.getText());
		nova.setAlternativa2(alt2.getText());
		nova.setAlternativa3(alt3.getText());
		nova.setAlternativa4(alt4.getText());
		
		nova.setCorreta(correta.getValue());
		nova.setIdUser(LoggedUser.getLoggedUser().getId());

		
		pd.atualizar(nova);
		
		
		try {
			ScreenLibrary.LoadTela(ScreenConstants.IDHOME, SharedInfo.getInstance());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
	}
	
	@FXML
	public void handlerVoltar(){
		try {
			ScreenLibrary.LoadTela(ScreenConstants.IDHOME, SharedInfo.getInstance());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public void recebeIdPergunta(long id){
		idPerguntaRecebida = id;
		System.out.println("ESTAMOS RECEBENDO NO CONTROLLER COM O ID: "+idPerguntaRecebida);
	}
	
}
