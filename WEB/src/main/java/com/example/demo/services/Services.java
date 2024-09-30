package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.NeuspeloPisanjeException;
import com.example.demo.repositories.IgraRepository;
import com.example.demo.repositories.IgraukolekcijiRepository;
import com.example.demo.repositories.KolekcijaRepository;
import com.example.demo.repositories.KomentarRepository;
import com.example.demo.repositories.KorisnikRepository;
import com.example.demo.repositories.ZanrigreRepository;

import model.Igra;
import model.Igraukolekciji;
import model.Kolekcija;
import model.Komentar;
import model.Korisnik;
import model.Zanrigre;

@Service
public class Services {

	@Autowired
	KorisnikRepository kr;
	
	@Autowired
	IgraRepository ir;
	
	@Autowired
	KomentarRepository krr;
	
	@Autowired
	KolekcijaRepository kkr;
	
	@Autowired
	ZanrigreRepository zir;
	
	@Autowired
	IgraukolekcijiRepository iukr;
	
	public Korisnik login(String korIme, String sifra) {
		Korisnik k = kr.login(korIme, sifra);
		if(k != null) {
			return k;
		} else {
			return null;
		}
	}


	public List<Igra> getContent() {
		return ir.findAll();
	}

	public List<Komentar> getKomentari() {
		return krr.findAll();
	}
	
	public Korisnik findKorisnikById(int id) {
		Korisnik k = kr.findById(id).get();
		if(k != null) {
			return k;
		} else {
			return null;
		}
	}
	
	public Igra findIgraById(int id) {
		Igra i = ir.findById(id).get();
		if(i != null) {
			return i;
		} else {
			return null;
		}
	}


	public Komentar saveKomentar(Komentar k) {
		Komentar komentar = krr.save(k);
		if(komentar != null) {
			return komentar;
		} else {
			throw new NeuspeloPisanjeException("Greska prilikom unosa komentara ubazu");
		}
	}


	public List<Igra> getIgraByName(String imeIgre) {
		List<Igra> i = ir.findByName(imeIgre);
		if(i != null) {
			return i;
		} else {
			return null;
		}
	}
	
	public List<Igra> getIgraByZanrId(Integer zanrIgre) {
		List<Igra> i = ir.getIgraByZanrId(zanrIgre);
		if(i != null) {
			return i;
		} else {
			return null;
		}
	}
	
	public List<Igra> getIgraByNameIZanrId(String imeIgre, Integer zanrIgre) {
		List<Igra> i = ir.getIgraByNameIZanrId(imeIgre, zanrIgre);
		if(i != null) {
			return i;
		} else {
			return null;
		}
	}
	


	public List<Kolekcija> getKolekcije() {
		return kkr.findAll();
	}


	public Kolekcija saveKolekcija(String imeKolekcije, Integer kID) {
		Kolekcija k = new Kolekcija();
		k.setIme(imeKolekcije);
		k.setKorisnik(kr.findById(kID).get());
		Kolekcija k1 = kkr.save(k);
		if(k1 != null) {
			return k1;
		} else {
			throw new NeuspeloPisanjeException("Greska prilikom unosa kolekcije ubazu");
		}
	}


	public void ukloniIzKolekcije(Integer id) {
		kkr.delete(kkr.findById(id).get());
	}


	public Korisnik registracija(String imePrezime, String korIme, String sifra) {
		Korisnik k = new Korisnik();
		k.setImePrezime(imePrezime);
		k.setKorIme(korIme);
		k.setSifra(sifra);
		return kr.save(k);
	}


	public List<Zanrigre> getZanrovi() {
		return zir.findAll();
	}


	public Kolekcija getKolekcijaById(Integer kolekcijaID) {
		return kkr.findById(kolekcijaID).get();
	}


	public void saveIgraukolekciji(Integer igraID, Integer kID) {
		Igraukolekciji iuk = new Igraukolekciji();
		iuk.setIgra(ir.findById(igraID).get());
		iuk.setKolekcija(kkr.findById(kID).get());
		Igraukolekciji iuk1 = iukr.save(iuk);
		if(iuk1 == null) {
			throw new NeuspeloPisanjeException("Greska prilikom unosa ubazu");
		}
	}


	public Zanrigre getZanrById(Integer zanrIgre) {
		return zir.findById(zanrIgre).get();
	}

}
