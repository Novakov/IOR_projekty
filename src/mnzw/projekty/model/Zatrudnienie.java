package mnzw.projekty.model;

import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ZATRUDNIENIE")
public class Zatrudnienie {
	@Id
	@GeneratedValue
	private int id;
	private GregorianCalendar odDaty;
	private GregorianCalendar doDaty;
	private int procentEtatu;
	
	@ManyToOne
	@JoinColumn(name = "PROGRAMISTA_ID", foreignKey = @ForeignKey(name = "FK_ZATRUDNIENIE_PROGRAMISTA"))
	private Programista programista;
	
	public Zatrudnienie(GregorianCalendar odDaty, GregorianCalendar doDaty, int procentEtatu, Programista programista) {
		super();
		this.odDaty = odDaty;
		this.doDaty = doDaty;
		this.procentEtatu = procentEtatu;
		this.programista = programista;
	}
}
