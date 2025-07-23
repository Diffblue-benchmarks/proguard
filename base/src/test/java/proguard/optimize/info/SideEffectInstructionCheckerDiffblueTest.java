package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
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
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;
import proguard.classfile.instruction.BranchInstruction;
import proguard.classfile.instruction.ConstantInstruction;
import proguard.classfile.instruction.Instruction;
import proguard.classfile.instruction.LookUpSwitchInstruction;
import proguard.classfile.instruction.SimpleInstruction;
import proguard.classfile.instruction.VariableInstruction;
import proguard.classfile.visitor.MemberVisitor;

class SideEffectInstructionCheckerDiffblueTest {
  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int,
   * Instruction)}.
   *
   * <p>Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method,
   * CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SideEffectInstructionChecker.hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)"
  })
  void testHasSideEffects() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker =
        new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertTrue(
        sideEffectInstructionChecker.hasSideEffects(
            clazz, method, codeAttribute, 2, new BranchInstruction((byte) -88, 1)));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int,
   * Instruction)}.
   *
   * <p>Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method,
   * CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SideEffectInstructionChecker.hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)"
  })
  void testHasSideEffects2() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker =
        new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertFalse(
        sideEffectInstructionChecker.hasSideEffects(
            clazz, method, codeAttribute, 2, new ConstantInstruction((byte) -78, 1)));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int,
   * Instruction)}.
   *
   * <p>Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method,
   * CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SideEffectInstructionChecker.hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)"
  })
  void testHasSideEffects3() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker =
        new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertFalse(
        sideEffectInstructionChecker.hasSideEffects(
            clazz, method, codeAttribute, 2, new ConstantInstruction((byte) -77, 1)));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int,
   * Instruction)}.
   *
   * <p>Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method,
   * CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SideEffectInstructionChecker.hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)"
  })
  void testHasSideEffects4() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker =
        new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertTrue(
        sideEffectInstructionChecker.hasSideEffects(
            clazz, method, codeAttribute, 2, new ConstantInstruction((byte) -76, 1)));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int,
   * Instruction)}.
   *
   * <p>Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method,
   * CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SideEffectInstructionChecker.hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)"
  })
  void testHasSideEffects5() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker =
        new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertTrue(
        sideEffectInstructionChecker.hasSideEffects(
            clazz, method, codeAttribute, 2, new ConstantInstruction((byte) -75, 1)));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int,
   * Instruction)}.
   *
   * <p>Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method,
   * CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SideEffectInstructionChecker.hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)"
  })
  void testHasSideEffects6() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker =
        new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertTrue(
        sideEffectInstructionChecker.hasSideEffects(
            clazz, method, codeAttribute, 2, new ConstantInstruction((byte) -74, 1)));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int,
   * Instruction)}.
   *
   * <p>Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method,
   * CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SideEffectInstructionChecker.hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)"
  })
  void testHasSideEffects7() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker =
        new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertFalse(
        sideEffectInstructionChecker.hasSideEffects(
            clazz, method, codeAttribute, 2, new ConstantInstruction((byte) -73, 1)));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int,
   * Instruction)}.
   *
   * <ul>
   *   <li>When {@link BranchInstruction#BranchInstruction(byte, int)} with opcode is {@code A} and
   *       branchOffset is one.
   * </ul>
   *
   * <p>Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method,
   * CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName(
      "Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction); when BranchInstruction(byte, int) with opcode is 'A' and branchOffset is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SideEffectInstructionChecker.hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)"
  })
  void testHasSideEffects_whenBranchInstructionWithOpcodeIsAAndBranchOffsetIsOne() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker =
        new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertFalse(
        sideEffectInstructionChecker.hasSideEffects(
            clazz, method, codeAttribute, 2, new BranchInstruction((byte) 'A', 1)));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int,
   * Instruction)}.
   *
   * <ul>
   *   <li>When {@link ConstantInstruction#ConstantInstruction(byte, int)} with opcode is {@code A}
   *       and constantIndex is one.
   * </ul>
   *
   * <p>Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method,
   * CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName(
      "Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction); when ConstantInstruction(byte, int) with opcode is 'A' and constantIndex is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SideEffectInstructionChecker.hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)"
  })
  void testHasSideEffects_whenConstantInstructionWithOpcodeIsAAndConstantIndexIsOne() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker =
        new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertFalse(
        sideEffectInstructionChecker.hasSideEffects(
            clazz, method, codeAttribute, 2, new ConstantInstruction((byte) 'A', 1)));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int,
   * Instruction)}.
   *
   * <ul>
   *   <li>When {@link LookUpSwitchInstruction#LookUpSwitchInstruction()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method,
   * CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName(
      "Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction); when LookUpSwitchInstruction(); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SideEffectInstructionChecker.hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)"
  })
  void testHasSideEffects_whenLookUpSwitchInstruction_thenReturnFalse() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker =
        new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertFalse(
        sideEffectInstructionChecker.hasSideEffects(
            clazz, method, codeAttribute, 2, new LookUpSwitchInstruction()));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int,
   * Instruction)}.
   *
   * <ul>
   *   <li>When {@link SimpleInstruction#SimpleInstruction(byte)} with opcode is {@code A}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method,
   * CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName(
      "Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction); when SimpleInstruction(byte) with opcode is 'A'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SideEffectInstructionChecker.hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)"
  })
  void testHasSideEffects_whenSimpleInstructionWithOpcodeIsA_thenReturnFalse() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker =
        new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertFalse(
        sideEffectInstructionChecker.hasSideEffects(
            clazz, method, codeAttribute, 2, new SimpleInstruction((byte) 'A')));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int,
   * Instruction)}.
   *
   * <ul>
   *   <li>When {@link SimpleInstruction#SimpleInstruction(byte)} with opcode is minus eighty-four.
   * </ul>
   *
   * <p>Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method,
   * CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName(
      "Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction); when SimpleInstruction(byte) with opcode is minus eighty-four")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SideEffectInstructionChecker.hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)"
  })
  void testHasSideEffects_whenSimpleInstructionWithOpcodeIsMinusEightyFour() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker =
        new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertTrue(
        sideEffectInstructionChecker.hasSideEffects(
            clazz, method, codeAttribute, 2, new SimpleInstruction((byte) -84)));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int,
   * Instruction)}.
   *
   * <ul>
   *   <li>When {@link SimpleInstruction#SimpleInstruction(byte)} with opcode is minus sixty-eight.
   * </ul>
   *
   * <p>Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method,
   * CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName(
      "Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction); when SimpleInstruction(byte) with opcode is minus sixty-eight")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SideEffectInstructionChecker.hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)"
  })
  void testHasSideEffects_whenSimpleInstructionWithOpcodeIsMinusSixtyEight() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker =
        new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertTrue(
        sideEffectInstructionChecker.hasSideEffects(
            clazz, method, codeAttribute, 2, new SimpleInstruction((byte) -68)));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int,
   * Instruction)}.
   *
   * <ul>
   *   <li>When {@link VariableInstruction#VariableInstruction(byte)} with opcode is {@code A}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method,
   * CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName(
      "Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction); when VariableInstruction(byte) with opcode is 'A'; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SideEffectInstructionChecker.hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)"
  })
  void testHasSideEffects_whenVariableInstructionWithOpcodeIsA_thenReturnFalse() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker =
        new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertFalse(
        sideEffectInstructionChecker.hasSideEffects(
            clazz, method, codeAttribute, 2, new VariableInstruction((byte) 'A')));
  }

  /**
   * Test {@link SideEffectInstructionChecker#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link FieldrefConstant#referencedFieldAccept(MemberVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link SideEffectInstructionChecker#visitFieldrefConstant(Clazz,
   * FieldrefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitFieldrefConstant(Clazz, FieldrefConstant); then calls referencedFieldAccept(MemberVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SideEffectInstructionChecker.visitFieldrefConstant(Clazz, FieldrefConstant)"
  })
  void testVisitFieldrefConstant_thenCallsReferencedFieldAccept() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker =
        new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    FieldrefConstant fieldrefConstant = mock(FieldrefConstant.class);
    doNothing().when(fieldrefConstant).referencedFieldAccept(Mockito.<MemberVisitor>any());

    // Act
    sideEffectInstructionChecker.visitFieldrefConstant(clazz, fieldrefConstant);

    // Assert
    verify(fieldrefConstant).referencedFieldAccept(isA(MemberVisitor.class));
  }

  /**
   * Test {@link SideEffectInstructionChecker#visitAnyMethodrefConstant(Clazz,
   * AnyMethodrefConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link InterfaceMethodrefConstant#referencedMethodAccept(MemberVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link SideEffectInstructionChecker#visitAnyMethodrefConstant(Clazz,
   * AnyMethodrefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant); then calls referencedMethodAccept(MemberVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SideEffectInstructionChecker.visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)"
  })
  void testVisitAnyMethodrefConstant_thenCallsReferencedMethodAccept() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker =
        new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    InterfaceMethodrefConstant anyMethodrefConstant = mock(InterfaceMethodrefConstant.class);
    doNothing().when(anyMethodrefConstant).referencedMethodAccept(Mockito.<MemberVisitor>any());

    // Act
    sideEffectInstructionChecker.visitAnyMethodrefConstant(clazz, anyMethodrefConstant);

    // Assert
    verify(anyMethodrefConstant).referencedMethodAccept(isA(MemberVisitor.class));
  }

  /**
   * Test {@link SideEffectInstructionChecker#visitAnyMethodrefConstant(Clazz,
   * AnyMethodrefConstant)}.
   *
   * <ul>
   *   <li>When {@link Method} {@link Method#accept(Clazz, MemberVisitor)} does nothing.
   *   <li>Then calls {@link Method#accept(Clazz, MemberVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link SideEffectInstructionChecker#visitAnyMethodrefConstant(Clazz,
   * AnyMethodrefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant); when Method accept(Clazz, MemberVisitor) does nothing; then calls accept(Clazz, MemberVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SideEffectInstructionChecker.visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)"
  })
  void testVisitAnyMethodrefConstant_whenMethodAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker =
        new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    Method referencedMethod = mock(Method.class);
    doNothing().when(referencedMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());

    // Act
    sideEffectInstructionChecker.visitAnyMethodrefConstant(
        clazz, new InterfaceMethodrefConstant(1, 1, new LibraryClass(), referencedMethod));

    // Assert
    verify(referencedMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
  }

  /**
   * Test {@link SideEffectInstructionChecker#visitProgramField(ProgramClass, ProgramField)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   *   <li>When {@link ProgramClass} {@link ProgramClass#getString(int)} return {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link SideEffectInstructionChecker#visitProgramField(ProgramClass,
   * ProgramField)}
   */
  @Test
  @DisplayName(
      "Test visitProgramField(ProgramClass, ProgramField); given 'String'; when ProgramClass getString(int) return 'String'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SideEffectInstructionChecker.visitProgramField(ProgramClass, ProgramField)"
  })
  void testVisitProgramField_givenString_whenProgramClassGetStringReturnString() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker =
        new SideEffectInstructionChecker(true, true, true);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    sideEffectInstructionChecker.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Test {@link SideEffectInstructionChecker#visitProgramField(ProgramClass, ProgramField)}.
   *
   * <ul>
   *   <li>Then calls {@link ClassOptimizationInfo#hasNoSideEffects()}.
   * </ul>
   *
   * <p>Method under test: {@link SideEffectInstructionChecker#visitProgramField(ProgramClass,
   * ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); then calls hasNoSideEffects()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SideEffectInstructionChecker.visitProgramField(ProgramClass, ProgramField)"
  })
  void testVisitProgramField_thenCallsHasNoSideEffects() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker =
        new SideEffectInstructionChecker(true, true, true);
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.hasNoSideEffects()).thenReturn(true);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    when(programClass.getString(anyInt())).thenReturn("<init>");

    // Act
    sideEffectInstructionChecker.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(programClass).getString(eq(0));
    verify(classOptimizationInfo).hasNoSideEffects();
    verify(programClass).getProcessingInfo();
  }

  /**
   * Test {@link SideEffectInstructionChecker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link SideEffectInstructionChecker#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); given MethodOptimizationInfo (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SideEffectInstructionChecker.visitProgramMethod(ProgramClass, ProgramMethod)"
  })
  void testVisitProgramMethod_givenMethodOptimizationInfo() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker =
        new SideEffectInstructionChecker(true, true, true);
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    sideEffectInstructionChecker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link SideEffectInstructionChecker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>Then calls {@link ProgramMethod#getAccessFlags()}.
   * </ul>
   *
   * <p>Method under test: {@link SideEffectInstructionChecker#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); given one; then calls getAccessFlags()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SideEffectInstructionChecker.visitProgramMethod(ProgramClass, ProgramMethod)"
  })
  void testVisitProgramMethod_givenOne_thenCallsGetAccessFlags() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker =
        new SideEffectInstructionChecker(true, true, true);
    ProgramClass programClass = new ProgramClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.hasSideEffects()).thenReturn(false);
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getAccessFlags()).thenReturn(1);
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");
    when(programMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    sideEffectInstructionChecker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod).getAccessFlags();
    verify(programMethod).getName(isA(Clazz.class));
    verify(methodOptimizationInfo).hasSideEffects();
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link SideEffectInstructionChecker#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link SideEffectInstructionChecker#visitLibraryMethod(LibraryClass,
   * LibraryMethod)}
   */
  @Test
  @DisplayName(
      "Test visitLibraryMethod(LibraryClass, LibraryMethod); given MethodOptimizationInfo (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SideEffectInstructionChecker.visitLibraryMethod(LibraryClass, LibraryMethod)"
  })
  void testVisitLibraryMethod_givenMethodOptimizationInfo() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker =
        new SideEffectInstructionChecker(true, true, true);
    LibraryClass libraryClass = new LibraryClass();
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    sideEffectInstructionChecker.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link SideEffectInstructionChecker#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} {@link MethodOptimizationInfo#hasNoSideEffects()}
   *       return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SideEffectInstructionChecker#visitLibraryMethod(LibraryClass,
   * LibraryMethod)}
   */
  @Test
  @DisplayName(
      "Test visitLibraryMethod(LibraryClass, LibraryMethod); given MethodOptimizationInfo hasNoSideEffects() return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SideEffectInstructionChecker.visitLibraryMethod(LibraryClass, LibraryMethod)"
  })
  void testVisitLibraryMethod_givenMethodOptimizationInfoHasNoSideEffectsReturnFalse() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker =
        new SideEffectInstructionChecker(true, true, true);
    LibraryClass libraryClass = new LibraryClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.hasNoSideEffects()).thenReturn(false);
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    sideEffectInstructionChecker.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(methodOptimizationInfo).hasNoSideEffects();
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link SideEffectInstructionChecker#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} {@link MethodOptimizationInfo#hasNoSideEffects()}
   *       return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SideEffectInstructionChecker#visitLibraryMethod(LibraryClass,
   * LibraryMethod)}
   */
  @Test
  @DisplayName(
      "Test visitLibraryMethod(LibraryClass, LibraryMethod); given MethodOptimizationInfo hasNoSideEffects() return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SideEffectInstructionChecker.visitLibraryMethod(LibraryClass, LibraryMethod)"
  })
  void testVisitLibraryMethod_givenMethodOptimizationInfoHasNoSideEffectsReturnTrue() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker =
        new SideEffectInstructionChecker(true, true, true);
    LibraryClass libraryClass = new LibraryClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.hasNoSideEffects()).thenReturn(true);
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    sideEffectInstructionChecker.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(methodOptimizationInfo).hasNoSideEffects();
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }
}
