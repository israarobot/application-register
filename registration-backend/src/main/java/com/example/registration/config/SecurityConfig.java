package com.example.registration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.config.Customizer;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CorsConfigurationSource corsConfigurationSource;

    // Encoder pour les mots de passe (utile pour plus tard : login / JWT)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Désactiver CSRF (nécessaire pour Angular en prototype)
                .csrf(csrf -> csrf.disable())

                // Activer CORS
                .cors(cors -> cors.configurationSource(corsConfigurationSource))

                // Autoriser TOUTES les requêtes (pas d'authentification)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll());

        return http.build();
    }
}
