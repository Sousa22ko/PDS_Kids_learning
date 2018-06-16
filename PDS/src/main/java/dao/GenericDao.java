package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import util.EntityManagerProducer;

public class GenericDao<T, I> {
	protected EntityManager manager;
	private Class<T> classePersistida;

	public GenericDao(Class<T> classePersistida) {
		manager = new EntityManagerProducer().createEntityManger();
		this.classePersistida = classePersistida;
	}
	
	public GenericDao(EntityManager manager, Class<T> classePersistida) {
		this.classePersistida = classePersistida;
		this.manager = manager;
	}

	public T adicionar(T entidade) {
		EntityTransaction t = manager.getTransaction();
		t.begin();
		manager.persist(entidade);
		manager.flush();
		t.commit();
		return entidade;
	}
	
	public T atualizar(T entidade) {
		EntityTransaction t = manager.getTransaction();
		t.begin();
		manager.persist(entidade);
		manager.flush();
		t.commit();
		return entidade;
	}

	public T remover(I id) {
		T entity = buscar(id);
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		T mergedEntity = manager.merge(entity);
		manager.remove(mergedEntity);
		manager.flush();
		tx.commit();
		return entity;
	}

	public T buscar(I id) {
		return (T) manager.find(classePersistida, id);
	}

	public List<T> getList() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(classePersistida);
		query.from(classePersistida);
		return manager.createQuery(query).getResultList();
	}
	
	public Query executeNamedQuery(String query) {
		return manager.createNamedQuery(query);
	}
	
	public Query executeQuery(String query) {
		return manager.createQuery(query);
	}
	
	public void close(){
		if(manager.isOpen())
			manager.close();
	}

}
