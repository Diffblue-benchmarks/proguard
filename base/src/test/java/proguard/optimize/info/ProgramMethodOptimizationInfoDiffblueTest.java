package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;

class ProgramMethodOptimizationInfoDiffblueTest {
  /**
   * Method under test:
   * {@link ProgramMethodOptimizationInfo#getProgramMethodOptimizationInfo(Method)}
   */
  @Test
  void testGetProgramMethodOptimizationInfo() {
    // Arrange, Act and Assert
    assertNull(
        ProgramMethodOptimizationInfo.getProgramMethodOptimizationInfo(new LibraryMethod(1, "Name", "Descriptor")));
  }
}
