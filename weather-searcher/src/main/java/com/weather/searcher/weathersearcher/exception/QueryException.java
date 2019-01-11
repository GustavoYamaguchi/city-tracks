package com.weather.searcher.weathersearcher.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class QueryException extends Exception {

    public QueryException(String message) {
        super(message);
    }
}
