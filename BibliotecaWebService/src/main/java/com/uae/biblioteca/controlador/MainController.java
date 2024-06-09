package com.uae.biblioteca.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index"; // Retorna o nome do arquivo HTML (index.html) na pasta static
    }
}
