package restfulbooker.tests

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import restfulbooker.utils.SpecificationNew.requestSpec
import restfulbooker.utils.SpecificationNew.installSpecification
import java.util.Base64

@Testcontainers
open class BaseTest {
    protected fun getAuthHeader(login: String, password: String): String {
        val auth = "$login:$password"
        val encodedAuth = Base64.getEncoder().encode(auth.toByteArray())
        return "Basic " + String(encodedAuth)
    }

    companion object {
        private const val IMAGE_NAME: String = "restful-booker:latest"
        private const val PORT: Int = 3001
        lateinit var adminLogin: String
        lateinit var adminPassword: String
        private val logger: Logger = LogManager.getLogger(this::class.java)

        @Container
        @JvmStatic
        var container: GenericContainer<*> = GenericContainer(DockerImageName.parse(IMAGE_NAME)).withExposedPorts(PORT)


        init {
            container.start()
            logger.info("Container is started? ${container.isRunning} on ${container.host}:${container.firstMappedPort} ")
        }

        @JvmStatic
        @BeforeAll
        fun setupSpec() {
            installSpecification(requestSpec(baseUrl = getBaseUrl()))

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
