package com.example.demo.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoPokemon implements Serializable{
	
	@Id
	@SequenceGenerator(name="tipo_pokemon_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "tipo_pokemon_id_seq")
	private Integer id;
	private String descripcion;
	private String uuid;
	@JsonIgnore
	@OneToMany(mappedBy = "tipoPokemon", cascade = CascadeType.ALL)
    private List<Pokemon> pokemones = new ArrayList<>();
	
}
