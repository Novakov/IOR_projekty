package mnzw.projekty.model;

import java.util.GregorianCalendar;
import java.util.Set;

public class Projekt {
	private int id;
	private String nazwa;
	private GregorianCalendar dataPoczatku;
	private GregorianCalendar dataKonca;
	private Kierownik kierownik;
	private Set<Zatrudnienie> zatrudnieni;
	private Set<Zapotrzebowanie> zapotrzebowanie;
}
