package com.uae.biblioteca.modelo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uae.biblioteca.entidades.Libro;

@Repository
public interface LibroRepository extends CrudRepository<Libro, Long> {
    // Métodos de consulta adicionais, se necessário
}
