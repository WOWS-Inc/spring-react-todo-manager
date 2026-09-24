package com.booleanuk.todo_react_be.service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

import org.springframework.stereotype.Service;

import com.booleanuk.todo_react_be.model.Todo;
import com.booleanuk.todo_react_be.model.User;
import com.booleanuk.todo_react_be.repo.TodoRepo;

@Service
public class TodoService {

    private TodoRepo todoRepo;

    public TodoService(TodoRepo todoRepo) {

        this.todoRepo = todoRepo;
    }

    public List<Todo> getAllTodos() {

        return todoRepo.findAll();
    }
    public Optional<Todo> getTodoById(int id) {

        return todoRepo.findById(id);
    }

    public Todo create(Todo todo) {

        return todoRepo.save(todo);
    }

    public Optional<Todo> update(int id, Todo todo) {

        Optional<Todo> todoOpt = getTodoById(id);

        if (todoOpt.isEmpty()) return Optional.empty();

        Todo todoDb = todoOpt.get();
        todoDb.setTitle(todo.getTitle());
        todoDb.setCompleted(todo.getCompleted());

        return Optional.of(todoRepo.save(todoDb));
    }

    public Optional<Long> getLastTodoUpdatedAt() {

        List<Todo> todos = todoRepo.findAll();

        if (todos.isEmpty()) return Optional.empty();

        long maxUpdatedAt = -1;
        for (Todo todo : todos)
            if (todo.getUpdatedAt() > maxUpdatedAt)
                maxUpdatedAt = todo.getUpdatedAt();

        return Optional.of(maxUpdatedAt);
    }

    public List<Todo> getTodoListByUser(User user) {

        return todoRepo.findByUser(user);
    }
}
