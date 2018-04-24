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

	public void initialize() {
		// TODO Auto-generated method stub
	}
}
