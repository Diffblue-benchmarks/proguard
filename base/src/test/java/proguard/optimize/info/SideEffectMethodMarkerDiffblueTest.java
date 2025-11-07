package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
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
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.instruction.BranchInstruction;
import proguard.classfile.instruction.Instruction;
import proguard.classfile.instruction.visitor.InstructionVisitor;

class SideEffectMethodMarkerDiffblueTest {
  /**
   * Test {@link SideEffectMethodMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectMethodMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given MethodOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SideEffectMethodMarker.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenMethodOptimizationInfo() {
    // Arrange
    SideEffectMethodMarker sideEffectMethodMarker = new SideEffectMethodMarker(true);
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).addSubClass(Mockito.<Clazz>any());
    programClass.addSubClass(new LibraryClass());

    ProgramMethod programMethod = new ProgramMethod(288, 1, 1, new Clazz[]{new LibraryClass()});
    programMethod.setProcessingInfo(new MethodOptimizationInfo());

    // Act
    sideEffectMethodMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programClass).addSubClass(isA(Clazz.class));
  }

  /**
   * Test {@link SideEffectMethodMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} {@link MethodOptimizationInfo#hasSideEffects()} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectMethodMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given MethodOptimizationInfo hasSideEffects() return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SideEffectMethodMarker.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenMethodOptimizationInfoHasSideEffectsReturnFalse() {
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
   * Test {@link SideEffectMethodMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} {@link MethodOptimizationInfo#hasSideEffects()} return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectMethodMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given MethodOptimizationInfo hasSideEffects() return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SideEffectMethodMarker.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenMethodOptimizationInfoHasSideEffectsReturnTrue() {
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
   * Test {@link SideEffectMethodMarker#visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)}.
   * <ul>
   *   <li>When {@link BranchInstruction} {@link BranchInstruction#accept(Clazz, Method, CodeAttribute, int, InstructionVisitor)} does nothing.</li>
   *   <li>Then calls {@link BranchInstruction#accept(Clazz, Method, CodeAttribute, int, InstructionVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectMethodMarker#visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)}
   */
  @Test
  @DisplayName("Test visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction); when BranchInstruction accept(Clazz, Method, CodeAttribute, int, InstructionVisitor) does nothing; then calls accept(Clazz, Method, CodeAttribute, int, InstructionVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void SideEffectMethodMarker.visitAnyInstruction(Clazz, Method, CodeAttribute, int, Instruction)"})
  void testVisitAnyInstruction_whenBranchInstructionAcceptDoesNothing_thenCallsAccept() {
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
   * Test {@link SideEffectMethodMarker#hasSideEffects(Method)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SideEffectMethodMarker#hasSideEffects(Method)}
   */
  @Test
  @DisplayName("Test hasSideEffects(Method); given MethodOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SideEffectMethodMarker.hasSideEffects(Method)"})
  void testHasSideEffects_givenMethodOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(SideEffectMethodMarker.hasSideEffects(method));
  }
}
