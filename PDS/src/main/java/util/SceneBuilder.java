package util;

import java.awt.Toolkit;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import vision.Main;

public class SceneBuilder {

	public static void setPrimaryStage(Stage stage) {
		ScreenLibrary.setStage(stage);
		//A primeira tarefa de vcs e a mais complicada é resolver esse erro
		//se der erro nisso chorem bastante pq resolver ele é um misterio
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

	// --------------------------------------------renderização dos cenarios
	public static void LoadScreen(int id) throws UnsupportedEncodingException {
		ScreenLibrary.LoadTela(id);// <----
	}


}
