package com.assignment.spring.resource.model;

import javax.validation.constraints.NotEmpty;

public class WeatherInfoRequest {

    @NotEmpty(message = "Parameter 'city' is required!")
    private String city;

    public WeatherInfoRequest(@NotEmpty String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "WeatherInfoRequest{" +
                "city='" + city + '\'' +
                '}';
    }
}
