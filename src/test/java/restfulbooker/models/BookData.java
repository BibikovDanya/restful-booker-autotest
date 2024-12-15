package restfulbooker.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class BookData {
    @JsonProperty("firstname")
    private String firstName;

    @JsonProperty("lastname")
    private String lastName;

    @JsonProperty("totalprice")
    private Integer totalPrice;

    @JsonProperty("depositpaid")
    private boolean depositPaid;

    @JsonProperty("bookingdates")
    private BookingDates bookingDates;

    @JsonProperty("additionalneeds")
    private String additionalNeeds;

    public BookData() {
    }

    public BookData(String firstName, String lastName, Integer totalPrice, boolean depositPaid, BookingDates bookingDates, String additionalNeeds) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPrice = totalPrice;
        this.depositPaid = depositPaid;
        this.bookingDates = bookingDates;
        this.additionalNeeds = additionalNeeds;
    }

    public BookData(String firstName, String lastName, Integer totalPrice, boolean depositPaid, String checkIn, String checkOut,  String additionalNeeds) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPrice = totalPrice;
        this.depositPaid = depositPaid;
        this.bookingDates = new BookingDates(checkIn, checkOut);
        this.additionalNeeds = additionalNeeds;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public boolean isDepositPaid() {
        return depositPaid;
    }

    public BookingDates getBookingDates() {
        return bookingDates;
    }

    public String getAdditionalNeeds() {
        return additionalNeeds;
    }

    @Override
    public String toString() {
        return "BookData{" +
                "firstName='" + this.firstName + '\'' +
                ", lastName='" + this.lastName + '\'' +
                ", totalPrice=" + this.totalPrice +
                ", depositPaid=" + this.depositPaid +
                ", bookingDates=" + this.bookingDates +
                ", additionalNeeds='" + this.additionalNeeds + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BookData that = (BookData) obj;
        return depositPaid == that.depositPaid &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(totalPrice, that.totalPrice) &&
                Objects.equals(bookingDates, that.bookingDates) &&
                Objects.equals(additionalNeeds, that.additionalNeeds);
    }


    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, totalPrice, depositPaid, bookingDates, additionalNeeds);
    }
}

