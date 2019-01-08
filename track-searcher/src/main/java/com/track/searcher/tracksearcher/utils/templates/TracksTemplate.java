package com.track.searcher.tracksearcher.utils.templates;

import java.util.List;

public class TracksTemplate {
    private List<Item> items;

    public List<Item> getItems() {
        return items;
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
