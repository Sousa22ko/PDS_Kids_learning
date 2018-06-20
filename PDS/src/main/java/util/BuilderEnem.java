package util;

import java.io.UnsupportedEncodingException;

import sources.ScreenConstants;

public class BuilderEnem extends ScreenBuilder{
	
	public static void renderLoginScreen() {

		path = "/vision/enem/Login_Screen.fxml"; // arquivo fxml q vcs vão abrir la no screnebuilder (o programa)
		title = "Login"; // nome q aparece no topo da tela
		buildScreen();

	}
	
	public static void renderHomePage() {

		path = "/vision/enem/HomePage_Screen.fxml"; // arquivo fxml q vcs vão abrir la no screnebuilder (o programa)
		title = "Home"; // nome q aparece no topo da tela
		buildScreen();

	}
	
	public static void renderCadastroPergunta() {

		path = "/vision/enem/CadPergunta_Screen.fxml"; // arquivo fxml q vcs vão abrir la no screnebuilder (o programa)
		title = "Cadastro de pergunta"; // nome q aparece no topo da tela
		buildScreen();

	}
	
	public static void renderContraOTempo() {

		path = "/vision/enem/ContraOTempo_Screen.fxml"; // arquivo fxml q vcs vão abrir la no screnebuilder (o programa)
		title = "Contra o Tempo"; // nome q aparece no topo da tela
		buildScreen();

	}
	
	public static void renderClassico() {

		path = "/vision/enem/Classico.fxml"; // arquivo fxml q vcs vão abrir la no screnebuilder (o programa)
		title = "Classico"; // nome q aparece no topo da tela
		buildScreen();

	}
	
	public static void renderFriends() {

		path = "/vision/enem/Friend_Screen.fxml"; // arquivo fxml q vcs vão abrir la no screnebuilder (o programa)
		title = "Lista de amigos"; // nome q aparece no topo da tela
		buildScreen();

	}
	
	public static void renderListPerg() {

		path = "/vision/enem/ListQuestions.fxml"; // arquivo fxml q vcs vão abrir la no screnebuilder (o programa)
		title = "Suas Perguntas"; // nome q aparece no topo da tela
		buildScreen();

	}

	public static void load(int id) throws UnsupportedEncodingException {
		if(id == ScreenConstants.IDLOGIN)
			BuilderEnem.renderLoginScreen();// <-- crtl click na classe screenbuilder
		
		else if(id == ScreenConstants.IDHOME)
			BuilderEnem.renderHomePage();
		
		else if(id == ScreenConstants.IDCADPERG)
			BuilderEnem.renderCadastroPergunta();
		
		else if(id == ScreenConstants.IDAGAINSTTIME)
			BuilderEnem.renderContraOTempo();
		
		else if(id == ScreenConstants.CLASSIC)
			BuilderEnem.renderClassico();
		
		else if(id == ScreenConstants.FRIENDS)
			BuilderEnem.renderFriends();
		
		else if(id == ScreenConstants.LISTPERG)
			BuilderEnem.renderListPerg();
		
		
		else throw new UnsupportedEncodingException("tela indefida");
		
	}

}
