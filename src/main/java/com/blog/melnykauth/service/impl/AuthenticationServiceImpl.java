package com.blog.melnykauth.service.impl;

import com.blog.melnykauth.dto.AuthenticationResponseDto;
import com.blog.melnykauth.dto.LogInDto;
import com.blog.melnykauth.dto.UserRegistrationDto;
import com.blog.melnykauth.entity.User;
import com.blog.melnykauth.service.AuthenticationService;
import com.blog.melnykauth.service.UserService;
import com.blog.melnykauth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationServiceImpl(UserService userService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Optional<User> registerUser(UserRegistrationDto userRegistrationDto) {
        var user = userService.findUserByEmail(userRegistrationDto.getEmail());
        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setEmail(userRegistrationDto.getEmail());
            newUser.setName(userRegistrationDto.getName());
            newUser.setAge(userRegistrationDto.getAge());
            newUser.setSex(userRegistrationDto.getSex());
            newUser.setPass(passwordEncoder.encode(userRegistrationDto.getPass()));
            userService.saveUser(newUser);
            return user;
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Override
    public AuthenticationResponseDto logIn(LogInDto logInDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInDto.getEmail(), logInDto.getPassword()));
        var user = userService.findUserByEmail(logInDto.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Uer not found"));
        var token = jwtUtil.generateToken(user);
        var responseDto = new AuthenticationResponseDto();
        responseDto.setToken(token);
        return responseDto;
    }
}
