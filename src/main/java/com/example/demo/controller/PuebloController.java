package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Pueblo;
import com.example.demo.repository.RepoPueblo;


@RestController
@RequestMapping("/pueblo")
public class PuebloController {
	@Autowired
	RepoPueblo repoPueblo;
	
	
	@GetMapping
    public ResponseEntity<List<Pueblo>> obtenerListaPueblos() {
        List<Pueblo> listaPueblos = repoPueblo.findAll();
        return new ResponseEntity<>(listaPueblos, HttpStatus.OK);
    }
	
	@PostMapping
	public ResponseEntity<Pueblo> crearPueblo(@RequestBody Pueblo nuevoPueblo) {
	    Pueblo puebloExistente = repoPueblo.findByNombre(nuevoPueblo.getNombre());

	    if (puebloExistente != null) {
	        return ResponseEntity.badRequest().body(null);
	    }

	    Pueblo puebloCreado = repoPueblo.save(nuevoPueblo);
	    return ResponseEntity.status(HttpStatus.CREATED).body(puebloCreado);
	}


}
