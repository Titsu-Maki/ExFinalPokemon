package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.RepoTipo;
import com.example.demo.entities.TipoPokemon;

@RestController
@RequestMapping("/tipo")
public class TipoController {
	
	@Autowired
	RepoTipo tiporepo;
	
	@GetMapping
	public ResponseEntity<List<TipoPokemon>> listarTipos(){
		List<TipoPokemon> tipos = tiporepo.findAll();
		
		if(tipos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(tipos);
	}
	

}
