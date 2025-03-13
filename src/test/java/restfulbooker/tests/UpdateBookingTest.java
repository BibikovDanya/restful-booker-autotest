package restfulbooker.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import restfulbooker.models.BookData;

public class UpdateBookingTest extends BaseBookingTest {
    String adminAuthHeader = getAuthHeader();

    @Test
    @Tag("api")
    public void updateBookTest(){
        BookData bookData = bookingHelpers.getBookingById(1);
        BookData newBook =  new BookData.Builder()
                .firstName("Jim")
                .lastName("Brown")
                .totalPrice(111)
                .depositPaid(true)
                .bookingDates("2022-11-01", "2022-01-02")
                .additionalNeeds("Breakfast")
                .build();

        BookData actualBook = bookingHelpers.updateBook(1, newBook, adminAuthHeader);
        Assertions.assertEquals(newBook, actualBook);
        Assertions.assertNotEquals(bookData, actualBook);
    }
}
