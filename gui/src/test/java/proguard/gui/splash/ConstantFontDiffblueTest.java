package proguard.gui.splash;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import java.awt.Font;
import java.text.AttributedCharacterIterator;
import java.util.HashMap;
import java.util.function.BiFunction;
import org.junit.jupiter.api.Test;

class ConstantFontDiffblueTest {
  /**
   * Method under test: {@link ConstantFont#getFont(long)}
   */
  @Test
  void testGetFont() {
    // Arrange
    Font value = Font.decode("foo");

    // Act and Assert
    assertSame(value, (new ConstantFont(value)).getFont(10L));
  }

  /**
   * Method under test: {@link ConstantFont#getFont(long)}
   */
  @Test
  void testGetFont2() {
    // Arrange
    HashMap<AttributedCharacterIterator.Attribute, Object> attributeObjectMap = new HashMap<>();
    attributeObjectMap.computeIfPresent(null, mock(BiFunction.class));
    Font value = new Font(attributeObjectMap);

    // Act and Assert
    assertSame(value, (new ConstantFont(value)).getFont(10L));
  }
}
