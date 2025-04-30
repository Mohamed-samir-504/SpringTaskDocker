package org.example.springtaskdocker.UnitTests.Model;
import static org.junit.jupiter.api.Assertions.*;

import generated.CourseXSD;
import org.junit.jupiter.api.Test;

class CourseXSDTest {

    @Test
    void gettersAndSetters_shouldWorkCorrectly() {
        CourseXSD course = new CourseXSD();

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
