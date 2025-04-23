package org.example.springtaskdocker.Repositories;

import org.example.springtaskdocker.Models.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    public Optional<Course> findFirstByName(String name);
    Page<Course> findAll(Pageable pageable);
}
