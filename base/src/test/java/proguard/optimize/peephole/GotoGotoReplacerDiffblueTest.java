package proguard.optimize.peephole;

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
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.editor.CodeAttributeEditor;
import proguard.classfile.instruction.BranchInstruction;
import proguard.classfile.instruction.Instruction;
import proguard.optimize.DuplicateInitializerInvocationFixer;

class GotoGotoReplacerDiffblueTest {
  /**
   * Method under test:
   * {@link GotoGotoReplacer#visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)}
   */
  @Test
  void testVisitBranchInstruction() {
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
   * Method under test:
   * {@link GotoGotoReplacer#visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)}
   */
  @Test
  void testVisitBranchInstruction2() {
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
   * Method under test:
   * {@link GotoGotoReplacer#visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)}
   */
  @Test
  void testVisitBranchInstruction3() {
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
   * Method under test:
   * {@link GotoGotoReplacer#visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)}
   */
  @Test
  void testVisitBranchInstruction4() {
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

  /**
   * Method under test:
   * {@link GotoGotoReplacer#visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)}
   */
  @Test
  void testVisitBranchInstruction5() {
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
}
