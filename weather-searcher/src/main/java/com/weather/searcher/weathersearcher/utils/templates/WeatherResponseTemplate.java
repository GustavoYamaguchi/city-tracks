package com.weather.searcher.weathersearcher.utils.templates;

import com.weather.searcher.weathersearcher.exception.QueryException;
import com.weather.searcher.weathersearcher.model.Weather;

public interface WeatherResponseTemplate {

    public Weather getNormalizedWeather() throws QueryException;
}
