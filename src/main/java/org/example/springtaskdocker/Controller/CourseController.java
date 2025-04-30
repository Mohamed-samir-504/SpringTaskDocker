package org.example.springtaskdocker.Controller;

import generated.AdvancedCourseXSD;
import org.example.springtaskdocker.DTO.CourseDTO;
import org.example.springtaskdocker.DTO.CourseXSDDTO;
import org.example.springtaskdocker.Mapper.CourseMapper;
import org.example.springtaskdocker.Mapper.CourseXSDMapper;
import org.example.springtaskdocker.Model.Course;
import org.example.springtaskdocker.Service.CourseService;
import org.example.springtaskdocker.Service.ExternalXSDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {

    //It will use SoftwareCourseRecommender bean
    CourseService courseService;
    ExternalXSDService externalXSDService;

    CourseMapper courseMapper;
    CourseXSDMapper courseXSDMapper;

    public CourseController(CourseService courseService, ExternalXSDService externalXSDService,
                            CourseMapper courseMapper, CourseXSDMapper courseXSDMapper) {
        this.courseService = courseService;
        this.externalXSDService = externalXSDService;
        this.courseMapper = courseMapper;
        this.courseXSDMapper = courseXSDMapper;

    }


    //shows one course by its name
    //Using parameter request
    @GetMapping("/view")
    public ResponseEntity<CourseDTO> viewCourse(@RequestParam String name) {

        return courseService.getCourseByName(name)
                .map(courseMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    //Shows all courses
    @GetMapping("/view/all")
    public ResponseEntity<List<CourseDTO>> viewAllCourses() {
        List<Course> courses = courseService.getRecommendedCourses();
        return ResponseEntity.ok(courseMapper.toDtoList(courses));

    }

    //show paginated response
    @GetMapping("/view/pages")
    public ResponseEntity<Page<Course>> getCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Course> courses = courseService.getCoursesPaginated(pageable);
        return ResponseEntity.ok(courses);
    }


    // Shows the form to add a course
    @GetMapping("/add")
    public RedirectView showAddForm() {
        return new RedirectView("/Add.html");
    }


    // Handles the form submission using model attribute
    @PostMapping("/add-submit")
    public ResponseEntity<String> submitCourse(@ModelAttribute CourseDTO course) {
        courseService.addCourse(courseMapper.toEntity(course));
        return ResponseEntity.ok("Course added successfully.");
    }

    //using request body and path variable
    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateCourse(@RequestBody Course course, @PathVariable Long id) {
        Optional<Course> originalCourse = courseService.getCourseById(id);
        courseService.updateCourse(originalCourse.get(),course);
        return ResponseEntity.ok("Course updated successfully.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully");
    }



    @GetMapping("/discover")
    public ResponseEntity<List<CourseXSDDTO>> getCourseXSDList() throws Exception {
        List<AdvancedCourseXSD> advCourses = externalXSDService.getDiscoveredCourses();
        return ResponseEntity.ok(courseXSDMapper.toDtoList(advCourses));
    }
}
