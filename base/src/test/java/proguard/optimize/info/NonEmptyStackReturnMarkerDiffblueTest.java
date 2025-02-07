package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
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
   * Test {@link NonEmptyStackReturnMarker#visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)}.
   * <p>
   * Method under test: {@link NonEmptyStackReturnMarker#visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)}
   */
  @Test
  @DisplayName("Test visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.NonEmptyStackReturnMarker.visitSimpleInstruction(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.SimpleInstruction)"})
  void testVisitSimpleInstruction() {
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
   * Test {@link NonEmptyStackReturnMarker#visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)}.
   * <ul>
   *   <li>Then calls {@link ProgramMethodOptimizationInfo#setReturnsWithNonEmptyStack()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonEmptyStackReturnMarker#visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)}
   */
  @Test
  @DisplayName("Test visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction); then calls setReturnsWithNonEmptyStack()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.NonEmptyStackReturnMarker.visitSimpleInstruction(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.SimpleInstruction)"})
  void testVisitSimpleInstruction_thenCallsSetReturnsWithNonEmptyStack() {
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
   * Test {@link NonEmptyStackReturnMarker#visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)}.
   * <ul>
   *   <li>When {@link SimpleInstruction#SimpleInstruction(byte)} with opcode is minus eighty-three.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonEmptyStackReturnMarker#visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)}
   */
  @Test
  @DisplayName("Test visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction); when SimpleInstruction(byte) with opcode is minus eighty-three")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.NonEmptyStackReturnMarker.visitSimpleInstruction(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.SimpleInstruction)"})
  void testVisitSimpleInstruction_whenSimpleInstructionWithOpcodeIsMinusEightyThree() {
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
   * Test {@link NonEmptyStackReturnMarker#visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)}.
   * <ul>
   *   <li>When {@link SimpleInstruction#SimpleInstruction(byte)} with opcode is minus seventy-nine.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonEmptyStackReturnMarker#visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)}
   */
  @Test
  @DisplayName("Test visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction); when SimpleInstruction(byte) with opcode is minus seventy-nine")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.NonEmptyStackReturnMarker.visitSimpleInstruction(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.SimpleInstruction)"})
  void testVisitSimpleInstruction_whenSimpleInstructionWithOpcodeIsMinusSeventyNine() {
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
   * Test {@link NonEmptyStackReturnMarker#returnsWithNonEmptyStack(Method)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NonEmptyStackReturnMarker#returnsWithNonEmptyStack(Method)}
   */
  @Test
  @DisplayName("Test returnsWithNonEmptyStack(Method); given MethodOptimizationInfo (default constructor); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.NonEmptyStackReturnMarker.returnsWithNonEmptyStack(proguard.classfile.Method)"})
  void testReturnsWithNonEmptyStack_givenMethodOptimizationInfo_thenReturnFalse() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertFalse(NonEmptyStackReturnMarker.returnsWithNonEmptyStack(method));
  }
}
