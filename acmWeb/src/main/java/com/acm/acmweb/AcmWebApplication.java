package com.acm.acmweb;

import com.acm.acmweb.services.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AcmWebApplication implements CommandLineRunner {

	private PeliculaService peliculaService;

	public AcmWebApplication(@Autowired PeliculaService peliculaService) {
		this.peliculaService = peliculaService;
	}

	public static void main(String[] args) {
		SpringApplication.run(AcmWebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		peliculaService.getPeliculaByName("interstellar");
		//peliculaService.getPeliculaByNameUsinRestTemplate("interstellar");
	}
}
