package proguard.optimize.gson;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.MethodImplementationFilter;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.testutils.cpa.NamedClass;

class OptimizedJsonFieldVisitorDiffblueTest {
  /**
   * Method under test:
   * {@link OptimizedJsonFieldVisitor#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doNothing().when(classVisitor).visitProgramClass(Mockito.<ProgramClass>any());
    OptimizedJsonFieldVisitor optimizedJsonFieldVisitor = new OptimizedJsonFieldVisitor(classVisitor,
        new KotlinAnnotationCounter());

    // Act
    optimizedJsonFieldVisitor.visitProgramClass(new NamedClass("Member Name"));

    // Assert
    verify(classVisitor).visitProgramClass(isA(ProgramClass.class));
  }

  /**
   * Method under test:
   * {@link OptimizedJsonFieldVisitor#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField() {
    // Arrange
    MethodImplementationFilter memberVisitor = mock(MethodImplementationFilter.class);
    doNothing().when(memberVisitor).visitProgramField(Mockito.<ProgramClass>any(), Mockito.<ProgramField>any());
    OptimizedJsonFieldVisitor optimizedJsonFieldVisitor = new OptimizedJsonFieldVisitor(mock(ClassVisitor.class),
        memberVisitor);
    ProgramClass programClass = new ProgramClass();

    // Act
    optimizedJsonFieldVisitor.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(memberVisitor).visitProgramField(isA(ProgramClass.class), isA(ProgramField.class));
  }
}
