package com.weather.searcher.weathersearcher.searcher;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

public enum WeatherRepository {
    OPEN_WEATHER_MAP("openweathermap", "owm");

    private List<String> repository;

    public List<String> getRepository() {
        return repository;
    }

    WeatherRepository(String... repository) {
        this.repository = Arrays.asList(repository);
    }

    public static WeatherRepository fromText(String repository) {
        for (WeatherRepository weatherRepository: WeatherRepository.values()) {
            for (String r: weatherRepository.getRepository()) {
                if (r.equalsIgnoreCase(repository)) {
                    return weatherRepository;
                }
            }
        }
        throw new InvalidParameterException();
    }
}
