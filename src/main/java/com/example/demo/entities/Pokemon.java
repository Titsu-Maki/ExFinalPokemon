package com.example.demo.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data

public class Pokemon implements Serializable{
	
	@Id
	@SequenceGenerator(name="pokemon_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pokemon_id_seq")
	private Integer id;
	private String nombre;
	private String descripcion;
	private Date fecha_descubrimiento;
	
	@ManyToOne
	@JoinColumn(name="tipo_pokemon")
	private TipoPokemon tipoPokemon;
	
	private Integer generacion;
	private String uuid;
	@JsonIgnore
	@ManyToMany(mappedBy = "pokemones")
    private List<Entrenador> entrenadores = new ArrayList<>();
	
	public Pokemon() {
		// Constructor sin argumentos requerido por JPA y otras configuraciones
	}

	// Constructor con los campos que desees inicializar al crear una instancia
	public Pokemon(String nombre, String descripcion, Date fecha_descubrimiento, TipoPokemon tipoPokemon, Integer generacion, String uuid) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha_descubrimiento = fecha_descubrimiento;
		this.tipoPokemon = tipoPokemon;
		this.generacion = generacion;
		this.uuid = uuid;
	}

}
