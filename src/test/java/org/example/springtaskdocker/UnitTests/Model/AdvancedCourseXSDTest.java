package org.example.springtaskdocker.UnitTests.Model;

import generated.AdvancedCourseXSD;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdvancedCourseXSDTest {

    @Test
    void gettersAndSetters_shouldWorkCorrectly() {
        AdvancedCourseXSD course = new AdvancedCourseXSD();

        course.setId(10L);
        course.setName("AI");
        course.setDescription("Artificial Intelligence");
        course.setCredit(4);
        course.setPrerequisites("Math, Logic");
        course.setLevel("Advanced");

        assertEquals(10L, course.getId());
        assertEquals("AI", course.getName());
        assertEquals("Artificial Intelligence", course.getDescription());
        assertEquals(4, course.getCredit());
        assertEquals("Math, Logic", course.getPrerequisites());
        assertEquals("Advanced", course.getLevel());
    }
}
