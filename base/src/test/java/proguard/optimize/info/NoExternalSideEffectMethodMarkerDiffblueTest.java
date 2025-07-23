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

class NoExternalSideEffectMethodMarkerDiffblueTest {
  /**
   * Test {@link NoExternalSideEffectMethodMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link NoExternalSideEffectMethodMarker#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); given MethodOptimizationInfo (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NoExternalSideEffectMethodMarker.visitProgramMethod(ProgramClass, ProgramMethod)"
  })
  void testVisitProgramMethod_givenMethodOptimizationInfo() {
    // Arrange
    NoExternalSideEffectMethodMarker noExternalSideEffectMethodMarker =
        new NoExternalSideEffectMethodMarker();
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    noExternalSideEffectMethodMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link NoExternalSideEffectMethodMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>Then calls {@link MethodOptimizationInfo#setNoExternalSideEffects()}.
   * </ul>
   *
   * <p>Method under test: {@link NoExternalSideEffectMethodMarker#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); then calls setNoExternalSideEffects()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NoExternalSideEffectMethodMarker.visitProgramMethod(ProgramClass, ProgramMethod)"
  })
  void testVisitProgramMethod_thenCallsSetNoExternalSideEffects() {
    // Arrange
    NoExternalSideEffectMethodMarker noExternalSideEffectMethodMarker =
        new NoExternalSideEffectMethodMarker();
    ProgramClass programClass = new ProgramClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    doNothing().when(methodOptimizationInfo).setNoExternalSideEffects();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    noExternalSideEffectMethodMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(methodOptimizationInfo).setNoExternalSideEffects();
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link NoExternalSideEffectMethodMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link NoExternalSideEffectMethodMarker#visitLibraryMethod(LibraryClass,
   * LibraryMethod)}
   */
  @Test
  @DisplayName(
      "Test visitLibraryMethod(LibraryClass, LibraryMethod); given MethodOptimizationInfo (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NoExternalSideEffectMethodMarker.visitLibraryMethod(LibraryClass, LibraryMethod)"
  })
  void testVisitLibraryMethod_givenMethodOptimizationInfo() {
    // Arrange
    NoExternalSideEffectMethodMarker noExternalSideEffectMethodMarker =
        new NoExternalSideEffectMethodMarker();
    LibraryClass libraryClass = new LibraryClass();
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    noExternalSideEffectMethodMarker.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link NoExternalSideEffectMethodMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   *
   * <ul>
   *   <li>Then calls {@link MethodOptimizationInfo#setNoExternalSideEffects()}.
   * </ul>
   *
   * <p>Method under test: {@link NoExternalSideEffectMethodMarker#visitLibraryMethod(LibraryClass,
   * LibraryMethod)}
   */
  @Test
  @DisplayName(
      "Test visitLibraryMethod(LibraryClass, LibraryMethod); then calls setNoExternalSideEffects()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NoExternalSideEffectMethodMarker.visitLibraryMethod(LibraryClass, LibraryMethod)"
  })
  void testVisitLibraryMethod_thenCallsSetNoExternalSideEffects() {
    // Arrange
    NoExternalSideEffectMethodMarker noExternalSideEffectMethodMarker =
        new NoExternalSideEffectMethodMarker();
    LibraryClass libraryClass = new LibraryClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    doNothing().when(methodOptimizationInfo).setNoExternalSideEffects();
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    noExternalSideEffectMethodMarker.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(methodOptimizationInfo).setNoExternalSideEffects();
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link NoExternalSideEffectMethodMarker#hasNoExternalSideEffects(Method)}.
   *
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link NoExternalSideEffectMethodMarker#hasNoExternalSideEffects(Method)}
   */
  @Test
  @DisplayName(
      "Test hasNoExternalSideEffects(Method); given MethodOptimizationInfo (default constructor); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NoExternalSideEffectMethodMarker.hasNoExternalSideEffects(Method)"})
  void testHasNoExternalSideEffects_givenMethodOptimizationInfo_thenReturnFalse() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertFalse(NoExternalSideEffectMethodMarker.hasNoExternalSideEffects(method));
  }
}
