package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertNull;
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

class MemberNameCleanerDiffblueTest {
  /**
   * Test {@link MemberNameCleaner#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Given {@code Processing Info}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberNameCleaner#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); given 'Processing Info'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.MemberNameCleaner.visitProgramField(proguard.classfile.ProgramClass, proguard.classfile.ProgramField)"})
  void testVisitProgramField_givenProcessingInfo() {
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
   * Test {@link MemberNameCleaner#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>When {@link ProgramField#ProgramField()}.</li>
   *   <li>Then {@link ProgramField#ProgramField()} ProcessingInfo is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberNameCleaner#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); when ProgramField(); then ProgramField() ProcessingInfo is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.MemberNameCleaner.visitProgramField(proguard.classfile.ProgramClass, proguard.classfile.ProgramField)"})
  void testVisitProgramField_whenProgramField_thenProgramFieldProcessingInfoIsNull() {
    // Arrange
    MemberNameCleaner memberNameCleaner = new MemberNameCleaner();
    ProgramClass programClass = new ProgramClass();
    ProgramField programField = new ProgramField();

    // Act
    memberNameCleaner.visitProgramField(programClass, programField);

    // Assert that nothing has changed
    assertNull(programField.getProcessingInfo());
  }

  /**
   * Test {@link MemberNameCleaner#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code Processing Info}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberNameCleaner#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given 'Processing Info'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.MemberNameCleaner.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_givenProcessingInfo() {
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
   * Test {@link MemberNameCleaner#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>When {@link ProgramMethod#ProgramMethod()}.</li>
   *   <li>Then {@link ProgramMethod#ProgramMethod()} ProcessingInfo is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberNameCleaner#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); when ProgramMethod(); then ProgramMethod() ProcessingInfo is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.MemberNameCleaner.visitProgramMethod(proguard.classfile.ProgramClass, proguard.classfile.ProgramMethod)"})
  void testVisitProgramMethod_whenProgramMethod_thenProgramMethodProcessingInfoIsNull() {
    // Arrange
    MemberNameCleaner memberNameCleaner = new MemberNameCleaner();
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = new ProgramMethod();

    // Act
    memberNameCleaner.visitProgramMethod(programClass, programMethod);

    // Assert that nothing has changed
    assertNull(programMethod.getProcessingInfo());
  }

  /**
   * Test {@link MemberNameCleaner#visitLibraryField(LibraryClass, LibraryField)}.
   * <ul>
   *   <li>Given {@code Processing Info}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberNameCleaner#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField); given 'Processing Info'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.MemberNameCleaner.visitLibraryField(proguard.classfile.LibraryClass, proguard.classfile.LibraryField)"})
  void testVisitLibraryField_givenProcessingInfo() {
    // Arrange
    MemberNameCleaner memberNameCleaner = new MemberNameCleaner();
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo("Processing Info");

    // Act
    memberNameCleaner.visitLibraryField(libraryClass, libraryField);

    // Assert
    assertNull(libraryField.getProcessingInfo());
  }

  /**
   * Test {@link MemberNameCleaner#visitLibraryField(LibraryClass, LibraryField)}.
   * <ul>
   *   <li>When {@link LibraryField#LibraryField(int, String, String)} with u2accessFlags is one and {@code Name} and {@code Descriptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberNameCleaner#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField); when LibraryField(int, String, String) with u2accessFlags is one and 'Name' and 'Descriptor'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.MemberNameCleaner.visitLibraryField(proguard.classfile.LibraryClass, proguard.classfile.LibraryField)"})
  void testVisitLibraryField_whenLibraryFieldWithU2accessFlagsIsOneAndNameAndDescriptor() {
    // Arrange
    MemberNameCleaner memberNameCleaner = new MemberNameCleaner();
    LibraryClass libraryClass = new LibraryClass();
    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");

    // Act
    memberNameCleaner.visitLibraryField(libraryClass, libraryField);

    // Assert that nothing has changed
    assertNull(libraryField.getProcessingInfo());
  }

  /**
   * Test {@link MemberNameCleaner#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <p>
   * Method under test: {@link MemberNameCleaner#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.MemberNameCleaner.visitLibraryMethod(proguard.classfile.LibraryClass, proguard.classfile.LibraryMethod)"})
  void testVisitLibraryMethod() {
    // Arrange
    MemberNameCleaner memberNameCleaner = new MemberNameCleaner();
    LibraryClass libraryClass = new LibraryClass();
    LibraryMethod libraryMethod = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    memberNameCleaner.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert that nothing has changed
    assertNull(libraryMethod.getProcessingInfo());
  }

  /**
   * Test {@link MemberNameCleaner#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <ul>
   *   <li>Given {@code Processing Info}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MemberNameCleaner#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod); given 'Processing Info'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.obfuscate.MemberNameCleaner.visitLibraryMethod(proguard.classfile.LibraryClass, proguard.classfile.LibraryMethod)"})
  void testVisitLibraryMethod_givenProcessingInfo() {
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
