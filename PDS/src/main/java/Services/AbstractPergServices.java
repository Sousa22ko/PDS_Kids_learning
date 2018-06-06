package Services;

import java.util.List;
import java.util.Random;

import dao.PerguntaDao;
import model.Pergunta;

public abstract class AbstractPergServices {

	public static PerguntaDao perguntaDao = new PerguntaDao(); // banco com perguntas
	public static Pergunta pergunta;
	public static long id;
	
	public PerguntaDao getDao() {
		return perguntaDao;
	}
	
	public void adicionar(Pergunta remote){
		perguntaDao.adicionar(remote); 	
	}
	
	public void atualizar(Pergunta remote){
		perguntaDao.atualizar(remote); 	
	}
	
	public Pergunta getPerguntaById(Long id){
		return perguntaDao.getPerguntaById(id);
	}

	public abstract Pergunta randomPerg();
	
	public abstract List<Pergunta> listandoPerguntas(long idUser);
}