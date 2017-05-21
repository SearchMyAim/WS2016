package at.fhj.swd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="person")
public class Person {
	@Id @Column(name="name") 		private String name;
	@Column(name="gender")			private String gender;
	@Column(name="nationality")		private String nationality;
	@Column(name="age")				private int age;
	
	public Person(String name, String geschlecht, String nationalitaet, int alter) {
		setName(name);
		setGeschlecht(geschlecht);
		setNationalität(nationalitaet);
		setAlter(alter);	
	}
	
	public Person() {
		this.name = "Empty";
		this.gender = "n";
		this.nationality = "NON";
		this.age = 0;
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
		return gender;
	}

	public void setGeschlecht(String geschlecht) {
		if((geschlecht.length() != 1) || (geschlecht.matches("[mMfF]") == false)) {
			throw new IllegalArgumentException("Either m or f!");
		}
		this.gender = geschlecht;
	}

	public String getNationalität() {
		return nationality;
	}

	public void setNationalität(String nationalitaet) {
		if(nationalitaet.length() != 3) {
			throw new IllegalArgumentException("National Country Code MUST be 3 letters!");
		}
		this.nationality = nationalitaet;
	}

	public int getAlter() {
		return age;
	}

	public void setAlter(int alter) {
		if((alter == 0) || (alter > 150)) {
			throw new IllegalArgumentException("Not born or for sure dead already!");
		}
		this.age = alter;
	}
}
