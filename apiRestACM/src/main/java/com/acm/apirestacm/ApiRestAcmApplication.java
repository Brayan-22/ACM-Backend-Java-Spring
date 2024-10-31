package com.acm.apirestacm;

import com.acm.apirestacm.persistence.entity.EmbeddedClass.EnumRol;
import com.acm.apirestacm.persistence.entity.Role;
import com.acm.apirestacm.persistence.entity.UserEntity;
import com.acm.apirestacm.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@SpringBootApplication
@EnableCaching
public class ApiRestAcmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestAcmApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserRepository userRepository) {
		return args -> {
			userRepository.save(UserEntity.builder()
					.username("alejandro")
					.email("alejandro@correo.com")
					.password(new BCryptPasswordEncoder().encode("alejandro1234"))
					.roles(Set.of(Role.builder()
							.rol(EnumRol.ADMIN)
							.build()))
					.build());
		};
	}
}
