package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {

	@Query("select k from Korisnik k where k.korIme =:korIme and k.sifra =:sifra")
	Korisnik login(@Param("korIme")String korIme, @Param("sifra")String sifra);
}
