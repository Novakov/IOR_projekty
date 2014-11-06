package mnzw.projekty.model;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class Projekt {
	private int id;
	private String nazwa;
	private GregorianCalendar dataPoczatku;
	private GregorianCalendar dataKonca;
	private Kierownik kierownik;
	private Set<Zatrudnienie> zatrudnieni = new HashSet<>();	
	private Set<Zapotrzebowanie> zapotrzebowanie = new HashSet<>();

	protected Projekt(){
		
	}
	
	public Projekt(String nazwa, GregorianCalendar dataPoczatku, GregorianCalendar dataKonca, Kierownik kierownik) {
		super();
		this.nazwa = nazwa;
		this.dataPoczatku = dataPoczatku;
		this.dataKonca = dataKonca;
		this.kierownik = kierownik;
	}

	public void zatrudnij(Programista programista, int procentEtatu, GregorianCalendar od, GregorianCalendar doDaty) {
		this.zatrudnieni.add(new Zatrudnienie(od, doDaty, procentEtatu, programista));
	}

	public void zglosZapotrzebowanie(JezykProgramowania jezyk, int stopienZnajomosci, int osoboGodziny) {
		this.zapotrzebowanie.add(new Zapotrzebowanie(jezyk, stopienZnajomosci, osoboGodziny));
	}
}
