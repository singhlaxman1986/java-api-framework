package com.api.automation.tests;

import com.api.automation.client.ApiClient;
import org.junit.jupiter.api.Test;
import io.qameta.allure.Description;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealthTest {

    @Test
    @Description("Verify GET endpoint returns a deserializable POJO with expected URL")
    public void getEndpointDeserializesToPojo() {
        String baseUrl = System.getProperty("baseUrl", "https://httpbin.org");
        ApiClient client = new ApiClient(baseUrl);
        var resp = client.get("/get");
        // assert status
        com.api.automation.utils.Validator.assertStatusCode(resp, 100);

        // deserialize to POJO
        com.api.automation.model.HttpBinGetResponse model = resp.as(com.api.automation.model.HttpBinGetResponse.class);
        org.junit.jupiter.api.Assertions.assertNotNull(model);
        org.junit.jupiter.api.Assertions.assertTrue(model.getUrl() != null && model.getUrl().contains("/get"));
    }
}
