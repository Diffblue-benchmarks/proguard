package proguard.retrace;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FramePatternDiffblueTest {
  /**
   * Test {@link FramePattern#FramePattern(String, boolean)}.
   * <p>
   * Method under test: {@link FramePattern#FramePattern(String, boolean)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"void proguard.retrace.FramePattern.<init>(java.lang.String, boolean)"})
  public void testNewFramePattern() {
    // Arrange, Act and Assert
    assertNull((new FramePattern("Regular Expression", true)).parse("Line"));
  }

  /**
   * Test {@link FramePattern#parse(String)}.
   * <ul>
   *   <li>When {@code Line}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FramePattern#parse(String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"proguard.retrace.FrameInfo proguard.retrace.FramePattern.parse(java.lang.String)"})
  public void testParse_whenLine_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull((new FramePattern("Regular Expression", true)).parse("Line"));
  }

  /**
   * Test {@link FramePattern#parse(String)}.
   * <ul>
   *   <li>When {@code Regular Expression}.</li>
   *   <li>Then return Arguments is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FramePattern#parse(String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"proguard.retrace.FrameInfo proguard.retrace.FramePattern.parse(java.lang.String)"})
  public void testParse_whenRegularExpression_thenReturnArgumentsIsNull() {
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
   * Test {@link FramePattern#format(String, FrameInfo)}.
   * <ul>
   *   <li>When {@code Line}.</li>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FramePattern#format(String, FrameInfo)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "java.lang.String proguard.retrace.FramePattern.format(java.lang.String, proguard.retrace.FrameInfo)"})
  public void testFormat_whenLine_thenReturnNull() {
    // Arrange
    FramePattern framePattern = new FramePattern("Regular Expression", true);

    // Act and Assert
    assertNull(framePattern.format("Line",
        new FrameInfo("Class Name", "Source File", 2, "Type", "Field Name", "Method Name", "Arguments")));
  }

  /**
   * Test {@link FramePattern#format(String, FrameInfo)}.
   * <ul>
   *   <li>When {@code Regular Expression}.</li>
   *   <li>Then return {@code Regular Expression}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FramePattern#format(String, FrameInfo)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
      "java.lang.String proguard.retrace.FramePattern.format(java.lang.String, proguard.retrace.FrameInfo)"})
  public void testFormat_whenRegularExpression_thenReturnRegularExpression() {
    // Arrange
    FramePattern framePattern = new FramePattern("Regular Expression", true);

    // Act and Assert
    assertEquals("Regular Expression", framePattern.format("Regular Expression",
        new FrameInfo("Class Name", "Source File", 2, "Type", "Field Name", "Method Name", "Arguments")));
  }
}
