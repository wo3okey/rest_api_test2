package com.example.rest_api_test2.stest;

import com.example.rest_api_test2.ext.JsonConverter;
import com.example.rest_api_test2.ext.TestObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@Slf4j
public class RestTemplateService {
    private RestTemplateApi restTemplateApi;

    @Autowired
    public RestTemplateService(RestTemplateApi restTemplateApi) {
        this.restTemplateApi = restTemplateApi;
    }

    /**
     * getApi1
     *
     * @param url
     * @return
     */
    public Map<String, Object> getApi1(String url) {
        Map<String, Object> resultMap = new TreeMap<>();

        String response = restTemplateApi.get(url);
        log.info("getApi1 - {}", response);

        // get string
        resultMap.put("string", JsonConverter.getStrJsonData(response, "string"));

        // get object
        String strJson1 = JsonConverter.getStrJsonData(response, "object");
        log.info("strJson1 : {}", strJson1);
        resultMap.put("object", JsonConverter.strJsonToObject(strJson1, TestObject.class));

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

    /**
     * getApi2
     *
     * @param url
     * @return
     */
    public Map<String, Object> getApi2(String url) {
        Map<String, Object> resultMap = new TreeMap<>();

        String response = restTemplateApi.get(url);
        log.info("getApi2 - {}", response);
        TestObject response2 = restTemplateApi.getObject(url, TestObject.class);

        // get object2
        resultMap.put("object2", response2);

        return resultMap;
    }
}
