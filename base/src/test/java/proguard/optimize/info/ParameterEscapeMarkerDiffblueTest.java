package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;

class ParameterEscapeMarkerDiffblueTest {
  /**
   * Test {@link ParameterEscapeMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <p>Method under test: {@link ParameterEscapeMarker#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ParameterEscapeMarker.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod() {
    // Arrange
    ParameterEscapeMarker parameterEscapeMarker = new ParameterEscapeMarker();
    ProgramClass programClass = new ProgramClass();

    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.modifiesAnything()).thenReturn(false);
    when(methodOptimizationInfo.getEscapingParameters()).thenReturn(-1L);
    when(methodOptimizationInfo.getModifiedParameters()).thenReturn(-1L);
    when(methodOptimizationInfo.getReturnedParameters()).thenReturn(-1L);
    Clazz[] referencedClasses = new Clazz[] {new LibraryClass()};

    ProgramMethod programMethod = new ProgramMethod(256, 1, 1, referencedClasses);
    programMethod.setProcessingInfo(methodOptimizationInfo);

    // Act
    parameterEscapeMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(methodOptimizationInfo).getEscapingParameters();
    verify(methodOptimizationInfo).getModifiedParameters();
    verify(methodOptimizationInfo).getReturnedParameters();
    verify(methodOptimizationInfo).modifiesAnything();
  }

  /**
   * Test {@link ParameterEscapeMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <p>Method under test: {@link ParameterEscapeMarker#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ParameterEscapeMarker.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod2() {
    // Arrange
    ParameterEscapeMarker parameterEscapeMarker = new ParameterEscapeMarker();
    ProgramClass programClass = new ProgramClass();

    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.modifiesAnything()).thenReturn(false);
    when(methodOptimizationInfo.getEscapingParameters()).thenReturn(0L);
    when(methodOptimizationInfo.getModifiedParameters()).thenReturn(1L);
    when(methodOptimizationInfo.getReturnedParameters()).thenReturn(-1L);
    Clazz[] referencedClasses = new Clazz[] {new LibraryClass()};

    ProgramMethod programMethod = new ProgramMethod(256, 1, 1, referencedClasses);
    programMethod.setProcessingInfo(methodOptimizationInfo);

    // Act
    parameterEscapeMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(methodOptimizationInfo).getEscapingParameters();
    verify(methodOptimizationInfo).getModifiedParameters();
    verify(methodOptimizationInfo).getReturnedParameters();
    verify(methodOptimizationInfo).modifiesAnything();
  }

  /**
   * Test {@link ParameterEscapeMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <p>Method under test: {@link ParameterEscapeMarker#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ParameterEscapeMarker.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod3() {
    // Arrange
    ParameterEscapeMarker parameterEscapeMarker = new ParameterEscapeMarker();
    ProgramClass programClass = new ProgramClass();

    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    when(methodOptimizationInfo.getModifiedParameters()).thenReturn(0L);
    when(methodOptimizationInfo.modifiesAnything()).thenReturn(true);
    when(methodOptimizationInfo.getEscapingParameters()).thenReturn(1L);
    when(methodOptimizationInfo.getReturnedParameters()).thenReturn(1L);
    Clazz[] referencedClasses = new Clazz[] {new LibraryClass()};

    ProgramMethod programMethod = new ProgramMethod(256, 1, 1, referencedClasses);
    programMethod.setProcessingInfo(methodOptimizationInfo);

    // Act
    parameterEscapeMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(methodOptimizationInfo).getEscapingParameters();
    verify(methodOptimizationInfo).getModifiedParameters();
    verify(methodOptimizationInfo).getReturnedParameters();
    verify(methodOptimizationInfo).modifiesAnything();
  }

  /**
   * Test {@link ParameterEscapeMarker#isParameterEscaping(Method, int)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ParameterEscapeMarker#isParameterEscaping(Method, int)}
   */
  @Test
  @DisplayName(
      "Test isParameterEscaping(Method, int); given MethodOptimizationInfo (default constructor); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ParameterEscapeMarker.isParameterEscaping(Method, int)"})
  void testIsParameterEscaping_givenMethodOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(ParameterEscapeMarker.isParameterEscaping(method, 1));
  }

  /**
   * Test {@link ParameterEscapeMarker#getEscapingParameters(Method)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link ParameterEscapeMarker#getEscapingParameters(Method)}
   */
  @Test
  @DisplayName(
      "Test getEscapingParameters(Method); given MethodOptimizationInfo (default constructor); then return minus one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long ParameterEscapeMarker.getEscapingParameters(Method)"})
  void testGetEscapingParameters_givenMethodOptimizationInfo_thenReturnMinusOne() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertEquals(-1L, ParameterEscapeMarker.getEscapingParameters(method));
  }

  /**
   * Test {@link ParameterEscapeMarker#isParameterReturned(Method, int)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ParameterEscapeMarker#isParameterReturned(Method, int)}
   */
  @Test
  @DisplayName(
      "Test isParameterReturned(Method, int); given MethodOptimizationInfo (default constructor); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ParameterEscapeMarker.isParameterReturned(Method, int)"})
  void testIsParameterReturned_givenMethodOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(ParameterEscapeMarker.isParameterReturned(method, 1));
  }

  /**
   * Test {@link ParameterEscapeMarker#getReturnedParameters(Method)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link ParameterEscapeMarker#getReturnedParameters(Method)}
   */
  @Test
  @DisplayName(
      "Test getReturnedParameters(Method); given MethodOptimizationInfo (default constructor); then return minus one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long ParameterEscapeMarker.getReturnedParameters(Method)"})
  void testGetReturnedParameters_givenMethodOptimizationInfo_thenReturnMinusOne() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertEquals(-1L, ParameterEscapeMarker.getReturnedParameters(method));
  }

  /**
   * Test {@link ParameterEscapeMarker#returnsNewInstances(Method)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ParameterEscapeMarker#returnsNewInstances(Method)}
   */
  @Test
  @DisplayName(
      "Test returnsNewInstances(Method); given MethodOptimizationInfo (default constructor); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ParameterEscapeMarker.returnsNewInstances(Method)"})
  void testReturnsNewInstances_givenMethodOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(ParameterEscapeMarker.returnsNewInstances(method));
  }

  /**
   * Test {@link ParameterEscapeMarker#returnsExternalValues(Method)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ParameterEscapeMarker#returnsExternalValues(Method)}
   */
  @Test
  @DisplayName(
      "Test returnsExternalValues(Method); given MethodOptimizationInfo (default constructor); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ParameterEscapeMarker.returnsExternalValues(Method)"})
  void testReturnsExternalValues_givenMethodOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(ParameterEscapeMarker.returnsExternalValues(method));
  }

  /**
   * Test {@link ParameterEscapeMarker#isParameterModified(Method, int)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ParameterEscapeMarker#isParameterModified(Method, int)}
   */
  @Test
  @DisplayName(
      "Test isParameterModified(Method, int); given MethodOptimizationInfo (default constructor); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ParameterEscapeMarker.isParameterModified(Method, int)"})
  void testIsParameterModified_givenMethodOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(ParameterEscapeMarker.isParameterModified(method, 1));
  }

  /**
   * Test {@link ParameterEscapeMarker#getModifiedParameters(Method)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link ParameterEscapeMarker#getModifiedParameters(Method)}
   */
  @Test
  @DisplayName(
      "Test getModifiedParameters(Method); given MethodOptimizationInfo (default constructor); then return minus one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long ParameterEscapeMarker.getModifiedParameters(Method)"})
  void testGetModifiedParameters_givenMethodOptimizationInfo_thenReturnMinusOne() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertEquals(-1L, ParameterEscapeMarker.getModifiedParameters(method));
  }

  /**
   * Test {@link ParameterEscapeMarker#modifiesAnything(Method)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ParameterEscapeMarker#modifiesAnything(Method)}
   */
  @Test
  @DisplayName(
      "Test modifiesAnything(Method); given MethodOptimizationInfo (default constructor); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ParameterEscapeMarker.modifiesAnything(Method)"})
  void testModifiesAnything_givenMethodOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertTrue(ParameterEscapeMarker.modifiesAnything(method));
  }
}
