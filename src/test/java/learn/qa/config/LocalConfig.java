package learn.qa.config;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public enum LocalConfig implements Config {
  INSTANCE;

  @Nonnull
  @Override
  public String frontUrl() {
    return "https://test4-tasks.lukit.ru";
  }

  @Nonnull
  @Override
  public String backendUrl() {
    final String url = System.getenv("BACKEND_URL");
    if (url == null || url.isBlank()) {
      throw new IllegalStateException("Переменная окружения BACKEND_URL не задана или пуста");
    }
    return url;
  }
}