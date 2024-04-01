package com.fake.privateApi;

import com.fake.privateApi.BasePrivateApi;
import com.fake.privateApi.pojo.Registration;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginTest extends BasePrivateApi {
    @Test
    public void loginShouldSucceed(){
        given()
                .spec(getBaseSpec())
                .body(new Registration("dusty.snow@example.com", 123456))
                .log().uri()
                .log().body()
                .when()
                .post("/authaccount/login")
                .then()
                .statusCode(200)
                .log().body();

    }
}
