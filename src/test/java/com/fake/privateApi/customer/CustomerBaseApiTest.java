package com.fake.privateApi.customer;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CustomerBaseApiTest {
    public RequestSpecification getSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri("https://www.quickpickdeal.com")
                .setBasePath("/api")
                .addHeader("Content-Type", "application/json")
                .build();
    }

    public RequestSpecification getSpecificationWithBearerToken() {
        return new RequestSpecBuilder()
                .addRequestSpecification(getSpecification())
                .addHeader("Authorization", "Bearer " + getBearerToken())
                .build();
    }

    public String getBearerToken() {
        return given()
                .spec(getSpecification())
//                .log().uri()
                .when()
                .get("/Auth/GetBearerToken")
                .then()
                .statusCode(200)
//                .log().body()
                .body("Success", equalTo(true))
                .extract().jsonPath().getString("Data.Token");
    }
}
