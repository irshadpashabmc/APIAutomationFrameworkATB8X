package org.irshadpashabmc.modules;

import com.google.gson.Gson;
import org.irshadpashabmc.pojos.*;

public class PayloadManager {

// Restful booker Create bookingid Request payload details

    public String CreateBookingPayload() {

        Booking booking = new Booking();
        booking.setFirstname("James");
        booking.setLastname("camron");
        booking.setTotalprice(1000);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-02");
        bookingdates.setCheckout("2024-02-04");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        // serialization of data
        Gson gson = new Gson();
        String Booking_Gson = gson.toJson(booking);
        System.out.println(Booking_Gson);

        return Booking_Gson;
    }

    public BookingidResponse BookingResponsePayload(String Booking_Response) {

        Gson BookingResponse_Gson = new Gson();

        BookingidResponse BR = BookingResponse_Gson.fromJson(Booking_Response,BookingidResponse.class);

        return BR;
    }

public Booking bookingresponse(String GetResponse) {

        Gson BK_GSON = new Gson();
        Booking booking_response = BK_GSON.fromJson(GetResponse, Booking.class);
        return booking_response;
}

    public String AuthTokenPayload() {

        AuthToken Auth_Token = new AuthToken();

        Auth_Token.setUsername("admin");
        Auth_Token.setPassword("password123");

        Gson Auth_Gson = new Gson();
        // Serialization
        String AuthToken_Gson = Auth_Gson.toJson(Auth_Token);
        System.out.println("This is AuthTokenJson:"+AuthToken_Gson);

        return AuthToken_Gson;

    }

    public String AuthTokenResponsePayload(String Token_Response) {

        Gson AuthTokenResponse_Gson = new Gson();

        TokenResponse TR = AuthTokenResponse_Gson.fromJson(Token_Response, TokenResponse.class);

         return TR.getToken();
    }



    public String UpdateBookingPayload() {

        Booking Update_Booking = new Booking();

        Update_Booking.setFirstname("Price");
        Update_Booking.setLastname("Charles");
        Update_Booking.setTotalprice(888);
        Update_Booking.setDepositpaid(true);

        Bookingdates Update_BookingDates = new Bookingdates();
        Update_BookingDates.setCheckin("2024-05-08");
        Update_BookingDates.setCheckout("2024-06-09");
        Update_Booking.setBookingdates(Update_BookingDates);
        Update_Booking.setAdditionalneeds("Breakfast");

        Gson Update_gson = new Gson();

        String UpdateBooking_Gson = Update_gson.toJson(Update_Booking);
        System.out.println("This is Updated booking payload:" +UpdateBooking_Gson);

        return UpdateBooking_Gson;
    }

    public String PartialUpdateBookingPayload() {

        PartialUpdateBooking PartialUpdate = new PartialUpdateBooking();

        PartialUpdate.setFirstname("Irshad");
        PartialUpdate.setLastname("Pasha");

        //Serialization
        Gson GSON = new Gson();

        String PartialUpdate_GSON = GSON.toJson(PartialUpdate);

        return PartialUpdate_GSON;
    }


}
