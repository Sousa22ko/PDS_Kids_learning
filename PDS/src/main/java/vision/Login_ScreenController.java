package vision;

import java.io.UnsupportedEncodingException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import sources.ScreenConstants;
import util.ComponentLoad;
import util.SceneBuilder;
import util.ScreenLibrary;
import util.SourcesLoader;

public class Login_ScreenController {

	@FXML
	private Button login;
	
	@FXML
	private Button cad;
	
	
	
	@FXML
	private TextField user;
	
	@FXML
	private PasswordField pass;
	
	@FXML
	private Pane logicPane;
	
	@FXML
	private Pane background;
	
	@FXML
	private Pane logo;
	
	@FXML
	private AnchorPane masterPane;

	//private User remote = new User();

	@FXML
	public void initialize() {
		SourcesLoader.loadBackground(background);
		SourcesLoader.loadLogo(logo);
		
		//logicPane.setLayoutX((masterPane.getHeight() / 2) - (logicPane.getHeight() / 2));
		logicPane.setLayoutX(533);
		//logicPane.setLayoutY((masterPane.getHeight() / 2) - (logicPane.getHeight() / 2));
		logicPane.setLayoutY(174);
	}

	@FXML
	public void LoginHandler() {

			//LoggedUser.setUserLogged(remote);
		try {
			ScreenLibrary.LoadTela(ScreenConstants.IDHOME);
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		
	}

	@FXML
	public void CadastroHandler() {

		try {
			ScreenLibrary.LoadTela(ScreenConstants.CADASTRA);
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		
		
		//-------------------------------------------TO FIX--------------		
		/*
		TextField nomeT, emailT, userT, instT;
		DatePicker niver;
		PasswordField senhaT;	
		Pane overView;
		
		nomeT = new TextField();
		emailT = new TextField();
		userT = new TextField();
		instT = new TextField();
		niver = new DatePicker();
		senhaT = new PasswordField();
		overView = new Pane();
				
		nomeT.setPromptText("Nome*");
		emailT.setPromptText("Email*");
		userT.setPromptText("Usuario*");
		instT.setPromptText("Instituicao");
		niver.setPromptText("Nascimento");
		senhaT.setPromptText("Senha*");
	*/
		}


}
