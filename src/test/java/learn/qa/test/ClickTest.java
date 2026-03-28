package learn.qa.test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ClickTest")
public class ClickTest {

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
    Selenide.open("http://uitestingplayground.com");
    $("a[href='/click']")
        .as("Главная страница. Кнопка клик")
        .click();
    $("#badButton")
        .as("Станица Клик. Кнопка клик")
        .click();
    $("#badButton")
        .as("Атрибут класс кнопки клик")
        .shouldHave(attribute("class", "btn btn-success"));
  }
}