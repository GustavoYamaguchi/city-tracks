package com.weather.searcher.weathersearcher.utils.templates;

import com.google.gson.Gson;
import com.weather.searcher.weathersearcher.exception.QueryException;
import com.weather.searcher.weathersearcher.model.Weather;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OpenWeatherMapTemplateTests {

    @Test
    public void testOpenWeatherMapTemplate() throws QueryException {
        WeatherResponseTemplate template = new Gson().fromJson(Response.OPEN_WEATHER_VALID_RESPONSE,
                OpenWeatherMapTemplate.class);
        Weather weather = new Weather();
        weather.setTemperature(new Double("27"));
        assertEquals(weather, template.getNormalizedWeather());
    }
}
