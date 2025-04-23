package org.example.springtaskdocker.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiService {

    private final RestTemplate restTemplate;

    @Value("${external.service.api}")
    private String serviceApiUrl;

    public ExternalApiService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public String getService(Long courseId) {
        return restTemplate.getForObject(serviceApiUrl + "/service/" + courseId, String.class);
    }
}
