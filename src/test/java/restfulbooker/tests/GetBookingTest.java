package restfulbooker.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static restfulbooker.utils.Specification.*;
import restfulbooker.models.BookData;


public class GetBookingTest extends BaseBookingTest {

    @Test
    @Tag("api")
    public void getBookingTest() {
        String regexDateFormat = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";

        BookData response = bookingHelpers.getBookingById(1);

        assertAll("Checks",
                () -> assertNotNull(response.getFirstName(), "firstName is null"),
                () -> assertNotNull(response.getLastName(), "lastName is null"),
                () -> assertTrue(response.getTotalPrice() > 0, "totalPrice < 0"),
//                () -> assertTrue(response.isDepositPaid(), "depositPaid false"),
                () -> assertTrue(response.getBookingDates().getCheckIn().matches(regexDateFormat), "date checkIn does not match the format"),
                () -> assertTrue(response.getBookingDates().getCheckout().matches(regexDateFormat), "date checkOut does not match the format")
        );


    }

    @Test
    @Tag("api")
    public void bookNotFoundTest() {
        installSpecification(responseSpecError404());
        bookingRequest.getBooking(404);

    }
}
