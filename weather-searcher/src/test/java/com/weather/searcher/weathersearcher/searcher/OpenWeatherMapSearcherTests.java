package com.weather.searcher.weathersearcher.searcher;

import com.google.gson.Gson;
import com.weather.searcher.weathersearcher.exception.CityNotFoundException;
import com.weather.searcher.weathersearcher.exception.QueryException;
import com.weather.searcher.weathersearcher.exception.UnauthorizedException;
import com.weather.searcher.weathersearcher.model.Location;
import com.weather.searcher.weathersearcher.model.Weather;
import com.weather.searcher.weathersearcher.utils.RequestUtil;
import com.weather.searcher.weathersearcher.utils.templates.OpenWeatherMapTemplate;
import com.weather.searcher.weathersearcher.utils.templates.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({OpenWeatherMapSearcher.class, RequestUtil.class})
public class OpenWeatherMapSearcherTests {

    private OpenWeatherMapSearcher searcher;

    @Before
    public void setUp() {
        searcher = PowerMockito.spy(new OpenWeatherMapSearcher());
    }

    @Test
    public void testRequestToOpenWeatherMap() throws QueryException {
        PowerMockito.mockStatic(RequestUtil.class);
        ResponseEntity<Object> response = new ResponseEntity<>(
                new Gson().fromJson(Response.OPEN_WEATHER_VALID_RESPONSE, OpenWeatherMapTemplate.class), HttpStatus.OK);
        BDDMockito.given(RequestUtil.getResponse(Mockito.any(),
                Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).willReturn(response);

        Location location = new Location();
        location.setCity("city");
        Weather expectedWeather = new Weather();
        expectedWeather.setTemperature(new Double("27"));
        assertEquals(expectedWeather, searcher.getWeatherFrom(location));
    }

    @Test
    public void testUnauthorizedRequestToOpenWeatherMap() {
        PowerMockito.mockStatic(RequestUtil.class);
        BDDMockito.given(RequestUtil.getResponse(Mockito.any(),
                Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).willThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED));

        Location location = new Location();
        location.setCity("city");
        Weather expectedWeather = new Weather();
        expectedWeather.setTemperature(new Double("27"));
        try {
            searcher.getWeatherFrom(location);
            fail();
        } catch (QueryException e) {
            assertTrue(e instanceof UnauthorizedException);
        }
    }

    @Test
    public void testNotFoundRequestToOpenWeatherMap() {
        PowerMockito.mockStatic(RequestUtil.class);
        BDDMockito.given(RequestUtil.getResponse(Mockito.any(),
                Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).willThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        Location location = new Location();
        location.setCity("city");
        Weather expectedWeather = new Weather();
        expectedWeather.setTemperature(new Double("27"));
        try {
            searcher.getWeatherFrom(location);
            fail();
        } catch (QueryException e) {
            assertTrue(e instanceof CityNotFoundException);
        }
    }

    @Test
    public void testUnknowErrorRequestToOpenWeatherMap() {
        PowerMockito.mockStatic(RequestUtil.class);
        BDDMockito.given(RequestUtil.getResponse(Mockito.any(),
                Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).willThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

        Location location = new Location();
        location.setCity("city");
        Weather expectedWeather = new Weather();
        expectedWeather.setTemperature(new Double("27"));
        try {
            searcher.getWeatherFrom(location);
            fail();
        } catch (QueryException e) {
            assertFalse(e instanceof CityNotFoundException);
            assertFalse(e instanceof UnauthorizedException);
        }
    }
}
