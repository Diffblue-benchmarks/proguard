package proguard.retrace;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class FramePatternDiffblueTest {
  /**
   * Method under test: {@link FramePattern#parse(String)}
   */
  @Test
  void testParse() {
    // Arrange, Act and Assert
    assertNull((new FramePattern("Regular Expression", true)).parse("Line"));
  }

  /**
   * Method under test: {@link FramePattern#parse(String)}
   */
  @Test
  void testParse2() {
    // Arrange and Act
    FrameInfo actualParseResult = (new FramePattern("Regular Expression", true)).parse("Regular Expression");

    // Assert
    assertNull(actualParseResult.getArguments());
    assertNull(actualParseResult.getClassName());
    assertNull(actualParseResult.getFieldName());
    assertNull(actualParseResult.getMethodName());
    assertNull(actualParseResult.getSourceFile());
    assertNull(actualParseResult.getType());
    assertEquals(0, actualParseResult.getLineNumber());
  }

  /**
   * Method under test: {@link FramePattern#format(String, FrameInfo)}
   */
  @Test
  void testFormat() {
    // Arrange
    FramePattern framePattern = new FramePattern("Regular Expression", true);

    // Act and Assert
    assertNull(framePattern.format("Line",
        new FrameInfo("Class Name", "Source File", 2, "Type", "Field Name", "Method Name", "Arguments")));
  }

  /**
   * Method under test: {@link FramePattern#format(String, FrameInfo)}
   */
  @Test
  void testFormat2() {
    // Arrange
    FramePattern framePattern = new FramePattern("Regular Expression", true);

    // Act and Assert
    assertEquals("Regular Expression", framePattern.format("Regular Expression",
        new FrameInfo("Class Name", "Source File", 2, "Type", "Field Name", "Method Name", "Arguments")));
  }

  /**
   * Method under test: {@link FramePattern#FramePattern(String, boolean)}
   */
  @Test
  void testNewFramePattern() {
    // Arrange, Act and Assert
    assertNull((new FramePattern("Regular Expression", true)).parse("Line"));
  }
}
