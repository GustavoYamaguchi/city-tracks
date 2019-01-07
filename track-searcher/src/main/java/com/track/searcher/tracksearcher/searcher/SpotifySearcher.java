package com.track.searcher.tracksearcher.searcher;

import com.track.searcher.tracksearcher.constants.Message;
import com.track.searcher.tracksearcher.exception.PlaylistNotFoundException;
import com.track.searcher.tracksearcher.model.Track;
import com.track.searcher.tracksearcher.utils.templates.Playlist;
import com.track.searcher.tracksearcher.utils.templates.RequestUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.List;

public class SpotifySearcher implements TrackSearcher {

    public static final String QUERY_PLAYLIST_URL = "https://api.spotify.com/v1/search?q=%s&type=playlist&limit=1";
    public static final String API_KEY_URL = "https://accounts.spotify.com/api/token";

    @Override
    public List<Track> getPlaylistFrom(String genre) throws PlaylistNotFoundException {
        String apiKey;
        apiKey = retrieveCachedApiKey();
        if (apiKey == null || apiKey.isEmpty()) {
            apiKey = refreshApiKey();
        }

        String tracksLink = retrievePlaylistForGenre(genre, apiKey);
        List<Track> tracks = retrieveTracksFromLink(tracksLink, apiKey);
        return tracks;
    }

    private String retrievePlaylistForGenre(String genre, String apiKey) throws PlaylistNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);

        String url = String.format(QUERY_PLAYLIST_URL, genre);
        Playlist playlist = RequestUtil.getResponse(headers, HttpMethod.GET, QUERY_PLAYLIST_URL, Playlist.class);
        if (playlist.getPlaylists().getItems().size() > 0) {
            return playlist.getPlaylists().getItems().get(0).getTracks().getHref();
        }
        throw new PlaylistNotFoundException(String.format(Message.Exception.PLAYLIST_NOT_FOUND, genre));
    }

    private List<Track> retrieveTracksFromLink(String tracksLink, String apiKey) {
        throw new UnsupportedOperationException();
    }

    private String retrieveCachedApiKey() {
        throw new UnsupportedOperationException();
    }

    private String refreshApiKey() {
        throw new UnsupportedOperationException();
    }
}
