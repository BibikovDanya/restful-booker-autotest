package restfulbooker.api;

import io.restassured.RestAssured;
import io.restassured.http.Method;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class ApiRequest {
    private String url;
    private Method method;
    private Map<String, String> queryParams;
    private Map<String, String> headers = new HashMap<>();
    private Object body;

    public ApiRequest(Method method, String url) {
        this.method = method;
        this.url = url;
    }

    public ApiRequest withQueryParams(Map<String, String> queryParams) {
        this.queryParams = queryParams;
        return this;
    }

    public ApiRequest withBody(Object body) {
        if (body != null) {
            this.body = body;
        }
        return this;
    }

    public ApiRequest withHeaders(Map<String, String> headers) {
        if (headers != null) {
            this.headers.putAll(headers);
        }
        return this;
    }

    public Response sendRequest() {
        RequestSpecification request = RestAssured.given().log().all();
        if (headers != null && !headers.isEmpty()) {
            request.headers(headers);
        }
        if (queryParams != null &&!queryParams.isEmpty()) {
            request.queryParams(queryParams);
        }

        return switch (method) {
            case GET -> request.get(url).then().log().all().extract().response();
            case POST -> request.body(body).post(url).then().log().all().extract().response();
            case PUT -> request.body(body).put(url).then().log().all().extract().response();
            default -> throw new IllegalArgumentException("Invalid method type: " + method);
        };
    }
}
