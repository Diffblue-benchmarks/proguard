package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMethod;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;

class MemberNameCleanerDiffblueTest {
  /**
   * Method under test:
   * {@link MemberNameCleaner#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField() {
    // Arrange
    MemberNameCleaner memberNameCleaner = new MemberNameCleaner();
    ProgramClass programClass = new ProgramClass();
    ProgramField programField = new ProgramField();

    // Act
    memberNameCleaner.visitProgramField(programClass, programField);

    // Assert
    assertNull(programField.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link MemberNameCleaner#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField2() {
    // Arrange
    MemberNameCleaner memberNameCleaner = new MemberNameCleaner();
    ProgramClass programClass = new ProgramClass();

    ProgramField programField = new ProgramField();
    programField.setProcessingInfo("Processing Info");

    // Act
    memberNameCleaner.visitProgramField(programClass, programField);

    // Assert
    assertNull(programField.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link MemberNameCleaner#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
    // Arrange
    MemberNameCleaner memberNameCleaner = new MemberNameCleaner();
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = new ProgramMethod();

    // Act
    memberNameCleaner.visitProgramMethod(programClass, programMethod);

    // Assert
    assertNull(programMethod.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link MemberNameCleaner#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod2() {
    // Arrange
    MemberNameCleaner memberNameCleaner = new MemberNameCleaner();
    ProgramClass programClass = new ProgramClass();

    ProgramMethod programMethod = new ProgramMethod();
    programMethod.setProcessingInfo("Processing Info");

    // Act
    memberNameCleaner.visitProgramMethod(programClass, programMethod);

    // Assert
    assertNull(programMethod.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link MemberNameCleaner#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  void testVisitLibraryField() {
    // Arrange
    MemberNameCleaner memberNameCleaner = new MemberNameCleaner();
    LibraryClass libraryClass = new LibraryClass();
    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");

    // Act
    memberNameCleaner.visitLibraryField(libraryClass, libraryField);

    // Assert
    assertNull(libraryField.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link MemberNameCleaner#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  void testVisitLibraryField2() {
    // Arrange
    MemberNameCleaner memberNameCleaner = new MemberNameCleaner();
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo("Library Field");

    // Act
    memberNameCleaner.visitLibraryField(libraryClass, libraryField);

    // Assert
    assertNull(libraryField.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link MemberNameCleaner#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  void testVisitLibraryField3() {
    // Arrange
    MemberNameCleaner memberNameCleaner = new MemberNameCleaner();
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo(null);

    LibraryField libraryField2 = new LibraryField(1, "Name", "Descriptor");
    libraryField2.setProcessingInfo(libraryField);

    // Act
    memberNameCleaner.visitLibraryField(libraryClass, libraryField2);

    // Assert
    Object processingInfo = libraryField2.getProcessingInfo();
    assertTrue(processingInfo instanceof LibraryField);
    assertSame(libraryField, processingInfo);
  }

  /**
   * Method under test:
   * {@link MemberNameCleaner#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod() {
    // Arrange
    MemberNameCleaner memberNameCleaner = new MemberNameCleaner();
    LibraryClass libraryClass = new LibraryClass();
    LibraryMethod libraryMethod = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    memberNameCleaner.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    assertNull(libraryMethod.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link MemberNameCleaner#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod2() {
    // Arrange
    MemberNameCleaner memberNameCleaner = new MemberNameCleaner();
    LibraryClass libraryClass = new LibraryClass();

    LibraryMethod libraryMethod = new LibraryMethod(1, "Name", "Descriptor");
    libraryMethod.setProcessingInfo("Processing Info");

    // Act
    memberNameCleaner.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    assertNull(libraryMethod.getProcessingInfo());
  }
}
