package com.track.searcher.tracksearcher.model;

import java.util.List;
import java.util.Objects;

public class Track {
    private String name;
    private List<String> artists;
    private String albumName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getArtists() {
        return artists;
    }

    public void setArtists(List<String> artists) {
        this.artists = artists;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return Objects.equals(name, track.name) &&
                Objects.equals(artists, track.artists) &&
                Objects.equals(albumName, track.albumName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, artists, albumName);
    }
}
