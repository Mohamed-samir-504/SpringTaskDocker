package org.example.springtaskdocker.UnitTests.Service;

import jakarta.persistence.EntityNotFoundException;
import org.example.springtaskdocker.DTO.CourseDTO;
import org.example.springtaskdocker.Mapper.CourseMapper;
import org.example.springtaskdocker.Model.Course;
import org.example.springtaskdocker.Repository.CourseRepository;
import org.example.springtaskdocker.Service.CourseService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseMapper courseMapper;

    @InjectMocks
    private CourseService courseService;



    @Test
    void getCoursesPaginated_shouldReturnPageOfCourses() {

        Pageable pageable = PageRequest.of(0, 2);
        List<Course> mockCourses = List.of(
                new Course(1L, "Java", "Java course"),
                new Course(2L, "Spring", "Spring course")
        );
        Page<Course> mockPage = new PageImpl<>(mockCourses, pageable, mockCourses.size());

        when(courseRepository.findAll(pageable)).thenReturn(mockPage);
        Page<Course> result = courseService.getCoursesPaginated(pageable);

        assertEquals(2, result.getContent().size());
        assertEquals("Java", result.getContent().get(0).getName());
        assertEquals("Spring", result.getContent().get(1).getName());

    }

    @Test
    void getRecommendedCourses_shouldReturnAllCourses() {

        List<Course> mockCourses = List.of(
                new Course(1L, "Java", "Intro to Java"),
                new Course(2L, "Spring", "Spring course")
        );

        List<CourseDTO> mockDTOs = List.of(
                new CourseDTO( "Java", "Intro to Java"),
                new CourseDTO( "Spring", "Spring course")
        );

        when(courseRepository.findAll()).thenReturn(mockCourses);
        when(courseMapper.toDtoList(mockCourses)).thenReturn(mockDTOs);

        List<CourseDTO> result = courseService.getRecommendedCourses();

        assertEquals(2, result.size());
        assertEquals("Java", result.get(0).getName());
        assertEquals("Spring", result.get(1).getName());

    }

    @Test
    void getCourseByName_courseExists_shouldReturnCorrectCourse() {
        Course mockCourse = new Course(1L, "Java", "Java course");
        CourseDTO mockDTO = new CourseDTO( "Java", "Java course");

        when(courseRepository.findFirstByName(mockCourse.getName())).thenReturn(mockCourse);
        when(courseMapper.toDto(mockCourse)).thenReturn(mockDTO);

        CourseDTO result = courseService.getCourseByName("Java");

        assertEquals("Java", result.getName());
        assertEquals("Java course", result.getDescription());
    }

    @Test
    void getCourseByName_NameIsNull_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            courseService.getCourseByName(null);
        });
    }

    @Test
    void getCourseByName_CourseDoesNotExist_shouldThrowEntityNotFoundException() {
        when(courseRepository.findFirstByName("C++")).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () ->
                courseService.getCourseByName("C++")
        );
    }



    @Test
    void getCourseById_courseExists_shouldReturnCorrectCourse() {
        Course mockCourse = new Course(1L, "Java", "Java course");
        when(courseRepository.findById(mockCourse.getId())).thenReturn(Optional.of(mockCourse));
        Course result = courseService.getCourseById(1L);
        assertEquals(1, result.getId());
    }

    @Test
    void getCourseById_IdIsNull_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            courseService.getCourseById(null);
        });
    }

    @Test
    void getCourseById_CourseDoesNotExist_shouldThrowEntityNotFoundException() {
        when(courseRepository.findById(25L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                courseService.getCourseById(25L)
        );
    }

    @Test
    void addCourse_shouldCallSaveMethod() {

        Course mockCourse = new Course(1L, "Java", "Java course");
        CourseDTO mockDTO = new CourseDTO( "Java", "Java course");
        when(courseMapper.toEntity(mockDTO)).thenReturn(mockCourse);
        courseService.addCourse(mockDTO);
        verify(courseRepository).save(mockCourse);
    }


    @Test
    void deleteCourse_CourseExists_shouldCallDeleteByIdMethod() {
        Long courseId = 1L;
        when(courseRepository.existsById(courseId)).thenReturn(true);
        courseService.deleteCourse(courseId);
        verify(courseRepository).deleteById(courseId);
    }

    @Test
    void deleteCourse_CourseDoesNotExists_shouldReturnEntityNotFoundException() {
        Long courseId = 1L;
        when(courseRepository.existsById(courseId)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () ->
                courseService.deleteCourse(courseId)
        );
    }

    @Test
    void updateCourse_nameAndDescriptionExists_shouldCallSaveMethod() {
        Course oldMockCourse = new Course(1L, "Java", "Java course");
        Course newMockCourse = new Course(null,"Spring", "Spring course");

        when(courseRepository.existsById(oldMockCourse.getId())).thenReturn(true);
        courseService.updateCourse(oldMockCourse, newMockCourse);

        verify(courseRepository).save(oldMockCourse);
        assertEquals("Spring", oldMockCourse.getName());
        assertEquals("Spring course", oldMockCourse.getDescription());

    }

    @Test
    void updateCourse_onlyNameExists_shouldCallSaveMethod() {
        Course oldMockCourse = new Course(1L, "Java", "Java course");
        Course newMockCourse = new Course(null,"Spring", null);
        when(courseRepository.existsById(oldMockCourse.getId())).thenReturn(true);
        courseService.updateCourse(oldMockCourse, newMockCourse);
        verify(courseRepository).save(oldMockCourse);
        assertEquals("Spring", oldMockCourse.getName());
        assertEquals("Java course", oldMockCourse.getDescription());
    }

    @Test
    void updateCourse_onlyDescriptionExists_shouldCallSaveMethod(){

        Course oldMockCourse = new Course(1L, "Java", "Java course");
        Course newMockCourse = new Course(null,null, "Intro to java");
        when(courseRepository.existsById(oldMockCourse.getId())).thenReturn(true);
        courseService.updateCourse(oldMockCourse, newMockCourse);
        verify(courseRepository).save(oldMockCourse);
        assertEquals("Java", oldMockCourse.getName());
        assertEquals("Intro to java", oldMockCourse.getDescription());

    }

    @Test
    void updateCourse_valuesAreNull_shouldNotUpdate() {
        Course oldMockCourse = new Course(1L, "Java", "Java course");
        Course newMockCourse = new Course();
        when(courseRepository.existsById(oldMockCourse.getId())).thenReturn(true);
        courseService.updateCourse(oldMockCourse, newMockCourse);

        verify(courseRepository).save(oldMockCourse);
        assertEquals("Java", oldMockCourse.getName());
        assertEquals("Java course", oldMockCourse.getDescription());

    }

    @Test
    void updateCourse_newCourseIsNull_shouldNotCallSaveMethod() {
        Course oldMockCourse = new Course(1L, "Java", "Java course");
        Course newMockCourse = null;
        when(courseRepository.existsById(oldMockCourse.getId())).thenReturn(true);
        courseService.updateCourse(oldMockCourse, newMockCourse);
        verify(courseRepository, never()).save(any());

    }

    @Test
    void updateCourse_oldCourseDoesNotExist_shouldNotCallSaveMethod() {
        Course oldMockCourse = new Course(13L, "Java", "Java course");
        Course newMockCourse = new Course(null, "Spring", "Spring course");
        when(courseRepository.existsById(oldMockCourse.getId())).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () ->
                courseService.updateCourse(oldMockCourse,newMockCourse)
        );

    }

}
