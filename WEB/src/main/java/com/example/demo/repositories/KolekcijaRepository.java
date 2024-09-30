package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Kolekcija;

public interface KolekcijaRepository extends JpaRepository<Kolekcija, Integer> {

}
