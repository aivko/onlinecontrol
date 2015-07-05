package com.vizaco.onlinecontrol.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class OAuthUtil {

    public String sendHttpRequest(String methodName, String url, String[] names, String[] values) throws IOException {

        HttpMethod method = getHttpMethod(methodName, url, names, values);
        return getBodyResult(method);

    }

    public HttpMethod getHttpMethod(String methodName, String url, String[] names, String[] values) {

        if (names == null || values == null){
            return null;
        }

        if (names.length != values.length) {
            return null;
        }
        if (!(methodName.equalsIgnoreCase("GET") | methodName.equalsIgnoreCase("POST"))) {
            return null;
        }

        HttpMethod method;
        if ("GET".equalsIgnoreCase(methodName)) {
            StringBuilder stringBuilder = new StringBuilder();
            ArrayList parameters = new ArrayList();
            for (int i = 0; i < names.length; i++) {
                stringBuilder.append(names[i] + "=" + values[i]);
                if ((names.length - 1) > i) {
                    stringBuilder.append("&");
                }
            }
            method = new GetMethod(url + "?" + stringBuilder.toString());
        } else {
            method = new PostMethod(url);
            for (int i = 0; i < names.length; i++) {
                ((PostMethod) method).addParameter(names[i], values[i]);
            }
            method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        }
        return method;
    }

    public String getBodyResult(HttpMethod method) throws IOException {
        if (method == null){
            return null;
        }
        new HttpClient().executeMethod(method);
        return method.getResponseBodyAsString();
    }

    public Map<String, String> parseURLQuery(String query) throws UnsupportedEncodingException {

        Map<String, String> result = new HashMap<String, String>();
        String params[] = query.split("&");
        for (String param : params) {
            String temp[] = param.split("=");
            result.put(temp[0], URLDecoder.decode(temp[1], "utf-8"));
        }
        return result;

    }

}
