package com.divyanshu.ecommerce_auth_service.service;

import com.divyanshu.ecommerce_auth_service.dto.AuthResponse;
import com.divyanshu.ecommerce_auth_service.dto.LoginRequest;
import com.divyanshu.ecommerce_auth_service.dto.SignupRequest;
import com.divyanshu.ecommerce_auth_service.entity.Role;
import com.divyanshu.ecommerce_auth_service.entity.User;
import com.divyanshu.ecommerce_auth_service.repository.UserRepository;
import com.divyanshu.ecommerce_auth_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil util;

    public AuthResponse signup(SignupRequest request){

        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        if ("ADMIN".equalsIgnoreCase(request.getRole())) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.CUSTOMER);
        }

        repository.save(user);

        return new AuthResponse(null,"User registered successfully");
    }

    public AuthResponse login(LoginRequest request){

        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Password");
        }

        String token = util.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole().name()
        );

        return new AuthResponse(token, "Login successful");
    }
}
