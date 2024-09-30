package model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the igraukolekciji database table.
 * 
 */
@Entity
@NamedQuery(name="Igraukolekciji.findAll", query="SELECT i FROM Igraukolekciji i")
public class Igraukolekciji implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Igra
	@ManyToOne
	private Igra igra;

	//bi-directional many-to-one association to Kolekcija
	@ManyToOne
	private Kolekcija kolekcija;

	public Igraukolekciji() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Igra getIgra() {
		return this.igra;
	}

	public void setIgra(Igra igra) {
		this.igra = igra;
	}

	public Kolekcija getKolekcija() {
		return this.kolekcija;
	}

	public void setKolekcija(Kolekcija kolekcija) {
		this.kolekcija = kolekcija;
	}

}