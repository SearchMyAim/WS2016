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
public class ProducerTest extends Assert {
	static EntityManagerFactory factory;
	static EntityManager manager;
	static EntityTransaction transaction;
	static final String persistenceUnitName = "beer_storage";
	
	static Producer producer = null;
	
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
	public void A_connectionTest() {
		transaction.begin();
	}
	
	@Test
	public void T_001_addProducer() {
		clean();
		producer = new Producer("Puntigamer");
		assertNotNull(producer);
		manager.persist(producer);
		transaction.commit();				
	}
	
	@Test
	public void T_002_removeProducer() {
		producer = manager.find(Producer.class, "Puntigamer");
		assertNotNull(producer);
		
		transaction.begin();
		manager.remove(producer);
		transaction.commit();
		
		producer = manager.find(Producer.class, producer.getName());
		assertNull(producer);
	}
	
	@AfterClass
	public static void tearDown() {
		if(manager == null) {
			return;
		}
		manager.close();
		factory.close();
	}
	
	public static void clean() {
		manager.createNativeQuery("DELETE FROM producer").executeUpdate();
	}
}
