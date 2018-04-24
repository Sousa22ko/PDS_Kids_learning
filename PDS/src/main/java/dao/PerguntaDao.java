package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

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
	
	public List<Pergunta> getPerguntaByUserId(long id){
		
		List<Pergunta> perg = new ArrayList<Pergunta>();
		Query q = executeQuery("from Pergunta where id_user = :id").setParameter("id", id);
		perg = q.getResultList();
		return perg; 
	}


}