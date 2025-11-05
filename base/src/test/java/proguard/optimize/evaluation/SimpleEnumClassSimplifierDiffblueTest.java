package proguard.optimize.evaluation;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ProgramClass;
import proguard.classfile.visitor.MemberVisitor;

class SimpleEnumClassSimplifierDiffblueTest {
  /**
   * Method under test:
   * {@link SimpleEnumClassSimplifier#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    SimpleEnumClassSimplifier simpleEnumClassSimplifier = new SimpleEnumClassSimplifier();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");
    when(programClass.findMethod(Mockito.<String>any(), Mockito.<String>any())).thenReturn(null);
    doNothing().when(programClass)
        .methodAccept(Mockito.<String>any(), Mockito.<String>any(), Mockito.<MemberVisitor>any());

    // Act
    simpleEnumClassSimplifier.visitProgramClass(programClass);

    // Assert
    verify(programClass).findMethod(eq("valueOf"), isNull());
    verify(programClass).getName();
    verify(programClass).methodAccept(eq("<clinit>"), eq("()V"), isA(MemberVisitor.class));
  }
}
