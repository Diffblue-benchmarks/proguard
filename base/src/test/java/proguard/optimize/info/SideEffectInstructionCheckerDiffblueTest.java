package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
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
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMember;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMember;
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
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}.
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.SideEffectInstructionChecker.hasSideEffects(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.Instruction)"})
  void testHasSideEffects() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertTrue(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new BranchInstruction((byte) -88, 1)));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}.
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.SideEffectInstructionChecker.hasSideEffects(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.Instruction)"})
  void testHasSideEffects2() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertFalse(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) -78, 1)));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}.
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.SideEffectInstructionChecker.hasSideEffects(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.Instruction)"})
  void testHasSideEffects3() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertFalse(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) -77, 1)));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}.
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.SideEffectInstructionChecker.hasSideEffects(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.Instruction)"})
  void testHasSideEffects4() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertTrue(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) -76, 1)));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}.
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.SideEffectInstructionChecker.hasSideEffects(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.Instruction)"})
  void testHasSideEffects5() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertTrue(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) -75, 1)));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}.
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.SideEffectInstructionChecker.hasSideEffects(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.Instruction)"})
  void testHasSideEffects6() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertTrue(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) -74, 1)));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}.
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.SideEffectInstructionChecker.hasSideEffects(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.Instruction)"})
  void testHasSideEffects7() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertFalse(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) -73, 1)));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}.
   * <ul>
   *   <li>When {@link BranchInstruction#BranchInstruction(byte, int)} with opcode is {@code A} and branchOffset is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction); when BranchInstruction(byte, int) with opcode is 'A' and branchOffset is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.SideEffectInstructionChecker.hasSideEffects(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.Instruction)"})
  void testHasSideEffects_whenBranchInstructionWithOpcodeIsAAndBranchOffsetIsOne() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertFalse(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new BranchInstruction((byte) 'A', 1)));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}.
   * <ul>
   *   <li>When {@link ConstantInstruction#ConstantInstruction(byte, int)} with opcode is {@code A} and constantIndex is one.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction); when ConstantInstruction(byte, int) with opcode is 'A' and constantIndex is one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.SideEffectInstructionChecker.hasSideEffects(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.Instruction)"})
  void testHasSideEffects_whenConstantInstructionWithOpcodeIsAAndConstantIndexIsOne() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertFalse(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) 'A', 1)));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}.
   * <ul>
   *   <li>When {@link LookUpSwitchInstruction#LookUpSwitchInstruction()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction); when LookUpSwitchInstruction(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.SideEffectInstructionChecker.hasSideEffects(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.Instruction)"})
  void testHasSideEffects_whenLookUpSwitchInstruction_thenReturnFalse() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertFalse(
        sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2, new LookUpSwitchInstruction()));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}.
   * <ul>
   *   <li>When {@link SimpleInstruction#SimpleInstruction(byte)} with opcode is {@code A}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction); when SimpleInstruction(byte) with opcode is 'A'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.SideEffectInstructionChecker.hasSideEffects(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.Instruction)"})
  void testHasSideEffects_whenSimpleInstructionWithOpcodeIsA_thenReturnFalse() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertFalse(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new SimpleInstruction((byte) 'A')));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}.
   * <ul>
   *   <li>When {@link SimpleInstruction#SimpleInstruction(byte)} with opcode is minus eighty-four.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction); when SimpleInstruction(byte) with opcode is minus eighty-four")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.SideEffectInstructionChecker.hasSideEffects(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.Instruction)"})
  void testHasSideEffects_whenSimpleInstructionWithOpcodeIsMinusEightyFour() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertTrue(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new SimpleInstruction((byte) -84)));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}.
   * <ul>
   *   <li>When {@link SimpleInstruction#SimpleInstruction(byte)} with opcode is minus sixty-eight.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction); when SimpleInstruction(byte) with opcode is minus sixty-eight")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.SideEffectInstructionChecker.hasSideEffects(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.Instruction)"})
  void testHasSideEffects_whenSimpleInstructionWithOpcodeIsMinusSixtyEight() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertTrue(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new SimpleInstruction((byte) -68)));
  }

  /**
   * Test {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}.
   * <ul>
   *   <li>When {@link VariableInstruction#VariableInstruction(byte)} with opcode is {@code A}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test hasSideEffects(Clazz, Method, CodeAttribute, int, Instruction); when VariableInstruction(byte) with opcode is 'A'; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.SideEffectInstructionChecker.hasSideEffects(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.Instruction)"})
  void testHasSideEffects_whenVariableInstructionWithOpcodeIsA_thenReturnFalse() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act and Assert
    assertFalse(sideEffectInstructionChecker.hasSideEffects(clazz, method, codeAttribute, 2,
        new VariableInstruction((byte) 'A')));
  }

  /**
   * Test {@link SideEffectInstructionChecker#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   * <ul>
   *   <li>Given {@link LibraryField} {@link LibraryMember#accept(Clazz, MemberVisitor)} does nothing.</li>
   *   <li>Then calls {@link LibraryMember#accept(Clazz, MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  @DisplayName("Test visitFieldrefConstant(Clazz, FieldrefConstant); given LibraryField accept(Clazz, MemberVisitor) does nothing; then calls accept(Clazz, MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.SideEffectInstructionChecker.visitFieldrefConstant(proguard.classfile.Clazz, proguard.classfile.constant.FieldrefConstant)"})
  void testVisitFieldrefConstant_givenLibraryFieldAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryField libraryField = mock(LibraryField.class);
    doNothing().when(libraryField).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    fieldrefConstant.referencedField = libraryField;

    // Act
    sideEffectInstructionChecker.visitFieldrefConstant(clazz, fieldrefConstant);

    // Assert
    verify(libraryField).accept((Clazz) isNull(), isA(MemberVisitor.class));
  }

  /**
   * Test {@link SideEffectInstructionChecker#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   * <ul>
   *   <li>Then calls {@link FieldrefConstant#referencedFieldAccept(MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  @DisplayName("Test visitFieldrefConstant(Clazz, FieldrefConstant); then calls referencedFieldAccept(MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.SideEffectInstructionChecker.visitFieldrefConstant(proguard.classfile.Clazz, proguard.classfile.constant.FieldrefConstant)"})
  void testVisitFieldrefConstant_thenCallsReferencedFieldAccept() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    FieldrefConstant fieldrefConstant = mock(FieldrefConstant.class);
    doNothing().when(fieldrefConstant).referencedFieldAccept(Mockito.<MemberVisitor>any());

    // Act
    sideEffectInstructionChecker.visitFieldrefConstant(clazz, fieldrefConstant);

    // Assert
    verify(fieldrefConstant).referencedFieldAccept(isA(MemberVisitor.class));
  }

  /**
   * Test {@link SideEffectInstructionChecker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   * <ul>
   *   <li>Then calls {@link LibraryMember#accept(Clazz, MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant); then calls accept(Clazz, MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.SideEffectInstructionChecker.visitAnyMethodrefConstant(proguard.classfile.Clazz, proguard.classfile.constant.AnyMethodrefConstant)"})
  void testVisitAnyMethodrefConstant_thenCallsAccept() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    doNothing().when(libraryMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    InterfaceMethodrefConstant anyMethodrefConstant = new InterfaceMethodrefConstant();
    anyMethodrefConstant.referencedMethod = libraryMethod;

    // Act
    sideEffectInstructionChecker.visitAnyMethodrefConstant(clazz, anyMethodrefConstant);

    // Assert
    verify(libraryMethod).accept((Clazz) isNull(), isA(MemberVisitor.class));
  }

  /**
   * Test {@link SideEffectInstructionChecker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   * <ul>
   *   <li>Then calls {@link MethodOptimizationInfo#hasSideEffects()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant); then calls hasSideEffects()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.SideEffectInstructionChecker.visitAnyMethodrefConstant(proguard.classfile.Clazz, proguard.classfile.constant.AnyMethodrefConstant)"})
  void testVisitAnyMethodrefConstant_thenCallsHasSideEffects() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.hasSideEffects()).thenReturn(true);

    ProgramMethod programMethod = new ProgramMethod();
    programMethod.setProcessingInfo(methodOptimizationInfo);
    InterfaceMethodrefConstant anyMethodrefConstant = new InterfaceMethodrefConstant();
    anyMethodrefConstant.referencedMethod = programMethod;

    // Act
    sideEffectInstructionChecker.visitAnyMethodrefConstant(clazz, anyMethodrefConstant);

    // Assert
    verify(methodOptimizationInfo).hasSideEffects();
  }

  /**
   * Test {@link SideEffectInstructionChecker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   * <ul>
   *   <li>Then calls {@link AnyMethodrefConstant#referencedMethodAccept(MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant); then calls referencedMethodAccept(MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.SideEffectInstructionChecker.visitAnyMethodrefConstant(proguard.classfile.Clazz, proguard.classfile.constant.AnyMethodrefConstant)"})
  void testVisitAnyMethodrefConstant_thenCallsReferencedMethodAccept() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    LibraryClass clazz = new LibraryClass();
    InterfaceMethodrefConstant anyMethodrefConstant = mock(InterfaceMethodrefConstant.class);
    doNothing().when(anyMethodrefConstant).referencedMethodAccept(Mockito.<MemberVisitor>any());

    // Act
    sideEffectInstructionChecker.visitAnyMethodrefConstant(clazz, anyMethodrefConstant);

    // Assert
    verify(anyMethodrefConstant).referencedMethodAccept(isA(MemberVisitor.class));
  }

  /**
   * Test {@link SideEffectInstructionChecker#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Given {@code String}.</li>
   *   <li>When {@link ProgramClass} {@link ProgramClass#getString(int)} return {@code String}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); given 'String'; when ProgramClass getString(int) return 'String'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.SideEffectInstructionChecker.visitProgramField(proguard.classfile.ProgramClass, proguard.classfile.ProgramField)"})
  void testVisitProgramField_givenString_whenProgramClassGetStringReturnString() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    sideEffectInstructionChecker.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Test {@link SideEffectInstructionChecker#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Then calls {@link ClassOptimizationInfo#hasNoSideEffects()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); then calls hasNoSideEffects()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.SideEffectInstructionChecker.visitProgramField(proguard.classfile.ProgramClass, proguard.classfile.ProgramField)"})
  void testVisitProgramField_thenCallsHasNoSideEffects() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
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
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given MethodOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.SideEffectInstructionChecker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_givenMethodOptimizationInfo() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
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
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} {@link MethodOptimizationInfo#hasSideEffects()} return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given MethodOptimizationInfo hasSideEffects() return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.SideEffectInstructionChecker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_givenMethodOptimizationInfoHasSideEffectsReturnTrue() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
    ProgramClass programClass = new ProgramClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.hasSideEffects()).thenReturn(true);
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    sideEffectInstructionChecker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(methodOptimizationInfo).hasSideEffects();
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link SideEffectInstructionChecker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given one.</li>
   *   <li>Then calls {@link ProgramMember#getAccessFlags()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given one; then calls getAccessFlags()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.SideEffectInstructionChecker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_givenOne_thenCallsGetAccessFlags() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
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
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod); given MethodOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.SideEffectInstructionChecker.visitLibraryMethod(proguard.classfile.LibraryClass, proguard.classfile.LibraryMethod)"})
  void testVisitLibraryMethod_givenMethodOptimizationInfo() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
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
   * <ul>
   *   <li>Then calls {@link MethodOptimizationInfo#hasNoSideEffects()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectInstructionChecker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod); then calls hasNoSideEffects()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.SideEffectInstructionChecker.visitLibraryMethod(proguard.classfile.LibraryClass, proguard.classfile.LibraryMethod)"})
  void testVisitLibraryMethod_thenCallsHasNoSideEffects() {
    // Arrange
    SideEffectInstructionChecker sideEffectInstructionChecker = new SideEffectInstructionChecker(true, true, true);
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
