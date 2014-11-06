package mnzw.projekty.model;

import java.util.HashSet;
import java.util.Set;

public class Programista extends Osoba {
	private Set<Jezyki> jezyki = new HashSet<Jezyki>();
	
	protected Programista(){
		
	}
	
	public Programista(String imie, String nazwisko) {
		super(imie, nazwisko);
	}

	public void addJezyk(JezykProgramowania jezyk, int stopienZnajomosci) {
		this.jezyki.add(new Jezyki(jezyk, stopienZnajomosci, 0));
	}	
}
