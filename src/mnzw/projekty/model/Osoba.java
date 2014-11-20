package mnzw.projekty.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "OSOBA")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "stanowisko", discriminatorType = DiscriminatorType.STRING)
public abstract class Osoba {	
	@Id
	@GeneratedValue
	private int id;
	private String imie;
	private String nazwisko;
	
	protected Osoba(){
		
	}
	
	public Osoba(String imie, String nazwisko) {
		super();
		this.imie = imie;
		this.nazwisko = nazwisko;
	}
}
