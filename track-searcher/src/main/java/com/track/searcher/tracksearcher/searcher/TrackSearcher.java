package com.track.searcher.tracksearcher.searcher;

import com.track.searcher.tracksearcher.exception.PlaylistNotFoundException;
import com.track.searcher.tracksearcher.model.Track;

import java.util.List;

public interface TrackSearcher {

    /**
     * Retrieves a playlist of a genre {genre}
     * @param genre
     */
    List<Track> getPlaylistFrom(String genre) throws PlaylistNotFoundException;

    /**
     * Retrieves cached entry for genre {genre} and repository {repository}
     * @param genre
     * @param repository
     * @return
     */
    default List<Track> retrieveCachedEntry(String genre, TrackRepository repository) {
        throw new UnsupportedOperationException();
    }
}
