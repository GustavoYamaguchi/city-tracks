package com.track.searcher.tracksearcher.controller;

import com.track.searcher.tracksearcher.exception.PlaylistNotFoundException;
import com.track.searcher.tracksearcher.model.Track;
import com.track.searcher.tracksearcher.searcher.TrackRepository;
import com.track.searcher.tracksearcher.searcher.TrackSearcher;
import com.track.searcher.tracksearcher.searcher.TrackSearcherFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrackRestController {

    @GetMapping(value = {"/{genre}", "/{genre}/{repository}"})
    public List<Track> getTracks(@PathVariable String genre, @PathVariable String repository) throws PlaylistNotFoundException {
        TrackRepository trackRepository = TrackRepository.fromText(repository);
        TrackSearcher trackSearcher = TrackSearcherFactory.getTrackSearcher(trackRepository);
        List<Track> tracks;

        tracks = trackSearcher.retrieveCachedEntry(genre, trackRepository);
        if (tracks == null){
            tracks = trackSearcher.getPlaylistFrom(genre);
        }
        return tracks;
    }
}
