package org.example.springtaskdocker.DTO;

import generated.CourseXSD;
import generated.AdvancedCourseXSD;
import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "courses", namespace = "http://example.org/course")
@XmlAccessorType(XmlAccessType.FIELD)
public class CourseXSDListWrapper {

    @XmlElements({
            @XmlElement(name = "course", type = AdvancedCourseXSD.class, namespace = "http://example.org/course"),
            @XmlElement(name = "advancedCourse", type = AdvancedCourseXSD.class, namespace = "http://example.org/course")
    })
    private List<AdvancedCourseXSD> courses = new ArrayList<>();

    public List<AdvancedCourseXSD> getCourses() {
        return courses;
    }

    public void setCourses(List<AdvancedCourseXSD> courses) {
        this.courses = courses;
    }
}
