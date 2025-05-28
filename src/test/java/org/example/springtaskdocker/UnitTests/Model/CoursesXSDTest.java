package org.example.springtaskdocker.UnitTests.Model;

import generated.CoursesXSD;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CoursesXSDTest {

    @Test
    void getCourse_whenUninitialized_returnsNonNullList() {
        CoursesXSD courses = new CoursesXSD();

        List<CoursesXSD.CourseXSD> list = courses.getCourseXSD();

        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    void getCourse_returnsLiveListReference() {
        CoursesXSD courses = new CoursesXSD();

        List<CoursesXSD.CourseXSD> list1 = courses.getCourseXSD();
        CoursesXSD.CourseXSD course = new CoursesXSD.CourseXSD();
        course.setName("Java");

        list1.add(course);

        List<CoursesXSD.CourseXSD> list2 = courses.getCourseXSD();

        assertEquals(1, list2.size());
        assertSame(list1, list2);
        assertEquals("Java", list2.get(0).getName());
    }
}
