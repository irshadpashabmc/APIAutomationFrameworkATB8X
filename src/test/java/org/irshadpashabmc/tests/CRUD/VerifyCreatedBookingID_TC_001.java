package org.irshadpashabmc.tests.CRUD;

import com.google.gson.Gson;
import io.qameta.allure.*;
import org.irshadpashabmc.base.BaseTest;
import org.irshadpashabmc.endpoints.APIConstants;
import org.irshadpashabmc.modules.PayloadManager;
import org.irshadpashabmc.pojos.Bookingdates;
import org.irshadpashabmc.pojos.BookingidResponse;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class VerifyCreatedBookingID_TC_001 extends BaseTest {

    @Owner("MdIrshadPasha")
    @Link(name = "testcase name",url ="www.jira.com/1234")
    @TmsLink("DRIIT = 0000")
    @Issue("As of now no ID")
    @Description("Verify the Created Booking id using POST Method")
    @Test (groups = "QA",priority = 1)
    public void VerifyCreatedBookingIDUsingPost() {

        Req_Spec.basePath(APIConstants.Booking_BasePath);
        Req_Spec.body(Req_Payload.CreateBookingPayload());
        Res = Req_Spec.log().all().when().post();

        // Capture booking id from response
        Integer booking_id = Res.jsonPath().getInt("bookingid");
        System.out.println("This is generated bookingid:"+booking_id);

        // Validations

        Val_Res = Res.then().log().all();

        // Deserilization

        String CreatedbookingidResponse_JSON = Res.asString();
     //   Gson G = new Gson();
     //   BookingidResponse BIDR = G.fromJson(CreatedbookingidResponse_JSON, BookingidResponse.class);

        // BookingidResponse BidRespons

        BookingidResponse Booking_Response_Data = Req_Payload.BookingResponsePayload(CreatedbookingidResponse_JSON);


    }

}
