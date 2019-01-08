package com.track.searcher.tracksearcher.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RequestUtil {

    public static <T, Y> ResponseEntity<T> getResponse(HttpHeaders headers, HttpMethod method, Y body, String url, Class<T> responseType) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Y> entity = new HttpEntity<>(body, headers);
        ResponseEntity<T> response = restTemplate.exchange(url, method, entity, responseType);

        return response;
    }
}
