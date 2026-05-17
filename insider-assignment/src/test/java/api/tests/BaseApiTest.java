package api.tests;

import org.testng.annotations.BeforeClass;

import api.client.ApiClient;
import io.restassured.RestAssured;
import com.insiderOne.core.BaseTest;

public abstract class BaseApiTest extends BaseTest {

    @BeforeClass(alwaysRun = true)
    public void configureRestAssured() {
        RestAssured.requestSpecification = ApiClient.defaultSpec();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
