package restfulbooker.api.requests.bookings;

import io.restassured.response.Response;
import restfulbooker.api.ApiRequest;
import restfulbooker.models.BookData;

import java.util.Map;

import static io.restassured.http.Method.GET;
import static io.restassured.http.Method.POST;

public class BookingRequest {
    private final String BASE_URL = "/booking";

    public Response getBookingIds() {
        return new ApiRequest(GET, BASE_URL).sendRequest();
    }

    public Response getBookingIds(Map<String, String> queryParams) {
        return new ApiRequest(GET, BASE_URL).withQueryParams(queryParams).sendRequest();
    }

    public Response getBooking(Integer id) {
        return new ApiRequest(GET, BASE_URL + '/' + +id).sendRequest();
    }

    public Response createBooking(BookData bookData) {
        return new ApiRequest(POST, BASE_URL).withBody(bookData).sendRequest();
    }


}
