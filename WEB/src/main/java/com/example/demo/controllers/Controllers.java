package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.InputStream;
import java.io.OutputStream;

import com.example.demo.services.Services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Igra;
import model.Komentar;
import model.Korisnik;

@Controller
@RequestMapping(value="/projekat/")
public class Controllers {

	@Autowired
	Services s;
	
	@PostMapping("registracija")
	public String registracija(@RequestParam("imePrezime")String imePrezime, @RequestParam("korIme")String korIme, @RequestParam("sifra")String sifra, HttpServletRequest request) {
		Korisnik k = s.registracija(imePrezime, korIme, sifra);
		if(k != null) {
			return "prijava";
		} else {
			return "registracija";
		}
	}
	
	@PostMapping("login")
	public String login(@RequestParam("korIme")String korIme,@RequestParam("sifra")String sifra,  HttpServletRequest request) {
		request.getSession().setAttribute("prijavljen", s.login(korIme, sifra));
		request.setAttribute("content", s.getContent());
		request.setAttribute("komentari", s.getKomentari());
		request.setAttribute("zanrovi", s.getZanrovi());
		return "pocetna";
	}
	
	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("prijavljen");
		return "prijava";
	}
	
	@GetMapping("pocetna")
	public String Pocetna(HttpServletRequest request) {
		request.setAttribute("content", s.getContent());
		request.setAttribute("komentari", s.getKomentari());
		request.setAttribute("zanrovi", s.getZanrovi());
		return "pocetna";
	}
	
	@PostMapping("komentarisi")
	public String komentarisi(@RequestParam("komentar")String komentar, @RequestParam("komentatorID")Integer komentatorID, @RequestParam("postID")Integer postID, HttpServletRequest request) {
		Korisnik k = s.findKorisnikById(komentatorID);
		Igra i = s.findIgraById(postID);
		Komentar ko = new Komentar();
		ko.setIgra(i);
		ko.setSadrzaj(komentar);
		ko.setKorisnik(k);
		
		s.saveKomentar(ko);
		request.setAttribute("content", s.getContent());
		request.setAttribute("komentari", s.getKomentari());
		request.setAttribute("zanrovi", s.getZanrovi());
		return "pocetna";
	}
	
	@GetMapping("pretraga")
	public String Pretraga(@RequestParam("imeIgre")String imeIgre, @RequestParam("zanrIgre")Integer zanrIgre, HttpServletRequest request) {
		if(imeIgre == "") {
			request.setAttribute("content", s.getIgraByZanrId(zanrIgre));
			request.setAttribute("pretraga", "Pretraga za zanr: " + zanrIgre);
		} else if(zanrIgre == -1) {
			request.setAttribute("content", s.getIgraByName(imeIgre));
			request.setAttribute("pretraga", "Pretraga za igru: " + imeIgre);
		} else {
			request.setAttribute("content", s.getIgraByNameIZanrId(imeIgre, zanrIgre));
			request.setAttribute("pretraga", "Pretraga za igru: " + imeIgre + " i zanr: " + zanrIgre);
		}
		request.setAttribute("komentari", s.getKomentari());
		request.setAttribute("zanrovi", s.getZanrovi());
		return "pocetna";
	}
	
	@GetMapping("kolekcije")
	public String Kolekcije(HttpServletRequest request) {
		request.getSession().getAttribute("prijavljen");
		request.setAttribute("kolekcije", s.getKolekcije());
		request.setAttribute("igre", s.getContent());
		request.getSession().removeAttribute("kolekcija");
		return "kolekcije";
	}
	
	@PostMapping("napraviKolekciju")
	public String napraviKolekciju(@RequestParam("imeKolekcije")String imeKolekcije, @RequestParam("kID")Integer kID, HttpServletRequest request) {
		s.saveKolekcija(imeKolekcije, kID);
		request.getSession().getAttribute("prijavljen");
		request.setAttribute("kolekcije", s.getKolekcije());
		request.setAttribute("igre", s.getContent());
		return "kolekcije";
	}
	
	@GetMapping("prikazKolekcije")
	public String prikazKolekcije(@RequestParam("kolekcijaID")Integer kolekcijaID, HttpServletRequest request) {
		request.getSession().getAttribute("prijavljen");
		request.setAttribute("kolekcije", s.getKolekcije());
		request.getSession().setAttribute("kolekcija", s.getKolekcijaById(kolekcijaID));
		request.setAttribute("igre", s.getContent());
		return "kolekcije";
	}
	
	@PostMapping("igreUkolekciju")
	public String igreUkolekciju(@RequestParam("igraID")Integer igraID, @RequestParam("kID")Integer kID, HttpServletRequest request) {
		s.saveIgraukolekciji(igraID, kID);
		request.setAttribute("kolekcije", s.getKolekcije());
		request.getSession().getAttribute("kolekcija");
		request.setAttribute("igre", s.getContent());
		return "kolekcije";
	}
	
	@GetMapping("/izvestaji")
	public String izvestaji(HttpServletRequest request) {
		request.setAttribute("zanrovi", s.getZanrovi());
		return "izvestaji";
	}
	
	@GetMapping("/izgenerisiIzvestaj1")
	public void izgenerisiIzvestaj1(@RequestParam("zanrIgre")Integer zanrIgre, HttpServletResponse response) throws Exception{
		response.setContentType("text/html");
		List<Igra> igre = s.getIgraByZanrId(zanrIgre);
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(igre);
		InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/Izvestaj.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		Map<String, Object> params = new HashMap<String, Object>();
		System.out.println("PROSAO MAPIRANJE");
		params.put("zanr", s.getZanrById(zanrIgre).getZanr());
		System.out.println("PROSAO put");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		System.out.println("PROSAO print");
		inputStream.close();
		System.out.println("zatvorio is");
		
		response.setContentType("application/x-download");
		response.addHeader("Content-disposition", "attachment; filename=Izvestaj.pdf");
		OutputStream out = response.getOutputStream();
		System.out.println("PROSAO output");
		JasperExportManager.exportReportToPdfStream(jasperPrint,out);
		System.out.println("PROSAO sve");
	}
	
}
