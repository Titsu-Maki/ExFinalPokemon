package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entities.Entrenador;
import com.example.demo.entities.Pokemon;
import com.example.demo.repository.RepoEntrenador;
import com.example.demo.repository.RepoPokemon;

@RestController
@RequestMapping("/entrenador")
public class EntrenadorController {
	
	@Autowired
	RepoEntrenador repoentrenador;
	@Autowired
	RepoPokemon pokemonRepository;
	
	@PostMapping("/crear")
	public ResponseEntity<Entrenador> crearEntrenador(@RequestBody Entrenador nuevoEntrenador) {
	    Entrenador entrenadorExistente = repoentrenador.findByEmail(nuevoEntrenador.getEmail());

	    if (entrenadorExistente != null) {
	        return ResponseEntity.badRequest().body(null);
	    }

	    Entrenador entrenadorCreado = repoentrenador.save(nuevoEntrenador);
	    return ResponseEntity.status(HttpStatus.CREATED).body(entrenadorCreado);
	}
	
	@GetMapping("/lista")
	public ResponseEntity<List<Entrenador>> listarEntrenadores() {
	    List<Entrenador> entrenadores = repoentrenador.findAll();

	    if (entrenadores.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    }

	    return ResponseEntity.ok(entrenadores);
	}

	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> obtenerUUIDPorEmail(@RequestParam String email) {
	    Entrenador entrenador = repoentrenador.findByEmail(email);

	    if (entrenador == null) {
	        return ResponseEntity.notFound().build();
	    }

	    Map<String, String> response = new HashMap<>();
	    response.put("uuid", entrenador.getUuid());

	    return ResponseEntity.ok(response);
	}

	 
	 

	 @GetMapping("/{uuid}/pokemons")
	 public ResponseEntity<List<Pokemon>> listarPokemonesDeEntrenador(@PathVariable("uuid") String uuid) {
	     Entrenador entrenador = repoentrenador.findByUuid(uuid);

	     if (entrenador == null) {
	         return ResponseEntity.notFound().build();
	     }

	     List<Pokemon> pokemones = entrenador.getPokemones();

	     return ResponseEntity.ok(pokemones);
	 }

	    
	 @PostMapping("/{entrenadorUuid}/pokemons/{pokemonUuid}")
	 public ResponseEntity<String> agregarPokemonAEntrenador(
	         @PathVariable("entrenadorUuid") String entrenadorUuid,
	         @PathVariable("pokemonUuid") String pokemonUuid) {

	     Entrenador entrenador = repoentrenador.findByUuid(entrenadorUuid);
	     Pokemon pokemon = pokemonRepository.findByUuid(pokemonUuid);

	     if (entrenador == null || pokemon == null) {
	         return ResponseEntity.notFound().build();
	     }

	     List<Pokemon> pokemonesEntrenador = entrenador.getPokemones();

	     // Verificar si el Pokemon ya está asociado al Entrenador
	     boolean yaAsociado = pokemonesEntrenador.contains(pokemon);

	     if (yaAsociado) {
	         return ResponseEntity.badRequest().body("El pokemon ya está registrado al entrenador");
	     }

	     pokemonesEntrenador.add(pokemon);
	     repoentrenador.save(entrenador);

	     return ResponseEntity.ok().build();
	 }


	    

}
