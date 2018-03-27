package util;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ScreenBuilder {
	
	//para criar as telas é preciso criar um metodo no setor ali em baixo

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
	//blz, mas cheguei até aqui e n sei como trocar de tela...
	//É SOMENTE CHAMAR O ScreenLibrary.loadTela(id da tela);
	//sim, é só isso, chama o metodo estatico la em qualquer parte do controller
	//se eu n caguei nada na refatoração ele deve trocar automaticamente de tela
	//PELO AMOR DE DEUS, n precisa declarar variavel tipo ScreenLibrary no controller(nem funcionaria)
	//afinal quem renderiza sao metodos estaticos!!!
	
	
	public static void renderLoginScreen() {

		path = "Login_Screen.fxml"; // arquivo fxml q vcs vão abrir la no screnebuilder (o programa)
		title = "Login"; // nome q aparece no topo da tela
		buildScreen();

	}

	public static void renderHomePage() {

		path = "Home.fxml";
		title = "Home";
		buildScreen();

	}

	public static void renderSearchMatchPage() {

		path = "SearchMatch.fxml";
		title = "Procurar partida";
		buildScreen();

	}

	public static void renderLeague() {

		path = "League.fxml";
		title = "Liga atual";
		buildScreen();

	}

	public static void renderNewMatch() {

		path = "NewMatch.fxml";
		title = "Criar partida";
		buildScreen();

	}

	public static void renderOldResults() {

		path = "OldResults.fxml";
		title = "Resultados anteriores";
		buildScreen();

	}

	public static void renderChallenger() {

		path = "Challenger.fxml";
		title = "Desafiar jogador";
		buildScreen();

	}

	public static void renderConfig() {

		path = "Config.fxml";
		title = "Configurações";
		buildScreen();

	}

	public static void renderUserView() {

		path = "UserView.fxml";
		title = "Usuario";
		buildScreen();

	}

	public static void renderUserViewEdit() {

		path = "UserViewEdit.fxml";
		title = "Editar informações pessoais do usuario";
		buildScreen();

	}

	public static void renderNewAccount() {
		path = "NewAccount.fxml";
		title = "Cadastrar nova conta";
		buildScreen();

	}

	public static void renderMatch() {
		path = "Match.fxml";
		title = "Let's Rock it";
		buildScreen();

	}
}
