package com.assignment.spring.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("open-weather")
public class OpenWeatherProperties {
    private String contextRoot;
    private String weatherResourcePath;
    private String appId;

    public OpenWeatherProperties(String contextRoot, String weatherResourcePath, String appId) {
        this.contextRoot = contextRoot;
        this.weatherResourcePath = weatherResourcePath;
        this.appId = appId;
    }

    public String getContextRoot() {
        return contextRoot;
    }

    public void setContextRoot(String contextRoot) {
        this.contextRoot = contextRoot;
    }

    public String getWeatherResourcePath() {
        return weatherResourcePath;
    }

    public void setWeatherResourcePath(String weatherResourcePath) {
        this.weatherResourcePath = weatherResourcePath;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}