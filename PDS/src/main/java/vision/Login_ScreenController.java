package vision;

import java.io.UnsupportedEncodingException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import sources.ScreenConstants;
import util.ScreenLibrary;
import util.SourcesLoader;

public class Login_ScreenController {

	@FXML
	private Button login;

	//private User remote = new User();

	@FXML
	public void initialize() {
		//SourcesLoader.loadBackground(background);
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
		//SceneBuilder.loadNewAccount();
	}


}
