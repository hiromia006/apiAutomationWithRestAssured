package com.fake.publicApi;

import com.thedeanda.lorem.LoremIpsum;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DynamicPostTest extends BaseTest {
    @Test
    public void postPostShouldSucceed() {
        String title = LoremIpsum.getInstance().getTitle(3);
        String author = LoremIpsum.getInstance().getName();
        Map<String, String> maps = new HashMap<>();
        maps.put("title", title);
        maps.put("author", author);

        given()
                .spec(getCommon())
                .log().uri()
                .body(maps)
                .when()
                .post("/posts")
                .then()
                .log().body()
                .statusCode(201)
                .body("author", equalTo(author))
                .body("title", equalTo(title));
    }

    @Test
    public void putPostShouldSucceed() {
        ValidatableResponse response = given()
                .spec(getCommon())
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .log().body()
                .statusCode(200);

        int id = response.extract().jsonPath().getInt("[0].id");
        Map<String, String> maps = new HashMap<>();
        maps.put("title", LoremIpsum.getInstance().getTitle(3));
        maps.put("author", LoremIpsum.getInstance().getName());

        given()
                .spec(getCommon())
                .log().uri()
                .body(maps)
                .when()
                .put("/posts/" + id)
                .then()
                .log().body();
    }

    @Test
    public void putPostShouldSucceed2() {
        Map<String, String> maps = new HashMap<>();
        maps.put("title", LoremIpsum.getInstance().getTitle(3));
        maps.put("author", LoremIpsum.getInstance().getName());

        ValidatableResponse response = given()
                .spec(getCommon())
                .log().uri()
                .body(maps)
                .when()
                .post("/posts")
                .then()
                .log().body();

        int id = response.extract().jsonPath().getInt("id");
        Map<String, String> map2 = new HashMap<>();
        map2.put("title", LoremIpsum.getInstance().getTitle(3));
        map2.put("author", LoremIpsum.getInstance().getName());

        given()
                .spec(getCommon())
                .log().uri()
                .body(map2)
                .when()
                .put("/posts/" + id)
                .then()
                .log().body();
    }

    @Test
    public void patchPostShouldSucceed() {
        Map<String, String> maps = new HashMap<>();
        maps.put("title", LoremIpsum.getInstance().getTitle(3));
        maps.put("author", LoremIpsum.getInstance().getName());

        ValidatableResponse response = given()
                .spec(getCommon())
                .log().uri()
                .body(maps)
                .when()
                .post("/posts")
                .then()
                .log().body();

        int id = response.extract().jsonPath().getInt("id");
        Map<String, String> map2 = new HashMap<>();
        map2.put("title", LoremIpsum.getInstance().getTitle(3));

        given()
                .spec(getCommon())
                .log().uri()
                .body(map2)
                .when()
                .patch("/posts/" + id)
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void deletePostShouldSucceed() {
        Map<String, String> maps = new HashMap<>();
        maps.put("title", LoremIpsum.getInstance().getTitle(3));
        maps.put("author", LoremIpsum.getInstance().getName());

        ValidatableResponse response = given()
                .spec(getCommon())
                .log().uri()
                .body(maps)
                .when()
                .post("/posts")
                .then()
                .log().body()
                .statusCode(201);

        int id = response.extract().jsonPath().getInt("id");
        given()
                .spec(getCommon())
                .log().uri()
                .when()
                .delete("/posts/" + id)
                .then()
                .log().body()
                .statusCode(200);
    }
}
