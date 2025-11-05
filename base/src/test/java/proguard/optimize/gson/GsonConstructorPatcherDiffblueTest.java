package proguard.optimize.gson;

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
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.editor.CodeAttributeEditor;
import proguard.classfile.instruction.BranchInstruction;
import proguard.classfile.instruction.ConstantInstruction;
import proguard.classfile.instruction.Instruction;
import proguard.classfile.instruction.visitor.InstructionVisitor;
import proguard.testutils.cpa.NamedMember;

class GsonConstructorPatcherDiffblueTest {
  /**
   * Method under test:
   * {@link GsonConstructorPatcher#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
    // Arrange
    GsonConstructorPatcher gsonConstructorPatcher = new GsonConstructorPatcher(new CodeAttributeEditor(), true);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    gsonConstructorPatcher.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link GsonConstructorPatcher#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod2() {
    // Arrange
    GsonConstructorPatcher gsonConstructorPatcher = new GsonConstructorPatcher(new CodeAttributeEditor(), true);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");
    when(programClass.getString(anyInt())).thenReturn(GsonClassConstants.FIELD_TYPE_DESERIALIZATION_STRATEGIES);

    // Act
    gsonConstructorPatcher.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).getName();
    verify(programClass, atLeast(1)).getString(eq(0));
  }

  /**
   * Method under test:
   * {@link GsonConstructorPatcher#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod3() {
    // Arrange
    GsonConstructorPatcher gsonConstructorPatcher = new GsonConstructorPatcher(new CodeAttributeEditor(), true);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getName()).thenReturn("Name");

    // Act
    gsonConstructorPatcher.visitProgramMethod(programClass,
        new NamedMember(GsonClassConstants.FIELD_TYPE_DESERIALIZATION_STRATEGIES,
            GsonClassConstants.FIELD_TYPE_DESERIALIZATION_STRATEGIES));

    // Assert
    verify(programClass).getName();
  }

  /**
   * Method under test:
   * {@link GsonConstructorPatcher#visitCodeAttribute(Clazz, Method, CodeAttribute)}
   */
  @Test
  void testVisitCodeAttribute() {
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
   * Method under test:
   * {@link GsonConstructorPatcher#visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  void testVisitAnyInstruction() {
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

  /**
   * Method under test:
   * {@link GsonConstructorPatcher#visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  void testVisitAnyInstruction2() {
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
}
