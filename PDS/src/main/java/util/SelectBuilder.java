package util;

import java.io.UnsupportedEncodingException;

public class SelectBuilder extends ScreenBuilder {

	public static void load(int id) throws UnsupportedEncodingException {
		path = "SelectAplication.fxml"; 
		title = "Selecione a aplicação";
		buildScreen();
	}
}
