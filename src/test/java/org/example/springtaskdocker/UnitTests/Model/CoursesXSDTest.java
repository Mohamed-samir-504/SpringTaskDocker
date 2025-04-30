package org.example.springtaskdocker.UnitTests.Model;

import generated.CourseXSD;
import generated.CoursesXSD;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CoursesXSDTest {

    @Test
    void getCourseOrAdvancedCourse_whenUninitialized_returnsNonNullList() {
        CoursesXSD courses = new CoursesXSD();

        List<CourseXSD> list = courses.getCourseOrAdvancedCourse();

        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    void getCourseOrAdvancedCourse_returnsLiveListReference() {
        CoursesXSD courses = new CoursesXSD();

        List<CourseXSD> list1 = courses.getCourseOrAdvancedCourse();
        CourseXSD course = new CourseXSD();
        course.setName("Java");

        list1.add(course);

        List<CourseXSD> list2 = courses.getCourseOrAdvancedCourse();

        assertEquals(1, list2.size());
        assertSame(list1, list2);
        assertEquals("Java", list2.get(0).getName());
    }
}
