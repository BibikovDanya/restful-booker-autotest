package restfulbooker.tests.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import restfulbooker.models.BookData;

class BookDataTest {
    private BookData bookData;

    @BeforeEach
    public void setUp() {
        bookData = new BookData.Builder()
                .firstName("Jim")
                .lastName("Brown")
                .totalPrice(111)
                .depositPaid(true)
                .bookingDates("2022-01-01", "2022-01-02")
                .additionalNeeds("Breakfast")
                .build();
    }


    @Test
    @Tag("model")
    void equalsSameObjectTest() {
        assertEquals(bookData, bookData);
    }

    @Test
    @Tag("model")
    void equalsNullTest() {
        assertFalse(bookData == null);
    }

    @Test
    @Tag("model")
    void equalsDifferentClassTest() {
        Object obj = new Object();
        assertNotEquals(bookData, obj);
    }

    @Test
    @Tag("model")
    void equalsSameValuesTest() {
        BookData bookData2 = new BookData.Builder()
                .firstName("Jim")
                .lastName("Brown")
                .totalPrice(111)
                .depositPaid(true)
                .bookingDates("2022-01-01", "2022-01-02")
                .additionalNeeds("Breakfast")
                .build();
        assertEquals(bookData, bookData2);
    }

    @Test
    @Tag("model")
    void equalsDifferentValuesFirstNameTest() {
        BookData bookDataDiffFirstName = new BookData.Builder()
                .firstName("Alex")
                .lastName("Brown")
                .totalPrice(111)
                .depositPaid(true)
                .bookingDates("2022-01-01", "2022-01-02")
                .additionalNeeds("Breakfast")
                .build();
        assertNotEquals(bookData, bookDataDiffFirstName);
    }

    @Test
    @Tag("model")
    void equalsDifferentValuesLastNameTest() {
        BookData bookDataDiffLastName = new BookData.Builder()
                .firstName("Jim")
                .lastName("Watson")
                .totalPrice(111)
                .depositPaid(true)
                .bookingDates("2022-01-01", "2022-01-02")
                .additionalNeeds("Breakfast")
                .build();
        assertNotEquals(bookData, bookDataDiffLastName);

    }

    @Test
    @Tag("model")
    void equalsDifferentValuesTotalPriceTest() {
        BookData bookDataDiffTotalPrice = new BookData.Builder()
                .firstName("Jim")
                .lastName("Brown")
                .totalPrice(240)
                .depositPaid(true)
                .bookingDates("2022-01-01", "2022-01-02")
                .additionalNeeds("Breakfast")
                .build();
        assertNotEquals(bookData, bookDataDiffTotalPrice);
    }

    @Test
    @Tag("model")
    void equalsDifferentValuesDepositPaidTest() {
        BookData bookDataDiffDepositPaid = new BookData.Builder()
                .firstName("Jim")
                .lastName("Brown")
                .totalPrice(111)
                .depositPaid(false)
                .bookingDates("2022-01-01", "2022-01-02")
                .additionalNeeds("Breakfast")
                .build();
        assertNotEquals(bookData, bookDataDiffDepositPaid);
    }


    @Test
    @Tag("model")
    void equalsDifferentValuesBookingDatesTest() {
        BookData bookDataDiffBookingDates = new BookData.Builder()
                .firstName("Jim")
                .lastName("Brown")
                .totalPrice(111)
                .depositPaid(true)
                .bookingDates("2022-11-01", "2022-01-02")
                .additionalNeeds("Breakfast")
                .build();
        assertNotEquals(bookData, bookDataDiffBookingDates);
    }

    @Test
    @Tag("model")
    void equalsDifferentValuesAdditionalNeedsTest() {
        BookData bookDataDiffBookingDates = new BookData.Builder()
                .firstName("Jim")
                .lastName("Brown")
                .totalPrice(111)
                .depositPaid(true)
                .bookingDates("2022-01-01", "2022-01-02")
                .additionalNeeds("")
                .build();
        assertNotEquals(bookData, bookDataDiffBookingDates);
    }

    @Test
    @Tag("model")
    public void hashCodeConsistencyTest() {
        BookData bookData2 = new BookData.Builder()
                .firstName("Jim")
                .lastName("Brown")
                .totalPrice(111)
                .depositPaid(true)
                .bookingDates("2022-01-01", "2022-01-02")
                .additionalNeeds("Breakfast")
                .build();
        assertEquals(bookData.hashCode(), bookData2.hashCode());
    }

    @Test
    @Tag("model")
    public void hashCodeSameObjectTest() {
        assertEquals(bookData.hashCode(), bookData.hashCode());
    }

    @Test
    @Tag("model")
    public void hashCodeDifferentValuesTest() {
        BookData bookDataDiffBookingDates = new BookData.Builder()
                .firstName("Jim")
                .lastName("Brown")
                .totalPrice(111)
                .depositPaid(true)
                .bookingDates("2022-11-01", "2022-01-02")
                .additionalNeeds("Breakfast")
                .build();
        assertNotEquals(bookData.hashCode(), bookDataDiffBookingDates.hashCode());

    }

    @Test
    @Tag("model")
    public void toStringBookDataTest() {
        String expected = "BookData{firstName='Jim', lastName='Brown', totalPrice=111, depositPaid=true, bookingDates=BookingDates{checkOut='2022-01-02', checkIn='2022-01-01'}, additionalNeeds='Breakfast'}";
        assertEquals(expected, bookData.toString());
    }
}