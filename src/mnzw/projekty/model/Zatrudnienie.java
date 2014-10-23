package mnzw.projekty.model;

import java.util.GregorianCalendar;

public class Zatrudnienie {			
	private GregorianCalendar odDaty;
	private GregorianCalendar doDaty;
	private int procentEtatu;
	private Programista programista;
	
	public Zatrudnienie(GregorianCalendar odDaty, GregorianCalendar doDaty, int procentEtatu, Programista programista) {
		super();
		this.odDaty = odDaty;
		this.doDaty = doDaty;
		this.procentEtatu = procentEtatu;
		this.programista = programista;
	}
}
