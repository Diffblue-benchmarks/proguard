package proguard.gui.splash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.awt.Font;
import java.awt.geom.AffineTransform;
import org.junit.jupiter.api.Test;

class VariableSizeFontDiffblueTest {
  /**
   * Method under test: {@link VariableSizeFont#getFont(long)}
   */
  @Test
  void testGetFont() {
    // Arrange
    VariableDouble size = mock(VariableDouble.class);
    when(size.getDouble(anyLong())).thenReturn(10.0d);

    // Act
    Font actualFont = (new VariableSizeFont(Font.decode("foo"), size)).getFont(10L);

    // Assert
    verify(size).getDouble(eq(10L));
    assertEquals("Dialog", actualFont.getFamily());
    assertEquals("Dialog.plain", actualFont.getFontName());
    assertEquals("Dialog.plain", actualFont.getPSName());
    assertEquals("foo", actualFont.getName());
    assertEquals(0, actualFont.getMissingGlyphCode());
    assertEquals(0, actualFont.getStyle());
    AffineTransform transform = actualFont.getTransform();
    assertEquals(0, transform.getType());
    assertEquals(0.0d, transform.getShearX());
    assertEquals(0.0d, transform.getShearY());
    assertEquals(0.0d, transform.getTranslateX());
    assertEquals(0.0d, transform.getTranslateY());
    assertEquals(0.0f, actualFont.getItalicAngle());
    assertEquals(1.0d, transform.getDeterminant());
    assertEquals(1.0d, transform.getScaleX());
    assertEquals(1.0d, transform.getScaleY());
    assertEquals(10, actualFont.getSize());
    assertEquals(10.0f, actualFont.getSize2D());
    assertEquals(22, actualFont.getAvailableAttributes().length);
    assertEquals(6253, actualFont.getNumGlyphs());
    assertEquals(8, actualFont.getAttributes().size());
    assertFalse(actualFont.hasLayoutAttributes());
    assertFalse(actualFont.hasUniformLineMetrics());
    assertFalse(actualFont.isBold());
    assertFalse(actualFont.isItalic());
    assertFalse(actualFont.isTransformed());
    assertTrue(actualFont.isPlain());
    assertTrue(transform.isIdentity());
  }
}
