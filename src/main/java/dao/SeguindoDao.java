package dao;

import model.Seguindo;

public class SeguindoDao extends GenericDao<Seguindo, Long> {

	public SeguindoDao() {
		super(Seguindo.class);
	}
	
	public Seguindo getUserBySeguidor(Long seguidor){		
		return (Seguindo)executeQuery("from User where idUser = :idUser").setParameter("idUser", seguidor)
				.getSingleResult();
	}

	public void initialize() {
		// TODO Auto-generated method stub
	}
}
