package com.weather.searcher.weathersearcher.searcher;

import com.weather.searcher.weathersearcher.constants.Message;
import com.weather.searcher.weathersearcher.constants.Property;
import com.weather.searcher.weathersearcher.exception.CityNotFoundException;
import com.weather.searcher.weathersearcher.exception.InvalidLocationException;
import com.weather.searcher.weathersearcher.exception.QueryException;
import com.weather.searcher.weathersearcher.exception.UnauthorizedException;
import com.weather.searcher.weathersearcher.model.Location;
import com.weather.searcher.weathersearcher.model.LocationType;
import com.weather.searcher.weathersearcher.model.Weather;
import com.weather.searcher.weathersearcher.utils.LocationUtil;
import com.weather.searcher.weathersearcher.utils.RequestUtil;
import com.weather.searcher.weathersearcher.utils.templates.OpenWeatherMapTemplate;
import com.weather.searcher.weathersearcher.utils.templates.WeatherResponseTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;

public class OpenWeatherMapSearcher implements WeatherSearcher {

    public static final String QUERY_WEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/weather?";
    public static final String QUERY_WEATHER_BY_NAME_URL = QUERY_WEATHER_BASE_URL + "q=%s&units=metric&appid=%s";
    public static final String QUERY_WEATHER_BY_LAT_LON_URL = QUERY_WEATHER_BASE_URL +
            "lat=%s&lon=%s&units=metric&appid=%s";

    @Override
    public Weather getWeatherFrom(Location location) throws QueryException {
        String appId = System.getProperty(Property.APP_ID);

        ResponseEntity<OpenWeatherMapTemplate> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        String url = getCorrectUrl(location, appId);
        try {
            response = RequestUtil.getResponse(null, HttpMethod.GET, null, url, OpenWeatherMapTemplate.class);
        } catch (HttpStatusCodeException exception) {
            handleException(exception);
        }
        WeatherResponseTemplate weatherTemplate = response.getBody();
        Weather weather = weatherTemplate.getNormalizedWeather();
        return weather;
    }

    private void handleException(HttpStatusCodeException exception) throws QueryException {
        switch (exception.getStatusCode()) {
            case UNAUTHORIZED:
                throw new UnauthorizedException(Message.Exception.UNAUTHORIZED);
            case NOT_FOUND:
                throw new CityNotFoundException(Message.Exception.NOT_FOUND);
            default:
                throw new QueryException(Message.Exception.RESPONSE_ERROR);
        }
    }

    private String getCorrectUrl(Location location, String appId) throws InvalidLocationException {
        LocationType locationType = LocationUtil.getLocationType(location);
        switch (locationType) {
            case CITY_NAME:
                return String.format(QUERY_WEATHER_BY_NAME_URL, location.getCity(), appId);
            case LAT_LON:
                return String.format(QUERY_WEATHER_BY_LAT_LON_URL, location.getLatitude(), location.getLongitude(), appId);
            default:
                throw new InvalidLocationException(Message.Exception.INVALID_LOCATION);
        }
    }
}
