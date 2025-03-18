package restfulbooker.helpers

import restfulbooker.api.requests.bookings.BookRequest
import restfulbooker.models.Book

class BookHelpers {
    val bookRequest: BookRequest = BookRequest()

    fun getBooksIds(params: Map<String, String>? = null): List<Int>? {
        return bookRequest.getBookingIds(params).jsonPath().getList("bookingid")
    }

    fun getBookById(bookId: Int): Book = bookRequest.getBooking(bookId).jsonPath().getObject("", Book::class.java)

    fun createBook(bookData: Book): Book {
        return bookRequest.createBooking(bookData).jsonPath().getObject("booking", Book::class.java)
    }

    fun updateBook(bookId: Int, bookData: Book, token: String): Book {
        return bookRequest.updateBooking(bookId, bookData, token).jsonPath().getObject("", Book::class.java)
    }


}