package restfulbooker.utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static java.net.HttpURLConnection.*;

public class Specification {


    public static RequestSpecification requestSpec(String url) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();

    }
    public static RequestSpecification requestSpec(String url, String basePath) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setBasePath(basePath)
                .setContentType(ContentType.JSON)
                .build();

    }

    public static ResponseSpecification responseSpecOK200() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HTTP_OK)
                .build();
    }

    public static ResponseSpecification responseSpecError400() {

        return new ResponseSpecBuilder()
            .expectStatusCode(HTTP_BAD_REQUEST)
                .build();
    }

    public static ResponseSpecification responseSpecError404() {

        return new ResponseSpecBuilder()
                .expectStatusCode(HTTP_NOT_FOUND)
                .build();
    }

    public static ResponseSpecification responseSpecUnique(int status) {

        return new ResponseSpecBuilder()
                .expectStatusCode(status)
                .build();
    }

    public static void installSpecification(ResponseSpecification response) {
        RestAssured.responseSpecification = response;
    }

    public static void installSpecification(RequestSpecification request) {
        RestAssured.requestSpecification = request;
    }

    public static void installSpecification(RequestSpecification request, ResponseSpecification response) {
        installSpecification(request);
        installSpecification(response);
    }


}
