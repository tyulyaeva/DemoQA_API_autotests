package helpers;

import requests.AuthorizationApiRequests;
import models.LoginResponseModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static tests.TestData.*;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        LoginResponseModel auth = AuthorizationApiRequests.loginRequest();

        userId = auth.getUserId();
        token = auth.getToken();
        expires = auth.getExpires();

        step("Авторизация пользователя @WithLogin", () ->
                open("/favicon.ico"));
        getWebDriver().manage().addCookie(new Cookie("userID", userId));
        getWebDriver().manage().addCookie(new Cookie("expires", expires));
        getWebDriver().manage().addCookie(new Cookie("token", token));
    }
}