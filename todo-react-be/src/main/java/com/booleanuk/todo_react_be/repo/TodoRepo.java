package com.booleanuk.todo_react_be.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booleanuk.todo_react_be.model.Todo;
import com.booleanuk.todo_react_be.model.User;

public interface TodoRepo extends JpaRepository<Todo, Integer> {

    List<Todo> findByUser(User user);
}
