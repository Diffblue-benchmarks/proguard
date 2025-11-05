package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class NumericNameFactoryDiffblueTest {
  /**
   * Method under test: {@link NumericNameFactory#nextName()}
   */
  @Test
  void testNextName() {
    // Arrange, Act and Assert
    assertEquals("1", (new NumericNameFactory()).nextName());
  }
}
