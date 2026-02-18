package com.example.registration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class RootController {

    // a) GET / → Welcome Endpoint
    @GetMapping("/")
    public Map<String, Object> welcome() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Welcome to Registration API");
        response.put("status", "Server is running");

        Map<String, String> endpoints = new LinkedHashMap<>();
        endpoints.put("Register", "POST /api/users/register");
        endpoints.put("Login", "POST /api/users/login");
        endpoints.put("Get All Users", "GET /api/users");
        endpoints.put("Get User By ID", "GET /api/users/{id}");
        endpoints.put("Update User", "PUT /api/users/{id}");
        endpoints.put("Delete User", "DELETE /api/users/{id}");

        response.put("endpoints", endpoints);
        return response;
    }

    // b) GET /health → Health Check
    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("status", "UP");
        response.put("message", "Application is healthy");
        return response;
    }
}
