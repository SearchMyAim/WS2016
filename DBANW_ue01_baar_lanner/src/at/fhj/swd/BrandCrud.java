package at.fhj.swd;

import java.util.List;

import javax.persistence.EntityManager;

public class BrandCrud {
	private EntityManagerSingelton ems;
	
	public BrandCrud() {
		ems = new EntityManagerSingelton();
	}
	
	public EntityManager getEntityManager() {
		return ems.getInstance();
	}
	
	public Brand insert(Brand entity) {
		ems.getInstance().persist(entity);
		return entity;
	}
	
	public Brand update(Brand entity) {
		return ems.getInstance().merge(entity);
	}
	
	public void delete(Brand entity) {
		ems.getInstance().remove(entity);
		return;
	}
	
	public Brand findByName(String name) {
		return ems.getInstance().find(Brand.class, name);
	}
	
	@SuppressWarnings("unchecked")
	public List<Brand> findAll() {
		return ems.getInstance().createQuery("SELECT u FROM " + Brand.class.getName() + " AS u").getResultList();
	}
	
	public Brand createBrand(String name) {
		Brand b = new Brand(name);
		insert(b);
		return b;
	}
}
