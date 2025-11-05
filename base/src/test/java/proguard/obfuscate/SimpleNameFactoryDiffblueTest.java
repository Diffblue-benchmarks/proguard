package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class SimpleNameFactoryDiffblueTest {
  /**
   * Method under test: {@link SimpleNameFactory#nextName()}
   */
  @Test
  void testNextName() {
    // Arrange, Act and Assert
    assertEquals("a", (new SimpleNameFactory(true)).nextName());
    assertEquals("a", (new SimpleNameFactory(false)).nextName());
  }
}
