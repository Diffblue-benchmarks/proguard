package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.classfile.instruction.SimpleInstruction;
import proguard.evaluation.PartialEvaluator;
import proguard.evaluation.TracedStack;
import proguard.evaluation.value.ArrayReferenceValue;

class ReferenceEscapeCheckerDiffblueTest {
  /**
   * Method under test: {@link ReferenceEscapeChecker#isInstanceEscaping(int)}
   */
  @Test
  void testIsInstanceEscaping() {
    // Arrange, Act and Assert
    assertFalse((new ReferenceEscapeChecker()).isInstanceEscaping(1));
  }

  /**
   * Method under test: {@link ReferenceEscapeChecker#isInstanceReturned(int)}
   */
  @Test
  void testIsInstanceReturned() {
    // Arrange, Act and Assert
    assertFalse((new ReferenceEscapeChecker()).isInstanceReturned(1));
  }

  /**
   * Method under test: {@link ReferenceEscapeChecker#isInstanceModified(int)}
   */
  @Test
  void testIsInstanceModified() {
    // Arrange, Act and Assert
    assertFalse((new ReferenceEscapeChecker()).isInstanceModified(1));
  }

  /**
   * Method under test: {@link ReferenceEscapeChecker#isInstanceExternal(int)}
   */
  @Test
  void testIsInstanceExternal() {
    // Arrange, Act and Assert
    assertFalse((new ReferenceEscapeChecker()).isInstanceExternal(1));
  }

  /**
   * Method under test:
   * {@link ReferenceEscapeChecker#visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)}
   */
  @Test
  void testVisitSimpleInstruction() {
    // Arrange
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.isNull()).thenReturn(1);
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTop(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);
    ReferenceEscapeChecker referenceEscapeChecker = new ReferenceEscapeChecker(partialEvaluator, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    SimpleInstruction simpleInstruction = new SimpleInstruction((byte) 'A');
    simpleInstruction.opcode = (byte) -80;

    // Act
    referenceEscapeChecker.visitSimpleInstruction(clazz, method, codeAttribute, 2, simpleInstruction);

    // Assert
    verify(partialEvaluator).getStackBefore(eq(2));
    verify(tracedStack).getTop(eq(0));
    verify(arrayReferenceValue).isNull();
  }

  /**
   * Method under test:
   * {@link ReferenceEscapeChecker#visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)}
   */
  @Test
  void testVisitSimpleInstruction2() {
    // Arrange
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.isNull()).thenReturn(1);
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTop(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);
    ReferenceEscapeChecker referenceEscapeChecker = new ReferenceEscapeChecker(partialEvaluator, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    SimpleInstruction simpleInstruction = new SimpleInstruction((byte) 'A');
    simpleInstruction.opcode = (byte) -65;

    // Act
    referenceEscapeChecker.visitSimpleInstruction(clazz, method, codeAttribute, 2, simpleInstruction);

    // Assert
    verify(partialEvaluator).getStackBefore(eq(2));
    verify(tracedStack).getTop(eq(0));
    verify(arrayReferenceValue).isNull();
  }

  /**
   * Method under test:
   * {@link ReferenceEscapeChecker#visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)}
   */
  @Test
  void testVisitSimpleInstruction3() {
    // Arrange
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.isNull()).thenReturn(1);
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTop(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);
    ReferenceEscapeChecker referenceEscapeChecker = new ReferenceEscapeChecker(partialEvaluator, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    SimpleInstruction simpleInstruction = new SimpleInstruction((byte) 'A');
    simpleInstruction.opcode = (byte) 'O';

    // Act
    referenceEscapeChecker.visitSimpleInstruction(clazz, method, codeAttribute, 2, simpleInstruction);

    // Assert
    verify(partialEvaluator).getStackBefore(eq(2));
    verify(tracedStack).getTop(eq(2));
    verify(arrayReferenceValue).isNull();
  }

