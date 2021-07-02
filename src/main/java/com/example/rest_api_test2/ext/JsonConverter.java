package com.example.rest_api_test2.ext;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Slf4j
public class JsonConverter {
    /**
     * getStrJsonData
     *
     * @param strJson
     * @param key
     * @return
     */
    public static String getStrJsonData(String strJson, String key) {
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
    public static <T> List<T> strJsonToList(String jsonData, Class<T> clazz) {
        Gson gson = new Gson();

        Type type = TypeToken.getParameterized(List.class, clazz).getType();
        return gson.fromJson(jsonData, type);
    }

    /**
     * strJsonToMap
     *
     * @param jsonData
     * @return
     */
    public static <K, V> Map<K, V> strJsonToMap(String jsonData) {
        Gson gson = new Gson();

        Type type = new TypeToken<Map<K, V>>() {}.getType();
        return gson.fromJson(jsonData, type);
    }

    /**
     * strJsonToObject
     *
     * @param jsonData
     * @return
     */
    public static <T> T strJsonToObject(String jsonData, Class<T> clazz) {
        Gson gson = new Gson();

        return gson.fromJson(jsonData, clazz);
    }
}
