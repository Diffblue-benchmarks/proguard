package proguard.gui.splash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class SineTimingDiffblueTest {
  /**
   * Method under test: {@link SineTiming#getTiming(long)}
   */
  @Test
  void testGetTiming() {
    // Arrange, Act and Assert
    assertEquals(0.4999999999999951d, (new SineTiming(1L, 1L)).getTiming(10L));
  }

  /**
   * Method under test: {@link SineTiming#SineTiming(long, long)}
   */
  @Test
  void testNewSineTiming() {
    // Arrange, Act and Assert
    assertEquals(0.4999999999999951d, (new SineTiming(1L, 1L)).getTiming(10L));
  }
}
