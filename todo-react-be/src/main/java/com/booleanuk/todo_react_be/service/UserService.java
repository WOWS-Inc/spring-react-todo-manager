package com.booleanuk.todo_react_be.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.booleanuk.todo_react_be.model.User;
import com.booleanuk.todo_react_be.repo.UserRepo;

@Service
public class UserService {

    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {

        this.userRepo = userRepo;
    }

    public List<User> getAllUsers() {

        return userRepo.findAll();
    }
    public Optional<User> getUserById(int id) {

        return userRepo.findById(id);
    }

    public User create(User user) {

        return userRepo.save(user);
    }
}
