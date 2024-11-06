package dev.alejandro.oauth2jwtspring;

import dev.alejandro.oauth2jwtspring.config.RSAKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({RSAKeyProperties.class})
public class Oauth2JwtSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2JwtSpringApplication.class, args);
	}
}
