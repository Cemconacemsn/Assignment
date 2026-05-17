package api.models;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import io.restassured.response.Response;

/**
 * Swagger Petstore user resource.
 */
public record User(
        long id,
        String username,
        String firstName,
        String lastName,
        String email,
        String password,
        String phone,
        int userStatus) {

  public static User forCreate(long id, String username) {
    return new User(
        id,
        username,
        "firstName-" + username,
        "lastName-" + username,
        username + "@example.com",
        "password-" + username,
        phoneFor(id),
        1);
  }

  public static User forUpdate(long id, String username) {
    return new User(
        id,
        username,
        "updatedFirst-" + username,
        "updatedLast-" + username,
        "updated." + username + "@example.com",
        "updatedPassword-" + username,
        phoneFor(id + 1),
        2);
  }

  public static User from(Response response) {
    var path = response.jsonPath();
    return new User(
        path.getLong("id"),
        path.getString("username"),
        path.getString("firstName"),
        path.getString("lastName"),
        path.getString("email"),
        path.getString("password"),
        path.getString("phone"),
        path.getInt("userStatus"));
  }

  public Map<String, Object> asMap() {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("id", id);
    body.put("username", username);
    body.put("firstName", firstName);
    body.put("lastName", lastName);
    body.put("email", email);
    body.put("password", password);
    body.put("phone", phone);
    body.put("userStatus", userStatus);
    return body;
  }

  private static String phoneFor(long seed) {
    int suffix = ThreadLocalRandom.current().nextInt(1_000_000, 9_999_999);
    return "555" + (Math.abs(seed) % 1_000) + suffix;
  }
}
