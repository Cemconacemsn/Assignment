package api.client;

import config.Configuration;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

/**
 * Shared RestAssured request specification for API tests.
 */
public final class ApiClient {

    private ApiClient() {
    }

    public static RequestSpecification defaultSpec() {
        Configuration config = Configuration.getInstance();
        boolean logAll = config.getBoolean("api.log.all", false);

        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(config.get("api.base.uri"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON);

        if (logAll) {
            builder.log(LogDetail.ALL);
        }

        String token = config.get("api.auth.token");
        if (token != null && !token.isBlank()) {
            builder.addHeader("Authorization", "Bearer " + token);
        }

        return builder.build();
    }
}
