package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;

class NoSideEffectMethodMarkerDiffblueTest {
  /**
   * Test {@link NoSideEffectMethodMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NoSideEffectMethodMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given MethodOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.NoSideEffectMethodMarker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_givenMethodOptimizationInfo() {
    // Arrange
    NoSideEffectMethodMarker noSideEffectMethodMarker = new NoSideEffectMethodMarker();
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    noSideEffectMethodMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link NoSideEffectMethodMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Then calls {@link MethodOptimizationInfo#setNoSideEffects()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NoSideEffectMethodMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); then calls setNoSideEffects()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.NoSideEffectMethodMarker.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_thenCallsSetNoSideEffects() {
    // Arrange
    NoSideEffectMethodMarker noSideEffectMethodMarker = new NoSideEffectMethodMarker();
    ProgramClass programClass = new ProgramClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    doNothing().when(methodOptimizationInfo).setNoSideEffects();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    noSideEffectMethodMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(methodOptimizationInfo).setNoSideEffects();
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link NoSideEffectMethodMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link NoSideEffectMethodMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod); given MethodOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.NoSideEffectMethodMarker.visitLibraryMethod(proguard.classfile.LibraryClass, proguard.classfile.LibraryMethod)"})
  void testVisitLibraryMethod_givenMethodOptimizationInfo() {
    // Arrange
    NoSideEffectMethodMarker noSideEffectMethodMarker = new NoSideEffectMethodMarker();
    LibraryClass libraryClass = new LibraryClass();
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn(new MethodOptimizationInfo());

    // Act
    noSideEffectMethodMarker.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link NoSideEffectMethodMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <ul>
   *   <li>Then calls {@link MethodOptimizationInfo#setNoSideEffects()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NoSideEffectMethodMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod); then calls setNoSideEffects()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.optimize.info.NoSideEffectMethodMarker.visitLibraryMethod(proguard.classfile.LibraryClass, proguard.classfile.LibraryMethod)"})
  void testVisitLibraryMethod_thenCallsSetNoSideEffects() {
    // Arrange
    NoSideEffectMethodMarker noSideEffectMethodMarker = new NoSideEffectMethodMarker();
    LibraryClass libraryClass = new LibraryClass();
    MethodOptimizationInfo methodOptimizationInfo = mock(MethodOptimizationInfo.class);
    doNothing().when(methodOptimizationInfo).setNoSideEffects();
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn(methodOptimizationInfo);

    // Act
    noSideEffectMethodMarker.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(methodOptimizationInfo).setNoSideEffects();
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link NoSideEffectMethodMarker#hasNoSideEffects(Method)}.
   * <ul>
   *   <li>Given {@link MethodOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NoSideEffectMethodMarker#hasNoSideEffects(Method)}
   */
  @Test
  @DisplayName("Test hasNoSideEffects(Method); given MethodOptimizationInfo (default constructor); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "boolean proguard.optimize.info.NoSideEffectMethodMarker.hasNoSideEffects(proguard.classfile.Method)"})
  void testHasNoSideEffects_givenMethodOptimizationInfo_thenReturnFalse() {
    // Arrange
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");
    method.setProcessingInfo(new MethodOptimizationInfo());

    // Act and Assert
    assertFalse(NoSideEffectMethodMarker.hasNoSideEffects(method));
  }
}
