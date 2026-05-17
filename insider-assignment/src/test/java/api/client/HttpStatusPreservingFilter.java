package api.client;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.util.EntityUtils;

import io.restassured.builder.ResponseBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

/**
 * Returns the HTTP response without failing on 4xx/5xx so {@code .then().statusCode(...)} can validate it.
 */
public final class HttpStatusPreservingFilter implements Filter {

    public static final HttpStatusPreservingFilter INSTANCE = new HttpStatusPreservingFilter();

    private HttpStatusPreservingFilter() {
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                          FilterableResponseSpecification responseSpec,
                          FilterContext ctx) {
        HttpUriRequest request = buildRequest(requestSpec);
        try (CloseableHttpResponse httpResponse =
                (CloseableHttpResponse) requestSpec.getHttpClient().execute(request)) {
            return toRestAssuredResponse(httpResponse);
        } catch (IOException e) {
            throw new IllegalStateException("HTTP request failed: " + requestSpec.getURI(), e);
        }
    }

    private static HttpUriRequest buildRequest(FilterableRequestSpecification requestSpec) {
        RequestBuilder builder = RequestBuilder.create(requestSpec.getMethod())
                .setUri(requestSpec.getURI());
        applyHeaders(builder, requestSpec.getHeaders());
        return builder.build();
    }

    private static void applyHeaders(RequestBuilder builder, Headers headers) {
        headers.asList().forEach(header -> builder.addHeader(header.getName(), header.getValue()));
    }

    private static Response toRestAssuredResponse(CloseableHttpResponse httpResponse) throws IOException {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        String body = httpResponse.getEntity() == null
                ? ""
                : EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
        String contentType = contentType(httpResponse);

        ResponseBuilder builder = new ResponseBuilder()
                .setStatusCode(statusCode)
                .setStatusLine(httpResponse.getStatusLine().toString())
                .setBody(body);

        if (contentType != null) {
            builder.setContentType(contentType);
        }
        return builder.build();
    }

    private static String contentType(CloseableHttpResponse httpResponse) {
        Header header = httpResponse.getFirstHeader("Content-Type");
        return header == null ? null : header.getValue();
    }
}
