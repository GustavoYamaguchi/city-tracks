package com.weather.searcher.weathersearcher.searcher;

import com.weather.searcher.weathersearcher.constants.Message;
import com.weather.searcher.weathersearcher.exception.InvalidLocationException;
import com.weather.searcher.weathersearcher.model.Location;
import com.weather.searcher.weathersearcher.model.LocationType;
import com.weather.searcher.weathersearcher.model.Weather;
import com.weather.searcher.weathersearcher.utils.LocationUtil;
import com.weather.searcher.weathersearcher.utils.RequestUtil;
import com.weather.searcher.weathersearcher.utils.templates.WeatherResponseTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class OpenWeatherMapSearcher implements WeatherSearcher {

    public static final String QUERY_WEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/weather?";
    public static final String QUERY_WEATHER_BY_NAME_URL = QUERY_WEATHER_BASE_URL + "q=%s&units=metric&appid=%s";
    public static final String QUERY_WEATHER_BY_LAT_LON_URL = QUERY_WEATHER_BASE_URL +
            "lat=%s&lon=%s&units=metric&appid=%s";

    @Override
    public Weather getWeatherFrom(Location location) throws InvalidLocationException {
        String appId = System.getProperty("app.id");

        LocationType locationType = LocationUtil.getLocationType(location);
        ResponseEntity<WeatherResponseTemplate> response;
        String url;
        switch (locationType) {
            case CITY:
                url = String.format(QUERY_WEATHER_BY_NAME_URL, location.getCity(), appId);
                response = RequestUtil.getResponse(null, HttpMethod.GET, null, url, WeatherResponseTemplate.class);
                break;
            case LAT_LON:
                url = String.format(QUERY_WEATHER_BY_LAT_LON_URL, location.getLatitude(), location.getLongitude(), appId);
                response = RequestUtil.getResponse(null, HttpMethod.GET, null, url, WeatherResponseTemplate.class);
                break;
            default:
                throw new InvalidLocationException(Message.Exception.INVALID_LOCATION);
        }
        WeatherResponseTemplate weatherTemplate = response.getBody();
        Weather weather = weatherTemplate.getNormalizedWeather();
        return weather;
    }
}
