package learn.qa.api;

import static io.restassured.RestAssured.given;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import javax.annotation.ParametersAreNonnullByDefault;
import learn.qa.config.Config;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
public final class AuthApi {

  private AuthApi() {
  }

  @Step("Api. Авторизация, получаем accessToken")
  public static String getToken(String mail, String password) {
    final String body = new JSONObject()
        .put("mail", mail)
        .put("password", password)
        .toString();

    return given()
        .filter(new AllureRestAssured())
        .baseUri(Config.getInstance().backendUrl())
        .body(body)
        .contentType(ContentType.JSON)
        .log().all()
        .then()
        .log().all()
        .expect()
        .statusCode(200)
        .when()
        .post("/auth/login")
        .jsonPath()
        .getString("accessToken");
  }
}