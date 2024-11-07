package dev.alejandro.springactuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@SpringBootApplication

public class SpringActuatorApplication {
	private static final Logger log = LoggerFactory.getLogger(SpringActuatorApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringActuatorApplication.class, args);
	}

}