  /**
   * Method under test:
   * {@link ReferenceEscapeChecker#visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)}
   */
  @Test
  void testVisitSimpleInstruction4() {
    // Arrange
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.isNull()).thenReturn(1);
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTop(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);
    ReferenceEscapeChecker referenceEscapeChecker = new ReferenceEscapeChecker(partialEvaluator, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    SimpleInstruction simpleInstruction = new SimpleInstruction((byte) 'A');
    simpleInstruction.opcode = (byte) 'S';

    // Act
    referenceEscapeChecker.visitSimpleInstruction(clazz, method, codeAttribute, 2, simpleInstruction);

    // Assert
    verify(partialEvaluator, atLeast(1)).getStackBefore(eq(2));
    verify(tracedStack, atLeast(1)).getTop(anyInt());
    verify(arrayReferenceValue, atLeast(1)).isNull();
  }

  /**
   * Method under test:
   * {@link ReferenceEscapeChecker#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  void testVisitFieldrefConstant() {
    // Arrange
    ReferenceEscapeChecker referenceEscapeChecker = new ReferenceEscapeChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());

    // Act
    referenceEscapeChecker.visitFieldrefConstant(clazz, new FieldrefConstant());

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(0), isA(ConstantVisitor.class));
  }

  /**
   * Method under test:
   * {@link ReferenceEscapeChecker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testVisitAnyMethodrefConstant() {
    // Arrange
    ReferenceEscapeChecker referenceEscapeChecker = new ReferenceEscapeChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getType(anyInt())).thenReturn("Type");

    // Act
    referenceEscapeChecker.visitAnyMethodrefConstant(clazz, new InterfaceMethodrefConstant());

    // Assert
    verify(clazz).getType(eq(0));
  }

  /**
   * Method under test:
   * {@link ReferenceEscapeChecker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testVisitAnyMethodrefConstant2() {
    // Arrange
    ReferenceEscapeChecker referenceEscapeChecker = new ReferenceEscapeChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getType(anyInt())).thenReturn("Type");
    LibraryClass referencedClass = new LibraryClass();

    // Act
    referenceEscapeChecker.visitAnyMethodrefConstant(clazz,
        new InterfaceMethodrefConstant(1, 1, referencedClass, new LibraryMethod(1, "Name", "Descriptor")));

    // Assert
    verify(clazz).getType(eq(1));
  }

  /**
   * Method under test:
   * {@link ReferenceEscapeChecker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testVisitAnyMethodrefConstant3() {
    // Arrange
    ReferenceEscapeChecker referenceEscapeChecker = new ReferenceEscapeChecker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getType(anyInt())).thenReturn("");
    LibraryClass referencedClass = new LibraryClass();

    // Act
    referenceEscapeChecker.visitAnyMethodrefConstant(clazz,
        new InterfaceMethodrefConstant(1, 1, referencedClass, new LibraryMethod(1, "Name", "Descriptor")));

    // Assert
    verify(clazz).getType(eq(1));
  }

  /**
   * Method under test: {@link ReferenceEscapeChecker#ReferenceEscapeChecker()}
   */
  @Test
  void testNewReferenceEscapeChecker() {
    // Arrange and Act
    ReferenceEscapeChecker actualReferenceEscapeChecker = new ReferenceEscapeChecker();

    // Assert
    assertFalse(actualReferenceEscapeChecker.isInstanceEscaping(1));
    assertFalse(actualReferenceEscapeChecker.isInstanceExternal(1));
    assertFalse(actualReferenceEscapeChecker.isInstanceModified(1));
    assertFalse(actualReferenceEscapeChecker.isInstanceReturned(1));
  }

  /**
   * Method under test:
   * {@link ReferenceEscapeChecker#ReferenceEscapeChecker(PartialEvaluator, boolean)}
   */
  @Test
  void testNewReferenceEscapeChecker2() {
    // Arrange and Act
    ReferenceEscapeChecker actualReferenceEscapeChecker = new ReferenceEscapeChecker(new PartialEvaluator(), true);

    // Assert
    assertFalse(actualReferenceEscapeChecker.isInstanceEscaping(1));
    assertFalse(actualReferenceEscapeChecker.isInstanceExternal(1));
    assertFalse(actualReferenceEscapeChecker.isInstanceModified(1));
    assertFalse(actualReferenceEscapeChecker.isInstanceReturned(1));
  }
}
