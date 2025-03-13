package restfulbooker.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;
import restfulbooker.api.requests.bookings.BookingRequest;
import restfulbooker.helpers.BookingHelpers;
import restfulbooker.utils.Specification;

public class BaseBookingTest {
    protected BookingRequest bookingRequest;
    protected BookingHelpers bookingHelpers;


    private static GenericContainer<?> container;
    private static final String IMAGE_NAME = "restful-booker:latest";
    private static final int PORT = 3001;
    private static String baseUrl;


    @BeforeAll
    public static void initSpecification() {
        container = new GenericContainer<>(DockerImageName.parse(IMAGE_NAME)).withExposedPorts(PORT);
        container.start();

        baseUrl = "http://" + container.getHost() + ":" + container.getMappedPort(PORT);

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
