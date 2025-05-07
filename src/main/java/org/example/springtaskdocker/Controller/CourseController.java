package org.example.springtaskdocker.Controller;


import org.example.springtaskdocker.DTO.CourseDTO;
import org.example.springtaskdocker.DTO.CourseXSDDTO;
import org.example.springtaskdocker.Model.Course;
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

    //It will use SoftwareCourseRecommender bean
    CourseService courseService;
    ExternalXSDService externalXSDService;


    public CourseController(CourseService courseService, ExternalXSDService externalXSDService) {
        this.courseService = courseService;
        this.externalXSDService = externalXSDService;

    }


    //shows one course by its name
    //Using parameter request
    @GetMapping("/courses")
    public ResponseEntity<Map<String, Object>> viewCourse(@RequestParam String name) {

        return ResponseEntity.ok(Map.of(
                "timestamp", LocalDateTime.now(),
                "message", "Courses retrieved successfully",
                "status", HttpStatus.OK.value(),
                "data", courseService.getCourseByName(name)
        ));


    }

    //Shows all courses
    @GetMapping("/courses/all")
    public ResponseEntity<Map<String, Object>> viewAllCourses() {
        return ResponseEntity.ok(Map.of(
                "timestamp", LocalDateTime.now(),
                "message", "Courses retrieved successfully",
                "status", HttpStatus.OK.value(),
                "data", courseService.getRecommendedCourses()
        ));

    }

    //show paginated response
    @GetMapping("/courses/pages")
    public ResponseEntity<Page<Course>> getCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Course> courses = courseService.getCoursesPaginated(pageable);
        return ResponseEntity.ok(courses);
    }


    // Shows the form to add a course
    @GetMapping("/form")
    public RedirectView showAddForm() {
        return new RedirectView("/Add.html");
    }


    // Handles the form submission using model attribute
    @PostMapping("/new-course")
    public ResponseEntity<Map<String, Object>> submitCourse(@ModelAttribute CourseDTO courseDTO) {
        courseService.addCourse(courseDTO);
        return ResponseEntity.ok(Map.of(
                "message", "Course updated successfully",
                "timestamp", LocalDateTime.now()
        ));
    }

    //using request body and path variable
    @PatchMapping("/courses/{id}")
    public ResponseEntity<Map<String, Object>> updateCourse(@RequestBody Course course, @PathVariable Long id) {
        Course originalCourse = courseService.getCourseById(id);
        courseService.updateCourse(originalCourse,course);
        return ResponseEntity.ok(Map.of(
                "message", "Course updated successfully",
                "timestamp", LocalDateTime.now()
        ));
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Map<String, Object>> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok(Map.of(
                "message", "Course deleted successfully",
                "timestamp", LocalDateTime.now()
        ));
    }



    @GetMapping("/coursesXSD")
    public ResponseEntity<Map<String, Object>> getCourseXSDList() throws Exception {
        return ResponseEntity.ok(Map.of(
                "timestamp", LocalDateTime.now(),
                "message", "Courses retrieved successfully",
                "status", HttpStatus.OK.value(),
                "data", externalXSDService.getDiscoveredCourses()
        ));
    }
}
