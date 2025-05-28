package org.example.springtaskdocker.Service;

import generated.CoursesXSD;
import jakarta.persistence.EntityNotFoundException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.example.springtaskdocker.Client.ExternalXSDClient;
import org.example.springtaskdocker.Model.DTO.CourseXSDDTO;
import org.example.springtaskdocker.Mapper.CourseXSDMapper;
import org.springframework.stereotype.Service;

import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.util.List;

@Service
public class ExternalXSDService {


    private final ExternalXSDClient externalXSDClient;

    private final CourseXSDMapper courseXSDMapper;

    public ExternalXSDService(ExternalXSDClient externalXSDClient, CourseXSDMapper courseXSDMapper) {

        this.externalXSDClient = externalXSDClient;
        this.courseXSDMapper = courseXSDMapper;
    }

    public List<CoursesXSD.CourseXSD> fetchAndParse() throws Exception {
        String xml = externalXSDClient.fetchCoursesXml();
        JAXBContext jaxbContext = JAXBContext.newInstance(CoursesXSD.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(xml);
        CoursesXSD courses = (CoursesXSD) unmarshaller.unmarshal(new StreamSource(reader));

        return courses.getCourseXSD();
    }

    public List<CourseXSDDTO> getDiscoveredCourses() throws Exception {
        List<CoursesXSD.CourseXSD> courses = fetchAndParse();

        if (courses == null || courses.isEmpty()) {
            throw new EntityNotFoundException("Courses not found");
        }

        return courseXSDMapper.toDtoList(courses);
    }
}
