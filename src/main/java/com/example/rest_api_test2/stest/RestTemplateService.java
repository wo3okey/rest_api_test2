package com.example.rest_api_test2.stest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RestTemplateService {
    private RestTemplateApi restTemplateApi;

    @Autowired
    public RestTemplateService(RestTemplateApi restTemplateApi) {
        this.restTemplateApi = restTemplateApi;
    }

    public String get(String url) {
        return restTemplateApi.get(url);
    }

    public <T> T getObject(String url, Class<T> clazz) {
        return restTemplateApi.getObject(url, clazz);
    }
}
