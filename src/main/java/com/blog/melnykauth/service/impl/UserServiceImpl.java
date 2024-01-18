package com.blog.melnykauth.service.impl;

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
}
