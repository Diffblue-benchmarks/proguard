package proguard.gui.splash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;

class LinearDoubleDiffblueTest {
  /**
   * Method under test: {@link LinearDouble#getDouble(long)}
   */
  @Test
  void testGetDouble() {
    // Arrange
    Timing timing = mock(Timing.class);
    when(timing.getTiming(anyLong())).thenReturn(10.0d);

    // Act
    double actualDouble = (new LinearDouble(10.0d, 10.0d, timing)).getDouble(10L);

    // Assert
    verify(timing).getTiming(eq(10L));
    assertEquals(10.0d, actualDouble);
  }

  /**
   * Method under test: {@link LinearDouble#LinearDouble(double, double, Timing)}
   */
  @Test
  void testNewLinearDouble() {
    // Arrange
    Timing timing = mock(Timing.class);
    when(timing.getTiming(anyLong())).thenReturn(10.0d);

    // Act
    double actualDouble = (new LinearDouble(10.0d, 10.0d, timing)).getDouble(10L);

    // Assert
    verify(timing).getTiming(eq(10L));
    assertEquals(10.0d, actualDouble);
  }
}
