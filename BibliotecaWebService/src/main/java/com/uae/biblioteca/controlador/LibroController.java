package com.uae.biblioteca.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uae.biblioteca.entidades.Libro;
import com.uae.biblioteca.modelo.LibroRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Query;

import java.util.List;

@Controller
@RequestMapping("/libro")
public class LibroController {


	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private LibroRepository libroRepository;


	@GetMapping("/")
	public String listarLibros(Model model) {
		model.addAttribute("libros", libroRepository.findAll());
		return "index";
	}

	@GetMapping("/form")
	public String mostrarFormulario(Model model) {
		model.addAttribute("libro", new Libro());
		return "form-libro";
	}

	@PostMapping("/guardar")
	public String guardarLibro(@ModelAttribute Libro libro) {
		libroRepository.save(libro);
		return "redirect:/libro/";
	}

	@GetMapping("/editar")
	public String mostrarFormularioEdicao(@RequestParam("id") Long id_libro, Model model) {
		Libro libro = libroRepository.findById(id_libro).orElse(null);
		model.addAttribute("livro", libro);
		return "form-libro";
	}

	@GetMapping("/eliminar")
	public String eliminarLibro(@RequestParam("id") Long id_libro) {
		libroRepository.deleteById(id_libro);
		return "redirect:/libro/";
	}

	@GetMapping("/mais-emprestados")
	public ResponseEntity<List<Libro>> listarLivrosMaisEmprestados() {
		Pageable pageable = PageRequest.of(0, 10); // Exemplo: top 10 mais emprestados
		List<Libro> libros = libroRepository.findTopLibrosByNumeroEmprestimos(pageable);
		return new ResponseEntity<>(libros, HttpStatus.OK);
	}

}
