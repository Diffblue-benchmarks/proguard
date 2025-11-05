package proguard.optimize.evaluation;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.instruction.LookUpSwitchInstruction;
import proguard.classfile.instruction.TableSwitchInstruction;
import proguard.evaluation.PartialEvaluator;
import proguard.evaluation.TracedStack;
import proguard.evaluation.value.ArrayReferenceValue;
import proguard.evaluation.value.InstructionOffsetValue;
import proguard.optimize.DuplicateInitializerInvocationFixer;

class EvaluationSimplifierDiffblueTest {
  /**
   * Method under test:
   * {@link EvaluationSimplifier#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute() {
    // Arrange
    EvaluationSimplifier evaluationSimplifier = new EvaluationSimplifier(true);
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName()).thenReturn("Name");
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    evaluationSimplifier.visitCodeAttribute(clazz, method, new CodeAttribute(1));

    // Assert
    verify(clazz, atLeast(1)).getName();
  }

  /**
   * Method under test:
   * {@link EvaluationSimplifier#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute2() {
    // Arrange
    EvaluationSimplifier evaluationSimplifier = new EvaluationSimplifier(true);
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName()).thenReturn("Name");
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    evaluationSimplifier.visitCodeAttribute(clazz, method, new CodeAttribute());

    // Assert
    verify(clazz, atLeast(1)).getName();
  }

  /**
   * Method under test:
   * {@link EvaluationSimplifier#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute3() {
    // Arrange
    EvaluationSimplifier evaluationSimplifier = new EvaluationSimplifier(true);
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName()).thenReturn("Name");

    // Act
    evaluationSimplifier.visitCodeAttribute(clazz, new LibraryMethod(1, "Name", "Descriptor"), null);

    // Assert
    verify(clazz, atLeast(1)).getName();
  }

  /**
   * Method under test:
   * {@link EvaluationSimplifier#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute4() {
    // Arrange
    EvaluationSimplifier evaluationSimplifier = new EvaluationSimplifier(true);
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName()).thenReturn("Name");
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    evaluationSimplifier.visitCodeAttribute(clazz, method,
        new CodeAttribute(1, 3, 3, 3, new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}));

    // Assert
    verify(clazz, atLeast(1)).getName();
  }

  /**
   * Method under test:
   * {@link EvaluationSimplifier#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute5() {
    // Arrange
    EvaluationSimplifier evaluationSimplifier = new EvaluationSimplifier(true);
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName()).thenReturn("Name");
    LibraryMethod method = new LibraryMethod(-1, "Name", "Descriptor");

    // Act
    evaluationSimplifier.visitCodeAttribute(clazz, method,
        new CodeAttribute(1, 3, 3, 3, new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}));

    // Assert
    verify(clazz, atLeast(1)).getName();
  }

  /**
   * Method under test:
   * {@link EvaluationSimplifier#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute6() {
    // Arrange
    EvaluationSimplifier evaluationSimplifier = new EvaluationSimplifier(true);
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName()).thenReturn("Name");
    LibraryMethod method = new LibraryMethod(1, "Name", "EvaluationSimplifier [{}.{}{}]");

    // Act
    evaluationSimplifier.visitCodeAttribute(clazz, method,
        new CodeAttribute(1, 3, 3, 3, new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}));

    // Assert
    verify(clazz, atLeast(1)).getName();
  }

  /**
   * Method under test:
   * {@link EvaluationSimplifier#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute7() {
    // Arrange
    EvaluationSimplifier evaluationSimplifier = new EvaluationSimplifier(true);
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getAccessFlags()).thenReturn(1);
    when(clazz.getName()).thenReturn("Name");
    LibraryMethod method = new LibraryMethod(1, "Name", "  Exception   = [{}] ({})");

    // Act
    evaluationSimplifier.visitCodeAttribute(clazz, method,
        new CodeAttribute(1, 3, 3, 3, new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}));

    // Assert
    verify(clazz).getAccessFlags();
    verify(clazz, atLeast(1)).getName();
  }

  /**
   * Method under test:
   * {@link EvaluationSimplifier#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute8() {
    // Arrange
    EvaluationSimplifier evaluationSimplifier = new EvaluationSimplifier(true);
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getAccessFlags()).thenReturn(-1);
    when(clazz.getName()).thenReturn("Name");
    LibraryMethod method = new LibraryMethod(1, "Name", "  Exception   = [{}] ({})");

    // Act
    evaluationSimplifier.visitCodeAttribute(clazz, method,
        new CodeAttribute(1, 3, 3, 3, new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}));

    // Assert
    verify(clazz).getAccessFlags();
    verify(clazz, atLeast(1)).getName();
  }

  /**
   * Method under test:
   * {@link EvaluationSimplifier#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute9() {
    // Arrange
    EvaluationSimplifier evaluationSimplifier = new EvaluationSimplifier(true);
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName()).thenReturn("Name");
    LibraryMethod method = new LibraryMethod(-1, "Name", "  Exception   = [{}] ({})");

    // Act
    evaluationSimplifier.visitCodeAttribute(clazz, method,
        new CodeAttribute(1, 3, 3, 3, new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}));

    // Assert
    verify(clazz, atLeast(1)).getName();
  }

  /**
   * Method under test:
   * {@link EvaluationSimplifier#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute10() {
    // Arrange
    EvaluationSimplifier evaluationSimplifier = new EvaluationSimplifier(true);
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName()).thenReturn("Name");
    LibraryMethod method = new LibraryMethod(1, "Name", "  Exception   = [{}] ({})");

    // Act
    evaluationSimplifier.visitCodeAttribute(clazz, method,
        new CodeAttribute(1, -1, 3, 3, new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}));

    // Assert
    verify(clazz, atLeast(1)).getName();
  }

  /**
   * Method under test:
   * {@link EvaluationSimplifier#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute11() {
    // Arrange
    EvaluationSimplifier evaluationSimplifier = new EvaluationSimplifier(true);
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName()).thenReturn("Name");
    LibraryMethod method = new LibraryMethod(1, "Name", "  Exception   = [{}] ({})");

    // Act
    evaluationSimplifier.visitCodeAttribute(clazz, method,
        new CodeAttribute(1, 3, 3, -1, new byte[]{'A', 1, 'A', 1, 'A', 1, 'A', 1}));

    // Assert
    verify(clazz, atLeast(1)).getName();
  }

  /**
   * Method under test:
   * {@link EvaluationSimplifier#visitTableSwitchInstruction(Clazz, Method, CodeAttribute, int, TableSwitchInstruction)}
   */
  @Test
  void testVisitTableSwitchInstruction() {
    // Arrange
    InstructionOffsetValue instructionOffsetValue = mock(InstructionOffsetValue.class);
    when(instructionOffsetValue.instructionOffset(anyInt())).thenThrow(new RuntimeException("foo"));
    when(instructionOffsetValue.instructionOffsetCount()).thenReturn(1);
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.instructionOffsetValue()).thenReturn(instructionOffsetValue);
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTopProducerValue(anyInt())).thenReturn(arrayReferenceValue);
    InstructionOffsetValue instructionOffsetValue2 = mock(InstructionOffsetValue.class);
    when(instructionOffsetValue2.instructionOffset(anyInt())).thenReturn(1);
    when(instructionOffsetValue2.instructionOffsetCount()).thenReturn(3);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);
    when(partialEvaluator.branchTargets(anyInt())).thenReturn(instructionOffsetValue2);
    EvaluationSimplifier evaluationSimplifier = new EvaluationSimplifier(partialEvaluator,
        new DuplicateInitializerInvocationFixer(), true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> evaluationSimplifier.visitTableSwitchInstruction(clazz, method,
        codeAttribute, 2, new TableSwitchInstruction((byte) 'A', 1, 1, 1, new int[]{1, 0, 1, 0})));
    verify(partialEvaluator).branchTargets(eq(2));
    verify(partialEvaluator).getStackBefore(eq(2));
    verify(tracedStack).getTopProducerValue(eq(0));
    verify(instructionOffsetValue).instructionOffset(eq(0));
    verify(instructionOffsetValue).instructionOffsetCount();
    verify(instructionOffsetValue2).instructionOffsetCount();
    verify(arrayReferenceValue).instructionOffsetValue();
  }

  /**
   * Method under test:
   * {@link EvaluationSimplifier#visitLookUpSwitchInstruction(Clazz, Method, CodeAttribute, int, LookUpSwitchInstruction)}
   */
  @Test
  void testVisitLookUpSwitchInstruction() {
    // Arrange
    InstructionOffsetValue instructionOffsetValue = mock(InstructionOffsetValue.class);
    when(instructionOffsetValue.instructionOffset(anyInt())).thenThrow(new RuntimeException("foo"));
    when(instructionOffsetValue.instructionOffsetCount()).thenReturn(1);
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.instructionOffsetValue()).thenReturn(instructionOffsetValue);
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTopProducerValue(anyInt())).thenReturn(arrayReferenceValue);
    InstructionOffsetValue instructionOffsetValue2 = mock(InstructionOffsetValue.class);
    when(instructionOffsetValue2.instructionOffset(anyInt())).thenReturn(1);
    when(instructionOffsetValue2.instructionOffsetCount()).thenReturn(3);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);
    when(partialEvaluator.branchTargets(anyInt())).thenReturn(instructionOffsetValue2);
    EvaluationSimplifier evaluationSimplifier = new EvaluationSimplifier(partialEvaluator,
        new DuplicateInitializerInvocationFixer(), true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> evaluationSimplifier.visitLookUpSwitchInstruction(clazz, method,
        codeAttribute, 2, new LookUpSwitchInstruction((byte) 'A', 1, new int[]{1, 0, 1, 0}, new int[]{1, 0, 1, 0})));
    verify(partialEvaluator).branchTargets(eq(2));
    verify(partialEvaluator).getStackBefore(eq(2));
    verify(tracedStack).getTopProducerValue(eq(0));
    verify(instructionOffsetValue).instructionOffset(eq(0));
    verify(instructionOffsetValue).instructionOffsetCount();
    verify(instructionOffsetValue2).instructionOffsetCount();
    verify(arrayReferenceValue).instructionOffsetValue();
  }
}
