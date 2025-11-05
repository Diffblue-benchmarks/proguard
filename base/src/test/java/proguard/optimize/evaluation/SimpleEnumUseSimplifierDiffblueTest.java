package proguard.optimize.evaluation;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Member;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.StringConstant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.classfile.instruction.ConstantInstruction;
import proguard.evaluation.PartialEvaluator;
import proguard.evaluation.TracedStack;
import proguard.evaluation.value.InstructionOffsetValue;
import proguard.evaluation.value.TopValue;
import proguard.evaluation.value.UnknownReferenceValue;
import proguard.evaluation.value.Value;
import proguard.optimize.DuplicateInitializerInvocationFixer;
import proguard.optimize.info.ClassOptimizationInfo;
import proguard.optimize.info.ProgramClassOptimizationInfo;

class SimpleEnumUseSimplifierDiffblueTest {
  /**
   * Method under test:
   * {@link SimpleEnumUseSimplifier#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute() {
    // Arrange
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isSimpleEnum()).thenReturn(true);
    Clazz clazz = mock(Clazz.class);
    when(clazz.getProcessingInfo()).thenReturn(classOptimizationInfo);
    when(clazz.getName()).thenReturn("Name");
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    simpleEnumUseSimplifier.visitCodeAttribute(clazz, method, new CodeAttribute(1));

    // Assert
    verify(clazz).getName();
    verify(classOptimizationInfo).isSimpleEnum();
    verify(clazz).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseSimplifier#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  void testVisitConstantInstruction() {
    // Arrange
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    simpleEnumUseSimplifier.visitConstantInstruction(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) -67, 1));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseSimplifier#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant() {
    // Arrange
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    libraryClass.setProcessingInfo(new ClassOptimizationInfo());
    StringConstant stringConstant = new StringConstant();
    stringConstant.referencedClass = libraryClass;

    // Act
    simpleEnumUseSimplifier.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(libraryClass).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseSimplifier#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant2() {
    // Arrange
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isSimpleEnum()).thenReturn(true);
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    libraryClass.setProcessingInfo(new ClassOptimizationInfo());
    StringConstant stringConstant = new StringConstant();
    stringConstant.referencedClass = libraryClass;

    // Act
    simpleEnumUseSimplifier.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(classOptimizationInfo).isSimpleEnum();
    verify(libraryClass).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseSimplifier#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant() {
    // Arrange
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    libraryClass.setProcessingInfo(new ClassOptimizationInfo());
    ClassConstant classConstant = new ClassConstant();
    classConstant.referencedClass = libraryClass;

    // Act
    simpleEnumUseSimplifier.visitClassConstant(clazz, classConstant);

    // Assert
    verify(libraryClass).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseSimplifier#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  void testVisitClassConstant2() {
    // Arrange
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isSimpleEnum()).thenReturn(true);
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    doNothing().when(libraryClass).setProcessingInfo(Mockito.<Object>any());
    libraryClass.setProcessingInfo(new ClassOptimizationInfo());
    ClassConstant classConstant = new ClassConstant();
    classConstant.referencedClass = libraryClass;

    // Act
    simpleEnumUseSimplifier.visitClassConstant(clazz, classConstant);

    // Assert
    verify(classOptimizationInfo).isSimpleEnum();
    verify(libraryClass).getProcessingInfo();
    verify(libraryClass).setProcessingInfo(isA(Object.class));
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseSimplifier#visitParameter(Clazz, Member, int, int, int, int, String, Clazz)}
   */
  @Test
  void testVisitParameter() {
    // Arrange
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    LibraryField member = new LibraryField(1, "Name", "Descriptor");

    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());

    // Act
    simpleEnumUseSimplifier.visitParameter(clazz, member, 1, 3, 1, 3, "Parameter Type", referencedClass);

    // Assert that nothing has changed
    verify(referencedClass).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseSimplifier#visitParameter(Clazz, Member, int, int, int, int, String, Clazz)}
   */
  @Test
  void testVisitParameter2() {
    // Arrange
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    LibraryField member = new LibraryField(1, "Name", "Descriptor");

    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());

    // Act
    simpleEnumUseSimplifier.visitParameter(clazz, member, 1, 3, 1, 3, "Parameter Type", referencedClass);

