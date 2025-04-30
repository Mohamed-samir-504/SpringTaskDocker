package org.example.springtaskdocker.Services;

import generated.AdvancedCourseXSD;
import generated.CourseXSD;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.example.springtaskdocker.DTO.CourseXSDListWrapper;
import org.example.springtaskdocker.DTO.CourseXSDDTO;
import org.example.springtaskdocker.FeignClients.ExternalXSDClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.util.List;

@Service
public class ExternalXSDService {

    @Autowired
    private ExternalXSDClient externalXSDClient;

    public ExternalXSDService(ExternalXSDClient externalXSDClient) {

        this.externalXSDClient = externalXSDClient;
    }

    public List<AdvancedCourseXSD> fetchCoursesFromMock() throws Exception {
        String xml = externalXSDClient.fetchCoursesXml();
        JAXBContext jaxbContext = JAXBContext.newInstance(CourseXSDListWrapper.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(xml);
        CourseXSDListWrapper wrapper = (CourseXSDListWrapper) unmarshaller.unmarshal(new StreamSource(reader));

        return wrapper.getCourses();
    }

    public CourseXSDDTO mapToDto(AdvancedCourseXSD advCourseXSD) {
        CourseXSDDTO dto = new CourseXSDDTO();
        dto.setName(advCourseXSD.getName());
        dto.setDescription(advCourseXSD.getDescription());
        dto.setCredit(advCourseXSD.getCredit());
        dto.setPrerequisites(advCourseXSD.getPrerequisites());
        return dto;
    }
}
