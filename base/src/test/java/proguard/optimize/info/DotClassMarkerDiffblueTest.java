package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.constant.ClassConstant;

class DotClassMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link DotClassMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant() {
    // Arrange
    DotClassMarker dotClassMarker = new DotClassMarker();
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant();

    // Act
    dotClassMarker.visitClassConstant(clazz, classConstant);

    // Assert that nothing has changed
    assertNull(classConstant.referencedClass);
  }

  /**
   * Method under test:
   * {@link DotClassMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant2() {
    // Arrange
    DotClassMarker dotClassMarker = new DotClassMarker();
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant();
    classConstant.referencedClass = new LibraryClass();

    // Act
    dotClassMarker.visitClassConstant(clazz, classConstant);

    // Assert that nothing has changed
    assertTrue(classConstant.referencedClass instanceof LibraryClass);
  }

  /**
   * Method under test:
   * {@link DotClassMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant3() {
    // Arrange
    DotClassMarker dotClassMarker = new DotClassMarker();
    LibraryClass clazz = new LibraryClass();

    LibraryClass libraryClass = new LibraryClass();
    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();
    libraryClass.setProcessingInfo(programClassOptimizationInfo);
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
    assertSame(programClassOptimizationInfo, processingInfo);
  }

  /**
   * Method under test: {@link DotClassMarker#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    DotClassMarker dotClassMarker = new DotClassMarker();

    LibraryClass clazz = new LibraryClass();
    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();
    clazz.setProcessingInfo(programClassOptimizationInfo);

    // Act
    dotClassMarker.visitAnyClass(clazz);

    // Assert
    Object processingInfo = clazz.getProcessingInfo();
    assertTrue(processingInfo instanceof ProgramClassOptimizationInfo);
    assertTrue(((ProgramClassOptimizationInfo) processingInfo).isDotClassed());
    assertSame(programClassOptimizationInfo, processingInfo);
  }

  /**
   * Method under test: {@link DotClassMarker#isDotClassed(Clazz)}
   */
  @Test
  void testIsDotClassed() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.processingInfo = new ClassOptimizationInfo();

    // Act and Assert
    assertTrue(DotClassMarker.isDotClassed(clazz));
  }

  /**
   * Method under test: {@link DotClassMarker#isDotClassed(Clazz)}
   */
  @Test
  void testIsDotClassed2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.processingInfo = new ProgramClassOptimizationInfo();

    // Act and Assert
    assertFalse(DotClassMarker.isDotClassed(clazz));
  }
}
