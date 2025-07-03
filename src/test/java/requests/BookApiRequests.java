package requests;

import models.AddBookRequestModel;
import models.DeleteBookRequestModel;

import static io.restassured.RestAssured.given;
import static specs.BookSpec.bookRequestSpec;
import static specs.BookSpec.bookResponseSpec;
import static tests.TestBase.URL_books;
import static tests.TestBase.URL_book;
import static tests.TestData.token;
import static tests.TestData.userId;

public class BookApiRequests {
    public static void deleteAllBooksFromBasket() {
        given(bookRequestSpec(token))
                .when()
                .delete(URL_book + "?UserId=" + userId)
                .then()
                .spec(bookResponseSpec(204));
    }

    public static void addBook(AddBookRequestModel addBookData) {
        given(bookRequestSpec(token))
                .body(addBookData)
                .when()
                .post(URL_book)
                .then()
                .spec(bookResponseSpec(201));
    }

    public static void deleteBook(DeleteBookRequestModel deleteBookData) {
        given(bookRequestSpec(token))
                .body(deleteBookData)
                .when()
                .delete(URL_books)
                .then()
                .spec(bookResponseSpec(204));
    }
}