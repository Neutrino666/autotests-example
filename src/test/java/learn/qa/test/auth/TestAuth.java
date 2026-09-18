package learn.qa.test.auth;

import static learn.qa.pages.auth.StartPage.openStartPage;

import learn.qa.test.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Авторизация")
public class TestAuth extends BaseTest {

  private String login;
  private String password;

  @BeforeEach
  void before() {
    login = System.getenv("TEST_AUTH_LOGIN");
    password = System.getenv("TEST_AUTH_PASSWORD");

    if (login == null || login.isBlank()) {
      throw new IllegalStateException("Переменная окружения TEST_AUTH_LOGIN не задана или пуста");
    }
    if (password == null || password.isBlank()) {
      throw new IllegalStateException(
          "Переменная окружения TEST_AUTH_PASSWORD не задана или пуста");
    }
  }

  @Test
  @DisplayName("Авторизация пользователя. Положительный сценарий.")
  void testAuth() {
    openStartPage()
        .inputLogin(login)
        .inputPassword(password)
        .clickLogin()
        .desktopOpened();
  }
}