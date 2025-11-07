package proguard.gui.splash;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.color.ICC_ColorSpace;
import java.awt.color.ICC_Profile;
import java.awt.color.ICC_ProfileRGB;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class LinearColorDiffblueTest {
  /**
   * Test {@link LinearColor#getColor(long)}.
   * <ul>
   *   <li>Given {@link Timing} {@link Timing#getTiming(long)} return minus one.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LinearColor#getColor(long)}
   */
  @Test
  @DisplayName("Test getColor(long); given Timing getTiming(long) return minus one; then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Color LinearColor.getColor(long)"})
  void testGetColor_givenTimingGetTimingReturnMinusOne_thenReturnNull() throws NumberFormatException {
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
   * Test {@link LinearColor#getColor(long)}.
   * <ul>
   *   <li>Given {@link Timing} {@link Timing#getTiming(long)} return one.</li>
   *   <li>Then ColorSpace return {@link ICC_ColorSpace}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LinearColor#getColor(long)}
   */
  @Test
  @DisplayName("Test getColor(long); given Timing getTiming(long) return one; then ColorSpace return ICC_ColorSpace")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Color LinearColor.getColor(long)"})
  void testGetColor_givenTimingGetTimingReturnOne_thenColorSpaceReturnICC_ColorSpace() throws NumberFormatException {
    // Arrange
    Timing timing = mock(Timing.class);
    when(timing.getTiming(anyLong())).thenReturn(1.0d);
    Color fromValue = Color.decode("42");

    // Act
    Color actualColor = (new LinearColor(fromValue, Color.decode("42"), timing)).getColor(10L);

    // Assert
    verify(timing).getTiming(eq(10L));
    ColorSpace colorSpace = actualColor.getColorSpace();
    assertTrue(colorSpace instanceof ICC_ColorSpace);
    ICC_Profile profile = ((ICC_ColorSpace) colorSpace).getProfile();
    assertTrue(profile instanceof ICC_ProfileRGB);
    float[][] matrix = ((ICC_ProfileRGB) profile).getMatrix();
    assertEquals(3, matrix.length);
    assertArrayEquals(new float[]{0.013916016f, 0.09713745f, 0.71383667f}, matrix[2], 0.0f);
    assertArrayEquals(new float[]{0.22238159f, 0.717041f, 0.06059265f}, matrix[1], 0.0f);
    assertArrayEquals(new float[]{0.43585205f, 0.3853302f, 0.14302063f}, matrix[0], 0.0f);
    assertArrayEquals(new float[]{0.95014954f, 1.0f, 1.0882568f}, ((ICC_ProfileRGB) profile).getMediaWhitePoint(),
        0.0f);
  }

  /**
   * Test {@link LinearColor#getColor(long)}.
   * <ul>
   *   <li>Given {@link Timing} {@link Timing#getTiming(long)} return ten.</li>
   *   <li>Then ColorSpace return {@link ICC_ColorSpace}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LinearColor#getColor(long)}
   */
  @Test
  @DisplayName("Test getColor(long); given Timing getTiming(long) return ten; then ColorSpace return ICC_ColorSpace")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Color LinearColor.getColor(long)"})
  void testGetColor_givenTimingGetTimingReturnTen_thenColorSpaceReturnICC_ColorSpace() throws NumberFormatException {
    // Arrange
    Timing timing = mock(Timing.class);
    when(timing.getTiming(anyLong())).thenReturn(10.0d);
    Color fromValue = Color.decode("42");

    // Act
    Color actualColor = (new LinearColor(fromValue, Color.decode("42"), timing)).getColor(10L);

    // Assert
    verify(timing).getTiming(eq(10L));
    ColorSpace colorSpace = actualColor.getColorSpace();
    assertTrue(colorSpace instanceof ICC_ColorSpace);
    ICC_Profile profile = ((ICC_ColorSpace) colorSpace).getProfile();
    assertTrue(profile instanceof ICC_ProfileRGB);
    float[][] matrix = ((ICC_ProfileRGB) profile).getMatrix();
    assertEquals(3, matrix.length);
    assertArrayEquals(new float[]{0.013916016f, 0.09713745f, 0.71383667f}, matrix[2], 0.0f);
    assertArrayEquals(new float[]{0.22238159f, 0.717041f, 0.06059265f}, matrix[1], 0.0f);
    assertArrayEquals(new float[]{0.43585205f, 0.3853302f, 0.14302063f}, matrix[0], 0.0f);
    assertArrayEquals(new float[]{0.95014954f, 1.0f, 1.0882568f}, ((ICC_ProfileRGB) profile).getMediaWhitePoint(),
        0.0f);
  }

  /**
   * Test {@link LinearColor#getColor(long)}.
   * <ul>
   *   <li>Given {@link Timing} {@link Timing#getTiming(long)} return zero.</li>
   *   <li>Then ColorSpace return {@link ICC_ColorSpace}.</li>
   * </ul>
   * <p>
   * Method under test: {@link LinearColor#getColor(long)}
   */
  @Test
  @DisplayName("Test getColor(long); given Timing getTiming(long) return zero; then ColorSpace return ICC_ColorSpace")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"Color LinearColor.getColor(long)"})
  void testGetColor_givenTimingGetTimingReturnZero_thenColorSpaceReturnICC_ColorSpace() throws NumberFormatException {
    // Arrange
    Timing timing = mock(Timing.class);
    when(timing.getTiming(anyLong())).thenReturn(0.0d);
    Color fromValue = Color.decode("42");

    // Act
    Color actualColor = (new LinearColor(fromValue, Color.decode("42"), timing)).getColor(10L);

    // Assert
    verify(timing).getTiming(eq(10L));
    ColorSpace colorSpace = actualColor.getColorSpace();
    assertTrue(colorSpace instanceof ICC_ColorSpace);
    ICC_Profile profile = ((ICC_ColorSpace) colorSpace).getProfile();
    assertTrue(profile instanceof ICC_ProfileRGB);
    float[][] matrix = ((ICC_ProfileRGB) profile).getMatrix();
    assertEquals(3, matrix.length);
    assertEquals(actualColor, actualColor.brighter().darker());
    assertArrayEquals(new float[]{0.013916016f, 0.09713745f, 0.71383667f}, matrix[2], 0.0f);
    assertArrayEquals(new float[]{0.22238159f, 0.717041f, 0.06059265f}, matrix[1], 0.0f);
    assertArrayEquals(new float[]{0.43585205f, 0.3853302f, 0.14302063f}, matrix[0], 0.0f);
    assertArrayEquals(new float[]{0.95014954f, 1.0f, 1.0882568f}, ((ICC_ProfileRGB) profile).getMediaWhitePoint(),
        0.0f);
  }
}
