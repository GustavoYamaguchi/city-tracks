package com.track.searcher.tracksearcher.searcher;

public class TrackSearcherFactory {

    public static TrackSearcher getTrackSearcher(TrackRepository repository) {
        switch (repository) {
            case DEEZER:
                throw new UnsupportedOperationException();
            case YOUTUBE:
                throw new UnsupportedOperationException();
            case SPOTIFY:
                return new SpotifySearcher();
            default:
                return new SpotifySearcher();
        }
    }
}
