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
public class CountryTest extends Assert {
	static EntityManagerFactory factory;
	static EntityManager manager;
	static EntityTransaction transaction;
	static final String persistenceUnitName = "beer_storage";
	
	static Country country = null;
	
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
	public void T_001_landNameTooShort() {
		country = new Country("Ös");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void T_002_landNameWithDigit() {
		country = new Country("Österreich1");	
	}
	
	@Test
	public void T_003_addLand() {
		clean();
		country = new Country("Österreich");
		assertNotNull(country);
		manager.persist(country);
		transaction.commit();				
	}
	
	@Test
	public void T_004_removeLand() {
		country = manager.find(Country.class, "Österreich");
		assertNotNull(country);
		
		transaction.begin();
		manager.remove(country);
		transaction.commit();
		
		country = manager.find(Country.class, country.getName());
		assertNull(country);
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
		manager.createNativeQuery("DELETE FROM country").executeUpdate();
	}
}
