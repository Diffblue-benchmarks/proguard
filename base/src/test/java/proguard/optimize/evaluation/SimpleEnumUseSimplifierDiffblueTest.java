package proguard.optimize.evaluation;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.StringConstant;
import proguard.classfile.instruction.BranchInstruction;
import proguard.classfile.instruction.SimpleInstruction;
import proguard.classfile.instruction.VariableInstruction;
import proguard.evaluation.PartialEvaluator;
import proguard.evaluation.TracedStack;
import proguard.evaluation.value.ArrayReferenceValue;
import proguard.optimize.info.ClassOptimizationInfo;
import proguard.resources.file.ResourceFile;

class SimpleEnumUseSimplifierDiffblueTest {
  /**
   * Test {@link SimpleEnumUseSimplifier#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   *
   * <ul>
   *   <li>Then calls {@link ClassOptimizationInfo#isSimpleEnum()}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitCodeAttribute(Clazz, Method,
   * CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute); then calls isSimpleEnum()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseSimplifier.visitCodeAttribute(Clazz, Method, CodeAttribute)"
  })
  void testVisitCodeAttribute_thenCallsIsSimpleEnum() {
    // Arrange
    SimpleEnumUseSimplifier simpleEnumUseSimplifier = new SimpleEnumUseSimplifier();

    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.isSimpleEnum()).thenReturn(true);

    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(classOptimizationInfo);
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    simpleEnumUseSimplifier.visitCodeAttribute(clazz, method, new CodeAttribute(1));

    // Assert
    verify(classOptimizationInfo).isSimpleEnum();
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
    verify(partialEvaluator).getStackBefore(2);
    verify(tracedStack).getTop(2);
    verify(arrayReferenceValue).getReferencedClass();
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
    verify(partialEvaluator).isSubroutineStart(2);
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
    verify(partialEvaluator).getStackBefore(2);
    verify(partialEvaluator).isSubroutineStart(2);
    verify(tracedStack).getTop(0);
    verify(arrayReferenceValue).getReferencedClass();
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
        clazz, method, codeAttribute, 2, new BranchInstruction((byte) -90, 1));

    // Assert
    verify(partialEvaluator).getStackBefore(2);
    verify(tracedStack).getTop(0);
    verify(arrayReferenceValue).getReferencedClass();
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
        clazz, method, codeAttribute, 2, new BranchInstruction((byte) -58, 1));

    // Assert
    verify(partialEvaluator).getStackBefore(2);
    verify(tracedStack).getTop(0);
    verify(arrayReferenceValue).getReferencedClass();
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
  void testVisitBranchInstruction3() {
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
        clazz, method, codeAttribute, 2, new BranchInstruction((byte) -57, 1));

    // Assert
    verify(partialEvaluator).getStackBefore(2);
    verify(tracedStack).getTop(0);
    verify(arrayReferenceValue).getReferencedClass();
  }

  /**
   * Test {@link SimpleEnumUseSimplifier#visitBranchInstruction(Clazz, Method, CodeAttribute, int,
   * BranchInstruction)}.
   *
   * <ul>
   *   <li>Then calls {@link PartialEvaluator#getStackBefore(int)}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleEnumUseSimplifier#visitBranchInstruction(Clazz, Method,
   * CodeAttribute, int, BranchInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction); then calls getStackBefore(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SimpleEnumUseSimplifier.visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)"
  })
  void testVisitBranchInstruction_thenCallsGetStackBefore() {
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
    verify(partialEvaluator).getStackBefore(2);
    verify(tracedStack).getTop(0);
    verify(arrayReferenceValue).getReferencedClass();
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

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.setProcessingInfo(classOptimizationInfo);
    StringConstant stringConstant = new StringConstant(1, new ResourceFile("foo.txt", 3L));
    stringConstant.referencedClass = libraryClass;

    // Act
    simpleEnumUseSimplifier.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(classOptimizationInfo).isSimpleEnum();
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

    LibraryClass referencedClass = new LibraryClass();
    referencedClass.setProcessingInfo(classOptimizationInfo);
    ClassConstant classConstant = new ClassConstant(1, referencedClass);

    // Act
    simpleEnumUseSimplifier.visitClassConstant(clazz, classConstant);

    // Assert
    verify(classOptimizationInfo).isSimpleEnum();
  }
}
