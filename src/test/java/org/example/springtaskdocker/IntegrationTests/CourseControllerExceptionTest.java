package org.example.springtaskdocker.IntegrationTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerExceptionTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturn400WhenCourseNameIsBlank() throws Exception {
        mockMvc.perform(get("/courses").with(httpBasic("admin", "admin123"))
                        .header("x-validation-report", "true").param("name", "  "))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Course name must not be blank"));
    }

    @Test
    void shouldReturn404WhenCourseNotFound() throws Exception {
        String name = "A course that does not exist";
        mockMvc.perform(get("/courses").with(httpBasic("admin", "admin123"))
                        .header("x-validation-report", "true").param("name", name))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Course with name " + name + " not found"));
    }
}
