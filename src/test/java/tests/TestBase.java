package tests;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attachments;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import config.ProjectConfig;
import config.WebConfig;
import config.ConfigReader;

public class TestBase {
    public static final WebConfig webConfig = ConfigReader.getWebConfig();

    public static String URL_login = "/Account/v1/Login";
    public static String URL_book = "/BookStore/v1/Books";
    public static String URL_books = "/BookStore/v1/Book";
    public static String UPDATE_token = "/Account/v1/GenerateToken";

    @BeforeAll
    public static void setupBaseTestConfiguration() {
        ProjectConfig projectConfig = new ProjectConfig(webConfig);
        projectConfig.setConfig();
    }

    @BeforeEach
    public void setupAllureListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void shutDown() {
        Attachments.screenshotAs("Last screenshot");
        Attachments.pageSource();
        if (!Configuration.browser.equals("firefox"))
            Attachments.browserConsoleLogs();
        if (webConfig.isRemote()) {
            Attachments.addVideo();
        }
        closeWebDriver();
    }
}
