package com.weather.searcher.weathersearcher.constants;

public class Message {

    public static class Exception {
        public static final String INVALID_LOCATION = "Invalid location, check name/coordinates.";
        public static final String UNAUTHORIZED = "Unauthorized client, please check your app id.";
        public static final String NOT_FOUND = "Could not find city for given name/coordinates.";
        public static final String RESPONSE_ERROR = "Cloud not retrieve information about given location.";
    }
}
