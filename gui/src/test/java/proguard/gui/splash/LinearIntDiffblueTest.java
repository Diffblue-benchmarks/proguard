package proguard.gui.splash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;

class LinearIntDiffblueTest {
  /**
   * Method under test: {@link LinearInt#getInt(long)}
   */
  @Test
  void testGetInt() {
    // Arrange
    Timing timing = mock(Timing.class);
    when(timing.getTiming(anyLong())).thenReturn(10.0d);

    // Act
    int actualInt = (new LinearInt(42, 42, timing)).getInt(10L);

    // Assert
    verify(timing).getTiming(eq(10L));
    assertEquals(42, actualInt);
  }
}
