package learn.qa.config;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface Config {

  @Nonnull
  static Config getInstance() {
    return LocalConfig.INSTANCE;
  }

  @Nonnull
  String frontUrl();
}
