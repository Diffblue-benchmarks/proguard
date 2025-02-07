package proguard.retrace;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FrameRemapperDiffblueTest {
  /**
   * Test {@link FrameRemapper#transform(FrameInfo)}.
   * <p>
   * Method under test: {@link FrameRemapper#transform(FrameInfo)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"java.util.List proguard.retrace.FrameRemapper.transform(proguard.retrace.FrameInfo)"})
  public void testTransform() {
    // Arrange
    FrameRemapper frameRemapper = new FrameRemapper();

    // Act
    List<FrameInfo> actualTransformResult = frameRemapper
        .transform(new FrameInfo("Class Name", "Source File", 2, "Type", "Field Name", "Method Name", "Arguments"));

    // Assert
    assertEquals(1, actualTransformResult.size());
    FrameInfo getResult = actualTransformResult.get(0);
    assertEquals("Arguments", getResult.getArguments());
    assertEquals("Class Name", getResult.getClassName());
    assertEquals("Class Name.java", getResult.getSourceFile());
    assertEquals("Field Name", getResult.getFieldName());
    assertEquals("Method Name", getResult.getMethodName());
    assertEquals("Type", getResult.getType());
    assertEquals(2, getResult.getLineNumber());
  }

  /**
   * Test {@link FrameRemapper#transform(FrameInfo)}.
   * <p>
   * Method under test: {@link FrameRemapper#transform(FrameInfo)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"java.util.List proguard.retrace.FrameRemapper.transform(proguard.retrace.FrameInfo)"})
  public void testTransform2() {
    // Arrange
    FrameRemapper frameRemapper = new FrameRemapper();

    // Act
    List<FrameInfo> actualTransformResult = frameRemapper
        .transform(new FrameInfo("Class Name", null, 2, "Type", "Field Name", "Method Name", "Arguments"));

    // Assert
    assertEquals(1, actualTransformResult.size());
    FrameInfo getResult = actualTransformResult.get(0);
    assertEquals("Arguments", getResult.getArguments());
    assertEquals("Class Name", getResult.getClassName());
    assertEquals("Class Name.java", getResult.getSourceFile());
    assertEquals("Field Name", getResult.getFieldName());
    assertEquals("Method Name", getResult.getMethodName());
    assertEquals("Type", getResult.getType());
    assertEquals(2, getResult.getLineNumber());
  }

  /**
   * Test {@link FrameRemapper#transform(FrameInfo)}.
   * <ul>
   *   <li>Then return first SourceFile is {@code Unknown Source}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FrameRemapper#transform(FrameInfo)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"java.util.List proguard.retrace.FrameRemapper.transform(proguard.retrace.FrameInfo)"})
  public void testTransform_thenReturnFirstSourceFileIsUnknownSource() {
    // Arrange
    FrameRemapper frameRemapper = new FrameRemapper();

    // Act
    List<FrameInfo> actualTransformResult = frameRemapper
        .transform(new FrameInfo("Class Name", "Unknown Source", 2, "Type", "Field Name", "Method Name", "Arguments"));

    // Assert
    assertEquals(1, actualTransformResult.size());
    FrameInfo getResult = actualTransformResult.get(0);
    assertEquals("Arguments", getResult.getArguments());
    assertEquals("Class Name", getResult.getClassName());
    assertEquals("Field Name", getResult.getFieldName());
    assertEquals("Method Name", getResult.getMethodName());
    assertEquals("Type", getResult.getType());
    assertEquals("Unknown Source", getResult.getSourceFile());
    assertEquals(2, getResult.getLineNumber());
  }

  /**
   * Test {@link FrameRemapper#transform(FrameInfo)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link FrameRemapper#transform(FrameInfo)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"java.util.List proguard.retrace.FrameRemapper.transform(proguard.retrace.FrameInfo)"})
  public void testTransform_thenReturnNull() {
    // Arrange
    FrameRemapper frameRemapper = new FrameRemapper();

    // Act and Assert
    assertNull(frameRemapper
        .transform(new FrameInfo(null, "Source File", 2, "Type", "Field Name", "Method Name", "Arguments")));
  }

  /**
   * Test {@link FrameRemapper#originalClassName(String)}.
   * <p>
   * Method under test: {@link FrameRemapper#originalClassName(String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"java.lang.String proguard.retrace.FrameRemapper.originalClassName(java.lang.String)"})
  public void testOriginalClassName() {
    // Arrange, Act and Assert
    assertEquals("Obfuscated Class Name", (new FrameRemapper()).originalClassName("Obfuscated Class Name"));
  }

  /**
   * Test {@link FrameRemapper#processClassMapping(String, String)}.
   * <p>
   * Method under test: {@link FrameRemapper#processClassMapping(String, String)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"boolean proguard.retrace.FrameRemapper.processClassMapping(java.lang.String, java.lang.String)"})
  public void testProcessClassMapping() {
    // Arrange, Act and Assert
    assertTrue((new FrameRemapper()).processClassMapping("Class Name", "New Class Name"));
  }
}
