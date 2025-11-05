package proguard.retrace;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class FrameInfoDiffblueTest {
  /**
   * Methods under test:
   * <ul>
   *   <li>
   * {@link FrameInfo#FrameInfo(String, String, int, String, String, String, String)}
   *   <li>{@link FrameInfo#toString()}
   *   <li>{@link FrameInfo#getArguments()}
   *   <li>{@link FrameInfo#getClassName()}
   *   <li>{@link FrameInfo#getFieldName()}
   *   <li>{@link FrameInfo#getLineNumber()}
   *   <li>{@link FrameInfo#getMethodName()}
   *   <li>{@link FrameInfo#getSourceFile()}
   *   <li>{@link FrameInfo#getType()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange and Act
    FrameInfo actualFrameInfo = new FrameInfo("Class Name", "Source File", 2, "Type", "Field Name", "Method Name",
        "Arguments");
    String actualToStringResult = actualFrameInfo.toString();
    String actualArguments = actualFrameInfo.getArguments();
    String actualClassName = actualFrameInfo.getClassName();
    String actualFieldName = actualFrameInfo.getFieldName();
    int actualLineNumber = actualFrameInfo.getLineNumber();
    String actualMethodName = actualFrameInfo.getMethodName();
    String actualSourceFile = actualFrameInfo.getSourceFile();

    // Assert
    assertEquals("Arguments", actualArguments);
    assertEquals("Class Name", actualClassName);
    assertEquals("Field Name", actualFieldName);
    assertEquals("Method Name", actualMethodName);
    assertEquals("Source File", actualSourceFile);
    assertEquals("Type", actualFrameInfo.getType());
    assertEquals(
        "proguard.retrace.FrameInfo(class=[Class Name], line=[2], type=[Type], field=[Field Name], method=[Method"
            + " Name], arguments=[Arguments]",
        actualToStringResult);
    assertEquals(2, actualLineNumber);
  }
}
