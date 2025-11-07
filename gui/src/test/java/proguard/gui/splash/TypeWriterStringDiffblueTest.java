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

class TypeWriterStringDiffblueTest {
  /**
   * Test {@link TypeWriterString#getString(long)}.
   * <ul>
   *   <li>Given {@link Timing} {@link Timing#getTiming(long)} return {@code 0.5}.</li>
   *   <li>Then return {@code Str_}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TypeWriterString#getString(long)}
   */
  @Test
  @DisplayName("Test getString(long); given Timing getTiming(long) return '0.5'; then return 'Str_'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TypeWriterString.getString(long)"})
  void testGetString_givenTimingGetTimingReturn05_thenReturnStr() {
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
   * Test {@link TypeWriterString#getString(long)}.
   * <ul>
   *   <li>Given {@link Timing} {@link Timing#getTiming(long)} return zero.</li>
   *   <li>Then return empty string.</li>
   * </ul>
   * <p>
   * Method under test: {@link TypeWriterString#getString(long)}
   */
  @Test
  @DisplayName("Test getString(long); given Timing getTiming(long) return zero; then return empty string")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TypeWriterString.getString(long)"})
  void testGetString_givenTimingGetTimingReturnZero_thenReturnEmptyString() {
    // Arrange
    Timing timing = mock(Timing.class);
    when(timing.getTiming(anyLong())).thenReturn(0.0d);

    // Act
    String actualString = (new TypeWriterString("String", timing)).getString(10L);

    // Assert
    verify(timing).getTiming(eq(10L));
    assertEquals("", actualString);
  }

  /**
   * Test {@link TypeWriterString#getString(long)}.
   * <ul>
   *   <li>Given {@link TypeWriterString#TypeWriterString(String, Timing)} with string is empty string and {@link Timing}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TypeWriterString#getString(long)}
   */
  @Test
  @DisplayName("Test getString(long); given TypeWriterString(String, Timing) with string is empty string and Timing")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String TypeWriterString.getString(long)"})
  void testGetString_givenTypeWriterStringWithStringIsEmptyStringAndTiming() {
    // Arrange
    Timing timing = mock(Timing.class);
    when(timing.getTiming(anyLong())).thenReturn(10.0d);

    // Act
    String actualString = (new TypeWriterString("", timing)).getString(10L);

    // Assert
    verify(timing).getTiming(eq(10L));
    assertEquals("", actualString);
  }
}
