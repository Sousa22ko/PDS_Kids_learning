package util;

import model.User;

public class LoggedUser {
	
	private static User log = new User();
	
	public static void setUserLogged(User logado){
		
		log = logado;
	}
	
	public static User getLoggedUser(){
		return log;
	}

}
