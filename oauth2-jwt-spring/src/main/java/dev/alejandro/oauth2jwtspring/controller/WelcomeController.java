package dev.alejandro.oauth2jwtspring.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping
    public String welcomeController() {
        return "Welcome to the OAuth2 JWT Spring Boot!";
    }
}
