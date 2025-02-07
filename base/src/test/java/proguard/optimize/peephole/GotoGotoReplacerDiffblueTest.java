package proguard.optimize.peephole;

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
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.editor.CodeAttributeEditor;
import proguard.classfile.instruction.BranchInstruction;
import proguard.classfile.instruction.Instruction;
import proguard.optimize.DuplicateInitializerInvocationFixer;
import proguard.optimize.evaluation.SimpleEnumUseChecker;

class GotoGotoReplacerDiffblueTest {
  /**
   * Test {@link GotoGotoReplacer#visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)}.
   * <p>
   * Method under test: {@link GotoGotoReplacer#visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)}
   */
  @Test
  @DisplayName("Test visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.GotoGotoReplacer.visitBranchInstruction(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.BranchInstruction)"})
  void testVisitBranchInstruction() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    when(codeAttributeEditor.isModified(anyInt())).thenReturn(false);
    doNothing().when(codeAttributeEditor).replaceInstruction(anyInt(), Mockito.<Instruction>any());
    GotoGotoReplacer gotoGotoReplacer = new GotoGotoReplacer(codeAttributeEditor,
        new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1, 3, 3, 3, new byte[]{'A', -89, 'A', -89, 'A', -89, 'A', -89});

    // Act
    gotoGotoReplacer.visitBranchInstruction(clazz, method, codeAttribute, 2, new BranchInstruction((byte) -89, 1));

    // Assert
    verify(codeAttributeEditor, atLeast(1)).isModified(anyInt());
    verify(codeAttributeEditor).replaceInstruction(eq(2), isA(Instruction.class));
  }

  /**
   * Test {@link GotoGotoReplacer#visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)}.
   * <p>
   * Method under test: {@link GotoGotoReplacer#visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)}
   */
  @Test
  @DisplayName("Test visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.GotoGotoReplacer.visitBranchInstruction(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.BranchInstruction)"})
  void testVisitBranchInstruction2() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    when(codeAttributeEditor.isModified(anyInt())).thenReturn(false);
    doNothing().when(codeAttributeEditor).replaceInstruction(anyInt(), Mockito.<Instruction>any());
    GotoGotoReplacer gotoGotoReplacer = new GotoGotoReplacer(codeAttributeEditor, new SimpleEnumUseChecker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1, 3, 3, 3, new byte[]{'A', -89, 'A', -89, 'A', -89, 'A', -89});

    // Act
    gotoGotoReplacer.visitBranchInstruction(clazz, method, codeAttribute, 2, new BranchInstruction((byte) -89, 1));

    // Assert
    verify(codeAttributeEditor, atLeast(1)).isModified(anyInt());
    verify(codeAttributeEditor).replaceInstruction(eq(2), isA(Instruction.class));
  }

  /**
   * Test {@link GotoGotoReplacer#visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)}.
   * <ul>
   *   <li>Given {@link CodeAttributeEditor} {@link CodeAttributeEditor#isModified(int)} return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GotoGotoReplacer#visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)}
   */
  @Test
  @DisplayName("Test visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction); given CodeAttributeEditor isModified(int) return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.GotoGotoReplacer.visitBranchInstruction(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.BranchInstruction)"})
  void testVisitBranchInstruction_givenCodeAttributeEditorIsModifiedReturnTrue() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    when(codeAttributeEditor.isModified(anyInt())).thenReturn(true);
    GotoGotoReplacer gotoGotoReplacer = new GotoGotoReplacer(codeAttributeEditor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    gotoGotoReplacer.visitBranchInstruction(clazz, method, codeAttribute, 2, new BranchInstruction((byte) -89, 1));

    // Assert
    verify(codeAttributeEditor).isModified(eq(2));
  }

  /**
   * Test {@link GotoGotoReplacer#visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)}.
   * <ul>
   *   <li>Then calls {@link CodeAttributeEditor#replaceInstruction(int, Instruction)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GotoGotoReplacer#visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)}
   */
  @Test
  @DisplayName("Test visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction); then calls replaceInstruction(int, Instruction)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.GotoGotoReplacer.visitBranchInstruction(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.BranchInstruction)"})
  void testVisitBranchInstruction_thenCallsReplaceInstruction() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    doNothing().when(codeAttributeEditor).replaceInstruction(anyInt(), Mockito.<Instruction>any());
    when(codeAttributeEditor.isModified(anyInt())).thenReturn(false);
    GotoGotoReplacer gotoGotoReplacer = new GotoGotoReplacer(codeAttributeEditor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1, 3, 3, 3, new byte[]{'A', -89, 'A', -89, 'A', -89, 'A', -89});

    // Act
    gotoGotoReplacer.visitBranchInstruction(clazz, method, codeAttribute, 2, new BranchInstruction((byte) -89, 1));

    // Assert
    verify(codeAttributeEditor, atLeast(1)).isModified(anyInt());
    verify(codeAttributeEditor).replaceInstruction(eq(2), isA(Instruction.class));
  }

  /**
   * Test {@link GotoGotoReplacer#visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)}.
   * <ul>
   *   <li>When {@code A}.</li>
   *   <li>Then calls {@link CodeAttributeEditor#isModified(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GotoGotoReplacer#visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)}
   */
  @Test
  @DisplayName("Test visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction); when 'A'; then calls isModified(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.GotoGotoReplacer.visitBranchInstruction(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.BranchInstruction)"})
  void testVisitBranchInstruction_whenA_thenCallsIsModified() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    when(codeAttributeEditor.isModified(anyInt())).thenReturn(false);
    GotoGotoReplacer gotoGotoReplacer = new GotoGotoReplacer(codeAttributeEditor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1, 3, 3, 3, new byte[]{'A', -89, 'A', 'A', 'A', -89, 'A', -89});

    // Act
    gotoGotoReplacer.visitBranchInstruction(clazz, method, codeAttribute, 2, new BranchInstruction((byte) -89, 1));

    // Assert
    verify(codeAttributeEditor, atLeast(1)).isModified(anyInt());
  }

  /**
   * Test {@link GotoGotoReplacer#visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)}.
   * <ul>
   *   <li>When {@code A}.</li>
   *   <li>Then calls {@link CodeAttributeEditor#isModified(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GotoGotoReplacer#visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)}
   */
  @Test
  @DisplayName("Test visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction); when 'A'; then calls isModified(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.peephole.GotoGotoReplacer.visitBranchInstruction(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.BranchInstruction)"})
  void testVisitBranchInstruction_whenA_thenCallsIsModified2() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    when(codeAttributeEditor.isModified(anyInt())).thenReturn(false);
    GotoGotoReplacer gotoGotoReplacer = new GotoGotoReplacer(codeAttributeEditor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1, 3, 3, 3, new byte[]{'A', -89, 'A', 1, 'A', -89, 'A', -89});

    // Act
    gotoGotoReplacer.visitBranchInstruction(clazz, method, codeAttribute, 2, new BranchInstruction((byte) -89, 1));

    // Assert
    verify(codeAttributeEditor, atLeast(1)).isModified(anyInt());
  }
}
