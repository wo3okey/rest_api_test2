package com.example.rest_api_test2.stest;

import com.example.rest_api_test2.ext.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
public class RestTemplateController {
    private final RestTemplateService restTemplateService;
    private final String callUrl = "http://localhost:8080/api1";

    @Autowired
    public RestTemplateController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/api1")
    public Map<String, Object> getApi1() {
        Map<String, Object> resultMap = new TreeMap<>();

        String response = restTemplateService.get(callUrl);

        // get string
        resultMap.put("string", JsonConverter.getStrJsonData(response, "string"));

        // get object
        resultMap.put("object", JsonConverter.getStrJsonData(response, "object"));

        // get list
        resultMap.put("list", JsonConverter.getStrJsonData(response, "list"));

        // get objectList
        resultMap.put("objectList", JsonConverter.getStrJsonData(response, "objectList"));

        // get map
        Map<String, Object> mapResult = JsonConverter.strJsonToMap(JsonConverter.getStrJsonData(response, "map"));
        resultMap.put("map", mapResult);

        List<Object> maplist = (List<Object>) mapResult.get("listKey");
        resultMap.put("maplist", maplist);

        return resultMap;
    }
}
