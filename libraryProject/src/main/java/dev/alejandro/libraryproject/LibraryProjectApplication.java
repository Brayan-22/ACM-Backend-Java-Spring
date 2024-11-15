package dev.alejandro.libraryproject;

import dev.alejandro.libraryproject.persistence.repository.LibroRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class LibraryProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryProjectApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(LibroRepository libroRepository){
		return args -> {
			//libroRepository.findByPrestamoAndCliente(1,1).forEach(l -> log.info("Libro: {}", l));
		};
	}

}
