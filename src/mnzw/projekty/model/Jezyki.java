package mnzw.projekty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "JEZYKI")
public class Jezyki {
	@Id
	@GeneratedValue	
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "JEZYK_ID", foreignKey = @javax.persistence.ForeignKey(name = "FK_JEZYKI_JEZYK"))
	
	private JezykProgramowania jezyk;
	
	private int stopienZnajomosci;
	private int uwagi;
	
	protected Jezyki(){
		
	}
	
	public Jezyki(JezykProgramowania jezyk, int stopienZnajomosci, int uwagi) {
		super();
		this.jezyk = jezyk;
		this.stopienZnajomosci = stopienZnajomosci;
		this.uwagi = uwagi;
	}
}
