package mnzw.projekty.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JEZYKPROGRAMOWANIA")
public class JezykProgramowania {	
	@Id
	@GeneratedValue	
	private int id;
	private String nazwa;
	
	public JezykProgramowania(){
		
	}
	
	public JezykProgramowania(String nazwa) {
		super();
		this.nazwa = nazwa;
	}
}
