package com.fake.privateApi;

import com.fake.privateApi.pojo.Registration;
import com.thedeanda.lorem.LoremIpsum;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RegistrationTest extends BasePrivateApi {

    @Test
    public void registrationShouldSucceed() {
        String name=LoremIpsum.getInstance().getName();
        String email=LoremIpsum.getInstance().getEmail();
        Registration registration=new Registration(name, email, 123456);
        given()
                .spec(getBaseSpec())
                .body(registration)
                .log().uri()
                .log().body()
                .when()
                .post("/authaccount/registration")
                .then()
                .statusCode(200)
                .log().body()
                .body("message", equalTo("success"))
                .body("data.Email", equalTo(email))
                .body("data.Name", equalTo(name));
    }

    @Test
    public void registrationWithExitingEmailShouldFail() {
        Registration registration=new Registration("Developer", "Developer5@gmail.com", 123456);
        given()
                .spec(getBaseSpec())
                .body(registration)
                .log().uri()
                .log().body()
                .when()
                .post("/authaccount/registration")
                .then()
                .statusCode(200)
                .log().body()
                .body("message", equalTo("The email address you have entered is already registered"));
    }
}
