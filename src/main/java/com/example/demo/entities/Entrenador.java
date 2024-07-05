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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entrenador implements Serializable{
	
	@Id
	@SequenceGenerator(name="entrenador_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "entrenador_id_seq")
	private Integer id;
	private String nombre;
	private String apellido;
	private String email;
	private Date fecha_nacimiento;
	private Date fecha_vinculacion;
	@JsonIgnore
	@ManyToMany
    @JoinTable(
        name = "captura",
        joinColumns = @JoinColumn(name = "entrenador_id"),
        inverseJoinColumns = @JoinColumn(name = "pokemon_id")
    )
    private List<Pokemon> pokemones = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="pueblo_id")
	private Pueblo pueblo;
	private String uuid;
	
	
	// Métodos para agregar y eliminar pokemones en la lista
    public void agregarPokemon(Pokemon pokemon) {
        pokemones.add(pokemon);
        pokemon.getEntrenadores().add(this);
    }

    public void eliminarPokemon(Pokemon pokemon) {
        pokemones.remove(pokemon);
        pokemon.getEntrenadores().remove(this);
    }
	
}
