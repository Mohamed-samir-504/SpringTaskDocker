package org.example.springtaskdocker.Models;

import jakarta.persistence.*;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rating_id;

    private int number;

    //join column is used to specify column name of foreign key mapped to pk of course
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;
}
