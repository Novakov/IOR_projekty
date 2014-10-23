package mnzw.projekty.model;

public abstract class Osoba {	
	private int id;
	private String imie;
	private String nazwisko;
	
	public Osoba(String imie, String nazwisko) {
		super();
		this.imie = imie;
		this.nazwisko = nazwisko;
	}
}
