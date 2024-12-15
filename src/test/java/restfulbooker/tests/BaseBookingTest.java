package restfulbooker.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import restfulbooker.utils.Specification;
import restfulbooker.api.RequestFactory;
import restfulbooker.models.BookData;
import static io.restassured.http.Method.GET;
import static io.restassured.http.Method.POST;


public class BaseBookingTest {
    //TODO config env
    private static final String baseUrl = "http://localhost:3001";
//    private static final String baseUrl = "http://restful-booker.herokuapp.com";


    @BeforeAll
    public static void initSpecification() {
        Specification.installSpecification(Specification.requestSpec(baseUrl + "/booking"));
        Specification.installSpecification(Specification.responseSpecOK200());

    }

    protected Response getBookingById(int id) {
        return RequestFactory
                .createRequest(GET, String.valueOf(id), null)
                .sendRequest();


    }

    protected Response getAllBooking() {
        return RequestFactory
                .createRequest(GET, "", null)
                .sendRequest();
    }

    protected Response createBooking(BookData bookInfo) {
        return RequestFactory
                .createRequest(POST, "", bookInfo)
                .sendRequest();
    }

}
