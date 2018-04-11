package util;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ScreenBuilder {
	
	//para criar as telas � preciso criar um metodo no setor ali em baixo

	public static String path;
	public static String title;
	private static AnchorPane pane;
	private static Stage primaryStage;

	// carrega o pane e o stage
	public static void load_stage(AnchorPane paneout, Stage primaryStageout) {
		pane = paneout;
		primaryStage = primaryStageout;
	}

	// -----------------------------------constroi as telas
	public static void buildScreen() {
		pane = (AnchorPane) SceneBuilder.getComponent(path);
		primaryStage.setTitle(title);

		primaryStage.setMaximized(true);
		primaryStage.setResizable(false);
		primaryStage.setScene(new Scene(pane));

		primaryStage.show();

	}

	// ------------------------------ cria as telas apartir daqui --------------------------------------//
	//usem uma assinatura parecida !!
	//public static void renderNomeDaTelaQVcsQuiserem();
	//blz, mas cheguei at� aqui e n sei como trocar de tela...
	//� SOMENTE CHAMAR O ScreenLibrary.loadTela(id da tela);
	//sim, � s� isso, chama o metodo estatico la em qualquer parte do controller
	//se eu n caguei nada na refatora��o ele deve trocar automaticamente de tela
	//PELO AMOR DE DEUS, n precisa declarar variavel tipo ScreenLibrary no controller(nem funcionaria)
	//afinal quem renderiza sao metodos estaticos!!!
	
	
	public static void renderLoginScreen() {

		path = "Login_Screen.fxml"; // arquivo fxml q vcs v�o abrir la no screnebuilder (o programa)
		title = "Login"; // nome q aparece no topo da tela
		buildScreen();

	}
	
	public static void renderHomePage() {

		path = "HomePage_Screen.fxml"; // arquivo fxml q vcs v�o abrir la no screnebuilder (o programa)
		title = "Home"; // nome q aparece no topo da tela
		buildScreen();

	}
	
	public static void renderCadastroPergunta() {

		path = "CadPergunta_Screen.fxml"; // arquivo fxml q vcs v�o abrir la no screnebuilder (o programa)
		title = "Cadastro de pergunta"; // nome q aparece no topo da tela
		buildScreen();

	}

}
