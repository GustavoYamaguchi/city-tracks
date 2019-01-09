package com.weather.searcher.weathersearcher.searcher;

public class WeatherSearcherFactory {

    public static WeatherSearcher getWeatherSearcher(WeatherRepository repository) {
        switch (repository) {
            case OPEN_WEATHER_MAP:
                return new OpenWeatherMapSearcher();
            default:
                return new OpenWeatherMapSearcher();
        }
    }
}
