package proguard.gui.splash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class SawToothTimingDiffblueTest {
  /**
   * Method under test: {@link SawToothTiming#getTiming(long)}
   */
  @Test
  void testGetTiming() {
    // Arrange, Act and Assert
    assertEquals(0.0d, (new SawToothTiming(1L, 1L)).getTiming(10L));
  }

  /**
   * Method under test: {@link SawToothTiming#SawToothTiming(long, long)}
   */
  @Test
  void testNewSawToothTiming() {
    // Arrange, Act and Assert
    assertEquals(0.0d, (new SawToothTiming(1L, 1L)).getTiming(10L));
  }
}
