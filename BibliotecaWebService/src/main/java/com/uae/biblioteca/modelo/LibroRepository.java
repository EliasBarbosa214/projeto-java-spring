package com.uae.biblioteca.modelo;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.uae.biblioteca.entidades.Libro;

public interface LibroRepository extends CrudRepository<Libro, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Libro l SET l.genero = ?2 WHERE l.autor.id = ?1") // Ajuste na consulta JPQL
    void atualizarGeneroDosLibrosPorAutor(Long autorId, String genero);
}
