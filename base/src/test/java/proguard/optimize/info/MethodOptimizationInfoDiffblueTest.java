package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.evaluation.value.TopValue;
import proguard.evaluation.value.Value;

class MethodOptimizationInfoDiffblueTest {
  /**
   * Method under test: {@link MethodOptimizationInfo#setNoSideEffects()}
   */
  @Test
  void testSetNoSideEffects() {
    // Arrange
    MethodOptimizationInfo methodOptimizationInfo = new MethodOptimizationInfo();

    // Act
    methodOptimizationInfo.setNoSideEffects();

    // Assert
    assertEquals(0L, methodOptimizationInfo.getEscapingParameters());
    assertEquals(0L, methodOptimizationInfo.getModifiedParameters());
    assertFalse(methodOptimizationInfo.hasSideEffects());
    assertTrue(methodOptimizationInfo.hasNoEscapingParameters());
    assertTrue(methodOptimizationInfo.hasNoExternalSideEffects());
    assertTrue(methodOptimizationInfo.hasNoSideEffects());
  }

  /**
   * Method under test: {@link MethodOptimizationInfo#setNoExternalSideEffects()}
   */
  @Test
  void testSetNoExternalSideEffects() {
    // Arrange
    MethodOptimizationInfo methodOptimizationInfo = new MethodOptimizationInfo();

    // Act
    methodOptimizationInfo.setNoExternalSideEffects();

    // Assert
    assertEquals(0L, methodOptimizationInfo.getEscapingParameters());
    assertEquals(1L, methodOptimizationInfo.getModifiedParameters());
    assertTrue(methodOptimizationInfo.hasNoEscapingParameters());
    assertTrue(methodOptimizationInfo.hasNoExternalSideEffects());
  }

  /**
   * Method under test: {@link MethodOptimizationInfo#hasSideEffects()}
   */
  @Test
  void testHasSideEffects() {
    // Arrange, Act and Assert
    assertTrue((new MethodOptimizationInfo()).hasSideEffects());
  }

  /**
   * Method under test: {@link MethodOptimizationInfo#canBeMadePrivate()}
   */
  @Test
  void testCanBeMadePrivate() {
    // Arrange, Act and Assert
    assertFalse((new MethodOptimizationInfo()).canBeMadePrivate());
  }

  /**
   * Method under test: {@link MethodOptimizationInfo#catchesExceptions()}
   */
  @Test
  void testCatchesExceptions() {
    // Arrange, Act and Assert
    assertTrue((new MethodOptimizationInfo()).catchesExceptions());
  }

  /**
   * Method under test: {@link MethodOptimizationInfo#branchesBackward()}
   */
  @Test
  void testBranchesBackward() {
    // Arrange, Act and Assert
    assertTrue((new MethodOptimizationInfo()).branchesBackward());
  }

  /**
   * Method under test: {@link MethodOptimizationInfo#invokesSuperMethods()}
   */
  @Test
  void testInvokesSuperMethods() {
    // Arrange, Act and Assert
    assertTrue((new MethodOptimizationInfo()).invokesSuperMethods());
  }

  /**
   * Method under test: {@link MethodOptimizationInfo#invokesDynamically()}
   */
  @Test
  void testInvokesDynamically() {
    // Arrange, Act and Assert
    assertTrue((new MethodOptimizationInfo()).invokesDynamically());
  }

  /**
   * Method under test: {@link MethodOptimizationInfo#accessesPrivateCode()}
   */
  @Test
  void testAccessesPrivateCode() {
    // Arrange, Act and Assert
    assertTrue((new MethodOptimizationInfo()).accessesPrivateCode());
  }

  /**
   * Method under test: {@link MethodOptimizationInfo#accessesPackageCode()}
   */
  @Test
  void testAccessesPackageCode() {
    // Arrange, Act and Assert
    assertTrue((new MethodOptimizationInfo()).accessesPackageCode());
  }

  /**
   * Method under test: {@link MethodOptimizationInfo#accessesProtectedCode()}
   */
  @Test
  void testAccessesProtectedCode() {
    // Arrange, Act and Assert
    assertTrue((new MethodOptimizationInfo()).accessesProtectedCode());
  }

  /**
   * Method under test: {@link MethodOptimizationInfo#assignsFinalField()}
   */
  @Test
  void testAssignsFinalField() {
    // Arrange, Act and Assert
    assertTrue((new MethodOptimizationInfo()).assignsFinalField());
  }

