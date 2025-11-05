package proguard.backport;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.attribute.BootstrapMethodInfo;
import proguard.classfile.attribute.BootstrapMethodsAttribute;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.attribute.visitor.BootstrapMethodInfoVisitor;
import proguard.classfile.constant.InvokeDynamicConstant;
import proguard.classfile.constant.MethodHandleConstant;
import proguard.classfile.editor.CodeAttributeEditor;
import proguard.classfile.instruction.BranchInstruction;
import proguard.classfile.instruction.ConstantInstruction;
import proguard.classfile.instruction.Instruction;
import proguard.optimize.DuplicateInitializerInvocationFixer;

class StringConcatenationConverterDiffblueTest {
  /**
   * Method under test:
   * {@link StringConcatenationConverter#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  void testVisitConstantInstruction() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    doNothing().when(codeAttributeEditor).insertBeforeOffset(anyInt(), Mockito.<Instruction>any());
    codeAttributeEditor.insertBeforeOffset(1, new BranchInstruction((byte) 'A', -70));
    StringConcatenationConverter stringConcatenationConverter = new StringConcatenationConverter(
        new DuplicateInitializerInvocationFixer(), codeAttributeEditor);
    ProgramClass clazz = mock(ProgramClass.class);
    doNothing().when(clazz).attributesAccept(Mockito.<AttributeVisitor>any());
    when(clazz.getConstant(anyInt())).thenReturn(new InvokeDynamicConstant());
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    stringConcatenationConverter.visitConstantInstruction(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) -70, 1));

    // Assert
    verify(clazz).attributesAccept(isA(AttributeVisitor.class));
    verify(clazz).getConstant(eq(1));
    verify(codeAttributeEditor).insertBeforeOffset(eq(1), isA(Instruction.class));
  }

  /**
   * Method under test:
   * {@link StringConcatenationConverter#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  void testVisitConstantInstruction2() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    doNothing().when(codeAttributeEditor).insertBeforeOffset(anyInt(), Mockito.<Instruction>any());
    codeAttributeEditor.insertBeforeOffset(1, new BranchInstruction((byte) 'A', -70));
    StringConcatenationConverter stringConcatenationConverter = new StringConcatenationConverter(
        new DuplicateInitializerInvocationFixer(), codeAttributeEditor);
    InvokeDynamicConstant invokeDynamicConstant = mock(InvokeDynamicConstant.class);
    when(invokeDynamicConstant.getBootstrapMethodAttributeIndex()).thenReturn(1);
    ProgramClass clazz = mock(ProgramClass.class);
    doNothing().when(clazz).attributesAccept(Mockito.<AttributeVisitor>any());
    when(clazz.getConstant(anyInt())).thenReturn(invokeDynamicConstant);
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    stringConcatenationConverter.visitConstantInstruction(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) -70, 1));

    // Assert
    verify(clazz).attributesAccept(isA(AttributeVisitor.class));
    verify(clazz).getConstant(eq(1));
    verify(invokeDynamicConstant).getBootstrapMethodAttributeIndex();
    verify(codeAttributeEditor).insertBeforeOffset(eq(1), isA(Instruction.class));
  }

  /**
   * Method under test:
   * {@link StringConcatenationConverter#visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute)}
   */
  @Test
  void testVisitBootstrapMethodsAttribute() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    doNothing().when(codeAttributeEditor).insertBeforeOffset(anyInt(), Mockito.<Instruction>any());
    codeAttributeEditor.insertBeforeOffset(0, new BranchInstruction((byte) 'A', 1));
    StringConcatenationConverter stringConcatenationConverter = new StringConcatenationConverter(
        new DuplicateInitializerInvocationFixer(), codeAttributeEditor);
    ProgramClass clazz = mock(ProgramClass.class);
    when(clazz.getRefClassName(anyInt())).thenReturn("Ref Class Name");
    when(clazz.getConstant(anyInt())).thenReturn(new MethodHandleConstant(1, 1));

    // Act
    stringConcatenationConverter.visitBootstrapMethodsAttribute(clazz,
        new BootstrapMethodsAttribute(1, 3, new BootstrapMethodInfo[]{new BootstrapMethodInfo()}));

