package at.fhj.swd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity @Table(name="brand")
public class Brand {
	@Id @Column(name="brewing_method")	private String brewing_method;
	
	public Brand() {
		this.brewing_method = "Alkoholfreies";
	}
	
	public Brand(String method) {
		setBrewingMethod(method);
	}	
	
	public String getBrewingMethod() {
		return brewing_method;
	}

	public void setBrewingMethod(String brewingMethod) {
		if(brewingMethod.matches("\\D+") == false) {
			throw new IllegalArgumentException();
		}
		this.brewing_method = brewingMethod;
	}
	
}
