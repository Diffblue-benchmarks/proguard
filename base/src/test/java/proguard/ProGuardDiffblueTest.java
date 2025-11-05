package proguard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ProGuardDiffblueTest {
  /**
   * Method under test: {@link ProGuard#getVersion()}
   */
  @Test
  void testGetVersion() {
    // Arrange, Act and Assert
    assertEquals("undefined", ProGuard.getVersion());
  }
}
