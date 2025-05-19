package org.example.springtaskdocker.UnitTests.Controller;

import org.example.springtaskdocker.Controller.AuthorController;
import org.example.springtaskdocker.Model.Entity.Author;
import org.example.springtaskdocker.Config.SecurityConfig;
import org.example.springtaskdocker.Service.AuthorService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AuthorController.class)
@Import(SecurityConfig.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthorService authorService;

    @Test
    void findByEmail_shouldReturnCorrectAuthor() throws Exception {
        Author author = new Author(1,"mtolba","mtolba@sumerge.com");

        when(authorService.getAuthorByEmail("mtolba@sumerge.com")).thenReturn(Optional.of(author));

        mockMvc.perform(get("/author")
                        .with(httpBasic("admin", "admin123"))
                        .header("x-validation-report", "true")
                        .param("email", author.getEmail()))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author_id").value(1))
                .andExpect(jsonPath("$.name").value("mtolba"))
                .andExpect(jsonPath("$.email").value("mtolba@sumerge.com"));
    }

}
