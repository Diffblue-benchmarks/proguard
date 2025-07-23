package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
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
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramMethod;

class ContainsConstructorsMarkerDiffblueTest {
  /**
   * Test {@link ContainsConstructorsMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link ContainsConstructorsMarker#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); given ProgramClassOptimizationInfo (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ContainsConstructorsMarker.visitProgramMethod(ProgramClass, ProgramMethod)"
  })
  void testVisitProgramMethod_givenProgramClassOptimizationInfo() {
    // Arrange
    ContainsConstructorsMarker containsConstructorsMarker = new ContainsConstructorsMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());
    when(programClass.getString(anyInt())).thenReturn("<init>");

    // Act
    containsConstructorsMarker.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).getString(eq(0));
    verify(programClass).getProcessingInfo();
  }

  /**
   * Test {@link ContainsConstructorsMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   *   <li>When {@link ProgramClass} {@link ProgramClass#getString(int)} return {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link ContainsConstructorsMarker#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); given 'String'; when ProgramClass getString(int) return 'String'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ContainsConstructorsMarker.visitProgramMethod(ProgramClass, ProgramMethod)"
  })
  void testVisitProgramMethod_givenString_whenProgramClassGetStringReturnString() {
    // Arrange
    ContainsConstructorsMarker containsConstructorsMarker = new ContainsConstructorsMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    containsConstructorsMarker.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Test {@link ContainsConstructorsMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>Then calls {@link ProgramClassOptimizationInfo#setContainsConstructors()}.
   * </ul>
   *
   * <p>Method under test: {@link ContainsConstructorsMarker#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); then calls setContainsConstructors()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ContainsConstructorsMarker.visitProgramMethod(ProgramClass, ProgramMethod)"
  })
  void testVisitProgramMethod_thenCallsSetContainsConstructors() {
    // Arrange
    ContainsConstructorsMarker containsConstructorsMarker = new ContainsConstructorsMarker();
    ProgramClassOptimizationInfo programClassOptimizationInfo =
        mock(ProgramClassOptimizationInfo.class);
    doNothing().when(programClassOptimizationInfo).setContainsConstructors();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(programClassOptimizationInfo);
    when(programClass.getString(anyInt())).thenReturn("<init>");

    // Act
    containsConstructorsMarker.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).getString(eq(0));
    verify(programClassOptimizationInfo).setContainsConstructors();
    verify(programClass).getProcessingInfo();
  }

  /**
   * Test {@link ContainsConstructorsMarker#containsConstructors(Clazz)}.
   *
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ContainsConstructorsMarker#containsConstructors(Clazz)}
   */
  @Test
  @DisplayName(
      "Test containsConstructors(Clazz); given ClassOptimizationInfo (default constructor); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ContainsConstructorsMarker.containsConstructors(Clazz)"})
  void testContainsConstructors_givenClassOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryClass clazz = new LibraryClass(1, "This Class Name", "Super Class Name");
    clazz.setProcessingInfo(new ClassOptimizationInfo());

    // Act and Assert
    assertTrue(ContainsConstructorsMarker.containsConstructors(clazz));
  }

  /**
   * Test {@link ContainsConstructorsMarker#containsConstructors(Clazz)}.
   *
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ContainsConstructorsMarker#containsConstructors(Clazz)}
   */
  @Test
  @DisplayName(
      "Test containsConstructors(Clazz); given ProgramClassOptimizationInfo (default constructor); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ContainsConstructorsMarker.containsConstructors(Clazz)"})
  void testContainsConstructors_givenProgramClassOptimizationInfo_thenReturnFalse() {
    // Arrange
    LibraryClass clazz = new LibraryClass(1, "This Class Name", "Super Class Name");
    clazz.setProcessingInfo(new ProgramClassOptimizationInfo());

    // Act and Assert
    assertFalse(ContainsConstructorsMarker.containsConstructors(clazz));
  }
}
