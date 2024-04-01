package com.fake.privateApi.customer;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetAllCustomersTest extends CustomerBaseApiTest {

    @Test
    public void getAllCustomersShouldSucceed() {
        given()
                .spec(getSpecificationWithBearerToken())
                .log().uri()
                .when()
                .get("Customer/GetAllCustomers")
                .then()
                .statusCode(200)
                .log().body()
                .body("Success", equalTo(true))
                .body("Error", nullValue());
    }
}
