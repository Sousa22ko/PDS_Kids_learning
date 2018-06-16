package Services;

import java.util.List;
import java.util.Random;

import dao.GenericDao;
import model.HPergunta;

public abstract class AbstractPergServices <T> {

	protected GenericDao<T, Long> perguntaDao ;
	protected HPergunta pergunta;
	protected long id;
	
	public GenericDao<T, Long> getDao() {
		return perguntaDao;
	}
	
	public abstract void adicionar(T remote) throws Exception;
	
	public void atualizar(T remote){
		perguntaDao.atualizar((T)remote); 	
	}
	
	@SuppressWarnings("unchecked")
	public T getPerguntaById(Long id){
		return (T) perguntaDao.getPerguntaById(id);
	}

	public HPergunta randomPerg() {	
		return perguntaDao.getPerguntaById((long)new Random().nextInt(perguntaDao.getList().size()-1)+1);
	}
	
	public abstract List<T> listandoPerguntas(long idUser);
}