package com.uae.biblioteca.modelo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uae.biblioteca.entidades.Libro;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface LibroRepository extends CrudRepository<Libro, Long> {
    @Query("SELECT l FROM Libro l WHERE l.disponivel = true")
    List<Libro> findLivrosDisponiveis();
}
