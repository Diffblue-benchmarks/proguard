package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;

class CaughtClassMarkerDiffblueTest {
  /**
   * Test {@link CaughtClassMarker#isCaught(Clazz)}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CaughtClassMarker#isCaught(Clazz)}
   */
  @Test
  @DisplayName("Test isCaught(Clazz); given ClassOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.info.CaughtClassMarker.isCaught(proguard.classfile.Clazz)"})
  void testIsCaught_givenClassOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ClassOptimizationInfo());

    // Act and Assert
    assertTrue(CaughtClassMarker.isCaught(clazz));
  }

  /**
   * Test {@link CaughtClassMarker#isCaught(Clazz)}.
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link CaughtClassMarker#isCaught(Clazz)}
   */
  @Test
  @DisplayName("Test isCaught(Clazz); given ProgramClassOptimizationInfo (default constructor); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.info.CaughtClassMarker.isCaught(proguard.classfile.Clazz)"})
  void testIsCaught_givenProgramClassOptimizationInfo_thenReturnFalse() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ProgramClassOptimizationInfo());

    // Act and Assert
    assertFalse(CaughtClassMarker.isCaught(clazz));
  }
}
