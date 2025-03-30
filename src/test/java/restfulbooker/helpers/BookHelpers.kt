package restfulbooker.helpers

import io.restassured.response.Response
import restfulbooker.api.requests.bookings.BookRequest
import restfulbooker.models.Book
import org.apache.http.HttpStatus.SC_NOT_FOUND
import org.apache.http.HttpStatus.SC_OK

object BookHelpers {
    private val bookRequest: BookRequest = BookRequest()

    fun getBooksIds(params: Map<String, String>? = null): List<Int>? {
        return bookRequest.getBookingIds(params).jsonPath().getList("bookingid")
    }

    fun getBookById(bookId: Int): Book? {
        val response:Response =   bookRequest.getBooking(bookId)
       return when (response.statusCode){
           SC_OK -> response.jsonPath().getObject("", Book::class.java)
           SC_NOT_FOUND -> null
           else -> null

       }



    }

    fun createBook(bookData: Book): Book {
        return bookRequest.createBooking(bookData).jsonPath().getObject("booking", Book::class.java)
    }

    fun updateBook(bookId: Int, bookData: Book, token: String): Book {
        return bookRequest.updateBooking(bookId, bookData, token).jsonPath().getObject("", Book::class.java)
    }

    fun  partialUpdateBook(bookId: Int,params: Map<String, String>?, token: String):Book{
        return bookRequest.partialUpdateBooking(
            bookId,
            params,
            token).jsonPath().getObject("", Book::class.java)
    }


}