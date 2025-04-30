
package org.example.springtaskdocker.Controller;

import org.example.springtaskdocker.Model.Author;
import org.example.springtaskdocker.Service.AuthorService;
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
    public ResponseEntity<Author> findByEmail(@RequestParam String email){

        return authorService.getAuthorByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }
}
