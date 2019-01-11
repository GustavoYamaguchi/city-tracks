package com.weather.searcher.weathersearcher.utils.templates;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weather.searcher.weathersearcher.constants.JsonTemplate;
import com.weather.searcher.weathersearcher.model.Weather;

public class OpenWeatherMapTemplate implements WeatherResponseTemplate {

    @JsonProperty(JsonTemplate.OpenWeatherMap.WEATHER_DATA)
    WeatherData weatherData;

    public Weather getNormalizedWeather() {
        Weather weather = new Weather();
        weather.setTemperature(getWeatherData().getTemperature());
        return weather;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public static class WeatherData {
        @JsonProperty(JsonTemplate.OpenWeatherMap.TEMPERATURE)
        Double temperature;

        public Double getTemperature() {
            return temperature;
        }
    }
}
