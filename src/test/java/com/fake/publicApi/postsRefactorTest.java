package com.fake.publicApi;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class postsRefactorTest extends BaseTest {

    @Test
    public void getPostShouldSucceed() {
        given()
                .spec(getCommon())
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void writePostShouldSucceed() {
        String json = "{\n" +
                "  \"title\": \"json-server3\",\n" +
                "  \"author\": \"typicode3\"\n" +
                "}";

        given()
                .spec(getCommon())
                .log().uri()
                .body(json)
                .when()
                .post("/posts")
                .then()
                .log().body();
    }

    @Test
    public void writePostShouldSucceed2() {
        Map<String, String> maps = new HashMap<>();
        maps.put("title", "Books");
        maps.put("author", "sharif");

        given()
                .spec(getCommon())
                .log().uri()
                .body(maps)
                .when()
                .post("/posts")
                .then()
                .log().body();
    }

    @Test
    public void putPostShouldSucceed2() {
        Map<String, String> maps = new HashMap<>();
        maps.put("title", "Books2");
        maps.put("author", "sharif2");

        given()
                .spec(getCommon())
                .log().uri()
                .body(maps)
                .when()
                .put("/posts/2")
                .then()
                .log().body();
    }


    @Test
    public void patchPostShouldSucceed2() {
        Map<String, String> maps = new HashMap<>();
        maps.put("author", "Raju");

        given()
                .spec(getCommon())
                .log().uri()
                .body(maps)
                .when()
                .patch("/posts/7")
                .then()
                .log().body();
    }

    @Test
    public void deletePostShouldSucceed2() {

        given()
                .spec(getCommon())
                .log().uri()
                .when()
                .delete("/posts/4")
                .then()
                .log().body();
    }


}
