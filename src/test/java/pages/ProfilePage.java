package pages;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {
    private final SelenideElement emptyTable = $(".rt-noData");

    @Step("Открыть страницу профиля авторизованного пользователя")
    public ProfilePage openUserPage() {
        open("/profile");
        return this;
    }

    @Step("Проверка отсутствия добавленных в таблицу книг")
    public void checkEmptyTableIsTrue() {
        emptyTable.shouldBe(visible);
    }
}