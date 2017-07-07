package at.fhj.swd;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

public class BeerCrud {
	private EntityManagerSingelton ems;
	
	public BeerCrud() {
		ems = new EntityManagerSingelton();
	}
	
	public EntityManager getEntityManager() {
		return ems.getInstance();
	}
	
	public Beer insert(Beer entity) {
		ems.getInstance().persist(entity);
		return entity;
	}
	
	public Beer update(Beer entity) {
		return ems.getInstance().merge(entity);
	}
	
	public void delete(Beer entity) {
		ems.getInstance().remove(entity);
		return;
	}
	
	public Beer findById(int id) {
		return ems.getInstance().find(Beer.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Beer> findAll() {
		String query = "SELECT u FROM " + Beer.class.getName() + " AS u";
		return ems.getInstance().createQuery(query).getResultList();
	}
	
	public Beer createBeer(	int id,
							String name, 
							Brand brand, 
							Person person, 
							Date buyDate, 
							Date expireDate, 
							Date productionDate, 
							Storage storage) {
		Beer b = new Beer(id, name, brand, person, buyDate, expireDate, productionDate, storage);
		insert(b);
		return b;
	}
	
	
}
