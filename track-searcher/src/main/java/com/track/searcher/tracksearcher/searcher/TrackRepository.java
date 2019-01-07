package com.track.searcher.tracksearcher.searcher;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TrackRepository {
    SPOTIFY("spotify"),
    DEEZER("deezer"),
    YOUTUBE("youtube");

    private String repository;

    TrackRepository(String repository) {
        this.repository = repository;
    }

    @JsonValue
    public String getRepository() {
        return repository;
    }

    public static TrackRepository fromText(String repository) {
        for (TrackRepository t : TrackRepository.values()) {
            if (t.getRepository().equalsIgnoreCase(repository)) {
                return t;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return this.repository;
    }
}
