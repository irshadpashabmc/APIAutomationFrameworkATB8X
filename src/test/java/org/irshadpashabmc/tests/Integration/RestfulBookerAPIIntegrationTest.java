package org.irshadpashabmc.tests.Integration;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
import org.hamcrest.Matchers;
import org.irshadpashabmc.base.BaseTest;
import org.irshadpashabmc.endpoints.APIConstants;
import org.irshadpashabmc.pojos.BookingidResponse;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class RestfulBookerAPIIntegrationTest extends BaseTest {

    @Link(name = "TC_001", url = "www.jira.com/Testcases/TC_001")
    @Description("Verify Create Booking functionality by using POST Method")
    @Test (groups = {"QA","Dev","Integration"},priority = 1)
    public void VerifyCreateBookingTest(ITestContext iTestContext) {

        Req_Spec.basePath(APIConstants.Booking_BasePath);
        Req_Spec.body(Req_Payload.CreateBookingPayload());
        Res = Req_Spec.log().all().when().post();
        // Capture booking id from response
        Integer booking_id = Res.jsonPath().getInt("bookingid");
        System.out.println("This is generated bookingid:"+booking_id);

        Val_Res = Res.then().log().all();

        String CreatedbookingidResponse_JSON = Res.asString();
        BookingidResponse Booking_Response_Data = Req_Payload.BookingResponsePayload(CreatedbookingidResponse_JSON);

        System.out.println("This is bookingresponsedata" +Booking_Response_Data);

          iTestContext.setAttribute("bookingid",Booking_Response_Data.getBookingid());

    }

    @Link(name = "TC_002", url = "www.jira.com/Testcases/TC_002")
    @Description("Verify Created Booking id by using GET Method")
    @Test (groups = {"QA","Dev","Integration"},priority = 2)
    public void VerifyCreatedBookingIDTest(ITestContext iTestContext) {

       // getAuthToken();
       iTestContext.setAttribute("token",getAuthToken());

        Integer BookingID = (Integer) iTestContext.getAttribute("bookingid");

        Req_Spec.basePath(APIConstants.Booking_BasePath+"/"+BookingID);

        Res = Req_Spec.log().all().when().get();
        Val_Res = Res.then().log().all();
        Val_Res.statusCode(200);

    }

    @Link(name = "TC_003", url= "www.jira.com/Testcases/TC_003")
    @Description("Verify updating Created Booking by using PUT Method")
    @Test (groups = {"QA","Dev","Integration"},priority = 3)
    public void UpdateBookingTest(ITestContext iTestContext) {

        String ResponseToken = (String) iTestContext.getAttribute("token");

        Integer BookingID = (Integer) iTestContext.getAttribute("bookingid");

        Req_Spec.basePath(APIConstants.Booking_BasePath+"/"+BookingID);
        Req_Spec.body(Req_Payload.UpdateBookingPayload());
        Req_Spec.cookie("token",ResponseToken);
        Res = Req_Spec.log().all().when().put();
        Val_Res = Res.then().log().all();
        Val_Res.statusCode(200);

    }

    @Link(name = "TC_004", url = "www.jira.com/Testcases/TC_004")
    @Description("Partially updating Created Booking by using PATCH Method")
    @Test (groups = {"QA","Dev","Integration"},priority = 4)
    public void PartialUpdatedBookingTest(ITestContext iTestContext) {

        String ResponseToken = (String) iTestContext.getAttribute("token");
        Integer BookingID = (Integer) iTestContext.getAttribute("bookingid");

        Req_Spec.basePath(APIConstants.Booking_BasePath+"/"+BookingID);
        Req_Spec.body(Req_Payload.PartialUpdateBookingPayload());
        Req_Spec.cookie("token",ResponseToken);
        Res = Req_Spec.log().all().when().patch();
        Val_Res = Res.then().log().all();
        Val_Res.statusCode(200);
        // validation

        String FirstName = Val_Res.extract().path("firstname");
        String LastName = Val_Res.extract().path("lastname");
        System.out.println("This is firstname:"+FirstName);
        System.out.println("This is lastname:"+LastName);

        // assertions
        Assert.assertEquals(FirstName,"Irshad");
        Assert.assertEquals(LastName,"Pasha");

        //Hamcrest
        Val_Res.body("firstname",Matchers.equalTo("Irshad"));
        Val_Res.body("lastname",Matchers.equalTo("Pasha"));

        String PartialUpdateResponsePrint = Res.asString();
        System.out.println("This is Partial response Print"+PartialUpdateResponsePrint);

        Val_Res.statusCode(200);

    }

    @Link(name = "TC_005", url = "www.jira.com/Testcases/TC_005")
    @Description("Delete Created Booking by using DELETE Method")
    @Test (groups = {"QA","Dev","Integration"},priority = 5)
    public void DeleteBookingTest(ITestContext iTestContext) {

        String ResponseToken = (String) iTestContext.getAttribute("token");
        Integer BookingID = (Integer) iTestContext.getAttribute("bookingid");

        Req_Spec.basePath(APIConstants.Booking_BasePath+"/"+BookingID);
        Req_Spec.cookie("token",ResponseToken);
        Res = Req_Spec.log().all().when().delete();
        Val_Res = Res.then().log().all();
        Val_Res.statusCode(201);

    }

}



