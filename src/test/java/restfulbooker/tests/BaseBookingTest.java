package restfulbooker.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import restfulbooker.api.requests.bookings.BookingRequest;
import restfulbooker.helpers.BookingHelpers;
import restfulbooker.utils.Specification;

//TODO разделить на request и helpers
public class BaseBookingTest {
    protected BookingRequest bookingRequest;
    protected BookingHelpers bookingHelpers;

    //TODO config env
    private static final String baseUrl = "http://localhost:3001";
//    private static final String baseUrl = "http://restful-booker.herokuapp.com";


    @BeforeAll
    public static void initSpecification() {
        Specification.installSpecification(Specification.requestSpec(baseUrl));
        Specification.installSpecification(Specification.responseSpecOK200());

    }
    @BeforeEach
    public void setUp() {
        bookingRequest = new BookingRequest();
        bookingHelpers = new BookingHelpers();

    }

}
