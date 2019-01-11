package com.track.searcher.tracksearcher.utils.templates.trackresponse;

import com.track.searcher.tracksearcher.model.Track;

import java.util.ArrayList;
import java.util.List;

public class SpotifyTemplate implements TracksTemplate {

    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    @Override
    public List<Track> getNormalizedTracks() {
        List<Track> tracks = new ArrayList<>();
        if (items != null && items.size() > 0) {
            items.stream().forEach(t -> {
                Track track = new Track();
                track.setName(t.getTrack().getName());
                track.setArtists(getAllArtists(t));
                track.setAlbumName(t.getTrack().getAlbum().getName());
                tracks.add(track);
            });
        }
        return tracks;
    }

    private static List<String> getAllArtists(Item t) {
        List<String> artists = new ArrayList<>();
        t.getTrack().getArtists().stream().forEach(a -> artists.add(a.getName()));
        return artists;
    }

    public static class Item {
        private Track track;

        public Track getTrack() {
            return track;
        }

        public static class Track {
            private Album album;
            private String name;
            private List<Artists> artists;

            public Album getAlbum() {
                return album;
            }

            public String getName() {
                return name;
            }

            public List<Artists> getArtists() {
                return artists;
            }

            public static class Artists {
                private String name;

                public String getName() {
                    return name;
                }
            }

            public static class Album {
                private String name;

                public String getName() {
                    return name;
                }
            }
        }
    }
}
