package restfulbooker;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetBookingTest {
    private static final String baseUrl = "http://localhost:3001";
//    private static final String baseUrl = "http://restful-booker.herokuapp.com";

    @BeforeAll
    public static void initSpecification(){
        Specification.installSpecification(Specification.requestSpec(baseUrl + "/booking"));
        Specification.installSpecification(Specification.responseSpecOK200());
    }

    @Test
    public void getBooking(){
        BookData response = given()
                .when()
                .get("/1")
                .then().log().all()
                .extract().body().jsonPath().getObject("", BookData.class);

        Assertions.assertEquals("Eric", response.getFirstname());
        Assertions.assertEquals("Jones", response.getLastname());
        Assertions.assertEquals(193, response.getTotalprice());
        Assertions.assertFalse(response.isDepositpaid());
        Assertions.assertEquals("2017-04-23", response.getBookingdates().getCheckin());
        Assertions.assertEquals("2022-10-15", response.getBookingdates().getCheckout());
    }


}
