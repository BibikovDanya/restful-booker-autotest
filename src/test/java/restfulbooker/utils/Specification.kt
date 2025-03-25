package restfulbooker.utils

import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.builder.ResponseSpecBuilder
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import io.restassured.specification.ResponseSpecification
import org.apache.http.HttpStatus.*

object SpecificationNew {
    fun requestSpec(
        baseUrl: String, basePath: String? = null,
    ): RequestSpecification = RequestSpecBuilder()
        .setBaseUri(baseUrl)
        .apply { basePath?.let { setBasePath(it) } }
        .setContentType(ContentType.JSON)
        .build()

    fun responseSpec(statusCode: Int): ResponseSpecification {
        return ResponseSpecBuilder()
            .expectStatusCode(statusCode)
            .build()
    }

    fun responseSpecOk() = responseSpec(SC_OK)
    fun responseSpecBadRequest() = responseSpec(SC_BAD_REQUEST)
    fun responseSpecNotFound() = responseSpec(SC_NOT_FOUND)

    fun installSpecification(request: RequestSpecification) {
        RestAssured.requestSpecification = request
    }

    fun installSpecification(response: ResponseSpecification) {
        RestAssured.responseSpecification = response
    }

    fun installSpecification(
        request: RequestSpecification, response: ResponseSpecification,
    ) {
        RestAssured.requestSpecification = request
        RestAssured.responseSpecification = response
    }


}