package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Pueblo;
import java.util.List;


public interface RepoPueblo extends JpaRepository<Pueblo, Integer>{
	
	Pueblo findByNombre(String nombre);

}
