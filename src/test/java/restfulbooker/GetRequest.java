package restfulbooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetRequest implements ApiRequest{
    private String url;

    public GetRequest(String url){
        this.url = url;
    }
    public GetRequest(){
    }
    @Override
    public Response sendRequest() {
        return RestAssured.get(url).then().log().all().extract().response();
    }


}
