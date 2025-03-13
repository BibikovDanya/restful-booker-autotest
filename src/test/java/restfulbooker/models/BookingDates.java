package restfulbooker.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class BookingDates implements Cloneable {
    @JsonProperty("checkin")
    private String checkIn;
    private String checkout;


    public BookingDates() {
    }

    public BookingDates(String checkIn, String checkout) {
        this.checkIn = checkIn;
        this.checkout = checkout;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    @Override
    public BookingDates clone() throws CloneNotSupportedException {
        return (BookingDates) super.clone();
    }

    @Override
    public String toString() {
        return "BookingDates{" +
                "checkOut='" + this.checkout + '\'' +
                ", checkIn='" + this.checkIn + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BookingDates that = (BookingDates) obj;
        return Objects.equals(checkIn, that.checkIn) && Objects.equals(checkout, that.checkout);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkIn, checkout);
    }
}
