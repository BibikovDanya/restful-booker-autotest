package restfulbooker.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class GetRequest implements ApiRequest {
    private String url;
    private Map<String, String> queryParams;

    public GetRequest(String url) {
        this.url = url;
    }

    public GetRequest withQueryParams(Map<String, String> queryParams) {
        this.queryParams = queryParams;
        return this;
    }
//
//    public GetRequest() {
//    }

    @Override
    public Response sendRequest() {
        if(this.queryParams != null) {
            return RestAssured.given().queryParams(queryParams).log().all().get(url).then().log().all().extract().response();
        }
        else {
            return RestAssured.given().log().all().get(url).then().log().all().extract().response();
        }
    }


}
