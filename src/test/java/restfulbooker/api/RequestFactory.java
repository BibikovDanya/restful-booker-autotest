package restfulbooker.api;

import java.lang.IllegalArgumentException;
import java.util.Map;

import io.restassured.http.Method;


public class RequestFactory {
    public static ApiRequest createRequest(Method method, String url, Map<String, String> queryParams, Object body) {
        return switch (method) {
            case GET:
                if (queryParams != null) {
                    yield new GetRequest(url).withQueryParams(queryParams);
                }
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
