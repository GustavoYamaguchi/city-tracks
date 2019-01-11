package com.weather.searcher.weathersearcher.utils.templates;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.weather.searcher.weathersearcher.constants.JsonTemplate;
import com.weather.searcher.weathersearcher.constants.Message;
import com.weather.searcher.weathersearcher.exception.QueryException;
import com.weather.searcher.weathersearcher.model.Weather;

public class OpenWeatherMapTemplate implements WeatherResponseTemplate {

    @JsonProperty(JsonTemplate.OpenWeatherMap.WEATHER_DATA)
    @SerializedName(JsonTemplate.OpenWeatherMap.WEATHER_DATA)
    private WeatherData weatherData;

    public Weather getNormalizedWeather() throws QueryException {
        Weather weather = new Weather();
        if (getWeatherData() != null && getWeatherData().getTemperature() != null) {
            weather.setTemperature(getWeatherData().getTemperature());
            return weather;
        }
        throw new QueryException(Message.Exception.RESPONSE_ERROR);
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    public static class WeatherData {
        @JsonProperty(JsonTemplate.OpenWeatherMap.TEMPERATURE)
        @SerializedName(JsonTemplate.OpenWeatherMap.TEMPERATURE)
        private Double temperature;

        public Double getTemperature() {
            return temperature;
        }

        public void setTemperature(Double temperature) {
            this.temperature = temperature;
        }
    }
}
