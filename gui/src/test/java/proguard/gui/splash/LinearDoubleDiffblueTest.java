package proguard.gui.splash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class LinearDoubleDiffblueTest {
  /**
   * Test {@link LinearDouble#LinearDouble(double, double, Timing)}.
   * <p>
   * Method under test: {@link LinearDouble#LinearDouble(double, double, Timing)}
   */
  @Test
  @DisplayName("Test new LinearDouble(double, double, Timing)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void LinearDouble.<init>(double, double, Timing)"})
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

  /**
   * Test {@link LinearDouble#getDouble(long)}.
   * <p>
   * Method under test: {@link LinearDouble#getDouble(long)}
   */
  @Test
  @DisplayName("Test getDouble(long)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"double LinearDouble.getDouble(long)"})
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
}
