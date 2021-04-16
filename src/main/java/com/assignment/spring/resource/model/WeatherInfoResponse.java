package com.assignment.spring.resource.model;

public class WeatherInfoResponse {
    private String city;
    private String country;
    private WeatherInfo weatherInfo;

    private WeatherInfoResponse(String city, String country, WeatherInfo weatherInfo) {
        this.city = city;
        this.country = country;
        this.weatherInfo = weatherInfo;
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

    public WeatherInfo getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(WeatherInfo weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

    public static class WeatherInfoResponseBuilder {
        public WeatherInfoResponse build(String city, String country, Double temperature) {
            return new WeatherInfoResponse(city, country, new WeatherInfo(displayTemperature(temperature)));
        }

        private String displayTemperature(Double temperature) {
            return String.format("%f Kelvins.", temperature);
        }
    }
}
