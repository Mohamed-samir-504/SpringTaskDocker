package org.example.springtaskdocker.Controller;


import org.example.springtaskdocker.DTO.CourseDTO;
import org.example.springtaskdocker.DTO.CourseXSDDTO;
import org.example.springtaskdocker.Model.Course;
import org.example.springtaskdocker.Service.CourseService;
import org.example.springtaskdocker.Service.ExternalXSDService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

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
    public ResponseEntity<CourseDTO> viewCourse(@RequestParam String name) {

        return ResponseEntity.ok(courseService.getCourseByName(name));


    }

    //Shows all courses
    @GetMapping("/courses/all")
    public ResponseEntity<List<CourseDTO>> viewAllCourses() {
        return ResponseEntity.ok(courseService.getRecommendedCourses());

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
    public ResponseEntity<String> submitCourse(@ModelAttribute CourseDTO courseDTO) {
        courseService.addCourse(courseDTO);
        return ResponseEntity.ok("Course added successfully.");
    }

    //using request body and path variable
    @PatchMapping("/courses/{id}")
    public ResponseEntity<String> updateCourse(@RequestBody Course course, @PathVariable Long id) {
        Course originalCourse = courseService.getCourseById(id);
        courseService.updateCourse(originalCourse,course);
        return ResponseEntity.ok("Course updated successfully.");
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully");
    }



    @GetMapping("/coursesXSD")
    public ResponseEntity<List<CourseXSDDTO>> getCourseXSDList() throws Exception {
        return ResponseEntity.ok(externalXSDService.getDiscoveredCourses());
    }
}
