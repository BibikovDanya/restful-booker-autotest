package restfulbooker.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;
import restfulbooker.api.requests.bookings.BookingRequest;
import restfulbooker.helpers.BookingHelpers;
import restfulbooker.utils.Specification;

import java.io.IOException;
import java.util.Base64;

public class BaseBookingTest {
    protected BookingRequest bookingRequest;
    protected BookingHelpers bookingHelpers;

    protected static String adminLogin;
    protected static String adminPassword;

    private static GenericContainer<?> container;
    private static final String IMAGE_NAME = "restful-booker:latest";
    private static final int PORT = 3001;


    @BeforeAll
    public static void getProperty() throws IOException {

        System.getProperties().load(ClassLoader.getSystemResourceAsStream("credentials.properties"));
        adminLogin = System.getProperty("admin.login");
        adminPassword = System.getProperty("admin.pass");

    }

    protected String getAuthHeader() {
        String auth = adminLogin + ":" + adminPassword;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        return "Basic " + new String(encodedAuth);
    }


    @BeforeAll
    public static void initSpecification() {
        container = new GenericContainer<>(DockerImageName.parse(IMAGE_NAME)).withExposedPorts(PORT);
        container.start();

        String baseUrl = "http://" + container.getHost() + ":" + container.getMappedPort(PORT);

        Specification.installSpecification(Specification.requestSpec(baseUrl));
        Specification.installSpecification(Specification.responseSpecOK200());

    }

    @AfterAll
    public static void tearDown() {
        if (container != null) {
            container.stop();
        }
    }

    @BeforeEach
    public void setUp() {
        bookingRequest = new BookingRequest();
        bookingHelpers = new BookingHelpers();

    }

}
