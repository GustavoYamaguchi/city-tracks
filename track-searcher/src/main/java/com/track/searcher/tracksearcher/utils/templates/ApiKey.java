package com.track.searcher.tracksearcher.utils.templates;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.track.searcher.tracksearcher.constants.JsonTemplates;

public class ApiKey {

    @JsonProperty(JsonTemplates.ACCESS_TOKEN_KEY)
    private String accessToken;

    @JsonProperty(JsonTemplates.EXPIRES_IN_KEY)
    private Long expires;

    public String getAccessToken() {
        return accessToken;
    }

    public Long getExpires() {
        return expires;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }
}
