package proguard.optimize.peephole;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.LibraryClass;
import proguard.classfile.ProgramClass;
import proguard.classfile.visitor.ClassVisitor;

class VerticalClassMergerDiffblueTest {
  /**
   * Method under test:
   * {@link VerticalClassMerger#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    VerticalClassMerger verticalClassMerger = new VerticalClassMerger(true, true);
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).subclassesAccept(Mockito.<ClassVisitor>any());

    // Act
    verticalClassMerger.visitProgramClass(programClass);

    // Assert
    verify(programClass).subclassesAccept(isA(ClassVisitor.class));
  }

  /**
   * Method under test:
   * {@link VerticalClassMerger#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass2() {
    // Arrange
    VerticalClassMerger verticalClassMerger = new VerticalClassMerger(true, true);
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).accept(Mockito.<ClassVisitor>any());

    ProgramClass programClass = new ProgramClass();
    programClass.addSubClass(clazz);

    // Act
    verticalClassMerger.visitProgramClass(programClass);

    // Assert
    verify(clazz).accept(isA(ClassVisitor.class));
  }
}
