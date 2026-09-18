package learn.qa.pages;

import com.codeborne.selenide.Selenide;

public abstract class BasePage {

  protected final long WAIT_TIME_AVERAGE_MILLIS = 3000;
  protected final long WAIT_TIME_AVERAGE_SEC = 5;

  public void refreshPage() {
    Selenide.refresh();
    waitFor(WAIT_TIME_AVERAGE_MILLIS);
  }

  public void waitFor(long milliseconds) {
    long currentTime = System.currentTimeMillis();
    while (System.currentTimeMillis() < currentTime + milliseconds) {
    }
  }

}
