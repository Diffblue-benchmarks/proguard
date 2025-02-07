package proguard.optimize.info;

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
import proguard.classfile.Clazz;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMember;
import proguard.classfile.ProgramMethod;
import proguard.classfile.visitor.MemberVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;

class UnusedParameterMethodFilterDiffblueTest {
  /**
   * Test {@link UnusedParameterMethodFilter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link UnusedParameterMethodFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given MethodOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.UnusedParameterMethodFilter.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_givenMethodOptimizationInfo() {
    // Arrange
    UnusedParameterMethodFilter unusedParameterMethodFilter = new UnusedParameterMethodFilter(
        new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    unusedParameterMethodFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link UnusedParameterMethodFilter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Then calls {@link MemberVisitor#visitProgramMethod(ProgramClass, ProgramMethod)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UnusedParameterMethodFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); then calls visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.UnusedParameterMethodFilter.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_thenCallsVisitProgramMethod() {
    // Arrange
    MemberVisitor memberVisitor = mock(MemberVisitor.class);
    doNothing().when(memberVisitor).visitProgramMethod(Mockito.<ProgramClass>any(), Mockito.<ProgramMethod>any());
    UnusedParameterMethodFilter unusedParameterMethodFilter = new UnusedParameterMethodFilter(memberVisitor);
    ProgramClass programClass = new ProgramClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.hasUnusedParameters()).thenReturn(true);
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    unusedParameterMethodFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(memberVisitor).visitProgramMethod(isA(ProgramClass.class), isA(ProgramMethod.class));
    verify(methodOptimizationInfo).hasUnusedParameters();
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link UnusedParameterMethodFilter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>When {@link ProgramMethod} {@link ProgramMember#accept(Clazz, MemberVisitor)} does nothing.</li>
   *   <li>Then calls {@link ProgramMember#accept(Clazz, MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link UnusedParameterMethodFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); when ProgramMethod accept(Clazz, MemberVisitor) does nothing; then calls accept(Clazz, MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.UnusedParameterMethodFilter.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_whenProgramMethodAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    UnusedParameterMethodFilter unusedParameterMethodFilter = new UnusedParameterMethodFilter(
        new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.hasUnusedParameters()).thenReturn(true);
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doNothing().when(programMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    when(programMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    unusedParameterMethodFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(methodOptimizationInfo).hasUnusedParameters();
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }
}
