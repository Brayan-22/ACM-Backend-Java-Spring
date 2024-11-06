package dev.alejandro.oauth2jwtspring.controller;


import dev.alejandro.oauth2jwtspring.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth")
    public String token(Authentication authentication) {
        return authService.generateToken(authentication);
    }
}
