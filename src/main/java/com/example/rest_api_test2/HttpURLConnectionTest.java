package com.example.rest_api_test2;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class HttpURLConnectionTest {
    Gson gson = new Gson();

    public static void main(String[] args) throws Exception {
        HttpURLConnectionTest httpTest = new HttpURLConnectionTest();

        // get list
        String response = httpTest.get("http://localhost:8080/api1");
        System.out.println(httpTest.strJsonToList(response, "list"));

        // get map
        Map<String, Object> result = httpTest.strJsonToMap(response, "map");
        System.out.println(result.get("list"));
        System.out.println(result.get("string"));
        System.out.println(result.get("int"));


//        System.out.println("POST으로 데이터 가져오기");
//        httpTest.post("http://localhost:8080/test2", "data!");
    }

    /**
     * get
     * @param targetUrl
     * @throws Exception
     */
    private String get(String targetUrl) throws Exception {
        URL url = new URL(targetUrl);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close(); // print result

        String strResponse = response.toString();
        System.out.println("HTTP 응답 코드 : " + responseCode);
        System.out.println("HTTP body : " + strResponse);

        return response.toString();
    }

    /**
     * strJsonToList
     * @param strJson
     * @param key
     * @return
     */
    public List<?> strJsonToList(String strJson, String key) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObj = (JsonObject) jsonParser.parse(strJson);
        JsonElement jsonElement = jsonObj.get(key);

        Type type = new TypeToken<List<Integer>>() {}.getType();
        return gson.fromJson(jsonElement, type);
    }

    /**
     * strJsonToMap
     * @param strJson
     * @param key
     * @return
     */
    public Map<String, Object> strJsonToMap(String strJson, String key) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObj = (JsonObject) jsonParser.parse(strJson);
        JsonElement jsonElement = jsonObj.get(key);

        Type empMapType = new TypeToken<Map<String, Object>>() {}.getType();
        return gson.fromJson(jsonElement, empMapType);
    }

    /**
     * post
     * @param targetUrl
     * @param parameters
     * @throws Exception
     */
    private void post(String targetUrl, String parameters) throws Exception {
        Gson gson = new Gson();
        URL url = new URL(targetUrl);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        con.setRequestMethod("POST");
        con.setDoOutput(true); // POST 파라미터 전달을 위한 설정

        // Send post request
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(parameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close(); // print result

        System.out.println("HTTP 응답 코드 : " + responseCode);
        System.out.println("HTTP body : " + response.toString());
    }
}
