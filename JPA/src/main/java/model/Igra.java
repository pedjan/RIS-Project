package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the igra database table.
 * 
 */
@Entity
@NamedQuery(name="Igra.findAll", query="SELECT i FROM Igra i")
public class Igra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String cpu;

	private String gpu;

	private String ime;

	private String opis;

	private String ram;

	private String storage;

	//bi-directional many-to-one association to Zanrigre
	@ManyToOne
	private Zanrigre zanrigre;

	//bi-directional many-to-one association to Igraukolekciji
	@OneToMany(mappedBy="igra")
	private List<Igraukolekciji> igraukolekcijis;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="igra")
	private List<Komentar> komentars;

	public Igra() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCpu() {
		return this.cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getGpu() {
		return this.gpu;
	}

	public void setGpu(String gpu) {
		this.gpu = gpu;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getRam() {
		return this.ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getStorage() {
		return this.storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public Zanrigre getZanrigre() {
		return this.zanrigre;
	}

	public void setZanrigre(Zanrigre zanrigre) {
		this.zanrigre = zanrigre;
	}

	public List<Igraukolekciji> getIgraukolekcijis() {
		return this.igraukolekcijis;
	}

	public void setIgraukolekcijis(List<Igraukolekciji> igraukolekcijis) {
		this.igraukolekcijis = igraukolekcijis;
	}

	public Igraukolekciji addIgraukolekciji(Igraukolekciji igraukolekciji) {
		getIgraukolekcijis().add(igraukolekciji);
		igraukolekciji.setIgra(this);

		return igraukolekciji;
	}

	public Igraukolekciji removeIgraukolekciji(Igraukolekciji igraukolekciji) {
		getIgraukolekcijis().remove(igraukolekciji);
		igraukolekciji.setIgra(null);

		return igraukolekciji;
	}

	public List<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(List<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setIgra(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setIgra(null);

		return komentar;
	}

}