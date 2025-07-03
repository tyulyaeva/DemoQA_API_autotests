package requests;

import models.LoginRequestModel;
import models.LoginResponseModel;

import static io.restassured.RestAssured.given;
import static specs.AuthorizationSpec.authRequestSpec;
import static specs.AuthorizationSpec.authResponseSpec;
import static tests.TestBase.URL_login;
import static tests.TestData.login;
import static tests.TestData.password;

public class AuthorizationApiRequests {

    public static LoginResponseModel loginRequest() {
        LoginRequestModel authData = new LoginRequestModel(login, password);

        return given(authRequestSpec)
                .body(authData)
                .when()
                .post(URL_login)
                .then()
                .spec(authResponseSpec(200))
                .extract().as(LoginResponseModel.class);
    }
}