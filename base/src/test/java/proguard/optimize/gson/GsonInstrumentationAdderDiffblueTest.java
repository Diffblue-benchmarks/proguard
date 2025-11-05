package proguard.optimize.gson;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.editor.CodeAttributeEditor;
import proguard.classfile.instruction.Instruction;

class GsonInstrumentationAdderDiffblueTest {
  /**
   * Method under test:
   * {@link GsonInstrumentationAdder#visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  void testVisitAnyInstruction() {
    // Arrange
    ClassPool programClassPool = mock(ClassPool.class);
    doNothing().when(programClassPool).addClass(Mockito.<Clazz>any());
    programClassPool.addClass(new LibraryClass());
    ClassPool libraryClassPool = new ClassPool();
    GsonInstrumentationAdder gsonInstrumentationAdder = new GsonInstrumentationAdder(programClassPool, libraryClassPool,
        new CodeAttributeEditor());
    Clazz clazz = mock(Clazz.class);
    Method method = mock(Method.class);
    CodeAttribute codeAttribute = new CodeAttribute(1);
    Instruction instruction = mock(Instruction.class);
    when(instruction.actualOpcode()).thenReturn((byte) 'A');

    // Act
    gsonInstrumentationAdder.visitAnyInstruction(clazz, method, codeAttribute, 2, instruction);

    // Assert
    verify(programClassPool).addClass(isA(Clazz.class));
    verify(instruction, atLeast(1)).actualOpcode();
  }
}
