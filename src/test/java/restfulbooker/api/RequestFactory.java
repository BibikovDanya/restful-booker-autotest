package restfulbooker.api;

import java.lang.IllegalArgumentException;

import io.restassured.http.Method;


public class RequestFactory {
    public static ApiRequest createRequest(Method method, String url, Object body) {
        return switch (method) {
            case GET:
                yield new GetRequest(url);
            case POST:
                if (body != null) {
                    yield new PostRequest(url).withBody(body);
                } else {
                    yield new PostRequest(url);
                }
            default:
                throw new IllegalArgumentException("Invalid method type: " + method);
        };
    }
}
