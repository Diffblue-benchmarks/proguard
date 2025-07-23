package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;

class NoEscapingParametersMethodMarkerDiffblueTest {
  /**
   * Test {@link NoEscapingParametersMethodMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link NoEscapingParametersMethodMarker#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); given MethodOptimizationInfo (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NoEscapingParametersMethodMarker.visitProgramMethod(ProgramClass, ProgramMethod)"
  })
  void testVisitProgramMethod_givenMethodOptimizationInfo() {
    // Arrange
    NoEscapingParametersMethodMarker noEscapingParametersMethodMarker =
        new NoEscapingParametersMethodMarker();
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    noEscapingParametersMethodMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link NoEscapingParametersMethodMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>Then calls {@link MethodOptimizationInfo#setNoEscapingParameters()}.
   * </ul>
   *
   * <p>Method under test: {@link NoEscapingParametersMethodMarker#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); then calls setNoEscapingParameters()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NoEscapingParametersMethodMarker.visitProgramMethod(ProgramClass, ProgramMethod)"
  })
  void testVisitProgramMethod_thenCallsSetNoEscapingParameters() {
    // Arrange
    NoEscapingParametersMethodMarker noEscapingParametersMethodMarker =
        new NoEscapingParametersMethodMarker();
    ProgramClass programClass = new ProgramClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    doNothing().when(methodOptimizationInfo).setNoEscapingParameters();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    noEscapingParametersMethodMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(methodOptimizationInfo).setNoEscapingParameters();
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link NoEscapingParametersMethodMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link NoEscapingParametersMethodMarker#visitLibraryMethod(LibraryClass,
   * LibraryMethod)}
   */
  @Test
  @DisplayName(
      "Test visitLibraryMethod(LibraryClass, LibraryMethod); given MethodOptimizationInfo (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NoEscapingParametersMethodMarker.visitLibraryMethod(LibraryClass, LibraryMethod)"
  })
  void testVisitLibraryMethod_givenMethodOptimizationInfo() {
    // Arrange
    NoEscapingParametersMethodMarker noEscapingParametersMethodMarker =
        new NoEscapingParametersMethodMarker();
    LibraryClass libraryClass = new LibraryClass();
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    noEscapingParametersMethodMarker.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link NoEscapingParametersMethodMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   *
   * <ul>
   *   <li>Then calls {@link MethodOptimizationInfo#setNoEscapingParameters()}.
   * </ul>
   *
   * <p>Method under test: {@link NoEscapingParametersMethodMarker#visitLibraryMethod(LibraryClass,
   * LibraryMethod)}
   */
  @Test
  @DisplayName(
      "Test visitLibraryMethod(LibraryClass, LibraryMethod); then calls setNoEscapingParameters()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NoEscapingParametersMethodMarker.visitLibraryMethod(LibraryClass, LibraryMethod)"
  })
  void testVisitLibraryMethod_thenCallsSetNoEscapingParameters() {
    // Arrange
    NoEscapingParametersMethodMarker noEscapingParametersMethodMarker =
        new NoEscapingParametersMethodMarker();
    LibraryClass libraryClass = new LibraryClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    doNothing().when(methodOptimizationInfo).setNoEscapingParameters();
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    noEscapingParametersMethodMarker.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(methodOptimizationInfo).setNoEscapingParameters();
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link NoEscapingParametersMethodMarker#hasNoParameterEscaping(Method)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link NoEscapingParametersMethodMarker#hasNoParameterEscaping(Method)}
   */
  @Test
  @DisplayName(
      "Test hasNoParameterEscaping(Method); given MethodOptimizationInfo (default constructor); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NoEscapingParametersMethodMarker.hasNoParameterEscaping(Method)"})
  void testHasNoParameterEscaping_givenMethodOptimizationInfo_thenReturnFalse() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertFalse(NoEscapingParametersMethodMarker.hasNoParameterEscaping(method));
  }
}
