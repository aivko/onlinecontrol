package com.vizaco.onlinecontrol.utils;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * Created by super on 3/10/15.
 */
public class OAuthUtilTest {

    private OAuthUtil spyOAuthUtil;
    private OAuthUtil oAuthUtil;

    @Before
    public void setUp() throws Exception {
        spyOAuthUtil = spy(new OAuthUtil());
        oAuthUtil = new OAuthUtil();
    }

    @Test
    public void getHttpMethodGetWith1KeyTest() throws IOException {
        HttpMethod expected = new GetMethod("https://www.google.com?key1=value1");
        HttpMethod actual = spyOAuthUtil.getHttpMethod("GET", "https://www.google.com", new String[]{"key1"}, new String[]{"value1"});
        assertEquals(expected.getQueryString(), actual.getQueryString());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    public void getHttpMethodGetWith2KeyTest() throws IOException {
        HttpMethod expected = new GetMethod("https://www.google.com?key1=value1&key2=value2");
        HttpMethod actual = spyOAuthUtil.getHttpMethod("GET", "https://www.google.com", new String[]{"key1", "key2"}, new String[]{"value1", "value2"});
        assertEquals(expected.getQueryString(), actual.getQueryString());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    public void getHttpMethodGetWithDifferentKeyValueTest() throws IOException {
        HttpMethod expected = null;
        HttpMethod actual = spyOAuthUtil.getHttpMethod("GET", "https://www.google.com", new String[]{"key1"}, new String[]{"value1", "value2"});
        assertEquals(expected, actual);
    }

    @Test
    public void getHttpMethodGetWithUnknowMethodTest() throws IOException {
        HttpMethod expected = null;
        HttpMethod actual = spyOAuthUtil.getHttpMethod("ABC", "https://www.google.com", new String[]{"key1"}, new String[]{"value1"});
        assertEquals(expected, actual);
    }

    @Test
    public void getHttpMethodGetWithNullValueTest() throws IOException {
        HttpMethod expected = null;
        HttpMethod actual = spyOAuthUtil.getHttpMethod("ABC", "https://www.google.com", new String[]{"key1"}, null);
        assertEquals(expected, actual);
    }

    @Test
    public void getHttpMethodGetWithNullKeyTest() throws IOException {
        HttpMethod expected = null;
        HttpMethod actual = spyOAuthUtil.getHttpMethod("ABC", "https://www.google.com", null, new String[]{"value1"});
        assertEquals(expected, actual);
    }

    @Test
    public void getHttpMethodPostWith1KeyTest() throws IOException {
        HttpMethod expected = new PostMethod("https://www.google.com?key1=value1");
        PostMethod actual = (PostMethod) spyOAuthUtil.getHttpMethod("POST", "https://www.google.com", new String[]{"key1"}, new String[]{"value1"});

        assertEquals("value1", actual.getParameter("key1").getValue());
        assertEquals("key1", actual.getParameter("key1").getName());

        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    public void getHttpMethodPostWith2KeyTest() throws IOException {
        PostMethod expected = new PostMethod("https://www.google.com?key1=value1&key2=value2");
        PostMethod actual = (PostMethod) spyOAuthUtil.getHttpMethod("POST", "https://www.google.com", new String[]{"key1", "key2"}, new String[]{"value1", "value2"});
        assertEquals("value1", actual.getParameter("key1").getValue());
        assertEquals("key1", actual.getParameter("key1").getName());
        assertEquals("value2", actual.getParameter("key2").getValue());
        assertEquals("key2", actual.getParameter("key2").getName());
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    public void getHttpMethodPostWithDifferentKeyValueTest() throws IOException {
        HttpMethod expected = null;
        HttpMethod actual = spyOAuthUtil.getHttpMethod("POST", "https://www.google.com", new String[]{"key1", "key2"}, new String[]{"value1"});
        assertEquals(expected, actual);
    }

    @Test
    public void getBodyResultWithNullArgumentTest() throws IOException {
        String expected = null;
        String actual = spyOAuthUtil.getBodyResult(null);
        assertEquals(expected, actual);
    }

    @Test
    public void sendHttpRequestGetWith1KeyTest() throws IOException {
        String expected = "text body";
        doReturn("text body").when(spyOAuthUtil).getBodyResult(any(HttpMethod.class));
        doReturn(null).when(spyOAuthUtil).getHttpMethod(anyString(), anyString(), any(String[].class), any(String[].class));
        String actual = spyOAuthUtil.sendHttpRequest("GET", "https://www.google.com", new String[]{"key1"}, new String[]{"value1"});
        assertEquals(expected, actual);
    }

    @Test
    public void sendHttpRequestGetWith2KeyTest() throws IOException {
        String expected = "text body";
        doReturn("text body").when(spyOAuthUtil).getBodyResult(any(HttpMethod.class));
        doReturn(null).when(spyOAuthUtil).getHttpMethod(anyString(), anyString(), any(String[].class), any(String[].class));
        String actual = spyOAuthUtil.sendHttpRequest("GET", "https://www.google.com", new String[]{"key1", "key2"}, new String[]{"value1", "value2"});
        assertEquals(expected, actual);
    }

    @Test
    public void parseURLQueryWithQueryString1KeyValueTest() throws IOException {
        String query = "key1=value1";
        Map<String, String> actual = oAuthUtil.parseURLQuery(query);
        assertEquals(1, actual.size());
        assertEquals("value1", actual.get("key1"));
    }

    @Test
    public void parseURLQueryWithQueryString2KeyValueTest() throws IOException {
        String query = "key1=value1&key2=value2";
        Map<String, String> actual = oAuthUtil.parseURLQuery(query);
        assertEquals(2, actual.size());
        assertEquals("value1", actual.get("key1"));
        assertEquals("value2", actual.get("key2"));
    }

    @Test
    public void parseURLQueryWithQueryString2KeyDecodeValueTest() throws IOException {
        String value1 = URLEncoder.encode("value1 value1", "utf-8");
        String value2 = URLEncoder.encode("value2 value2", "utf-8");
        String query = "key1=" + value1 + "&key2=" + value2;
        Map<String, String> actual = oAuthUtil.parseURLQuery(query);
        assertEquals(2, actual.size());
        assertEquals("value1 value1", actual.get("key1"));
        assertEquals("value2 value2", actual.get("key2"));
    }

}