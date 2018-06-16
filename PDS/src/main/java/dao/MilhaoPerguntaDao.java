package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import model.MilhaoPergunta;

public class MilhaoPerguntaDao extends GenericDao<MilhaoPergunta, Long>{
	
	
	public MilhaoPerguntaDao() {
		super(MilhaoPergunta.class);
	}
	
	public MilhaoPergunta getPerguntaById(long id){		
		return (MilhaoPergunta)executeQuery("from EnemPergunta where id = :id").setParameter("id", id)
				.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<MilhaoPergunta> getPerguntaByUserId(long id){
		
		List<MilhaoPergunta> perg = new ArrayList<MilhaoPergunta>();
		Query q = executeQuery("from MilhaoPergunta where id_user = :id").setParameter("id", id);
		perg = q.getResultList();
		return perg; 
	}


}