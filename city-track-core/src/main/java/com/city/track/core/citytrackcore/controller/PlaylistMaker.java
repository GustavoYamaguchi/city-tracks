package com.city.track.core.citytrackcore.controller;

import com.city.track.core.citytrackcore.bean.Location;
import com.city.track.core.citytrackcore.bean.TrackBean;
import com.city.track.core.citytrackcore.bean.TracksResponse;
import com.city.track.core.citytrackcore.bean.WeatherBean;
import com.city.track.core.citytrackcore.proxy.TrackSearcherProxy;
import com.city.track.core.citytrackcore.proxy.WeatherSearcherProxy;
import com.city.track.core.citytrackcore.util.GenreResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlaylistMaker {

    @Autowired
    private WeatherSearcherProxy weatherProxy;

    @Autowired
    private TrackSearcherProxy trackProxy;

    @GetMapping("/")
    public TracksResponse createPlaylistFor(Location location) {
        WeatherBean weatherBean = weatherProxy.getWeatherFromOWM(location.getCity(),
                location.getLatitude(), location.getLongitude());
        String genre = GenreResolver.getGenre(weatherBean);
        List<TrackBean> tracks = trackProxy.getTracksFromSpotifyByGenre(genre);
        TracksResponse tracksResponse = new TracksResponse();
        tracksResponse.setGenre(genre);
        tracksResponse.setTemperature(weatherBean.getTemperature());
        tracksResponse.setTracks(tracks);
        return tracksResponse;
    }
}
