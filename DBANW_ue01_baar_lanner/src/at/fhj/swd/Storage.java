package at.fhj.swd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="storage")
public class Storage {
	@Id @Column(name="pk_id") 		private int id;
	
	public Storage() {
		this.id = 0;
	}
	
	public Storage(int id) {
		this.id = id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
}
