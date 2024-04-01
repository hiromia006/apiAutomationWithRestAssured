package com.fake.privateApi.customer;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetBearerTokenTest extends CustomerBaseApiTest {

    @Test
    public void getBearerTokenShouldSucceed() {
        given()
                .spec(getSpecification())
                .log().uri()
                .when()
                .get("/Auth/GetBearerToken")
                .then()
                .statusCode(200)
                .log().body()
                .body("Success", equalTo(true));
    }
}
