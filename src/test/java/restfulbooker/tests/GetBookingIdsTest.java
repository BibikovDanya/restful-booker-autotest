package restfulbooker.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import restfulbooker.models.BookData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    // TODO refactor
    @ParameterizedTest(name = "Get ids by firstname = {0}")
    @ValueSource(strings = {"Sally", "Guoqiang"})
    public void getBookingIdFilterByFirstName(String firstName) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("firstname", firstName);
        Response response = RestAssured.given().queryParams(queryParams).log().all().get().then().log().all().extract().response();
        List<Integer> bookingIds = response.jsonPath().getList("bookingid");
        // проверим, что все книги в ответе соответствуют queryParam
        for(Integer i : bookingIds){
            BookData book = getBookingById(i).jsonPath().getObject("", BookData.class);
            Assertions.assertEquals(firstName, book.getFirstName());
        }

    }
}
