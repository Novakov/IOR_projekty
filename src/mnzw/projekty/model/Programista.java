package mnzw.projekty.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;


@Entity
@DiscriminatorValue("programista")
public class Programista extends Osoba {
	@OneToMany
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "PROGRAMISTA_ID")	
	@org.hibernate.annotations.ForeignKey(name = "FK_JEZYKI_PROGRAMISTA")
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
