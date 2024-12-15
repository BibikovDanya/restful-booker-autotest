package restfulbooker.tests.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restfulbooker.models.BookData;

class BookDataTest {
    private BookData bookData;

    @BeforeEach
    public void setUp() {
        bookData = new BookData("Jim", "Brown", 111, true, "2022-01-01", "2022-01-02", "Breakfast");
    }


    @Test
    void equalsSameObjectTest() {
        Assertions.assertEquals(bookData, bookData);
    }

    @Test
    void equalsNullTest() {
        Assertions.assertFalse(bookData == null);
    }

    @Test
    void equalsDifferentClassTest() {
        Object obj = new Object();
        Assertions.assertNotEquals(bookData, obj);
    }

    @Test
    void equalsSameValuesTest() {
        BookData bookData2 = new BookData("Jim", "Brown", 111, true, "2022-01-01", "2022-01-02", "Breakfast");
        Assertions.assertEquals(bookData, bookData2);
    }

    @Test
    void equalsDifferentValuesFirstNameTest() {
        BookData bookDataDiffFirstName = new BookData("Alex", "Brown", 111, false, "2022-01-01", "2022-01-02", "Breakfast");
        Assertions.assertNotEquals(bookData, bookDataDiffFirstName);
    }

    @Test
    void equalsDifferentValuesLastNameTest() {
        BookData bookDataDiffLastName = new BookData("Jim", "Watson", 111, true, "2022-01-01", "2022-01-02", "Breakfast");
        Assertions.assertNotEquals(bookData, bookDataDiffLastName);

    }

    @Test
    void equalsDifferentValuesTotalPriceTest() {
        BookData bookDataDiffTotalPrice = new BookData("Jim", "Brown", 240, false, "2022-01-01", "2022-01-02", "Breakfast");
        Assertions.assertNotEquals(bookData, bookDataDiffTotalPrice);
    }

    @Test
    void equalsDifferentValuesDepositPaidTest() {
        BookData bookDataDiffDepositPaid = new BookData("Jim", "Brown", 111, false, "2022-01-01", "2022-01-02", "Breakfast");
        Assertions.assertNotEquals(bookData, bookDataDiffDepositPaid);
    }


    @Test
    void equalsDifferentValuesBookingDatesTest() {
        BookData bookDataDiffBookingDates = new BookData("Jim", "Brown", 111, true, "2022-11-01", "2022-01-02", "Breakfast");
        Assertions.assertNotEquals(bookData, bookDataDiffBookingDates);
    }

    @Test
    void equalsDifferentValuesAdditionalNeedsTest() {
        BookData bookDataDiffBookingDates = new BookData("Jim", "Brown", 111, true, "2022-11-01", "2022-01-02", "");
        Assertions.assertNotEquals(bookData, bookDataDiffBookingDates);
    }

    @Test
    public void hashCodeConsistencyTest() {
        BookData bookData2 = new BookData("Jim", "Brown", 111, true, "2022-01-01", "2022-01-02", "Breakfast");
        Assertions.assertEquals(bookData.hashCode(), bookData2.hashCode());
    }

    @Test
    public void hashCodeSameObjectTest() {
        Assertions.assertEquals(bookData.hashCode(), bookData.hashCode());
    }

    @Test
    public void hashCodeDifferentValuesTest() {
        BookData bookDataDiffBookingDates = new BookData("Jim", "Brown", 111, true, "2022-11-01", "2022-01-02", "Breakfast");
        Assertions.assertNotEquals(bookData.hashCode(), bookDataDiffBookingDates.hashCode());

    }

    @Test
    public void toStringBookDataTest() {
        String expected = "BookData{firstName='Jim', lastName='Brown', totalPrice=111, depositPaid=true, bookingDates=BookingDates{checkOut='2022-01-02', checkIn='2022-01-01'}, additionalNeeds='Breakfast'}";
        Assertions.assertEquals(expected, bookData.toString());
    }
}