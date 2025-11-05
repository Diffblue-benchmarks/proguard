package proguard.gui.splash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class SmoothTimingDiffblueTest {
  /**
   * Method under test: {@link SmoothTiming#getTiming(long)}
   */
  @Test
  void testGetTiming() {
    // Arrange, Act and Assert
    assertEquals(1.0d, (new SmoothTiming(1L, 1L)).getTiming(10L));
    assertEquals(0.0d, (new SmoothTiming(10L, 1L)).getTiming(10L));
    assertEquals(2.8564512724981586E-36d, (new SmoothTiming(1L, Long.MAX_VALUE)).getTiming(10L));
  }

  /**
   * Method under test: {@link SmoothTiming#SmoothTiming(long, long)}
   */
  @Test
  void testNewSmoothTiming() {
    // Arrange, Act and Assert
    assertEquals(1.0d, (new SmoothTiming(1L, 1L)).getTiming(10L));
    assertEquals(0.0d, (new SmoothTiming(10L, 1L)).getTiming(10L));
    assertEquals(2.8564512724981586E-36d, (new SmoothTiming(1L, Long.MAX_VALUE)).getTiming(10L));
  }
}
