package proguard.optimize.gson;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
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
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.editor.CodeAttributeEditor;
import proguard.classfile.instruction.BranchInstruction;
import proguard.classfile.instruction.ConstantInstruction;
import proguard.classfile.instruction.Instruction;
import proguard.classfile.instruction.visitor.InstructionVisitor;

class GsonConstructorPatcherDiffblueTest {
  /**
   * Test {@link GsonConstructorPatcher#visitCodeAttribute(Clazz, Method, CodeAttribute)}.
   * <ul>
   *   <li>Then calls {@link CodeAttribute#instructionsAccept(Clazz, Method, InstructionVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GsonConstructorPatcher#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  @DisplayName("Test visitCodeAttribute(Clazz, Method, CodeAttribute); then calls instructionsAccept(Clazz, Method, InstructionVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GsonConstructorPatcher.visitCodeAttribute(Clazz, Method, CodeAttribute)"})
  void testVisitCodeAttribute_thenCallsInstructionsAccept() {
    // Arrange
    GsonConstructorPatcher gsonConstructorPatcher = new GsonConstructorPatcher(new CodeAttributeEditor(), true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = mock(CodeAttribute.class);
    doNothing().when(codeAttribute)
        .instructionsAccept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<InstructionVisitor>any());

    // Act
    gsonConstructorPatcher.visitCodeAttribute(clazz, method, codeAttribute);

    // Assert
    verify(codeAttribute).instructionsAccept(isA(Clazz.class), isA(Method.class), isA(InstructionVisitor.class));
  }

  /**
   * Test {@link GsonConstructorPatcher#visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)}.
   * <p>
   * Method under test: {@link GsonConstructorPatcher#visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GsonConstructorPatcher.visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)"})
  void testVisitAnyInstruction() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    doNothing().when(codeAttributeEditor).insertBeforeOffset(anyInt(), Mockito.<Instruction>any());
    codeAttributeEditor.insertBeforeOffset(-1, new BranchInstruction((byte) 'A', -71));
    GsonConstructorPatcher gsonConstructorPatcher = new GsonConstructorPatcher(codeAttributeEditor, true);
    ProgramClass clazz = new ProgramClass(-71, 3, new Constant[]{new ClassConstant()}, -71, -71, -71);

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    ConstantInstruction instruction = mock(ConstantInstruction.class);
    when(instruction.actualOpcode()).thenReturn((byte) -71);

    // Act
    gsonConstructorPatcher.visitAnyInstruction(clazz, method, codeAttribute, 2, instruction);

    // Assert
    verify(codeAttributeEditor).insertBeforeOffset(eq(-1), isA(Instruction.class));
    verify(instruction).actualOpcode();
  }

  /**
   * Test {@link GsonConstructorPatcher#visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>When {@link ConstantInstruction} {@link Instruction#actualOpcode()} return {@code A}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GsonConstructorPatcher#visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction); given 'A'; when ConstantInstruction actualOpcode() return 'A'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void GsonConstructorPatcher.visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)"})
  void testVisitAnyInstruction_givenA_whenConstantInstructionActualOpcodeReturnA() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    doNothing().when(codeAttributeEditor).insertBeforeOffset(anyInt(), Mockito.<Instruction>any());
    codeAttributeEditor.insertBeforeOffset(-1, new BranchInstruction((byte) 'A', -71));
    GsonConstructorPatcher gsonConstructorPatcher = new GsonConstructorPatcher(codeAttributeEditor, true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    ConstantInstruction instruction = mock(ConstantInstruction.class);
    when(instruction.actualOpcode()).thenReturn((byte) 'A');

    // Act
    gsonConstructorPatcher.visitAnyInstruction(clazz, method, codeAttribute, 2, instruction);

    // Assert
    verify(codeAttributeEditor).insertBeforeOffset(eq(-1), isA(Instruction.class));
    verify(instruction, atLeast(1)).actualOpcode();
  }
}
