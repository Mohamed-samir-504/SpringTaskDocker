package org.example.springtaskdocker.Controller;


import org.example.springtaskdocker.Common.ApiResponse;
import org.example.springtaskdocker.Model.DTO.CourseDTO;
import org.example.springtaskdocker.Model.DTO.CourseXSDDTO;
import org.example.springtaskdocker.Model.Entity.Course;
import org.example.springtaskdocker.Service.CourseService;
import org.example.springtaskdocker.Service.ExternalXSDService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
public class CourseController {


    private final CourseService courseService;
    private final ExternalXSDService externalXSDService;


    public CourseController(CourseService courseService, ExternalXSDService externalXSDService) {
        this.courseService = courseService;
        this.externalXSDService = externalXSDService;

    }


    //shows one course by its name
    //Using parameter request
    @GetMapping("/courses")
    public ResponseEntity<ApiResponse<CourseDTO>> viewCourse(@RequestParam String name) {

        return ResponseEntity.ok(new ApiResponse<>(
                        "Course retrieved successfully",
                        200,
                        courseService.getCourseByName(name))
        );

    }

    //Shows all courses
    @GetMapping("/courses/all")
    public ResponseEntity<ApiResponse<List<CourseDTO>>> viewAllCourses() {
        return ResponseEntity.ok(new ApiResponse<>(
                "Courses retrieved successfully",
                HttpStatus.OK.value(),
                courseService.getRecommendedCourses())
        );
    }

    //show paginated response
    @GetMapping("/courses/pages")
    public ResponseEntity<ApiResponse<Page<Course>>> getCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(new ApiResponse<>(
                "Courses retrieved successfully",
                HttpStatus.OK.value(),
                courseService.getCoursesPaginated(pageable))
        );
    }


    // Shows the form to add a course
    @GetMapping("/form")
    public RedirectView showAddForm() {
        return new RedirectView("/Add.html");
    }


    // Handles the form submission using model attribute
    @PostMapping("/new-course")
    public ResponseEntity<ApiResponse<Object>> submitCourse(@ModelAttribute CourseDTO courseDTO) {
        courseService.addCourse(courseDTO);
        return ResponseEntity.ok(new ApiResponse<>(
                "Course added successfully",
                HttpStatus.OK.value())
        );
    }

    //using request body and path variable
    @PatchMapping("/courses/{id}")
    public ResponseEntity<ApiResponse<Object>> updateCourse(@RequestBody Course course, @PathVariable Long id) {
        Course originalCourse = courseService.getCourseById(id);
        courseService.updateCourse(originalCourse,course);
        return ResponseEntity.ok(new ApiResponse<>(
                "Course updated successfully",
                HttpStatus.OK.value())
        );
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok(new ApiResponse<>(
                "Course deleted successfully",
                HttpStatus.OK.value())
        );
    }


    @GetMapping("/coursesXSD")
    public ResponseEntity<ApiResponse<List<CourseXSDDTO>>> getCourseXSDList() throws Exception {
        return ResponseEntity.ok(new ApiResponse<>(
                "Courses retrieved successfully",
                HttpStatus.OK.value(),
                externalXSDService.getDiscoveredCourses())
        );
    }
}
