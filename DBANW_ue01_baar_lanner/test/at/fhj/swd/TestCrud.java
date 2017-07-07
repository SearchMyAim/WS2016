package at.fhj.swd;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	
	static private PersonCrud peC = new PersonCrud();
	static private ArrayList<Person> peL = new ArrayList<Person>();
	
	static private CountryCrud cc = new CountryCrud();
	static private ArrayList<Country> cl = new ArrayList<Country>();
	
	static private BrandCrud brC = new BrandCrud();
	static private ArrayList<Brand> brL = new ArrayList<Brand>();
	
	static private LocationCrud lc = new LocationCrud();
	static private ArrayList<Location> ll = new ArrayList<Location>();
	
	static private BeerCrud beC = new BeerCrud();
	static private ArrayList<Beer> beL = new ArrayList<Beer>();
	
	static private ProducerCrud prC = new ProducerCrud();
	static private ArrayList<Producer> prL = new ArrayList<Producer>();
	
	static private StorageCrud sc = new StorageCrud();
	static private ArrayList<Storage> sl = new ArrayList<Storage>();
	
	@BeforeClass
	public static void setup() {
		et = peC.getEntityManager().getTransaction();	
		createTestData();		
	}
	
	@AfterClass
	public static void teardown() {
		clearTestData();		
	}
	
	@Test
	public void testPerson() {
		et.begin();		
		Person p = peC.findByName("Alexander Baar");
		assertNotNull(p);
		assertEquals("Alexander Baar", p.getName());
		assertEquals("m", p.getGeschlecht());
		assertEquals("AUT", p.getNationalität());
		assertEquals(29, p.getAlter());
		
		p.setAlter(71);
		peC.update(p);			
		et.commit();
		
		et.begin();
		Person pv = peC.findByName("Alexander Baar");
		assertNotNull(pv);
		assertEquals(71, pv.getAlter());
		et.commit();
		
		et.begin();		
		List<Person> tpl = peC.findAll();
		assertEquals(5, tpl.size());
		assertTrue(listEqualsNoOrder(tpl, peL));
		et.commit();
	}
	
	@Test
	public void testCountry() {
		et.begin();
		Country c = cc.findByName("Belgium");
		assertNotNull(c);
		assertEquals("Belgium", c.getName());
		
		List<Country> tcl = cc.findAll();
		assertEquals(cl.size(), tcl.size());
		assertTrue(listEqualsNoOrder(tcl, cl));		
		et.commit();
	}
	
	@Test
	public void testProducer() {
		et.begin();
		Producer p = prC.findByName("Murauer");
		assertNotNull(p);
		assertEquals("Murauer", p.getName());
		
		List<Producer> tpl = prC.findAll();
		assertEquals(3, tpl.size());
		assertTrue(listEqualsNoOrder(tpl, prL));
		et.commit();
	}
	
	@Test
	public void testStorage() {
		et.begin();
		Storage s = sc.findById(2);
		assertNotNull(s);
		assertEquals(2, s.getId());
		
		List<Storage> tsl = sc.findAll();
		assertEquals(5, tsl.size());
		assertTrue(listEqualsNoOrder(tsl, sl));
		et.commit();
	}
	
	@Test
	public void testBrand() {
		et.begin();
		Brand b = brC.findByName("Malzbier");
		assertNotNull(b);
		assertEquals("Malzbier", b.getBrewingMethod());
		
		List<Brand> tbl = brC.findAll();
		assertEquals(4, tbl.size());
		assertTrue(listEqualsNoOrder(tbl, brL));
		et.commit();
	}
	
	@Test
	public void testLocation() {
		et.begin();
		Location l = lc.findByName("Graz");
		assertNotNull(l);
		assertEquals("Graz", l.getLocationName());
		assertEquals("Austria", l.getCountry().getName());
		assertEquals("Puntigamer", l.getProducer().getName());

		List<Location> tlc = lc.findAll();
		assertEquals(ll.size(), tlc.size());
		assertTrue(listEqualsNoOrder(tlc, ll));
		et.commit();
	}
	
	@Test
	public void testBeer() {
		et.begin();
		Beer b = beC.findById(2);
		assertNotNull(b);
		assertEquals("Blaue Forelle", b.getName());
		assertEquals("Märzenbier", b.getBrand().getBrewingMethod());
		assertEquals("Donald Duck", b.getPerson().getName());
		
		List<Beer> tbl = beC.findAll();
		assertEquals(beL.size(), tbl.size());
		assertTrue(listEqualsNoOrder(tbl, beL));
		et.commit();
	}
	
	public static void createTestData() {
		createPerson();
		createCountry();
		createProducer();
		createStorage();
		createBrand();
		createLocation();
		createBeer();
	}
	
	public static void clearTestData() {
		cleanBeer();
		cleanLocation();
		cleanBrand();
		cleanPerson();
		cleanCountry();
		cleanProducer();
		cleanStorage();
	}

	public static void createPerson() {
		et.begin();
		peL.add(peC.createPerson("Alexander Baar", "m", "AUT", 29));
		peL.add(peC.createPerson("Donald Duck", "m", "DKH", 30));
		peL.add(peC.createPerson("Daisy Duck", "f", "DKH", 25));
		peL.add(peC.createPerson("Donald Trump", "m", "USA", 71));
		peL.add(peC.createPerson("John Doe", "m", "XXX", 45));
		et.commit();
	}
	
	public static void cleanPerson() {
		et.begin();
		List<Person> persons = peC.findAll();
		for(Person p : persons) {
			peC.delete(p);
		}
		et.commit();
	}
	
	public static void createCountry() {
		et.begin();
		cl.add(cc.createCountry("Austria"));
		cl.add(cc.createCountry("Belgium"));
		cl.add(cc.createCountry("Germany"));
		et.commit();
	}
	
	public static void cleanCountry() {
		et.begin();
		List<Country> countries = cc.findAll();
		for(Country c : countries) {
			cc.delete(c);
		}
		et.commit();
	}
	
	public static void createProducer() {
		et.begin();
		prL.add(prC.createProducer("Puntigamer"));
		prL.add(prC.createProducer("Murauer"));
		prL.add(prC.createProducer("Stiegl"));
		et.commit();
	}
	
	public static void cleanProducer() {
		et.begin();
		List<Producer> producers = prC.findAll();
		for(Producer p : producers) {
			prC.delete(p);
		}
		et.commit();
	}
	
	public static void createStorage() {
		et.begin();
		for(int i = 1; i < 6; i++) {
			sl.add(sc.createStorage(i));
		}
		et.commit();
	}
	
	public static void cleanStorage() {
		et.begin();
		List<Storage> storages = sc.findAll();
		for(Storage s : storages) {
			sc.delete(s);
		}
		et.commit();
	}

	public static void createBrand() {
		et.begin();
		brL.add(brC.createBrand("Bockbier"));
		brL.add(brC.createBrand("Märzenbier"));
		brL.add(brC.createBrand("Malzbier"));
		brL.add(brC.createBrand("Weizenbier"));
		et.commit();
	}
	
	public static void cleanBrand() {
		et.begin();
		List<Brand> brands = brC.findAll();
		for(Brand b : brands) {
			brC.delete(b);
		}
		et.commit();
	}

	public static void createLocation() {
		et.begin();
		ll.add(lc.createLocation("Graz", cl.get(0), prL.get(0)));
		ll.add(lc.createLocation("Wien", cl.get(1), prL.get(1)));
		ll.add(lc.createLocation("Dorf", cl.get(2), prL.get(2)));		
		et.commit();
	}
	
	public static void cleanLocation() {
		et.begin();
		List<Location> locations = lc.findAll();
		for(Location l : locations) {
			lc.delete(l);
		}
		et.commit();
	}
	
	public static void createBeer() {
		et.begin();
		Date bDate = createDate(2017, 7, 7);
		Date eDate = createDate(2020, 1, 1);
		Date pDate = createDate(2016, 11, 12);
		beL.add(beC.createBeer(1, "Blaue Forelle", brL.get(0), peL.get(0), bDate, eDate , pDate , sl.get(0)));
		bDate = createDate(2017, 7, 7);
		eDate = createDate(2020, 1, 1);
		pDate = createDate(2016, 11, 12);
		beL.add(beC.createBeer(2, "Blaue Forelle", brL.get(1), peL.get(1), bDate, eDate , pDate , sl.get(1)));
		et.commit();
	}
	
	public static void cleanBeer() {
		et.begin();
		List<Beer> beers = beC.findAll();
		for(Beer b : beers) {
			beC.delete(b);
		}
		et.commit();
	}
	
	public static <T> boolean listEqualsNoOrder(List<T> l1, List<T> l2) {
	    final Set<T> s1 = new HashSet<>(l1);
	    final Set<T> s2 = new HashSet<>(l2);

	    return s1.equals(s2);
	}
	
	public static Date createDate(int year, int month, int date) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);
		cal.set(year, month, date);
		Date d = cal.getTime();
		return d;
	}
}

