package restfulbooker.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import restfulbooker.utils.Specification;
import restfulbooker.api.RequestFactory;
import restfulbooker.models.BookData;

import java.util.List;
import java.util.Map;

import static io.restassured.http.Method.GET;
import static io.restassured.http.Method.POST;

//TODO разделить на request и helpers
public class BaseBookingTest {
    //TODO config env
    private static final String baseUrl = "http://localhost:3001";
//    private static final String baseUrl = "http://restful-booker.herokuapp.com";


    @BeforeAll
    public static void initSpecification() {
        Specification.installSpecification(Specification.requestSpec(baseUrl + "/booking"));
        Specification.installSpecification(Specification.responseSpecOK200());

    }
    //TODO возвращать сразу объекты BookData
    protected Response getBookingById(int id) {
        return RequestFactory
                .createRequest(GET, String.valueOf(id), null, null)
                .sendRequest();


    }

    protected Response getAllBooking() {
        return RequestFactory
                .createRequest(GET, "", null, null)
                .sendRequest();
    }
    protected List<Integer> getAllBooking(Map<String, String> queryParams) {
        return RequestFactory
                .createRequest(GET, "", queryParams, null)
                .sendRequest().jsonPath().getList("bookingid");
    }

    protected Response createBooking(BookData bookInfo) {
        return RequestFactory
                .createRequest(POST, "", null, bookInfo)
                .sendRequest();
    }

}
