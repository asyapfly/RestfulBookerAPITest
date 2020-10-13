package org.example.steps;

import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.Когда;
import org.example.BookingInterface;
import org.junit.Assert;

import static org.example.BookingInterface.createBooking;
import static org.example.BookingInterface.getBookingById;

public class MyBookingTestSteps {

    @Дано("^Пользователь получает токен$")
    public void пользовательПолучаетТокен() {
        String response = BookingInterface.getTokenRequest();
        Assert.assertFalse(response.equals(""));
    }

    @Когда("^Пользователь создает заказ c данными$")
    public void пользовательСоздаетЗаказCДанными() {
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
        Assert.assertFalse(createBooking(bodyOfNewBooking).equals(""));
    }

    @Дано("^Пользователь получает данные о заказе <id>$")
    public void пользовательПолучаетДанныеОЗаказеId(Integer Id) {
        Integer statusCode = 200;
        Assert.assertEquals(statusCode, getBookingById(Id));
    }

}
