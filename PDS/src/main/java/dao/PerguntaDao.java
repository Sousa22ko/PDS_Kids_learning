package dao;

import model.Pergunta;
import model.User;

public class PerguntaDao extends GenericDao<Pergunta, Long>{
	
	public PerguntaDao() {
		super(Pergunta.class);
	}
	
	public Pergunta getPerguntaById(long id){		
		return (Pergunta)executeQuery("from Pergunta where id = :id").setParameter("id", id)
				.getSingleResult();
	}

}
