package com.weather.searcher.weathersearcher.controller;

import com.weather.searcher.weathersearcher.searcher.WeatherRepository;
import com.weather.searcher.weathersearcher.searcher.WeatherSearcher;
import com.weather.searcher.weathersearcher.model.Weather;
import com.weather.searcher.weathersearcher.searcher.WeatherSearcherFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherRestController {

    @GetMapping(value = "/{repository}")
    public Weather getWeather(@PathVariable String repository, Location location) {
        WeatherRepository weatherRepository = WeatherRepository.fromText(repository);
        WeatherSearcher weatherSearcher = WeatherSearcherFactory.getWeatherSearcher(weatherRepository);

        Weather weather = weatherSearcher.retrieveCachedWeather(weatherRepository, location);
        if (weather == null) {
            weather = weatherSearcher.getWeatherFrom(location);
        }
        return weather;
    }
}
