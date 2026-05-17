package api.tests;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import api.models.Post;

public class SampleApiTest extends BaseApiTest {

    private static final String JSON_PLACEHOLDER_URI = "https://jsonplaceholder.typicode.com";

    @Test(description = "GET /posts/1 returns expected post id and title")
    public void shouldReturnPostById() {
        var response = given()
                .baseUri(JSON_PLACEHOLDER_URI)
                .pathParam("id", 1)
                .when()
                .get("/posts/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", notNullValue())
                .extract()
                .response();

        Post post = Post.from(response);

        assertThat(post.userId()).isEqualTo(1);
        assertThat(post.title()).isNotBlank();
    }

    @Test(description = "GET /posts returns a non-empty list")
    public void shouldReturnPostsList() {
        int size = given()
                .baseUri(JSON_PLACEHOLDER_URI)
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .extract()
                .path("size()");

        assertThat(size).isPositive();
    }
}
