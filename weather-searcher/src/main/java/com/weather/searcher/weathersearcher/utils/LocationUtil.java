package com.weather.searcher.weathersearcher.utils;

import com.weather.searcher.weathersearcher.model.Location;
import com.weather.searcher.weathersearcher.model.LocationType;

public class LocationUtil {

    public static LocationType getLocationType(Location location) {
        if (location.getCity() != null && !location.getCity().isEmpty()) {
            return LocationType.CITY_NAME;
        }
        if (location.getLatitude() == null || location.getLongitude() == null) {
            return LocationType.INVALID;
        }
        double lat;
        double lon;
        try {
            lat = Double.parseDouble(location.getLatitude());
            lon = Double.parseDouble(location.getLongitude());
        } catch (NumberFormatException e) {
            return LocationType.INVALID;
        }
        if (Math.abs(lat) <= 90 && Math.abs(lon) <= 180) {
            return LocationType.LAT_LON;
        }
        return LocationType.INVALID;
    }
}
