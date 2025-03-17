package restfulbooker.api.requests.bookings

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import io.restassured.http.Method
import io.restassured.response.Response
import restfulbooker.api.ApiRequestNew
import restfulbooker.models.Book

class BookRequest {
    val baseUrl = "/booking"

    fun getBookingIds(params: Map<String, String>? = null): Response =
        ApiRequestNew(Method.GET, baseUrl, queryParams = params).sendRequest()


    fun getBooking(id: Int): Response = ApiRequestNew(Method.GET, "$baseUrl/$id").sendRequest()

    fun createBooking(book: Book): Response {
        val objectMapper = ObjectMapper().registerModule(kotlinModule())
        val jsonBody = objectMapper.writeValueAsString(book)
        return ApiRequestNew(Method.POST, baseUrl, body = jsonBody).sendRequest()
    }

    fun updateBooking(bookId: Int, bookData: Book, token: String): Response {
        return ApiRequestNew(
            method = Method.PUT, url = "$baseUrl/$bookId", body = bookData, headers = mapOf("Authorization" to token)
        ).sendRequest()
    }

}