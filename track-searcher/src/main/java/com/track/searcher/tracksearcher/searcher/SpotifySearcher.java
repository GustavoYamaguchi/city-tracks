package com.track.searcher.tracksearcher.searcher;

import com.track.searcher.tracksearcher.constants.Message;
import com.track.searcher.tracksearcher.constants.Propety;
import com.track.searcher.tracksearcher.exception.PlaylistNotFoundException;
import com.track.searcher.tracksearcher.exception.UnauthorizedException;
import com.track.searcher.tracksearcher.model.Track;
import com.track.searcher.tracksearcher.utils.RequestUtil;
import com.track.searcher.tracksearcher.utils.templates.ApiKey;
import com.track.searcher.tracksearcher.utils.templates.Playlist;
import com.track.searcher.tracksearcher.utils.templates.trackresponse.SpotifyTemplate;
import org.springframework.http.*;

import java.util.List;

public class SpotifySearcher implements TrackSearcher {

    public static final String QUERY_PLAYLIST_URL = "https://api.spotify.com/v1/search?q=%s&type=playlist&limit=1";
    public static final String API_KEY_URL = "https://accounts.spotify.com/api/token";
    public static final String GRANT_TYPE = "grant_type";
    public static final String CLIENT_CREDENTIALS = "client_credentials";

    @Override
    public List<Track> getPlaylistFrom(String genre) throws PlaylistNotFoundException, UnauthorizedException {
        String accessToken;
        accessToken = retrieveCachedApiKey();
        if (accessToken == null || accessToken.isEmpty()) {
            accessToken = refreshApiKey().getAccessToken();
        }

        String tracksLink = retrievePlaylistForGenre(genre, accessToken);
        SpotifyTemplate tracksTemplate = retrieveTracksFromLink(tracksLink, accessToken);
        List<Track> tracks = tracksTemplate.getNormalizedTracks();
        return tracks;
    }

    private String retrievePlaylistForGenre(String genre, String accessToken) throws PlaylistNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        String url = String.format(QUERY_PLAYLIST_URL, genre);
        ResponseEntity<Playlist> response = RequestUtil.getResponse(headers, HttpMethod.GET, null, url, Playlist.class);
        Playlist playlist = response.getBody();
        if (playlist.getPlaylists().getItems().size() > 0) {
            return playlist.getPlaylists().getItems().get(0).getTracks().getHref();
        }
        throw new PlaylistNotFoundException(String.format(Message.Exception.PLAYLIST_NOT_FOUND, genre));
    }

    private SpotifyTemplate retrieveTracksFromLink(String tracksLink, String accessToken) throws PlaylistNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        ResponseEntity<SpotifyTemplate> response = RequestUtil.getResponse(headers, HttpMethod.GET, null,
                tracksLink, SpotifyTemplate.class);
        SpotifyTemplate tracks = response.getBody();

        if (tracks.getItems().size() > 0) {
            return tracks;
        }
        throw new PlaylistNotFoundException(String.format(Message.Exception.TRACKS_NOT_FOUND));
    }

    private ApiKey refreshApiKey() throws UnauthorizedException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String clientId = System.getProperty(Propety.CLIENT_ID);
        String clientSecret = System.getProperty(Propety.CLIENT_SECRET);
        headers.setBasicAuth(clientId, clientSecret);

        String body = GRANT_TYPE + "=" + CLIENT_CREDENTIALS;

        ResponseEntity<ApiKey> response = RequestUtil.getResponse(headers, HttpMethod.POST, body,
                API_KEY_URL, ApiKey.class);
        ApiKey apiKey = response.getBody();
        if (response.getStatusCode() == HttpStatus.OK && apiKey != null && !apiKey.getAccessToken().isEmpty()) {
            return apiKey;
        }
        throw new UnauthorizedException(Message.Exception.UNAUTHORIZED);
    }

    private String retrieveCachedApiKey() {
        //TODO: retrieve api key from cache
        return null;
    }
}
