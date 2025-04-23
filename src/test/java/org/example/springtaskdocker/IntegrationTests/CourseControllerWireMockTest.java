package org.example.springtaskdocker.IntegrationTests;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.test.web.servlet.RequestBuilder;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest()
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CourseControllerWireMockTest {

    @Autowired
    private MockMvc mockMvc;

    private static WireMockServer wireMockServer;


    @BeforeAll
    static void setup() {
        wireMockServer = new WireMockServer(8089);
        wireMockServer.start();

        configureFor("localhost", 8089);
    }

    @AfterAll
    static void teardown() {
        wireMockServer.stop();
    }

    @Test
    void getRating_shouldReturnCustomJsonResponse() throws Exception {
        //Stub external API to return custom JSON
        stubFor(WireMock.get(urlEqualTo("/service/1"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                        {
                            "courseId": 1,
                            "rating": 5,
                            "reviews": 125
                        }
                    """)));



        mockMvc.perform(get("/courses/1/service").with(httpBasic("admin", "admin123")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.courseId").value(1))
                .andExpect(jsonPath("$.rating").value(5))
                .andExpect(jsonPath("$.reviews").value(125));
    }
}
