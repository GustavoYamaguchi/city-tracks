package com.weather.searcher.weathersearcher.controller;

import com.weather.searcher.weathersearcher.exception.QueryException;
import com.weather.searcher.weathersearcher.model.Location;
import com.weather.searcher.weathersearcher.model.Weather;
import com.weather.searcher.weathersearcher.searcher.WeatherRepository;
import com.weather.searcher.weathersearcher.searcher.WeatherSearcher;
import com.weather.searcher.weathersearcher.searcher.WeatherSearcherFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidParameterException;

@RestController
public class WeatherRestController {

    private static final Logger logger = LogManager.getLogger(WeatherRestController.class);

    @GetMapping(value = {"/{repository}", "/"})
    public Weather getWeather(@PathVariable String repository, Location location) throws QueryException {
        logger.info("Getting weather from " + location);
        WeatherRepository weatherRepository;
        try {
            weatherRepository = WeatherRepository.fromText(repository);
        } catch (InvalidParameterException e) {
            weatherRepository = WeatherRepository.OPEN_WEATHER_MAP;
        }
        WeatherSearcher weatherSearcher = WeatherSearcherFactory.getWeatherSearcher(weatherRepository);

        Weather weather = weatherSearcher.retrieveCachedWeather(weatherRepository, location);
        if (weather == null) {
            weather = weatherSearcher.getWeatherFrom(location);
        }
        logger.info("Weather in " + location + "is " + weather.getTemperature() + " degrees Celsius");
        return weather;
    }
}
