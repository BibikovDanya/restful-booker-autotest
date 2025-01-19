package restfulbooker.helpers;

import restfulbooker.api.requests.bookings.BookingRequest;
import restfulbooker.models.BookData;

import java.util.List;
import java.util.Map;

public class BookingHelpers {
    BookingRequest bookingRequest = new BookingRequest();

    public List<Integer> getBookingsIds() {
        return bookingRequest.getBookingIds()
                .jsonPath()
                .getList("bookingid");
    }

    public List<Integer> getBookingsIds(Map<String, String> queryParams) {
        return bookingRequest.getBookingIds(queryParams)
                .jsonPath()
                .getList("bookingid");
    }

    public BookData getBookingById(Integer bookId) {
        return bookingRequest.getBooking(bookId).jsonPath().getObject("", BookData.class);
    }


    public void createBooking() {

    }

}
