package at.fhj.swd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="Person")
public class Person {
	@Id @Column(name="name") 		private String name;
	@Column(name="geschlecht")		private String geschlecht;
	@Column(name="nationalitaet")	private String nationalitaet;
	@Column(name="alter")			private String alter;
	
	public Person(String name, String geschlecht, String nationalitaet, String alter) {
		setName(name);
		setGeschlecht(geschlecht);
		setNationalität(nationalitaet);
		setAlter(alter);		
		return;
	}
	
	public Person() {
		this.name = "Empty";
		this.geschlecht = "n";
		this.nationalitaet = "NON";
		this.alter = "000";
		return;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(String geschlecht) {
		this.geschlecht = geschlecht;
	}

	public String getNationalität() {
		return nationalitaet;
	}

	public void setNationalität(String nationalitaet) {
		this.nationalitaet = nationalitaet;
	}

	public String getAlter() {
		return alter;
	}

	public void setAlter(String alter) {
		this.alter = alter;
	}

	

}
