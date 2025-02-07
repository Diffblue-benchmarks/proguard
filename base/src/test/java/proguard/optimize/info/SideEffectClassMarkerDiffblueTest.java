package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;

class SideEffectClassMarkerDiffblueTest {
  /**
   * Test {@link SideEffectClassMarker#hasSideEffects(Clazz)}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectClassMarker#hasSideEffects(Clazz)}
   */
  @Test
  @DisplayName("Test hasSideEffects(Clazz); given ClassOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.info.SideEffectClassMarker.hasSideEffects(proguard.classfile.Clazz)"})
  void testHasSideEffects_givenClassOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.processingInfo = new ClassOptimizationInfo();

    // Act and Assert
    assertTrue(SideEffectClassMarker.hasSideEffects(clazz));
  }

  /**
   * Test {@link SideEffectClassMarker#hasSideEffects(Clazz)}.
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectClassMarker#hasSideEffects(Clazz)}
   */
  @Test
  @DisplayName("Test hasSideEffects(Clazz); given ProgramClassOptimizationInfo (default constructor); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.info.SideEffectClassMarker.hasSideEffects(proguard.classfile.Clazz)"})
  void testHasSideEffects_givenProgramClassOptimizationInfo_thenReturnFalse() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.processingInfo = new ProgramClassOptimizationInfo();

    // Act and Assert
    assertFalse(SideEffectClassMarker.hasSideEffects(clazz));
  }
}
