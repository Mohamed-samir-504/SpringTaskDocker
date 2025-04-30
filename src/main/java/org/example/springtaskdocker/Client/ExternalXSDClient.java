package org.example.springtaskdocker.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "external-xsd-client", url = "${external.xsd.service.api}")
public interface ExternalXSDClient {

    @GetMapping("/xsdService/list")
    String fetchCoursesXml();
}