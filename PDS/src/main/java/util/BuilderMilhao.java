package util;

import java.io.UnsupportedEncodingException;

import sources.ScreenConstants;

public class BuilderMilhao extends ScreenBuilder{
	
	public static void renderLoginScreen() {

		path = "/vision/kids/Login_Screen.fxml"; // arquivo fxml q vcs v�o abrir la no screnebuilder (o programa)
		title = "Login"; // nome q aparece no topo da tela
		buildScreen();

	}
	
	private static void renderHomePage() {

		path = "/vision/kids/HomePage_Screen.fxml"; // arquivo fxml q vcs v�o abrir la no screnebuilder (o programa)
		title = "Home"; // nome q aparece no topo da tela
		buildScreen();

	}
	
	public static void renderCadastroPergunta() {

		path = "/vision/kids/CadPergunta_Screen.fxml"; // arquivo fxml q vcs v�o abrir la no screnebuilder (o programa)
		title = "Cadastro de pergunta"; // nome q aparece no topo da tela
		buildScreen();

	}
	
	public static void renderContraOTempo() {

		path = "/vision/kids/ContraOTempo_Screen.fxml"; // arquivo fxml q vcs v�o abrir la no screnebuilder (o programa)
		title = "Contra o Tempo"; // nome q aparece no topo da tela
		buildScreen();

	}
	
	public static void renderClassico() {

		path = "/vision/kids/Classico.fxml"; // arquivo fxml q vcs v�o abrir la no screnebuilder (o programa)
		title = "Classico"; // nome q aparece no topo da tela
		buildScreen();

	}
	
	public static void renderFriends() {

		path = "/vision/kids/Friend_Screen.fxml"; // arquivo fxml q vcs v�o abrir la no screnebuilder (o programa)
		title = "Lista de amigos"; // nome q aparece no topo da tela
		buildScreen();

	}
	
	public static void renderListPerg() {

		path = "/vision/kids/ListQuestions.fxml"; // arquivo fxml q vcs v�o abrir la no screnebuilder (o programa)
		title = "Suas Perguntas"; // nome q aparece no topo da tela
		buildScreen();

	}

	public static void load(int id) throws UnsupportedEncodingException {
		if(id == ScreenConstants.IDLOGIN)
			BuilderMilhao.renderLoginScreen();// <-- crtl click na classe screenbuilder
		
		else if(id == ScreenConstants.IDHOME)
			BuilderMilhao.renderHomePage();
		
		else if(id == ScreenConstants.IDCADPERG)
			BuilderMilhao.renderCadastroPergunta();
		
		else if(id == ScreenConstants.IDAGAINSTTIME)
			BuilderMilhao.renderContraOTempo();
		
		else if(id == ScreenConstants.CLASSIC)
			BuilderMilhao.renderClassico();
		
		else if(id == ScreenConstants.FRIENDS)
			BuilderMilhao.renderFriends();
		
		else if(id == ScreenConstants.LISTPERG)
			BuilderMilhao.renderListPerg();
		
		
		else throw new UnsupportedEncodingException("tela indefida");
		
	}

}
