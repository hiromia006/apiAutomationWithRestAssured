package com.fake.publicApi;

import com.fake.publicApi.pojo.Post;
import com.thedeanda.lorem.LoremIpsum;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostWithPojoTest extends BaseTest {
    @Test
    public void postPostShouldSucceed() {
        String title = LoremIpsum.getInstance().getTitle(3);
        String author = LoremIpsum.getInstance().getName();
        Post post = new Post(title, author);

        Post post1 = given()
                .spec(getCommon())
                .log().uri()
                .log().body()
                .body(post)
                .when()
                .post("/posts")
                .then()
                .log().body()
                .statusCode(201)
                .body("author", equalTo(author))
                .body("title", equalTo(title))
                .extract().jsonPath().getObject("", Post.class);

        System.out.println(post1.getId());
        System.out.println(post1.getTitle());
    }

    @Test
    public void putPostShouldSucceed() {
        List<Post> posts = given()
                .spec(getCommon())
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getList("", Post.class);

        String title = LoremIpsum.getInstance().getTitle(2);
        int id = posts.get(0).getId();
        String author = posts.get(0).getAuthor();
        System.out.println("Author  ==: " + author);

        Post post = new Post(title, author);

        given()
                .spec(getCommon())
                .body(post)
                .log().uri()
                .log().body()
                .when()
                .put("/posts/" + id)
                .then()
                .statusCode(200)
                .log().body()
                .body("id", equalTo(id))
                .body("title", equalTo(title))
                .body("author", equalTo(author));


    }

    @Test
    public void patchPostShouldSucceed() {
        List<Post> posts = given()
                .spec(getCommon())
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getList("", Post.class);


        int id = posts.get(2).getId();
        String title = posts.get(2).getTitle();
        String author = LoremIpsum.getInstance().getName();
        System.out.println("title  ==: " + title);

        Post post = new Post(author);

        given()
                .spec(getCommon())
                .body(post)
                .log().uri()
                .log().body()
                .when()
                .patch("/posts/" + id)
                .then()
                .statusCode(200)
                .log().body()
                .body("id", equalTo(id))
                .body("title", equalTo(title))
                .body("author", equalTo(author));
    }

    @Test
    public void deletePostShouldSucceed() {
        List<Post> posts = given()
                .spec(getCommon())
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getList("", Post.class);


        int id = posts.get(1).getId();

        given()
                .spec(getCommon())
                .log().uri()
                .log().body()
                .when()
                .delete("/posts/" + id)
                .then()
                .statusCode(200)
                .log().body();
    }
}
