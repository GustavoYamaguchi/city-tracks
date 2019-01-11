package com.weather.searcher.weathersearcher.model;

import java.util.Objects;

public class Weather {

    private Double temperature;

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return Objects.equals(temperature, weather.temperature);
    }

    @Override
    public int hashCode() {

        return Objects.hash(temperature);
    }
}
