package org.example.springtaskdocker.Repositories;

import org.example.springtaskdocker.Models.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

}
