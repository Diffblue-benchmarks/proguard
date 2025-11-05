package proguard.gui.splash;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.awt.Graphics;
import org.junit.jupiter.api.Test;

class TextSpriteDiffblueTest {
  /**
   * Method under test: {@link TextSprite#paint(Graphics, long)}
   */
  @Test
  void testPaint() {
    // Arrange
    VariableInt spacing = mock(VariableInt.class);
    when(spacing.getInt(anyLong())).thenReturn(1);
    VariableInt x = mock(VariableInt.class);
    when(x.getInt(anyLong())).thenReturn(1);
    VariableInt y = mock(VariableInt.class);
    when(y.getInt(anyLong())).thenReturn(1);

    // Act
    (new TextSprite(new VariableString[]{}, spacing, x, y)).paint(null, 10L);

    // Assert
    verify(spacing).getInt(eq(10L));
    verify(x).getInt(eq(10L));
    verify(y).getInt(eq(10L));
  }
}
