package proguard.optimize.peephole;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
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
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;
import proguard.classfile.editor.CodeAttributeEditor;
import proguard.classfile.instruction.ConstantInstruction;
import proguard.classfile.instruction.Instruction;
import proguard.classfile.instruction.visitor.InstructionCounter;
import proguard.optimize.DuplicateInitializerInvocationFixer;
import proguard.optimize.info.ClassOptimizationInfo;

class NoConstructorReferenceReplacerDiffblueTest {
  /**
   * Test {@link NoConstructorReferenceReplacer#visitConstantInstruction(Clazz, Method,
   * CodeAttribute, int, ConstantInstruction)}.
   *
   * <p>Method under test: {@link NoConstructorReferenceReplacer#visitConstantInstruction(Clazz,
   * Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NoConstructorReferenceReplacer.visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)"
  })
  void testVisitConstantInstruction() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    doNothing()
        .when(codeAttributeEditor)
        .replaceInstruction(anyInt(), Mockito.<Instruction[]>any());
    NoConstructorReferenceReplacer noConstructorReferenceReplacer =
        new NoConstructorReferenceReplacer(codeAttributeEditor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    noConstructorReferenceReplacer.visitConstantInstruction(
        clazz, method, codeAttribute, 0, new ConstantInstruction((byte) -76, 1));

    // Assert
    verify(codeAttributeEditor).replaceInstruction(eq(0), isA(Instruction[].class));
  }

  /**
   * Test {@link NoConstructorReferenceReplacer#visitConstantInstruction(Clazz, Method,
   * CodeAttribute, int, ConstantInstruction)}.
   *
   * <p>Method under test: {@link NoConstructorReferenceReplacer#visitConstantInstruction(Clazz,
   * Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NoConstructorReferenceReplacer.visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)"
  })
  void testVisitConstantInstruction2() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    doNothing()
        .when(codeAttributeEditor)
        .replaceInstruction(anyInt(), Mockito.<Instruction[]>any());
    NoConstructorReferenceReplacer noConstructorReferenceReplacer =
        new NoConstructorReferenceReplacer(
            codeAttributeEditor, new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    noConstructorReferenceReplacer.visitConstantInstruction(
        clazz, method, codeAttribute, 0, new ConstantInstruction((byte) -76, 1));

    // Assert
    verify(codeAttributeEditor).replaceInstruction(eq(0), isA(Instruction[].class));
  }

  /**
   * Test {@link NoConstructorReferenceReplacer#visitConstantInstruction(Clazz, Method,
   * CodeAttribute, int, ConstantInstruction)}.
   *
   * <p>Method under test: {@link NoConstructorReferenceReplacer#visitConstantInstruction(Clazz,
   * Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NoConstructorReferenceReplacer.visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)"
  })
  void testVisitConstantInstruction3() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    doNothing()
        .when(codeAttributeEditor)
        .replaceInstruction(anyInt(), Mockito.<Instruction[]>any());
    NoConstructorReferenceReplacer noConstructorReferenceReplacer =
        new NoConstructorReferenceReplacer(codeAttributeEditor, new InstructionCounter());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    noConstructorReferenceReplacer.visitConstantInstruction(
        clazz, method, codeAttribute, 0, new ConstantInstruction((byte) -76, 1));

    // Assert
    verify(codeAttributeEditor).replaceInstruction(eq(0), isA(Instruction[].class));
  }

  /**
   * Test {@link NoConstructorReferenceReplacer#visitConstantInstruction(Clazz, Method,
   * CodeAttribute, int, ConstantInstruction)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link NoConstructorReferenceReplacer#visitConstantInstruction(Clazz,
   * Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction); then throw IllegalArgumentException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NoConstructorReferenceReplacer.visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)"
  })
  void testVisitConstantInstruction_thenThrowIllegalArgumentException() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    doNothing()
        .when(codeAttributeEditor)
        .replaceInstruction(anyInt(), Mockito.<Instruction[]>any());
    NoConstructorReferenceReplacer noConstructorReferenceReplacer =
        new NoConstructorReferenceReplacer(
            codeAttributeEditor, new NoConstructorReferenceReplacer(new CodeAttributeEditor()));
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            noConstructorReferenceReplacer.visitConstantInstruction(
                clazz, method, codeAttribute, 0, new ConstantInstruction((byte) -76, 1)));
    verify(codeAttributeEditor).replaceInstruction(eq(0), isA(Instruction[].class));
  }

  /**
   * Test {@link NoConstructorReferenceReplacer#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link ClassOptimizationInfo#containsConstructors()}.
   * </ul>
   *
   * <p>Method under test: {@link NoConstructorReferenceReplacer#visitFieldrefConstant(Clazz,
   * FieldrefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitFieldrefConstant(Clazz, FieldrefConstant); then calls containsConstructors()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NoConstructorReferenceReplacer.visitFieldrefConstant(Clazz, FieldrefConstant)"
  })
  void testVisitFieldrefConstant_thenCallsContainsConstructors() {
    // Arrange
    NoConstructorReferenceReplacer noConstructorReferenceReplacer =
        new NoConstructorReferenceReplacer(new CodeAttributeEditor());
    LibraryClass clazz = new LibraryClass();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.containsConstructors()).thenReturn(true);

    LibraryClass referencedClass = new LibraryClass(1, "This Class Name", "Super Class Name");
    referencedClass.setProcessingInfo(classOptimizationInfo);

    // Act
    noConstructorReferenceReplacer.visitFieldrefConstant(
        clazz,
        new FieldrefConstant(1, 1, referencedClass, new LibraryField(1, "Name", "Descriptor")));

    // Assert
    verify(classOptimizationInfo).containsConstructors();
  }

  /**
   * Test {@link NoConstructorReferenceReplacer#visitAnyMethodrefConstant(Clazz,
   * AnyMethodrefConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link ClassOptimizationInfo#containsConstructors()}.
   * </ul>
   *
   * <p>Method under test: {@link NoConstructorReferenceReplacer#visitAnyMethodrefConstant(Clazz,
   * AnyMethodrefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant); then calls containsConstructors()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NoConstructorReferenceReplacer.visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)"
  })
  void testVisitAnyMethodrefConstant_thenCallsContainsConstructors() {
    // Arrange
    NoConstructorReferenceReplacer noConstructorReferenceReplacer =
        new NoConstructorReferenceReplacer(new CodeAttributeEditor());
    LibraryClass clazz = new LibraryClass();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.containsConstructors()).thenReturn(true);

    LibraryClass referencedClass = new LibraryClass(1, "This Class Name", "Super Class Name");
    referencedClass.setProcessingInfo(classOptimizationInfo);

    // Act
    noConstructorReferenceReplacer.visitAnyMethodrefConstant(
        clazz,
        new InterfaceMethodrefConstant(
            1, 1, referencedClass, new LibraryMethod(1, "Name", "Descriptor")));

    // Assert
    verify(classOptimizationInfo).containsConstructors();
  }
}
