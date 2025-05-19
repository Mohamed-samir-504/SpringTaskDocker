package org.example.springtaskdocker.Service;

import jakarta.persistence.EntityNotFoundException;
import org.example.springtaskdocker.Model.Entity.Course;
import org.example.springtaskdocker.Repository.AuthorRepository;
import org.example.springtaskdocker.Model.Entity.Author;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthorByEmail(String email){

        Author author = authorRepository.findByEmail(email);
        if (author == null) {
            throw new EntityNotFoundException("Author with email " + email + " not found");
        }
        return author;
    }

}
