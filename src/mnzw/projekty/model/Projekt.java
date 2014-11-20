package mnzw.projekty.model;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "PROJEKT")
public class Projekt {
	@Id
	@GeneratedValue
	private int id;
	private String nazwa;
	private GregorianCalendar dataPoczatku;
	private GregorianCalendar dataKonca;
	
	@ManyToOne
	@JoinColumn(name = "KIEROWNIK_ID", foreignKey = @ForeignKey(name = "FK_PROJEKT_KIEROWNIK"))
	private Kierownik kierownik;
	
	@OneToMany
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "PROJEKT_ID")	
	@org.hibernate.annotations.ForeignKey(name = "FK_ZATRUDNIENI_PROJEKT")
	private Set<Zatrudnienie> zatrudnieni = new HashSet<>();	
	
	@OneToMany
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "PROJEKT_ID")	
	@org.hibernate.annotations.ForeignKey(name = "FK_ZAPOTRZEBOWANIE_PROJEKT")
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
