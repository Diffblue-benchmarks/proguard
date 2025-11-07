package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Stack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.RefConstant;
import proguard.classfile.instruction.BranchInstruction;
import proguard.classfile.instruction.Instruction;
import proguard.classfile.instruction.VariableInstruction;
import proguard.classfile.util.BranchTargetFinder;
import proguard.evaluation.BasicBranchUnit;
import proguard.evaluation.PartialEvaluator;
import proguard.evaluation.PartialEvaluator.Builder;
import proguard.evaluation.ParticularReferenceValueFactory;
import proguard.evaluation.util.jsonprinter.JsonPrinter;
import proguard.optimize.DuplicateInitializerInvocationFixer;
import proguard.optimize.evaluation.LoadingInvocationUnit;

class TypeArgumentFinderDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link TypeArgumentFinder#TypeArgumentFinder(ClassPool, ClassPool, PartialEvaluator)}
   *   <li>{@link TypeArgumentFinder#visitAnyConstant(Clazz, Constant)}
   *   <li>{@link TypeArgumentFinder#visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TypeArgumentFinder.<init>(ClassPool, ClassPool, PartialEvaluator)",
      "void TypeArgumentFinder.visitAnyConstant(Clazz, Constant)",
      "void TypeArgumentFinder.visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)"})
  void testGettersAndSetters() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    Builder createResult = Builder.create();
    Builder setBranchTargetFinderResult = createResult.setBranchTargetFinder(new BranchTargetFinder());
    Builder setBranchUnitResult = setBranchTargetFinderResult.setBranchUnit(new BasicBranchUnit());
    Builder setEvaluateAllCodeResult = setBranchUnitResult.setCallingInstructionBlockStack(new Stack<>())
        .setEvaluateAllCode(true);
    Builder setExtraInstructionVisitorResult = setEvaluateAllCodeResult
        .setExtraInstructionVisitor(new DuplicateInitializerInvocationFixer());
    Builder setPrettyPrintingResult = setExtraInstructionVisitorResult
        .setInvocationUnit(new LoadingInvocationUnit(new ParticularReferenceValueFactory()))
        .setPrettyPrinting(1);
    Builder stopAnalysisAfterNEvaluationsResult = setPrettyPrintingResult.setStateTracker(new JsonPrinter())
        .stopAnalysisAfterNEvaluations(42);
    PartialEvaluator partialEvaluator = stopAnalysisAfterNEvaluationsResult
        .setValueFactory(new ParticularReferenceValueFactory())
        .build();

    // Act
    TypeArgumentFinder actualTypeArgumentFinder = new TypeArgumentFinder(programClassPool, libraryClassPool,
        partialEvaluator);
    LibraryClass clazz = new LibraryClass();
    actualTypeArgumentFinder.visitAnyConstant(clazz, new ClassConstant());
    LibraryClass clazz2 = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    actualTypeArgumentFinder.visitAnyInstruction(clazz2, method, codeAttribute, 2,
        new BranchInstruction((byte) 'A', 1));

    // Assert
    assertNull(actualTypeArgumentFinder.typeArgumentClasses);
  }

  /**
   * Test {@link TypeArgumentFinder#visitVariableInstruction(Clazz, Method, CodeAttribute, int, VariableInstruction)}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>Then calls {@link VariableInstruction#canonicalOpcode()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TypeArgumentFinder#visitVariableInstruction(Clazz, Method, CodeAttribute, int, VariableInstruction)}
   */
  @Test
  @DisplayName("Test visitVariableInstruction(Clazz, Method, CodeAttribute, int, VariableInstruction); given 'A'; then calls canonicalOpcode()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void TypeArgumentFinder.visitVariableInstruction(Clazz, Method, CodeAttribute, int, VariableInstruction)"})
  void testVisitVariableInstruction_givenA_thenCallsCanonicalOpcode() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    Builder createResult = Builder.create();
    Builder setBranchTargetFinderResult = createResult.setBranchTargetFinder(new BranchTargetFinder());
    Builder setBranchUnitResult = setBranchTargetFinderResult.setBranchUnit(new BasicBranchUnit());
    Builder setEvaluateAllCodeResult = setBranchUnitResult.setCallingInstructionBlockStack(new Stack<>())
        .setEvaluateAllCode(true);
    Builder setExtraInstructionVisitorResult = setEvaluateAllCodeResult
        .setExtraInstructionVisitor(new DuplicateInitializerInvocationFixer());
    Builder setPrettyPrintingResult = setExtraInstructionVisitorResult
        .setInvocationUnit(new LoadingInvocationUnit(new ParticularReferenceValueFactory()))
        .setPrettyPrinting(1);
    Builder stopAnalysisAfterNEvaluationsResult = setPrettyPrintingResult.setStateTracker(new JsonPrinter())
        .stopAnalysisAfterNEvaluations(42);
    PartialEvaluator partialEvaluator = stopAnalysisAfterNEvaluationsResult
        .setValueFactory(new ParticularReferenceValueFactory())
        .build();
    TypeArgumentFinder typeArgumentFinder = new TypeArgumentFinder(programClassPool, libraryClassPool,
        partialEvaluator);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    VariableInstruction variableInstruction = mock(VariableInstruction.class);
    when(variableInstruction.canonicalOpcode()).thenReturn((byte) 'A');

    // Act
    typeArgumentFinder.visitVariableInstruction(clazz, method, codeAttribute, 2, variableInstruction);

    // Assert
    verify(variableInstruction).canonicalOpcode();
  }

  /**
   * Test {@link TypeArgumentFinder#visitAnyRefConstant(Clazz, RefConstant)}.
   * <ul>
   *   <li>Given {@code Class Name}.</li>
   *   <li>When {@link LibraryClass}.</li>
   *   <li>Then calls {@link RefConstant#getClassName(Clazz)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TypeArgumentFinder#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyRefConstant(Clazz, RefConstant); given 'Class Name'; when LibraryClass; then calls getClassName(Clazz)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TypeArgumentFinder.visitAnyRefConstant(Clazz, RefConstant)"})
  void testVisitAnyRefConstant_givenClassName_whenLibraryClass_thenCallsGetClassName() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    Builder createResult = Builder.create();
    Builder setBranchTargetFinderResult = createResult.setBranchTargetFinder(new BranchTargetFinder());
    Builder setBranchUnitResult = setBranchTargetFinderResult.setBranchUnit(new BasicBranchUnit());
    Builder setEvaluateAllCodeResult = setBranchUnitResult.setCallingInstructionBlockStack(new Stack<>())
        .setEvaluateAllCode(true);
    Builder setExtraInstructionVisitorResult = setEvaluateAllCodeResult
        .setExtraInstructionVisitor(new DuplicateInitializerInvocationFixer());
    Builder setPrettyPrintingResult = setExtraInstructionVisitorResult
        .setInvocationUnit(new LoadingInvocationUnit(new ParticularReferenceValueFactory()))
        .setPrettyPrinting(1);
    Builder stopAnalysisAfterNEvaluationsResult = setPrettyPrintingResult.setStateTracker(new JsonPrinter())
        .stopAnalysisAfterNEvaluations(42);
    PartialEvaluator partialEvaluator = stopAnalysisAfterNEvaluationsResult
        .setValueFactory(new ParticularReferenceValueFactory())
        .build();
    TypeArgumentFinder typeArgumentFinder = new TypeArgumentFinder(programClassPool, libraryClassPool,
        partialEvaluator);
    LibraryClass clazz = mock(LibraryClass.class);
    FieldrefConstant refConstant = mock(FieldrefConstant.class);
    when(refConstant.getClassName(Mockito.<Clazz>any())).thenReturn("Class Name");

    // Act
    typeArgumentFinder.visitAnyRefConstant(clazz, refConstant);

    // Assert
    verify(refConstant).getClassName(isA(Clazz.class));
    assertArrayEquals(new String[]{"Class Name"}, typeArgumentFinder.typeArgumentClasses);
  }

  /**
   * Test {@link TypeArgumentFinder#visitAnyRefConstant(Clazz, RefConstant)}.
   * <ul>
   *   <li>Then calls {@link LibraryClass#getClassName(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link TypeArgumentFinder#visitAnyRefConstant(Clazz, RefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyRefConstant(Clazz, RefConstant); then calls getClassName(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TypeArgumentFinder.visitAnyRefConstant(Clazz, RefConstant)"})
  void testVisitAnyRefConstant_thenCallsGetClassName() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    Builder createResult = Builder.create();
    Builder setBranchTargetFinderResult = createResult.setBranchTargetFinder(new BranchTargetFinder());
    Builder setBranchUnitResult = setBranchTargetFinderResult.setBranchUnit(new BasicBranchUnit());
    Builder setEvaluateAllCodeResult = setBranchUnitResult.setCallingInstructionBlockStack(new Stack<>())
        .setEvaluateAllCode(true);
    Builder setExtraInstructionVisitorResult = setEvaluateAllCodeResult
        .setExtraInstructionVisitor(new DuplicateInitializerInvocationFixer());
    Builder setPrettyPrintingResult = setExtraInstructionVisitorResult
        .setInvocationUnit(new LoadingInvocationUnit(new ParticularReferenceValueFactory()))
        .setPrettyPrinting(1);
    Builder stopAnalysisAfterNEvaluationsResult = setPrettyPrintingResult.setStateTracker(new JsonPrinter())
        .stopAnalysisAfterNEvaluations(42);
    PartialEvaluator partialEvaluator = stopAnalysisAfterNEvaluationsResult
        .setValueFactory(new ParticularReferenceValueFactory())
        .build();
    TypeArgumentFinder typeArgumentFinder = new TypeArgumentFinder(programClassPool, libraryClassPool,
        partialEvaluator);
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getClassName(anyInt())).thenReturn("Class Name");

    // Act
    typeArgumentFinder.visitAnyRefConstant(clazz, new FieldrefConstant());

    // Assert
    verify(clazz).getClassName(eq(0));
    assertArrayEquals(new String[]{"Class Name"}, typeArgumentFinder.typeArgumentClasses);
  }

  /**
   * Test {@link TypeArgumentFinder#visitClassConstant(Clazz, ClassConstant)}.
   * <p>
   * Method under test: {@link TypeArgumentFinder#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TypeArgumentFinder.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    Builder createResult = Builder.create();
    Builder setBranchTargetFinderResult = createResult.setBranchTargetFinder(new BranchTargetFinder());
    Builder setBranchUnitResult = setBranchTargetFinderResult.setBranchUnit(new BasicBranchUnit());
    Builder setEvaluateAllCodeResult = setBranchUnitResult.setCallingInstructionBlockStack(new Stack<>())
        .setEvaluateAllCode(true);
    Builder setExtraInstructionVisitorResult = setEvaluateAllCodeResult
        .setExtraInstructionVisitor(new DuplicateInitializerInvocationFixer());
    Builder setPrettyPrintingResult = setExtraInstructionVisitorResult
        .setInvocationUnit(new LoadingInvocationUnit(new ParticularReferenceValueFactory()))
        .setPrettyPrinting(1);
    Builder stopAnalysisAfterNEvaluationsResult = setPrettyPrintingResult.setStateTracker(new JsonPrinter())
        .stopAnalysisAfterNEvaluations(42);
    PartialEvaluator partialEvaluator = stopAnalysisAfterNEvaluationsResult
        .setValueFactory(new ParticularReferenceValueFactory())
        .build();
    TypeArgumentFinder typeArgumentFinder = new TypeArgumentFinder(programClassPool, libraryClassPool,
        partialEvaluator);
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");

    // Act
    typeArgumentFinder.visitClassConstant(clazz, new ClassConstant());

    // Assert
    verify(clazz).getString(eq(0));
    assertArrayEquals(new String[]{"String"}, typeArgumentFinder.typeArgumentClasses);
  }

  /**
   * Test {@link TypeArgumentFinder#visitClassConstant(Clazz, ClassConstant)}.
   * <p>
   * Method under test: {@link TypeArgumentFinder#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void TypeArgumentFinder.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant2() {
    // Arrange
    ClassPool programClassPool = new ClassPool();
    ClassPool libraryClassPool = new ClassPool();
    Builder createResult = Builder.create();
    Builder setBranchTargetFinderResult = createResult.setBranchTargetFinder(new BranchTargetFinder());
    Builder setBranchUnitResult = setBranchTargetFinderResult.setBranchUnit(new BasicBranchUnit());
    Builder setEvaluateAllCodeResult = setBranchUnitResult.setCallingInstructionBlockStack(new Stack<>())
        .setEvaluateAllCode(true);
    Builder setExtraInstructionVisitorResult = setEvaluateAllCodeResult
        .setExtraInstructionVisitor(new DuplicateInitializerInvocationFixer());
    Builder setPrettyPrintingResult = setExtraInstructionVisitorResult
        .setInvocationUnit(new LoadingInvocationUnit(new ParticularReferenceValueFactory()))
        .setPrettyPrinting(1);
    Builder stopAnalysisAfterNEvaluationsResult = setPrettyPrintingResult.setStateTracker(new JsonPrinter())
        .stopAnalysisAfterNEvaluations(42);
    PartialEvaluator partialEvaluator = stopAnalysisAfterNEvaluationsResult
        .setValueFactory(new ParticularReferenceValueFactory())
        .build();
    TypeArgumentFinder typeArgumentFinder = new TypeArgumentFinder(programClassPool, libraryClassPool,
        partialEvaluator);
    LibraryClass clazz = mock(LibraryClass.class);
    ClassConstant classConstant = mock(ClassConstant.class);
    when(classConstant.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    typeArgumentFinder.visitClassConstant(clazz, classConstant);

    // Assert
    verify(classConstant).getName(isA(Clazz.class));
    assertArrayEquals(new String[]{"Name"}, typeArgumentFinder.typeArgumentClasses);
  }
}
