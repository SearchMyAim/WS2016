package at.fhj.swd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name="location")
public class Location {
	@Id @Column(name="pk_id")		
	private String locationName;
	
	@ManyToOne @JoinColumn(name="country_name", referencedColumnName="name")
	private Country country;
	
	@ManyToOne @JoinColumn(name="producer_name", referencedColumnName="name")
	private Producer producer;
	
	public Location(Country country, Producer producer) {
		setCountry(country);
	}
	
	public Location() {
		
	}
	
	public void setProducer(Producer producer) {
		this.producer = producer;
	}
	
	public Producer getProducer() {
		return this.producer;
	}
	
	public void setCountry(Country country) {
		this.country = country;
	}
	
	public Country getCountry() {
		return this.country;
	}
	
	
	
}
