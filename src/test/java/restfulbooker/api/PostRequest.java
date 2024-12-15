package restfulbooker.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PostRequest implements ApiRequest {
    private String url;
    private Object body;

    public PostRequest(String url) {
        this.url = url;
    }

    public PostRequest withBody(Object body) {
        this.body = body;
        return this;
    }

    @Override
    public Response sendRequest() {
        if (this.body != null) {
            return RestAssured.given().body(body).log().all().post(url).then().log().all().extract().response();
        } else {
            return RestAssured.post(url).then().log().all().extract().response();
        }
    }


}
