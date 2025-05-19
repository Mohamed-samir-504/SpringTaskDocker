package org.example.springtaskdocker.Repository;

import org.example.springtaskdocker.Model.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    public Optional<Author> findByEmail(String email);
}
