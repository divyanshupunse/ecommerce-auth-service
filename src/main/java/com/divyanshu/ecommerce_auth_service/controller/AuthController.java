package com.divyanshu.ecommerce_auth_service.controller;

import com.divyanshu.ecommerce_auth_service.dto.AuthResponse;
import com.divyanshu.ecommerce_auth_service.dto.LoginRequest;
import com.divyanshu.ecommerce_auth_service.dto.SignupRequest;
import com.divyanshu.ecommerce_auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/signup")
    public AuthResponse signup(@RequestBody SignupRequest request){
       return service.signup(request);
    }
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request){
       return service.login(request);
    }


}
