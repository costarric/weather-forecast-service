package com.assignment.spring.api;

import com.assignment.spring.service.WeatherForecast;
import com.assignment.spring.service.WeatherForecastResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class OpenWeatherService implements WeatherForecast {

    private final OpenWeatherProperties openWeatherProperties;
    private final RestTemplate restTemplate;

    public OpenWeatherService(OpenWeatherProperties openWeatherProperties, RestTemplate restTemplate) {
        this.openWeatherProperties = openWeatherProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public WeatherForecastResponse weatherForecastByCity(String city) {
        String url = UriComponentsBuilder.fromUriString(openWeatherProperties.getContextRoot())
                .path(openWeatherProperties.getWeatherResourcePath())
                .queryParam("q", city)
                .queryParam("appid", openWeatherProperties.getAppId())
                .toUriString();

        WeatherResponse weatherResponse = restTemplate.getForEntity(url, WeatherResponse.class).getBody();

        if (weatherResponse == null) {
            throw new WeatherForecastException();
        }

        return new WeatherForecastResponse(weatherResponse.getName(),
                weatherResponse.getSys().getCountry(),
                weatherResponse.getMain().getTemp());
    }
}
