package com.booleanuk.todo_react_be.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booleanuk.todo_react_be.model.Todo;
import com.booleanuk.todo_react_be.service.TodoService;
import com.booleanuk.todo_react_be.service.UserService;
import com.booleanuk.todo_react_be.util.DatabaseSeeder;

@RestController
@RequestMapping("api")
@CrossOrigin("http://localhost:5173/")
public class MainController {

    private UserService userService;
    private TodoService todoService;

    public MainController(UserService userService, TodoService todoService) {

        this.userService = userService;
        this.todoService = todoService;
    }

    @GetMapping("db/init")
    public void mockupDbData() {

        DatabaseSeeder.seed(userService, todoService);
    }

    @GetMapping("todos")
    public ResponseEntity<List<Todo>> getAllTodos() {

        return ResponseEntity.ok(todoService.getAllTodos());
    }

    @GetMapping("todos/udpate")
    public ResponseEntity<Long> getLastTodoUpdatedAt() {

        Optional<Long> maxTodoUpdatedAtOpt = todoService.getLastTodoUpdatedAt();

        if (maxTodoUpdatedAtOpt.isEmpty())
            return ResponseEntity.internalServerError().build();

        return ResponseEntity.ok(maxTodoUpdatedAtOpt.get());
    }

    @PutMapping("todos/{id}/{state}")
    public ResponseEntity<Todo> updateTodoCompleted(
        @PathVariable int id,
        @PathVariable boolean state
    ) {

        Optional<Todo> todoOpt = todoService.getTodoById(id);
        
        if (todoOpt.isEmpty())
            return ResponseEntity.notFound().build();

        Todo todo = todoOpt.get();
        todo.setCompleted(state);

        return ResponseEntity.ok(
            todoService.update(id, todo).get()
        );
    }
}
