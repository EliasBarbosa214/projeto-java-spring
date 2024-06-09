package com.uae.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class BibliotecaWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BibliotecaWebServiceApplication.class);
		Environment env = app.run(args).getEnvironment();
		String port = env.getProperty("server.port");
		System.out.println("Aplicativo iniciado na porta: " + port);
	}
}
