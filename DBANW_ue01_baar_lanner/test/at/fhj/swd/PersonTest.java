package at.fhj.swd;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.AfterClass;
import org.junit.Assert;


@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class PersonTest extends Assert {
	static EntityManagerFactory factory;
	static EntityManager manager;
	static EntityTransaction transaction;
	static final String persistenceUnitName = "Beer";
	
	static Person person = null;
	
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
	public void B_personNameTooShort() {
		person = new Person("Al", "m", "AUT", 28);
	}
	
	@Test (expected=IllegalArgumentException.class) 
	public void B_personGenderWrong() {
		person = new Person("Alex", "a", "AUT", 28);
	}
	
	@Test (expected=IllegalArgumentException.class) 
	public void B_personGenderTooLong() {
		person = new Person("Alex", "mm", "AUT", 28);
	}
	
	@Test (expected=IllegalArgumentException.class) 
	public void B_personNationalityTooShort() {
		person = new Person("Alex", "m", "AU", 28);
	}
	
	@Test (expected=IllegalArgumentException.class) 
	public void B_personNationalityTooLong() {
		person = new Person("Alex", "m", "AUTT", 28);
	}
	
	@Test (expected=IllegalArgumentException.class) 
	public void B_personAgeZero() {
		person = new Person("Alex", "m", "AUTT", 0);
	}
	
	@Test (expected=IllegalArgumentException.class) 
	public void B_personAgeTooHigh() {
		person = new Person("Alex", "m", "AUTT", 151);
	}
	
	@Test
	public void C_addPerson() {
		clean();
		person = new Person("Alex", "m", "AUT", 28);
		assertNotNull(person);
		manager.persist(person);
		transaction.commit();
	}
	
	@Test
	public void C_removePerson() {
		person = manager.find(Person.class, "Alex");
		assertNotNull(person);
		
		transaction.begin();
		manager.remove(person);
		transaction.commit();		
		
		person = manager.find(Person.class, person.getName());
		assertNull(person);
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
		manager.createNativeQuery("DELETE FROM person").executeUpdate();
	}
	
}
