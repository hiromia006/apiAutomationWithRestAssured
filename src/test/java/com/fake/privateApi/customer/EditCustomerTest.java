package com.fake.privateApi.customer;

import com.fake.privateApi.customer.pojo.CreateCustomer;
import com.thedeanda.lorem.LoremIpsum;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class EditCustomerTest extends CustomerBaseApiTest {
    @Test
    public void editCustomerShouldSucceed() {
        String email = LoremIpsum.getInstance().getEmail();
        String firstName = LoremIpsum.getInstance().getFirstName();
        String lastName = LoremIpsum.getInstance().getLastName();
        CreateCustomer customer = new CreateCustomer(12, email, firstName, lastName);
        given()
                .spec(getSpecificationWithBearerToken())
                .body(customer)
                .log().uri()
                .log().body()
                .when()
                .put("/Customer/EditCustomer")
                .then()
                .statusCode(405)
                .log().body()
                .body("Success", equalTo(true))
                .body("Data.Id", notNullValue())
                .body("Data.Email", equalTo(email))
                .body("Data.FirstName", equalTo(firstName))
                .body("Data.LastName", equalTo(lastName))
                .body("Error", nullValue());
    }
}
