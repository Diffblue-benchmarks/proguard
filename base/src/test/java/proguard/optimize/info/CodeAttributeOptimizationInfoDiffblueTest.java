package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.attribute.CodeAttribute;

class CodeAttributeOptimizationInfoDiffblueTest {
  /**
   * Test {@link CodeAttributeOptimizationInfo#setCodeAttributeOptimizationInfo(CodeAttribute)}.
   * <p>
   * Method under test: {@link CodeAttributeOptimizationInfo#setCodeAttributeOptimizationInfo(CodeAttribute)}
   */
  @Test
  @DisplayName("Test setCodeAttributeOptimizationInfo(CodeAttribute)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.CodeAttributeOptimizationInfo.setCodeAttributeOptimizationInfo(proguard.classfile.attribute.CodeAttribute)"})
  void testSetCodeAttributeOptimizationInfo() {
    // Arrange
    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    CodeAttributeOptimizationInfo.setCodeAttributeOptimizationInfo(codeAttribute);

    // Assert
    Object processingInfo = codeAttribute.getProcessingInfo();
    assertTrue(processingInfo instanceof CodeAttributeOptimizationInfo);
    assertTrue(((CodeAttributeOptimizationInfo) processingInfo).isKept());
  }

  /**
   * Test {@link CodeAttributeOptimizationInfo#getCodeAttributeOptimizationInfo(CodeAttribute)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CodeAttributeOptimizationInfo#getCodeAttributeOptimizationInfo(CodeAttribute)}
   */
  @Test
  @DisplayName("Test getCodeAttributeOptimizationInfo(CodeAttribute); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.optimize.info.CodeAttributeOptimizationInfo proguard.optimize.info.CodeAttributeOptimizationInfo.getCodeAttributeOptimizationInfo(proguard.classfile.attribute.CodeAttribute)"})
  void testGetCodeAttributeOptimizationInfo_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(CodeAttributeOptimizationInfo.getCodeAttributeOptimizationInfo(new CodeAttribute(1)));
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link CodeAttributeOptimizationInfo}
   *   <li>{@link CodeAttributeOptimizationInfo#isKept()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.info.CodeAttributeOptimizationInfo.<init>()",
      "boolean proguard.optimize.info.CodeAttributeOptimizationInfo.isKept()"})
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertTrue((new CodeAttributeOptimizationInfo()).isKept());
  }
}
