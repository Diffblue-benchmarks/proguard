package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.instruction.BranchInstruction;
import proguard.classfile.instruction.LookUpSwitchInstruction;
import proguard.classfile.instruction.SwitchInstruction;

class BackwardBranchMarkerDiffblueTest {
  /**
   * Test {@link BackwardBranchMarker#visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)}.
   * <ul>
   *   <li>Then calls {@link ProgramMethodOptimizationInfo#setBranchesBackward()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BackwardBranchMarker#visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)}
   */
  @Test
  @DisplayName("Test visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction); then calls setBranchesBackward()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.BackwardBranchMarker.visitBranchInstruction(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.BranchInstruction)"})
  void testVisitBranchInstruction_thenCallsSetBranchesBackward() {
    // Arrange
    BackwardBranchMarker backwardBranchMarker = new BackwardBranchMarker();
    LibraryClass clazz = new LibraryClass();
    ProgramMethodOptimizationInfo programMethodOptimizationInfo = mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setBranchesBackward();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);
    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    backwardBranchMarker.visitBranchInstruction(clazz, method, codeAttribute, 2, new BranchInstruction((byte) 'A', -1));

    // Assert
    verify(programMethodOptimizationInfo).setBranchesBackward();
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link BackwardBranchMarker#visitAnySwitchInstruction(Clazz, Method, CodeAttribute, int, SwitchInstruction)}.
   * <ul>
   *   <li>Then calls {@link ProgramMethodOptimizationInfo#setBranchesBackward()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BackwardBranchMarker#visitAnySwitchInstruction(Clazz, Method, CodeAttribute, int, SwitchInstruction)}
   */
  @Test
  @DisplayName("Test visitAnySwitchInstruction(Clazz, Method, CodeAttribute, int, SwitchInstruction); then calls setBranchesBackward()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.BackwardBranchMarker.visitAnySwitchInstruction(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.SwitchInstruction)"})
  void testVisitAnySwitchInstruction_thenCallsSetBranchesBackward() {
    // Arrange
    BackwardBranchMarker backwardBranchMarker = new BackwardBranchMarker();
    LibraryClass clazz = new LibraryClass();
    ProgramMethodOptimizationInfo programMethodOptimizationInfo = mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setBranchesBackward();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);
    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    backwardBranchMarker.visitAnySwitchInstruction(clazz, method, codeAttribute, 2,
        new LookUpSwitchInstruction((byte) 'A', -1, new int[]{1, 0, 1, 0}, new int[]{1, 0, 1, 0}));

    // Assert
    verify(programMethodOptimizationInfo).setBranchesBackward();
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link BackwardBranchMarker#branchesBackward(Method)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link BackwardBranchMarker#branchesBackward(Method)}
   */
  @Test
  @DisplayName("Test branchesBackward(Method); given MethodOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.optimize.info.BackwardBranchMarker.branchesBackward(proguard.classfile.Method)"})
  void testBranchesBackward_givenMethodOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(BackwardBranchMarker.branchesBackward(method));
  }
}
