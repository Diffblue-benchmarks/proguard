package proguard.gui.splash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ConstantTimingDiffblueTest {
  /**
   * Method under test: {@link ConstantTiming#getTiming(long)}
   */
  @Test
  void testGetTiming() {
    // Arrange, Act and Assert
    assertEquals(10.0d, (new ConstantTiming(10.0d)).getTiming(10L));
  }
}
