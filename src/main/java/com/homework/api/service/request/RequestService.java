package com.homework.api.service.request;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestService {

    @Value(("${financial.house.base.url}"))
    private String baseUrl;

    private final RestTemplate restTemplate;

    public RequestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> ResponseEntity<T> postRequest(String url, HttpEntity<?> entity, Class<T> responseType) {
        return restTemplate.postForEntity(baseUrl+url, entity, responseType);
    }

    public HttpHeaders generateDefaultHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

}
