package vision;

import java.io.UnsupportedEncodingException;

import Services.UserServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import sources.ScreenConstants;
import util.ScreenLibrary;

public class HomePage_ScreenController {

	@FXML
	public Pane backPane;

	@FXML
	public void initialize() {
		if (UserServices.getUserConnected().getTipoUsuario() == 2
				|| UserServices.getUserConnected().getTipoUsuario() == 3) {
			Button cadastrarPergunta = new Button("Cadastrar nova pergunta");

			cadastrarPergunta.setPrefSize(100, 20);
			cadastrarPergunta.setLayoutX(16);
			cadastrarPergunta.setLayoutY(100);

			cadastrarPergunta.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					try {
						ScreenLibrary.LoadTela(ScreenConstants.IDCADPERG);
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
					return;
				}
			});

			backPane.getChildren().add(cadastrarPergunta);
		}

	}

	@FXML
	public void handlerContraOTempo() {
		try {
			ScreenLibrary.LoadTela(ScreenConstants.IDAGAINSTTIME);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
