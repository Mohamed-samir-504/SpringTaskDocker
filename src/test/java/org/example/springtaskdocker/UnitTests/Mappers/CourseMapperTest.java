package org.example.springtaskdocker.UnitTests.Mappers;


import org.example.springtaskdocker.Mappers.CourseMapper;
import org.mapstruct.factory.Mappers;
import org.example.springtaskdocker.DTO.CourseDTO;
import org.example.springtaskdocker.Models.Course;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;




public class CourseMapperTest {

    private final CourseMapper courseMapper = Mappers.getMapper(CourseMapper.class);;

    @Test
    void toDto_validCourse_returnsCorrectDto() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Java");
        course.setDescription("Learn Java");

        CourseDTO dto = courseMapper.toDto(course);

        assertEquals(course.getName(), dto.getName());
        assertEquals(course.getDescription(), dto.getDescription());

    }

    @Test
    void toDto_emptyCourse_returnsNull() {
        Course course = null;

        CourseDTO dto = courseMapper.toDto(course);

        assertNull(dto);


    }

    @Test
    void toEntity_validCourse_returnsCorrectEntity() {
        CourseDTO dto = new CourseDTO("Spring Boot","REST APIs");

        Course course = courseMapper.toEntity(dto);

        assertEquals(dto.getName(), course.getName());
        assertEquals(dto.getDescription(), course.getDescription());

    }

    @Test
    void toEntity_emptyCourse_returnsNull() {
        CourseDTO dto = null;

        Course course = courseMapper.toEntity(dto);

        assertNull(course);

    }

    @Test
    void toDtoList_validList_returnsCorrectDtoList() {
        Course c1 = new Course(1L, "Java", "Learn Java");
        Course c2 = new Course(2L, "Spring", "Spring Boot");
        Course c3 = new Course(3L, "C++", "C++ basics");

        List<CourseDTO> dtoList = courseMapper.toDtoList(List.of(c1, c2, c3));

        assertEquals(3, dtoList.size());

        CourseDTO dto1 = dtoList.get(0);
        assertEquals("Java", dto1.getName());
        assertEquals("Learn Java", dto1.getDescription());
        

        CourseDTO dto2 = dtoList.get(1);
        assertEquals("Spring", dto2.getName());
        assertEquals("Spring Boot", dto2.getDescription());

        CourseDTO dto3 = dtoList.get(2);
        assertEquals("C++", dto3.getName());
        assertEquals("C++ basics", dto3.getDescription());
        

    }

    @Test
    void toDtoList_emptyList_returnsNull(){

        List<Course> courseList = null;
        List<CourseDTO> dtoList = courseMapper.toDtoList(courseList);
        assertNull(dtoList);
    }

}
