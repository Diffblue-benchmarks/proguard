package proguard.obfuscate;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;
import proguard.fixer.kotlin.KotlinAnnotationCounter;

class MemberSpecialNameFilterDiffblueTest {
  /**
   * Method under test:
   * {@link MemberSpecialNameFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField() {
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
   * Method under test:
   * {@link MemberSpecialNameFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
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
   * Method under test:
   * {@link MemberSpecialNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod() {
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
