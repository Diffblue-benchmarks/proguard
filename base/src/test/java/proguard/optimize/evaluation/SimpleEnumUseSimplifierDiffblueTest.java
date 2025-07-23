package proguard.optimize.evaluation;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Member;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.constant.StringConstant;
import proguard.classfile.instruction.BranchInstruction;
import proguard.classfile.instruction.SimpleInstruction;
import proguard.classfile.instruction.VariableInstruction;
import proguard.evaluation.PartialEvaluator;
import proguard.evaluation.TracedStack;
import proguard.evaluation.value.ArrayReferenceValue;
import proguard.evaluation.value.TopValue;
import proguard.optimize.info.ClassOptimizationInfo;
import proguard.optimize.info.ProgramClassOptimizationInfo;
import proguard.resources.file.ResourceFile;

class SimpleEnumUseSimplifierDiffblueTest {
  /**
   * Test {@link SimpleEnumUseSimplifier#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   *
   * <ul>
   *   <li>Then calls {@link Clazz#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitCodeAttribute(Clazz, Method,
   * CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute); then calls getName()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseSimplifier.visitCodeAttribute(Clazz, Method, CodeAttribute)"
  })
  void testVisitCodeAttribute_thenCallsGetName() {
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
   * Test {@link SimpleEnumUseSimplifier#visitSimpleInstruction(Clazz, Method, CodeAttribute, int,
   * SimpleInstruction)}.
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitSimpleInstruction(Clazz, Method,
   * CodeAttribute, int, SimpleInstruction)}
   */
  @Test
  @DisplayName("Test visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseSimplifier.visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)"
  })
  void testVisitSimpleInstruction() {
    // Arrange
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, -80, 2, -80);
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.getReferencedClass())
        .thenReturn(
            new ProgramClass(
                1,
                3,
                new Constant[] {constant},
                1,
                1,
                1,
                "Feature Name",
                1,
                new ClassOptimizationInfo()));
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTop(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackAfter(anyInt())).thenReturn(tracedStack);
    SimpleEnumUseSimplifier simpleEnumUseSimplifier =
        new SimpleEnumUseSimplifier(partialEvaluator, null);
    LibraryClass clazz = new LibraryClass();
    ProgramMethod method = new ProgramMethod(1, 1, 1, null);

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    simpleEnumUseSimplifier.visitSimpleInstruction(
        clazz, method, codeAttribute, 2, new SimpleInstruction((byte) '2'));

    // Assert
    verify(partialEvaluator).getStackAfter(eq(2));
    verify(tracedStack).getTop(eq(0));
    verify(arrayReferenceValue).getReferencedClass();
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitSimpleInstruction(Clazz, Method, CodeAttribute, int,
   * SimpleInstruction)}.
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitSimpleInstruction(Clazz, Method,
   * CodeAttribute, int, SimpleInstruction)}
   */
  @Test
  @DisplayName("Test visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseSimplifier.visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)"
  })
  void testVisitSimpleInstruction2() {
    // Arrange
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, -80, 2, -80);
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.getReferencedClass())
        .thenReturn(
            new ProgramClass(
                1,
                3,
                new Constant[] {constant},
                1,
                1,
                1,
                "Feature Name",
                1,
                new ProgramClassOptimizationInfo()));
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTop(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackAfter(anyInt())).thenReturn(tracedStack);
    SimpleEnumUseSimplifier simpleEnumUseSimplifier =
        new SimpleEnumUseSimplifier(partialEvaluator, null);
    LibraryClass clazz = new LibraryClass();
    ProgramMethod method = new ProgramMethod(1, 1, 1, null);

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    simpleEnumUseSimplifier.visitSimpleInstruction(
        clazz, method, codeAttribute, 2, new SimpleInstruction((byte) '2'));

    // Assert
    verify(partialEvaluator).getStackAfter(eq(2));
    verify(tracedStack).getTop(eq(0));
    verify(arrayReferenceValue).getReferencedClass();
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitSimpleInstruction(Clazz, Method, CodeAttribute, int,
   * SimpleInstruction)}.
   *
   * <ul>
   *   <li>Given {@link ArrayReferenceValue} {@link ArrayReferenceValue#getReferencedClass()} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitSimpleInstruction(Clazz, Method,
   * CodeAttribute, int, SimpleInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction); given ArrayReferenceValue getReferencedClass() return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseSimplifier.visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)"
  })
  void testVisitSimpleInstruction_givenArrayReferenceValueGetReferencedClassReturnNull() {
    // Arrange
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.getReferencedClass()).thenReturn(null);
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTop(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackAfter(anyInt())).thenReturn(tracedStack);
    SimpleEnumUseSimplifier simpleEnumUseSimplifier =
        new SimpleEnumUseSimplifier(partialEvaluator, null);
    LibraryClass clazz = new LibraryClass();
    ProgramMethod method = new ProgramMethod(1, 1, 1, null);

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    simpleEnumUseSimplifier.visitSimpleInstruction(
        clazz, method, codeAttribute, 2, new SimpleInstruction((byte) '2'));

    // Assert
    verify(partialEvaluator).getStackAfter(eq(2));
    verify(tracedStack).getTop(eq(0));
    verify(arrayReferenceValue).getReferencedClass();
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitSimpleInstruction(Clazz, Method, CodeAttribute, int,
   * SimpleInstruction)}.
   *
   * <ul>
   *   <li>Then calls {@link PartialEvaluator#getStackBefore(int)}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitSimpleInstruction(Clazz, Method,
   * CodeAttribute, int, SimpleInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction); then calls getStackBefore(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseSimplifier.visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)"
  })
  void testVisitSimpleInstruction_thenCallsGetStackBefore() {
    // Arrange
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, -80, 2, -80);
    new ProgramClass(
        1,
        3,
        new Constant[] {constant},
        1,
        1,
        1,
        "Feature Name",
        1,
        mock(ClassOptimizationInfo.class));

    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.getReferencedClass()).thenReturn(null);
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTop(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);
    SimpleEnumUseSimplifier simpleEnumUseSimplifier =
        new SimpleEnumUseSimplifier(partialEvaluator, null);
    LibraryClass clazz = new LibraryClass();
    ProgramMethod method = new ProgramMethod(1, 1, 1, null);

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    simpleEnumUseSimplifier.visitSimpleInstruction(
        clazz, method, codeAttribute, 2, new SimpleInstruction((byte) 'S'));

    // Assert
    verify(partialEvaluator).getStackBefore(eq(2));
    verify(tracedStack).getTop(eq(2));
    verify(arrayReferenceValue).getReferencedClass();
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitSimpleInstruction(Clazz, Method, CodeAttribute, int,
   * SimpleInstruction)}.
   *
   * <ul>
   *   <li>Then calls {@link ClassOptimizationInfo#isSimpleEnum()}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitSimpleInstruction(Clazz, Method,
   * CodeAttribute, int, SimpleInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction); then calls isSimpleEnum()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseSimplifier.visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)"
  })
  void testVisitSimpleInstruction_thenCallsIsSimpleEnum() {
    // Arrange
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, -80, 2, -80);
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isSimpleEnum()).thenReturn(false);
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.getReferencedClass())
        .thenReturn(
            new ProgramClass(
                1,
                3,
                new Constant[] {constant},
                1,
                1,
                1,
                "Feature Name",
                1,
                classOptimizationInfo));
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTop(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackAfter(anyInt())).thenReturn(tracedStack);
    SimpleEnumUseSimplifier simpleEnumUseSimplifier =
        new SimpleEnumUseSimplifier(partialEvaluator, null);
    LibraryClass clazz = new LibraryClass();
    ProgramMethod method = new ProgramMethod(1, 1, 1, null);

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    simpleEnumUseSimplifier.visitSimpleInstruction(
        clazz, method, codeAttribute, 2, new SimpleInstruction((byte) '2'));

    // Assert
    verify(partialEvaluator).getStackAfter(eq(2));
    verify(tracedStack).getTop(eq(0));
    verify(arrayReferenceValue).getReferencedClass();
    verify(classOptimizationInfo).isSimpleEnum();
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitSimpleInstruction(Clazz, Method, CodeAttribute, int,
   * SimpleInstruction)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitSimpleInstruction(Clazz, Method,
   * CodeAttribute, int, SimpleInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction); then throw IllegalArgumentException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseSimplifier.visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)"
  })
  void testVisitSimpleInstruction_thenThrowIllegalArgumentException() {
    // Arrange
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, -80, 2, -80);
    new ProgramClass(
        1,
        3,
        new Constant[] {constant},
        1,
        1,
        1,
        "Feature Name",
        1,
        mock(ClassOptimizationInfo.class));

    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTop(anyInt())).thenReturn(new TopValue());
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);
    SimpleEnumUseSimplifier simpleEnumUseSimplifier =
        new SimpleEnumUseSimplifier(partialEvaluator, null);
    LibraryClass clazz = new LibraryClass();
    ProgramMethod method = new ProgramMethod(1, 1, 1, null);

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            simpleEnumUseSimplifier.visitSimpleInstruction(
                clazz, method, codeAttribute, 2, new SimpleInstruction((byte) 'S')));
    verify(partialEvaluator).getStackBefore(eq(2));
    verify(tracedStack).getTop(eq(2));
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitVariableInstruction(Clazz, Method, CodeAttribute, int,
   * VariableInstruction)}.
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitVariableInstruction(Clazz, Method,
   * CodeAttribute, int, VariableInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitVariableInstruction(Clazz, Method, CodeAttribute, int, VariableInstruction)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseSimplifier.visitVariableInstruction(Clazz, Method, CodeAttribute, int, VariableInstruction)"
  })
  void testVisitVariableInstruction() {
    // Arrange
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.getReferencedClass()).thenReturn(null);
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTop(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackAfter(anyInt())).thenReturn(tracedStack);
    SimpleEnumUseSimplifier simpleEnumUseSimplifier =
        new SimpleEnumUseSimplifier(partialEvaluator, null);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    simpleEnumUseSimplifier.visitVariableInstruction(
        clazz, method, codeAttribute, 2, new VariableInstruction((byte) 25));

    // Assert
    verify(partialEvaluator).getStackAfter(eq(2));
    verify(tracedStack).getTop(eq(0));
    verify(arrayReferenceValue).getReferencedClass();
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitVariableInstruction(Clazz, Method, CodeAttribute, int,
   * VariableInstruction)}.
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitVariableInstruction(Clazz, Method,
   * CodeAttribute, int, VariableInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitVariableInstruction(Clazz, Method, CodeAttribute, int, VariableInstruction)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseSimplifier.visitVariableInstruction(Clazz, Method, CodeAttribute, int, VariableInstruction)"
  })
  void testVisitVariableInstruction2() {
    // Arrange
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 25, 2, 25);
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.getReferencedClass())
        .thenReturn(
            new ProgramClass(
                1,
                3,
                new Constant[] {constant},
                1,
                1,
                1,
                "Feature Name",
                1,
                new ClassOptimizationInfo()));
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTop(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackAfter(anyInt())).thenReturn(tracedStack);
    SimpleEnumUseSimplifier simpleEnumUseSimplifier =
        new SimpleEnumUseSimplifier(partialEvaluator, null);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    simpleEnumUseSimplifier.visitVariableInstruction(
        clazz, method, codeAttribute, 2, new VariableInstruction((byte) 25));

    // Assert
    verify(partialEvaluator).getStackAfter(eq(2));
    verify(tracedStack).getTop(eq(0));
    verify(arrayReferenceValue).getReferencedClass();
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitVariableInstruction(Clazz, Method, CodeAttribute, int,
   * VariableInstruction)}.
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitVariableInstruction(Clazz, Method,
   * CodeAttribute, int, VariableInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitVariableInstruction(Clazz, Method, CodeAttribute, int, VariableInstruction)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseSimplifier.visitVariableInstruction(Clazz, Method, CodeAttribute, int, VariableInstruction)"
  })
  void testVisitVariableInstruction3() {
    // Arrange
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 25, 2, 25);
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.getReferencedClass())
        .thenReturn(
            new ProgramClass(
                1,
                3,
                new Constant[] {constant},
                1,
                1,
                1,
                "Feature Name",
                1,
                new ProgramClassOptimizationInfo()));
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTop(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackAfter(anyInt())).thenReturn(tracedStack);
    SimpleEnumUseSimplifier simpleEnumUseSimplifier =
        new SimpleEnumUseSimplifier(partialEvaluator, null);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    simpleEnumUseSimplifier.visitVariableInstruction(
        clazz, method, codeAttribute, 2, new VariableInstruction((byte) 25));

    // Assert
    verify(partialEvaluator).getStackAfter(eq(2));
    verify(tracedStack).getTop(eq(0));
    verify(arrayReferenceValue).getReferencedClass();
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitVariableInstruction(Clazz, Method, CodeAttribute, int,
   * VariableInstruction)}.
   *
   * <ul>
   *   <li>Given {@link PartialEvaluator} {@link PartialEvaluator#isSubroutineStart(int)} return
   *       {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitVariableInstruction(Clazz, Method,
   * CodeAttribute, int, VariableInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitVariableInstruction(Clazz, Method, CodeAttribute, int, VariableInstruction); given PartialEvaluator isSubroutineStart(int) return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseSimplifier.visitVariableInstruction(Clazz, Method, CodeAttribute, int, VariableInstruction)"
  })
  void testVisitVariableInstruction_givenPartialEvaluatorIsSubroutineStartReturnTrue() {
    // Arrange
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 25, 2, 25);
    new ProgramClass(
        1,
        3,
        new Constant[] {constant},
        1,
        1,
        1,
        "Feature Name",
        1,
        mock(ClassOptimizationInfo.class));

    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.isSubroutineStart(anyInt())).thenReturn(true);
    SimpleEnumUseSimplifier simpleEnumUseSimplifier =
        new SimpleEnumUseSimplifier(partialEvaluator, null);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    simpleEnumUseSimplifier.visitVariableInstruction(
        clazz, method, codeAttribute, 2, new VariableInstruction((byte) ':'));

    // Assert
    verify(partialEvaluator).isSubroutineStart(eq(2));
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitVariableInstruction(Clazz, Method, CodeAttribute, int,
   * VariableInstruction)}.
   *
   * <ul>
   *   <li>Then calls {@link PartialEvaluator#getStackBefore(int)}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitVariableInstruction(Clazz, Method,
   * CodeAttribute, int, VariableInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitVariableInstruction(Clazz, Method, CodeAttribute, int, VariableInstruction); then calls getStackBefore(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseSimplifier.visitVariableInstruction(Clazz, Method, CodeAttribute, int, VariableInstruction)"
  })
  void testVisitVariableInstruction_thenCallsGetStackBefore() {
    // Arrange
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 25, 2, 25);
    new ProgramClass(
        1,
        3,
        new Constant[] {constant},
        1,
        1,
        1,
        "Feature Name",
        1,
        mock(ClassOptimizationInfo.class));

    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.getReferencedClass()).thenReturn(null);
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTop(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.isSubroutineStart(anyInt())).thenReturn(false);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);
    SimpleEnumUseSimplifier simpleEnumUseSimplifier =
        new SimpleEnumUseSimplifier(partialEvaluator, null);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    simpleEnumUseSimplifier.visitVariableInstruction(
        clazz, method, codeAttribute, 2, new VariableInstruction((byte) ':'));

    // Assert
    verify(partialEvaluator).getStackBefore(eq(2));
    verify(partialEvaluator).isSubroutineStart(eq(2));
    verify(tracedStack).getTop(eq(0));
    verify(arrayReferenceValue).getReferencedClass();
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitVariableInstruction(Clazz, Method, CodeAttribute, int,
   * VariableInstruction)}.
   *
   * <ul>
   *   <li>Then calls {@link ClassOptimizationInfo#isSimpleEnum()}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitVariableInstruction(Clazz, Method,
   * CodeAttribute, int, VariableInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitVariableInstruction(Clazz, Method, CodeAttribute, int, VariableInstruction); then calls isSimpleEnum()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseSimplifier.visitVariableInstruction(Clazz, Method, CodeAttribute, int, VariableInstruction)"
  })
  void testVisitVariableInstruction_thenCallsIsSimpleEnum() {
    // Arrange
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 25, 2, 25);
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isSimpleEnum()).thenReturn(false);
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.getReferencedClass())
        .thenReturn(
            new ProgramClass(
                1,
                3,
                new Constant[] {constant},
                1,
                1,
                1,
                "Feature Name",
                1,
                classOptimizationInfo));
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTop(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackAfter(anyInt())).thenReturn(tracedStack);
    SimpleEnumUseSimplifier simpleEnumUseSimplifier =
        new SimpleEnumUseSimplifier(partialEvaluator, null);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    simpleEnumUseSimplifier.visitVariableInstruction(
        clazz, method, codeAttribute, 2, new VariableInstruction((byte) 25));

    // Assert
    verify(partialEvaluator).getStackAfter(eq(2));
    verify(tracedStack).getTop(eq(0));
    verify(arrayReferenceValue).getReferencedClass();
    verify(classOptimizationInfo).isSimpleEnum();
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitVariableInstruction(Clazz, Method, CodeAttribute, int,
   * VariableInstruction)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitVariableInstruction(Clazz, Method,
   * CodeAttribute, int, VariableInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitVariableInstruction(Clazz, Method, CodeAttribute, int, VariableInstruction); then throw IllegalArgumentException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseSimplifier.visitVariableInstruction(Clazz, Method, CodeAttribute, int, VariableInstruction)"
  })
  void testVisitVariableInstruction_thenThrowIllegalArgumentException() {
    // Arrange
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, 25, 2, 25);
    new ProgramClass(
        1,
        3,
        new Constant[] {constant},
        1,
        1,
        1,
        "Feature Name",
        1,
        mock(ClassOptimizationInfo.class));

    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTop(anyInt())).thenReturn(new TopValue());
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.isSubroutineStart(anyInt())).thenReturn(false);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);
    SimpleEnumUseSimplifier simpleEnumUseSimplifier =
        new SimpleEnumUseSimplifier(partialEvaluator, null);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            simpleEnumUseSimplifier.visitVariableInstruction(
                clazz, method, codeAttribute, 2, new VariableInstruction((byte) ':')));
    verify(partialEvaluator).getStackBefore(eq(2));
    verify(partialEvaluator).isSubroutineStart(eq(2));
    verify(tracedStack).getTop(eq(0));
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitBranchInstruction(Clazz, Method, CodeAttribute, int,
   * BranchInstruction)}.
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitBranchInstruction(Clazz, Method,
   * CodeAttribute, int, BranchInstruction)}
   */
  @Test
  @DisplayName("Test visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseSimplifier.visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)"
  })
  void testVisitBranchInstruction() {
    // Arrange
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, -91, 2, -91);
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.getReferencedClass())
        .thenReturn(
            new ProgramClass(
                1,
                3,
                new Constant[] {constant},
                1,
                1,
                1,
                "Feature Name",
                1,
                new ClassOptimizationInfo()));
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTop(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);
    SimpleEnumUseSimplifier simpleEnumUseSimplifier =
        new SimpleEnumUseSimplifier(partialEvaluator, null);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    simpleEnumUseSimplifier.visitBranchInstruction(
        clazz, method, codeAttribute, 2, new BranchInstruction((byte) -91, 1));

    // Assert
    verify(partialEvaluator).getStackBefore(eq(2));
    verify(tracedStack).getTop(eq(0));
    verify(arrayReferenceValue).getReferencedClass();
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitBranchInstruction(Clazz, Method, CodeAttribute, int,
   * BranchInstruction)}.
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitBranchInstruction(Clazz, Method,
   * CodeAttribute, int, BranchInstruction)}
   */
  @Test
  @DisplayName("Test visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseSimplifier.visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)"
  })
  void testVisitBranchInstruction2() {
    // Arrange
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, -91, 2, -91);
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.getReferencedClass())
        .thenReturn(
            new ProgramClass(
                1,
                3,
                new Constant[] {constant},
                1,
                1,
                1,
                "Feature Name",
                1,
                new ProgramClassOptimizationInfo()));
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTop(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);
    SimpleEnumUseSimplifier simpleEnumUseSimplifier =
        new SimpleEnumUseSimplifier(partialEvaluator, null);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    simpleEnumUseSimplifier.visitBranchInstruction(
        clazz, method, codeAttribute, 2, new BranchInstruction((byte) -91, 1));

    // Assert
    verify(partialEvaluator).getStackBefore(eq(2));
    verify(tracedStack).getTop(eq(0));
    verify(arrayReferenceValue).getReferencedClass();
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitBranchInstruction(Clazz, Method, CodeAttribute, int,
   * BranchInstruction)}.
   *
   * <ul>
   *   <li>Given {@link ArrayReferenceValue} {@link ArrayReferenceValue#getReferencedClass()} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitBranchInstruction(Clazz, Method,
   * CodeAttribute, int, BranchInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction); given ArrayReferenceValue getReferencedClass() return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseSimplifier.visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)"
  })
  void testVisitBranchInstruction_givenArrayReferenceValueGetReferencedClassReturnNull() {
    // Arrange
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.getReferencedClass()).thenReturn(null);
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTop(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);
    SimpleEnumUseSimplifier simpleEnumUseSimplifier =
        new SimpleEnumUseSimplifier(partialEvaluator, null);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    simpleEnumUseSimplifier.visitBranchInstruction(
        clazz, method, codeAttribute, 2, new BranchInstruction((byte) -91, 1));

    // Assert
    verify(partialEvaluator).getStackBefore(eq(2));
    verify(tracedStack).getTop(eq(0));
    verify(arrayReferenceValue).getReferencedClass();
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitBranchInstruction(Clazz, Method, CodeAttribute, int,
   * BranchInstruction)}.
   *
   * <ul>
   *   <li>Then calls {@link ClassOptimizationInfo#isSimpleEnum()}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitBranchInstruction(Clazz, Method,
   * CodeAttribute, int, BranchInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction); then calls isSimpleEnum()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseSimplifier.visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)"
  })
  void testVisitBranchInstruction_thenCallsIsSimpleEnum() {
    // Arrange
    Constant constant = mock(Constant.class);
    doNothing().when(constant).addProcessingFlags((int[]) Mockito.any());
    constant.addProcessingFlags(2, -91, 2, -91);
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isSimpleEnum()).thenReturn(false);
    ArrayReferenceValue arrayReferenceValue = mock(ArrayReferenceValue.class);
    when(arrayReferenceValue.getReferencedClass())
        .thenReturn(
            new ProgramClass(
                1,
                3,
                new Constant[] {constant},
                1,
                1,
                1,
                "Feature Name",
                1,
                classOptimizationInfo));
    TracedStack tracedStack = mock(TracedStack.class);
    when(tracedStack.getTop(anyInt())).thenReturn(arrayReferenceValue);
    PartialEvaluator partialEvaluator = mock(PartialEvaluator.class);
    when(partialEvaluator.getStackBefore(anyInt())).thenReturn(tracedStack);
    SimpleEnumUseSimplifier simpleEnumUseSimplifier =
        new SimpleEnumUseSimplifier(partialEvaluator, null);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    simpleEnumUseSimplifier.visitBranchInstruction(
        clazz, method, codeAttribute, 2, new BranchInstruction((byte) -91, 1));

    // Assert
    verify(partialEvaluator).getStackBefore(eq(2));
    verify(tracedStack).getTop(eq(0));
    verify(arrayReferenceValue).getReferencedClass();
    verify(classOptimizationInfo).isSimpleEnum();
    verify(constant).addProcessingFlags((int[]) Mockito.any());
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitStringConstant(Clazz, StringConstant)}.
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitStringConstant(Clazz,
   * StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpleEnumUseSimplifier.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant() {
    // Arrange
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    StringConstant stringConstant = new StringConstant(1, new ResourceFile("foo.txt", 3L));

    stringConstant.referencedClass = libraryClass;

    // Act
    simpleEnumUseSimplifier.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitStringConstant(Clazz, StringConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link ClassOptimizationInfo#isSimpleEnum()}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitStringConstant(Clazz,
   * StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant); then calls isSimpleEnum()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpleEnumUseSimplifier.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant_thenCallsIsSimpleEnum() {
    // Arrange
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isSimpleEnum()).thenReturn(true);
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    StringConstant stringConstant = new StringConstant(1, new ResourceFile("foo.txt", 3L));

    stringConstant.referencedClass = libraryClass;

    // Act
    simpleEnumUseSimplifier.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(classOptimizationInfo).isSimpleEnum();
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName(
      "Test visitClassConstant(Clazz, ClassConstant); given ClassOptimizationInfo (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpleEnumUseSimplifier.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_givenClassOptimizationInfo() {
    // Arrange
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());

    // Act
    simpleEnumUseSimplifier.visitClassConstant(clazz, new ClassConstant(1, referencedClass));

    // Assert
    verify(referencedClass).getProcessingInfo();
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link ClassOptimizationInfo#isSimpleEnum()}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then calls isSimpleEnum()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpleEnumUseSimplifier.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_thenCallsIsSimpleEnum() {
    // Arrange
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isSimpleEnum()).thenReturn(true);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act
    simpleEnumUseSimplifier.visitClassConstant(clazz, new ClassConstant(1, referencedClass));

    // Assert
    verify(classOptimizationInfo).isSimpleEnum();
    verify(referencedClass).getProcessingInfo();
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitParameter(Clazz, Member, int, int, int, int, String,
   * Clazz)}.
   *
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitParameter(Clazz, Member, int, int,
   * int, int, String, Clazz)}
   */
  @Test
  @DisplayName(
      "Test visitParameter(Clazz, Member, int, int, int, int, String, Clazz); given ClassOptimizationInfo (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseSimplifier.visitParameter(Clazz, Member, int, int, int, int, String, Clazz)"
  })
  void testVisitParameter_givenClassOptimizationInfo() {
    // Arrange
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    LibraryField member = new LibraryField(1, "Name", "Descriptor");

    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());

    // Act
    simpleEnumUseSimplifier.visitParameter(
        clazz, member, 1, 3, 1, 3, "Parameter Type", referencedClass);

    // Assert
    verify(referencedClass).getProcessingInfo();
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitParameter(Clazz, Member, int, int, int, int, String,
   * Clazz)}.
   *
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitParameter(Clazz, Member, int, int,
   * int, int, String, Clazz)}
   */
  @Test
  @DisplayName(
      "Test visitParameter(Clazz, Member, int, int, int, int, String, Clazz); given ProgramClassOptimizationInfo (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseSimplifier.visitParameter(Clazz, Member, int, int, int, int, String, Clazz)"
  })
  void testVisitParameter_givenProgramClassOptimizationInfo() {
    // Arrange
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    LibraryField member = new LibraryField(1, "Name", "Descriptor");

    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());

    // Act
    simpleEnumUseSimplifier.visitParameter(
        clazz, member, 1, 3, 1, 3, "Parameter Type", referencedClass);

    // Assert
    verify(referencedClass).getProcessingInfo();
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitParameter(Clazz, Member, int, int, int, int, String,
   * Clazz)}.
   *
   * <ul>
   *   <li>Then calls {@link ClassOptimizationInfo#isSimpleEnum()}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitParameter(Clazz, Member, int, int,
   * int, int, String, Clazz)}
   */
  @Test
  @DisplayName(
      "Test visitParameter(Clazz, Member, int, int, int, int, String, Clazz); then calls isSimpleEnum()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseSimplifier.visitParameter(Clazz, Member, int, int, int, int, String, Clazz)"
  })
  void testVisitParameter_thenCallsIsSimpleEnum() {
    // Arrange
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier();
    LibraryClass clazz = new LibraryClass();
    LibraryField member = new LibraryField(1, "Name", "Descriptor");

    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isSimpleEnum()).thenReturn(false);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act
    simpleEnumUseSimplifier.visitParameter(
        clazz, member, 1, 3, 1, 3, "Parameter Type", referencedClass);

    // Assert
    verify(classOptimizationInfo).isSimpleEnum();
    verify(referencedClass).getProcessingInfo();
  }
}
