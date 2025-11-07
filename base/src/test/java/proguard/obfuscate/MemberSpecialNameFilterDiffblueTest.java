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
  @MethodsUnderTest({"void MemberSpecialNameFilter.visitProgramField(ProgramClass, ProgramField)"})
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
  @MethodsUnderTest({"void MemberSpecialNameFilter.visitProgramMethod(ProgramClass, ProgramMethod)"})
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
  @MethodsUnderTest({"void MemberSpecialNameFilter.visitLibraryMethod(LibraryClass, LibraryMethod)"})
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
