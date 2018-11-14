package util;

import java.awt.Toolkit;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import main.Main;

public class SceneBuilder {

	public static void setPrimaryStage(Stage stage) {
		ScreenLibrary.setStage(stage);
	}

	public static void setDimensions() {
		ScreenLibrary.setDimension(Toolkit.getDefaultToolkit().getScreenSize());
	}

	public static Stage getStage() {
		return ScreenLibrary.getStage();
	}

	public static Object getComponent(String path) {

		setDimensions();

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource(path));

		try {
			return loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void addPaneComponent(Node novo) {
		ScreenLibrary.getPane().getChildren().add(novo);
	}
	//---------------------------------------------remove objetos dinamicos da tela
	public static void removePaneComponent(Node removed) {
		ScreenLibrary.getPane().getChildren().remove(removed);
	}

	// --------------------------------------------renderiza��o dos cenarios
	public static void LoadScreen(int id, int instance) throws UnsupportedEncodingException {
		ScreenLibrary.LoadTela(id, instance);// <----
	}


}
