package api.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import api.client.UserApiSteps;
import api.models.User;
import api.models.UserFixture;

/**
 * Independent Petstore user API tests — each method is self-contained and parallel-safe.
 */
public class UserCrudTest extends BaseApiTest {

    @Test(description = "POST /user creates a new user")
    public void shouldCreateUser() {
        UserFixture fixture = UserFixture.unique();

        UserApiSteps.createUser(fixture.payload())
                .statusCode(200)
                .body("code", equalTo(200))
                .body("message", equalTo(String.valueOf(fixture.id())));
    }

    @Test(description = "GET /user/{username} returns the created user")
    public void shouldGetCreatedUser() {
        UserFixture fixture = UserApiSteps.provisionUser();

        var response = UserApiSteps.getUser(fixture.username())
                .statusCode(200);

        UserApiSteps.assertUserBodyMatches(fixture.payload(), response);

        User fetched = User.from(response.extract().response());
        assertThat(fetched.id()).isPositive();
        assertThat(fetched.username()).isEqualTo(fixture.username());
    }

    @Test(description = "PUT /user/{username} updates the user")
    public void shouldUpdateUser() {
        UserFixture fixture = UserApiSteps.provisionUser();

        User created = User.from(UserApiSteps.extractUser(fixture.username()));
        User updatePayload = User.forUpdate(created.id(), fixture.username());

        UserApiSteps.updateUser(fixture.username(), updatePayload)
                .statusCode(200)
                .body("code", equalTo(200))
                .body("message", equalTo(String.valueOf(updatePayload.id())));

        var getResponse = UserApiSteps.getUser(fixture.username()).statusCode(200);
        UserApiSteps.assertUserBodyMatches(updatePayload, getResponse);

        User updated = User.from(getResponse.extract().response());
        assertThat(updated.id()).isEqualTo(updatePayload.id());
        assertThat(updated.username()).isEqualTo(fixture.username());
    }

    @Test(description = "DELETE /user/{username} removes the user")
    public void shouldDeleteUser() {
        UserFixture fixture = UserApiSteps.provisionUser();

        UserApiSteps.deleteUser(fixture.username())
                .statusCode(200)
                .body("code", equalTo(200))
                .body("message", equalTo(fixture.username()));
    }

    @Test(description = "GET /user/{username} after delete returns 404 User not found")
    public void shouldReturnNotFoundForDeletedUser() {
        UserFixture fixture = UserApiSteps.provisionUser();

        UserApiSteps.deleteUser(fixture.username())
                .statusCode(200)
                .body("code", equalTo(200));

        UserApiSteps.getUserAllowingErrorStatus(fixture.username())
                .statusCode(404)
                .body("code", equalTo(1))
                .body("message", equalTo("User not found"));
    }
}
