package proguard.obfuscate;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMethod;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;
import proguard.fixer.kotlin.KotlinAnnotationCounter;

class MemberSpecialNameFilterDiffblueTest {
  /**
   * Test {@link MemberSpecialNameFilter#visitProgramField(ProgramClass, ProgramField)}.
   *
   * <ul>
   *   <li>Given {@code Processing Info}.
   *   <li>Then calls {@link ProgramField#getProcessingInfo()}.
   * </ul>
   *
   * <p>Method under test: {@link MemberSpecialNameFilter#visitProgramField(ProgramClass,
   * ProgramField)}
   */
  @Test
  @DisplayName(
      "Test visitProgramField(ProgramClass, ProgramField); given 'Processing Info'; then calls getProcessingInfo()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void MemberSpecialNameFilter.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_givenProcessingInfo_thenCallsGetProcessingInfo() {
    // Arrange
    MemberSpecialNameFilter memberSpecialNameFilter =
        new MemberSpecialNameFilter(new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();
    ProgramField programField = mock(ProgramField.class);
    when(programField.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    memberSpecialNameFilter.visitProgramField(programClass, programField);

    // Assert
    verify(programField, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link MemberSpecialNameFilter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>Given {@code Processing Info}.
   *   <li>Then calls {@link ProgramMethod#getProcessingInfo()}.
   * </ul>
   *
   * <p>Method under test: {@link MemberSpecialNameFilter#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); given 'Processing Info'; then calls getProcessingInfo()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MemberSpecialNameFilter.visitProgramMethod(ProgramClass, ProgramMethod)"
  })
  void testVisitProgramMethod_givenProcessingInfo_thenCallsGetProcessingInfo() {
    // Arrange
    MemberSpecialNameFilter memberSpecialNameFilter =
        new MemberSpecialNameFilter(new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    memberSpecialNameFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link MemberSpecialNameFilter#visitLibraryField(LibraryClass, LibraryField)}.
   *
   * <ul>
   *   <li>Given {@code Processing Info}.
   *   <li>Then calls {@link LibraryField#getProcessingInfo()}.
   * </ul>
   *
   * <p>Method under test: {@link MemberSpecialNameFilter#visitLibraryField(LibraryClass,
   * LibraryField)}
   */
  @Test
  @DisplayName(
      "Test visitLibraryField(LibraryClass, LibraryField); given 'Processing Info'; then calls getProcessingInfo()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void MemberSpecialNameFilter.visitLibraryField(LibraryClass, LibraryField)"})
  void testVisitLibraryField_givenProcessingInfo_thenCallsGetProcessingInfo() {
    // Arrange
    MemberSpecialNameFilter memberSpecialNameFilter =
        new MemberSpecialNameFilter(new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();
    LibraryField libraryField = mock(LibraryField.class);
    when(libraryField.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    memberSpecialNameFilter.visitLibraryField(libraryClass, libraryField);

    // Assert
    verify(libraryField, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link MemberSpecialNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   *
   * <ul>
   *   <li>Given {@code Processing Info}.
   *   <li>Then calls {@link LibraryMethod#getProcessingInfo()}.
   * </ul>
   *
   * <p>Method under test: {@link MemberSpecialNameFilter#visitLibraryMethod(LibraryClass,
   * LibraryMethod)}
   */
  @Test
  @DisplayName(
      "Test visitLibraryMethod(LibraryClass, LibraryMethod); given 'Processing Info'; then calls getProcessingInfo()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MemberSpecialNameFilter.visitLibraryMethod(LibraryClass, LibraryMethod)"
  })
  void testVisitLibraryMethod_givenProcessingInfo_thenCallsGetProcessingInfo() {
    // Arrange
    MemberSpecialNameFilter memberSpecialNameFilter =
        new MemberSpecialNameFilter(new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    memberSpecialNameFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }
}
