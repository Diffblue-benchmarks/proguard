package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;
import proguard.classfile.constant.StringConstant;
import proguard.classfile.visitor.MemberVisitor;

class MethodInvocationMarkerDiffblueTest {
  /**
   * Test {@link MethodInvocationMarker#visitStringConstant(Clazz, StringConstant)}.
   * <ul>
   *   <li>Then calls {@link ProgramMethodOptimizationInfo#incrementInvocationCount()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodInvocationMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant); then calls incrementInvocationCount()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MethodInvocationMarker.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant_thenCallsIncrementInvocationCount() {
    // Arrange
    MethodInvocationMarker methodInvocationMarker = new MethodInvocationMarker();
    LibraryClass clazz = new LibraryClass();
    ProgramMethodOptimizationInfo programMethodOptimizationInfo = mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).incrementInvocationCount();

    ProgramMethod programMethod = new ProgramMethod();
    programMethod.setProcessingInfo(programMethodOptimizationInfo);
    StringConstant stringConstant = new StringConstant();
    stringConstant.referencedMember = programMethod;

    // Act
    methodInvocationMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(programMethodOptimizationInfo).incrementInvocationCount();
  }

  /**
   * Test {@link MethodInvocationMarker#visitStringConstant(Clazz, StringConstant)}.
   * <ul>
   *   <li>Then calls {@link StringConstant#referencedMemberAccept(MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodInvocationMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant); then calls referencedMemberAccept(MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MethodInvocationMarker.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant_thenCallsReferencedMemberAccept() {
    // Arrange
    MethodInvocationMarker methodInvocationMarker = new MethodInvocationMarker();
    LibraryClass clazz = new LibraryClass();
    StringConstant stringConstant = mock(StringConstant.class);
    doNothing().when(stringConstant).referencedMemberAccept(Mockito.<MemberVisitor>any());

    // Act
    methodInvocationMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(stringConstant).referencedMemberAccept(isA(MemberVisitor.class));
  }

  /**
   * Test {@link MethodInvocationMarker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   * <ul>
   *   <li>Then calls {@link ProgramMethodOptimizationInfo#incrementInvocationCount()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodInvocationMarker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant); then calls incrementInvocationCount()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MethodInvocationMarker.visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)"})
  void testVisitAnyMethodrefConstant_thenCallsIncrementInvocationCount() {
    // Arrange
    MethodInvocationMarker methodInvocationMarker = new MethodInvocationMarker();
    LibraryClass clazz = new LibraryClass();
    ProgramMethodOptimizationInfo programMethodOptimizationInfo = mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).incrementInvocationCount();

    ProgramMethod programMethod = new ProgramMethod();
    programMethod.setProcessingInfo(programMethodOptimizationInfo);
    InterfaceMethodrefConstant anyMethodrefConstant = new InterfaceMethodrefConstant();
    anyMethodrefConstant.referencedMethod = programMethod;

    // Act
    methodInvocationMarker.visitAnyMethodrefConstant(clazz, anyMethodrefConstant);

    // Assert
    verify(programMethodOptimizationInfo).incrementInvocationCount();
  }

  /**
   * Test {@link MethodInvocationMarker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   * <ul>
   *   <li>Then calls {@link AnyMethodrefConstant#referencedMethodAccept(MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodInvocationMarker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant); then calls referencedMethodAccept(MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MethodInvocationMarker.visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)"})
  void testVisitAnyMethodrefConstant_thenCallsReferencedMethodAccept() {
    // Arrange
    MethodInvocationMarker methodInvocationMarker = new MethodInvocationMarker();
    LibraryClass clazz = new LibraryClass();
    InterfaceMethodrefConstant anyMethodrefConstant = mock(InterfaceMethodrefConstant.class);
    doNothing().when(anyMethodrefConstant).referencedMethodAccept(Mockito.<MemberVisitor>any());

    // Act
    methodInvocationMarker.visitAnyMethodrefConstant(clazz, anyMethodrefConstant);

    // Assert
    verify(anyMethodrefConstant).referencedMethodAccept(isA(MemberVisitor.class));
  }

  /**
   * Test {@link MethodInvocationMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Then calls {@link ProgramMethodOptimizationInfo#incrementInvocationCount()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodInvocationMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); then calls incrementInvocationCount()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void MethodInvocationMarker.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_thenCallsIncrementInvocationCount() {
    // Arrange
    MethodInvocationMarker methodInvocationMarker = new MethodInvocationMarker();
    ProgramClass programClass = new ProgramClass();
    ProgramMethodOptimizationInfo programMethodOptimizationInfo = mock(ProgramMethodOptimizationInfo.class);
    doNothing().when(programMethodOptimizationInfo).incrementInvocationCount();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(programMethodOptimizationInfo);

    // Act
    methodInvocationMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethodOptimizationInfo).incrementInvocationCount();
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link MethodInvocationMarker#getInvocationCount(Method)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@link Integer#MAX_VALUE}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MethodInvocationMarker#getInvocationCount(Method)}
   */
  @Test
  @DisplayName("Test getInvocationCount(Method); given MethodOptimizationInfo (default constructor); then return MAX_VALUE")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"int MethodInvocationMarker.getInvocationCount(Method)"})
  void testGetInvocationCount_givenMethodOptimizationInfo_thenReturnMax_value() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertEquals(Integer.MAX_VALUE, MethodInvocationMarker.getInvocationCount(method));
  }
}
