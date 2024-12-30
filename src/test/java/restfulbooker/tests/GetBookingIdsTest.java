package restfulbooker.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
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

        assertEquals(200, response.statusCode());
        assertNotNull(response);

        JsonPath jsonPath = response.jsonPath();
        List<Integer> bookingIds = jsonPath.getList("bookingid");
        System.out.println(bookingIds.toString());
        assertTrue(bookingIds.stream().allMatch(Objects::nonNull),
                "Все идентификаторы книг должны быть целыми числами");

    }
    // TODO refactor
    @ParameterizedTest(name = "Get ids by firstname = {0}")
    @ValueSource(strings = {"Sally", "Guoqiang", "Jim"})
    public void getBookingIdFilterByFirstName(String firstName) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("firstname", firstName);
        List<Integer> bookingIds = getAllBooking(queryParams);
        // проверим, что все книги в ответе соответствуют queryParam
        for(Integer i : bookingIds){
            BookData book = getBookingById(i).jsonPath().getObject("", BookData.class);
            assertEquals(firstName, book.getFirstName());
        }

    }
}
