package com.uae.biblioteca.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uae.biblioteca.entidades.Libro;
import com.uae.biblioteca.modelo.LibroRepository;

import java.util.List;

@Controller
@RequestMapping("/libro")
public class LibroController {

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
		model.addAttribute("libro", libro);
		return "form-libro";
	}

	@GetMapping("/eliminar")
	public String eliminarLibro(@RequestParam("id") Long id_libro) {
		libroRepository.deleteById(id_libro);
		return "redirect:/libro/";
	}
}
