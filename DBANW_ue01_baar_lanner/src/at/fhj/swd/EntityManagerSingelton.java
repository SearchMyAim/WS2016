package at.fhj.swd;

import static org.junit.Assert.assertNotNull;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerSingelton {
	
	private static EntityManager INSTANCE;
	private static final String persistenceUnitName = "beer_storage";
	private static EntityManagerFactory factory;

	public EntityManagerSingelton() {
		if (INSTANCE == null) {
			factory = Persistence.createEntityManagerFactory(persistenceUnitName);
			assertNotNull(factory);
			INSTANCE = factory.createEntityManager();
			assertNotNull(INSTANCE);
		} else {
			return;
		}
	}

	public EntityManager getInstance() {
		return INSTANCE;
	}
	
}
