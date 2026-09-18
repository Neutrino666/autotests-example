package learn.qa.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class BaseTest {

  @BeforeAll
  public void beforeAll() {
    closeBrowsers();
    Configuration.browser = "chrome";
    System.setProperty("webdriver.chrome.driver", "D:/Работа/chromedriver-win64/chromedriver.exe");
  }

  @AfterAll
  public void afterAll() {
    closeBrowsers();
  }

  private static void closeBrowsers() {
    while (WebDriverRunner.hasWebDriverStarted()) {
      Selenide.closeWebDriver();
    }
  }

}
