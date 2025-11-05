package proguard.optimize.evaluation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.instruction.visitor.InstructionVisitor;
import proguard.evaluation.PartialEvaluator;
import proguard.evaluation.TracedStack;
import proguard.evaluation.value.ArrayReferenceValue;
import proguard.evaluation.value.InstructionOffsetValue;
import proguard.optimize.DuplicateInitializerInvocationFixer;

class InstructionUsageMarkerDiffblueTest {
  /**
   * Method under test: {@link InstructionUsageMarker#isTraced(int)}
   */
  @Test
  void testIsTraced() {
    // Arrange, Act and Assert
    assertFalse((new InstructionUsageMarker(true)).isTraced(1));
  }

  /**
   * Method under test:
   * {@link InstructionUsageMarker#tracedInstructionFilter(InstructionVisitor)}
   */
  @Test
  void testTracedInstructionFilter() {
    // Arrange
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    DuplicateInitializerInvocationFixer duplicateInitializerInvocationFixer = new DuplicateInitializerInvocationFixer();
    when(partialEvaluator.tracedInstructionFilter(Mockito.<InstructionVisitor>any()))
        .thenReturn(duplicateInitializerInvocationFixer);
    InstructionUsageMarker instructionUsageMarker = new InstructionUsageMarker(partialEvaluator, true, true);

    // Act
    InstructionVisitor actualTracedInstructionFilterResult = instructionUsageMarker
        .tracedInstructionFilter(new DuplicateInitializerInvocationFixer());

    // Assert
    verify(partialEvaluator).tracedInstructionFilter(isA(InstructionVisitor.class));
    assertTrue(actualTracedInstructionFilterResult instanceof DuplicateInitializerInvocationFixer);
    assertSame(duplicateInitializerInvocationFixer, actualTracedInstructionFilterResult);
  }

  /**
   * Method under test:
   * {@link InstructionUsageMarker#tracedInstructionFilter(boolean, InstructionVisitor)}
   */
  @Test
  void testTracedInstructionFilter2() {
    // Arrange
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    DuplicateInitializerInvocationFixer duplicateInitializerInvocationFixer = new DuplicateInitializerInvocationFixer();
    when(partialEvaluator.tracedInstructionFilter(anyBoolean(), Mockito.<InstructionVisitor>any()))
        .thenReturn(duplicateInitializerInvocationFixer);
    InstructionUsageMarker instructionUsageMarker = new InstructionUsageMarker(partialEvaluator, true, true);

    // Act
    InstructionVisitor actualTracedInstructionFilterResult = instructionUsageMarker.tracedInstructionFilter(true,
        new DuplicateInitializerInvocationFixer());

    // Assert
    verify(partialEvaluator).tracedInstructionFilter(eq(true), isA(InstructionVisitor.class));
    assertTrue(actualTracedInstructionFilterResult instanceof DuplicateInitializerInvocationFixer);
    assertSame(duplicateInitializerInvocationFixer, actualTracedInstructionFilterResult);
  }

  /**
   * Method under test: {@link InstructionUsageMarker#isInstructionNecessary(int)}
   */
  @Test
  void testIsInstructionNecessary() {
    // Arrange, Act and Assert
    assertFalse((new InstructionUsageMarker(true)).isInstructionNecessary(1));
  }

  /**
   * Method under test:
   * {@link InstructionUsageMarker#isExtraPushPopInstructionNecessary(int)}
   */
  @Test
  void testIsExtraPushPopInstructionNecessary() {
    // Arrange, Act and Assert
    assertFalse((new InstructionUsageMarker(true)).isExtraPushPopInstructionNecessary(1));
  }

  /**
   * Method under test: {@link InstructionUsageMarker#getStackBefore(int)}
   */
  @Test
  void testGetStackBefore() {
    // Arrange, Act and Assert
    assertNull((new InstructionUsageMarker(true)).getStackBefore(1));
  }

