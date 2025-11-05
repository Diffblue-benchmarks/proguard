package proguard.gui.splash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ConstantDoubleDiffblueTest {
  /**
   * Method under test: {@link ConstantDouble#getDouble(long)}
   */
  @Test
  void testGetDouble() {
    // Arrange, Act and Assert
    assertEquals(10.0d, (new ConstantDouble(10.0d)).getDouble(10L));
  }
}
