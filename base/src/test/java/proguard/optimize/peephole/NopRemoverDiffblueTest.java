package proguard.optimize.peephole;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.editor.CodeAttributeEditor;
import proguard.classfile.instruction.SimpleInstruction;

class NopRemoverDiffblueTest {
  /**
   * Method under test:
   * {@link NopRemover#visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)}
   */
  @Test
  void testVisitSimpleInstruction() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    when(codeAttributeEditor.isModified(anyInt())).thenReturn(true);
    NopRemover nopRemover = new NopRemover(codeAttributeEditor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    nopRemover.visitSimpleInstruction(clazz, method, codeAttribute, 2, new SimpleInstruction((byte) 0));

    // Assert
    verify(codeAttributeEditor).isModified(eq(2));
  }

  /**
   * Method under test:
   * {@link NopRemover#visitSimpleInstruction(Clazz, Method, CodeAttribute, int, SimpleInstruction)}
   */
  @Test
  void testVisitSimpleInstruction2() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    when(codeAttributeEditor.isModified(anyInt())).thenReturn(false);
    doNothing().when(codeAttributeEditor).deleteInstruction(anyInt());
    NopRemover nopRemover = new NopRemover(codeAttributeEditor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    nopRemover.visitSimpleInstruction(clazz, method, codeAttribute, 2, new SimpleInstruction((byte) 0));

    // Assert
    verify(codeAttributeEditor).deleteInstruction(eq(2));
    verify(codeAttributeEditor).isModified(eq(2));
  }
}
