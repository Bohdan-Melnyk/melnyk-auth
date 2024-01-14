package com.blog.melnykauth.service;

import com.blog.melnykauth.dto.UserRegistrationDto;
import com.blog.melnykauth.entity.User;

import java.util.Optional;

public interface UserService {

    void saveUser(User user);

    Optional<User> findUserByEmail(String email);

    User registerUser(UserRegistrationDto userRegistrationDto);
}
