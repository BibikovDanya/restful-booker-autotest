package restfulbooker;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.*;

public class GetBookingIdsTest extends BaseBookingTest {
    private static final String baseUrl = "http://localhost:3001";
//    private static final String baseUrl = "http://restful-booker.herokuapp.com";

//    @BeforeAll
//    public static void initSpecification(){
//       Specification.installSpecification(Specification.requestSpec(baseUrl + "/booking"));
//       Specification.installSpecification(Specification.responseSpecOK200());
//    }

    @Test
    public void getBookingIdTest() {
//        Response response2 = given()
//                .when()
//                .get()
//                .then().log().all()
//                .extract().response();
       Response response = getAllBooking();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertNotNull(response);

        JsonPath jsonPath = response.jsonPath();
        List<Integer> bookingIds = jsonPath.getList("bookingid");
        System.out.println(bookingIds.toString());
        Assertions.assertTrue(bookingIds.stream().allMatch(Objects::nonNull),
                "Все идентификаторы книг должны быть целыми числами");

    }
}
