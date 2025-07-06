package tests;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attachments;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.Map;
import java.util.UUID;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    private static final String SELENOID_URL = System.getProperty("selenoid.url");
    private static final String SELENOID_LOGIN = System.getProperty("selenoid.login");;
    private static final String SELENOID_PASSWORD = System.getProperty("selenoid.password");
    public static String URL_login = "/Account/v1/Login";
    public static String URL_book = "/BookStore/v1/Books";
    public static String URL_books = "/BookStore/v1/Book";
    public static String UPDATE_token = "/Account/v1/GenerateToken";

    @BeforeAll
    public static void setupGlobalTestConfiguration() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        RestAssured.baseURI = "https://demoqa.com";
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browser.version", "");
        Configuration.browserSize = System.getProperty("browser.size", "1920x1080");

        if (isRemoteExecution()) {
            setupRemoteWebDriver();
        }
    }

    private static boolean isRemoteExecution() {
        return SELENOID_URL != null && !SELENOID_URL.isEmpty();
    }

    private static void setupRemoteWebDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true,
                "name", "Test: " + UUID.randomUUID()
        ));
        Configuration.remote = "https://" + SELENOID_LOGIN + ":" + SELENOID_PASSWORD + "@" + SELENOID_URL + "/wd/hub";
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    public void setupAllureListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void shutDown() {
        Attachments.screenshotAs("Скриншот результата");
        Attachments.pageSource();
        if (!Configuration.browser.equals("firefox"))
            Attachments.browserConsoleLogs();
        if (isRemoteExecution()) {
            Attachments.addVideo();
        }
        closeWebDriver();
    }
}