package Services;

import java.util.List;

import dao.GenericDao;

public abstract class AbstractPergServices <T> {

	protected GenericDao<T, Long> perguntaDao ;
	protected T pergunta;
	protected long id;
	
	public GenericDao<T, Long> getDao() {
		return perguntaDao;
	}
	
	public abstract void adicionar(T remote) throws Exception;
	
	public void atualizar(T remote){
		perguntaDao.atualizar((T)remote); 	
	}
	
	public T getPerguntaById(Long id){
		return (T) perguntaDao.buscar(id);
	}

	public abstract T absRandomPerg() ;
	{	
		//return perguntaDao.buscar((long)new Random().nextInt(perguntaDao.getList().size()-1)+1);
	}
	
	public abstract List<T> listandoPerguntas(long idUser);
}