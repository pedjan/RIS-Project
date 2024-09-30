package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the kolekcija database table.
 * 
 */
@Entity
@NamedQuery(name="Kolekcija.findAll", query="SELECT k FROM Kolekcija k")
public class Kolekcija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String ime;

	//bi-directional many-to-one association to Igraukolekciji
	@OneToMany(mappedBy="kolekcija")
	private List<Igraukolekciji> igraukolekcijis;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik korisnik;

	public Kolekcija() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public List<Igraukolekciji> getIgraukolekcijis() {
		return this.igraukolekcijis;
	}

	public void setIgraukolekcijis(List<Igraukolekciji> igraukolekcijis) {
		this.igraukolekcijis = igraukolekcijis;
	}

	public Igraukolekciji addIgraukolekciji(Igraukolekciji igraukolekciji) {
		getIgraukolekcijis().add(igraukolekciji);
		igraukolekciji.setKolekcija(this);

		return igraukolekciji;
	}

	public Igraukolekciji removeIgraukolekciji(Igraukolekciji igraukolekciji) {
		getIgraukolekcijis().remove(igraukolekciji);
		igraukolekciji.setKolekcija(null);

		return igraukolekciji;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

}