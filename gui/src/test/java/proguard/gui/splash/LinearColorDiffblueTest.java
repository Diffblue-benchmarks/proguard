package proguard.gui.splash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.awt.Color;
import org.junit.jupiter.api.Test;

class LinearColorDiffblueTest {
  /**
   * Method under test: {@link LinearColor#getColor(long)}
   */
  @Test
  void testGetColor() throws NumberFormatException {
    // Arrange
    Timing timing = mock(Timing.class);
    when(timing.getTiming(anyLong())).thenReturn(10.0d);
    Color fromValue = Color.decode("42");

    // Act
    Color actualColor = (new LinearColor(fromValue, Color.decode("42"), timing)).getColor(10L);

    // Assert
    verify(timing).getTiming(eq(10L));
    assertEquals(fromValue, actualColor);
  }

  /**
   * Method under test: {@link LinearColor#getColor(long)}
   */
  @Test
  void testGetColor2() throws NumberFormatException {
    // Arrange
    Timing timing = mock(Timing.class);
    when(timing.getTiming(anyLong())).thenReturn(-1.0d);
    Color fromValue = Color.decode("42");

    // Act
    Color actualColor = (new LinearColor(fromValue, Color.decode("42"), timing)).getColor(10L);

    // Assert
    verify(timing).getTiming(eq(10L));
    assertNull(actualColor);
  }

  /**
   * Method under test: {@link LinearColor#getColor(long)}
   */
  @Test
  void testGetColor3() throws NumberFormatException {
    // Arrange
    Timing timing = mock(Timing.class);
    when(timing.getTiming(anyLong())).thenReturn(1.0d);
    Color fromValue = Color.decode("42");
    Color toValue = Color.decode("42");

    // Act
    Color actualColor = (new LinearColor(fromValue, toValue, timing)).getColor(10L);

    // Assert
    verify(timing).getTiming(eq(10L));
    assertSame(toValue, actualColor);
  }

  /**
   * Method under test: {@link LinearColor#getColor(long)}
   */
  @Test
  void testGetColor4() throws NumberFormatException {
    // Arrange
    Timing timing = mock(Timing.class);
    when(timing.getTiming(anyLong())).thenReturn(0.0d);
    Color fromValue = Color.decode("42");

    // Act
    Color actualColor = (new LinearColor(fromValue, Color.decode("42"), timing)).getColor(10L);

    // Assert
    verify(timing).getTiming(eq(10L));
    assertSame(fromValue, actualColor);
  }
}
