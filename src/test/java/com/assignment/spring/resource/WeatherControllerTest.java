package com.assignment.spring.resource;

import com.assignment.spring.resource.model.WeatherInfoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({MockitoExtension.class})
@SpringBootTest
@Sql("classpath:testcases/initial-dataset.sql")
public class WeatherControllerTest {

    private static final String TEST_CASES_FOLDER = "testcases/weather/";

    private MockMvc restDeliveryStepAnnounceMockMvc;
    @Autowired
    private WebApplicationContext applicationContext;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    public void testWeather_Success() throws Exception {
        MvcResult result = restDeliveryStepAnnounceMockMvc
                .perform(post("weather/").content(readFileContent(TEST_CASES_FOLDER + "WeatherRequest.json"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(objectMapper.readValue(
                readFileContent(TEST_CASES_FOLDER + "testWeather_Success.json"), WeatherInfoResponse.class))
                .isEqualTo(objectMapper.readValue(result.getResponse().getContentAsString(), WeatherInfoResponse.class));
    }


    private static String readFileContent(String filePath) throws IOException {
        Resource resource = new ClassPathResource(filePath);
        String text;
        try (Scanner scanner = new Scanner(resource.getInputStream(), StandardCharsets.UTF_8.name())) {
            text = scanner.useDelimiter("\\A").next();
        }
        return text;

    }
}
