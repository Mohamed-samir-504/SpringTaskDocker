package org.example.springtaskdocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringTaskDockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTaskDockerApplication.class, args);
    }

}
