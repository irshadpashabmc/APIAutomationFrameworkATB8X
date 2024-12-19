package org.irshadpashabmc.tests.CRUD;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.irshadpashabmc.base.BaseTest;
import org.irshadpashabmc.endpoints.APIConstants;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import javax.swing.*;

public class VerifyGenerateAuthToken_TC_002 extends BaseTest {


    @Owner("MdIrshadPasha")
    @Description("Verify the Generation of Auth Token")
    @Test
    public void VerifyAndGenerateAuthToken() {

        Req_Spec.basePath(APIConstants.Auth_BasePath);
        Req_Spec.body(Req_Payload.AuthTokenPayload());
        Res = Req_Spec.log().all().when().post();

        // Capture token id

        String Token = Res.jsonPath().getString("token");
        System.out.println("This is Generated Token:"+Token);

        Val_Res = Res.then().log().all();

    }

}
