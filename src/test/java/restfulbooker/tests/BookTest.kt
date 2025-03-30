package restfulbooker.tests

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import restfulbooker.models.Book
import restfulbooker.models.BookingDatesNew
import restfulbooker.utils.SpecificationNew.installSpecification
import restfulbooker.utils.SpecificationNew.responseSpecNotFound
import restfulbooker.utils.SpecificationNew.responseSpecOk
import java.util.stream.Stream
import restfulbooker.helpers.BookHelpers.getBookById
import restfulbooker.helpers.BookHelpers.getBooksIds
import restfulbooker.helpers.BookHelpers.createBook
import restfulbooker.helpers.BookHelpers.updateBook
import restfulbooker.helpers.BookHelpers.partialUpdateBook
import org.junit.jupiter.api.Assertions.*

class BookTest : BaseBookTest() {
    @BeforeEach
    fun setSpec() {
        installSpecification(responseSpecOk())
    }

    @Test
    @Tag("GetBooking")
    fun getBookTest() {
        val regexDateFormat = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$"

        val book: Book? = getBookById(1)

        assertAll(
            "Checks books",
            Executable { assertNotNull(book?.firstName) },
            Executable { assertNotNull(book?.lastName, "lastName is null") },
            Executable { assertTrue(book?.totalPrice!! > 0, "totalPrice < 0") },
            Executable {
                assertTrue(
                    book?.bookingDates?.checkIn!!.matches(regexDateFormat.toRegex()),
                    "date checkIn does not match the format",
                )
            },
            Executable {
                assertTrue(
                    book!!.bookingDates.checkout.matches(regexDateFormat.toRegex()),
                    "date checkOut does not match the format",
                )
            },
        )
    }

    @Test
    @Tag("GetBooking")
    fun bookNotFoundTest() {
        installSpecification(responseSpecNotFound())
        assertNull(getBookById(404))
    }

    @Test
    @Tag("GetBookingIds")
    fun getBooksIdsTest() {
        val bookIds: List<Int>? = getBooksIds()
        bookIds?.forEach { it ->
            assertTrue(it > 0, "Все идентификаторы книг должны быть целыми числами. Не соответствует условию: $it")
        }
    }

    @Tag("GetBookingIds")
    @ParameterizedTest(name = "Get ids by firstname = {0}")
    @ValueSource(strings = ["Mark", "Mary", "Sally"])
    fun getBookingIdFilterByFirstNameTest(firstName: String) {
        val bookingIds: List<Int>? = getBooksIds(mapOf("firstname" to firstName))

        bookingIds?.forEach { it ->
            assertEquals(firstName, getBookById(it)?.firstName)
        }
    }

    @Tag("GetBookingIds")
    @ParameterizedTest(name = "Get ids by lastName = {0}")
    @ValueSource(strings = ["Jones", "Wilson", "Jackson"])
    fun getBookingIdFilterByLastNameTest(lastName: String) {
        val bookingIds: List<Int>? = getBooksIds(mapOf("lastname" to lastName))

        bookingIds?.forEach { it ->
            assertEquals(lastName, getBookById(it)!!.lastName)
        }
    }

    @Tag("GetBookingIds")
    @ParameterizedTest(name = "Get ids by checkIn = {0}, checkOut = {1}")
    @MethodSource("provideCheckInAndCheckout")
    fun getBookingIdFilterByCheckInAndCheckOutTest(checkIn: String, checkout: String) {
        val bookingIds: List<Int>? = getBooksIds(mapOf("checkin" to checkIn, "checkout" to checkout))

        bookingIds?.forEach { it ->
            assertTrue(getBookById(it)!!.bookingDates.checkIn in checkIn..checkout)
            assertTrue(getBookById(it)!!.bookingDates.checkout in checkIn..checkout)
        }
    }

    @Tag("CreateBooking")
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

        val createdBook: Book = createBook(book)
        assertEquals(book, createdBook)
    }

    @Tag("UpdateBooking")
    @Test
    fun updateFullBookTest() {
        val adminToken: String = getAuthHeader(adminLogin, adminPassword)
        val oldBook: Book? = getBookById(1)
        val updateBook =
            Book("Jon", "Minov", 111, false, BookingDatesNew("2024-11-11", "2024-11-12"), "Br")
        val actualBook: Book = updateBook(1, updateBook, adminToken)

        assertEquals(updateBook, actualBook)
        assertNotEquals(oldBook, actualBook)
    }

    @Tag("PartialUpdateBooking")
    @Test
    fun updateFirstNameBookTest() {
        val adminToken: String = getAuthHeader(adminLogin, adminPassword)
        val bookId = 1
        val newFirstName = "Jon"

        val oldBook: Book? = getBookById(bookId)
        val newBook = oldBook?.copy(firstName = newFirstName)

        val actualBook = partialUpdateBook(bookId, mapOf("firstname" to newFirstName), adminToken)

        assertAll(
            "в ответе приходит обновленная книга",
            Executable { assertEquals(newBook, actualBook) },
            Executable { assertEquals(newFirstName, actualBook.firstName) },
        )
        assertAll(
            "при запросе обновленной книги, возвращается обновленная книга",
            Executable { assertEquals(newBook, getBookById(bookId)) },
            Executable { assertEquals(newFirstName, getBookById(bookId)?.firstName) },
        )
    }

    companion object {
        @JvmStatic
        fun provideCheckInAndCheckout(): Stream<Arguments> = Stream.of(
            Arguments.of("2014-03-13", "2022-10-20"),
            Arguments.of("2022-03-13", "2022-10-20"),
            Arguments.of("2019-06-01", "2019-08-31"),
        )
    }
}
