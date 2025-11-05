package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.attribute.visitor.StackSizeComputer;
import proguard.classfile.instruction.SimpleInstruction;

class NonEmptyStackReturnMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link NonEmptyStackReturnMarker#visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)}
   */
  @Test
  void testVisitSimpleInstruction() {
    // Arrange
    StackSizeComputer stackSizeComputer = mock(StackSizeComputer.class);
    when(stackSizeComputer.getStackSizeBefore(anyInt())).thenReturn(3);
    when(stackSizeComputer.isReachable(anyInt())).thenReturn(true);
    NonEmptyStackReturnMarker nonEmptyStackReturnMarker = new NonEmptyStackReturnMarker(stackSizeComputer);
    LibraryClass clazz = new LibraryClass();
    ProgramMethodOptimizationInfo programMethodOptimizationInfo = mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setReturnsWithNonEmptyStack();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);
    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    nonEmptyStackReturnMarker.visitSimpleInstruction(clazz, method, codeAttribute, 2,
        new SimpleInstruction((byte) -84));

    // Assert
    verify(stackSizeComputer).getStackSizeBefore(eq(2));
    verify(stackSizeComputer).isReachable(eq(2));
    verify(programMethodOptimizationInfo).setReturnsWithNonEmptyStack();
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link NonEmptyStackReturnMarker#visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)}
   */
  @Test
  void testVisitSimpleInstruction2() {
    // Arrange
    StackSizeComputer stackSizeComputer = mock(StackSizeComputer.class);
    when(stackSizeComputer.getStackSizeBefore(anyInt())).thenReturn(-84);
    when(stackSizeComputer.isReachable(anyInt())).thenReturn(true);
    NonEmptyStackReturnMarker nonEmptyStackReturnMarker = new NonEmptyStackReturnMarker(stackSizeComputer);
    LibraryClass clazz = new LibraryClass();
    Method method = mock(Method.class);
    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    nonEmptyStackReturnMarker.visitSimpleInstruction(clazz, method, codeAttribute, 2,
        new SimpleInstruction((byte) -84));

    // Assert
    verify(stackSizeComputer).getStackSizeBefore(eq(2));
    verify(stackSizeComputer).isReachable(eq(2));
  }

  /**
   * Method under test:
   * {@link NonEmptyStackReturnMarker#visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)}
   */
  @Test
  void testVisitSimpleInstruction3() {
    // Arrange
    StackSizeComputer stackSizeComputer = mock(StackSizeComputer.class);
    when(stackSizeComputer.getStackSizeBefore(anyInt())).thenReturn(3);
    when(stackSizeComputer.isReachable(anyInt())).thenReturn(true);
    NonEmptyStackReturnMarker nonEmptyStackReturnMarker = new NonEmptyStackReturnMarker(stackSizeComputer);
    LibraryClass clazz = new LibraryClass();
    ProgramMethodOptimizationInfo programMethodOptimizationInfo = mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setReturnsWithNonEmptyStack();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);
    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    nonEmptyStackReturnMarker.visitSimpleInstruction(clazz, method, codeAttribute, 2,
        new SimpleInstruction((byte) -83));

    // Assert
    verify(stackSizeComputer).getStackSizeBefore(eq(2));
    verify(stackSizeComputer).isReachable(eq(2));
    verify(programMethodOptimizationInfo).setReturnsWithNonEmptyStack();
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link NonEmptyStackReturnMarker#visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)}
   */
  @Test
  void testVisitSimpleInstruction4() {
    // Arrange
    StackSizeComputer stackSizeComputer = mock(StackSizeComputer.class);
    when(stackSizeComputer.getStackSizeBefore(anyInt())).thenReturn(3);
    when(stackSizeComputer.isReachable(anyInt())).thenReturn(true);
    NonEmptyStackReturnMarker nonEmptyStackReturnMarker = new NonEmptyStackReturnMarker(stackSizeComputer);
    LibraryClass clazz = new LibraryClass();
    ProgramMethodOptimizationInfo programMethodOptimizationInfo = mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setReturnsWithNonEmptyStack();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);
    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    nonEmptyStackReturnMarker.visitSimpleInstruction(clazz, method, codeAttribute, 2,
        new SimpleInstruction((byte) -79));

    // Assert
    verify(stackSizeComputer).getStackSizeBefore(eq(2));
    verify(stackSizeComputer).isReachable(eq(2));
    verify(programMethodOptimizationInfo).setReturnsWithNonEmptyStack();
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link NonEmptyStackReturnMarker#returnsWithNonEmptyStack(Method)}
   */
  @Test
  void testReturnsWithNonEmptyStack() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertFalse(NonEmptyStackReturnMarker.returnsWithNonEmptyStack(method));
  }
}
