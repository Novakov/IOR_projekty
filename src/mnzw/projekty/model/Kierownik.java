package mnzw.projekty.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("kierownik")
public class Kierownik extends Osoba {

	protected Kierownik(){
		
	}
	
	public Kierownik(String imie, String nazwisko) {
		super(imie, nazwisko);
	}
	
}
