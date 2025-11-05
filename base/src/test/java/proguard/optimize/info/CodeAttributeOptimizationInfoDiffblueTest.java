package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import proguard.classfile.attribute.CodeAttribute;

class CodeAttributeOptimizationInfoDiffblueTest {
  /**
   * Method under test:
   * {@link CodeAttributeOptimizationInfo#setCodeAttributeOptimizationInfo(CodeAttribute)}
   */
  @Test
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
   * Method under test:
   * {@link CodeAttributeOptimizationInfo#getCodeAttributeOptimizationInfo(CodeAttribute)}
   */
  @Test
  void testGetCodeAttributeOptimizationInfo() {
    // Arrange, Act and Assert
    assertNull(CodeAttributeOptimizationInfo.getCodeAttributeOptimizationInfo(new CodeAttribute(1)));
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of
   * {@link CodeAttributeOptimizationInfo}
   *   <li>{@link CodeAttributeOptimizationInfo#isKept()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertTrue((new CodeAttributeOptimizationInfo()).isKept());
  }
}
