package com.assignment.spring.service;

public class WeatherForecastResponse {

    private String city;
    private String country;
    private Double temperature;

    public WeatherForecastResponse(String city, String country, Double temperature) {
        this.city = city;
        this.country = country;
        this.temperature = temperature;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}
