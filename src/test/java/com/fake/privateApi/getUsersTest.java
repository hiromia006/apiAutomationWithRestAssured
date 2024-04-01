package com.fake.privateApi;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class getUsersTest extends BasePrivateApi {

    @Test
    public void getUserShouldSucceed() {
        given()
                .spec(getBaseWithAuthSpec())
                .log().uri()
                .when()
                .get("/users?page=1")
                .then()
                .statusCode(200)
                .log().body();
    }
}
