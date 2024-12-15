package restfulbooker.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import restfulbooker.models.BookData;
import restfulbooker.models.BookingDates;

public class CreateBookingTest extends BaseBookingTest {

    @Test
    public void createBookTest() {
        BookData book = new BookData("Jim", "Brown", 111, true, "2022-01-01", "2022-01-02", "Breakfast");
        Response response = createBooking(book);

        BookData createdBook = response.jsonPath().getObject("booking", BookData.class);

        Assertions.assertEquals(book, createdBook);

    }

}
