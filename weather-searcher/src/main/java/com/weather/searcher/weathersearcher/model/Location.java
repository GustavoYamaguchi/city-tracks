package com.weather.searcher.weathersearcher.model;

import java.util.Objects;

public class Location {

    private String city;
    private String latitude;
    private String longitude;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(city, location.city) ||
                (Objects.equals(latitude, location.latitude) &&
                Objects.equals(longitude, location.longitude));
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, latitude, longitude);
    }

    @Override
    public String toString() {
        return "Location {" +
                "city='" + city + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                "} ";
    }
}
