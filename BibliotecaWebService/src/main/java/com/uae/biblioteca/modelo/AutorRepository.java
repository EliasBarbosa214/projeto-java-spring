package com.uae.biblioteca.modelo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uae.biblioteca.entidades.Autor;


@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    // Métodos de consulta adicionais, se necessário
}
