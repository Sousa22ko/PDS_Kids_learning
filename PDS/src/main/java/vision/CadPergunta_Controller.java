package vision;

import java.io.UnsupportedEncodingException;

import dao.PerguntaDao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.Pergunta;
import sources.ScreenConstants;
import util.LoggedUser;
import util.ScreenLibrary;

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
	private ChoiceBox<String> correta = new ChoiceBox<String>(FXCollections.observableArrayList("correta 1", "correta 2", "correta 3", "correta 4"));
	
	@FXML
	private Pane background;
	
	@FXML
	private Pane logo;
	
	
	@FXML
	public void initialize() {
		correta = new ChoiceBox<String>(FXCollections.observableArrayList("correta 1", "correta 2", "correta 3", "correta 4"));
	}
	
	
	@FXML
	public void handlerCadastrarPergunta(){
		
		Pergunta nova = new Pergunta();
		
		nova.setPergunta(pergunta.getText());
		nova.setAlternativa1(alt1.getText());
		nova.setAlternativa2(alt2.getText());
		nova.setAlternativa3(alt3.getText());
		nova.setAlternativa4(alt4.getText());
		nova.setCorreta(correta.getValue());
		nova.setIdUser(LoggedUser.getLoggedUser().getId());

		PerguntaDao pd = new PerguntaDao();
		pd.adicionar(nova);
		
		
		try {
			ScreenLibrary.LoadTela(ScreenConstants.IDHOME);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void handlerVoltar(){
		try {
			ScreenLibrary.LoadTela(ScreenConstants.IDHOME);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
