package restfulbooker.tests

import org.apache.http.HttpStatus.SC_CREATED
import org.apache.http.HttpStatus.SC_FORBIDDEN
import org.apache.http.HttpStatus.SC_METHOD_NOT_ALLOWED
import org.apache.http.HttpStatus.SC_NOT_FOUND
import org.apache.http.HttpStatus.SC_OK
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import restfulbooker.api.requests.BookRequest
import restfulbooker.models.Book
import restfulbooker.models.BookingDatesNew
import java.util.stream.Stream

class BookTest : BaseTest() {
    private val bookRequest: BookRequest = BookRequest()
    private val adminToken: String = getAuthHeader(adminLogin, adminPassword)

    // region Positive Idempotent Tests
    @Test
    @Tag("book")
    fun getBookTest() {
        val response = bookRequest.getBooking(1)
        val regexDateFormat = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$"
        assertEquals(SC_OK, response.statusCode)
        response.`as`(Book::class.java)?.let { book ->
            assertAll(
                { assertNotNull(book.firstName) },
                { assertNotNull(book.lastName) },
                { assertTrue(book.totalPrice > 0, "actual totalPrice: ${book.totalPrice}. Expected totalPrice < 0") },
                {
                    assertTrue(
                        book.bookingDates.checkout.matches(regexDateFormat.toRegex()),
                        "actual date checkout: ${book.bookingDates.checkout}. Expected  match the format",
                    )
                },
                {
                    assertTrue(
                        book.bookingDates.checkIn.matches(regexDateFormat.toRegex()),
                        "actual date checkIn: ${book.bookingDates.checkIn}. Expected  match the format",
                    )
                },
            )
        } ?: fail<String>("Response book is empty")
    }

    @Test
    @Tag("book")
    fun getBooksIdsTest() {
        val response = bookRequest.getBookingIds()
        val booksList: List<Int> = response.jsonPath().getList(BOOKING_ID_KEY)
        assertAll(
            { assertEquals(SC_OK, response.statusCode) },
            {
                booksList.forEach {
                    assertTrue(
                        it > 0,
                        "All book identifiers must be integers. Does not meet the condition: $it",
                    )
                }
            },
        )
    }

    @Tag("book")
    @Tag("flaky")
    @ParameterizedTest(name = "Get ids by firstname = {0}")
    @ValueSource(strings = ["Mark", "Mary", "Sally"])
    fun getBookingIdFilterByFirstNameTest(firstName: String) {
        val response = bookRequest.getBookingIds(mapOf("firstname" to firstName))
        val bookingIds: List<Int> = response.jsonPath().getList(BOOKING_ID_KEY)
        assertAll(
            {
                assertEquals(SC_OK, response.statusCode)
            },
            {
                if (bookingIds.isEmpty()) {
                    fail<String>("Expected non-empty booking IDs for firstname = $firstName. Check filter logic or test data.")
                }
                bookingIds.forEach {
                    assertEquals(
                        firstName,
                        bookRequest.getBooking(it).`as`(Book::class.java).firstName,
                    )
                }
            },
        )
    }

    @Tag("book")
    @Tag("flaky")
    @ParameterizedTest(name = "Get ids by lastName = {0}")
    @ValueSource(strings = ["Jones", "Wilson", "Jackson"])
    fun getBookingIdFilterByLastNameTest(lastName: String) {
        val response = bookRequest.getBookingIds(mapOf("lastname" to lastName))
        val bookingIds: List<Int> = response.jsonPath().getList(BOOKING_ID_KEY)
        assertAll(
            { assertEquals(SC_OK, response.statusCode) },
            {
                if (bookingIds.isEmpty()) {
                    fail<String>("Expected non-empty booking IDs for lastName = $lastName. Check filter logic or test data.")
                }

                bookingIds.forEach {
                    assertEquals(lastName, bookRequest.getBooking(it).`as`(Book::class.java).lastName)
                }
            },
        )
    }

    @Tag("book")
    @Tag("flaky")
    @ParameterizedTest(name = "Get ids by checkIn = {0}, checkOut = {1}")
    @MethodSource("dateFiltersProvider")
    fun getBookingIdFilterByCheckInAndCheckOutTest(
        checkIn: String,
        checkout: String,
    ) {
        val response = bookRequest.getBookingIds(mapOf("checkin" to checkIn, "checkout" to checkout))
        val bookingIds: List<Int> = response.jsonPath().getList(BOOKING_ID_KEY)

        assertAll(
            { assertEquals(SC_OK, response.statusCode) },
            {
                if (bookingIds.isEmpty()) {
                    fail<String>("Expected non-empty booking IDs for dates $checkIn-$checkout. Check filter logic or test data.")
                }
                bookingIds.forEach {
                    bookRequest.getBooking(it).`as`(Book::class.java)?.let { book ->
                        assertTrue(book.bookingDates.checkIn in checkIn..checkout)
                        assertTrue(book.bookingDates.checkout in checkIn..checkout)
                    } ?: fail<String>("Empty response for bookId: $it")
                }
            },
        )
    }
    // endregion

