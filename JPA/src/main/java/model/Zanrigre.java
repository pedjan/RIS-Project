package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the zanrigre database table.
 * 
 */
@Entity
@NamedQuery(name="Zanrigre.findAll", query="SELECT z FROM Zanrigre z")
public class Zanrigre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String zanr;

	//bi-directional many-to-one association to Igra
	@OneToMany(mappedBy="zanrigre")
	private List<Igra> igras;

	public Zanrigre() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getZanr() {
		return this.zanr;
	}

	public void setZanr(String zanr) {
		this.zanr = zanr;
	}

	public List<Igra> getIgras() {
		return this.igras;
	}

	public void setIgras(List<Igra> igras) {
		this.igras = igras;
	}

	public Igra addIgra(Igra igra) {
		getIgras().add(igra);
		igra.setZanrigre(this);

		return igra;
	}

	public Igra removeIgra(Igra igra) {
		getIgras().remove(igra);
		igra.setZanrigre(null);

		return igra;
	}

}