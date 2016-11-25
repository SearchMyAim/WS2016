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
	@Column(name="alter")			private int alter;
	
	public Person(String name, String geschlecht, String nationalitaet, int alter) {
		setName(name);
		setGeschlecht(geschlecht);
		setNationalität(nationalitaet);
		setAlter(alter);	
	}
	
	public Person() {
		this.name = "Empty";
		this.geschlecht = "n";
		this.nationalitaet = "NON";
		this.alter = 0;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name.length() < 3) {
			throw new IllegalArgumentException("Name too short");
		}
		this.name = name;
	}

	public String getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(String geschlecht) {
		if((geschlecht.length() != 1) || (geschlecht.matches("[mMfF]") == false)) {
			throw new IllegalArgumentException("Either m or f!");
		}
		this.geschlecht = geschlecht;
	}

	public String getNationalität() {
		return nationalitaet;
	}

	public void setNationalität(String nationalitaet) {
		if(nationalitaet.length() != 3) {
			throw new IllegalArgumentException("National Country Code MUST be 3 letters!");
		}
		this.nationalitaet = nationalitaet;
	}

	public int getAlter() {
		return alter;
	}

	public void setAlter(int alter) {
		if((alter == 0) || (alter > 150)) {
			throw new IllegalArgumentException("Not born or for sure dead already!");
		}
		this.alter = alter;
	}
}
