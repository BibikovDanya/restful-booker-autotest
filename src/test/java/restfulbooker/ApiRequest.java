package restfulbooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public interface ApiRequest {
    Response sendRequest();

}