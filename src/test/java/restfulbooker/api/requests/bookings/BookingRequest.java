package restfulbooker.api.requests.bookings;

import io.restassured.response.Response;
import restfulbooker.api.ApiRequest;
import restfulbooker.models.BookData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.http.Method.*;

public class BookingRequest {
    private final String BASE_URL = "/booking";

    public Response getBookingIds() {
        return new ApiRequest(GET, BASE_URL).sendRequest();
    }

    public Response getBookingIds(Map<String, String> queryParams) {
        return new ApiRequest(GET, BASE_URL).withQueryParams(queryParams).sendRequest();
    }

    public Response getBooking(Integer id) {
        return new ApiRequest(GET, BASE_URL + '/' + id).sendRequest();
    }

    public Response createBooking(BookData bookData) {
        return new ApiRequest(POST, BASE_URL).withBody(bookData).sendRequest();
    }

    public Response updateBooking(Integer bookingId, BookData bookData, String token) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", token);
        return new ApiRequest(PUT, BASE_URL + "/" + bookingId).withBody(bookData).withHeaders(headers).sendRequest();

    }



}
