package com.blog.melnykauth.service;

import com.blog.melnykauth.dto.AuthenticationResponseDto;
import com.blog.melnykauth.dto.LogInDto;
import com.blog.melnykauth.dto.UserRegistrationDto;
import com.blog.melnykauth.entity.User;

import java.util.Optional;

public interface AuthenticationService {

    Optional<User> registerUser(UserRegistrationDto userRegistrationDto);

    AuthenticationResponseDto logIn(LogInDto logInDto);
}
