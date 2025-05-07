package org.example.springtaskdocker.UnitTests.Model;

import generated.CoursesXSD;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseXSDTest {

    @Test
    void gettersAndSetters_shouldWorkCorrectly() {
        CoursesXSD.CourseXSD course = new CoursesXSD.CourseXSD();

        long expectedId = 1L;
        String expectedName = "Java";
        String expectedDescription = "Java basics";
        int expectedCredit = 3;

        course.setId(expectedId);
        course.setName(expectedName);
        course.setDescription(expectedDescription);
        course.setCredit(expectedCredit);

        assertEquals(expectedId, course.getId());
        assertEquals(expectedName, course.getName());
        assertEquals(expectedDescription, course.getDescription());
        assertEquals(expectedCredit, course.getCredit());
    }
}
