package restfulbooker.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

public class GetBookingIdsTest extends BaseBookingTest {

    @Test
    public void getBookingIdTest() {

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
