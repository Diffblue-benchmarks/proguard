package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;
import proguard.classfile.visitor.ClassVisitor;

class ParameterEscapedMarkerDiffblueTest {
  /**
   * Test {@link ParameterEscapedMarker#visitClassPool(ClassPool)}.
   * <ul>
   *   <li>When {@link ClassPool} {@link ClassPool#classesAccept(ClassVisitor)} does nothing.</li>
   *   <li>Then calls {@link ClassPool#classesAccept(ClassVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterEscapedMarker#visitClassPool(ClassPool)}
   */
  @Test
  @DisplayName("Test visitClassPool(ClassPool); when ClassPool classesAccept(ClassVisitor) does nothing; then calls classesAccept(ClassVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.info.ParameterEscapedMarker.visitClassPool(proguard.classfile.ClassPool)"})
  void testVisitClassPool_whenClassPoolClassesAcceptDoesNothing_thenCallsClassesAccept() {
    // Arrange
    ParameterEscapedMarker parameterEscapedMarker = new ParameterEscapedMarker();
    ClassPool classPool = mock(ClassPool.class);
    doNothing().when(classPool).classesAccept(Mockito.<ClassVisitor>any());

    // Act
    parameterEscapedMarker.visitClassPool(classPool);

    // Assert
    verify(classPool).classesAccept(isA(ClassVisitor.class));
  }

  /**
   * Test {@link ParameterEscapedMarker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterEscapedMarker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant); given MethodOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.ParameterEscapedMarker.visitAnyMethodrefConstant(proguard.classfile.Clazz, proguard.classfile.constant.AnyMethodrefConstant)"})
  void testVisitAnyMethodrefConstant_givenMethodOptimizationInfo() {
    // Arrange
    ParameterEscapedMarker parameterEscapedMarker = new ParameterEscapedMarker();
    LibraryClass clazz = new LibraryClass();
    Method referencedMethod = mock(Method.class);
    when(referencedMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    parameterEscapedMarker.visitAnyMethodrefConstant(clazz,
        new InterfaceMethodrefConstant(1, 1, new LibraryClass(), referencedMethod));

    // Assert
    verify(referencedMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link ParameterEscapedMarker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   * <ul>
   *   <li>Given {@link ProgramMethodOptimizationInfo}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterEscapedMarker#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant); given ProgramMethodOptimizationInfo")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.ParameterEscapedMarker.visitAnyMethodrefConstant(proguard.classfile.Clazz, proguard.classfile.constant.AnyMethodrefConstant)"})
  void testVisitAnyMethodrefConstant_givenProgramMethodOptimizationInfo() {
    // Arrange
    ParameterEscapedMarker parameterEscapedMarker = new ParameterEscapedMarker();
    LibraryClass clazz = new LibraryClass();
    Method referencedMethod = mock(Method.class);
    when(referencedMethod.getProcessingInfo()).thenReturn(mock(ProgramMethodOptimizationInfo.class));

    // Act
    parameterEscapedMarker.visitAnyMethodrefConstant(clazz,
        new InterfaceMethodrefConstant(1, 1, new LibraryClass(), referencedMethod));

    // Assert
    verify(referencedMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link ParameterEscapedMarker#hasParameterEscaped(Method, int)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterEscapedMarker#hasParameterEscaped(Method, int)}
   */
  @Test
  @DisplayName("Test hasParameterEscaped(Method, int); given MethodOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.ParameterEscapedMarker.hasParameterEscaped(proguard.classfile.Method, int)"})
  void testHasParameterEscaped_givenMethodOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(ParameterEscapedMarker.hasParameterEscaped(method, 1));
  }

  /**
   * Test {@link ParameterEscapedMarker#getEscapedParameters(Method)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   *   <li>Then return minus one.</li>
   * </ul>
   * <p>
   * Method under test: {@link ParameterEscapedMarker#getEscapedParameters(Method)}
   */
  @Test
  @DisplayName("Test getEscapedParameters(Method); given MethodOptimizationInfo (default constructor); then return minus one")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "long proguard.optimize.info.ParameterEscapedMarker.getEscapedParameters(proguard.classfile.Method)"})
  void testGetEscapedParameters_givenMethodOptimizationInfo_thenReturnMinusOne() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertEquals(-1L, ParameterEscapedMarker.getEscapedParameters(method));
  }
}
