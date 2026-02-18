package com.example.registration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();

        // Autoriser Angular
        config.setAllowedOrigins(List.of("http://localhost:4200", "http://127.0.0.1:4200"));

        // Méthodes autorisées
        config.setAllowedMethods(List.of(
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "OPTIONS"));

        // Autoriser tous les headers
        config.setAllowedHeaders(List.of("*"));

        // Autoriser les credentials (cookies, auth headers plus tard)
        config.setAllowCredentials(true);

        // Appliquer CORS à toutes les routes
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
