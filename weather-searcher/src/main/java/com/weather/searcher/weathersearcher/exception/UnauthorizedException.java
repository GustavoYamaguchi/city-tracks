package com.weather.searcher.weathersearcher.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends QueryException {

    public UnauthorizedException(String message) {
        super(message);
    }
}
