package com.track.searcher.tracksearcher.utils;

import com.track.searcher.tracksearcher.model.Track;
import com.track.searcher.tracksearcher.utils.templates.TracksTemplate;

import java.util.ArrayList;
import java.util.List;

public class UnmarshalTracks {
    public static List<Track> unmarshalTracks(TracksTemplate tracksTemplate) {
        List<Track> tracks = new ArrayList<>();
        if (tracksTemplate.getItems() != null && tracksTemplate.getItems().size() > 0) {
            tracksTemplate.getItems().stream().forEach(t -> {
                Track track = new Track();
                track.setName(t.getTrack().getName());
                track.setArtists(getAllArtists(t));
                track.setAlbumName(t.getTrack().getAlbum().getName());
                tracks.add(track);
            });
        }
        return tracks;
    }

    private static List<String> getAllArtists(TracksTemplate.Item t) {
        List<String> artists = new ArrayList<>();
        t.getTrack().getArtists().stream().forEach(a -> artists.add(a.getName()));
        return artists;
    }
}
