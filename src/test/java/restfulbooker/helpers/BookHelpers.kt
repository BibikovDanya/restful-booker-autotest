package restfulbooker.helpers

import io.restassured.response.Response
import restfulbooker.api.requests.bookings.BookRequest
import restfulbooker.models.Book
import org.apache.http.HttpStatus.SC_NOT_FOUND
import org.apache.http.HttpStatus.SC_OK

// TODO обработка статусов
object BookHelpers {
    private val bookRequest: BookRequest = BookRequest()

    fun getBooksIds(params: Map<String, String>? = null): List<Int>? {
        return bookRequest.getBookingIds(params).jsonPath().getList("bookingid")
    }

    fun getBookById(bookId: Int): Book? {
        val response:Response =   bookRequest.getBooking(bookId)
       return when (response.statusCode){
           SC_OK -> response.jsonPath().getObject("", Book::class.java)
           else -> null

       }



    }

    fun createBook(bookData: Book): Book {
        return bookRequest.createBooking(bookData).jsonPath().getObject("booking", Book::class.java)
    }

    fun updateBook(bookId: Int, bookData: Book, token: String? = null): Book? {
        val response:Response = bookRequest.updateBooking(bookId, bookData, token)
        return when (response.statusCode){
            SC_OK -> response.jsonPath().getObject("", Book::class.java)
            else -> null
        }
    }

    fun  partialUpdateBook(bookId: Int,params: Map<String, String>?, token: String? = null):Book?{
        val response:Response = bookRequest.partialUpdateBooking(bookId, params, token)

        return when(response.statusCode){
            SC_OK -> response.jsonPath().getObject("", Book::class.java)
            else -> null
        }
    }


}