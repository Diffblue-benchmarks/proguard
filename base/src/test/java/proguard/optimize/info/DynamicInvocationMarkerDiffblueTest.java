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
import proguard.classfile.instruction.ConstantInstruction;

class DynamicInvocationMarkerDiffblueTest {
  /**
   * Test {@link DynamicInvocationMarker#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}.
   * <ul>
   *   <li>Then calls {@link ProgramMethodOptimizationInfo#setInvokesDynamically()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamicInvocationMarker#visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction)}
   */
  @Test
  @DisplayName("Test visitConstantInstruction(Clazz, Method, CodeAttribute, int, ConstantInstruction); then calls setInvokesDynamically()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.DynamicInvocationMarker.visitConstantInstruction(proguard.classfile.Clazz, proguard.classfile.Method, proguard.classfile.attribute.CodeAttribute, int, proguard.classfile.instruction.ConstantInstruction)"})
  void testVisitConstantInstruction_thenCallsSetInvokesDynamically() {
    // Arrange
    DynamicInvocationMarker dynamicInvocationMarker = new DynamicInvocationMarker();
    LibraryClass clazz = new LibraryClass();
    ProgramMethodOptimizationInfo programMethodOptimizationInfo = mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).setInvokesDynamically();
    Method method = mock(Method.class);
    when(method.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);
    CodeAttribute codeAttribute = new CodeAttribute(1);

    // Act
    dynamicInvocationMarker.visitConstantInstruction(clazz, method, codeAttribute, 2,
        new ConstantInstruction((byte) -70, 1));

    // Assert
    verify(programMethodOptimizationInfo).setInvokesDynamically();
    verify(method, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link DynamicInvocationMarker#invokesDynamically(Method)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link DynamicInvocationMarker#invokesDynamically(Method)}
   */
  @Test
  @DisplayName("Test invokesDynamically(Method); given MethodOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.DynamicInvocationMarker.invokesDynamically(proguard.classfile.Method)"})
  void testInvokesDynamically_givenMethodOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(DynamicInvocationMarker.invokesDynamically(method));
  }
}
