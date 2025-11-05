package proguard.gui.splash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class LinearTimingDiffblueTest {
  /**
   * Method under test: {@link LinearTiming#getTiming(long)}
   */
  @Test
  void testGetTiming() {
    // Arrange, Act and Assert
    assertEquals(1.0d, (new LinearTiming(1L, 1L)).getTiming(10L));
    assertEquals(0.0d, (new LinearTiming(10L, 1L)).getTiming(10L));
    assertEquals(9.75781955236954E-19d, (new LinearTiming(1L, Long.MAX_VALUE)).getTiming(10L));
  }

  /**
   * Method under test: {@link LinearTiming#LinearTiming(long, long)}
   */
  @Test
  void testNewLinearTiming() {
    // Arrange, Act and Assert
    assertEquals(1.0d, (new LinearTiming(1L, 1L)).getTiming(10L));
    assertEquals(0.0d, (new LinearTiming(10L, 1L)).getTiming(10L));
    assertEquals(9.75781955236954E-19d, (new LinearTiming(1L, Long.MAX_VALUE)).getTiming(10L));
  }
}
