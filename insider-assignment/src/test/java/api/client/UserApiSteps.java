package api.client;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import api.models.User;
import api.models.UserFixture;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

/**
 * Reusable RestAssured steps for Petstore user endpoints.
 */
public final class UserApiSteps {

    private UserApiSteps() {
    }

    public static ValidatableResponse createUser(User user) {
        return given()
                .body(user.asMap())
                .when()
                .post("/user")
                .then();
    }

    /**
     * Creates a unique user via POST and returns its fixture (for tests that need an existing user).
     */
    public static UserFixture provisionUser() {
        UserFixture fixture = UserFixture.unique();
        createUser(fixture.payload())
                .statusCode(200)
                .body("code", equalTo(200));
        return fixture;
    }

    public static ValidatableResponse getUser(String username) {
        return given()
                .pathParam("username", username)
                .when()
                .get("/user/{username}")
                .then();
    }

    public static ValidatableResponse getUserAllowingErrorStatus(String username) {
        return given()
                .filter(HttpStatusPreservingFilter.INSTANCE)
                .pathParam("username", username)
                .when()
                .get("/user/{username}")
                .then();
    }

    public static ValidatableResponse updateUser(String username, User user) {
        return given()
                .pathParam("username", username)
                .body(user.asMap())
                .when()
                .put("/user/{username}")
                .then();
    }

    public static ValidatableResponse deleteUser(String username) {
        return given()
                .pathParam("username", username)
                .when()
                .delete("/user/{username}")
                .then();
    }

    public static Response extractUser(String username) {
        return getUser(username)
                .statusCode(200)
                .extract()
                .response();
    }

    public static void assertUserBodyMatches(User expected, ValidatableResponse response) {
        response
                .body("id", equalTo(expected.id()))
                .body("username", equalTo(expected.username()))
                .body("firstName", equalTo(expected.firstName()))
                .body("lastName", equalTo(expected.lastName()))
                .body("email", equalTo(expected.email()))
                .body("password", equalTo(expected.password()))
                .body("phone", equalTo(expected.phone()))
                .body("userStatus", equalTo(expected.userStatus()));
    }
}
