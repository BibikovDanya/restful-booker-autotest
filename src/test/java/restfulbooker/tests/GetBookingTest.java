package restfulbooker.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import restfulbooker.utils.Specification;
import restfulbooker.models.BookData;


public class GetBookingTest extends BaseBookingTest {


    @Test
    public void getBookingTest() {
        String regexDateFormat = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
        BookData response = getBookingById(1).jsonPath().getObject("", BookData.class);

        assertAll("Checks",
                () -> assertNotNull(response.getFirstName(), "firstName is null"),
                () -> assertNotNull(response.getLastName(), "lastName is null"),
                () -> assertTrue(response.getTotalPrice() > 0, "totalPrice < 0"),
                () -> assertTrue(response.isDepositPaid(), "depositPaid false"),
                () -> assertTrue(response.getBookingDates().getCheckIn().matches(regexDateFormat), "date checkIn does not match the format"),
                () -> assertTrue(response.getBookingDates().getCheckOut().matches(regexDateFormat), "date checkOut does not match the format")
        );


    }

    @Test
    public void bookNotFoundTest() {
        Specification.installSpecification(Specification.responseSpecError404());
        getBookingById(404);

    }


}
