package org.example.springtaskdocker.Service;

import generated.AdvancedCourseXSD;
import jakarta.persistence.EntityNotFoundException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import generated.CourseXSDListWrapper;
import org.example.springtaskdocker.Client.ExternalXSDClient;
import org.example.springtaskdocker.DTO.CourseXSDDTO;
import org.example.springtaskdocker.Mapper.CourseXSDMapper;
import org.springframework.stereotype.Service;

import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.util.List;

@Service
public class ExternalXSDService {


    private ExternalXSDClient externalXSDClient;

    private CourseXSDMapper courseXSDMapper;

    public ExternalXSDService(ExternalXSDClient externalXSDClient, CourseXSDMapper courseXSDMapper) {

        this.externalXSDClient = externalXSDClient;
        this.courseXSDMapper = courseXSDMapper;
    }

    public List<AdvancedCourseXSD> fetchAndParse() throws Exception {
        String xml = externalXSDClient.fetchCoursesXml();
        JAXBContext jaxbContext = JAXBContext.newInstance(CourseXSDListWrapper.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(xml);
        CourseXSDListWrapper wrapper = (CourseXSDListWrapper) unmarshaller.unmarshal(new StreamSource(reader));

        return wrapper.getCourses();
    }

    public List<CourseXSDDTO> getDiscoveredCourses() throws Exception {
        List<AdvancedCourseXSD> advCourses = fetchAndParse();

        if (advCourses == null || advCourses.isEmpty()) {
            throw new EntityNotFoundException("Courses not found");
        }

        return courseXSDMapper.toDtoList(advCourses);
    }
}
