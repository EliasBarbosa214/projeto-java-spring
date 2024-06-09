package com.uae.biblioteca.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uae.biblioteca.entidades.Autor;
import com.uae.biblioteca.entidades.Libro;
import com.uae.biblioteca.modelo.AutorRepository;
import com.uae.biblioteca.modelo.LibroRepository; // Importe necessário para LibroRepository

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/autor")
public class AutorController {

	@Autowired
	private AutorRepository autorRepository;

	@Autowired
	private LibroRepository libroRepository; // Declaração do LibroRepository

	@GetMapping("/get")
	public List<Autor> listarAutores() {
		Iterable<Autor> autoresIterable = autorRepository.findAll();
		List<Autor> autoresList = new ArrayList<>();
		autoresIterable.forEach(autoresList::add);
		return autoresList;
	}

	@PostMapping("/agregar")
	public ResponseEntity<String> agregarAutor(@RequestBody Autor autor) {
		autorRepository.save(autor);
		return ResponseEntity.ok("Autor adicionado com sucesso");
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> eliminarAutor(@PathVariable Long id) {
		autorRepository.deleteById(id);
		return ResponseEntity.ok("Autor excluído com sucesso");
	}

	@GetMapping("/{autorId}/libros")
	public List<Libro> listarLibrosPorAutor(@PathVariable Long autorId) {
		Autor autor = autorRepository.findById(autorId).orElse(null);
		if (autor != null) {
			return autor.getLibros();
		} else {
			return null;
		}
	}

	@PostMapping("/{autorId}/libros/agregar")
	public ResponseEntity<String> adicionarLibro(@PathVariable Long autorId, @RequestBody Libro libro) {
		Autor autor = autorRepository.findById(autorId).orElse(null);
		if (autor != null) {
			libro.setAutor(autor);
			libroRepository.save(libro);
			return ResponseEntity.ok("Libro adicionado com sucesso");
		} else {
			return ResponseEntity.badRequest().body("Autor não encontrado");
		}
	}

	@DeleteMapping("/{autorId}/libros/eliminar/{libroId}")
	public ResponseEntity<String> eliminarLibro(@PathVariable Long autorId, @PathVariable Long libroId) {
		Libro libro = libroRepository.findById(libroId).orElse(null);
		if (libro != null && libro.getAutor().getId().equals(autorId)) {
			libroRepository.deleteById(libroId);
			return ResponseEntity.ok("Libro excluído com sucesso");
		} else {
			return ResponseEntity.badRequest().body("Libro não encontrado ou não pertence ao autor especificado");
		}
	}
}
