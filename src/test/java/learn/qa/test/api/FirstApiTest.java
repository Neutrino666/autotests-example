package learn.qa.test.api;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Авторизация")
public class FirstApiTest {

  private static final String backendUrl;
  private static final String userMail;
  private static final String userPassword;

  static {
    backendUrl = System.getenv("BACKEND_URL");
    userMail = System.getenv("TEST_AUTH_LOGIN");
    userPassword = System.getenv("TEST_AUTH_PASSWORD");

    if (backendUrl == null || backendUrl.isBlank()) {
      throw new IllegalStateException("Переменная окружения BACKEND_URL не задана или пуста");
    }
    if (userMail == null || userMail.isBlank()) {
      throw new IllegalStateException("Переменная окружения TEST_AUTH_LOGIN не задана или пуста");
    }
    if (userPassword == null || userPassword.isBlank()) {
      throw new IllegalStateException(
          "Переменная окружения TEST_AUTH_PASSWORD не задана или пуста");
    }
  }

  @Test
  @DisplayName("Авторизация пользователя")
  void authShouldBeSuccess() {
    final String body = new JSONObject()
        .put("mail", userMail)
        .put("password", userPassword)
        .toString();

    Response res = step("Api. Авторизация", () -> given()
        .filter(new AllureRestAssured())
        .baseUri(backendUrl)
        .body(body)
        .contentType(ContentType.JSON)
        .log().all()
        .then()
        .log().all()
        .expect()
        .statusCode(200)
        .when()
        .post("/auth/login")
    );

    String accessToken = res.jsonPath().getString("accessToken");
    String refreshToken = res.jsonPath().getString("refreshToken");
    String mail = res.jsonPath().getString("mail");

    assertThat(accessToken)
        .as("Проверка наличия токена при авторизации")
        .isNotNull()
        .hasSizeGreaterThan(0);

    assertThat(refreshToken)
        .as("Проверка наличия refreshToken при авторизации")
        .isNotNull()
        .hasSizeGreaterThan(0);

    assertThat(mail)
        .as("Проверка наличия mail при авторизации")
        .isNotNull()
        .isEqualTo(userMail);
  }

  @DisplayName("auth/me содержит mail")
  @Test
  void authMeShouldContainsMail() {
    final String token = getToken();

    final String mail = given()
        .filter(new AllureRestAssured())
        .baseUri(backendUrl)
        .contentType(ContentType.JSON)
        .auth().oauth2(token)
        .log().all()
        .then()
        .log().all()
        .expect()
        .statusCode(200)
        .when()
        .get("/auth/me")
        .jsonPath()
        .getString("user.mail");

    assertThat(mail)
        .as("Проверка наличия mail в ответе")
        .isNotNull()
        .isEqualTo(userMail);
  }

  @Step("Api. Авторизация")
  private String getToken() {
    final String body = new JSONObject()
        .put("mail", userMail)
        .put("password", userPassword)
        .toString();

    return given()
        .filter(new AllureRestAssured())
        .baseUri(backendUrl)
        .body(body)
        .contentType(ContentType.JSON)
        .expect()
        .statusCode(200)
        .when()
        .post("/auth/login")
        .jsonPath()
        .getString("accessToken");
  }
}