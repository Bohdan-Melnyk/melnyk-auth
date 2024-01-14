package com.blog.melnykauth.controller;

import com.blog.melnykauth.dto.UserRegistrationDto;
import com.blog.melnykauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AuthController {

    private final UserService userService;


    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @RequestMapping("/registration")
    public void registration(UserRegistrationDto userRegistrationDto) {
        userService.registerUser(userRegistrationDto);
    }
}
