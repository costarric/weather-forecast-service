package com.assignment.spring.resource;

import com.assignment.spring.resource.model.WeatherInfoResponse;
import com.assignment.spring.service.WeatherForecastResponse;
import org.springframework.stereotype.Component;

@Component
public class WeatherMapper {

    public WeatherInfoResponse mapFromWeatherResponse(WeatherForecastResponse weatherResponse) {
        return new WeatherInfoResponse.WeatherInfoResponseBuilder().build(
                weatherResponse.getCity(), weatherResponse.getCountry(), weatherResponse.getTemperature());
    }
}
