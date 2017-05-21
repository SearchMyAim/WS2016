package at.fhj.swd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="producer")
public class Producer {
	@Id @Column(name="name")	private String name;
	
	public Producer(String name) {
		setName(name);
	}
	
	public Producer() {
		this.name = "None";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
