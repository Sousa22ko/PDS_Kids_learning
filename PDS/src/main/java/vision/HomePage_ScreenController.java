package vision;
import java.io.UnsupportedEncodingException;

import javafx.fxml.FXML;
import sources.ScreenConstants;
import util.ScreenLibrary;

public class HomePage_ScreenController {

	
	
	@FXML
	public void handlerCadastrarPergunta(){
		try {
			ScreenLibrary.LoadTela(ScreenConstants.IDCADPERG);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
