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
        wireMockServer = new WireMockServer(8088);
        wireMockServer.start();
        configureFor("localhost", 8088);
    }

    @AfterAll
    static void teardown() {
        wireMockServer.stop();
    }

    @Test
    void getXSDService_shouldReturnCustomJsonResponse() throws Exception {

        stubFor(WireMock.get(urlEqualTo("/xsdService/list"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/xml")
                        .withBody("""
                        <courses xmlns="http://example.org/course">
                              <course>
                                   <id>1</id>
                                   <name>Business analysis</name>
                                   <description>Learn Business analysis</description>
                                   <credit>3</credit>
                              </course>
                              <course>
                                   <id>2</id>
                                   <name>Business administration</name>
                                   <description>Learn Business administration</description>
                                   <credit>4</credit>
                              </course>
                        </courses>
                    """)));


        mockMvc.perform(get("/coursesXSD").with(httpBasic("admin", "admin123"))
                        .header("x-validation-report", "true"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2)) // 2 courses returned
                .andExpect(jsonPath("$[0].name").value("Business analysis"))
                .andExpect(jsonPath("$[0].description").value("Learn Business analysis"))
                .andExpect(jsonPath("$[0].credit").value(3))
                .andExpect(jsonPath("$[1].name").value("Business administration"))
                .andExpect(jsonPath("$[1].description").value("Learn Business administration"))
                .andExpect(jsonPath("$[1].credit").value(4));
    }
}
