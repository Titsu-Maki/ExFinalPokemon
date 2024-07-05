package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Entrenador;

public interface RepoEntrenador extends JpaRepository<Entrenador, Integer> {
	Entrenador findByEmail(String email);
	Entrenador findByUuid(String uuid);

}
