package api.models;

import io.restassured.response.Response;

/**
 * JSONPlaceholder post resource.
 */
public record Post(int id, int userId, String title, String body) {

    public static Post from(Response response) {
        var path = response.jsonPath();
        return new Post(
                path.getInt("id"),
                path.getInt("userId"),
                path.getString("title"),
                path.getString("body"));
    }
}
