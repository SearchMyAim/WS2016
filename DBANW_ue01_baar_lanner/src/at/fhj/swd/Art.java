package at.fhj.swd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name="art")
public class Art {
	@ManyToOne private Beer beer;
	@Id @Column(name="Brauart")	private String brauart;
	
	public void setBeer(Beer beer) {
		this.beer = beer;
	}

	public String getBrauart() {
		return brauart;
	}

	public void setBrauart(String brauart) {
		if(brauart.matches("\\D+") == false) {
			throw new IllegalArgumentException();
		}
		this.brauart = brauart;
	}
	
}
