package learn.qa.api;

import static io.restassured.RestAssured.given;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import learn.qa.config.Config;
import learn.qa.model.UserProfile;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
public final class UserProfileApi {

  private UserProfileApi() {
  }

  @Nonnull
  @Step("Api. Получаем профиль сотрудника {login}")
  public static UserProfile getProfile(String login, String token) {
    return given()
        .filter(new AllureRestAssured())
        .baseUri(Config.getInstance().backendUrl())
        .auth().oauth2(token)
        .contentType(ContentType.JSON)
        .log().all()
        .then()
        .log().all()
        .expect()
        .statusCode(200)
        .when()
        .get("/team/userProfile/{login}", login)
        .as(UserProfile.class);
  }

  @Step("Api. Обновляем профиль сотрудника (id={profile.id}, phone={profile.personalPhone})")
  public static void saveProfile(UserProfile profile, String token) {
    final String body = new JSONObject()
        .put("id", profile.id())
        .put("mail", profile.mail())
        .put("personalPhone", profile.personalPhone())
        .toString();

    given()
        .filter(new AllureRestAssured())
        .baseUri(Config.getInstance().backendUrl())
        .auth().oauth2(token)
        .body(body)
        .contentType(ContentType.JSON)
        .log().all()
        .then()
        .log().all()
        .expect()
        .statusCode(200)
        .when()
        .put("/team/userProfile/save");
  }
}