package com.city.track.core.citytrackcore.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class WeatherBean {

    private Double temperature;

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}
