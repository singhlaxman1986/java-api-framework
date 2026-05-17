package com.api.automation.client;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

public class ApiClient {
    private static final Logger logger = LoggerFactory.getLogger(ApiClient.class);
    private final String baseUrl;

    public ApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Response get(String path) {
        String url = baseUrl + path;
        logger.debug("Sending GET request to {}", url);

        Response resp = given()
                .baseUri(baseUrl)
                .when()
                .get(path)
                .then()
                .extract()
                .response();

        logger.info("Received response: {} for {}", resp.statusCode(), url);
        logger.debug("Response headers: {}", resp.getHeaders().toString());
        logger.debug("Response body: {}", resp.asString());

        // Attach response details to Allure report
        try {
            Allure.addAttachment("Response Status", String.valueOf(resp.statusCode()));
            Allure.addAttachment("Response Headers", resp.getHeaders().toString());
            Allure.addAttachment("Response Body", resp.asString());
        } catch (Exception ignored) {
            logger.warn("Failed to add Allure attachments", ignored);
        }

        return resp;
    }
}
