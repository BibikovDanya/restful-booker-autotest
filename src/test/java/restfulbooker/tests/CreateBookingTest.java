package restfulbooker.tests;

import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import restfulbooker.models.BookData;

public class CreateBookingTest extends BaseBookingTest {

    @Test
    public void createBookTest() {
        BookData book = new BookData("Jim", "Brown", 111, true, "2022-01-01", "2022-01-02", "Breakfast");

        BookData createdBook = bookingHelpers.createBooking(book);

        assertEquals(book, createdBook);

    }

}
