package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.constant.ClassConstant;

class InstanceofClassMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link InstanceofClassMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant() {
    // Arrange
    InstanceofClassMarker instanceofClassMarker = new InstanceofClassMarker();
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant();

    // Act
    instanceofClassMarker.visitClassConstant(clazz, classConstant);

    // Assert that nothing has changed
    assertNull(classConstant.referencedClass);
  }

  /**
   * Method under test:
   * {@link InstanceofClassMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant2() {
    // Arrange
    InstanceofClassMarker instanceofClassMarker = new InstanceofClassMarker();
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant();
    classConstant.referencedClass = new LibraryClass();

    // Act
    instanceofClassMarker.visitClassConstant(clazz, classConstant);

    // Assert that nothing has changed
    assertTrue(classConstant.referencedClass instanceof LibraryClass);
  }

  /**
   * Method under test:
   * {@link InstanceofClassMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant3() {
    // Arrange
    InstanceofClassMarker instanceofClassMarker = new InstanceofClassMarker();
    LibraryClass clazz = new LibraryClass();

    LibraryClass libraryClass = new LibraryClass();
    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();
    libraryClass.setProcessingInfo(programClassOptimizationInfo);
    ClassConstant classConstant = new ClassConstant();
    classConstant.referencedClass = libraryClass;

    // Act
    instanceofClassMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Clazz clazz2 = classConstant.referencedClass;
    assertTrue(clazz2 instanceof LibraryClass);
    Object processingInfo = clazz2.getProcessingInfo();
    assertTrue(processingInfo instanceof ProgramClassOptimizationInfo);
    assertTrue(((ProgramClassOptimizationInfo) processingInfo).isInstanceofed());
    assertSame(programClassOptimizationInfo, processingInfo);
  }

  /**
   * Method under test: {@link InstanceofClassMarker#visitAnyClass(Clazz)}
   */
  @Test
  void testVisitAnyClass() {
    // Arrange
    InstanceofClassMarker instanceofClassMarker = new InstanceofClassMarker();

    LibraryClass clazz = new LibraryClass();
    ProgramClassOptimizationInfo programClassOptimizationInfo = new ProgramClassOptimizationInfo();
    clazz.setProcessingInfo(programClassOptimizationInfo);

    // Act
    instanceofClassMarker.visitAnyClass(clazz);

    // Assert
    Object processingInfo = clazz.getProcessingInfo();
    assertTrue(processingInfo instanceof ProgramClassOptimizationInfo);
    assertTrue(((ProgramClassOptimizationInfo) processingInfo).isInstanceofed());
    assertSame(programClassOptimizationInfo, processingInfo);
  }

  /**
   * Method under test: {@link InstanceofClassMarker#isInstanceofed(Clazz)}
   */
  @Test
  void testIsInstanceofed() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.processingInfo = new ClassOptimizationInfo();

    // Act and Assert
    assertTrue(InstanceofClassMarker.isInstanceofed(clazz));
  }

  /**
   * Method under test: {@link InstanceofClassMarker#isInstanceofed(Clazz)}
   */
  @Test
  void testIsInstanceofed2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.processingInfo = new ProgramClassOptimizationInfo();

    // Act and Assert
    assertFalse(InstanceofClassMarker.isInstanceofed(clazz));
  }
}
