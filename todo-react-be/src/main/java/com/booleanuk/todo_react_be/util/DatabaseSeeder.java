package com.booleanuk.todo_react_be.util;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.booleanuk.todo_react_be.model.Todo;
import com.booleanuk.todo_react_be.model.User;
import com.booleanuk.todo_react_be.service.TodoService;
import com.booleanuk.todo_react_be.service.UserService;

public class DatabaseSeeder {

    private static final int USER_COUNT = 10;
    private static final int TODO_COUNT = 20;

    private static final String[] USERNAMES = {
            "alice", "bob", "charlie", "diana", "edward",
            "fiona", "george", "hannah", "isaac", "julia"
    };

    private static final String[] TITLES = {
            "Buy groceries", "Walk the dog", "Write unit tests", "Review pull request",
            "Book flight tickets", "Renew gym membership", "Call the dentist", "Clean the garage",
            "Read a chapter", "Water the plants", "Pay electricity bill", "Plan the sprint",
            "Refactor the service layer", "Update documentation", "Back up the laptop",
            "Prepare dinner", "Fix the leaking tap", "Answer emails", "Practice guitar",
            "Organise the bookshelf"
    };

    private DatabaseSeeder() { }

    public static void seed(UserService userService, TodoService todoService) {

        Random random = new Random();
        List<User> users = new ArrayList<>();

        for (int i = 0; i < USER_COUNT; i++) {

            User user = new User();
            user.setUsername(USERNAMES[i % USERNAMES.length] + (i / USERNAMES.length > 0 ? i : ""));

            users.add(userService.create(user));
        }

        for (int i = 0; i < TODO_COUNT; i++) {

            Todo todo = new Todo();
            todo.setTitle(TITLES[i % TITLES.length]);
            todo.setCompleted(random.nextBoolean());
            todo.setUser(users.get(random.nextInt(users.size())));

            todoService.create(todo);
        }
    }
}
