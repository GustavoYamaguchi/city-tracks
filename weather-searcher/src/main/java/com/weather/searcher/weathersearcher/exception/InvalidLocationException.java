package com.weather.searcher.weathersearcher.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidLocationException extends QueryException {

    public InvalidLocationException(String message) {
        super(message);
    }
}
