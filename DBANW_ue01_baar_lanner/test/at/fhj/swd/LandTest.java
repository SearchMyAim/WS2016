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
public class LandTest extends Assert {
	static EntityManagerFactory factory;
	static EntityManager manager;
	static EntityTransaction transaction;
	static final String persistenceUnitName = "Beer";
	
	static Land land = null;
	
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
	
	@Test (expected=IllegalArgumentException.class)
	public void B_landNameTooShort() {
		land = new Land("Ös");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void B_landNameWithDigit() {
		land = new Land("Österreich1");	
	}
	
	@Test
	public void C_addLand() {
		clean();
		land = new Land("Österreich");
		assertNotNull(land);
		manager.persist(land);
		transaction.commit();				
	}
	
	@Test
	public void C_removeLand() {
		land = manager.find(Land.class, "Österreich");
		assertNotNull(land);
		
		transaction.begin();
		manager.remove(land);
		transaction.commit();
		
		land = manager.find(Land.class, land.getName());
		assertNull(land);
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
		manager.createNativeQuery("DELETE FROM land").executeUpdate();
	}
}
