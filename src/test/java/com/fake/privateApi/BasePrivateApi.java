package com.fake.privateApi;

import com.fake.privateApi.pojo.Registration;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BasePrivateApi {

    public RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("http://restapi.adequateshop.com")
                .setBasePath("/api")
                .addHeader("Content-Type", "application/json")
                .build();
    }

    public RequestSpecification getBaseWithAuthSpec() {
        return new RequestSpecBuilder()
                .addRequestSpecification(getBaseSpec())
                .addHeader("Authorization", "Bearer " + getToken())
                .build();
    }

    public String getToken(){
      return   given()
                .spec(getBaseSpec())
                .body(new Registration("dusty.snow@example.com", 123456))
                .log().uri()
                .log().body()
                .when()
                .post("/authaccount/login")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getString("data.Token");
    }
}
