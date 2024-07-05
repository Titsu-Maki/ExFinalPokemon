package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.TipoPokemon;

public interface RepoTipo extends JpaRepository<TipoPokemon, Integer> {
	
	TipoPokemon findByUuid(String uuid);

}
