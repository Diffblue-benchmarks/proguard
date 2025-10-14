package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.visitor.ClassVisitor;

class ParameterEscapedMarkerDiffblueTest {
  /**
   * Test {@link ParameterEscapedMarker#visitClassPool(ClassPool)}.
   *
   * <ul>
   *   <li>When {@link ClassPool} {@link ClassPool#classesAccept(ClassVisitor)} does nothing.
   *   <li>Then calls {@link ClassPool#classesAccept(ClassVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link ParameterEscapedMarker#visitClassPool(ClassPool)}
   */
  @Test
  @DisplayName(
      "Test visitClassPool(ClassPool); when ClassPool classesAccept(ClassVisitor) does nothing; then calls classesAccept(ClassVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ParameterEscapedMarker.visitClassPool(ClassPool)"})
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
   * Test {@link ParameterEscapedMarker#hasParameterEscaped(Method, int)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ParameterEscapedMarker#hasParameterEscaped(Method, int)}
   */
  @Test
  @DisplayName(
      "Test hasParameterEscaped(Method, int); given MethodOptimizationInfo (default constructor); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ParameterEscapedMarker.hasParameterEscaped(Method, int)"})
  void testHasParameterEscaped_givenMethodOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(ParameterEscapedMarker.hasParameterEscaped(method, 1));
  }

  /**
   * Test {@link ParameterEscapedMarker#getEscapedParameters(Method)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link ParameterEscapedMarker#getEscapedParameters(Method)}
   */
  @Test
  @DisplayName(
      "Test getEscapedParameters(Method); given MethodOptimizationInfo (default constructor); then return minus one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long ParameterEscapedMarker.getEscapedParameters(Method)"})
  void testGetEscapedParameters_givenMethodOptimizationInfo_thenReturnMinusOne() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertEquals(-1L, ParameterEscapedMarker.getEscapedParameters(method));
  }
}
