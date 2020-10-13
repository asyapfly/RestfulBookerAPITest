package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.http.ContentType.JSON;

public class BookingInterface {
    @Getter
    @Setter
    private static String token;

    /*
    * Получает booking по Id
    * */

    public static Integer getBookingById(Integer id){
        try {

            Integer statusCode = RestAssured
                    .given()

                    .when().get("https://restful-booker.herokuapp.com/booking/" + id.toString())

                    .then()
                    .extract()
                    .statusCode();
            return statusCode;
        }
       catch (Exception ex){
           System.out.println("Внимание! Возникло исключение: " + ex.getMessage());
           return null;
       }
    }

    /*
    * Проходит авторизацию и получает куки(токен)
    * */

    public static String getTokenRequest(){
        Map<String,String> data = new HashMap<>();
        data.put("username","admin");
        data.put("password","password123");
        String response = "";
            try {
                 response =
                     RestAssured
                        .given()

                        .body(data)

                        .contentType(ContentType.JSON)
                        .header("Accept","*/*")
                        .header("Accept-Encoding","gzip, deflate, br")
                        .header("Connection","keep-alive")

                        .when()
                        .post("https://restful-booker.herokuapp.com/auth")

                        .then()
                        .contentType(ContentType.JSON)
                        .statusCode(200)
                        .extract()
                        .path("token");

                System.out.println("Your token is: " + response);
                return response;
            }
            catch (Exception ex){
                System.out.println("Внимание! Возникло исключение: " + ex.getMessage());
                return response;
            }
    }

    /*
    * Создаёт новый заказ из
    * */

    public static String createBooking(String jsonBody){

        String bodyOfNewBooking = "{\n" +
                "  \"firstname\":\"Vitaly\",\n" +
                "  \"lastname\":\"Mutko\",\n" +
                "  \"totalprice\":145,\n" +
                "  \"depositpaid\":true,\n" +
                "  \"bookingdates\": {\n" +
                "  \"checkin\":\"2020-10-15\",\n" +
                "  \"checkout\":\"2020-10-22\"\n" +
                "  },\n" +
                "  \"additionalneeds\":\"Breakfast\"\n" +
                "}";

        if (jsonBody.equals("")){
            jsonBody = bodyOfNewBooking;
        }
        String bookingId = "";
        try {
            ValidatableResponse validatableResponse = RestAssured
                    .given()
                    .log()
                    .body()

                    .body(jsonBody)

                    .contentType(ContentType.JSON)

                    .when()
                    .post("https://restful-booker.herokuapp.com/booking")

                    .then()
                    .contentType(JSON)
                    .statusCode(200);
            bookingId = validatableResponse.extract().path("bookingid").toString();
            System.out.println(validatableResponse.extract().response().body().prettyPrint());
            System.out.println("Заказ успешно добавлен. Полученный Id: " + bookingId);
            return bookingId;
        }
        catch (Exception ex){
            System.out.println("Внимание! Возникло исключение: " + ex.getMessage());
            return bookingId;
        }
    }
}
