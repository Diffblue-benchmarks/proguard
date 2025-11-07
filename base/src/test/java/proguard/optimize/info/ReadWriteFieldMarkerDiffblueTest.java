package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.Field;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMember;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.StringConstant;
import proguard.classfile.visitor.MemberVisitor;

class ReadWriteFieldMarkerDiffblueTest {
  /**
   * Test {@link ReadWriteFieldMarker#visitStringConstant(Clazz, StringConstant)}.
   * <p>
   * Method under test: {@link ReadWriteFieldMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReadWriteFieldMarker.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant() {
    // Arrange
    ReadWriteFieldMarker readWriteFieldMarker = new ReadWriteFieldMarker(new MutableBoolean(), false, true);
    LibraryClass clazz = new LibraryClass();
    FieldOptimizationInfo fieldOptimizationInfo = mock(FieldOptimizationInfo.class);
    when(fieldOptimizationInfo.isWritten()).thenReturn(true);

    ProgramField programField = new ProgramField();
    programField.setProcessingInfo(fieldOptimizationInfo);
    StringConstant stringConstant = new StringConstant();
    stringConstant.referencedMember = programField;

    // Act
    readWriteFieldMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(fieldOptimizationInfo).isWritten();
  }

  /**
   * Test {@link ReadWriteFieldMarker#visitStringConstant(Clazz, StringConstant)}.
   * <ul>
   *   <li>Given {@link FieldOptimizationInfo} {@link FieldOptimizationInfo#isRead()} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadWriteFieldMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant); given FieldOptimizationInfo isRead() return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReadWriteFieldMarker.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant_givenFieldOptimizationInfoIsReadReturnFalse() {
    // Arrange
    ReadWriteFieldMarker readWriteFieldMarker = new ReadWriteFieldMarker(new MutableBoolean());
    LibraryClass clazz = new LibraryClass();
    FieldOptimizationInfo fieldOptimizationInfo = mock(FieldOptimizationInfo.class);
    when(fieldOptimizationInfo.isRead()).thenReturn(false);
    when(fieldOptimizationInfo.isWritten()).thenReturn(true);

    ProgramField programField = new ProgramField();
    programField.setProcessingInfo(fieldOptimizationInfo);
    StringConstant stringConstant = new StringConstant();
    stringConstant.referencedMember = programField;

    // Act
    readWriteFieldMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(fieldOptimizationInfo).isRead();
    verify(fieldOptimizationInfo).isWritten();
  }

  /**
   * Test {@link ReadWriteFieldMarker#visitStringConstant(Clazz, StringConstant)}.
   * <ul>
   *   <li>Given {@link FieldOptimizationInfo} {@link FieldOptimizationInfo#isWritten()} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadWriteFieldMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant); given FieldOptimizationInfo isWritten() return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReadWriteFieldMarker.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant_givenFieldOptimizationInfoIsWrittenReturnFalse() {
    // Arrange
    ReadWriteFieldMarker readWriteFieldMarker = new ReadWriteFieldMarker(new MutableBoolean());
    LibraryClass clazz = new LibraryClass();
    FieldOptimizationInfo fieldOptimizationInfo = mock(FieldOptimizationInfo.class);
    when(fieldOptimizationInfo.isRead()).thenReturn(true);
    when(fieldOptimizationInfo.isWritten()).thenReturn(false);

    ProgramField programField = new ProgramField();
    programField.setProcessingInfo(fieldOptimizationInfo);
    StringConstant stringConstant = new StringConstant();
    stringConstant.referencedMember = programField;

    // Act
    readWriteFieldMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(fieldOptimizationInfo).isRead();
    verify(fieldOptimizationInfo).isWritten();
  }

  /**
   * Test {@link ReadWriteFieldMarker#visitStringConstant(Clazz, StringConstant)}.
   * <ul>
   *   <li>Given {@link LibraryField} {@link LibraryMember#accept(Clazz, MemberVisitor)} does nothing.</li>
   *   <li>Then calls {@link LibraryMember#accept(Clazz, MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadWriteFieldMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant); given LibraryField accept(Clazz, MemberVisitor) does nothing; then calls accept(Clazz, MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReadWriteFieldMarker.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant_givenLibraryFieldAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    ReadWriteFieldMarker readWriteFieldMarker = new ReadWriteFieldMarker(new MutableBoolean());
    LibraryClass clazz = new LibraryClass();
    LibraryField libraryField = mock(LibraryField.class);
    doNothing().when(libraryField).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    StringConstant stringConstant = new StringConstant();
    stringConstant.referencedMember = libraryField;

    // Act
    readWriteFieldMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(libraryField).accept((Clazz) isNull(), isA(MemberVisitor.class));
  }

  /**
   * Test {@link ReadWriteFieldMarker#visitStringConstant(Clazz, StringConstant)}.
   * <ul>
   *   <li>Then calls {@link FieldOptimizationInfo#isRead()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadWriteFieldMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant); then calls isRead()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReadWriteFieldMarker.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant_thenCallsIsRead() {
    // Arrange
    ReadWriteFieldMarker readWriteFieldMarker = new ReadWriteFieldMarker(new MutableBoolean());
    LibraryClass clazz = new LibraryClass();
    FieldOptimizationInfo fieldOptimizationInfo = mock(FieldOptimizationInfo.class);
    when(fieldOptimizationInfo.isRead()).thenReturn(true);
    when(fieldOptimizationInfo.isWritten()).thenReturn(true);

    ProgramField programField = new ProgramField();
    programField.setProcessingInfo(fieldOptimizationInfo);
    StringConstant stringConstant = new StringConstant();
    stringConstant.referencedMember = programField;

    // Act
    readWriteFieldMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(fieldOptimizationInfo).isRead();
    verify(fieldOptimizationInfo).isWritten();
  }

  /**
   * Test {@link ReadWriteFieldMarker#visitStringConstant(Clazz, StringConstant)}.
   * <ul>
   *   <li>Then calls {@link StringConstant#referencedMemberAccept(MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadWriteFieldMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant); then calls referencedMemberAccept(MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReadWriteFieldMarker.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant_thenCallsReferencedMemberAccept() {
    // Arrange
    ReadWriteFieldMarker readWriteFieldMarker = new ReadWriteFieldMarker(new MutableBoolean());
    LibraryClass clazz = new LibraryClass();
    StringConstant stringConstant = mock(StringConstant.class);
    doNothing().when(stringConstant).referencedMemberAccept(Mockito.<MemberVisitor>any());

    // Act
    readWriteFieldMarker.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(stringConstant).referencedMemberAccept(isA(MemberVisitor.class));
  }

  /**
   * Test {@link ReadWriteFieldMarker#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   * <p>
   * Method under test: {@link ReadWriteFieldMarker#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  @DisplayName("Test visitFieldrefConstant(Clazz, FieldrefConstant)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReadWriteFieldMarker.visitFieldrefConstant(Clazz, FieldrefConstant)"})
  void testVisitFieldrefConstant() {
    // Arrange
    ReadWriteFieldMarker readWriteFieldMarker = new ReadWriteFieldMarker(new MutableBoolean(), false, true);
    LibraryClass clazz = new LibraryClass();
    FieldOptimizationInfo fieldOptimizationInfo = mock(FieldOptimizationInfo.class);
    when(fieldOptimizationInfo.isWritten()).thenReturn(true);

    ProgramField programField = new ProgramField();
    programField.setProcessingInfo(fieldOptimizationInfo);
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    fieldrefConstant.referencedField = programField;

    // Act
    readWriteFieldMarker.visitFieldrefConstant(clazz, fieldrefConstant);

    // Assert
    verify(fieldOptimizationInfo).isWritten();
  }

  /**
   * Test {@link ReadWriteFieldMarker#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   * <ul>
   *   <li>Given {@link FieldOptimizationInfo} {@link FieldOptimizationInfo#isRead()} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadWriteFieldMarker#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  @DisplayName("Test visitFieldrefConstant(Clazz, FieldrefConstant); given FieldOptimizationInfo isRead() return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReadWriteFieldMarker.visitFieldrefConstant(Clazz, FieldrefConstant)"})
  void testVisitFieldrefConstant_givenFieldOptimizationInfoIsReadReturnFalse() {
    // Arrange
    ReadWriteFieldMarker readWriteFieldMarker = new ReadWriteFieldMarker(new MutableBoolean());
    LibraryClass clazz = new LibraryClass();
    FieldOptimizationInfo fieldOptimizationInfo = mock(FieldOptimizationInfo.class);
    when(fieldOptimizationInfo.isRead()).thenReturn(false);
    when(fieldOptimizationInfo.isWritten()).thenReturn(true);

    ProgramField programField = new ProgramField();
    programField.setProcessingInfo(fieldOptimizationInfo);
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    fieldrefConstant.referencedField = programField;

    // Act
    readWriteFieldMarker.visitFieldrefConstant(clazz, fieldrefConstant);

    // Assert
    verify(fieldOptimizationInfo).isRead();
    verify(fieldOptimizationInfo).isWritten();
  }

  /**
   * Test {@link ReadWriteFieldMarker#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   * <ul>
   *   <li>Given {@link FieldOptimizationInfo} {@link FieldOptimizationInfo#isWritten()} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadWriteFieldMarker#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  @DisplayName("Test visitFieldrefConstant(Clazz, FieldrefConstant); given FieldOptimizationInfo isWritten() return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReadWriteFieldMarker.visitFieldrefConstant(Clazz, FieldrefConstant)"})
  void testVisitFieldrefConstant_givenFieldOptimizationInfoIsWrittenReturnFalse() {
    // Arrange
    ReadWriteFieldMarker readWriteFieldMarker = new ReadWriteFieldMarker(new MutableBoolean());
    LibraryClass clazz = new LibraryClass();
    FieldOptimizationInfo fieldOptimizationInfo = mock(FieldOptimizationInfo.class);
    when(fieldOptimizationInfo.isRead()).thenReturn(true);
    when(fieldOptimizationInfo.isWritten()).thenReturn(false);

    ProgramField programField = new ProgramField();
    programField.setProcessingInfo(fieldOptimizationInfo);
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    fieldrefConstant.referencedField = programField;

    // Act
    readWriteFieldMarker.visitFieldrefConstant(clazz, fieldrefConstant);

    // Assert
    verify(fieldOptimizationInfo).isRead();
    verify(fieldOptimizationInfo).isWritten();
  }

  /**
   * Test {@link ReadWriteFieldMarker#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   * <ul>
   *   <li>Given {@link LibraryField} {@link LibraryMember#accept(Clazz, MemberVisitor)} does nothing.</li>
   *   <li>Then calls {@link LibraryMember#accept(Clazz, MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadWriteFieldMarker#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  @DisplayName("Test visitFieldrefConstant(Clazz, FieldrefConstant); given LibraryField accept(Clazz, MemberVisitor) does nothing; then calls accept(Clazz, MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReadWriteFieldMarker.visitFieldrefConstant(Clazz, FieldrefConstant)"})
  void testVisitFieldrefConstant_givenLibraryFieldAcceptDoesNothing_thenCallsAccept() {
    // Arrange
    ReadWriteFieldMarker readWriteFieldMarker = new ReadWriteFieldMarker(new MutableBoolean());
    LibraryClass clazz = new LibraryClass();
    LibraryField libraryField = mock(LibraryField.class);
    doNothing().when(libraryField).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    fieldrefConstant.referencedField = libraryField;

    // Act
    readWriteFieldMarker.visitFieldrefConstant(clazz, fieldrefConstant);

    // Assert
    verify(libraryField).accept((Clazz) isNull(), isA(MemberVisitor.class));
  }

  /**
   * Test {@link ReadWriteFieldMarker#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   * <ul>
   *   <li>Then calls {@link FieldOptimizationInfo#isRead()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadWriteFieldMarker#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  @DisplayName("Test visitFieldrefConstant(Clazz, FieldrefConstant); then calls isRead()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReadWriteFieldMarker.visitFieldrefConstant(Clazz, FieldrefConstant)"})
  void testVisitFieldrefConstant_thenCallsIsRead() {
    // Arrange
    ReadWriteFieldMarker readWriteFieldMarker = new ReadWriteFieldMarker(new MutableBoolean());
    LibraryClass clazz = new LibraryClass();
    FieldOptimizationInfo fieldOptimizationInfo = mock(FieldOptimizationInfo.class);
    when(fieldOptimizationInfo.isRead()).thenReturn(true);
    when(fieldOptimizationInfo.isWritten()).thenReturn(true);

    ProgramField programField = new ProgramField();
    programField.setProcessingInfo(fieldOptimizationInfo);
    FieldrefConstant fieldrefConstant = new FieldrefConstant();
    fieldrefConstant.referencedField = programField;

    // Act
    readWriteFieldMarker.visitFieldrefConstant(clazz, fieldrefConstant);

    // Assert
    verify(fieldOptimizationInfo).isRead();
    verify(fieldOptimizationInfo).isWritten();
  }

  /**
   * Test {@link ReadWriteFieldMarker#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   * <ul>
   *   <li>Then calls {@link FieldrefConstant#referencedFieldAccept(MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadWriteFieldMarker#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  @DisplayName("Test visitFieldrefConstant(Clazz, FieldrefConstant); then calls referencedFieldAccept(MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReadWriteFieldMarker.visitFieldrefConstant(Clazz, FieldrefConstant)"})
  void testVisitFieldrefConstant_thenCallsReferencedFieldAccept() {
    // Arrange
    ReadWriteFieldMarker readWriteFieldMarker = new ReadWriteFieldMarker(new MutableBoolean());
    LibraryClass clazz = new LibraryClass();
    FieldrefConstant fieldrefConstant = mock(FieldrefConstant.class);
    doNothing().when(fieldrefConstant).referencedFieldAccept(Mockito.<MemberVisitor>any());

    // Act
    readWriteFieldMarker.visitFieldrefConstant(clazz, fieldrefConstant);

    // Assert
    verify(fieldrefConstant).referencedFieldAccept(isA(MemberVisitor.class));
  }

  /**
   * Test {@link ReadWriteFieldMarker#visitProgramField(ProgramClass, ProgramField)}.
   * <p>
   * Method under test: {@link ReadWriteFieldMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReadWriteFieldMarker.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField() {
    // Arrange
    ReadWriteFieldMarker readWriteFieldMarker = new ReadWriteFieldMarker(new MutableBoolean(), false, true);
    ProgramClass programClass = new ProgramClass();
    FieldOptimizationInfo fieldOptimizationInfo = mock(FieldOptimizationInfo.class);
    when(fieldOptimizationInfo.isWritten()).thenReturn(true);
    ProgramField programField = mock(ProgramField.class);
    when(programField.getProcessingInfo()).thenReturn(fieldOptimizationInfo);

    // Act
    readWriteFieldMarker.visitProgramField(programClass, programField);

    // Assert
    verify(fieldOptimizationInfo).isWritten();
    verify(programField).getProcessingInfo();
  }

  /**
   * Test {@link ReadWriteFieldMarker#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Given {@link FieldOptimizationInfo} (default constructor).</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadWriteFieldMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); given FieldOptimizationInfo (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReadWriteFieldMarker.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_givenFieldOptimizationInfo() {
    // Arrange
    ReadWriteFieldMarker readWriteFieldMarker = new ReadWriteFieldMarker(new MutableBoolean());
    ProgramClass programClass = new ProgramClass();
    ProgramField programField = mock(ProgramField.class);
    when(programField.getProcessingInfo()).thenReturn(new FieldOptimizationInfo());

    // Act
    readWriteFieldMarker.visitProgramField(programClass, programField);

    // Assert
    verify(programField, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link ReadWriteFieldMarker#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Given {@link FieldOptimizationInfo} {@link FieldOptimizationInfo#isRead()} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadWriteFieldMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); given FieldOptimizationInfo isRead() return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReadWriteFieldMarker.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_givenFieldOptimizationInfoIsReadReturnFalse() {
    // Arrange
    ReadWriteFieldMarker readWriteFieldMarker = new ReadWriteFieldMarker(new MutableBoolean());
    ProgramClass programClass = new ProgramClass();
    FieldOptimizationInfo fieldOptimizationInfo = mock(FieldOptimizationInfo.class);
    when(fieldOptimizationInfo.isRead()).thenReturn(false);
    when(fieldOptimizationInfo.isWritten()).thenReturn(true);
    ProgramField programField = mock(ProgramField.class);
    when(programField.getProcessingInfo()).thenReturn(fieldOptimizationInfo);

    // Act
    readWriteFieldMarker.visitProgramField(programClass, programField);

    // Assert
    verify(fieldOptimizationInfo).isRead();
    verify(fieldOptimizationInfo).isWritten();
    verify(programField, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link ReadWriteFieldMarker#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Given {@link FieldOptimizationInfo} {@link FieldOptimizationInfo#isRead()} return {@code true}.</li>
   *   <li>Then calls {@link FieldOptimizationInfo#isRead()}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadWriteFieldMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); given FieldOptimizationInfo isRead() return 'true'; then calls isRead()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReadWriteFieldMarker.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_givenFieldOptimizationInfoIsReadReturnTrue_thenCallsIsRead() {
    // Arrange
    ReadWriteFieldMarker readWriteFieldMarker = new ReadWriteFieldMarker(new MutableBoolean());
    ProgramClass programClass = new ProgramClass();
    FieldOptimizationInfo fieldOptimizationInfo = mock(FieldOptimizationInfo.class);
    when(fieldOptimizationInfo.isRead()).thenReturn(true);
    when(fieldOptimizationInfo.isWritten()).thenReturn(true);
    ProgramField programField = mock(ProgramField.class);
    when(programField.getProcessingInfo()).thenReturn(fieldOptimizationInfo);

    // Act
    readWriteFieldMarker.visitProgramField(programClass, programField);

    // Assert
    verify(fieldOptimizationInfo).isRead();
    verify(fieldOptimizationInfo).isWritten();
    verify(programField, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link ReadWriteFieldMarker#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Given {@link FieldOptimizationInfo} {@link FieldOptimizationInfo#isWritten()} return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadWriteFieldMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); given FieldOptimizationInfo isWritten() return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReadWriteFieldMarker.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_givenFieldOptimizationInfoIsWrittenReturnFalse() {
    // Arrange
    ReadWriteFieldMarker readWriteFieldMarker = new ReadWriteFieldMarker(new MutableBoolean());
    ProgramClass programClass = new ProgramClass();
    FieldOptimizationInfo fieldOptimizationInfo = mock(FieldOptimizationInfo.class);
    when(fieldOptimizationInfo.isRead()).thenReturn(true);
    when(fieldOptimizationInfo.isWritten()).thenReturn(false);
    ProgramField programField = mock(ProgramField.class);
    when(programField.getProcessingInfo()).thenReturn(fieldOptimizationInfo);

    // Act
    readWriteFieldMarker.visitProgramField(programClass, programField);

    // Assert
    verify(fieldOptimizationInfo).isRead();
    verify(fieldOptimizationInfo).isWritten();
    verify(programField, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link ReadWriteFieldMarker#isRead(Field)}.
   * <ul>
   *   <li>Given {@link FieldOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadWriteFieldMarker#isRead(Field)}
   */
  @Test
  @DisplayName("Test isRead(Field); given FieldOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ReadWriteFieldMarker.isRead(Field)"})
  void testIsRead_givenFieldOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryField field = new LibraryField(1, "Name", "Descriptor");
    field.setProcessingInfo(new FieldOptimizationInfo());

    // Act and Assert
    assertTrue(ReadWriteFieldMarker.isRead(field));
  }

  /**
   * Test {@link ReadWriteFieldMarker#isRead(Field)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadWriteFieldMarker#isRead(Field)}
   */
  @Test
  @DisplayName("Test isRead(Field); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ReadWriteFieldMarker.isRead(Field)"})
  void testIsRead_thenReturnFalse() {
    // Arrange
    LibraryField field = new LibraryField(1, "Name", "Descriptor");
    LibraryClass clazz = new LibraryClass();
    field.setProcessingInfo(new ProgramFieldOptimizationInfo(clazz, new LibraryField(1, "Name", "Descriptor"), true));

    // Act and Assert
    assertFalse(ReadWriteFieldMarker.isRead(field));
  }

  /**
   * Test {@link ReadWriteFieldMarker#isWritten(Field)}.
   * <ul>
   *   <li>Given {@link FieldOptimizationInfo} (default constructor).</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadWriteFieldMarker#isWritten(Field)}
   */
  @Test
  @DisplayName("Test isWritten(Field); given FieldOptimizationInfo (default constructor); then return 'true'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ReadWriteFieldMarker.isWritten(Field)"})
  void testIsWritten_givenFieldOptimizationInfo_thenReturnTrue() {
    // Arrange
    LibraryField field = new LibraryField(1, "Name", "Descriptor");
    field.setProcessingInfo(new FieldOptimizationInfo());

    // Act and Assert
    assertTrue(ReadWriteFieldMarker.isWritten(field));
  }

  /**
   * Test {@link ReadWriteFieldMarker#isWritten(Field)}.
   * <ul>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReadWriteFieldMarker#isWritten(Field)}
   */
  @Test
  @DisplayName("Test isWritten(Field); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ReadWriteFieldMarker.isWritten(Field)"})
  void testIsWritten_thenReturnFalse() {
    // Arrange
    LibraryField field = new LibraryField(1, "Name", "Descriptor");
    LibraryClass clazz = new LibraryClass();
    field.setProcessingInfo(new ProgramFieldOptimizationInfo(clazz, new LibraryField(1, "Name", "Descriptor"), true));

    // Act and Assert
    assertFalse(ReadWriteFieldMarker.isWritten(field));
  }
}
