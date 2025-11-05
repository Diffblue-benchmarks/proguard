package proguard.retrace;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.Test;

class FrameRemapperDiffblueTest {
  /**
   * Method under test: {@link FrameRemapper#transform(FrameInfo)}
   */
  @Test
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
   * Method under test: {@link FrameRemapper#transform(FrameInfo)}
   */
  @Test
  void testTransform2() {
    // Arrange
    FrameRemapper frameRemapper = new FrameRemapper();

    // Act and Assert
    assertNull(frameRemapper
        .transform(new FrameInfo(null, "Source File", 2, "Type", "Field Name", "Method Name", "Arguments")));
  }

  /**
   * Method under test: {@link FrameRemapper#transform(FrameInfo)}
   */
  @Test
  void testTransform3() {
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
   * Method under test: {@link FrameRemapper#transform(FrameInfo)}
   */
  @Test
  void testTransform4() {
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
   * Method under test: {@link FrameRemapper#originalClassName(String)}
   */
  @Test
  void testOriginalClassName() {
    // Arrange, Act and Assert
    assertEquals("Obfuscated Class Name", (new FrameRemapper()).originalClassName("Obfuscated Class Name"));
  }

  /**
   * Method under test: {@link FrameRemapper#processClassMapping(String, String)}
   */
  @Test
  void testProcessClassMapping() {
    // Arrange, Act and Assert
    assertTrue((new FrameRemapper()).processClassMapping("Class Name", "New Class Name"));
  }
}