  /**
   * Method under test: {@link MethodOptimizationInfo#returnsWithNonEmptyStack()}
   */
  @Test
  void testReturnsWithNonEmptyStack() {
    // Arrange, Act and Assert
    assertFalse((new MethodOptimizationInfo()).returnsWithNonEmptyStack());
  }

  /**
   * Method under test: {@link MethodOptimizationInfo#isParameterUsed(int)}
   */
  @Test
  void testIsParameterUsed() {
    // Arrange, Act and Assert
    assertTrue((new MethodOptimizationInfo()).isParameterUsed(1));
  }

  /**
   * Method under test: {@link MethodOptimizationInfo#hasParameterEscaped(int)}
   */
  @Test
  void testHasParameterEscaped() {
    // Arrange, Act and Assert
    assertTrue((new MethodOptimizationInfo()).hasParameterEscaped(1));
  }

  /**
   * Method under test: {@link MethodOptimizationInfo#isParameterEscaping(int)}
   */
  @Test
  void testIsParameterEscaping() {
    // Arrange, Act and Assert
    assertTrue((new MethodOptimizationInfo()).isParameterEscaping(1));
  }

  /**
   * Method under test: {@link MethodOptimizationInfo#getEscapingParameters()}
   */
  @Test
  void testGetEscapingParameters() {
    // Arrange, Act and Assert
    assertEquals(-1L, (new MethodOptimizationInfo()).getEscapingParameters());
  }

  /**
   * Method under test: {@link MethodOptimizationInfo#isParameterModified(int)}
   */
  @Test
  void testIsParameterModified() {
    // Arrange, Act and Assert
    assertTrue((new MethodOptimizationInfo()).isParameterModified(1));
  }

  /**
   * Method under test: {@link MethodOptimizationInfo#getModifiedParameters()}
   */
  @Test
  void testGetModifiedParameters() {
    // Arrange, Act and Assert
    assertEquals(-1L, (new MethodOptimizationInfo()).getModifiedParameters());
  }

  /**
   * Method under test: {@link MethodOptimizationInfo#modifiesAnything()}
   */
  @Test
  void testModifiesAnything() {
    // Arrange, Act and Assert
    assertTrue((new MethodOptimizationInfo()).modifiesAnything());
  }

  /**
   * Method under test: {@link MethodOptimizationInfo#getParameterValue(int)}
   */
  @Test
  void testGetParameterValue() {
    // Arrange, Act and Assert
    assertNull((new MethodOptimizationInfo()).getParameterValue(1));
  }

  /**
   * Method under test: {@link MethodOptimizationInfo#returnsParameter(int)}
   */
  @Test
  void testReturnsParameter() {
    // Arrange, Act and Assert
    assertTrue((new MethodOptimizationInfo()).returnsParameter(1));
  }

  /**
   * Method under test: {@link MethodOptimizationInfo#returnsNewInstances()}
   */
  @Test
  void testReturnsNewInstances() {
    // Arrange, Act and Assert
    assertTrue((new MethodOptimizationInfo()).returnsNewInstances());
  }

  /**
   * Method under test: {@link MethodOptimizationInfo#returnsExternalValues()}
   */
  @Test
  void testReturnsExternalValues() {
    // Arrange, Act and Assert
    assertTrue((new MethodOptimizationInfo()).returnsExternalValues());
  }

  /**
   * Method under test:
   * {@link MethodOptimizationInfo#setMethodOptimizationInfo(Clazz, Method)}
   */
  @Test
  void testSetMethodOptimizationInfo() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    MethodOptimizationInfo.setMethodOptimizationInfo(clazz, method);

