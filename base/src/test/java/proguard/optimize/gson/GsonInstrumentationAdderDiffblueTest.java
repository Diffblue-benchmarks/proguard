package proguard.optimize.gson;

import static org.mockito.ArgumentMatchers.isA;
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
import proguard.classfile.ClassPool;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.editor.CodeAttributeEditor;
import proguard.classfile.instruction.Instruction;

class GsonInstrumentationAdderDiffblueTest {
  /**
   * Test {@link GsonInstrumentationAdder#visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)}.
   * <ul>
   *   <li>Given {@code A}.</li>
   *   <li>When {@link Clazz}.</li>
   *   <li>Then calls {@link ClassPool#addClass(Clazz)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link GsonInstrumentationAdder#visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction); given 'A'; when Clazz; then calls addClass(Clazz)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void GsonInstrumentationAdder.visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)"})
  void testVisitAnyInstruction_givenA_whenClazz_thenCallsAddClass() {
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
