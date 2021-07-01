package com.example.rest_api_test2.jtest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class HttpURLConnectionTest {
    Gson gson = new Gson();

    public static void main(String[] args) throws Exception {
        HttpURLConnectionTest httpTest = new HttpURLConnectionTest();
        String response = httpTest.get("http://localhost:8080/api1");
        log.info("response : {}", response);

        // get string
        String strJson = httpTest.getStrJsonData(response, "string");
        log.info("string : {}", strJson);

        // get object
        String strJson1 = httpTest.getStrJsonData(response, "object");
        TestObject testObject = httpTest.strJsonToObject(strJson1, TestObject.class);
        log.info("object : {}", testObject);

        // get list
        String strJson2 = httpTest.getStrJsonData(response, "list");
        log.info("list : {}", httpTest.strJsonToList(strJson2, Integer.class));

        // get objectList
        String strJson3 = httpTest.getStrJsonData(response, "objectList");
        log.info("objectList : {}", httpTest.strJsonToList(strJson3, TestObject.class));

        // get map
        String strJson4 = httpTest.getStrJsonData(response, "map");
        Map<String, Object> mapResult = httpTest.strJsonToMap(strJson4);
        log.info("map : {}", mapResult);
        List<String> maplist = (List<String>)mapResult.get("listKey");
        log.info("mapList : {}", maplist.stream().map(e -> Integer.parseInt(e) + 1).collect(Collectors.toList()));
    }

    /**
     * get
     *
     * @param targetUrl
     * @throws Exception
     */
    private String get(String targetUrl) throws Exception {
        URL url = new URL(targetUrl);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close(); // print result

        return response.toString();
    }

    /**
     * getStrJsonData
     *
     * @param strJson
     * @param key
     * @return
     */
    public String getStrJsonData(String strJson, String key) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObj = (JsonObject) jsonParser.parse(strJson);
        JsonElement jsonElement = jsonObj.get(key);

        return jsonElement.toString();
    }

    /**
     * strJsonToList
     *
     * @param jsonData
     * @return
     */
    public <T> List<T> strJsonToList(String jsonData, Class<T> clazz) {
        Type type = TypeToken.getParameterized(List.class, clazz).getType();
        return gson.fromJson(jsonData, type);
    }

    /**
     * strJsonToMap
     *
     * @param jsonData
     * @return
     */
    public <K, V> Map<K, V> strJsonToMap(String jsonData) {
        Type empMapType = new TypeToken<Map<K, V>>() {
        }.getType();
        return gson.fromJson(jsonData, empMapType);
    }

    /**
     * strJsonToObject
     *
     * @param jsonData
     * @return
     */
    public <T> T strJsonToObject(String jsonData, Class<T> clazz) {
        return gson.fromJson(jsonData, clazz);
    }
}
