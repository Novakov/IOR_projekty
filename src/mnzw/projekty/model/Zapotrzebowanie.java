package mnzw.projekty.model;

public class Zapotrzebowanie {	
	private int osoboGodziny;
	private int stopienZnajomosci;
	private JezykProgramowania jezyk;
	
	public Zapotrzebowanie(JezykProgramowania jezyk, int stopienZnajomosci, int osoboGodziny) {
		this.jezyk = jezyk;
		this.stopienZnajomosci = stopienZnajomosci;
		this.osoboGodziny = osoboGodziny;
	}
}
