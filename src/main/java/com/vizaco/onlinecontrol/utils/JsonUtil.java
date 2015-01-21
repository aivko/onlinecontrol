package com.vizaco.onlinecontrol.utils;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by i.vartanian on 17.01.2015.
 */
public class JsonUtil {

    public static String getJsonElement(String tokenRequest, String key) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(tokenRequest);
        String value = jsonNode.get(key).asText();
        return value;

    }

    public static Map<String, Object> getMapFromJsonElement(String tokenRequest) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Map result = mapper.readValue(tokenRequest, HashMap.class);
        return result;

    }

}
