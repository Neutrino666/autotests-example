package learn.qa.pages.auth;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import java.time.Duration;
import javax.annotation.ParametersAreNonnullByDefault;
import learn.qa.config.LocalConfig;
import learn.qa.pages.BasePage;
import learn.qa.pages.desktop.DesktopPage;

@ParametersAreNonnullByDefault
public class StartPage extends BasePage {

  private final SelenideElement loginField = $x("//input[@id='email']")
      .as("Поле ввода логина");
  private final SelenideElement passwordField = $x("//input[@id='password']")
      .as("Поле ввода пароля");
  private final SelenideElement loginButton = $x("//button[@id='kc-login']")
      .as("Кнопка 'Войти'");

  public static StartPage openStartPage() {
    Selenide.open(LocalConfig.INSTANCE.frontUrl());
    return new StartPage();
  }

  @Step("Вводим логин")
  public StartPage inputLogin(String login) {
    loginField.should(exist, Duration.ofSeconds(WAIT_TIME_AVERAGE_SEC)).sendKeys(login);
    return this;
  }

  @Step("Вводим пароль")
  public StartPage inputPassword(String password) {
    passwordField.should(exist, Duration.ofSeconds(WAIT_TIME_AVERAGE_SEC)).sendKeys(password);
    return this;
  }

  @Step("Нажимаем кнопку Войти")
  public DesktopPage clickLogin() {
    loginButton.should(exist, Duration.ofSeconds(WAIT_TIME_AVERAGE_SEC)).click();
    waitFor(WAIT_TIME_AVERAGE_MILLIS);
    return new DesktopPage();
  }

}
