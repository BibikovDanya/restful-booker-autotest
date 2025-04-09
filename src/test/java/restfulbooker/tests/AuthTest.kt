package restfulbooker.tests


import org.junit.jupiter.api.Test
import org.apache.http.HttpStatus.SC_OK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.function.Executable
import restfulbooker.api.requests.AuthRequest

class AuthTest : BaseTest() {

    @Test
    @Tag("auth")
    fun getTokenTest() {
        val response = AuthRequest().getToken(adminLogin, adminPassword)
        assertAll(
            Executable { assertNotNull(response.jsonPath().getString("token")) },
            Executable { assertEquals(SC_OK, response.statusCode) },

            )
    }

    @Test
    @Tag("auth")
    fun getTokenBadLoginTest() {
        val response = AuthRequest().getToken("user", adminPassword)
        assertAll(
            Executable { assertEquals(BAD_CREDENTIALS_MESSAGE, response.jsonPath().getString("reason")) },
            Executable { assertEquals(SC_OK, response.statusCode) },

            )
    }

    @Test
    @Tag("auth")
    fun getTokenBadPassTest() {
        val response = AuthRequest().getToken(adminLogin, adminPassword.drop(3))
        assertAll(
            Executable { assertEquals(BAD_CREDENTIALS_MESSAGE, response.jsonPath().getString("reason")) },
            Executable { assertEquals(SC_OK, response.statusCode) },

            )
    }

    @Test
    @Tag("auth")
    fun getTokenLoginNoPresentTest() {
        val response = AuthRequest().getToken(pass = adminPassword)
        assertAll(
            Executable { assertEquals(BAD_CREDENTIALS_MESSAGE, response.jsonPath().getString("reason")) },
            Executable { assertEquals(SC_OK, response.statusCode) },

            )
    }

    @Test
    @Tag("auth")
    fun getTokenPassNoPresentTest() {
        val response = AuthRequest().getToken(login = adminLogin)
        assertAll(
            Executable { assertEquals(BAD_CREDENTIALS_MESSAGE, response.jsonPath().getString("reason")) },
            Executable { assertEquals(SC_OK, response.statusCode) },

            )

    }

    companion object {
       const val BAD_CREDENTIALS_MESSAGE = "Bad credentials"
    }

}