package com.taskmanagementsystem.EmployeeTaskManagementSystem.controller;

import com.taskmanagementsystem.EmployeeTaskManagementSystem.dto.LoginRequestDto;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.response.LoginResponse;
import com.taskmanagementsystem.EmployeeTaskManagementSystem.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequestDto request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String token = jwtUtil.generateToken(request.getUsername());

        System.out.println(token);

        return LoginResponse.builder()
                .token(token)
                .build();
    }
}
