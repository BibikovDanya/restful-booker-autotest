package restfulbooker.tests

import org.apache.http.HttpStatus.SC_OK
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import restfulbooker.api.requests.AuthRequest

class AuthTest : BaseTest() {
    @Test
    @Tag("auth")
    fun getTokenTest() {
        val response = AuthRequest().getToken(adminLogin, adminPassword)
        assertAll(
            { assertNotNull(response.jsonPath().getString("token")) },
            { assertEquals(SC_OK, response.statusCode) },
        )
    }

    @Test
    @Tag("auth")
    fun getTokenBadLoginTest() {
        val response = AuthRequest().getToken("user", adminPassword)
        assertAll(
            { assertEquals(BAD_CREDENTIALS_MESSAGE, response.jsonPath().getString(REASON)) },
            { assertEquals(SC_OK, response.statusCode) },
        )
    }

    @Test
    @Tag("auth")
    fun getTokenBadPassTest() {
        val response = AuthRequest().getToken(adminLogin, adminPassword.drop(3))
        assertAll(
            { assertEquals(BAD_CREDENTIALS_MESSAGE, response.jsonPath().getString(REASON)) },
            { assertEquals(SC_OK, response.statusCode) },
        )
    }

    @Test
    @Tag("auth")
    fun getTokenLoginNoPresentTest() {
        val response = AuthRequest().getToken(pass = adminPassword)
        assertAll(
            { assertEquals(BAD_CREDENTIALS_MESSAGE, response.jsonPath().getString(REASON)) },
            { assertEquals(SC_OK, response.statusCode) },
        )
    }

    @Test
    @Tag("auth")
    fun getTokenPassNoPresentTest() {
        val response = AuthRequest().getToken(login = adminLogin)
        assertAll(
            { assertEquals(BAD_CREDENTIALS_MESSAGE, response.jsonPath().getString(REASON)) },
            { assertEquals(SC_OK, response.statusCode) },
        )
    }

    companion object {
        const val REASON = "reason"
        const val BAD_CREDENTIALS_MESSAGE = "Bad credentials"
    }
}
