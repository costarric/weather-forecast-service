package com.assignment.spring;

import com.assignment.spring.api.OpenWeatherProperties;
import com.assignment.spring.api.OpenWeatherService;
import com.assignment.spring.service.WeatherForecast;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    WeatherForecast weatherForecast(
            OpenWeatherProperties openWeatherProperties,
            RestTemplate restTemplate) {
        return new OpenWeatherService(openWeatherProperties, restTemplate);
    }
}
