package proguard.backport;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
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
   * Test {@link StringConcatenationConverter#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}.
   * <ul>
   *   <li>Given {@link InvokeDynamicConstant#InvokeDynamicConstant()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringConcatenationConverter#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  @DisplayName("Test visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction); given InvokeDynamicConstant()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.backport.StringConcatenationConverter.visitConstantInstruction(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.ConstantInstruction)"})
  void testVisitConstantInstruction_givenInvokeDynamicConstant() {
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
   * Test {@link StringConcatenationConverter#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}.
   * <ul>
   *   <li>Then calls {@link InvokeDynamicConstant#getBootstrapMethodAttributeIndex()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringConcatenationConverter#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  @DisplayName("Test visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction); then calls getBootstrapMethodAttributeIndex()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.backport.StringConcatenationConverter.visitConstantInstruction(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.ConstantInstruction)"})
  void testVisitConstantInstruction_thenCallsGetBootstrapMethodAttributeIndex() {
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
   * Test {@link StringConcatenationConverter#visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute)}.
   * <ul>
   *   <li>Given {@code Ref Class Name}.</li>
   *   <li>Then calls {@link ProgramClass#getRefClassName(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringConcatenationConverter#visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute)}
   */
  @Test
  @DisplayName("Test visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute); given 'Ref Class Name'; then calls getRefClassName(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.backport.StringConcatenationConverter.visitBootstrapMethodsAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.BootstrapMethodsAttribute)"})
  void testVisitBootstrapMethodsAttribute_givenRefClassName_thenCallsGetRefClassName() {
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
   * Test {@link StringConcatenationConverter#visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute)}.
   * <ul>
   *   <li>Then calls {@link BootstrapMethodsAttribute#bootstrapMethodEntryAccept(Clazz, int, BootstrapMethodInfoVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringConcatenationConverter#visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute)}
   */
  @Test
  @DisplayName("Test visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute); then calls bootstrapMethodEntryAccept(Clazz, int, BootstrapMethodInfoVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.backport.StringConcatenationConverter.visitBootstrapMethodsAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.BootstrapMethodsAttribute)"})
  void testVisitBootstrapMethodsAttribute_thenCallsBootstrapMethodEntryAccept() {
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
   * Test {@link StringConcatenationConverter#visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute)}.
   * <ul>
   *   <li>Then calls {@link MethodHandleConstant#getClassName(Clazz)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringConcatenationConverter#visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute)}
   */
  @Test
  @DisplayName("Test visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute); then calls getClassName(Clazz)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.backport.StringConcatenationConverter.visitBootstrapMethodsAttribute(proguard.classfile.Clazz, proguard.classfile.attribute.BootstrapMethodsAttribute)"})
  void testVisitBootstrapMethodsAttribute_thenCallsGetClassName() {
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
   * Test {@link StringConcatenationConverter#visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo)}.
   * <ul>
   *   <li>Then calls {@link MethodHandleConstant#getClassName(Clazz)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringConcatenationConverter#visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo)}
   */
  @Test
  @DisplayName("Test visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo); then calls getClassName(Clazz)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.backport.StringConcatenationConverter.visitBootstrapMethodInfo(proguard.classfile.Clazz, proguard.classfile.attribute.BootstrapMethodInfo)"})
  void testVisitBootstrapMethodInfo_thenCallsGetClassName() {
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

  /**
   * Test {@link StringConcatenationConverter#visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo)}.
   * <ul>
   *   <li>Then calls {@link ProgramClass#getRefClassName(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link StringConcatenationConverter#visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo)}
   */
  @Test
  @DisplayName("Test visitBootstrapMethodInfo(Clazz, BootstrapMethodInfo); then calls getRefClassName(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.backport.StringConcatenationConverter.visitBootstrapMethodInfo(proguard.classfile.Clazz, proguard.classfile.attribute.BootstrapMethodInfo)"})
  void testVisitBootstrapMethodInfo_thenCallsGetRefClassName() {
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
}
