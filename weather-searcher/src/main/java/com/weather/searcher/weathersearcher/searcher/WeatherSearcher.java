package com.weather.searcher.weathersearcher.searcher;

import com.weather.searcher.weathersearcher.exception.QueryException;
import com.weather.searcher.weathersearcher.model.Location;
import com.weather.searcher.weathersearcher.model.Weather;

public interface WeatherSearcher {

    public Weather getWeatherFrom(Location location) throws QueryException;

    default public Weather retrieveCachedWeather(WeatherRepository weatherRepository, Location location) {
        //TODO: implement cache service
        return null;
    }
}