    // Assert
    verify(clazz).getConstant(eq(0));
    verify(clazz).getRefClassName(eq(1));
    verify(codeAttributeEditor).insertBeforeOffset(eq(0), isA(Instruction.class));
  }

  /**
   * Method under test:
   * {@link StringConcatenationConverter#visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute)}
   */
  @Test
  void testVisitBootstrapMethodsAttribute2() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    doNothing().when(codeAttributeEditor).insertBeforeOffset(anyInt(), Mockito.<Instruction>any());
    codeAttributeEditor.insertBeforeOffset(0, new BranchInstruction((byte) 'A', 1));
    StringConcatenationConverter stringConcatenationConverter = new StringConcatenationConverter(
        new DuplicateInitializerInvocationFixer(), codeAttributeEditor);
    MethodHandleConstant methodHandleConstant = mock(MethodHandleConstant.class);
    when(methodHandleConstant.getClassName(Mockito.<Clazz>any())).thenReturn("Class Name");
    ProgramClass clazz = mock(ProgramClass.class);
    when(clazz.getConstant(anyInt())).thenReturn(methodHandleConstant);

    // Act
    stringConcatenationConverter.visitBootstrapMethodsAttribute(clazz,
        new BootstrapMethodsAttribute(1, 3, new BootstrapMethodInfo[]{new BootstrapMethodInfo()}));

    // Assert
    verify(clazz).getConstant(eq(0));
    verify(methodHandleConstant).getClassName(isA(Clazz.class));
    verify(codeAttributeEditor).insertBeforeOffset(eq(0), isA(Instruction.class));
  }

  /**
   * Method under test:
   * {@link StringConcatenationConverter#visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute)}
   */
  @Test
  void testVisitBootstrapMethodsAttribute3() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    doNothing().when(codeAttributeEditor).insertBeforeOffset(anyInt(), Mockito.<Instruction>any());
    codeAttributeEditor.insertBeforeOffset(0, new BranchInstruction((byte) 'A', 1));
    StringConcatenationConverter stringConcatenationConverter = new StringConcatenationConverter(
        new DuplicateInitializerInvocationFixer(), codeAttributeEditor);
    ProgramClass clazz = mock(ProgramClass.class);
    BootstrapMethodsAttribute bootstrapMethodsAttribute = mock(BootstrapMethodsAttribute.class);
    doNothing().when(bootstrapMethodsAttribute)
        .bootstrapMethodEntryAccept(Mockito.<Clazz>any(), anyInt(), Mockito.<BootstrapMethodInfoVisitor>any());

    // Act
    stringConcatenationConverter.visitBootstrapMethodsAttribute(clazz, bootstrapMethodsAttribute);

    // Assert
    verify(bootstrapMethodsAttribute).bootstrapMethodEntryAccept(isA(Clazz.class), eq(0),
        isA(BootstrapMethodInfoVisitor.class));
    verify(codeAttributeEditor).insertBeforeOffset(eq(0), isA(Instruction.class));
  }

  /**
   * Method under test:
   * {@link StringConcatenationConverter#visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo)}
   */
  @Test
  void testVisitBootstrapMethodInfo() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    doNothing().when(codeAttributeEditor).insertBeforeOffset(anyInt(), Mockito.<Instruction>any());
    codeAttributeEditor.insertBeforeOffset(1, new BranchInstruction((byte) 'A', 1));
    StringConcatenationConverter stringConcatenationConverter = new StringConcatenationConverter(
        new DuplicateInitializerInvocationFixer(), codeAttributeEditor);
    ProgramClass clazz = mock(ProgramClass.class);
    when(clazz.getRefClassName(anyInt())).thenReturn("Ref Class Name");
    when(clazz.getConstant(anyInt())).thenReturn(new MethodHandleConstant(1, 1));

    // Act
    stringConcatenationConverter.visitBootstrapMethodInfo(clazz, new BootstrapMethodInfo());

    // Assert
    verify(clazz).getConstant(eq(0));
    verify(clazz).getRefClassName(eq(1));
    verify(codeAttributeEditor).insertBeforeOffset(eq(1), isA(Instruction.class));
  }

  /**
   * Method under test:
   * {@link StringConcatenationConverter#visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo)}
   */
  @Test
  void testVisitBootstrapMethodInfo2() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    doNothing().when(codeAttributeEditor).insertBeforeOffset(anyInt(), Mockito.<Instruction>any());
    codeAttributeEditor.insertBeforeOffset(1, new BranchInstruction((byte) 'A', 1));
    StringConcatenationConverter stringConcatenationConverter = new StringConcatenationConverter(
        new DuplicateInitializerInvocationFixer(), codeAttributeEditor);
    MethodHandleConstant methodHandleConstant = mock(MethodHandleConstant.class);
    when(methodHandleConstant.getClassName(Mockito.<Clazz>any())).thenReturn("Class Name");
    ProgramClass clazz = mock(ProgramClass.class);
    when(clazz.getConstant(anyInt())).thenReturn(methodHandleConstant);

    // Act
    stringConcatenationConverter.visitBootstrapMethodInfo(clazz, new BootstrapMethodInfo());

    // Assert
    verify(clazz).getConstant(eq(0));
    verify(methodHandleConstant).getClassName(isA(Clazz.class));
    verify(codeAttributeEditor).insertBeforeOffset(eq(1), isA(Instruction.class));
  }
}
