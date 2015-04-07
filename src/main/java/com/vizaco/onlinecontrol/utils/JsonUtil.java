package com.vizaco.onlinecontrol.utils;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonUtil {

    public String getJsonElement(String tokenRequest, String key) throws IOException {

        if (key == null || tokenRequest == null){
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(tokenRequest);
        String value = jsonNode.get(key).asText();
        return value;

    }

    public Map<String, Object> getMapFromJsonElement(String tokenRequest) throws IOException {

        if (tokenRequest == null){
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        Map result = mapper.readValue(tokenRequest, HashMap.class);
        return result;

    }

}
