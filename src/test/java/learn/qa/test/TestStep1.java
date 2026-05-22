package learn.qa.test;

import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("StoreReport")
public class TestStep1 {

  @Test
  void TestIT_() {
    Map<String, Integer> someMap = Map.of(
        "1", 1,
        "2", 2
    );
    System.out.println(someMap);

    for (Entry<String, Integer> productReport : someMap.entrySet()) {
      System.out.println(productReport);
    }
  }
}