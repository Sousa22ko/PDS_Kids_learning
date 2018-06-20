package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import model.EnemPergunta;

public class EnemPerguntaDao extends GenericDao<EnemPergunta, Long>{
	
	
	public EnemPerguntaDao() {
		super(EnemPergunta.class);
	}
	
	public EnemPergunta getPerguntaById(long id){		
		return (EnemPergunta)executeQuery("from EnemPergunta where id = :id").setParameter("id", id)
				.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<EnemPergunta> getPerguntaByUserId(long id){
		
		List<EnemPergunta> perg = new ArrayList<EnemPergunta>();
		Query q = executeQuery("from EnemPergunta where id_user = :id").setParameter("id", id);
		perg = q.getResultList();
		return perg; 
	}


}