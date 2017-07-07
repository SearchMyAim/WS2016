package at.fhj.swd;

import java.util.List;

import javax.persistence.EntityManager;

public class CountryCrud {
	private EntityManagerSingelton ems;
	
	public CountryCrud() {
		ems = new EntityManagerSingelton();
	}
	
	public EntityManager getEntityManager() {
		return ems.getInstance();
	}
	
	public Country insert(Country entity) {
		ems.getInstance().persist(entity);
		return entity;
	}
	
	public Country update(Country entity) {
		return ems.getInstance().merge(entity);
	}
	
	public void delete(Country entity) {
		ems.getInstance().remove(entity);
		return;
	}
	
	public Country findByName(String name) {
		return ems.getInstance().find(Country.class, name);
	}
	
	@SuppressWarnings("unchecked")
	public List<Country> findAll() {
		return ems.getInstance().createQuery("SELECT u FROM " + Country.class.getName() + " AS u").getResultList();
	}
	
	public Country createCountry(String name) {
		Country c = new Country(name);
		insert(c);
		return c;
	}
}
