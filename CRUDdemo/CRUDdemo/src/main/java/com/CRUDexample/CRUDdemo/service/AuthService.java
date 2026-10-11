package com.CRUDexample.CRUDdemo.service;

import org.springframework.stereotype.Service;

import com.CRUDexample.CRUDdemo.dto.RegisterRequest;
import com.CRUDexample.CRUDdemo.entity.User;
import com.CRUDexample.CRUDdemo.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service 
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder){

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public String register(RegisterRequest request){
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return "Username already exists";
        }

        User user = new User();

        user.setUsername(request.getUsername());

        user.setPassword(
            passwordEncoder.encode(request.getPassword())
        );

        user.setRole("USER");

        userRepository.save(user);

        return "User registered successfully";
    }
    
}
