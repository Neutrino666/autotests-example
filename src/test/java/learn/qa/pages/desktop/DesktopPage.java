package learn.qa.pages.desktop;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import java.time.Duration;
import learn.qa.pages.BasePage;
import learn.qa.pages.profile.ProfilePage;

public class DesktopPage extends BasePage {

  private final SelenideElement desktopHeader =
      $x("//div[@data-class='Page_headerContent']//span[text() = 'Рабочий стол']")
          .as("Заголовок рабочего стола");

  private final SelenideElement profileIcon =
      $x("//*[@id='root']/div[1]/div[1]/div/div[8]/div[1]/div/div/div/button")
          .as("Иконка профиля в хедере");

  private final SelenideElement openProfileButton =
      $x("//*[@id='root']/div[2]/div/div/div/div[1]")
          .as("Пункт меню 'Открыть профиль'");

  @Step("Проверяем, что рабочий стол открыт")
  public DesktopPage desktopOpened() {
    desktopHeader.should(exist, Duration.ofSeconds(WAIT_TIME_AVERAGE_SEC));
    return this;
  }

  @Step("UI. Нажимаем на иконку профиля")
  public DesktopPage clickProfileIcon() {
    profileIcon.should(exist, Duration.ofSeconds(WAIT_TIME_AVERAGE_SEC)).click();
    return this;
  }

  @Step("UI. Нажимаем 'Открыть профиль' и переходим в профиль сотрудника")
  public ProfilePage openProfile() {
    openProfileButton.should(exist, Duration.ofSeconds(WAIT_TIME_AVERAGE_SEC)).click();
    return new ProfilePage();
  }
}