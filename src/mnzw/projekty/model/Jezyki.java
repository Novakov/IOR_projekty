package mnzw.projekty.model;

public class Jezyki {	
	private JezykProgramowania jezyk;
	private int stopienZnajomosci;
	private int uwagi;
	
	public Jezyki(JezykProgramowania jezyk, int stopienZnajomosci, int uwagi) {
		super();
		this.jezyk = jezyk;
		this.stopienZnajomosci = stopienZnajomosci;
		this.uwagi = uwagi;
	}
}
