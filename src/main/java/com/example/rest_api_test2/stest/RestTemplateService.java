package com.example.rest_api_test2.stest;

import com.example.rest_api_test2.ext.JsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class RestTemplateService {
    private RestTemplateApi restTemplateApi;

    @Autowired
    public RestTemplateService(RestTemplateApi restTemplateApi) {
        this.restTemplateApi = restTemplateApi;
    }

    public String get(String url) {
        ObjectMapper objectMapper = new ObjectMapper();
        String strJson = "";
        try {
            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON); // tojson
//            headers.set("Authorization", appKey); // appKey
//            HttpEntity entity = new HttpEntity("parameters", headers);
//            URI url= URI.create(url);
            strJson = objectMapper.writeValueAsString(restTemplateApi.get(url, headers));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Object result = JsonConverter.strJsonToMap(strJson).get("body");
        log.info("test - {}", result);
        return result.toString();
    }
}
