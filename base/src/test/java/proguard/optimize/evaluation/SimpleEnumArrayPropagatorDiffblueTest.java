package proguard.optimize.evaluation;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ProgramClass;
import proguard.classfile.visitor.MemberVisitor;

class SimpleEnumArrayPropagatorDiffblueTest {
  /**
   * Method under test:
   * {@link SimpleEnumArrayPropagator#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    SimpleEnumArrayPropagator simpleEnumArrayPropagator = new SimpleEnumArrayPropagator();
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).fieldsAccept(Mockito.<MemberVisitor>any());

    // Act
    simpleEnumArrayPropagator.visitProgramClass(programClass);

    // Assert
    verify(programClass).fieldsAccept(isA(MemberVisitor.class));
  }
}
