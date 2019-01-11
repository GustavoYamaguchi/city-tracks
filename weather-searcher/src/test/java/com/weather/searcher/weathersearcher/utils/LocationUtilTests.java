package com.weather.searcher.weathersearcher.utils;

import com.weather.searcher.weathersearcher.model.Location;
import com.weather.searcher.weathersearcher.model.LocationType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LocationUtilTests {

    private Location location = new Location();

    @Test
    public void testCityName() {
        location.setCity("city");
        assertEquals(LocationType.CITY_NAME, LocationUtil.getLocationType(location));
        location.setCity("city");
        location.setLatitude("12");
        location.setLongitude("4.5");
        assertEquals(LocationType.CITY_NAME, LocationUtil.getLocationType(location));
        location.setCity("city");
        location.setLatitude("notParsable");
        location.setLongitude("notParsable");
        assertEquals(LocationType.CITY_NAME, LocationUtil.getLocationType(location));
    }

    @Test
    public void testNotParsableLatLon() {
        location.setLatitude("12");
        location.setLongitude("notParsable");
        assertEquals(LocationType.INVALID, LocationUtil.getLocationType(location));
        location.setLatitude("notParsable");
        location.setLongitude("12");
        assertEquals(LocationType.INVALID, LocationUtil.getLocationType(location));
        location.setLatitude("notParsable");
        location.setLongitude("notParsable");
        assertEquals(LocationType.INVALID, LocationUtil.getLocationType(location));
        location.setLatitude(null);
        location.setLongitude("12");
        assertEquals(LocationType.INVALID, LocationUtil.getLocationType(location));
        location.setLatitude("12");
        location.setLongitude(null);
        assertEquals(LocationType.INVALID, LocationUtil.getLocationType(location));
    }

    @Test
    public void testParsableLatLon() {
        location.setLatitude("12");
        location.setLongitude("12");
        assertEquals(LocationType.LAT_LON, LocationUtil.getLocationType(location));
        location.setLatitude("90");
        location.setLongitude("12");
        assertEquals(LocationType.LAT_LON, LocationUtil.getLocationType(location));
        location.setLatitude("-90");
        location.setLongitude("12");
        assertEquals(LocationType.LAT_LON, LocationUtil.getLocationType(location));
        location.setLatitude("12");
        location.setLongitude("-90.1");
        assertEquals(LocationType.LAT_LON, LocationUtil.getLocationType(location));
        location.setLatitude("12");
        location.setLongitude("-180.0");
        assertEquals(LocationType.LAT_LON, LocationUtil.getLocationType(location));
        location.setLatitude("12");
        location.setLongitude("180.0");
        assertEquals(LocationType.LAT_LON, LocationUtil.getLocationType(location));
        location.setLatitude("12");
        location.setLongitude("-180.1");
        assertEquals(LocationType.INVALID, LocationUtil.getLocationType(location));
        location.setLatitude("-90.1");
        location.setLongitude("12");
        assertEquals(LocationType.INVALID, LocationUtil.getLocationType(location));
        location.setLatitude("12");
        location.setLongitude("180.1");
        assertEquals(LocationType.INVALID, LocationUtil.getLocationType(location));
        location.setLatitude("90.1");
        location.setLongitude("12");
        assertEquals(LocationType.INVALID, LocationUtil.getLocationType(location));
        location.setLatitude("90.1");
        location.setLongitude("180");
        assertEquals(LocationType.INVALID, LocationUtil.getLocationType(location));
    }
}
