package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;

class CatchExceptionMarkerDiffblueTest {
  /**
   * Test {@link CatchExceptionMarker#catchesExceptions(Method)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CatchExceptionMarker#catchesExceptions(Method)}
   */
  @Test
  @DisplayName("Test catchesExceptions(Method); given MethodOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.CatchExceptionMarker.catchesExceptions(proguard.classfile.Method)"})
  void testCatchesExceptions_givenMethodOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(CatchExceptionMarker.catchesExceptions(method));
  }
}
