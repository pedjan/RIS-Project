package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the korisnik database table.
 * 
 */
@Entity
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String imePrezime;

	private String korIme;

	private String sifra;

	//bi-directional many-to-one association to Kolekcija
	@OneToMany(mappedBy="korisnik")
	private List<Kolekcija> kolekcijas;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="korisnik")
	private List<Komentar> komentars;

	public Korisnik() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImePrezime() {
		return this.imePrezime;
	}

	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}

	public String getKorIme() {
		return this.korIme;
	}

	public void setKorIme(String korIme) {
		this.korIme = korIme;
	}

	public String getSifra() {
		return this.sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public List<Kolekcija> getKolekcijas() {
		return this.kolekcijas;
	}

	public void setKolekcijas(List<Kolekcija> kolekcijas) {
		this.kolekcijas = kolekcijas;
	}

	public Kolekcija addKolekcija(Kolekcija kolekcija) {
		getKolekcijas().add(kolekcija);
		kolekcija.setKorisnik(this);

		return kolekcija;
	}

	public Kolekcija removeKolekcija(Kolekcija kolekcija) {
		getKolekcijas().remove(kolekcija);
		kolekcija.setKorisnik(null);

		return kolekcija;
	}

	public List<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(List<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setKorisnik(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setKorisnik(null);

		return komentar;
	}

}