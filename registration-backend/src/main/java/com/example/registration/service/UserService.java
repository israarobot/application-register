package com.example.registration.service;

import com.example.registration.dto.UserLoginDto;
import com.example.registration.dto.UserRegistrationDto;
import com.example.registration.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User registerUser(UserRegistrationDto registrationDto);
    User loginUser(UserLoginDto loginDto);
    org.springframework.data.domain.Page<User> getAllUsers(org.springframework.data.domain.Pageable pageable);
    User getUserById(Long id);
    User updateUser(Long id, UserRegistrationDto userDto);
    void deleteUser(Long id);
}
