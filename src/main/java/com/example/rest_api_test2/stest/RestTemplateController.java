package com.example.rest_api_test2.stest;

import com.example.rest_api_test2.ext.JsonConverter;
import com.example.rest_api_test2.ext.TestObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
        Map<String, Object> resultMap = new TreeMap<>();

        String response = restTemplateService.get(callUrl);
        TestObject response2 = restTemplateService.getObject(callUrl2, TestObject.class);
        log.info("test - {}", response);

        // get string
        resultMap.put("string", JsonConverter.getStrJsonData(response, "string"));

        // get object
        String strJson1 = JsonConverter.getStrJsonData(response, "object");
        log.info("strJson1 : {}", strJson1);
        resultMap.put("object", JsonConverter.strJsonToObject(strJson1, TestObject.class));

        // get object2
        resultMap.put("object2", response2);

        // get list
        String strJson2 = JsonConverter.getStrJsonData(response, "list");
        resultMap.put("list", JsonConverter.strJsonToList(strJson2, Integer.class));

        // get objectList
        String strJson3 = JsonConverter.getStrJsonData(response, "objectList");
        resultMap.put("objectList", JsonConverter.strJsonToList(strJson3, TestObject.class));

        // get map
        String strJson4 = JsonConverter.getStrJsonData(response, "map");
        Map<String, Object> mapResult = JsonConverter.strJsonToMap(strJson4);
        resultMap.put("map", mapResult);

        List<Object> maplist = (List<Object>) mapResult.get("listKey");
        resultMap.put("maplist", maplist);

        return resultMap;
    }
}
