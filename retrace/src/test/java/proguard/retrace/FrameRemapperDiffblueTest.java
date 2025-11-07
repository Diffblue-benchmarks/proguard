package proguard.retrace;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class FrameRemapperDiffblueTest {
  /**
   * Test {@link FrameRemapper#transform(FrameInfo)}.
   * <p>
   * Method under test: {@link FrameRemapper#transform(FrameInfo)}
   */
  @Test
  @DisplayName("Test transform(FrameInfo)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List FrameRemapper.transform(FrameInfo)"})
  void testTransform() {
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
  @DisplayName("Test transform(FrameInfo)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List FrameRemapper.transform(FrameInfo)"})
  void testTransform2() {
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
  @DisplayName("Test transform(FrameInfo); then return first SourceFile is 'Unknown Source'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List FrameRemapper.transform(FrameInfo)"})
  void testTransform_thenReturnFirstSourceFileIsUnknownSource() {
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
  @DisplayName("Test transform(FrameInfo); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"List FrameRemapper.transform(FrameInfo)"})
  void testTransform_thenReturnNull() {
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
  @DisplayName("Test originalClassName(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String FrameRemapper.originalClassName(String)"})
  void testOriginalClassName() {
    // Arrange, Act and Assert
    assertEquals("Obfuscated Class Name", (new FrameRemapper()).originalClassName("Obfuscated Class Name"));
  }

  /**
   * Test {@link FrameRemapper#processClassMapping(String, String)}.
   * <p>
   * Method under test: {@link FrameRemapper#processClassMapping(String, String)}
   */
  @Test
  @DisplayName("Test processClassMapping(String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean FrameRemapper.processClassMapping(String, String)"})
  void testProcessClassMapping() {
    // Arrange, Act and Assert
    assertTrue((new FrameRemapper()).processClassMapping("Class Name", "New Class Name"));
  }
}
