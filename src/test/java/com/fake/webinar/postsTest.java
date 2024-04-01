package com.fake.webinar;

import com.thedeanda.lorem.LoremIpsum;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class postsTest {

    @Test
    public void getPostsShouldSucceed() {
        given()
                .baseUri("http://localhost")
                .port(3000)
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .log().body()
                .statusCode(200);
    }


    @Test
    public void getDetailPostsShouldSucceed() {

        String id = given()
                .baseUri("http://localhost")
                .port(3000)
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .log().body()
                .statusCode(200)
                .extract().jsonPath().getString("[0].id");

        given()
                .baseUri("http://localhost")
                .port(3000)
                .log().uri()
                .when()
                .get("/posts/" + id)
                .then()
                .log().body()
                .statusCode(200)
                .body("id", equalTo(id));
    }

    @Test
    public void PostPostsShouldSucceed() {
        String json = "{ \"title\": \"a title2\", \"views\": 101 }";

        given()
                .header("Content-Type", "application/json")
                .baseUri("http://localhost")
                .port(3000)
                .body(json)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .log().body()
                .statusCode(201);
    }

    @Test
    public void PostPostsMapShouldSucceed() {

        Map<String, Object> json = new HashMap<>();
        json.put("title", LoremIpsum.getInstance().getTitle(2));
        json.put("views", new Random().nextInt((1000 - 100) + 1) + 100);


        given()
                .header("Content-Type", "application/json")
                .baseUri("http://localhost")
                .port(3000)
                .body(json)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .log().body()
                .statusCode(201);
    }

    @Test
    public void PostPostsMap2ShouldSucceed() {

        String tittle = LoremIpsum.getInstance().getTitle(2);
        int viewCount = new Random().nextInt((1000 - 100) + 1) + 100;
        Map<String, Object> json = new HashMap<>();
        json.put("title", tittle);
        json.put("views", viewCount);


        given()
                .header("Content-Type", "application/json")
                .baseUri("http://localhost")
                .port(3000)
                .body(json)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .log().body()
                .statusCode(201)
                .body("title", equalTo(tittle))
                .body("views", equalTo(viewCount));
    }


    @Test
    public void putPostsMapShouldSucceed() {
        String id = given()
                .baseUri("http://localhost")
                .port(3000)
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .log().body()
                .statusCode(200)
                .extract().jsonPath().getString("[0].id");

        String tittle = LoremIpsum.getInstance().getTitle(2);
        Map<String, Object> json = new HashMap<>();
        json.put("title", tittle);
        json.put("views", 100);


        given()
                .header("Content-Type", "application/json")
                .baseUri("http://localhost")
                .port(3000)
                .body(json)
                .log().uri()
                .log().body()
                .when()
                .put("/posts/"+id)
                .then()
                .log().body()
                .statusCode(200)
                .body("title", equalTo(tittle));
    }

    @Test
    public void patchPostsMapShouldSucceed() {
        String id = given()
                .baseUri("http://localhost")
                .port(3000)
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .log().body()
                .statusCode(200)
                .extract().jsonPath().getString("[0].id");

        int viewCount = new Random().nextInt((1000 - 100) + 1) + 100;

        Map<String, Object> json = new HashMap<>();
        json.put("views", viewCount);


        given()
                .header("Content-Type", "application/json")
                .baseUri("http://localhost")
                .port(3000)
                .body(json)
                .log().uri()
                .log().body()
                .when()
                .patch("/posts/"+id)
                .then()
                .log().body()
                .statusCode(200)
                .body("views", equalTo(viewCount));
    }

    @Test
    public void deletePostsShouldSucceed() {
        String id = given()
                .baseUri("http://localhost")
                .port(3000)
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .log().body()
                .statusCode(200)
                .extract().jsonPath().getString("[0].id");

        given()
                .header("Content-Type", "application/json")
                .baseUri("http://localhost")
                .port(3000)
                .log().uri()
                .when()
                .delete("/posts/"+id)
                .then()
                .log().body()
                .statusCode(200);
    }
}
