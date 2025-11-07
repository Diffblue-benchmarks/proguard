package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;

class NoSideEffectClassMarkerDiffblueTest {
  /**
   * Test {@link NoSideEffectClassMarker#visitAnyClass(Clazz)}.
   * <ul>
   *   <li>Then {@link LibraryClass#LibraryClass()} ProcessingInfo {@link ClassOptimizationInfo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NoSideEffectClassMarker#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName("Test visitAnyClass(Clazz); then LibraryClass() ProcessingInfo ClassOptimizationInfo")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NoSideEffectClassMarker.visitAnyClass(Clazz)"})
  void testVisitAnyClass_thenLibraryClassProcessingInfoClassOptimizationInfo() {
    // Arrange
    NoSideEffectClassMarker noSideEffectClassMarker = new NoSideEffectClassMarker();

    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ClassOptimizationInfo());

    // Act
    noSideEffectClassMarker.visitAnyClass(clazz);

    // Assert
    Object processingInfo = clazz.getProcessingInfo();
    assertTrue(processingInfo instanceof ClassOptimizationInfo);
    assertFalse(((ClassOptimizationInfo) processingInfo).hasSideEffects());
    assertFalse(((ClassOptimizationInfo) processingInfo).isDotClassed());
    assertFalse(((ClassOptimizationInfo) processingInfo).isInstanceofed());
    assertTrue(((ClassOptimizationInfo) processingInfo).hasNoSideEffects());
  }

  /**
   * Test {@link NoSideEffectClassMarker#hasNoSideEffects(Clazz)}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NoSideEffectClassMarker#hasNoSideEffects(Clazz)}
   */
  @Test
  @DisplayName("Test hasNoSideEffects(Clazz); given ClassOptimizationInfo (default constructor); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean NoSideEffectClassMarker.hasNoSideEffects(Clazz)"})
  void testHasNoSideEffects_givenClassOptimizationInfo_thenReturnFalse() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ClassOptimizationInfo());

    // Act and Assert
    assertFalse(NoSideEffectClassMarker.hasNoSideEffects(clazz));
  }
}
