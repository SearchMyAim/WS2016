package at.fhj.swd;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity @Table(name="beer")
public class Beer {
	
	@ManyToOne @JoinColumn(name="brewing_method", referencedColumnName="brewing_method")	
	private Brand art = null;
	
	@ManyToOne @JoinColumn(name="person_name", referencedColumnName="name")		
	private Person person;
	
	@ManyToOne @JoinColumn(name="storage_id", referencedColumnName="pk_id")		
	private Storage storage = null;
	
	@Id @Column(name="name")				private String name;
	@Column(name="buy_date")				private Date bDate;
	@Column(name="expire_date")				private Date eDate;
	@Column(name="production_date")			private Date pDate;
	
	public Beer() {}
	
	public Beer(String name, Brand brand, Person person, Date buyDate, Date expireDate, Date productionDate, Storage storage) {
		setName(name);
		setBrauArt(brand);
		setPerson(person);
		setBuyDate(buyDate);
		setExpireDate(expireDate);
		setProductionDate(productionDate);
		setStorage(storage);
	}
	
	public void setBrauArt(Brand art) {
		this.art = art;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getBuyDate() {
		return bDate;
	}
	
	public void setBuyDate(Date buyDate) {
		this.bDate = buyDate;
	}
	
	public Date getExpireDate() {
		return eDate;
	}
	
	public void setExpireDate(Date expireDate) {
		this.eDate = expireDate;
	}
	
	public Date getProductionDate() {
		return pDate;
	}
	
	public void setProductionDate(Date productionDate) {
		this.pDate = productionDate;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Person getPerson() {
		return this.person;
	}
	
	public void setStorage(Storage storage) {
		this.storage = storage;
	}
	
	public Storage getStorage() {
		return this.storage;
	}
}
