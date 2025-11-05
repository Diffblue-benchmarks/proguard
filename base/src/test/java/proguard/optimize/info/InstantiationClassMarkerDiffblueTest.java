package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.constant.ClassConstant;

class InstantiationClassMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link InstantiationClassMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant() {
    // Arrange
    InstantiationClassMarker instantiationClassMarker = new InstantiationClassMarker();
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant();

    // Act
    instantiationClassMarker.visitClassConstant(clazz, classConstant);

    // Assert
    assertNull(classConstant.referencedClass);
  }

  /**
   * Method under test:
   * {@link InstantiationClassMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant2() {
    // Arrange
    InstantiationClassMarker instantiationClassMarker = new InstantiationClassMarker();
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant();
    classConstant.referencedClass = new LibraryClass();

    // Act
    instantiationClassMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Clazz clazz2 = classConstant.referencedClass;
    assertTrue(clazz2 instanceof LibraryClass);
    assertNull(clazz2.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link InstantiationClassMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant3() {
    // Arrange
    InstantiationClassMarker instantiationClassMarker = new InstantiationClassMarker();
    LibraryClass clazz = new LibraryClass();

    LibraryClass libraryClass = new LibraryClass();
    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();
    libraryClass.setProcessingInfo(programClassOptimizationInfo);
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
    assertSame(programClassOptimizationInfo, processingInfo);
  }

  /**
   * Method under test: {@link InstantiationClassMarker#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    InstantiationClassMarker instantiationClassMarker = new InstantiationClassMarker();

    LibraryClass clazz = new LibraryClass();
    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();
    clazz.setProcessingInfo(programClassOptimizationInfo);

    // Act
    instantiationClassMarker.visitAnyClass(clazz);

    // Assert
    Object processingInfo = clazz.getProcessingInfo();
    assertTrue(processingInfo instanceof ProgramClassOptimizationInfo);
    assertTrue(((ProgramClassOptimizationInfo) processingInfo).isInstantiated());
    assertSame(programClassOptimizationInfo, processingInfo);
  }

  /**
   * Method under test: {@link InstantiationClassMarker#isInstantiated(Clazz)}
   */
  @Test
  void testIsInstantiated() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ClassOptimizationInfo());

    // Act and Assert
    assertTrue(InstantiationClassMarker.isInstantiated(clazz));
  }

  /**
   * Method under test: {@link InstantiationClassMarker#isInstantiated(Clazz)}
   */
  @Test
  void testIsInstantiated2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ProgramClassOptimizationInfo());

    // Act and Assert
    assertFalse(InstantiationClassMarker.isInstantiated(clazz));
  }
}
