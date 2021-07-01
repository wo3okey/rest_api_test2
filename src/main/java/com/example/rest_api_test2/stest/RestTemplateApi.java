package com.example.rest_api_test2.stest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@Slf4j
public class RestTemplateApi {
    private RestTemplate restTemplate;

    @Autowired
    public RestTemplateApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T, K, V> ResponseEntity<Map<K, V>> get(String url, HttpHeaders httpHeaders) {
        return callApiEndpoint(url, HttpMethod.GET, httpHeaders, null, (Class<T>) Object.class);
    }

    public <T, K, V> ResponseEntity<Map<K, V>> get(String url, HttpHeaders httpHeaders, Class<T> clazz) {
        return callApiEndpoint(url, HttpMethod.GET, httpHeaders, null, clazz);
    }

    public <T, K, V> ResponseEntity<Map<K, V>> post(String url, HttpHeaders httpHeaders, Object body) {
        return callApiEndpoint(url, HttpMethod.POST, httpHeaders, body, (Class<T>) Object.class);
    }

    public <T, K, V> ResponseEntity<Map<K, V>> post(String url, HttpHeaders httpHeaders, Object body, Class<T> clazz) {
        return callApiEndpoint(url, HttpMethod.POST, httpHeaders, body, clazz);
    }

    private <T, K, V> ResponseEntity<Map<K, V>> callApiEndpoint(String url, HttpMethod httpMethod, HttpHeaders httpHeaders, Object body, Class<T> clazz) {
        ParameterizedTypeReference<Map<K, V>> responseType = new ParameterizedTypeReference<Map<K, V>>() {};
        return restTemplate.exchange(url, httpMethod, new HttpEntity<>(body, httpHeaders), responseType);
    }
}
