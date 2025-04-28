package org.example.springtaskdocker.UnitTests.Controllers;

import org.example.springtaskdocker.Controllers.CourseController;
import org.example.springtaskdocker.DTO.CourseDTO;
import org.example.springtaskdocker.Mappers.CourseMapper;
import org.example.springtaskdocker.Models.Course;
import org.example.springtaskdocker.SecurityConfig;
import org.example.springtaskdocker.Services.CourseService;
import org.example.springtaskdocker.Services.ExternalXSDService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseController.class)
@Import(SecurityConfig.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CourseService courseService;

    @MockitoBean
    private CourseMapper courseMapper;

    @MockitoBean
    ExternalXSDService externalXSDService;


    @Test
    void viewCourse_shouldReturnCourseDTOByName() throws Exception {
        Course course = new Course(1L, "Spring", "Spring course");
        CourseDTO courseDTO = new CourseDTO("Spring","Spring course");

        when(courseService.getCourseByName("Spring")).thenReturn(Optional.of(course));
        when(courseMapper.toDto(course)).thenReturn(courseDTO);

        mockMvc.perform(get("/view")
                        .param("name", "Spring"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Spring"))
                .andExpect(jsonPath("$.description").value("Spring course"));
    }

    @Test
    void viewAllCourses_shouldReturnAllCourses() throws Exception {
        List<Course> courseList = List.of(
                new Course(1L, "Java", "Java course"),
                new Course(2L, "Spring", "Spring course")
        );

        List<CourseDTO> courseDTOList = List.of(
                new CourseDTO( "Java", "Java course"),
                new CourseDTO("Spring", "Spring course")
        );

        when(courseService.getRecommendedCourses()).thenReturn(courseList);
        when(courseMapper.toDtoList(courseList)).thenReturn(courseDTOList);

        mockMvc.perform(get("/view/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Java"))
                .andExpect(jsonPath("$[0].description").value("Java course"))
                .andExpect(jsonPath("$[1].name").value("Spring"))
                .andExpect(jsonPath("$[1].description").value("Spring course"));

    }

    @Test
    void viewAllCourses_NoCoursesExist_shouldReturnNull() throws Exception {
        List<Course> courseList = List.of();

        List<CourseDTO> courseDTOList = List.of();


        when(courseService.getRecommendedCourses()).thenReturn(courseList);
        when(courseMapper.toDtoList(courseList)).thenReturn(courseDTOList);

        mockMvc.perform(get("/view/all"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

    }

    @Test
    void getCoursesPaginated_shouldReturnPagesOfCourses() throws Exception {
        Pageable pageable = PageRequest.of(0, 3);
        List<Course> mockCourses = List.of(
                new Course(1L, "Java", "Java course"),
                new Course(2L, "Spring", "Spring course"),
                new Course(3L, "C++", "C++ course")
        );
        Page<Course> mockPage = new PageImpl<>(mockCourses, pageable, mockCourses.size());

        when(courseService.getCoursesPaginated(pageable)).thenReturn(mockPage);

        mockMvc.perform(get("/view/pages")
                        .param("page", "0").param("size", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pageable.pageSize").value(3))
                .andExpect(jsonPath("$.pageable.pageNumber").value(0))
                .andExpect(jsonPath("$.content[0].name").value("Java"))
                .andExpect(jsonPath("$.content[0].description").value("Java course"))
                .andExpect(jsonPath("$.content[2].description").value("C++ course"));

    }

    @Test
    void showAddForm_shouldRedirectToAddHtml() throws Exception {
        mockMvc.perform(get("/add").with(httpBasic("admin", "admin123")))
                .andExpect(status().is3xxRedirection()) // redirect
                .andExpect(redirectedUrl("/Add.html")); // verify the target
    }

    @Test
    void submitCourse_shouldReturnSuccessMessage() throws Exception {

        Course course = new Course(1L, "Java", "Java Course");

        when(courseMapper.toEntity(any(CourseDTO.class))).thenReturn(course);

        mockMvc.perform(post("/add-submit").with(httpBasic("admin", "admin123"))
                        .param("name", "Java")
                        .param("description", "Java Course"))
                .andExpect(status().isOk())
                .andExpect(content().string("Course added successfully."));

        verify(courseService).addCourse(course);
    }

    @Test
    void deleteCourse_shouldReturnSuccessMessage() throws Exception {
        Long courseId = 1L;

        mockMvc.perform(delete("/delete/{id}", courseId)
                        .header("x-validation-report", "true")
                        .with(httpBasic("admin", "admin123")))
                        .andExpect(status().isOk())
                        .andExpect(content().string("Course deleted successfully"));

        verify(courseService).deleteCourse(courseId);
    }

    @Test
    void updateCourse_CourseExists_shouldReturnSuccessMessage() throws Exception {
        Course originalCourse = new Course(1L, "Java", "Java Course");
        Course updatedCourse = new Course(1L, "Spring", "Spring course");

        String json = String.format("""
            {
                "name": "%s",
                "description": "%s"
            }
            """, updatedCourse.getName(), updatedCourse.getDescription());

        when(courseService.getCourseById(originalCourse.getId())).thenReturn(Optional.of(originalCourse));

        mockMvc.perform(patch("/update/{id}", originalCourse.getId())
                        .header("x-validation-report", "true")
                        .with(httpBasic("admin", "admin123"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Course updated successfully."));

    }

    @Test
    void updateCourse_CourseDoesNotExist_shouldReturnNotFound() throws Exception {
        Long id = 100L;

        when(courseService.getCourseById(id)).thenReturn(Optional.empty());

        mockMvc.perform(patch("/update/{id}", id)
                        .header("x-validation-report", "true")
                        .with(httpBasic("admin", "admin123"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "name": "Advanced Java",
                              "description": "Updated"
                            }
                        """))
                .andExpect(status().isNotFound());
    }

}
