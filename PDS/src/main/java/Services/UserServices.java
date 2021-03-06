package Services;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.commons.validator.routines.EmailValidator;

import dao.UserDao;
import model.User;
import util.LoggedUser;

public class UserServices {

	public static UserDao userDao = new UserDao();
	public static User user = new User();
	public static LoggedUser connected = new LoggedUser();

	public static UserDao getDao() {
		return userDao;
	}
	
	public static void initialize(){
		userDao.initialize();
	}

	// persiste um usuario no bd
	public static void adicionar(User remote) {
		userDao.adicionar(remote);
	}

	// cria um usuario e persiste ele no BD
	public static void createUserAdd(User novo){//String nome, String email, String userName, String inst, String senha,
			//String value) {


		if (novo.getNome() != null && novo.getNome().length() < 30) {
			if (!checkUni(novo.getNome())) {
				throw new IllegalArgumentException("Usuario indisponivel");
			}
		} else {
			throw new IllegalArgumentException("Usuario vazio ou muito grande (maximo 30 caracteres)");
		}

		System.out.println("User check");
		
		if (!EmailValidator.getInstance().isValid(novo.getEmail())) {
			throw new IllegalArgumentException("Email invalido");
		}
		System.out.println("Email check");
		
		if (novo.getSenha().length() == 0  || novo.getSenha().length() >= 30) {
			throw new IllegalArgumentException("Senha Vazia ou muito grande");
		}

		userDao.adicionar(novo);

	}

	//lista todos os usu�rios exceto o que est� logado
	public static List<User> listUsers(){
		List<User> listUser = new ArrayList<User>();
		//listUser = userDao.getList();
		for(int i=0; i<userDao.getList().size(); i++){
			if(getUserConnected().getId() != userDao.getList().get(i).getId()){
				listUser.add(userDao.getList().get(i));
			}
		}
		return listUser;
	}
	
	//lista todos os usu�rios que possuem a string 'nome' em alguma parte do seu nome, email ou username
	public static List<User> listUsersByName(String nome){
		List<User> listUser = new ArrayList<User>();
		for(int i=0; i<userDao.getList().size(); i++){
			if(containsIgnoreCase(userDao.getList().get(i).getNome(), nome) ||
					containsIgnoreCase(userDao.getList().get(i).getEmail(), nome) ||
					containsIgnoreCase(userDao.getList().get(i).getUserName(), nome)){
				listUser.add(userDao.getList().get(i));
			}
		}
		return listUser;
	}
	
	//m�todo para buscar os nomes no banco ignorando letras mai�sculas (FUNCIONA)
	//https://stackoverflow.com/questions/14018478/string-contains-ignore-case
	public static boolean containsIgnoreCase(String str, String searchStr)     {
	    if(str == null || searchStr == null) return false;

	    final int length = searchStr.length();
	    if (length == 0)
	        return true;

	    for (int i = str.length() - length; i >= 0; i--) {
	        if (str.regionMatches(true, i, searchStr, 0, length))
	            return true;
	    }
	    return false;
	}
	
	private static boolean checkUni(String userName) {
		System.out.println(userName);
		try {
			user = userDao.getUserByUserName(userName);
		} catch (NoResultException e) {
			return true;
		}
		return false;
	}

	public static void login(String usuario, String senha) throws UnsupportedEncodingException {

		try {
			user = userDao.getUserByUserName(usuario);
		} catch (NoResultException e) {
			throw new NoResultException("Usuario n�o encontrado");
		}
		
		//---
		
		if (comparePassword(user, senha)) {
			LoggedUser.setUserLogged(user);
		}else
			throw new UnsupportedEncodingException("Senha incorreta");
	}

	public static boolean comparePassword(User u1, String senha) {

		if (u1.getSenha().equals(senha)) {
			return true;
		} else
			return false;
	}

	public static User getUserConnected() {
		return LoggedUser.getLoggedUser();
	}
}