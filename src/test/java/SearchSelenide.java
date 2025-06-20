import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class SearchSelenide {
    @BeforeAll
    static void setupConfig(){
        Configuration.browserSize = "1920x1080";
        baseUrl = "https://github.com/";
        Configuration.pageLoadStrategy ="eager";
    }
    @Test
    void FourthLesson() {
        open(baseUrl + "/selenide/selenide");
        $("#wiki-tab").shouldBe(visible).click();
        $("#wiki-body a[href$='SoftAssertions']").shouldBe(visible).click();
        $(".page").shouldHave(text("Using JUnit5 extend test class:"));
        $("#wiki-body").$(".markdown-body").shouldHave(
                text("class Tests {\n" +
                        "  @RegisterExtension \n" +
                        "  static SoftAssertsExtension softAsserts = new SoftAssertsExtension();\n" +
                        "\n" +
                        "  @Test\n" +
                        "  void test() {\n" +
                        "    Configuration.assertionMode = SOFT;\n" +
                        "    open(\"page.html\");\n" +
                        "\n" +
                        "    $(\"#first\").should(visible).click();\n" +
                        "    $(\"#second\").should(visible).click();\n" +
                        "  }\n" +
                        "}"),
                text("@ExtendWith({SoftAssertsExtension.class})\n" +
                        "class Tests {\n" +
                        "  @Test\n" +
                        "  void test() {\n" +
                        "    Configuration.assertionMode = SOFT;\n" +
                        "    open(\"page.html\");\n" +
                        "\n" +
                        "    $(\"#first\").should(visible).click();\n" +
                        "    $(\"#second\").should(visible).click();\n" +
                        "  }\n" +
                        "}"));
    }
}