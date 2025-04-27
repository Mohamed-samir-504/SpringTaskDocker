package org.example.springtaskdocker.Services;

import generatedSources.org.example.course.CourseXSD;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.example.springtaskdocker.CourseWrapper;
import org.example.springtaskdocker.DTO.CourseXSDDTO;
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

    private final RestTemplate restTemplate;

    @Value("${external.xsd.service.api}")
    private String serviceApiUrl;

    public ExternalXSDService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public String getService() {
        return restTemplate.getForObject(serviceApiUrl + "/xsdService/list",String.class);
    }

    public List<CourseXSD> fetchCoursesFromMock() throws Exception {
        String xml = getService();

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
