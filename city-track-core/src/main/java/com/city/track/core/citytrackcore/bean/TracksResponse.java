package com.city.track.core.citytrackcore.bean;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"genre", "temperature", "tracks"})
public class TracksResponse {

    private String genre;
    private Double temperature;
    private List<TrackBean> tracks;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public List<TrackBean> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackBean> tracks) {
        this.tracks = tracks;
    }
}
