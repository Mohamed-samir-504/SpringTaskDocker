package org.example.springtaskdocker.Services;

import com.generatedSources.CourseXSD;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.example.springtaskdocker.CourseWrapper;
import org.example.springtaskdocker.DTO.CourseXSDDTO;
import org.example.springtaskdocker.FeignClients.ExternalXSDClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    public List<CourseXSD> fetchCoursesFromMock() throws Exception {
        String xml = externalXSDClient.fetchCoursesXml();

        JAXBContext jaxbContext = JAXBContext.newInstance(CourseWrapper.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(xml);
        CourseWrapper wrapper = (CourseWrapper) unmarshaller.unmarshal(new StreamSource(reader));

        return wrapper.getCourses();
    }

    public CourseXSDDTO mapToDto(CourseXSD courseXSD) {
        CourseXSDDTO dto = new CourseXSDDTO();
        dto.setName(courseXSD.getName());
        dto.setDescription(courseXSD.getDescription());
        dto.setCredit(courseXSD.getCredit());
        return dto;
    }
}
