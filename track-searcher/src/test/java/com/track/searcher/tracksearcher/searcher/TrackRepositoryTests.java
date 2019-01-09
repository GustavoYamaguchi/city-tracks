package com.track.searcher.tracksearcher.searcher;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TrackRepositoryTests {

    @Test
    public void testFromText() {
        String spotifyRepoName = "spotify";
        String deezerRepoName = "deezer";
        String youtubeRepoName = "youtube";
        String nonExistentRepoName = "abcd";

        assertEquals(TrackRepository.SPOTIFY, TrackRepository.fromText(spotifyRepoName));
        assertEquals(TrackRepository.DEEZER, TrackRepository.fromText(deezerRepoName));
        assertEquals(TrackRepository.YOUTUBE, TrackRepository.fromText(youtubeRepoName));
        try {
            TrackRepository.fromText(nonExistentRepoName);
            fail();
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void testFromTextIgonringCase() {
        String spotifyRepoName = "Spotify";
        String deezerRepoName = "Deezer";
        String youtubeRepoName = "Youtube";

        assertEquals(TrackRepository.SPOTIFY, TrackRepository.fromText(spotifyRepoName));
        assertEquals(TrackRepository.DEEZER, TrackRepository.fromText(deezerRepoName));
        assertEquals(TrackRepository.YOUTUBE, TrackRepository.fromText(youtubeRepoName));

        spotifyRepoName = "spotifY";
        deezerRepoName = "deezeR";
        youtubeRepoName = "YoutubE";

        assertEquals(TrackRepository.SPOTIFY, TrackRepository.fromText(spotifyRepoName));
        assertEquals(TrackRepository.DEEZER, TrackRepository.fromText(deezerRepoName));
        assertEquals(TrackRepository.YOUTUBE, TrackRepository.fromText(youtubeRepoName));

        spotifyRepoName = "sPotifY";
        deezerRepoName = "DEEZER";
        youtubeRepoName = "YoUTUbE";

        assertEquals(TrackRepository.SPOTIFY, TrackRepository.fromText(spotifyRepoName));
        assertEquals(TrackRepository.DEEZER, TrackRepository.fromText(deezerRepoName));
        assertEquals(TrackRepository.YOUTUBE, TrackRepository.fromText(youtubeRepoName));
    }
}
