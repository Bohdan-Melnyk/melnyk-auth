package com.blog.melnykauth.controller;

import com.blog.melnykauth.dto.AuthenticationResponseDto;
import com.blog.melnykauth.dto.LogInDto;
import com.blog.melnykauth.dto.UserRegistrationDto;
import com.blog.melnykauth.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthenticationService authenticationService;


    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    @RequestMapping("/registration")
    public void registration(@RequestBody UserRegistrationDto userRegistrationDto) {
        authenticationService.registerUser(userRegistrationDto);
    }

    @PostMapping
    @RequestMapping("/log-in")
    public ResponseEntity<AuthenticationResponseDto> logIn(@RequestBody LogInDto dto) {
        return ResponseEntity.ok(authenticationService.logIn(dto));
    }
}
