package com.example.cartcheckoutservice.configs;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JWTConfig {
    @Value("${jwt.secret.key}")
    private String SecretKey;
    @Bean
    public SecretKey secretKey() {
        return Keys.hmacShaKeyFor(SecretKey.getBytes());
    }
}

