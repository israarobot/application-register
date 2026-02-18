package com.example.registration.controller;

import com.example.registration.dto.UserLoginDto;
import com.example.registration.dto.UserRegistrationDto;
import com.example.registration.model.User;
import com.example.registration.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")

public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDto dto) {

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        userRepository.save(user);

        return ResponseEntity.ok(user);
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDto loginDto) {

        Optional<User> userOpt = userRepository.findByEmail(loginDto.getEmail());

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Email ou mot de passe incorrect");
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Email ou mot de passe incorrect");
        }

        return ResponseEntity.ok(user);
    }

    // ✅ DELETE
    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        userRepository.delete(userOpt.get());
        return ResponseEntity.ok("User deleted successfully");
    }
}
