package com.api.automation.utils;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Validator {
    private static final Logger logger = LoggerFactory.getLogger(Validator.class);
    private Validator() {}

    public static void assertStatusCode(Response resp, int expected) {
        int actual = resp.statusCode();
        logger.debug("Asserting status code, expected={}, actual={}", expected, actual);
        Assertions.assertEquals(expected, actual, "Unexpected status code");
    }

    public static void assertHeaderEquals(Response resp, String headerName, String expected) {
        String actual = resp.getHeader(headerName);
        logger.debug("Asserting header '{}', expected='{}', actual='{}'", headerName, expected, actual);
        Assertions.assertEquals(expected, actual, "Header mismatch: " + headerName);
    }

    public static void assertJsonFieldEquals(Response resp, String jsonPath, Object expected) {
        Object actual = resp.jsonPath().get(jsonPath);
        logger.debug("Asserting JSON field '{}', expected='{}', actual='{}'", jsonPath, expected, actual);
        Assertions.assertEquals(expected, actual, "JSON field mismatch: " + jsonPath);
    }

    public static void assertBodyContains(Response resp, String substring) {
        String body = resp.asString();
        logger.debug("Asserting body contains '{}', body='{}'", substring, body);
        Assertions.assertTrue(body.contains(substring), "Response body does not contain: " + substring);
    }

    public static void assertJsonEquals(Response resp, String expectedJson) {
        JsonElement actualElem = JsonParser.parseString(resp.asString());
        JsonElement expectedElem = JsonParser.parseString(expectedJson);
        logger.debug("Asserting full JSON equality");
        Assertions.assertEquals(expectedElem, actualElem, "JSON bodies are not equal");
    }
}
