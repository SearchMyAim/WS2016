package at.fhj.swd;

import java.util.List;

import javax.persistence.EntityManager;

public class PersonCrud {
	private EntityManagerSingelton ems;
	
	public PersonCrud() {
		ems = new EntityManagerSingelton();
	}
	
	public EntityManager getEntityManager() {
		return ems.getInstance();
	}
	
	public Person insert(Person entity) {
		ems.getInstance().persist(entity);
		return entity;
	}
	
	public Person update(Person entity) {
		return ems.getInstance().merge(entity);
	}
	
	public void delete(Person entity) {
		ems.getInstance().remove(entity);
		return;
	}
	
	public Person findByName(String name) {
		return ems.getInstance().find(Person.class, name);
	}
	
	@SuppressWarnings("unchecked")
	public List<Person> findAll() {
		return ems.getInstance().createQuery("SELECT u FROM " + Person.class.getName() + " AS u").getResultList();
	}
	
	public Person createPerson(String name, String sex, String nationality, int age) {
		Person p = new Person(name, sex, nationality, age);
		insert(p);
		return p;
	}
}
