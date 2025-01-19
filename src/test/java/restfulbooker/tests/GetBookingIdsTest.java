package restfulbooker.tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import restfulbooker.models.BookData;
import restfulbooker.utils.Specification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GetBookingIdsTest extends BaseBookingTest {

    @Test
    public void getBookingIdTest() {
        List<Integer> bookingIds = bookingHelpers.getBookingsIds();

        assertTrue(bookingIds.stream().allMatch(Objects::nonNull),
                "Все идентификаторы книг должны быть целыми числами");

    }
//    // TODO refactor
    @ParameterizedTest(name = "Get ids by firstname = {0}")
    @ValueSource(strings = {"Sally", "Guoqiang", "Jim"})
    public void getBookingIdFilterByFirstName(String firstName) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("firstname", firstName);

        List<Integer> bookingIds = bookingHelpers.getBookingsIds(queryParams);

        // проверим, что все книги в ответе соответствуют queryParam
        for(Integer i : bookingIds){
            BookData book = bookingHelpers.getBookingById(i);
            assertEquals(firstName, book.getFirstName());
        }

    }
}
