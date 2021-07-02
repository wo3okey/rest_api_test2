package com.example.rest_api_test2.stest;

import com.example.rest_api_test2.ext.JsonConverter;
import com.example.rest_api_test2.ext.TestObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public Map<String, Object> getApi3(String url) {
        Map<String, Object> resultMap = new TreeMap<>();
        Map<String, Object> response = restTemplateApi.getObject2(url);
        try {
            List<TestObject> t = (List<TestObject>) response.get("objectList");
            resultMap.put("test", t);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return resultMap;
    }

    public Map<String, Object> getApi4(String url) {
        Map<String, Object> resultMap = new TreeMap<>();
        List<TestObject> response = restTemplateApi.getTestObject(url);

        // avg2
        int sum = 0;
        int count = 0;
        List<List<Integer>> numbers = response.stream().map(r -> r.getNumberList()).collect(Collectors.toList());
        for (List<Integer> list : numbers) {
            sum += list.stream().mapToInt(i -> i).sum();
            count += list.size();
        }
        double d = sum / count;


        List<Integer> numbers2 = response
                .stream()
                .flatMap(r -> r.getNumberList().stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        Double numbers3 = response
                .stream()
                .flatMap(r -> r.getNumberList().stream())
                .mapToInt(Integer::intValue)
                .average()
                .getAsDouble();

        log.info("data1 : {}", numbers);
        log.info("data2 : {}", numbers2);
        log.info("data3 : {}", numbers3);
        log.info("data4 : {}", d);

        resultMap.put("list", response);

        return resultMap;
    }
}
