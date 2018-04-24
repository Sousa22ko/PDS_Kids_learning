package vision;
	
import javafx.application.Application;
import javafx.stage.Stage;
import sources.ScreenConstants;
import util.SceneBuilder;


public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {		
		SceneBuilder.setPrimaryStage(primaryStage);// carrega o stage... a brincadeira come�a aqui em baixo
		SceneBuilder.LoadScreen(ScreenConstants.IDLOGIN);// <-- v�o dando crtl click no metodo pra ver como funciona
		
	}
}
