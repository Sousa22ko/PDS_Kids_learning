package Services;

import java.io.UnsupportedEncodingException;

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
	public static void createUserAdd(String nome, String email, String userName, String inst, String senha,
			String value) {

		user = new User();

		if (userName != null && userName.length() < 30) {
			if (checkUni(userName)) {
				user.setUserName(userName);
			} else {
				throw new IllegalArgumentException("Usuario indisponivel");
			}
		} else {
			throw new IllegalArgumentException("Usuario vazio ou muito grande (maximo 30 caracteres)");
		}

		System.out.println("User check");
		if (EmailValidator.getInstance().isValid(email)) {
			user.setEmail(email);
		} else {
			throw new IllegalArgumentException("Email invalido");
		}
		System.out.println("Email check");

		user.setNome(nome);
		user.setEmail(email);
		user.setUserName(userName);
		user.setInstit(inst);
		user.setSenha(senha);

		if (value == "Aluno")
			user.setTipoUsuario(1);
		else if (value == "Professor")
			user.setTipoUsuario(2);
		else
			user.setTipoUsuario(3);
		userDao.adicionar(user);

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
			throw new NoResultException("Usuario não encontrado");
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