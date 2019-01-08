package com.track.searcher.tracksearcher.utils;

import com.google.gson.Gson;
import com.track.searcher.tracksearcher.model.Track;
import com.track.searcher.tracksearcher.utils.templates.Response;
import com.track.searcher.tracksearcher.utils.templates.TracksTemplate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UnmarshalTracksTest {

    @Test
    public void testUnmarshalTracks() {
        TracksTemplate tracksTemplate = new Gson().fromJson(Response.VALID_TRACKS_TEMPLATE_SPOTIFY, TracksTemplate.class);
        List<Track> tracks = UnmarshalTracks.unmarshalTracks(tracksTemplate);
        List<String> expectedArtistsFirstSong = new ArrayList<>();
        expectedArtistsFirstSong.add("Brenda Lee");
        String expectedNameFirstSong = "Rockin' Around The Christmas Tree";
        String expectedAlbumNameFirstSong = "Christmas Classics Featuring Rudolph The Red-Nosed Reindeer, " +
                "Frosty The Snowman & The Little Drummer Boy";

        assertEquals(5, tracks.size());
        assertEquals(expectedArtistsFirstSong, tracks.get(0).getArtists());
        assertEquals(expectedNameFirstSong, tracks.get(0).getName());
        assertEquals(expectedAlbumNameFirstSong, tracks.get(0).getAlbumName());

        List<String> expectedArtistsSecondSong = new ArrayList<>();
        expectedArtistsSecondSong.add("Perry Como");
        expectedArtistsSecondSong.add("The Fontane Sisters");
        expectedArtistsSecondSong.add("Mitchell Ayres & His Orchestra");
        String expectedNameSecondSong = "It's Beginning to Look a Lot Like Christmas (with Mitchell Ayres & His Orchestra)";
        String expectedAlbumNameSecondSong = "Greatest Christmas Songs";

        assertEquals(expectedArtistsSecondSong, tracks.get(1).getArtists());
        assertEquals(expectedNameSecondSong, tracks.get(1).getName());
        assertEquals(expectedAlbumNameSecondSong, tracks.get(1).getAlbumName());

        assertEquals(5, tracks.stream().distinct().count());
    }
}
