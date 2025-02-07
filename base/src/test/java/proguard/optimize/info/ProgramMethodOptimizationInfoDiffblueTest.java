package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertNull;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;

class ProgramMethodOptimizationInfoDiffblueTest {
  /**
   * Test {@link ProgramMethodOptimizationInfo#getProgramMethodOptimizationInfo(Method)}.
   * <ul>
   *   <li>Then return {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProgramMethodOptimizationInfo#getProgramMethodOptimizationInfo(Method)}
   */
  @Test
  @DisplayName("Test getProgramMethodOptimizationInfo(Method); then return 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.optimize.info.ProgramMethodOptimizationInfo proguard.optimize.info.ProgramMethodOptimizationInfo.getProgramMethodOptimizationInfo(proguard.classfile.Method)"})
  void testGetProgramMethodOptimizationInfo_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(
        ProgramMethodOptimizationInfo.getProgramMethodOptimizationInfo(new LibraryMethod(1, "Name", "Descriptor")));
  }
}
