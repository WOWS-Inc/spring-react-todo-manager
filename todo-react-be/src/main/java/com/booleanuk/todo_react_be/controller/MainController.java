package com.booleanuk.todo_react_be.controller;

import com.booleanuk.todo_react_be.repo.UserRepo;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booleanuk.todo_react_be.model.Todo;
import com.booleanuk.todo_react_be.model.User;
import com.booleanuk.todo_react_be.service.TodoService;
import com.booleanuk.todo_react_be.service.UserService;
import com.booleanuk.todo_react_be.util.DatabaseSeeder;

@RestController
@RequestMapping("api")
@CrossOrigin("http://localhost:5173/")
public class MainController {

    private final UserRepo userRepo;
    private UserService userService;
    private TodoService todoService;

    public MainController(UserService userService, TodoService todoService, UserRepo userRepo) {

        this.userService = userService;
        this.todoService = todoService;
        this.userRepo = userRepo;
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

    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUsers() {

        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {

        Optional<User> userOpt = userService.getUserById(id);

        if (userOpt.isEmpty()) 
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(userOpt.get());
    }

    @GetMapping("users/{id}/todos")
    public ResponseEntity<List<Todo>> getTodoListByUserId(
        @PathVariable int id
    ) {
        Optional<User> userOpt = userService.getUserById(id);

        if (userOpt.isEmpty())
            return ResponseEntity.notFound().build();

        List<Todo> userTodo = todoService.getTodoListByUser(userOpt.get());

        return ResponseEntity.ok(userTodo);
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
