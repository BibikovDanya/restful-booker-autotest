package restfulbooker;

import java.lang.IllegalArgumentException;


public class RequestFactory {
    public static ApiRequest createRequest(String method, String url, Object body) {
        if ("GET".equalsIgnoreCase(method)) {
            return new GetRequest(url);
        } else if ("POST".equalsIgnoreCase(method)) {
            if (body != null) {
                return new PostRequest(url).withBody(body);
            } else {
                return new PostRequest(url);
            }
        } else {
            throw new IllegalArgumentException("Invalid method type: " + method);
        }
    }
}
