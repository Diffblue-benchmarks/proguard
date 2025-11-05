package proguard.optimize.peephole;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.visitor.ClassVisitor;
import proguard.optimize.info.ClassOptimizationInfo;
import proguard.optimize.info.ProgramClassOptimizationInfo;

class ClassFinalizerDiffblueTest {
  /**
   * Method under test: {@link ClassFinalizer#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    ClassFinalizer classFinalizer = new ClassFinalizer();
    ProgramClass programClass = new ProgramClass();

    // Act
    classFinalizer.visitProgramClass(programClass);

    // Assert
    assertEquals(Short.SIZE, programClass.getAccessFlags());
  }

  /**
   * Method under test: {@link ClassFinalizer#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass2() {
    // Arrange
    ClassVisitor extraClassVisitor = mock(ClassVisitor.class);
    doNothing().when(extraClassVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    ClassFinalizer classFinalizer = new ClassFinalizer(extraClassVisitor);
    ProgramClass programClass = new ProgramClass();

    // Act
    classFinalizer.visitProgramClass(programClass);

    // Assert
    verify(extraClassVisitor).visitProgramClass(isA(ProgramClass.class));
    assertEquals(Short.SIZE, programClass.getAccessFlags());
  }

  /**
   * Method under test: {@link ClassFinalizer#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass3() {
    // Arrange
    ClassFinalizer classFinalizer = new ClassFinalizer(mock(ClassVisitor.class));

    ProgramClass programClass = new ProgramClass();
    programClass.addSubClass(new LibraryClass());

    // Act
    classFinalizer.visitProgramClass(programClass);

    // Assert that nothing has changed
    assertEquals(0, programClass.getAccessFlags());
  }

  /**
   * Method under test: {@link ClassFinalizer#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass4() {
    // Arrange
    ClassFinalizer classFinalizer = new ClassFinalizer(mock(ClassVisitor.class));

    ProgramClass programClass = new ProgramClass();
    programClass.setProcessingInfo(new ClassOptimizationInfo());
    programClass.addSubClass(new LibraryClass());

    // Act
    classFinalizer.visitProgramClass(programClass);

    // Assert that nothing has changed
    assertEquals(0, programClass.getAccessFlags());
  }

  /**
   * Method under test: {@link ClassFinalizer#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass5() {
    // Arrange
    ClassFinalizer classFinalizer = new ClassFinalizer(mock(ClassVisitor.class));

    ProgramClass programClass = new ProgramClass();
    programClass.setProcessingInfo(new ProgramClassOptimizationInfo());
    programClass.addSubClass(new LibraryClass());

    // Act
    classFinalizer.visitProgramClass(programClass);

    // Assert that nothing has changed
    assertEquals(0, programClass.getAccessFlags());
  }
}
