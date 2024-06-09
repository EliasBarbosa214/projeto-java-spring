package com.uae.biblioteca.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "libro") // Corrigir o nome da tabela para "livro"
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_libro") // Especifica o nome correto da coluna de ID na tabela do banco de dados
	private Long id;

	private String titulo;

	private String genero;

	private boolean disponivel;

	@ManyToOne
	@JoinColumn(name = "id_autor") // Especifica o nome correto da coluna na tabela do banco de dados
	@JsonBackReference
	private Autor autor;

	// Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
}
