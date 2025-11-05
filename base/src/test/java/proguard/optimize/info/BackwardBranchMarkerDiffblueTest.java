package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
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
import proguard.classfile.instruction.BranchInstruction;
import proguard.classfile.instruction.LookUpSwitchInstruction;
import proguard.classfile.instruction.SwitchInstruction;

class BackwardBranchMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link BackwardBranchMarker#visitBranchInstruction(Clazz, Method, CodeAttribute, int, BranchInstruction)}
   */
  @Test
  void testVisitBranchInstruction() {
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
   * Method under test:
   * {@link BackwardBranchMarker#visitAnySwitchInstruction(Clazz, Method, CodeAttribute, int, SwitchInstruction)}
   */
  @Test
  void testVisitAnySwitchInstruction() {
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
   * Method under test: {@link BackwardBranchMarker#branchesBackward(Method)}
   */
  @Test
  void testBranchesBackward() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(BackwardBranchMarker.branchesBackward(method));
  }
}
