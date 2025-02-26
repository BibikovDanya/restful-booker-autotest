package restfulbooker.tests;

import static org.junit.jupiter.api.Assertions.*;

import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import restfulbooker.models.BookData;

public class CreateBookingTest extends BaseBookingTest {

    @Test
    @Tag("api")
    public void createBookTest() {
        BookData book = new BookData.Builder()
                .firstName("Jim")
                .lastName("Brown")
                .totalPrice(111)
                .depositPaid(true)
                .bookingDates("2022-01-01", "2022-01-02")
                .additionalNeeds("Breakfast")
                .build();

        BookData createdBook = bookingHelpers.createBooking(book);

        assertEquals(book, createdBook);

    }

}
