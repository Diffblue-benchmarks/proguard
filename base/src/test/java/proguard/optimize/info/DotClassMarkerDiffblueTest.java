package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.constant.ClassConstant;

class DotClassMarkerDiffblueTest {
  /**
   * Test {@link DotClassMarker#visitClassConstant(Clazz, ClassConstant)}.
   * <ul>
   *   <li>Then {@link ClassConstant#ClassConstant()} {@link ClassConstant#referencedClass} {@link LibraryClass}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DotClassMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then ClassConstant() referencedClass LibraryClass")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.DotClassMarker.visitClassConstant(proguard.classfile.Clazz, proguard.classfile.constant.ClassConstant)"})
  void testVisitClassConstant_thenClassConstantReferencedClassLibraryClass() {
    // Arrange
    DotClassMarker dotClassMarker = new DotClassMarker();
    LibraryClass clazz = new LibraryClass();

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.setProcessingInfo(new ProgramClassOptimizationInfo());
    ClassConstant classConstant = new ClassConstant();
    classConstant.referencedClass = libraryClass;

    // Act
    dotClassMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Clazz clazz2 = classConstant.referencedClass;
    assertTrue(clazz2 instanceof LibraryClass);
    Object processingInfo = clazz2.getProcessingInfo();
    assertTrue(processingInfo instanceof ProgramClassOptimizationInfo);
    assertTrue(((ProgramClassOptimizationInfo) processingInfo).isDotClassed());
  }

  /**
   * Test {@link DotClassMarker#visitAnyClass(Clazz)}.
   * <ul>
   *   <li>Then {@link LibraryClass#LibraryClass()} ProcessingInfo {@link ProgramClassOptimizationInfo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DotClassMarker#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName("Test visitAnyClass(Clazz); then LibraryClass() ProcessingInfo ProgramClassOptimizationInfo")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.info.DotClassMarker.visitAnyClass(proguard.classfile.Clazz)"})
  void testVisitAnyClass_thenLibraryClassProcessingInfoProgramClassOptimizationInfo() {
    // Arrange
    DotClassMarker dotClassMarker = new DotClassMarker();

    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ProgramClassOptimizationInfo());

    // Act
    dotClassMarker.visitAnyClass(clazz);

    // Assert
    Object processingInfo = clazz.getProcessingInfo();
    assertTrue(processingInfo instanceof ProgramClassOptimizationInfo);
    assertTrue(((ProgramClassOptimizationInfo) processingInfo).isDotClassed());
  }

  /**
   * Test {@link DotClassMarker#isDotClassed(Clazz)}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DotClassMarker#isDotClassed(Clazz)}
   */
  @Test
  @DisplayName("Test isDotClassed(Clazz); given ClassOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.info.DotClassMarker.isDotClassed(proguard.classfile.Clazz)"})
  void testIsDotClassed_givenClassOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.processingInfo = new ClassOptimizationInfo();

    // Act and Assert
    assertTrue(DotClassMarker.isDotClassed(clazz));
  }

  /**
   * Test {@link DotClassMarker#isDotClassed(Clazz)}.
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DotClassMarker#isDotClassed(Clazz)}
   */
  @Test
  @DisplayName("Test isDotClassed(Clazz); given ProgramClassOptimizationInfo (default constructor); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.info.DotClassMarker.isDotClassed(proguard.classfile.Clazz)"})
  void testIsDotClassed_givenProgramClassOptimizationInfo_thenReturnFalse() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.processingInfo = new ProgramClassOptimizationInfo();

    // Act and Assert
    assertFalse(DotClassMarker.isDotClassed(clazz));
  }
}
