package learn.qa.pages.profile;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import java.time.Duration;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import learn.qa.pages.BasePage;

@ParametersAreNonnullByDefault
public class ProfilePage extends BasePage {

  private final SelenideElement phoneValue =
      $x("//*[@id='layoutContent']/div/div[2]/div[2]/div[2]/div[2]/div[4]/div/div/div")
          .as("Поле с номером телефона в профиле сотрудника");

  @Nonnull
  @Step("UI. Получаем номер телефона из профиля")
  public String getPhone() {
    return phoneValue
        .should(exist, Duration.ofSeconds(WAIT_TIME_AVERAGE_SEC))
        .getText()
        .trim();
  }
}