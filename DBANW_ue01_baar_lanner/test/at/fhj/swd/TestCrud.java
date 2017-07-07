package at.fhj.swd;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityTransaction;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.AfterClass;
import org.junit.Assert;

public class TestCrud extends Assert {
	
	static private EntityTransaction et;
	static private PersonCrud pc = new PersonCrud();
	static private ArrayList<Person> pl = new ArrayList<Person>();
	
	@BeforeClass
	public static void setup() {
		et = pc.getEntityManager().getTransaction();	
		createPerson();
	}
	
	@Test
	public void testPerson() {
		et.begin();		
		Person p = pc.findByName("Alexander Baar");
		assertNotNull(p);
		assertEquals("Alexander Baar", p.getName());
		assertEquals("m", p.getGeschlecht());
		assertEquals("AUT", p.getNationalität());
		assertEquals(29, p.getAlter());
		
		p.setAlter(71);
		pc.update(p);			
		et.commit();
		
		et.begin();
		Person pv = pc.findByName("Alexander Baar");
		assertNotNull(pv);
		assertEquals(71, pv.getAlter());
		pv.setAlter(29);
		pc.update(pv);
		et.commit();
		
		et.begin();		
		List<Person> tpl = pc.findAll();
		assertEquals(5, tpl.size());
		assertTrue(listEqualsNoOrder(tpl, pl));
			
		
		et.commit();
	}
	
	public static void createPerson() {
		et.begin();
		pl.add(pc.createPerson("Alexander Baar", "m", "AUT", 29));
		pl.add(pc.createPerson("Donald Duck", "m", "DKH", 30));
		pl.add(pc.createPerson("Daisy Duck", "f", "DKH", 25));
		pl.add(pc.createPerson("Donald Trump", "m", "USA", 71));
		pl.add(pc.createPerson("John Doe", "m", "XXX", 45));
		et.commit();
	}
	
	@AfterClass
	public static void teardown() {
		et.begin();
		
		List<Person> persons = pc.findAll();
		for(Person p : persons) {
			pc.delete(p);
		}
		
		et.commit();
	}
	
	public static <T> boolean listEqualsNoOrder(List<T> l1, List<T> l2) {
	    final Set<T> s1 = new HashSet<>(l1);
	    final Set<T> s2 = new HashSet<>(l2);

	    return s1.equals(s2);
	}
}

