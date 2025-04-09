package restfulbooker.api.requests

import com.fasterxml.jackson.module.kotlin.jsonMapper
import io.restassured.http.Method
import io.restassured.response.Response
import restfulbooker.api.ApiRequest

class AuthRequest {

    fun getToken(login: String? = null, pass: String? = null): Response {
        val bodyMap = mutableMapOf<String, String?>()

        login?.let { bodyMap["username"] = login }
        pass?.let { bodyMap["password"] = pass }
        val body = jsonMapper().writeValueAsString(bodyMap)
        return ApiRequest(
            method = Method.POST,
            body = body,
            url = baseUrl
        ).sendRequest()
    }

    companion object {
        val baseUrl = "/auth"
    }
}