    // region Positive Non-idempotent Tests

    @Tag("book")
    @Test
    fun createBookTest() {
        val book =
            Book(
                firstName = "Jim",
                lastName = "Brown",
                totalPrice = 111,
                depositPaid = true,
                BookingDatesNew("2022-01-01", "2022-01-02"),
                additionalNeeds = "Breakfast",
            )

        val response = bookRequest.createBooking(book)
        val createdBook: Book? = response.jsonPath().getObject("booking", Book::class.java)
        assertAll(
            {
                assertEquals(SC_OK, response.statusCode)
            },
            {
                assertEquals(book, createdBook)
            },
        )
    }

    @Tag("book")
    @Test
    fun updateFullBookTest() {
        val oldBook: Book? = bookRequest.getBooking(1).`as`(Book::class.java)
        val updateBook =
            Book("Jon", "Minov", 111, false, BookingDatesNew("2024-11-11", "2024-11-12"), "Br")
        val response = bookRequest.updateBooking(1, updateBook, adminToken)
        val actualBook: Book? = response.`as`(Book::class.java)

        assertAll(
            {
                assertEquals(SC_OK, response.statusCode)
            },
            {
                assertEquals(updateBook, actualBook)
            },
            {
                assertNotEquals(oldBook, actualBook)
            },
        )
    }

    @Tag("book")
    @Test
    fun updateFirstNameBookTest() {
        val bookId = 1
        val newFirstName = "Jon"

        val oldBook: Book? = bookRequest.getBooking(bookId).`as`(Book::class.java)
        val newBook = oldBook?.copy(firstName = newFirstName)

        val response =
            bookRequest.partialUpdateBooking(bookId, mapOf("firstname" to newFirstName), adminToken)
        val actualBook = response.`as`(Book::class.java)

        assertAll(
            "в ответе приходит обновленная книга",
            { assertEquals(newBook, actualBook) },
            { assertEquals(newFirstName, actualBook?.firstName) },
        )
        assertEquals(SC_OK, response.statusCode)
    }

    @Tag("book")
    @Test
    fun deleteBookTest() {
        val response = bookRequest.deleteBook(4, adminToken)
        assertEquals(SC_CREATED, response.statusCode)
    }

    // endregion

    // region Negative Tests
    @Test
    @Tag("book")
    fun bookNotFoundTest() {
        val response = bookRequest.getBooking(404)
        assertEquals(SC_NOT_FOUND, response.statusCode)
    }

    @Tag("book")
    @Test
    fun partialUpdateBookNotFoundTest() {
        val bookId = 404
        val newFirstName = "Jon"

        val response = bookRequest.partialUpdateBooking(bookId, mapOf("firstname" to newFirstName), adminToken)
        assertAll(
            { assertEquals(SC_METHOD_NOT_ALLOWED, response.statusCode) },
            { assertEquals("Method Not Allowed", response.body().asString()) },
        )
    }

    // endregion

    // region Security Tests
    @Tag("book")
    @Tag("security")
    @Test
    fun updateFullBookTokenNoPresentTest() {
        val updateBook =
            Book("Jon", "Minov", 111, false, BookingDatesNew("2024-11-11", "2024-11-12"), "Br")
        val response = bookRequest.updateBooking(1, updateBook, null)

        assertAll(
            {
                assertEquals(SC_FORBIDDEN, response.statusCode)
            },
            {
                assertEquals("Forbidden", response.body().asString())
            },
        )
    }

    @Tag("book")
    @Tag("security")
    @Test
    fun deleteBookTokenNoPresentTest() {
        val response = bookRequest.deleteBook(4, null)
        assertEquals(SC_FORBIDDEN, response.statusCode)
    }
    // endregion

    companion object {
        private const val BOOKING_ID_KEY = "bookingid"
//        private const val BOOKING_KEY = "booking"

        @JvmStatic
        fun dateFiltersProvider(): Stream<Arguments> =
            Stream.of(
                Arguments.of("2014-03-13", "2022-10-20"),
//                Arguments.of("2022-03-13", "2022-10-20"), // data for fail test
                Arguments.of("2019-01-01", "2023-08-31"),
            )
    }
}
