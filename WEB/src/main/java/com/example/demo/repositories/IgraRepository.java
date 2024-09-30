package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Igra;

public interface IgraRepository extends JpaRepository<Igra, Integer> {
	@Query("select i from Igra i where i.ime =:imeIgre")
	List<Igra> findByName(@Param("imeIgre")String imeIgre);
	
	@Query("select i from Igra i where i.zanrigre.id =:zanrIgre")
	List<Igra> getIgraByZanrId(@Param("zanrIgre")Integer zanrIgre);
	
	@Query("select i from Igra i where i.ime =:imeIgre and i.zanrigre.id =:zanrIgre")
	List<Igra> getIgraByNameIZanrId(@Param("imeIgre")String imeIgre, @Param("zanrIgre")Integer zanrIgre);
	
}
