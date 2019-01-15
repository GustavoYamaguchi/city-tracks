package com.city.track.core.citytrackcore.controller;

import com.city.track.core.citytrackcore.bean.Location;
import com.city.track.core.citytrackcore.bean.TrackBean;
import com.city.track.core.citytrackcore.bean.TracksResponse;
import com.city.track.core.citytrackcore.bean.WeatherBean;
import com.city.track.core.citytrackcore.proxy.Repositories;
import com.city.track.core.citytrackcore.proxy.TrackSearcherProxy;
import com.city.track.core.citytrackcore.proxy.WeatherSearcherProxy;
import com.city.track.core.citytrackcore.util.GenreResolver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlaylistMakerController {

    @Autowired
    private WeatherSearcherProxy weatherProxy;

    @Autowired
    private TrackSearcherProxy trackProxy;

    private static final Logger logger = LogManager.getLogger(PlaylistMakerController.class);

    @GetMapping("/")
    public TracksResponse createPlaylistFor(Location location,
                                            @RequestParam(required = false, defaultValue = Repositories.Weather.OPEN_WEATHER_MAP_REPOSITORY) String weatherRepo,
                                            @RequestParam(required = false, defaultValue = Repositories.Tracks.SPOTIFY_REPOSITORY) String trackRepo) {
        logger.info(String.format("Searching playlist for %s with weather repository: %s and track repository: %s",
                location.toString(), weatherRepo, trackRepo));
        WeatherBean weatherBean = weatherProxy.getWeather(weatherRepo, location.getCity(),
                location.getLatitude(), location.getLongitude());
        String genre = GenreResolver.getGenre(weatherBean);
        List<TrackBean> tracks = trackProxy.getTracksByGenre(trackRepo, genre);
        TracksResponse tracksResponse = new TracksResponse();
        tracksResponse.setGenre(genre);
        tracksResponse.setTemperature(weatherBean.getTemperature());
        tracksResponse.setTracks(tracks);
        logger.info("Found " + tracks.size() + " tracks for city " + location);
        return tracksResponse;
    }
}
