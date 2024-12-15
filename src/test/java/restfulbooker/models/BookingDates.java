package restfulbooker.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class BookingDates {
    @JsonProperty("checkin")
    private String checkIn;
    @JsonProperty("checkout")
    private String checkOut;

    public BookingDates() {
    }

    public BookingDates(String checkIn, String checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "BookingDates{" +
                "checkOut='" + this.checkOut + '\'' +
                ", checkIn='" + this.checkIn + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BookingDates that = (BookingDates) obj;
        return Objects.equals(checkIn, that.checkIn) && Objects.equals(checkOut, that.checkOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkIn, checkOut);
    }
}
