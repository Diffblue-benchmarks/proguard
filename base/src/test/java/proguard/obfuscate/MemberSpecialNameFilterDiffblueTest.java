package proguard.obfuscate;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
import proguard.util.SimpleProcessable;

class MemberSpecialNameFilterDiffblueTest {
  /**
   * Test {@link MemberSpecialNameFilter#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Given {@code Processing Info}.</li>
   *   <li>Then calls {@link SimpleProcessable#getProcessingInfo()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberSpecialNameFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); given 'Processing Info'; then calls getProcessingInfo()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.MemberSpecialNameFilter.visitProgramField(proguard.classfile.ProgramClass, proguard.classfile.ProgramField)"})
  void testVisitProgramField_givenProcessingInfo_thenCallsGetProcessingInfo() {
    // Arrange
    MemberSpecialNameFilter memberSpecialNameFilter = new MemberSpecialNameFilter(new KotlinAnnotationCounter());
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
   * <ul>
   *   <li>Given {@code Processing Info}.</li>
   *   <li>Then calls {@link SimpleProcessable#getProcessingInfo()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberSpecialNameFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given 'Processing Info'; then calls getProcessingInfo()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.MemberSpecialNameFilter.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_givenProcessingInfo_thenCallsGetProcessingInfo() {
    // Arrange
    MemberSpecialNameFilter memberSpecialNameFilter = new MemberSpecialNameFilter(new KotlinAnnotationCounter());
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
   * <ul>
   *   <li>Given {@code Processing Info}.</li>
   *   <li>Then calls {@link SimpleProcessable#getProcessingInfo()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberSpecialNameFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField); given 'Processing Info'; then calls getProcessingInfo()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.MemberSpecialNameFilter.visitLibraryField(proguard.classfile.LibraryClass, proguard.classfile.LibraryField)"})
  void testVisitLibraryField_givenProcessingInfo_thenCallsGetProcessingInfo() {
    // Arrange
    MemberSpecialNameFilter memberSpecialNameFilter = new MemberSpecialNameFilter(new KotlinAnnotationCounter());
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
   * <ul>
   *   <li>Given {@code Processing Info}.</li>
   *   <li>Then calls {@link SimpleProcessable#getProcessingInfo()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberSpecialNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod); given 'Processing Info'; then calls getProcessingInfo()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.MemberSpecialNameFilter.visitLibraryMethod(proguard.classfile.LibraryClass, proguard.classfile.LibraryMethod)"})
  void testVisitLibraryMethod_givenProcessingInfo_thenCallsGetProcessingInfo() {
    // Arrange
    MemberSpecialNameFilter memberSpecialNameFilter = new MemberSpecialNameFilter(new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();
    LibraryMethod libraryMethod = mock(LibraryMethod.class);
    when(libraryMethod.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    memberSpecialNameFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    verify(libraryMethod, atLeast(1)).getProcessingInfo();
  }
}
