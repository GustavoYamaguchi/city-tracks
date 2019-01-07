package com.track.searcher.tracksearcher.utils.templates;

import java.util.List;

public class Playlist {

    private Playlists playlists;

    public Playlists getPlaylists() {
        return playlists;
    }

    public static class Playlists {
        private List<Item> items;

        public List<Item> getItems() {
            return items;
        }

        public static class Item {
            private Tracks tracks;

            public Tracks getTracks() {
                return tracks;
            }

            public static class Tracks {
                private String href;

                public String getHref() {
                    return href;
                }
            }
        }
    }
}
