package com.track.searcher.tracksearcher.controller;

import com.track.searcher.tracksearcher.exception.PlaylistNotFoundException;
import com.track.searcher.tracksearcher.exception.UnauthorizedException;
import com.track.searcher.tracksearcher.model.Track;
import com.track.searcher.tracksearcher.searcher.TrackRepository;
import com.track.searcher.tracksearcher.searcher.TrackSearcher;
import com.track.searcher.tracksearcher.searcher.TrackSearcherFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrackRestController {

    private static final Logger logger = LogManager.getLogger(TrackRestController.class);

    @GetMapping(value = {"/{repository}/{genre}", "/{genre}"})
    public List<Track> getTracks(@PathVariable String genre, @PathVariable String repository)
            throws PlaylistNotFoundException, UnauthorizedException {
        logger.info("Getting track for genre " + genre);
        TrackRepository trackRepository;
        try {
            trackRepository = TrackRepository.fromText(repository);
        } catch (IllegalArgumentException e) {
            trackRepository = TrackRepository.SPOTIFY;
        }
        TrackSearcher trackSearcher = TrackSearcherFactory.getTrackSearcher(trackRepository);
        List<Track> tracks;

        tracks = trackSearcher.retrieveCachedPlaylist(genre, trackRepository);
        if (tracks == null) {
            tracks = trackSearcher.getPlaylistFrom(genre);
        }
        logger.info("Found " + tracks.size() + " tracks for genre " + genre);
        return tracks;
    }
}
