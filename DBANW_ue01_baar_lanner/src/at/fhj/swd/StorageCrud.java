package at.fhj.swd;

import java.util.List;

import javax.persistence.EntityManager;

public class StorageCrud {
	private EntityManagerSingelton ems;
	
	public StorageCrud() {
		ems = new EntityManagerSingelton();
	}
	
	public EntityManager getEntityManager() {
		return ems.getInstance();
	}
	
	public Storage insert(Storage entity) {
		ems.getInstance().persist(entity);
		return entity;
	}
	
	public Storage update(Storage entity) {
		return ems.getInstance().merge(entity);
	}
	
	public void delete(Storage entity) {
		ems.getInstance().remove(entity);
		return;
	}
	
	public Storage findById(int id) {
		return ems.getInstance().find(Storage.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Storage> findAll() {
		return ems.getInstance().createQuery("SELECT u FROM " + Storage.class.getName() + " AS u").getResultList();
	}
	
	public Storage createStorage(int id) {
		Storage s = new Storage(id);
		insert(s);
		return s;
	}
}
