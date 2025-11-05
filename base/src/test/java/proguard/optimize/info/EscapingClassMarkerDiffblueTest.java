package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
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
import proguard.classfile.ProgramClass;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.instruction.BranchInstruction;
import proguard.classfile.instruction.Instruction;

class EscapingClassMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link EscapingClassMarker#visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  void testVisitAnyInstruction() {
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
   * Method under test:
   * {@link EscapingClassMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass() {
    // Arrange
    EscapingClassMarker escapingClassMarker = new EscapingClassMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());

    // Act
    escapingClassMarker.visitProgramClass(programClass);

    // Assert
    verify(programClass).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link EscapingClassMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass2() {
    // Arrange
    EscapingClassMarker escapingClassMarker = new EscapingClassMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());

    // Act
    escapingClassMarker.visitProgramClass(programClass);

    // Assert
    verify(programClass).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link EscapingClassMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  void testVisitProgramClass3() {
    // Arrange
    EscapingClassMarker escapingClassMarker = new EscapingClassMarker();
    ProgramClassOptimizationInfo programClassOptimizationInfo = mock(ProgramClassOptimizationInfo.class);
    doNothing().when(programClassOptimizationInfo).setEscaping();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(programClassOptimizationInfo);

    // Act
    escapingClassMarker.visitProgramClass(programClass);

    // Assert
    verify(programClassOptimizationInfo).setEscaping();
    verify(programClass).getProcessingInfo();
  }

  /**
   * Method under test: {@link EscapingClassMarker#isClassEscaping(Clazz)}
   */
  @Test
  void testIsClassEscaping() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ClassOptimizationInfo());

    // Act and Assert
    assertTrue(EscapingClassMarker.isClassEscaping(clazz));
  }

  /**
   * Method under test: {@link EscapingClassMarker#isClassEscaping(Clazz)}
   */
  @Test
  void testIsClassEscaping2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    clazz.setProcessingInfo(new ProgramClassOptimizationInfo());

    // Act and Assert
    assertFalse(EscapingClassMarker.isClassEscaping(clazz));
  }
}