    // Assert
    Object processingInfo = method.getProcessingInfo();
    assertTrue(processingInfo instanceof MethodOptimizationInfo);
    assertNull(((MethodOptimizationInfo) processingInfo).getReturnValue());
    assertEquals(-1L, ((MethodOptimizationInfo) processingInfo).getEscapedParameters());
    assertEquals(-1L, ((MethodOptimizationInfo) processingInfo).getEscapingParameters());
    assertEquals(-1L, ((MethodOptimizationInfo) processingInfo).getModifiedParameters());
    assertEquals(-1L, ((MethodOptimizationInfo) processingInfo).getReturnedParameters());
    assertEquals(-1L, ((MethodOptimizationInfo) processingInfo).getUsedParameters());
    assertEquals(0, ((MethodOptimizationInfo) processingInfo).getParameterSize());
    assertFalse(((MethodOptimizationInfo) processingInfo).hasNoEscapingParameters());
    assertFalse(((MethodOptimizationInfo) processingInfo).hasNoExternalReturnValues());
    assertFalse(((MethodOptimizationInfo) processingInfo).hasNoExternalSideEffects());
    assertFalse(((MethodOptimizationInfo) processingInfo).hasNoSideEffects());
    assertFalse(((MethodOptimizationInfo) processingInfo).hasUnusedParameters());
    assertTrue(((MethodOptimizationInfo) processingInfo).hasSideEffects());
    assertTrue(((MethodOptimizationInfo) processingInfo).hasSynchronizedBlock());
    assertTrue(((MethodOptimizationInfo) processingInfo).isKept());
    assertEquals(Integer.MAX_VALUE, ((MethodOptimizationInfo) processingInfo).getInvocationCount());
  }

  /**
   * Method under test:
   * {@link MethodOptimizationInfo#setMethodOptimizationInfo(Clazz, Method)}
   */
  @Test
  void testSetMethodOptimizationInfo2() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo("Processing Info");

    // Act
    MethodOptimizationInfo.setMethodOptimizationInfo(clazz, method);

    // Assert
    Object processingInfo = method.getProcessingInfo();
    assertTrue(processingInfo instanceof MethodOptimizationInfo);
    assertNull(((MethodOptimizationInfo) processingInfo).getReturnValue());
    assertEquals(-1L, ((MethodOptimizationInfo) processingInfo).getEscapedParameters());
    assertEquals(-1L, ((MethodOptimizationInfo) processingInfo).getEscapingParameters());
    assertEquals(-1L, ((MethodOptimizationInfo) processingInfo).getModifiedParameters());
    assertEquals(-1L, ((MethodOptimizationInfo) processingInfo).getReturnedParameters());
    assertEquals(-1L, ((MethodOptimizationInfo) processingInfo).getUsedParameters());
    assertEquals(0, ((MethodOptimizationInfo) processingInfo).getParameterSize());
    assertFalse(((MethodOptimizationInfo) processingInfo).hasNoEscapingParameters());
    assertFalse(((MethodOptimizationInfo) processingInfo).hasNoExternalReturnValues());
    assertFalse(((MethodOptimizationInfo) processingInfo).hasNoExternalSideEffects());
    assertFalse(((MethodOptimizationInfo) processingInfo).hasNoSideEffects());
    assertFalse(((MethodOptimizationInfo) processingInfo).hasUnusedParameters());
    assertTrue(((MethodOptimizationInfo) processingInfo).hasSideEffects());
    assertTrue(((MethodOptimizationInfo) processingInfo).hasSynchronizedBlock());
    assertTrue(((MethodOptimizationInfo) processingInfo).isKept());
    assertEquals(Integer.MAX_VALUE, ((MethodOptimizationInfo) processingInfo).getInvocationCount());
  }

  /**
   * Method under test:
   * {@link MethodOptimizationInfo#getMethodOptimizationInfo(Method)}
   */
  @Test
  void testGetMethodOptimizationInfo() {
    // Arrange, Act and Assert
    assertNull(MethodOptimizationInfo.getMethodOptimizationInfo(new LibraryMethod(1, "Name", "Descriptor")));
  }

  /**
   * Method under test:
   * {@link MethodOptimizationInfo#getMethodOptimizationInfo(Method)}
   */
  @Test
  void testGetMethodOptimizationInfo2() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act
    MethodOptimizationInfo actualMethodOptimizationInfo = MethodOptimizationInfo.getMethodOptimizationInfo(method);

    // Assert
    assertNull(actualMethodOptimizationInfo.getReturnValue());
    assertEquals(-1L, actualMethodOptimizationInfo.getEscapedParameters());
    assertEquals(-1L, actualMethodOptimizationInfo.getEscapingParameters());
    assertEquals(-1L, actualMethodOptimizationInfo.getModifiedParameters());
    assertEquals(-1L, actualMethodOptimizationInfo.getReturnedParameters());
    assertEquals(-1L, actualMethodOptimizationInfo.getUsedParameters());
    assertEquals(0, actualMethodOptimizationInfo.getParameterSize());
    assertFalse(actualMethodOptimizationInfo.hasNoEscapingParameters());
    assertFalse(actualMethodOptimizationInfo.hasNoExternalReturnValues());
    assertFalse(actualMethodOptimizationInfo.hasNoExternalSideEffects());
    assertFalse(actualMethodOptimizationInfo.hasNoSideEffects());
    assertFalse(actualMethodOptimizationInfo.hasUnusedParameters());
    assertTrue(actualMethodOptimizationInfo.hasSideEffects());
    assertTrue(actualMethodOptimizationInfo.hasSynchronizedBlock());
    assertTrue(actualMethodOptimizationInfo.isKept());
    assertEquals(Integer.MAX_VALUE, actualMethodOptimizationInfo.getInvocationCount());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link MethodOptimizationInfo}
   *   <li>{@link MethodOptimizationInfo#setReturnValue(Value)}
   *   <li>{@link MethodOptimizationInfo#setNoEscapingParameters()}
   *   <li>{@link MethodOptimizationInfo#setNoExternalReturnValues()}
   *   <li>{@link MethodOptimizationInfo#getEscapedParameters()}
   *   <li>{@link MethodOptimizationInfo#getInvocationCount()}
   *   <li>{@link MethodOptimizationInfo#getParameterSize()}
   *   <li>{@link MethodOptimizationInfo#getReturnValue()}
   *   <li>{@link MethodOptimizationInfo#getReturnedParameters()}
   *   <li>{@link MethodOptimizationInfo#getUsedParameters()}
   *   <li>{@link MethodOptimizationInfo#hasNoEscapingParameters()}
   *   <li>{@link MethodOptimizationInfo#hasNoExternalReturnValues()}
   *   <li>{@link MethodOptimizationInfo#hasNoExternalSideEffects()}
   *   <li>{@link MethodOptimizationInfo#hasNoSideEffects()}
   *   <li>{@link MethodOptimizationInfo#hasSynchronizedBlock()}
   *   <li>{@link MethodOptimizationInfo#hasUnusedParameters()}
   *   <li>{@link MethodOptimizationInfo#isKept()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange and Act
    MethodOptimizationInfo actualMethodOptimizationInfo = new MethodOptimizationInfo();
    TopValue returnValue = new TopValue();
    actualMethodOptimizationInfo.setReturnValue(returnValue);
    actualMethodOptimizationInfo.setNoEscapingParameters();
    actualMethodOptimizationInfo.setNoExternalReturnValues();
    long actualEscapedParameters = actualMethodOptimizationInfo.getEscapedParameters();
    int actualInvocationCount = actualMethodOptimizationInfo.getInvocationCount();
    int actualParameterSize = actualMethodOptimizationInfo.getParameterSize();
    Value actualReturnValue = actualMethodOptimizationInfo.getReturnValue();
    long actualReturnedParameters = actualMethodOptimizationInfo.getReturnedParameters();
    long actualUsedParameters = actualMethodOptimizationInfo.getUsedParameters();
    boolean actualHasNoEscapingParametersResult = actualMethodOptimizationInfo.hasNoEscapingParameters();
    boolean actualHasNoExternalReturnValuesResult = actualMethodOptimizationInfo.hasNoExternalReturnValues();
    boolean actualHasNoExternalSideEffectsResult = actualMethodOptimizationInfo.hasNoExternalSideEffects();
    boolean actualHasNoSideEffectsResult = actualMethodOptimizationInfo.hasNoSideEffects();
    boolean actualHasSynchronizedBlockResult = actualMethodOptimizationInfo.hasSynchronizedBlock();
    boolean actualHasUnusedParametersResult = actualMethodOptimizationInfo.hasUnusedParameters();

    // Assert that nothing has changed
    assertEquals(-1L, actualEscapedParameters);
    assertEquals(-1L, actualReturnedParameters);
    assertEquals(-1L, actualUsedParameters);
    assertEquals(0, actualParameterSize);
    assertFalse(actualHasNoExternalSideEffectsResult);
    assertFalse(actualHasNoSideEffectsResult);
    assertFalse(actualHasUnusedParametersResult);
    assertTrue(actualHasNoEscapingParametersResult);
    assertTrue(actualHasNoExternalReturnValuesResult);
    assertTrue(actualHasSynchronizedBlockResult);
    assertTrue(actualMethodOptimizationInfo.isKept());
    assertEquals(Integer.MAX_VALUE, actualInvocationCount);
    assertSame(returnValue, actualReturnValue);
  }
}
