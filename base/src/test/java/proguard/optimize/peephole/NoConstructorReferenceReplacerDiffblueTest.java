package proguard.optimize.peephole;

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
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;
import proguard.classfile.editor.CodeAttributeEditor;
import proguard.classfile.instruction.ConstantInstruction;
import proguard.classfile.instruction.Instruction;
import proguard.optimize.info.ClassOptimizationInfo;

class NoConstructorReferenceReplacerDiffblueTest {
  /**
   * Method under test:
   * {@link NoConstructorReferenceReplacer#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  void testVisitConstantInstruction() {
    // Arrange
    CodeAttributeEditor codeAttributeEditor = mock(CodeAttributeEditor.class);
    doNothing().when(codeAttributeEditor).replaceInstruction(anyInt(), Mockito.<Instruction[]>any());
    NoConstructorReferenceReplacer noConstructorReferenceReplacer = new NoConstructorReferenceReplacer(
        codeAttributeEditor);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    noConstructorReferenceReplacer.visitConstantInstruction(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) -76, 1));

    // Assert
    verify(codeAttributeEditor).replaceInstruction(eq(2), isA(Instruction[].class));
  }

  /**
   * Method under test:
   * {@link NoConstructorReferenceReplacer#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  void testVisitAnyMethodrefConstant() {
    // Arrange
    NoConstructorReferenceReplacer noConstructorReferenceReplacer = new NoConstructorReferenceReplacer(
        new CodeAttributeEditor());
    LibraryClass clazz = new LibraryClass();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.containsConstructors()).thenReturn(true);

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.setProcessingInfo(classOptimizationInfo);
    InterfaceMethodrefConstant anyMethodrefConstant = new InterfaceMethodrefConstant();
    anyMethodrefConstant.referencedClass = libraryClass;

    // Act
    noConstructorReferenceReplacer.visitAnyMethodrefConstant(clazz, anyMethodrefConstant);

    // Assert
    verify(classOptimizationInfo).containsConstructors();
  }
}
