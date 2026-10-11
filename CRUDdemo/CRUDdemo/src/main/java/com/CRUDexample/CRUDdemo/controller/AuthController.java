package com.CRUDexample.CRUDdemo.controller;

import com.CRUDexample.CRUDdemo.dto.LoginRequest;
import com.CRUDexample.CRUDdemo.dto.LoginResponse;
import com.CRUDexample.CRUDdemo.dto.RegisterRequest;
import com.CRUDexample.CRUDdemo.security.JwtService;
import com.CRUDexample.CRUDdemo.service.AuthService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(
            AuthService authService,
            AuthenticationManager authenticationManager,
            JwtService jwtService) {

        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String token =
                jwtService.generateToken(
                        request.getUsername()
                );

        return new LoginResponse(token);
    }
}
