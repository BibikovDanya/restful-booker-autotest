package restfulbooker.api.requests.bookings

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import io.restassured.http.Method
import io.restassured.response.Response
import restfulbooker.api.ApiRequest
import restfulbooker.models.Book

class BookRequest {
    val baseUrl = "/booking"
    private val mapper = jacksonObjectMapper()

    fun getBookingIds(params: Map<String, String>? = null): Response = ApiRequest(Method.GET, baseUrl, queryParams = params).sendRequest()

    fun getBooking(id: Int): Response = ApiRequest(Method.GET, "$baseUrl/$id").sendRequest()

    fun createBooking(book: Book): Response {
        val objectMapper = ObjectMapper().registerModule(kotlinModule())
        val jsonBody = objectMapper.writeValueAsString(book)
        return ApiRequest(Method.POST, baseUrl, body = jsonBody).sendRequest()
    }

    fun updateBooking(
        bookId: Int,
        bookData: Book,
        token: String?,
    ): Response {
        val headers = token?.let { mapOf(AUTH_HEADER to token) } ?: emptyMap()
        return ApiRequest(
            method = Method.PUT,
            url = "$baseUrl/$bookId",
            body = bookData,
            headers = headers,
        ).sendRequest()
    }

    fun partialUpdateBooking(
        bookId: Int,
        params: Map<String, String>?,
        token: String?,
    ): Response {
        val headers = token?.let { mapOf(AUTH_HEADER to token) } ?: emptyMap()
        val body = mapper.writeValueAsString(params)
        return ApiRequest(
            method = Method.PATCH,
            url = "$baseUrl/$bookId",
            body = body,
            headers = headers,
        ).sendRequest()
    }

    fun deleteBook(
        bookId: Int,
        token: String?,
    ): Response {
        val headers = token?.let { mapOf(AUTH_HEADER to token) ?: emptyMap() }
        return ApiRequest(
            method = Method.DELETE,
            url = "$baseUrl/$bookId",
            headers = headers,
        ).sendRequest()
    }

    companion object {
        const val AUTH_HEADER = "Authorization"
    }
}
