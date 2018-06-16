package main;
	
import javafx.application.Application;
import javafx.stage.Stage;
import sources.ScreenConstants;
import util.SceneBuilder;
import util.SharedInfo;


public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		SharedInfo.setInstance(2);
		SceneBuilder.setPrimaryStage(primaryStage);// carrega o stage... a brincadeira começa aqui em baixo
		SceneBuilder.LoadScreen(ScreenConstants.IDLOGIN , SharedInfo.getInstance());// <-- vão dando crtl click no metodo pra ver como funciona
		
	}
}
