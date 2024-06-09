package com.uae.biblioteca.controlador;

import com.uae.biblioteca.entidades.Libro;
import com.uae.biblioteca.modelo.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private LibroRepository libroRepository;

    @PostMapping("/emprestar/{idLibro}")
    public String emprestarLibro(@PathVariable Long idLibro) {
        Libro libro = libroRepository.findById(idLibro).orElse(null);
        if (libro != null && libro.isDisponivel()) {
            libro.setDisponivel(false);
            libroRepository.save(libro);
            return "Livro emprestado com sucesso";
        } else {
            return "Livro não disponível para empréstimo";
        }
    }

    @PostMapping("/devolver/{idLibro}")
    public String devolverLibro(@PathVariable Long idLibro) {
        Libro libro = libroRepository.findById(idLibro).orElse(null);
        if (libro != null && !libro.isDisponivel()) {
            libro.setDisponivel(true);
            libroRepository.save(libro);
            return "Livro devolvido com sucesso";
        } else {
            return "Livro já está disponível ou não encontrado";
        }
    }

    @GetMapping("/livros-disponiveis")
    public List<Libro> getLivrosDisponiveis() {
        return libroRepository.findLivrosDisponiveis();
    }
}
