package org.example;

/**
 * Hello world!
 *
 */
public class App extends BookingInterface
{
    public static void main( String[] args )
    {
        String token = getTokenRequest();
        String bodyOfNewBooking = "{\n" +
                "  \"firstname\":\"Yuri\",\n" +
                "  \"lastname\":\"Gluhih\",\n" +
                "  \"totalprice\":851,\n" +
                "  \"depositpaid\":true,\n" +
                "  \"bookingdates\": {\n" +
                "  \"checkin\":\"2020-10-22\",\n" +
                "  \"checkout\":\"2020-10-28\"\n" +
                "  },\n" +
                "  \"additionalneeds\":\"Breakfast\"\n" +
                "}";
        createBooking(bodyOfNewBooking);
        getBookingById(12);
    }

}
