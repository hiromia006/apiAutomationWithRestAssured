package com.fake.privateApi.customer;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class GetCustomerByIdTest extends CustomerBaseApiTest {

    @Test
    public void getCustomerByIdShouldSucceed() {
        int id = given()
                .spec(getSpecificationWithBearerToken())
                .when()
                .get("Customer/GetAllCustomers")
                .then()
                .statusCode(200)
                .body("Success", equalTo(true))
                .body("Error", nullValue())
                .extract().jsonPath().getInt("Data[1].Id");

        given()
                .spec(getSpecificationWithBearerToken())
                .log().uri()
                .when()
                .get("/Customer/GetCustomerById?id=" + id)
                .then()
                .statusCode(200)
                .log().body()
                .body("Success", equalTo(true))
                .body("Data.Id", equalTo(id))
                .body("Error", nullValue());
    }
}
