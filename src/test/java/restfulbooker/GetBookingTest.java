package restfulbooker;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetBookingTest extends BaseBookingTest {


    @Test
    public void getBookingTest() {
        String regexDateFormat = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
        BookData response = getBookingById(1).jsonPath().getObject("", BookData.class);

        Assertions.assertAll("Checks",
                () -> Assertions.assertNotNull(response.getFirstName(), "firstName is null"),
                () -> Assertions.assertNotNull(response.getLastName(), "lastName is null"),
                () -> Assertions.assertTrue(response.getTotalPrice() > 0, "totalPrice < 0"),
                () -> Assertions.assertTrue(response.isDepositPaid(), "depositPaid false"),
                () -> Assertions.assertTrue(response.getBookingDates().getCheckIn().matches(regexDateFormat), "date checkIn does not match the format"),
                () -> Assertions.assertTrue(response.getBookingDates().getCheckOut().matches(regexDateFormat), "date checkOut does not match the format")
        );


    }

    @Test
    public void bookNotFoundTest() {
        Specification.installSpecification(Specification.responseSpecError404());
        getBookingById(404);

    }


}
