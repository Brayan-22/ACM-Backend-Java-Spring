package dev.alejandro.oauth2jwtspring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "rsa")
public record RSAKeyProperties(
        RSAPrivateKey privateKey,
        RSAPublicKey publicKey
) {
}
