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

class InstantiationClassMarkerDiffblueTest {
  /**
   * Test {@link InstantiationClassMarker#visitClassConstant(Clazz, ClassConstant)}.
   * <ul>
   *   <li>Then {@link ClassConstant#ClassConstant()} {@link ClassConstant#referencedClass} {@link LibraryClass}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InstantiationClassMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then ClassConstant() referencedClass LibraryClass")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.InstantiationClassMarker.visitClassConstant(proguard.classfile.Clazz, proguard.classfile.constant.ClassConstant)"})
  void testVisitClassConstant_thenClassConstantReferencedClassLibraryClass() {
    // Arrange
    InstantiationClassMarker instantiationClassMarker = new InstantiationClassMarker();
    LibraryClass clazz = new LibraryClass();

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.setProcessingInfo(new ProgramClassOptimizationInfo());
    ClassConstant classConstant = new ClassConstant();
    classConstant.referencedClass = libraryClass;

    // Act
    instantiationClassMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Clazz clazz2 = classConstant.referencedClass;
    assertTrue(clazz2 instanceof LibraryClass);
    Object processingInfo = clazz2.getProcessingInfo();
    assertTrue(processingInfo instanceof ProgramClassOptimizationInfo);
    assertTrue(((ProgramClassOptimizationInfo) processingInfo).isInstantiated());
  }

  /**
   * Test {@link InstantiationClassMarker#visitAnyClass(Clazz)}.
   * <ul>
   *   <li>Then {@link LibraryClass#LibraryClass()} ProcessingInfo {@link ProgramClassOptimizationInfo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InstantiationClassMarker#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName("Test visitAnyClass(Clazz); then LibraryClass() ProcessingInfo ProgramClassOptimizationInfo")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.info.InstantiationClassMarker.visitAnyClass(proguard.classfile.Clazz)"})
  void testVisitAnyClass_thenLibraryClassProcessingInfoProgramClassOptimizationInfo() {
    // Arrange
    InstantiationClassMarker instantiationClassMarker = new InstantiationClassMarker();

    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ProgramClassOptimizationInfo());

    // Act
    instantiationClassMarker.visitAnyClass(clazz);

    // Assert
    Object processingInfo = clazz.getProcessingInfo();
    assertTrue(processingInfo instanceof ProgramClassOptimizationInfo);
    assertTrue(((ProgramClassOptimizationInfo) processingInfo).isInstantiated());
  }

  /**
   * Test {@link InstantiationClassMarker#isInstantiated(Clazz)}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InstantiationClassMarker#isInstantiated(Clazz)}
   */
  @Test
  @DisplayName("Test isInstantiated(Clazz); given ClassOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.InstantiationClassMarker.isInstantiated(proguard.classfile.Clazz)"})
  void testIsInstantiated_givenClassOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ClassOptimizationInfo());

    // Act and Assert
    assertTrue(InstantiationClassMarker.isInstantiated(clazz));
  }

  /**
   * Test {@link InstantiationClassMarker#isInstantiated(Clazz)}.
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link InstantiationClassMarker#isInstantiated(Clazz)}
   */
  @Test
  @DisplayName("Test isInstantiated(Clazz); given ProgramClassOptimizationInfo (default constructor); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.InstantiationClassMarker.isInstantiated(proguard.classfile.Clazz)"})
  void testIsInstantiated_givenProgramClassOptimizationInfo_thenReturnFalse() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ProgramClassOptimizationInfo());

    // Act and Assert
    assertFalse(InstantiationClassMarker.isInstantiated(clazz));
  }
}
