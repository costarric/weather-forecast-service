package com.assignment.spring.service;

import com.assignment.spring.domain.WeatherEntity;
import com.assignment.spring.exceptionhandle.ExceptionTranslator;
import com.assignment.spring.repository.WeatherRepository;
import com.assignment.spring.resource.model.WeatherInfoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WeatherService {

    private final Logger logger = LoggerFactory.getLogger(WeatherService.class);
    private final WeatherForecast weatherForecast;
    private final WeatherRepository weatherRepository;

    public WeatherService(WeatherForecast weatherForecast, WeatherRepository weatherRepository) {
        this.weatherForecast = weatherForecast;
        this.weatherRepository = weatherRepository;
    }

    public WeatherForecastResponse fetchWeatherForecast(WeatherInfoRequest request) {
        logger.debug("Fetching weather forecast for {}", request);
        return weatherForecast.weatherForecastByCity(request.getCity());
    }

    @Transactional
    public void saveWeatherForecast(WeatherForecastResponse weatherForecast) {
        WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setCity(weatherForecast.getCity());
        weatherEntity.setCity(weatherForecast.getCountry());
        weatherEntity.setTemperature(weatherEntity.getTemperature());

        weatherRepository.save(weatherEntity);
    }
}