  /**
   * Method under test: {@link InstructionUsageMarker#getStackAfter(int)}
   */
  @Test
  void testGetStackAfter() {
    // Arrange, Act and Assert
    assertNull((new InstructionUsageMarker(true)).getStackAfter(1));
  }

  /**
   * Method under test:
   * {@link InstructionUsageMarker#isStackEntryUnwantedBefore(int, int)}
   */
  @Test
  void testIsStackEntryUnwantedBefore() {
    // Arrange, Act and Assert
    assertFalse((new InstructionUsageMarker(true)).isStackEntryUnwantedBefore(1, 1));
  }

  /**
   * Method under test:
   * {@link InstructionUsageMarker#isStackEntriesPresentBefore(int, int, int)}
   */
  @Test
  void testIsStackEntriesPresentBefore() {
    // Arrange
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.instructionOffsetValue()).thenReturn(new InstructionOffsetValue(42));
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getBottomProducerValue(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);

    // Act
    boolean actualIsStackEntriesPresentBeforeResult = (new InstructionUsageMarker(partialEvaluator, true, true))
        .isStackEntriesPresentBefore(1, 1, 1);

    // Assert
    verify(partialEvaluator, atLeast(1)).getStackBefore(eq(1));
    verify(tracedStack, atLeast(1)).getBottomProducerValue(eq(1));
    verify(arrayReferenceValue, atLeast(1)).instructionOffsetValue();
    assertFalse(actualIsStackEntriesPresentBeforeResult);
  }

  /**
   * Method under test:
   * {@link InstructionUsageMarker#isStackEntriesPresentBefore(int, int, int)}
   */
  @Test
  void testIsStackEntriesPresentBefore2() {
    // Arrange
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.instructionOffsetValue())
        .thenReturn(new InstructionOffsetValue(InstructionOffsetValue.EXCEPTION_HANDLER));
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getBottomProducerValue(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);

    // Act
    boolean actualIsStackEntriesPresentBeforeResult = (new InstructionUsageMarker(partialEvaluator, true, true))
        .isStackEntriesPresentBefore(1, 1, 1);

    // Assert
    verify(partialEvaluator, atLeast(1)).getStackBefore(eq(1));
    verify(tracedStack, atLeast(1)).getBottomProducerValue(eq(1));
    verify(arrayReferenceValue, atLeast(1)).instructionOffsetValue();
    assertTrue(actualIsStackEntriesPresentBeforeResult);
  }

  /**
   * Method under test:
   * {@link InstructionUsageMarker#isStackEntriesPresentBefore(int, int, int)}
   */
  @Test
  void testIsStackEntriesPresentBefore3() {
    // Arrange
    InstructionOffsetValue instructionOffsetValue = mock(InstructionOffsetValue.class);
    when(instructionOffsetValue.isExceptionHandler(anyInt())).thenReturn(true);
    when(instructionOffsetValue.instructionOffsetCount()).thenReturn(3);
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.instructionOffsetValue()).thenReturn(instructionOffsetValue);
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getBottomProducerValue(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);

    // Act
    boolean actualIsStackEntriesPresentBeforeResult = (new InstructionUsageMarker(partialEvaluator, true, true))
        .isStackEntriesPresentBefore(1, 1, 1);

    // Assert
    verify(partialEvaluator, atLeast(1)).getStackBefore(eq(1));
    verify(tracedStack, atLeast(1)).getBottomProducerValue(eq(1));
    verify(instructionOffsetValue, atLeast(1)).instructionOffsetCount();
    verify(instructionOffsetValue, atLeast(1)).isExceptionHandler(eq(0));
    verify(arrayReferenceValue, atLeast(1)).instructionOffsetValue();
    assertTrue(actualIsStackEntriesPresentBeforeResult);
  }

  /**
   * Method under test:
   * {@link InstructionUsageMarker#isStackEntriesPresentBefore(int, int, int)}
   */
  @Test
  void testIsStackEntriesPresentBefore4() {
    // Arrange
    InstructionOffsetValue instructionOffsetValue = mock(InstructionOffsetValue.class);
    when(instructionOffsetValue.isExceptionHandler(anyInt())).thenThrow(new RuntimeException("foo"));
    when(instructionOffsetValue.instructionOffsetCount()).thenReturn(3);
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.instructionOffsetValue()).thenReturn(instructionOffsetValue);
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getBottomProducerValue(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);

    // Act and Assert
    assertThrows(RuntimeException.class,
        () -> (new InstructionUsageMarker(partialEvaluator, true, true)).isStackEntriesPresentBefore(1, 1, 1));
    verify(partialEvaluator).getStackBefore(eq(1));
    verify(tracedStack).getBottomProducerValue(eq(1));
    verify(instructionOffsetValue).instructionOffsetCount();
    verify(instructionOffsetValue).isExceptionHandler(eq(0));
    verify(arrayReferenceValue).instructionOffsetValue();
  }

  /**
   * Method under test:
   * {@link InstructionUsageMarker#isStackEntriesPresentBefore(int, int, int)}
   */
  @Test
  void testIsStackEntriesPresentBefore5() {
    // Arrange
    InstructionOffsetValue instructionOffsetValue = mock(InstructionOffsetValue.class);
    when(instructionOffsetValue.isExceptionHandler(anyInt())).thenReturn(false);
    when(instructionOffsetValue.instructionOffset(anyInt())).thenReturn(1);
    when(instructionOffsetValue.instructionOffsetCount()).thenReturn(3);
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.instructionOffsetValue()).thenReturn(instructionOffsetValue);
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getBottomProducerValue(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);

    // Act
    boolean actualIsStackEntriesPresentBeforeResult = (new InstructionUsageMarker(partialEvaluator, true, true))
        .isStackEntriesPresentBefore(1, 1, 1);

    // Assert
    verify(partialEvaluator, atLeast(1)).getStackBefore(eq(1));
    verify(tracedStack, atLeast(1)).getBottomProducerValue(eq(1));
    verify(instructionOffsetValue, atLeast(1)).instructionOffset(anyInt());
    verify(instructionOffsetValue, atLeast(1)).instructionOffsetCount();
    verify(instructionOffsetValue, atLeast(1)).isExceptionHandler(anyInt());
    verify(arrayReferenceValue, atLeast(1)).instructionOffsetValue();
    assertFalse(actualIsStackEntriesPresentBeforeResult);
  }

  /**
   * Method under test:
   * {@link InstructionUsageMarker#isStackEntriesPresentBefore(int, int, int)}
   */
  @Test
  void testIsStackEntriesPresentBefore6() {
    // Arrange
    InstructionOffsetValue instructionOffsetValue = mock(InstructionOffsetValue.class);
    when(instructionOffsetValue.isExceptionHandler(anyInt())).thenReturn(false);
    when(instructionOffsetValue.instructionOffset(anyInt())).thenReturn(536870912);
    when(instructionOffsetValue.instructionOffsetCount()).thenReturn(3);
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.instructionOffsetValue()).thenReturn(instructionOffsetValue);
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getBottomProducerValue(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);

    // Act
    boolean actualIsStackEntriesPresentBeforeResult = (new InstructionUsageMarker(partialEvaluator, true, true))
        .isStackEntriesPresentBefore(1, 1, 1);

    // Assert
    verify(partialEvaluator, atLeast(1)).getStackBefore(eq(1));
    verify(tracedStack, atLeast(1)).getBottomProducerValue(eq(1));
    verify(instructionOffsetValue, atLeast(1)).instructionOffset(eq(0));
    verify(instructionOffsetValue, atLeast(1)).instructionOffsetCount();
    verify(instructionOffsetValue, atLeast(1)).isExceptionHandler(eq(0));
    verify(arrayReferenceValue, atLeast(1)).instructionOffsetValue();
    assertTrue(actualIsStackEntriesPresentBeforeResult);
  }

  /**
   * Method under test:
   * {@link InstructionUsageMarker#isStackEntryPresentBefore(int, int)}
   */
  @Test
  void testIsStackEntryPresentBefore() {
    // Arrange
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.instructionOffsetValue()).thenReturn(new InstructionOffsetValue(42));
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getBottomProducerValue(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);

    // Act
    boolean actualIsStackEntryPresentBeforeResult = (new InstructionUsageMarker(partialEvaluator, true, true))
        .isStackEntryPresentBefore(1, 1);

    // Assert
    verify(partialEvaluator).getStackBefore(eq(1));
    verify(tracedStack).getBottomProducerValue(eq(1));
    verify(arrayReferenceValue).instructionOffsetValue();
    assertFalse(actualIsStackEntryPresentBeforeResult);
  }

  /**
   * Method under test:
   * {@link InstructionUsageMarker#isStackEntryPresentBefore(int, int)}
   */
  @Test
  void testIsStackEntryPresentBefore2() {
    // Arrange
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.instructionOffsetValue())
        .thenReturn(new InstructionOffsetValue(InstructionOffsetValue.EXCEPTION_HANDLER));
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getBottomProducerValue(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);

    // Act
    boolean actualIsStackEntryPresentBeforeResult = (new InstructionUsageMarker(partialEvaluator, true, true))
        .isStackEntryPresentBefore(1, 1);

    // Assert
    verify(partialEvaluator).getStackBefore(eq(1));
    verify(tracedStack).getBottomProducerValue(eq(1));
    verify(arrayReferenceValue).instructionOffsetValue();
    assertTrue(actualIsStackEntryPresentBeforeResult);
  }

  /**
   * Method under test:
   * {@link InstructionUsageMarker#isStackEntryPresentBefore(int, int)}
   */
  @Test
  void testIsStackEntryPresentBefore3() {
    // Arrange
    InstructionOffsetValue instructionOffsetValue = mock(InstructionOffsetValue.class);
    when(instructionOffsetValue.isExceptionHandler(anyInt())).thenReturn(true);
    when(instructionOffsetValue.instructionOffsetCount()).thenReturn(3);
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.instructionOffsetValue()).thenReturn(instructionOffsetValue);
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getBottomProducerValue(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);

    // Act
    boolean actualIsStackEntryPresentBeforeResult = (new InstructionUsageMarker(partialEvaluator, true, true))
        .isStackEntryPresentBefore(1, 1);

    // Assert
    verify(partialEvaluator).getStackBefore(eq(1));
    verify(tracedStack).getBottomProducerValue(eq(1));
    verify(instructionOffsetValue).instructionOffsetCount();
    verify(instructionOffsetValue).isExceptionHandler(eq(0));
    verify(arrayReferenceValue).instructionOffsetValue();
    assertTrue(actualIsStackEntryPresentBeforeResult);
  }

  /**
   * Method under test:
   * {@link InstructionUsageMarker#isStackEntryPresentBefore(int, int)}
   */
  @Test
  void testIsStackEntryPresentBefore4() {
    // Arrange
    InstructionOffsetValue instructionOffsetValue = mock(InstructionOffsetValue.class);
    when(instructionOffsetValue.isExceptionHandler(anyInt())).thenThrow(new RuntimeException("foo"));
    when(instructionOffsetValue.instructionOffsetCount()).thenReturn(3);
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.instructionOffsetValue()).thenReturn(instructionOffsetValue);
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getBottomProducerValue(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);

    // Act and Assert
    assertThrows(RuntimeException.class,
        () -> (new InstructionUsageMarker(partialEvaluator, true, true)).isStackEntryPresentBefore(1, 1));
    verify(partialEvaluator).getStackBefore(eq(1));
    verify(tracedStack).getBottomProducerValue(eq(1));
    verify(instructionOffsetValue).instructionOffsetCount();
    verify(instructionOffsetValue).isExceptionHandler(eq(0));
    verify(arrayReferenceValue).instructionOffsetValue();
  }

  /**
   * Method under test:
   * {@link InstructionUsageMarker#isStackEntryPresentBefore(int, int)}
   */
  @Test
  void testIsStackEntryPresentBefore5() {
    // Arrange
    InstructionOffsetValue instructionOffsetValue = mock(InstructionOffsetValue.class);
    when(instructionOffsetValue.isExceptionHandler(anyInt())).thenReturn(false);
    when(instructionOffsetValue.instructionOffset(anyInt())).thenReturn(1);
    when(instructionOffsetValue.instructionOffsetCount()).thenReturn(3);
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.instructionOffsetValue()).thenReturn(instructionOffsetValue);
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getBottomProducerValue(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);

    // Act
    boolean actualIsStackEntryPresentBeforeResult = (new InstructionUsageMarker(partialEvaluator, true, true))
        .isStackEntryPresentBefore(1, 1);

    // Assert
    verify(partialEvaluator).getStackBefore(eq(1));
    verify(tracedStack).getBottomProducerValue(eq(1));
    verify(instructionOffsetValue, atLeast(1)).instructionOffset(anyInt());
    verify(instructionOffsetValue).instructionOffsetCount();
    verify(instructionOffsetValue, atLeast(1)).isExceptionHandler(anyInt());
    verify(arrayReferenceValue).instructionOffsetValue();
    assertFalse(actualIsStackEntryPresentBeforeResult);
  }

  /**
   * Method under test:
   * {@link InstructionUsageMarker#isStackEntryPresentBefore(int, int)}
   */
  @Test
  void testIsStackEntryPresentBefore6() {
    // Arrange
    InstructionOffsetValue instructionOffsetValue = mock(InstructionOffsetValue.class);
    when(instructionOffsetValue.isExceptionHandler(anyInt())).thenReturn(false);
    when(instructionOffsetValue.instructionOffset(anyInt())).thenReturn(536870912);
    when(instructionOffsetValue.instructionOffsetCount()).thenReturn(3);
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.instructionOffsetValue()).thenReturn(instructionOffsetValue);
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getBottomProducerValue(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);

    // Act
    boolean actualIsStackEntryPresentBeforeResult = (new InstructionUsageMarker(partialEvaluator, true, true))
        .isStackEntryPresentBefore(1, 1);

    // Assert
    verify(partialEvaluator).getStackBefore(eq(1));
    verify(tracedStack).getBottomProducerValue(eq(1));
    verify(instructionOffsetValue).instructionOffset(eq(0));
    verify(instructionOffsetValue).instructionOffsetCount();
    verify(instructionOffsetValue).isExceptionHandler(eq(0));
    verify(arrayReferenceValue).instructionOffsetValue();
    assertTrue(actualIsStackEntryPresentBeforeResult);
  }

  /**
   * Method under test:
   * {@link InstructionUsageMarker#isStackEntriesNecessaryAfter(int, int, int)}
   */
  @Test
  void testIsStackEntriesNecessaryAfter() {
    // Arrange, Act and Assert
    assertFalse((new InstructionUsageMarker(true)).isStackEntriesNecessaryAfter(1, 1, 1));
    assertTrue((new InstructionUsageMarker(true)).isStackEntriesNecessaryAfter(536870912, 1, 1));
  }

  /**
   * Method under test:
   * {@link InstructionUsageMarker#isAnyStackEntryNecessaryAfter(InstructionOffsetValue, int)}
   */
  @Test
  void testIsAnyStackEntryNecessaryAfter() {
    // Arrange
    InstructionUsageMarker instructionUsageMarker = new InstructionUsageMarker(true);

    // Act and Assert
    assertFalse(instructionUsageMarker.isAnyStackEntryNecessaryAfter(new InstructionOffsetValue(42), 1));
  }

  /**
   * Method under test:
   * {@link InstructionUsageMarker#isAnyStackEntryNecessaryAfter(InstructionOffsetValue, int)}
   */
  @Test
  void testIsAnyStackEntryNecessaryAfter2() {
    // Arrange
    InstructionUsageMarker instructionUsageMarker = new InstructionUsageMarker(true);

    // Act and Assert
    assertTrue(instructionUsageMarker
        .isAnyStackEntryNecessaryAfter(new InstructionOffsetValue(InstructionOffsetValue.EXCEPTION_HANDLER), 1));
  }

  /**
   * Method under test:
   * {@link InstructionUsageMarker#isStackEntryNecessaryAfter(int, int)}
   */
  @Test
  void testIsStackEntryNecessaryAfter() {
    // Arrange, Act and Assert
    assertFalse((new InstructionUsageMarker(true)).isStackEntryNecessaryAfter(1, 1));
    assertTrue((new InstructionUsageMarker(true)).isStackEntryNecessaryAfter(536870912, 1));
  }

  /**
   * Method under test: {@link InstructionUsageMarker#branchTargets(int)}
   */
  @Test
  void testBranchTargets() {
    // Arrange, Act and Assert
    assertNull((new InstructionUsageMarker(true)).branchTargets(1));
  }

  /**
   * Method under test:
   * {@link InstructionUsageMarker#InstructionUsageMarker(PartialEvaluator, boolean, boolean)}
   */
  @Test
  void testNewInstructionUsageMarker() {
    // Arrange and Act
    InstructionUsageMarker actualInstructionUsageMarker = new InstructionUsageMarker(new PartialEvaluator(), true,
        true);

    // Assert
    assertNull(actualInstructionUsageMarker.getStackAfter(1));
    assertNull(actualInstructionUsageMarker.getStackBefore(1));
    assertNull(actualInstructionUsageMarker.branchTargets(1));
    assertFalse(actualInstructionUsageMarker.isExtraPushPopInstructionNecessary(1));
  }

  /**
   * Method under test:
   * {@link InstructionUsageMarker#InstructionUsageMarker(PartialEvaluator, boolean, boolean, boolean)}
   */
  @Test
  void testNewInstructionUsageMarker2() {
    // Arrange and Act
    InstructionUsageMarker actualInstructionUsageMarker = new InstructionUsageMarker(new PartialEvaluator(), true, true,
        true);

    // Assert
    assertNull(actualInstructionUsageMarker.getStackAfter(1));
    assertNull(actualInstructionUsageMarker.getStackBefore(1));
    assertNull(actualInstructionUsageMarker.branchTargets(1));
    assertFalse(actualInstructionUsageMarker.isExtraPushPopInstructionNecessary(1));
  }

  /**
   * Method under test:
   * {@link InstructionUsageMarker#InstructionUsageMarker(PartialEvaluator, boolean, boolean, boolean)}
   */
  @Test
  void testNewInstructionUsageMarker3() {
    // Arrange and Act
    InstructionUsageMarker actualInstructionUsageMarker = new InstructionUsageMarker(new PartialEvaluator(), true,
        false, true);

    // Assert
    assertNull(actualInstructionUsageMarker.getStackAfter(1));
    assertNull(actualInstructionUsageMarker.getStackBefore(1));
    assertNull(actualInstructionUsageMarker.branchTargets(1));
    assertFalse(actualInstructionUsageMarker.isExtraPushPopInstructionNecessary(1));
  }

  /**
   * Method under test:
   * {@link InstructionUsageMarker#InstructionUsageMarker(boolean)}
   */
  @Test
  void testNewInstructionUsageMarker4() {
    // Arrange and Act
    InstructionUsageMarker actualInstructionUsageMarker = new InstructionUsageMarker(true);

    // Assert
    assertNull(actualInstructionUsageMarker.getStackAfter(1));
    assertNull(actualInstructionUsageMarker.getStackBefore(1));
    assertNull(actualInstructionUsageMarker.branchTargets(1));
    assertFalse(actualInstructionUsageMarker.isExtraPushPopInstructionNecessary(1));
  }
}
