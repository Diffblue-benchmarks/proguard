package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.ProgramClass;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;

class ProgramClassOptimizationInfoSetterDiffblueTest {
  /**
   * Test {@link ProgramClassOptimizationInfoSetter#visitProgramClass(ProgramClass)}.
   * <p>
   * Method under test: {@link ProgramClassOptimizationInfoSetter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.ProgramClassOptimizationInfoSetter.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass() {
    // Arrange
    ProgramClassOptimizationInfoSetter programClassOptimizationInfoSetter = new ProgramClassOptimizationInfoSetter(
        true);
    ProgramClass programClass = new ProgramClass(1, 3, new Constant[]{new ClassConstant()}, 1, 1, 1, "Feature Name", 1,
        "Processing Info");

    // Act
    programClassOptimizationInfoSetter.visitProgramClass(programClass);

    // Assert
    Object processingInfo = programClass.getProcessingInfo();
    assertTrue(processingInfo instanceof ProgramClassOptimizationInfo);
    assertNull(((ProgramClassOptimizationInfo) processingInfo).getTargetClass());
    assertNull(((ProgramClassOptimizationInfo) processingInfo).getWrappedClass());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).hasNoSideEffects());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).containsConstructors());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).containsPackageVisibleMembers());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).hasSideEffects());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).invokesPackageVisibleMembers());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).isCaught());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).isDotClassed());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).isEscaping());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).isInstanceofed());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).isInstantiated());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).isKept());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).isSimpleEnum());
    assertTrue(((ProgramClassOptimizationInfo) processingInfo).mayBeMerged());
  }

  /**
   * Test {@link ProgramClassOptimizationInfoSetter#visitProgramClass(ProgramClass)}.
   * <p>
   * Method under test: {@link ProgramClassOptimizationInfoSetter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.ProgramClassOptimizationInfoSetter.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass2() {
    // Arrange
    ProgramClassOptimizationInfoSetter programClassOptimizationInfoSetter = new ProgramClassOptimizationInfoSetter(
        false);
    ProgramClass programClass = new ProgramClass(1, 3, new Constant[]{new ClassConstant()}, 1, 1, 1, "Feature Name", 1,
        "Processing Info");

    // Act
    programClassOptimizationInfoSetter.visitProgramClass(programClass);

    // Assert that nothing has changed
    assertEquals("Processing Info", programClass.getProcessingInfo());
  }

  /**
   * Test {@link ProgramClassOptimizationInfoSetter#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Then {@link ProgramClass#ProgramClass()} ProcessingInfo {@link ProgramClassOptimizationInfo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ProgramClassOptimizationInfoSetter#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then ProgramClass() ProcessingInfo ProgramClassOptimizationInfo")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.ProgramClassOptimizationInfoSetter.visitProgramClass(proguard.classfile.ProgramClass)"})
  void testVisitProgramClass_thenProgramClassProcessingInfoProgramClassOptimizationInfo() {
    // Arrange
    ProgramClassOptimizationInfoSetter programClassOptimizationInfoSetter = new ProgramClassOptimizationInfoSetter(
        true);
    ProgramClass programClass = new ProgramClass();

    // Act
    programClassOptimizationInfoSetter.visitProgramClass(programClass);

    // Assert
    Object processingInfo = programClass.getProcessingInfo();
    assertTrue(processingInfo instanceof ProgramClassOptimizationInfo);
    assertNull(((ProgramClassOptimizationInfo) processingInfo).getTargetClass());
    assertNull(((ProgramClassOptimizationInfo) processingInfo).getWrappedClass());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).hasNoSideEffects());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).containsConstructors());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).containsPackageVisibleMembers());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).hasSideEffects());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).invokesPackageVisibleMembers());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).isCaught());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).isDotClassed());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).isEscaping());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).isInstanceofed());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).isInstantiated());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).isKept());
    assertFalse(((ProgramClassOptimizationInfo) processingInfo).isSimpleEnum());
    assertTrue(((ProgramClassOptimizationInfo) processingInfo).mayBeMerged());
  }
}
