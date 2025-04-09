package restfulbooker.helpers

import io.restassured.response.Response
import org.apache.http.HttpStatus.SC_OK
import restfulbooker.api.requests.BookRequest
import restfulbooker.models.Book

object BookHelpers {
    private val bookRequest: BookRequest = BookRequest()
    private const val BOOKING_ID_KEY = "bookingid"
    private const val BOOKING_KEY = "booking"

    fun getBooksIds(params: Map<String, String>? = null): List<Int>? {
        val response: Response = bookRequest.getBookingIds(params)
        return when (response.statusCode) {
            SC_OK -> response.jsonPath().getList(BOOKING_ID_KEY)
            else -> null
        }
    }

    fun getBookById(bookId: Int): Book? {
        val response: Response = bookRequest.getBooking(bookId)
        return when (response.statusCode) {
            SC_OK -> response.jsonPath().getObject("", Book::class.java)
            else -> null
        }
    }

    fun createBook(bookData: Book): Book? {
        val response: Response = bookRequest.createBooking(bookData)
        return when (response.statusCode) {
            SC_OK -> response.jsonPath().getObject(BOOKING_KEY, Book::class.java)
            else -> null
        }
    }

    fun updateBook(
        bookId: Int,
        bookData: Book,
        token: String? = null,
    ): Book? {
        val response: Response = bookRequest.updateBooking(bookId, bookData, token)
        return when (response.statusCode) {
            SC_OK -> response.jsonPath().getObject("", Book::class.java)
            else -> null
        }
    }

    fun partialUpdateBook(
        bookId: Int,
        params: Map<String, String>?,
        token: String? = null,
    ): Book? {
        val response: Response = bookRequest.partialUpdateBooking(bookId, params, token)

        return when (response.statusCode) {
            SC_OK -> response.jsonPath().getObject("", Book::class.java)
            else -> null
        }
    }

    fun deleteBook(
        bookId: Int,
        token: String? = null,
    ) = bookRequest.deleteBook(bookId, token)
}
