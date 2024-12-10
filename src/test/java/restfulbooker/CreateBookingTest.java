package restfulbooker;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateBookingTest extends BaseBookingTest {

    @Test
    public void createBookTest() {
        BookingDates bookingDates = new BookingDates("2022-01-01", "2022-01-02");
        BookData book = new BookData("Jim", "Brown", 111, true, bookingDates, "Breakfast");
        Response response = createBooking(book);

        BookData createdBook = response.jsonPath().getObject("booking", BookData.class);

        Assertions.assertEquals(book, createdBook);

    }

}
