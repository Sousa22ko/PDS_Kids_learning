package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProducer {
	private EntityManagerFactory factory;
	private EntityManager manager;
	
	public EntityManagerProducer() {
		factory = 
				Persistence.createEntityManagerFactory("KidsLearning");
	}
	
	public EntityManager createEntityManger() {
		manager = factory.createEntityManager();
		return manager;
	}
	
	public void closeEntityManager(EntityManager manager) {
		manager.close();
	}
	
	public void closeEntityManager() {
		if (manager != null)
			manager.close();
		if (factory != null)
			factory.close();
	}
	
	public static void main(String... args) {
		new EntityManagerProducer();
	}
}