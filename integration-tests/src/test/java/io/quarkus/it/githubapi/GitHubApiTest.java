package io.quarkus.it.githubapi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class GitHubApiTest {

    @Test
    public void test() throws Exception {
        given().when()
                .get("/test")
                .then()
                .statusCode(200)
                .body(is("quarkiverse/quarkus-github-api"));
    }
}
