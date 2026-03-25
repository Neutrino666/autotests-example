package learn.qa.test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import learn.qa.config.Config;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ClickTest")
public class ClickTest {

  private final Config CFG = Config.getInstance();

  @Test
  @DisplayName("Selenide click button")
  void clickButtonTest() {
    // Добавили логирование
    SelenideLogger.addListener("Allure-selenide", new AllureSelenide()
        .savePageSource(false)
        .screenshots(false)
    );

    // Настроили браузер
    Configuration.browser = "chrome";
    Configuration.browserVersion = "145";

    // Сценарий
    Selenide.open(CFG.frontUrl());
    $("a[href='/click']").click();
    $("#badButton").click();
    $("#badButton")
        .shouldHave(attribute("class", "btn btn-success"));
  }
}
