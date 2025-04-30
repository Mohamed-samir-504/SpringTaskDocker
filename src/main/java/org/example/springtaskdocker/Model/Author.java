package org.example.springtaskdocker.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int author_id;

    private String name;
    private String email;
    private LocalDate birthDate;

    @ManyToMany(mappedBy = "authors")
    private List<Course> courses;

    public Author(int author_id, String name, String email) {
        this.author_id = author_id;
        this.name = name;
        this.email = email;
    }
    public Author() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }


    public void setAuthor_id(int id) {
        this.author_id = id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
