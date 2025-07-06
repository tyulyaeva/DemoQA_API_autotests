package tests;
import static tests.TestData.ISBN_book;
import static tests.TestData.userId;
import helpers.WithLogin;
import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;
import java.util.List;
import static requests.BookApiRequests.*;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

@Tag("demoqa")
@DisplayName("Удаление добавленной книги")

public class DeleteBookDemoQATests extends TestBase {

    @DisplayName("Удаление добавленной книги. DemoQA.")
    @WithLogin
    @Test
    public void successDeleteBookTest() {
        DeleteBookRequestModel deleteBook = new DeleteBookRequestModel(userId, ISBN_book);
        IsbnModel isbn = new IsbnModel(ISBN_book);
        List<IsbnModel> isbns = List.of(isbn);
        AddBookRequestModel addBook = new AddBookRequestModel(userId, isbns);
        step("Удалить все добавленные книги из корзины", () ->
                deleteAllBooksFromBasket());
        step("Добавить книгу в корзину", () ->
                addBook(addBook));
        step("Удалить добавленную книгу из корзины", () ->
                deleteBook(deleteBook));
        ProfilePage profilePage = new ProfilePage();
        profilePage.openUserPage()
                .checkEmptyTableIsTrue();
    }
}