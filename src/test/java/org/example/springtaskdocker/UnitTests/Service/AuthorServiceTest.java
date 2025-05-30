package org.example.springtaskdocker.UnitTests.Service;

import jakarta.persistence.EntityNotFoundException;
import org.example.springtaskdocker.Model.Entity.Author;
import org.example.springtaskdocker.Repository.AuthorRepository;
import org.example.springtaskdocker.Service.AuthorService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void getAuthorByEmail_authorExists_shouldReturnCorrectAuthor() {
        Author mockAuthor = new Author(1,"mtolba","mtolba@sumerge.com");

        when(authorRepository.findByEmail(mockAuthor.getEmail())).thenReturn(mockAuthor);

        Author result = authorService.getAuthorByEmail("mtolba@sumerge.com");

        assertEquals(1, result.getAuthor_id());
        assertEquals("mtolba", result.getName());
        assertEquals("mtolba@sumerge.com", result.getEmail());

    }

    @Test
    void getAuthorByEmail_authorDoesNotExist_shouldThrowEntityNotFoundException() {
        when(authorRepository.findByEmail("missing@example.com")).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () ->
                authorService.getAuthorByEmail("missing@example.com")
        );
    }
}

