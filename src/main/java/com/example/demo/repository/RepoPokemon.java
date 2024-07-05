package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Pokemon;

public interface RepoPokemon extends JpaRepository<Pokemon, Integer> {
	List<Pokemon> findByTipoPokemon_Descripcion(String tipo);
	Pokemon findByUuid(String uuid);

}
