package restfulbooker.api

import io.restassured.RestAssured
import io.restassured.http.Method
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification

class ApiRequest(
    val method: Method = Method.GET, val url: String = "",
    val queryParams: Map<String, String?>? = null,
    val headers: Map<String, String?>? = null,
    val body: Any? = null,
) {

    fun sendRequest(): Response {
        val request: RequestSpecification = RestAssured.given().log().all()
        queryParams?.let {
            request.queryParams(it)
        }
        headers?.let {
            request.headers(it)
        }
        return when (method) {
            Method.GET -> request.get(url).then().log().all().extract().response()
            Method.POST -> request.body(body).post(url).then().log().all().extract().response()
            Method.PUT -> request.body(body).put(url).then().log().all().extract().response()
            Method.PATCH -> request.body(body).patch(url).then().log().all().extract().response()
            else -> throw IllegalArgumentException("Invalid method type: $method")
        }
    }


}