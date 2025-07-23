package proguard.optimize.peephole;

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
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.editor.CodeAttributeEditor;
import proguard.classfile.instruction.SimpleInstruction;
import proguard.classfile.instruction.visitor.InstructionConstantVisitor;
import proguard.optimize.DuplicateInitializerInvocationFixer;

class NopRemoverDiffblueTest {
  /**
   * Test {@link NopRemover#visitSimpleInstruction(Clazz, Method, CodeAttribute, int,
   * SimpleInstruction)}.
   *
   * <p>Method under test: {@link NopRemover#visitSimpleInstruction(Clazz, Method, CodeAttribute,
   * int, SimpleInstruction)}
   */
  @Test
  @DisplayName("Test visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NopRemover.visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)"
  })
  void testVisitSimpleInstruction() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    when(codeAttributeEditor.isModified(anyInt())).thenReturn(false);
    doNothing().when(codeAttributeEditor).deleteInstruction(anyInt());
    NopRemover nopRemover =
        new NopRemover(codeAttributeEditor, new DuplicateInitializerInvocationFixer());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    nopRemover.visitSimpleInstruction(
        clazz, method, codeAttribute, 0, new SimpleInstruction((byte) 0));

    // Assert
    verify(codeAttributeEditor).deleteInstruction(eq(0));
    verify(codeAttributeEditor).isModified(eq(0));
  }

  /**
   * Test {@link NopRemover#visitSimpleInstruction(Clazz, Method, CodeAttribute, int,
   * SimpleInstruction)}.
   *
   * <ul>
   *   <li>Given {@link CodeAttributeEditor} {@link CodeAttributeEditor#isModified(int)} return
   *       {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link NopRemover#visitSimpleInstruction(Clazz, Method, CodeAttribute,
   * int, SimpleInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction); given CodeAttributeEditor isModified(int) return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NopRemover.visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)"
  })
  void testVisitSimpleInstruction_givenCodeAttributeEditorIsModifiedReturnTrue() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    when(codeAttributeEditor.isModified(anyInt())).thenReturn(true);
    NopRemover nopRemover = new NopRemover(codeAttributeEditor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    nopRemover.visitSimpleInstruction(
        clazz, method, codeAttribute, 0, new SimpleInstruction((byte) 0));

    // Assert
    verify(codeAttributeEditor).isModified(eq(0));
  }

  /**
   * Test {@link NopRemover#visitSimpleInstruction(Clazz, Method, CodeAttribute, int,
   * SimpleInstruction)}.
   *
   * <ul>
   *   <li>Given {@link NopRemover#NopRemover(CodeAttributeEditor)} with {@link
   *       CodeAttributeEditor}.
   * </ul>
   *
   * <p>Method under test: {@link NopRemover#visitSimpleInstruction(Clazz, Method, CodeAttribute,
   * int, SimpleInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction); given NopRemover(CodeAttributeEditor) with CodeAttributeEditor")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NopRemover.visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)"
  })
  void testVisitSimpleInstruction_givenNopRemoverWithCodeAttributeEditor() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    when(codeAttributeEditor.isModified(anyInt())).thenReturn(false);
    doNothing().when(codeAttributeEditor).deleteInstruction(anyInt());
    NopRemover nopRemover = new NopRemover(codeAttributeEditor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    nopRemover.visitSimpleInstruction(
        clazz, method, codeAttribute, 0, new SimpleInstruction((byte) 0));

    // Assert
    verify(codeAttributeEditor).deleteInstruction(eq(0));
    verify(codeAttributeEditor).isModified(eq(0));
  }

  /**
   * Test {@link NopRemover#visitSimpleInstruction(Clazz, Method, CodeAttribute, int,
   * SimpleInstruction)}.
   *
   * <ul>
   *   <li>Then calls {@link InstructionConstantVisitor#visitSimpleInstruction(Clazz, Method,
   *       CodeAttribute, int, SimpleInstruction)}.
   * </ul>
   *
   * <p>Method under test: {@link NopRemover#visitSimpleInstruction(Clazz, Method, CodeAttribute,
   * int, SimpleInstruction)}
   */
  @Test
  @DisplayName(
      "Test visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction); then calls visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NopRemover.visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)"
  })
  void testVisitSimpleInstruction_thenCallsVisitSimpleInstruction() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    when(codeAttributeEditor.isModified(anyInt())).thenReturn(false);
    doNothing().when(codeAttributeEditor).deleteInstruction(anyInt());
    InstructionConstantVisitor extraInstructionVisitor = mock(InstructionConstantVisitor.class);
    doNothing()
        .when(extraInstructionVisitor)
        .visitSimpleInstruction(
            Mockito.<Clazz>any(),
            Mockito.<Method>any(),
            Mockito.<CodeAttribute>any(),
            anyInt(),
            Mockito.<SimpleInstruction>any());
    NopRemover nopRemover = new NopRemover(codeAttributeEditor, extraInstructionVisitor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    nopRemover.visitSimpleInstruction(
        clazz, method, codeAttribute, 0, new SimpleInstruction((byte) 0));

    // Assert
    verify(codeAttributeEditor).deleteInstruction(eq(0));
    verify(codeAttributeEditor).isModified(eq(0));
    verify(extraInstructionVisitor)
        .visitSimpleInstruction(
            isA(Clazz.class),
            isA(Method.class),
            isA(CodeAttribute.class),
            eq(0),
            isA(SimpleInstruction.class));
  }
}
