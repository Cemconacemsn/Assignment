package api.models;

import java.util.UUID;

/**
 * Unique user data for a single test method (parallel-safe).
 */
public record UserFixture(long id, String username, User payload) {

    public static UserFixture unique() {
        long id = System.currentTimeMillis();
        String username = "qa_user_" + id + "_" + UUID.randomUUID().toString().substring(0, 8);
        return new UserFixture(id, username, User.forCreate(id, username));
    }
}
