package at.fhj.swd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="land")
public class Land {
	@Id @Column(name="name")	private String name;
	
	public Land(String name) {
		setName(name);
	}
	
	public Land() {
		this.name = "Unknown";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if((name.length() < 3) || (name.matches("\\D+") == false)) {
			throw new IllegalArgumentException("Land name length must be at least 3 letters!");
		}
		this.name = name;
	}
}
