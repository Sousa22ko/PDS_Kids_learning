package main;
	
import Services.UserServices;
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
		
		UserServices.initialize();
		SharedInfo.setInstance(0);
		SceneBuilder.setPrimaryStage(primaryStage);// carrega o stage... a brincadeira começa aqui em baixo
		SceneBuilder.LoadScreen(ScreenConstants.SELECIONAPP , SharedInfo.getInstance());// <-- vão dando crtl click no metodo pra ver como funciona
		
	}
}
