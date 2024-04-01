package com.fake.publicApi;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseTest {
    public RequestSpecification getCommon() {
        return new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(3000)
                .addHeader("Content-Type", "application/json")
                .build();
    }
}
