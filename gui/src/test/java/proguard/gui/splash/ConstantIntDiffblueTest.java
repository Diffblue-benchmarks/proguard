package proguard.gui.splash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ConstantIntDiffblueTest {
  /**
   * Method under test: {@link ConstantInt#getInt(long)}
   */
  @Test
  void testGetInt() {
    // Arrange, Act and Assert
    assertEquals(42, (new ConstantInt(42)).getInt(10L));
  }
}
