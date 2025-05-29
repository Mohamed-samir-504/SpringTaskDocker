package org.example.springtaskdocker.Controller;

import org.example.springtaskdocker.Common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
public class DummyTodosController {

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/api/dummy-todos")
    public ResponseEntity<ApiResponse<Object>> getDummyTodos() {
        List<Map<String, Object>> todos = new ArrayList<>();

        Map<String, Object> todo1 = new HashMap<>();
        todo1.put("id", 1);
        todo1.put("title", "Learn Spring Boot");
        todo1.put("description", "Learn Spring Boot");
        todo1.put("status", "todo");
        todo1.put("priority", false);

        Map<String, Object> todo2 = new HashMap<>();
        todo2.put("id", 2);
        todo2.put("title", "Build REST API");
        todo2.put("description", "Build REST API");
        todo2.put("status", "todo");
        todo2.put("priority", false);

        Map<String, Object> todo3 = new HashMap<>();
        todo3.put("id", 3);
        todo3.put("title", "Connect frontend");
        todo3.put("description", "Connect frontend");
        todo3.put("status", "todo");
        todo3.put("priority", true);

        todos.add(todo1);
        todos.add(todo2);
        todos.add(todo3);

        ApiResponse<Object> response = new ApiResponse<>(
                "Fetched dummy todo items successfully",
                HttpStatus.OK.value(),
                todos
        );

        return ResponseEntity.ok(response);
    }
}

