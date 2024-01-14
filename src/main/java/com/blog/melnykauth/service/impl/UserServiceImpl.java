package com.blog.melnykauth.service.impl;

import com.blog.melnykauth.dto.UserRegistrationDto;
import com.blog.melnykauth.entity.User;
import com.blog.melnykauth.repository.UserRepo;
import com.blog.melnykauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void saveUser(User user) {
        userRepo.save(user);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User registerUser(UserRegistrationDto userRegistrationDto) {
        var user = findUserByEmail(userRegistrationDto.getEmail());
        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setEmail(userRegistrationDto.getEmail());
            newUser.setName(userRegistrationDto.getName());
            newUser.setAge(userRegistrationDto.getAge());
            newUser.setSex(userRegistrationDto.getSex());
            newUser.setPass(userRegistrationDto.getPass());
            saveUser(newUser);
            return newUser;
        } else {
            return null;
        }
    }
}
