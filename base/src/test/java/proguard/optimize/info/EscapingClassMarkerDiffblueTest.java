package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
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
import proguard.classfile.instruction.BranchInstruction;
import proguard.classfile.instruction.Instruction;

class EscapingClassMarkerDiffblueTest {
  /**
   * Test {@link EscapingClassMarker#visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)}.
   * <ul>
   *   <li>Given three.</li>
   *   <li>Then calls {@link Instruction#stackPushCount(Clazz)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EscapingClassMarker#visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction); given three; then calls stackPushCount(Clazz)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.EscapingClassMarker.visitAnyInstruction(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.Instruction)"})
  void testVisitAnyInstruction_givenThree_thenCallsStackPushCount() {
    // Arrange
    EscapingClassMarker escapingClassMarker = new EscapingClassMarker();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    BranchInstruction instruction = mock(BranchInstruction.class);
    when(instruction.stackPushCount(Mockito.<Clazz>any())).thenReturn(3);

    // Act
    escapingClassMarker.visitAnyInstruction(clazz, method, codeAttribute, 2, instruction);

    // Assert
    verify(instruction).stackPushCount(isA(Clazz.class));
  }

  /**
   * Test {@link EscapingClassMarker#isClassEscaping(Clazz)}.
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EscapingClassMarker#isClassEscaping(Clazz)}
   */
  @Test
  @DisplayName("Test isClassEscaping(Clazz); given ClassOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.info.EscapingClassMarker.isClassEscaping(proguard.classfile.Clazz)"})
  void testIsClassEscaping_givenClassOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ClassOptimizationInfo());

    // Act and Assert
    assertTrue(EscapingClassMarker.isClassEscaping(clazz));
  }

  /**
   * Test {@link EscapingClassMarker#isClassEscaping(Clazz)}.
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link EscapingClassMarker#isClassEscaping(Clazz)}
   */
  @Test
  @DisplayName("Test isClassEscaping(Clazz); given ProgramClassOptimizationInfo (default constructor); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.info.EscapingClassMarker.isClassEscaping(proguard.classfile.Clazz)"})
  void testIsClassEscaping_givenProgramClassOptimizationInfo_thenReturnFalse() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ProgramClassOptimizationInfo());

    // Act and Assert
    assertFalse(EscapingClassMarker.isClassEscaping(clazz));
  }
}
