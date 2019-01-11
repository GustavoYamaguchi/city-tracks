package com.weather.searcher.weathersearcher.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CityNotFoundException extends QueryException {

    public CityNotFoundException(String message) {
        super(message);
    }
}
