package at.fhj.swd;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.AfterClass;
import org.junit.Assert;


@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class BeerTest extends Assert {
	static EntityManagerFactory factory;
	static EntityManager manager;
	static EntityTransaction transaction;
	
	static final String persistenceUnitName = "Beer";
	
	
	@BeforeClass 
	public static void setup() {
		factory = Persistence.createEntityManagerFactory(persistenceUnitName);
		assertNotNull(factory);
	
		manager = factory.createEntityManager();
		assertNotNull(manager);
		
		transaction = manager.getTransaction();
		assertNotNull(transaction);
	}
	
	@Test
	public void connectionTest() {
		transaction.begin();
	}
	
	@Test
	public void addPerson() {
		transaction.begin();
		manager.createNativeQuery("TRUNCATE TABLE Person").executeUpdate();
		Person per = new Person("Alex", "m", "AUT", "028");
		assertNotNull(per);
		manager.persist(per);
		transaction.commit();
	}
	
	@AfterClass
	public static void tearDown() {
		if(manager == null) {
			return;
		}
		manager.close();
		factory.close();
	}
	
}
