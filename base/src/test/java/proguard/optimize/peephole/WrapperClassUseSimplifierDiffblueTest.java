package proguard.optimize.peephole;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.MethodrefConstant;
import proguard.classfile.visitor.ClassVisitor;
import proguard.optimize.info.ClassOptimizationInfo;
import proguard.optimize.info.ProgramClassOptimizationInfo;

class WrapperClassUseSimplifierDiffblueTest {
  /**
   * Method under test:
   * {@link WrapperClassUseSimplifier#visitMethodrefConstant(Clazz, MethodrefConstant)}
   */
  @Test
  void testVisitMethodrefConstant() {
    // Arrange
    WrapperClassUseSimplifier wrapperClassUseSimplifier = new WrapperClassUseSimplifier();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName(anyInt())).thenReturn("Name");

    // Act
    wrapperClassUseSimplifier.visitMethodrefConstant(clazz, new MethodrefConstant());

    // Assert
    verify(clazz).getName(eq(0));
  }

  /**
   * Method under test:
   * {@link WrapperClassUseSimplifier#visitMethodrefConstant(Clazz, MethodrefConstant)}
   */
  @Test
  void testVisitMethodrefConstant2() {
    // Arrange
    WrapperClassUseSimplifier wrapperClassUseSimplifier = new WrapperClassUseSimplifier();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName(anyInt())).thenReturn("<init>");

    // Act
    wrapperClassUseSimplifier.visitMethodrefConstant(clazz, new MethodrefConstant());

    // Assert
    verify(clazz).getName(eq(0));
  }

  /**
   * Method under test:
   * {@link WrapperClassUseSimplifier#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant() {
    // Arrange
    WrapperClassUseSimplifier wrapperClassUseSimplifier = new WrapperClassUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = mock(ClassConstant.class);
    doNothing().when(classConstant).referencedClassAccept(Mockito.<ClassVisitor>any());

    // Act
    wrapperClassUseSimplifier.visitClassConstant(clazz, classConstant);

    // Assert
    verify(classConstant).referencedClassAccept(isA(ClassVisitor.class));
  }

  /**
   * Method under test:
   * {@link WrapperClassUseSimplifier#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant2() {
    // Arrange
    WrapperClassUseSimplifier wrapperClassUseSimplifier = new WrapperClassUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    doNothing().when(libraryClass).accept(Mockito.<ClassVisitor>any());
    ClassConstant classConstant = new ClassConstant();
    classConstant.referencedClass = libraryClass;

    // Act
    wrapperClassUseSimplifier.visitClassConstant(clazz, classConstant);

    // Assert
    verify(libraryClass).accept(isA(ClassVisitor.class));
  }

  /**
   * Method under test:
   * {@link WrapperClassUseSimplifier#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    WrapperClassUseSimplifier wrapperClassUseSimplifier = new WrapperClassUseSimplifier();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());

    // Act
    wrapperClassUseSimplifier.visitProgramClass(programClass);

    // Assert
    verify(programClass).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link WrapperClassUseSimplifier#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass2() {
    // Arrange
    WrapperClassUseSimplifier wrapperClassUseSimplifier = new WrapperClassUseSimplifier();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());

    // Act
    wrapperClassUseSimplifier.visitProgramClass(programClass);

    // Assert
    verify(programClass).getProcessingInfo();
  }
}
