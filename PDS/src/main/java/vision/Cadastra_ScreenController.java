package vision;

import java.io.UnsupportedEncodingException;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import sources.ScreenConstants;
import util.ScreenLibrary;
import util.SourcesLoader;

public class Cadastra_ScreenController {

	
		@FXML
		private TextField nomeT; 
		
		@FXML
		private TextField emailT;
		
		@FXML
		private TextField userT;
		
		@FXML
		private TextField instT; 
		
		@FXML
		private DatePicker niver;
		
		@FXML
		private PasswordField senhaT;
		
		@FXML
		private ChoiceBox<String> tipo;
		
		@FXML
		private Pane background;
		
		
		@FXML
		public void initialize() {
			SourcesLoader.loadBackground(background);
			tipo = new ChoiceBox<String>(FXCollections.observableArrayList("op1", "op2", "op3"));
		}
		
		@FXML void Handler_Voltar(){
			try {
				ScreenLibrary.LoadTela(ScreenConstants.IDLOGIN);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		
		@FXML void Handler_cadastrar(){
			
			//persiste no BD			
			
			try {
				ScreenLibrary.LoadTela(ScreenConstants.IDLOGIN);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
}
