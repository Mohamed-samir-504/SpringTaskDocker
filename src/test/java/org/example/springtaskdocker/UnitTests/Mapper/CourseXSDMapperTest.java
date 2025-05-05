package org.example.springtaskdocker.UnitTests.Mapper;

import org.example.springtaskdocker.DTO.CourseXSDDTO;
import org.example.springtaskdocker.Mapper.CourseXSDMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CourseXSDMapperTest {

    private final CourseXSDMapper courseXSDMapper = Mappers.getMapper(CourseXSDMapper.class);

    @Test
    void toDto_validCourse_returnsCorrectDto() {
        AdvancedCourseXSD course = new AdvancedCourseXSD();
        course.setId(1L);
        course.setName("AI");
        course.setDescription("Intro to AI");
        course.setCredit(4);
        course.setPrerequisites("Math");
        course.setLevel("Advanced");

        CourseXSDDTO dto = courseXSDMapper.toDto(course);

        assertEquals("AI", dto.getName());
        assertEquals("Intro to AI", dto.getDescription());
        assertEquals("Math", dto.getPrerequisites());
        assertEquals(4, dto.getCredit());
    }

    @Test
    void toDto_nullCourse_returnsNull() {
        CourseXSDDTO dto = courseXSDMapper.toDto(null);
        assertNull(dto);
    }

    @Test
    void toEntity_validDto_returnsCorrectEntity() {
        CourseXSDDTO dto = new CourseXSDDTO();

        dto.setName("Spring Boot");
        dto.setDescription("Spring Web Development");
        dto.setCredit(3);
        dto.setPrerequisites("Java");

        AdvancedCourseXSD course = courseXSDMapper.toEntity(dto);

        assertEquals("Spring Boot", course.getName());
        assertEquals("Spring Web Development", course.getDescription());
        assertEquals("Java", course.getPrerequisites());
        assertEquals(3, course.getCredit());
    }

    @Test
    void toEntity_nullDto_returnsNull() {
        AdvancedCourseXSD course = courseXSDMapper.toEntity(null);
        assertNull(course);
    }

    @Test
    void toDtoList_validList_returnsCorrectDtoList() {
        AdvancedCourseXSD c1 = new AdvancedCourseXSD();
        c1.setId(1L);
        c1.setName("Java");
        c1.setDescription("Core Java");
        c1.setCredit(3);

        AdvancedCourseXSD c2 = new AdvancedCourseXSD();
        c2.setId(2L);
        c2.setName("ML");
        c2.setDescription("Machine Learning");
        c2.setCredit(4);

        List<CourseXSDDTO> dtoList = courseXSDMapper.toDtoList(List.of(c1, c2));

        assertEquals(2, dtoList.size());

        CourseXSDDTO dto1 = dtoList.get(0);
        assertEquals("Java", dto1.getName());
        assertEquals("Core Java", dto1.getDescription());

        CourseXSDDTO dto2 = dtoList.get(1);
        assertEquals("ML", dto2.getName());
        assertEquals("Machine Learning", dto2.getDescription());
    }

    @Test
    void toDtoList_nullList_returnsNull() {
        List<CourseXSDDTO> dtoList = courseXSDMapper.toDtoList(null);
        assertNull(dtoList);
    }
}
