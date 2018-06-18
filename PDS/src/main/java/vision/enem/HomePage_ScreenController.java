package vision.enem;

import java.io.UnsupportedEncodingException;

import Services.UserServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import sources.ScreenConstants;
import util.SharedInfo;
import util.ScreenLibrary;

public class HomePage_ScreenController { //inserindo um comentário só para conseguir enviar pro git

	@FXML
	private Pane backPane;

	@FXML
	private Button novaPergunta;

	@FXML
	private Button listaPergunta;

	@FXML
	public void initialize() {
		if (UserServices.getUserConnected().getTipoUsuario() == 1) {
			novaPergunta.setVisible(false);
			listaPergunta.setVisible(false);
		}
	}

	@FXML
	public void handlerNovaPergunta() {
		try {
			SharedInfo.setId((long) -1);
			ScreenLibrary.LoadTela(ScreenConstants.IDCADPERG, SharedInfo.getInstance());
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	}

	@FXML
	public void handlerListaPergunta() {
		try {
			ScreenLibrary.LoadTela(ScreenConstants.LISTPERG, SharedInfo.getInstance());
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	}

	@FXML
	public void handlerClassico() {
		try {
			ScreenLibrary.LoadTela(ScreenConstants.CLASSIC, SharedInfo.getInstance());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void handlerFriends() {
		try {
			ScreenLibrary.LoadTela(ScreenConstants.FRIENDS, SharedInfo.getInstance());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
