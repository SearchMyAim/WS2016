package at.fhj.swd;

import java.util.List;

import javax.persistence.EntityManager;

public class LocationCrud {
	private EntityManagerSingelton ems;
	
	public LocationCrud() {
		ems = new EntityManagerSingelton();
	}
	
	public EntityManager getEntityManager() {
		return ems.getInstance();
	}
	
	public Location insert(Location entity) {
		ems.getInstance().persist(entity);
		return entity;
	}
	
	public Location update(Location entity) {
		return ems.getInstance().merge(entity);
	}
	
	public void delete(Location entity) {
		ems.getInstance().remove(entity);
		return;
	}
	
	public Location findByName(String name) {
		return ems.getInstance().find(Location.class, name);
	}
	
	@SuppressWarnings("unchecked")
	public List<Location> findAll() {
		return ems.getInstance().createQuery("SELECT u FROM " + Location.class.getName() + " AS u").getResultList();
	}
	
	public Location createLocation(String name, Country country, Producer producer) {
		Location l = new Location(name, country, producer);
		insert(l);
		return l;
	}
}
