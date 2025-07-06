package requests;
import models.LoginRequestModel;
import models.LoginResponseModel;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static specs.AuthorizationSpec.authRequestSpec;
import static specs.AuthorizationSpec.authResponseSpec;
import static tests.TestBase.UPDATE_token;
import static tests.TestBase.URL_login;
import static tests.TestData.login;
import static tests.TestData.password;

public class AuthorizationApiRequests {

    public static LoginResponseModel loginRequest() {
        LoginRequestModel authData = new LoginRequestModel(login, password);
        given()
                .body(authData)
                .contentType(JSON)
                .when()
                .post(UPDATE_token)
                .then()
                .spec(authResponseSpec(200));

        return given(authRequestSpec)
                .body(authData)
                .when()
                .post(URL_login)
                .then()
                .spec(authResponseSpec(200))
                .extract().as(LoginResponseModel.class);
    }
}