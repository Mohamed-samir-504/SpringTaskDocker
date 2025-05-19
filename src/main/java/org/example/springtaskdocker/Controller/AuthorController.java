
package org.example.springtaskdocker.Controller;

import org.example.springtaskdocker.Common.ApiResponse;
import org.example.springtaskdocker.Model.DTO.CourseDTO;
import org.example.springtaskdocker.Model.Entity.Author;
import org.example.springtaskdocker.Service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {
    AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author")
    public ResponseEntity<ApiResponse<Author>> findByEmail(@RequestParam String email){

        return ResponseEntity.ok(new ApiResponse<>(
                "Author retrieved successfully",
                HttpStatus.OK.value(),
                authorService.getAuthorByEmail(email))
        );

    }
}
