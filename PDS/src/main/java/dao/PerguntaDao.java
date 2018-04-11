package dao;

import model.Pergunta;

public class PerguntaDao extends GenericDao<Pergunta, Long>{
	
	protected Object close;

	public PerguntaDao() {
		super(Pergunta.class);
	}
	
	public Pergunta getPerguntaById(long id){		
		return (Pergunta)executeQuery("from Pergunta where id = :id").setParameter("id", id)
				.getSingleResult();
	}


}