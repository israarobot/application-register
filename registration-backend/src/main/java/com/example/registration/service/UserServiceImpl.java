package com.example.registration.service;

import com.example.registration.dto.UserLoginDto;
import com.example.registration.dto.UserRegistrationDto;
import com.example.registration.model.User;
import com.example.registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserRegistrationDto registrationDto) {
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User loginUser(UserLoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return user;
    }

    @Override
    public org.springframework.data.domain.Page<User> getAllUsers(org.springframework.data.domain.Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User updateUser(Long id, UserRegistrationDto userDto) {
        User user = getUserById(id);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        // Simple update logic, normally we'd check if email is taken if changed
        if (!user.getEmail().equals(userDto.getEmail()) && userRepository.existsByEmail(userDto.getEmail())){
             throw new RuntimeException("Email already exists");
        }
        user.setEmail(userDto.getEmail());
        // Only update password if provided
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
}
