package org.example.springtaskdocker;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


@SpringBootTest
class SpringTaskDockerApplicationTests {

    @Test
    void main_shouldStartApplication() {
        assertDoesNotThrow(() -> SpringTaskDockerApplication.main(new String[]{}));
    }

}
