package org.example.springtaskdocker.UnitTests.Models;

import org.example.springtaskdocker.Models.Course;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CourseModelTest {

    private Course course;

    @BeforeEach
    void setUp() {
        course = new Course();
    }
    @Test
    void gettersAndSetters_shouldWorkCorrectly() {

        Long expectedId = 1L;
        String expectedName = "Java";
        String expectedDescription = "Java basics";

        course.setId(expectedId);
        course.setName(expectedName);
        course.setDescription(expectedDescription);

        assertEquals(expectedId, course.getId());
        assertEquals(expectedName, course.getName());
        assertEquals(expectedDescription, course.getDescription());

    }

    @Test
    void toString_shouldReturnExpectedString() {
        course.setId(1L);
        course.setName("Java");
        course.setDescription("Intro to Java");


        String expected = "Course id: " + 1 + "\nCourse name: " + "Java" +
                "\nCourse description: " + "Intro to Java";
        assertEquals(expected, course.toString());
    }

    @Test
    void defaultConstructor_shouldInitializeEmptyObject() {
        assertNull(course.getId());
        assertNull(course.getName());
        assertNull(course.getDescription());

    }
}
