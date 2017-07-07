package at.fhj.swd;

import java.util.List;

import javax.persistence.EntityManager;

public class ProducerCrud {
	private EntityManagerSingelton ems;
	
	public ProducerCrud() {
		ems = new EntityManagerSingelton();
	}
	
	public EntityManager getEntityManager() {
		return ems.getInstance();
	}
	
	public Producer insert(Producer entity) {
		ems.getInstance().persist(entity);
		return entity;
	}
	
	public Producer update(Producer entity) {
		return ems.getInstance().merge(entity);
	}
	
	public void delete(Producer entity) {
		ems.getInstance().remove(entity);
		return;
	}
	
	public Producer findByName(String name) {
		return ems.getInstance().find(Producer.class, name);
	}
	
	@SuppressWarnings("unchecked")
	public List<Producer> findAll() {
		return ems.getInstance().createQuery("SELECT u FROM " + Producer.class.getName() + " AS u").getResultList();
	}
	
	public Producer createProducer(String name) {
		Producer p = new Producer(name);
		insert(p);
		return p;
	}
}
