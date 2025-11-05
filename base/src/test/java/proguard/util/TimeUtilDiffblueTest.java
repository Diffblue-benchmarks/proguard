package proguard.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class TimeUtilDiffblueTest {
  /**
   * Method under test: {@link TimeUtil#millisecondsToMinSecReadable(int)}
   */
  @Test
  void testMillisecondsToMinSecReadable() {
    // Arrange, Act and Assert
    assertEquals("0:0.1 (m:s.ms)", TimeUtil.millisecondsToMinSecReadable(1));
  }
}
