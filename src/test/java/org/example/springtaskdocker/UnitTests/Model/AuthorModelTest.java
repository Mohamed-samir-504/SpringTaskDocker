package org.example.springtaskdocker.UnitTests.Model;

import org.example.springtaskdocker.Model.Entity.Author;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorModelTest {

    @Test
    void gettersAndSetters_shouldWorkCorrectly() {
        Author author = new Author();

        int expectedId = 1;
        String expectedName = "mtolba";
        LocalDate expectedBirthDate = LocalDate.of(2002, 1, 18);
        String expectedEmail = "mtolba@sumerge.com";

        author.setAuthor_id(expectedId);
        author.setName(expectedName);
        author.setBirthDate(expectedBirthDate);
        author.setEmail(expectedEmail);

        assertEquals(expectedId, author.getAuthor_id());
        assertEquals(expectedName, author.getName());
        assertEquals(expectedBirthDate, author.getBirthDate());
        assertEquals(expectedEmail, author.getEmail());
    }
}
