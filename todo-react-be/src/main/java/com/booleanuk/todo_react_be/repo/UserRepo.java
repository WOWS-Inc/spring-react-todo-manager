package com.booleanuk.todo_react_be.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booleanuk.todo_react_be.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
