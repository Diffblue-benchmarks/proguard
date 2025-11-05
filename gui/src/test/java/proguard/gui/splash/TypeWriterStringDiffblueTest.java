package proguard.gui.splash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;

class TypeWriterStringDiffblueTest {
  /**
   * Method under test: {@link TypeWriterString#getString(long)}
   */
  @Test
  void testGetString() {
    // Arrange
    Timing timing = mock(Timing.class);
    when(timing.getTiming(anyLong())).thenReturn(10.0d);

    // Act
    String actualString = (new TypeWriterString("", timing)).getString(10L);

    // Assert
    verify(timing).getTiming(eq(10L));
    assertEquals("", actualString);
  }

  /**
   * Method under test: {@link TypeWriterString#getString(long)}
   */
  @Test
  void testGetString2() {
    // Arrange
    Timing timing = mock(Timing.class);
    when(timing.getTiming(anyLong())).thenReturn(0.5d);

    // Act
    String actualString = (new TypeWriterString("String", timing)).getString(10L);

    // Assert
    verify(timing).getTiming(eq(10L));
    assertEquals("Str_", actualString);
  }

  /**
   * Method under test: {@link TypeWriterString#getString(long)}
   */
  @Test
  void testGetString3() {
    // Arrange
    Timing timing = mock(Timing.class);
    when(timing.getTiming(anyLong())).thenReturn(0.0d);

    // Act
    String actualString = (new TypeWriterString("String", timing)).getString(10L);

    // Assert
    verify(timing).getTiming(eq(10L));
    assertEquals("", actualString);
  }
}
