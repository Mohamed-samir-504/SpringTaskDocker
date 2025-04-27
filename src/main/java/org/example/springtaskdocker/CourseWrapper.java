package org.example.springtaskdocker;

import generatedSources.org.example.course.CourseXSD;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "courses", namespace = "http://example.org/course")
@XmlAccessorType(XmlAccessType.FIELD)
public class CourseWrapper {

    @XmlElement(name = "course", namespace = "http://example.org/course")
    private List<CourseXSD> courses = new ArrayList<>();

    public List<CourseXSD> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseXSD> courses) {
        this.courses = courses;
    }
}
