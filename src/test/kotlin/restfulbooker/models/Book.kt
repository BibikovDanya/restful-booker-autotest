package restfulbooker.models

import com.fasterxml.jackson.annotation.JsonProperty


data class Book(
    @JsonProperty("firstname")
    val firstName: String = "",
    @JsonProperty("lastname")
    val lastName: String = "",
    @JsonProperty("totalprice")
    val totalPrice: Int = -1,
    @JsonProperty("depositpaid")
    val depositPaid: Boolean = false,
    @JsonProperty("bookingdates")
    val bookingDates: BookingDatesNew = BookingDatesNew(),
    @JsonProperty("additionalneeds")
    val additionalNeeds: String? = ""

) {

}

data class BookingDatesNew(
    @JsonProperty("checkin")
    val checkIn: String = "",
    @JsonProperty("checkout")
    val checkout: String = ""
) {

}
