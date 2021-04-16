package com.assignment.spring.resource;

import com.assignment.spring.resource.model.WeatherInfoRequest;
import com.assignment.spring.resource.model.WeatherInfoResponse;
import com.assignment.spring.service.WeatherService;
import com.assignment.spring.service.WeatherForecastResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class WeatherController {

    private final WeatherMapper weatherMapper;
    private final WeatherService weatherService;

    public WeatherController(WeatherMapper weatherMapper, WeatherService weatherService) {
        this.weatherMapper = weatherMapper;
        this.weatherService = weatherService;
    }

    /**
     * Fetch weather for given city and store it on the database.
     * Temperature is shown in kelvins.
     *
     * @param request weather request
     * @return weather info for the given city.
     */
    @RequestMapping("/weather")
    public WeatherInfoResponse weather(@Valid @RequestBody WeatherInfoRequest request) {
        WeatherForecastResponse weatherForecast = weatherService.fetchWeatherForecast(request);
        weatherService.saveWeatherForecast(weatherForecast);
        return weatherMapper.mapFromWeatherResponse(weatherForecast);
    }
}
