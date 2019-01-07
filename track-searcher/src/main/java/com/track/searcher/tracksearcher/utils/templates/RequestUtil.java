package com.track.searcher.tracksearcher.utils.templates;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RequestUtil<T> {

    public static <T> T getResponse(HttpHeaders headers, HttpMethod method, String url, Class<T> responseType) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<T> response = restTemplate.exchange(url, method, entity, responseType);

        return response.getBody();
    }
}
