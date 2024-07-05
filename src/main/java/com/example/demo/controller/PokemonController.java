package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Entrenador;
import com.example.demo.entities.Pokemon;
import com.example.demo.entities.TipoPokemon;
import com.example.demo.repository.RepoPokemon;
import com.example.demo.repository.RepoTipo;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {
	
	@Autowired
    private RepoPokemon pokemonRepository;
	@Autowired
	private RepoTipo tiporepo;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Pokemon>> listarPokemons() {
	    List<Pokemon> pokemones = pokemonRepository.findAll();

	    if (pokemones.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    }

	    return ResponseEntity.ok(pokemones);
	}


	@GetMapping("/{tipoUuid}/pokemons")
	public ResponseEntity<List<Pokemon>> listarPokemonesPorTipo(@PathVariable("tipoUuid") String tipoUuid) {
	    TipoPokemon tipoPokemon = tiporepo.findByUuid(tipoUuid);

	    if (tipoPokemon == null) {
	        return ResponseEntity.notFound().build();
	    }

	    List<Pokemon> pokemones = tipoPokemon.getPokemones();

	    if (pokemones.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }

	    return ResponseEntity.ok(pokemones);
	}

    
    @PostMapping("/registrar")
    public ResponseEntity<Pokemon> registrarPokemon(@RequestBody Pokemon nuevoPokemon) {
        if (nuevoPokemon.getUuid() != null && pokemonRepository.findByUuid(nuevoPokemon.getUuid()) != null) {
            return ResponseEntity.badRequest().body(null); // O podrías lanzar una excepción indicando que ya existe
        }

        Pokemon pokemonGuardado = pokemonRepository.save(nuevoPokemon);
        return ResponseEntity.ok(pokemonGuardado);
    }

    
    

}
