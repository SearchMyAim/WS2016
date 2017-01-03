package at.fhj.swd;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity @Table(name="Bier")
public class Beer {
	
	@OneToMany @JoinColumn(name="brauart")	private Art art = null;
	@Id @Column(name="Name")				private String name;
	@Column(name="Kaufdatum")				private Date kDate;
	@Column(name="Ablaufdatum")				private Date aDate;
	@Column(name="Produktionsdatum")		private Date pDate;
	
	
	public Beer() {
	}
	
	public void setBrauArt(Art art) {
		this.art = art;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getBuyDate() {
		return kDate;
	}
	
	public void setBuyDate(Date buyDate) {
		this.kDate = buyDate;
	}
	
	public Date getExpireDate() {
		return aDate;
	}
	
	public void setExpireDate(Date expireDate) {
		this.aDate = expireDate;
	}
	
	public Date getProductionDate() {
		return pDate;
	}
	
	public void setProductionDate(Date productionDate) {
		this.pDate = productionDate;
	}
}
