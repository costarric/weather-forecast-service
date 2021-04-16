package com.assignment.spring.service;

public interface WeatherForecast {

    /**
     * Fetch weather information for the given city.
     *
     * @param city
     * @return weather forecast response
     */
    WeatherForecastResponse weatherForecastByCity(String city);
}
