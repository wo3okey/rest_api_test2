package com.example.rest_api_test2.stest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class RestTemplateApi {
    private final RestTemplate restTemplate;

    @Autowired
    public RestTemplateApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String get(String url) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("one", "test1");

        ResponseEntity<String> response = restTemplate.getForEntity(url + "/{one}", String.class, params);
        log.info("header : {}", response.getHeaders());
        log.info("status : {}", response.getStatusCode());
        log.info("body : {}", response.getBody());
        return response.getBody();
    }

    public <T> T getObject(String url, Class<T> clazz) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("two", "test2");

        T response = restTemplate.getForObject(url + "/{two}", clazz, params);
        return response;
    }
}
