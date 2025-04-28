package org.example.springtaskdocker.FeignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "external-xsd-client", url = "localhost:8089/xsdService")
public interface ExternalXSDClient {

    @GetMapping("/list")
    String fetchCoursesXml();
}