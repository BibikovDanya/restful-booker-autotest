package restfulbooker.tests.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import restfulbooker.models.BookData;
import restfulbooker.models.BookingDates;

class BookDataTest {


    @Test
    void equalsSameObjectTest() {
//        BookingDates bookingDates = new BookingDates("2022-01-01", "2022-01-02");
//        BookData book = new BookData("Jim", "Brown", 111, true, bookingDates, "Breakfast");
        BookData book = new BookData("Jim", "Brown", 111, true, "2022-01-01", "2022-01-02", "Breakfast");
        Assertions.assertEquals(book, book);
    }

    @Test
    void equalsNullTest() {
        BookingDates bookingDates = new BookingDates("2022-01-01", "2022-01-02");
        BookData book = new BookData("Jim", "Brown", 111, true, bookingDates, "Breakfast");
        Assertions.assertNotEquals(book, null);
    }

    @Test
    void equalsDifferentClassTest() {
        Object obj = new Object();
        BookingDates bookingDates = new BookingDates("2022-01-01", "2022-01-02");
        BookData book = new BookData("Jim", "Brown", 111, true, bookingDates, "Breakfast");
        Assertions.assertNotEquals(book, obj);
    }

    @Test
    void equalsSameValuesTest() {
        BookingDates bookingDates = new BookingDates("2022-01-01", "2022-01-02");
        BookingDates bookingDates2 = new BookingDates("2022-01-01", "2022-01-02");
        BookData book = new BookData("Jim", "Brown", 111, true, bookingDates, "Breakfast");
        BookData book2 = new BookData("Jim", "Brown", 111, true, bookingDates2, "Breakfast");
        Assertions.assertEquals(book, book2);
    }

    @Test
    void equalsDifferentValuesDepositPaidTest() {
        BookingDates bookingDates = new BookingDates("2022-01-01", "2022-01-02");
        BookingDates bookingDates2 = new BookingDates("2022-01-01", "2022-01-02");
        BookData book = new BookData("Jim", "Brown", 111, true, bookingDates, "Breakfast");
        BookData book2 = new BookData("Jim", "Brown", 111, false, bookingDates2, "Breakfast");
        Assertions.assertNotEquals(book, book2);
    }

    @Test
    void equalsDifferentValuesLastNameTest() {
        BookingDates bookingDates = new BookingDates("2022-01-01", "2022-01-02");
        BookingDates bookingDates2 = new BookingDates("2022-01-01", "2022-01-02");
        BookData book = new BookData("", "Brown", 111, true, bookingDates, "Breakfast");
        BookData book2 = new BookData("Jim", "Watson", 111, true, bookingDates2, "Breakfast");
        Assertions.assertNotEquals(book, book2);
    }

    @Test
    void equalsDifferentValuesBookingDatesTest() {
        BookingDates bookingDates = new BookingDates("2022-01-01", "2022-01-02");
        BookingDates bookingDates2 = new BookingDates("2022-11-11", "2022-01-02");
        BookData book = new BookData("Jim", "Brown", 111, true, bookingDates, "Breakfast");
        BookData book2 = new BookData("Jim", "Brown", 111, true, bookingDates2, "Breakfast");
        Assertions.assertNotEquals(book, book2);
    }

    @Test
    public void testHashCode_Consistency() {
        BookingDates bookingDates = new BookingDates("2022-01-01", "2022-01-02");
        BookingDates bookingDates2 = new BookingDates("2022-01-01", "2022-01-02");
        BookData book = new BookData("Jim", "Brown", 111, true, bookingDates, "Breakfast");
        BookData book2 = new BookData("Jim", "Brown", 111, true, bookingDates2, "Breakfast");
        Assertions.assertEquals(book.hashCode(), book2.hashCode());

    }

    @Test
    public void hashCodeDifferentValuesTest() {
        BookingDates bookingDates = new BookingDates("2022-01-01", "2022-01-02");
        BookingDates bookingDates2 = new BookingDates("2022-01-01", "2024-01-02");
        BookData book = new BookData("Jim", "Brown", 111, true, bookingDates, "Breakfast");
        BookData book2 = new BookData("Jim", "Brown", 111, true, bookingDates2, "Breakfast");
        Assertions.assertNotEquals(book.hashCode(), book2.hashCode());

    }

    @Test
    public void toStringBookDataTest() {
        BookingDates bookingDates = new BookingDates("2022-01-01", "2022-01-02");
        BookData book = new BookData("Jim", "Brown", 111, true, bookingDates, "Breakfast");
        String expected = "BookData{firstName='Jim', lastName='Brown', totalPrice=111, depositPaid=true, bookingDates=BookingDates{checkOut='2022-01-02', checkIn='2022-01-01'}, additionalNeeds='Breakfast'}";
        Assertions.assertEquals(expected, book.toString());
    }
}