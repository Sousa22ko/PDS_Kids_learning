package dao;

import model.User;

public class UserDao extends GenericDao<User, Long> {

	public UserDao() {
		super(User.class);
	}
	
	public User getUserByUserName(String userName){		
		return (User)executeQuery("from User where userName = :userName").setParameter("userName", userName)
				.getSingleResult();
	}
	
	public static boolean comparePassword(User u1, User u2){
		
		if(u1.getNome().equals(u2.getNome())){
			if(u1.getSenha().equals(u2.getSenha())){
				return true;
			}
			return false;
		}
		return false;
	}
}
