package org.example.springtaskdocker.Service;


//import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.example.springtaskdocker.Model.DTO.CourseDTO;
import org.example.springtaskdocker.Mapper.CourseMapper;
import org.example.springtaskdocker.Repository.CourseRepository;
import org.example.springtaskdocker.Model.Entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CourseService {


    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public Page<Course> getCoursesPaginated(Pageable pageable) {

        return courseRepository.findAll(pageable);
    }

    public List<CourseDTO> getRecommendedCourses(){
        List<Course> courses = courseRepository.findAll();

        if (courses.isEmpty()) {
            throw new EntityNotFoundException("Courses not found");
        }
        return courseMapper.toDtoList(courses);
    }

    public CourseDTO getCourseByName(String name){
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Course name must not be blank");
        }
        Course course = courseRepository.findFirstByName(name);
        if (course == null) {
            throw new EntityNotFoundException("Course with name " + name + " not found");
        }
        return courseMapper.toDto(course);

    }
    public Course getCourseById(Long id){
        if (id == null) {
            throw new IllegalArgumentException("Course id must not be blank");
        }
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course with id " + id + " not found"));

    }

    public void addCourse (CourseDTO courseDTO) {
        courseRepository.save(courseMapper.toEntity(courseDTO));
    }

    public void updateCourse (Long id, CourseDTO newCourse) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course with ID " + id + " does not exist"));

        if(newCourse!= null) {
            if (newCourse.getName() != null) {
                course.setName(newCourse.getName());
            }
            if (newCourse.getDescription() != null) {
                course.setDescription(newCourse.getDescription());
            }
            courseRepository.save(course);
        }
    }

    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new EntityNotFoundException("Course with ID " + id + " does not exist");
        }
        courseRepository.deleteById(id);
    }


}
