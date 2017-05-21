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
public class BrandTest extends Assert {
	static EntityManagerFactory factory;
	static EntityManager manager;
	static EntityTransaction transaction;
	static final String persistenceUnitName = "beer_storage";
	
	static Brand brewing_method = null;
	
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
	public void T_001_addBrewingMethod() {
		clean();
		brewing_method = new Brand("Bockbier");
		assertNotNull(brewing_method);
		manager.persist(brewing_method);
		transaction.commit();				
	}
	
	@Test
	public void T_002_removeBrewingMethod() {
		brewing_method = manager.find(Brand.class, "Bockbier");
		assertNotNull(brewing_method);
		
		transaction.begin();
		manager.remove(brewing_method);
		transaction.commit();
		
		brewing_method = manager.find(Brand.class, brewing_method.getBrewingMethod());
		assertNull(brewing_method);
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
		manager.createNativeQuery("DELETE FROM brand").executeUpdate();
	}
}
