package proguard.gui.splash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ConstantStringDiffblueTest {
  /**
   * Method under test: {@link ConstantString#getString(long)}
   */
  @Test
  void testGetString() {
    // Arrange, Act and Assert
    assertEquals("42", (new ConstantString("42")).getString(10L));
  }
}
