package com.example.rest_api_test2.stest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class RestTemplateController {
    private final RestTemplateService restTemplateService;
    private final String callUrl = "http://localhost:8080/api1";
    private final String callUrl2 = "http://localhost:8080/api2";

    @Autowired
    public RestTemplateController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/api1")
    public Map<String, Object> getApi1() {
        return restTemplateService.getApi1(callUrl);
    }

    @GetMapping("/api2")
    public Map<String, Object> getApi2() {
        return restTemplateService.getApi2(callUrl2);
    }
}
