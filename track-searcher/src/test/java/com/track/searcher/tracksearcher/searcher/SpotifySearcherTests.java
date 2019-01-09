package com.track.searcher.tracksearcher.searcher;

import com.track.searcher.tracksearcher.utils.templates.ApiKey;
import com.track.searcher.tracksearcher.utils.templates.TracksTemplate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.times;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SpotifySearcher.class)
public class SpotifySearcherTests {

    private SpotifySearcher spotifySearcher;

    private String fakeAccessToken = "token";
    private Long fakeExpiresIn = new Long(3600);
    private ApiKey apiKey = new ApiKey();
    private String fakeUrl = "http://fakeurl.com";
    private String fakeGenre = "genre";

    @Before
    public void setUp() {
        apiKey.setAccessToken(fakeAccessToken);
        apiKey.setExpires(fakeExpiresIn);
    }

    @Test
    public void testSearcherBehavior() throws Exception {
        spotifySearcher = PowerMockito.spy(new SpotifySearcher());
        PowerMockito.doReturn(apiKey).when(spotifySearcher, "refreshApiKey");
        PowerMockito.doReturn(fakeUrl).when(spotifySearcher, "retrievePlaylistForGenre",
                Mockito.eq(fakeGenre), Mockito.eq(fakeAccessToken));
        TracksTemplate tracksTemplate = new TracksTemplate();
        PowerMockito.doReturn(tracksTemplate).when(spotifySearcher, "retrieveTracksFromLink",
                Mockito.eq(fakeUrl), Mockito.eq(fakeAccessToken));

        spotifySearcher.getPlaylistFrom(fakeGenre);
        PowerMockito.verifyPrivate(spotifySearcher, times(1)).invoke("retrieveCachedApiKey");
        PowerMockito.verifyPrivate(spotifySearcher, times(1)).invoke("refreshApiKey");
        PowerMockito.verifyPrivate(spotifySearcher, times(1)).
                invoke("retrievePlaylistForGenre", fakeGenre, fakeAccessToken);
        PowerMockito.verifyPrivate(spotifySearcher, times(1)).
                invoke("retrieveTracksFromLink", fakeUrl, fakeAccessToken);
    }

    @Test
    public void testSearcherBehaviorWhenHasCachedApiKey() throws Exception {
        spotifySearcher = PowerMockito.spy(new SpotifySearcher());
        PowerMockito.doReturn(fakeAccessToken).when(spotifySearcher, "retrieveCachedApiKey");
        PowerMockito.doReturn(fakeUrl).when(spotifySearcher, "retrievePlaylistForGenre",
                Mockito.eq(fakeGenre), Mockito.eq(fakeAccessToken));
        TracksTemplate tracksTemplate = new TracksTemplate();
        PowerMockito.doReturn(tracksTemplate).when(spotifySearcher, "retrieveTracksFromLink",
                Mockito.eq(fakeUrl), Mockito.eq(fakeAccessToken));

        spotifySearcher.getPlaylistFrom(fakeGenre);
        PowerMockito.verifyPrivate(spotifySearcher, times(1)).invoke("retrieveCachedApiKey");
        PowerMockito.verifyPrivate(spotifySearcher, times(0)).invoke("refreshApiKey");
        PowerMockito.verifyPrivate(spotifySearcher, times(1)).
                invoke("retrievePlaylistForGenre", fakeGenre, fakeAccessToken);
        PowerMockito.verifyPrivate(spotifySearcher, times(1)).
                invoke("retrieveTracksFromLink", fakeUrl, fakeAccessToken);
    }
}
