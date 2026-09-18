package learn.qa.test.auth;

import static io.qameta.allure.Allure.step;
import static learn.qa.pages.auth.StartPage.openStartPage;
import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.ParametersAreNonnullByDefault;
import learn.qa.api.AuthApi;
import learn.qa.api.UserProfileApi;
import learn.qa.model.UserProfile;
import learn.qa.pages.desktop.DesktopPage;
import learn.qa.pages.profile.ProfilePage;
import learn.qa.test.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Профиль сотрудника")
@ParametersAreNonnullByDefault
public class TestAuthProfile extends BaseTest {

  private String login;
  private String password;
  private String token;
  private UserProfile initialProfile;

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

    token = AuthApi.getToken(login, password);
    initialProfile = UserProfileApi.getProfile(login, token);
  }

  @AfterEach
  void after() {

    if (initialProfile != null && token != null) {
      UserProfileApi.saveProfile(initialProfile, token);
    }
  }

  @Test
  @DisplayName("Изменение номера телефона сотрудника. Положительный сценарий.")
  void testChangePhone() {
    final String newPhone = "89057755824";

    step("Авторизоваться", () ->
        openStartPage()
            .inputLogin(login)
            .inputPassword(password)
            .clickLogin()
            .desktopOpened());

    final ProfilePage profilePage = step("Перейти на главную и открыть профиль", () ->
        new DesktopPage()
            .clickProfileIcon()
            .openProfile());

    step("Убедиться, что номер телефона в профиле совпадает с установленным в предусловии",
        () -> {
          final String actualPhone = profilePage.getPhone();
          assertThat(actualPhone)
              .as("Номер телефона в профиле сотрудника")
              .isEqualTo(initialProfile.personalPhone());
        });

    step("Изменить номер телефона сотрудника через API", () -> {
      final UserProfile updated = new UserProfile(
          initialProfile.id(),
          initialProfile.mail(),
          newPhone);
      UserProfileApi.saveProfile(updated, token);
    });

    step("Обновить страницу профиля", profilePage::refreshPage);

    step("Убедиться, что изменения применились в UI", () -> {
      final String actualPhone = profilePage.getPhone();
      assertThat(actualPhone)
          .as("Номер телефона в профиле после изменения")
          .isEqualTo(newPhone);
    });

    step("Убедиться, что изменения применились через API", () -> {
      final UserProfile actual = UserProfileApi.getProfile(login, token);
      assertThat(actual.personalPhone())
          .as("personalPhone в ответе /backend/team/userProfile/{login}")
          .isEqualTo(newPhone);
    });
  }
}