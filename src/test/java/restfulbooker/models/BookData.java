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

    private BookData() {
    }

    private BookData(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.totalPrice = builder.totalPrice;
        this.depositPaid = builder.depositPaid;
        this.bookingDates = builder.bookingDates;
        this.additionalNeeds = builder.additionalNeeds;
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private Integer totalPrice;
        private boolean depositPaid;
        private BookingDates bookingDates;
        private String additionalNeeds;

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder totalPrice(Integer totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder depositPaid(boolean depositPaid) {
            this.depositPaid = depositPaid;
            return this;
        }

        public Builder bookingDates(BookingDates bookingDates) {
            this.bookingDates = bookingDates;
            return this;
        }

        public Builder bookingDates(String checkIn, String checkOut) {
            this.bookingDates = new BookingDates(checkIn, checkOut);
            return this;
        }

        public Builder additionalNeeds(String additionalNeeds) {
            this.additionalNeeds = additionalNeeds;
            return this;
        }

        public BookData build() {
            return new BookData(this);
        }

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
        return "BookData{" + "firstName='" + this.firstName + '\'' + ", lastName='" + this.lastName + '\'' + ", totalPrice="
                + this.totalPrice + ", depositPaid=" + this.depositPaid + ", bookingDates=" + this.bookingDates
                + ", additionalNeeds='" + this.additionalNeeds + '\'' + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        BookData that = (BookData) obj;
        return depositPaid == that.depositPaid && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName) && Objects.equals(totalPrice, that.totalPrice)
                && Objects.equals(bookingDates, that.bookingDates) && Objects.equals(additionalNeeds, that.additionalNeeds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, totalPrice, depositPaid, bookingDates, additionalNeeds);
    }
}
