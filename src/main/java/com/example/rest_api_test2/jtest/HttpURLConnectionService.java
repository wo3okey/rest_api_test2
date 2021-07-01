package com.example.rest_api_test2.jtest;

import com.example.rest_api_test2.ext.JsonConverter;
import com.example.rest_api_test2.ext.TestObject;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class HttpURLConnectionService {
    Gson gson = new Gson();

    public static void main(String[] args) throws Exception {
        HttpURLConnectionService httpTest = new HttpURLConnectionService();

        // http get
        String url = "http://localhost:8080/api1";
        String response = httpTest.get(url);
        log.info("response : {}", response);

        // get string
        String strJson = JsonConverter.getStrJsonData(response, "string");
        log.info("string : {}", strJson);

        // get object
        String strJson1 = JsonConverter.getStrJsonData(response, "object");
        TestObject testObject = JsonConverter.strJsonToObject(strJson1, TestObject.class);
        log.info("object : {}", testObject);

        // get list
        String strJson2 = JsonConverter.getStrJsonData(response, "list");
        log.info("list : {}", JsonConverter.strJsonToList(strJson2, Integer.class));

        // get objectList
        String strJson3 = JsonConverter.getStrJsonData(response, "objectList");
        log.info("objectList : {}", JsonConverter.strJsonToList(strJson3, TestObject.class));

        // get map
        String strJson4 = JsonConverter.getStrJsonData(response, "map");
        Map<String, Object> mapResult = JsonConverter.strJsonToMap(strJson4);
        log.info("map : {}", mapResult);
        List<String> maplist = (List<String>) mapResult.get("listKey");
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
}
