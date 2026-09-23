package com.booleanuk.todo_react_be.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booleanuk.todo_react_be.model.Todo;

public interface TodoRepo extends JpaRepository<Todo, Integer> {

}
