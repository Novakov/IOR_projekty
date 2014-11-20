package mnzw.projekty.model;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ZAPOTRZEBOWANIE")
public class Zapotrzebowanie {
	@Id
	@GeneratedValue
	private int id;
	private int osoboGodziny;
	private int stopienZnajomosci;
	
	@ManyToOne
	@JoinColumn(name = "JEZYK_ID", foreignKey = @ForeignKey(name = "FK_ZAPOTRZEBOWANIE_JEZYK"))
	private JezykProgramowania jezyk;
	
	public Zapotrzebowanie(JezykProgramowania jezyk, int stopienZnajomosci, int osoboGodziny) {
		this.jezyk = jezyk;
		this.stopienZnajomosci = stopienZnajomosci;
		this.osoboGodziny = osoboGodziny;
	}
}
