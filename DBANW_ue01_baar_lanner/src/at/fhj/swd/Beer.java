package at.fhj.swd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="Person")
public class Beer {
	@Id @Column(name="Name")			private String name;
	@Column(name="Kaufdatum")			private String kDate;
	@Column(name="Ablaufdatum")			private String aDate;
	@Column(name="Produktionsdatum")	private String pDate;
}
