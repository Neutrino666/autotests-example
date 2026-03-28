package learn.qa.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FirstTest {

  @Test
  void test() {

    Assertions.assertEquals(1, 0, "not equals int");
  }
}
