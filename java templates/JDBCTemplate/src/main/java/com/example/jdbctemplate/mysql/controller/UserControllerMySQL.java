package com.example.jdbctemplate.mysql.controller;

import com.example.jdbctemplate.mysql.model.UserMySQL;
import com.example.jdbctemplate.mysql.repository.UserRepositoryMySQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserControllerMySQL {
    @Autowired
    private UserRepositoryMySQL userRepository;

    @GetMapping
    public List<UserMySQL> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public String addUser(@RequestBody UserMySQL user) {
        userRepository.save(user);
        return "User added successfully!";
    }
}
