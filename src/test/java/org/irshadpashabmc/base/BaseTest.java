package org.irshadpashabmc.base;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.irshadpashabmc.asserts.AssertActions;
import org.irshadpashabmc.endpoints.APIConstants;
import org.irshadpashabmc.modules.PayloadManager;
import org.irshadpashabmc.pojos.TokenResponse;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    // Here in the Base Test, we have pre-conditions and post conditions
    public RequestSpecification Req_Spec;
    public Response Res;
    public ValidatableResponse Val_Res;
    public AssertActions Assert_Actions;
    public PayloadManager Req_Payload;
    public JsonPath JsonPath;

    @BeforeTest
    public void setup() {

        Req_Payload = new PayloadManager();

        Req_Spec = RestAssured.given();
        Req_Spec.baseUri(APIConstants.BASE_URL);
        Req_Spec.contentType(ContentType.JSON);

    }

    public String getAuthToken() {

        Req_Spec.basePath(APIConstants.Auth_BasePath);
        Req_Spec.body(Req_Payload.AuthTokenPayload());
        Res = Req_Spec.log().all().when().post();

        // Capture token id

        String Token = Res.jsonPath().getString("token");
        System.out.println("This is Generated Token:"+Token);

        // Capture response

        String CaptureAuthResponse = Res.asString();
        Val_Res = Res.then().log().all();

     //   String TKN = Req_Payload.AuthTokenResponsePayload(CaptureAuthResponse);
       String  TokenResponse  = Req_Payload.AuthTokenResponsePayload(CaptureAuthResponse);


        return TokenResponse;
    }



}
