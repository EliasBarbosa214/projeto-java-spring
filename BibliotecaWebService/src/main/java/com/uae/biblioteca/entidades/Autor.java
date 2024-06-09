package com.uae.biblioteca.entidades;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "autor")
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_autor")
	private Long id;

	@Column(name = "nombre_completo") // Especifica o nome da coluna no banco de dados
	private String nomeCompleto;

	@Column(name = "nacionalidad") // Adiciona a nova propriedade nacionalidad
	private String nacionalidad;

	@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Libro> libros;

	// Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getNacionalidad() { // Método getter para nacionalidad
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) { // Método setter para nacionalidad
		this.nacionalidad = nacionalidad;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}
}