    // Assert that nothing has changed
    verify(referencedClass).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseSimplifier#visitParameter(Clazz, Member, int, int, int, int, String, Clazz)}
   */
  @Test
  void testVisitParameter3() {
    // Arrange
    Value value = mock(Value.class);
    when(value.instructionOffsetValue()).thenReturn(new InstructionOffsetValue(42));
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTopActualProducerValue(anyInt())).thenReturn(value);
    TracedStack tracedStack2 = mock(TracedStack.class);
    when(tracedStack2.getTop(anyInt())).thenReturn(new TopValue());
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackAfter(anyInt())).thenReturn(tracedStack2);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier(partialEvaluator,
        new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = new LibraryClass();
    LibraryField member = new LibraryField(1, "Name", "Descriptor");

    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isSimpleEnum()).thenReturn(true);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act and Assert
    assertThrows(IllegalArgumentException.class,
        () -> simpleEnumUseSimplifier.visitParameter(clazz, member, 1, 3, 1, 3, "Parameter Type", referencedClass));
    verify(partialEvaluator).getStackAfter(eq(42));
    verify(partialEvaluator).getStackBefore(eq(0));
    verify(tracedStack2).getTop(eq(0));
    verify(tracedStack).getTopActualProducerValue(eq(1));
    verify(value).instructionOffsetValue();
    verify(classOptimizationInfo).isSimpleEnum();
    verify(referencedClass).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseSimplifier#visitParameter(Clazz, Member, int, int, int, int, String, Clazz)}
   */
  @Test
  void testVisitParameter4() {
    // Arrange
    Value value = mock(Value.class);
    when(value.instructionOffsetValue()).thenReturn(new InstructionOffsetValue(42));
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTopActualProducerValue(anyInt())).thenReturn(value);
    Value value2 = mock(Value.class);
    when(value2.referenceValue()).thenReturn(new UnknownReferenceValue());
    TracedStack tracedStack2 = mock(TracedStack.class);
    when(tracedStack2.getTop(anyInt())).thenReturn(value2);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackAfter(anyInt())).thenReturn(tracedStack2);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier(partialEvaluator,
        new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = new LibraryClass();
    LibraryField member = new LibraryField(1, "Name", "Descriptor");

    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isSimpleEnum()).thenReturn(true);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act
    simpleEnumUseSimplifier.visitParameter(clazz, member, 1, 3, 1, 3, "Parameter Type", referencedClass);

    // Assert
    verify(partialEvaluator).getStackAfter(eq(42));
    verify(partialEvaluator).getStackBefore(eq(0));
    verify(tracedStack2).getTop(eq(0));
    verify(tracedStack).getTopActualProducerValue(eq(1));
    verify(value).instructionOffsetValue();
    verify(value2).referenceValue();
    verify(classOptimizationInfo).isSimpleEnum();
    verify(referencedClass).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link SimpleEnumUseSimplifier#visitParameter(Clazz, Member, int, int, int, int, String, Clazz)}
   */
  @Test
  void testVisitParameter5() {
    // Arrange
    InstructionOffsetValue instructionOffsetValue = mock(InstructionOffsetValue.class);
    when(instructionOffsetValue.instructionOffset(anyInt())).thenReturn(-1);
    when(instructionOffsetValue.instructionOffsetCount()).thenReturn(3);
    Value value = mock(Value.class);
    when(value.instructionOffsetValue()).thenReturn(instructionOffsetValue);
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTopActualProducerValue(anyInt())).thenReturn(value);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier(partialEvaluator,
        new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = new LibraryClass();
    LibraryField member = new LibraryField(1, "Name", "Descriptor");

    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isSimpleEnum()).thenReturn(true);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act
    simpleEnumUseSimplifier.visitParameter(clazz, member, 1, 3, 1, 3, "Parameter Type", referencedClass);

    // Assert
    verify(partialEvaluator).getStackBefore(eq(0));
    verify(tracedStack).getTopActualProducerValue(eq(1));
    verify(instructionOffsetValue, atLeast(1)).instructionOffset(anyInt());
    verify(instructionOffsetValue, atLeast(1)).instructionOffsetCount();
    verify(value).instructionOffsetValue();
    verify(classOptimizationInfo).isSimpleEnum();
    verify(referencedClass).getProcessingInfo();
  }
}
