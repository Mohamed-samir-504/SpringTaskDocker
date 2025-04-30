package org.example.springtaskdocker.IntegrationTests;

import org.example.springtaskdocker.Repository.CourseRepository;
import org.example.springtaskdocker.Service.CourseService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.example.springtaskdocker.Model.Course;


import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CourseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepository courseRepository;


    @Test
    void shouldReturnCourseDTO_whenCourseExistsByName() throws Exception {

        Course course = new Course();
        course.setName("Testing");
        course.setDescription("Testing course");
        courseService.addCourse(course);

        mockMvc.perform(get("/courses").with(httpBasic("admin", "admin123"))
                        .header("x-validation-report", "true")
                        .param("name", "Testing"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Testing"))
                .andExpect(jsonPath("$.description").value("Testing course"));

        Optional<Course> addedCourse = courseService.getCourseByName("Testing");

        courseService.deleteCourse(addedCourse.get().getId());
    }
}