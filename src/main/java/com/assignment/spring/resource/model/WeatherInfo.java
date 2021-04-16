package com.assignment.spring.resource.model;

public class WeatherInfo {

    private String temperature;

    public WeatherInfo(String temperature) {
        this.temperature = temperature;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

}
