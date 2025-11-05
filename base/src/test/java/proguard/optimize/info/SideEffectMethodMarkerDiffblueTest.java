package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertTrue;
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
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.instruction.BranchInstruction;
import proguard.classfile.instruction.Instruction;
import proguard.classfile.instruction.visitor.InstructionVisitor;

class SideEffectMethodMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link SideEffectMethodMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
    // Arrange
    SideEffectMethodMarker sideEffectMethodMarker = new SideEffectMethodMarker(true);
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).addSubClass(Mockito.<Clazz>any());
    programClass.addSubClass(new LibraryClass());

    ProgramMethod programMethod = new ProgramMethod(288, 1, 1, new Clazz[]{new LibraryClass()});
    programMethod.setProcessingInfo(new MethodOptimizationInfo());

    // Act
    sideEffectMethodMarker.visitProgramMethod(programClass, programMethod);

    // Assert that nothing has changed
    verify(programClass).addSubClass(isA(Clazz.class));
  }

  /**
   * Method under test:
   * {@link SideEffectMethodMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod2() {
    // Arrange
    SideEffectMethodMarker sideEffectMethodMarker = new SideEffectMethodMarker(true);
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).addSubClass(Mockito.<Clazz>any());
    programClass.addSubClass(new LibraryClass());
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.hasSideEffects()).thenReturn(true);

    ProgramMethod programMethod = new ProgramMethod(288, 1, 1, new Clazz[]{new LibraryClass()});
    programMethod.setProcessingInfo(methodOptimizationInfo);

    // Act
    sideEffectMethodMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programClass).addSubClass(isA(Clazz.class));
    verify(methodOptimizationInfo).hasSideEffects();
  }

  /**
   * Method under test:
   * {@link SideEffectMethodMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod3() {
    // Arrange
    SideEffectMethodMarker sideEffectMethodMarker = new SideEffectMethodMarker(true);
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).addSubClass(Mockito.<Clazz>any());
    programClass.addSubClass(new LibraryClass());
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.hasSideEffects()).thenReturn(false);

    ProgramMethod programMethod = new ProgramMethod(288, 1, 1, new Clazz[]{new LibraryClass()});
    programMethod.setProcessingInfo(methodOptimizationInfo);

    // Act
    sideEffectMethodMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programClass).addSubClass(isA(Clazz.class));
    verify(methodOptimizationInfo).hasSideEffects();
  }

  /**
   * Method under test:
   * {@link SideEffectMethodMarker#visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  void testVisitAnyInstruction() {
    // Arrange
    SideEffectMethodMarker sideEffectMethodMarker = new SideEffectMethodMarker(true);
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    BranchInstruction instruction = mock(BranchInstruction.class);
    doNothing().when(instruction)
        .accept(Mockito.<Clazz>any(), Mockito.<Method>any(), Mockito.<CodeAttribute>any(), anyInt(),
            Mockito.<InstructionVisitor>any());

    // Act
    sideEffectMethodMarker.visitAnyInstruction(clazz, method, codeAttribute, 2, instruction);

    // Assert
    verify(instruction).accept(isA(Clazz.class), isA(Method.class), isA(CodeAttribute.class), eq(2),
        isA(InstructionVisitor.class));
  }

  /**
   * Method under test: {@link SideEffectMethodMarker#hasSideEffects(Method)}
   */
  @Test
  void testHasSideEffects() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(SideEffectMethodMarker.hasSideEffects(method));
  }
}
