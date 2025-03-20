package restfulbooker.tests

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.testcontainers.containers.GenericContainer
import org.testcontainers.utility.DockerImageName
import restfulbooker.api.requests.bookings.BookRequest
import restfulbooker.helpers.BookHelpers
import restfulbooker.utils.Specification
import java.util.*

open class BaseBookTest {
    protected fun getAuthHeader(login: String, password: String): String {
        val auth = "$login:$password"
        val encodedAuth = Base64.getEncoder().encode(auth.toByteArray())
        return "Basic " + String(encodedAuth)
    }

    companion object {
        private var container: GenericContainer<*>
        private const val IMAGE_NAME: String = "restful-booker:latest"
        private const val PORT: Int = 3001
        val bookRequest: BookRequest = BookRequest()
        val bookHelpers: BookHelpers = BookHelpers()
        lateinit var adminLogin: String
        lateinit var adminPassword: String

        init {
            container = GenericContainer(DockerImageName.parse(IMAGE_NAME)).withExposedPorts(PORT)
            container.start()
            Specification.installSpecification(Specification.requestSpec(getBaseUrl()))
        }

        @JvmStatic
        @BeforeAll
        fun getProperties() {
            System.getProperties().load(ClassLoader.getSystemResourceAsStream("credentials.properties"))
            adminLogin = System.getProperty("admin.login")
            adminPassword = System.getProperty("admin.pass")
        }

        private fun getBaseUrl(): String {
            return "http://${container.host}:${container.firstMappedPort}"
        }

        @JvmStatic
        @AfterAll
        fun tearDown() {
            container.stop()
        }
    }

}
