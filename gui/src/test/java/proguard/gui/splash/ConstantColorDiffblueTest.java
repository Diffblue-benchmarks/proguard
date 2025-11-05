package proguard.gui.splash;

import static org.junit.jupiter.api.Assertions.assertSame;
import java.awt.Color;
import org.junit.jupiter.api.Test;

class ConstantColorDiffblueTest {
  /**
   * Method under test: {@link ConstantColor#getColor(long)}
   */
  @Test
  void testGetColor() throws NumberFormatException {
    // Arrange
    Color value = Color.decode("42");

    // Act and Assert
    assertSame(value, (new ConstantColor(value)).getColor(10L));
  }
}
