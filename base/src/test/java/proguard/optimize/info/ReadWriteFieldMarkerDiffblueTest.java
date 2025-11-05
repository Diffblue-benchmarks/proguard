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
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.Field;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.StringConstant;
import proguard.classfile.visitor.MemberVisitor;

class ReadWriteFieldMarkerDiffblueTest {
  /**
   * Method under test:
   * {@link ReadWriteFieldMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant() {
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
   * Method under test:
   * {@link ReadWriteFieldMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant2() {
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
   * Method under test:
   * {@link ReadWriteFieldMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant3() {
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
   * Method under test:
   * {@link ReadWriteFieldMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant4() {
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
   * Method under test:
   * {@link ReadWriteFieldMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant5() {
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
   * Method under test:
   * {@link ReadWriteFieldMarker#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  void testVisitStringConstant6() {
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
   * Method under test:
   * {@link ReadWriteFieldMarker#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  void testVisitFieldrefConstant() {
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
   * Method under test:
   * {@link ReadWriteFieldMarker#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  void testVisitFieldrefConstant2() {
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
   * Method under test:
   * {@link ReadWriteFieldMarker#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  void testVisitFieldrefConstant3() {
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
   * Method under test:
   * {@link ReadWriteFieldMarker#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  void testVisitFieldrefConstant4() {
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
   * Method under test:
   * {@link ReadWriteFieldMarker#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  void testVisitFieldrefConstant5() {
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
   * Method under test:
   * {@link ReadWriteFieldMarker#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  void testVisitFieldrefConstant6() {
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
   * Method under test:
   * {@link ReadWriteFieldMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField() {
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
   * Method under test:
   * {@link ReadWriteFieldMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField2() {
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
   * Method under test:
   * {@link ReadWriteFieldMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField3() {
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
   * Method under test:
   * {@link ReadWriteFieldMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField4() {
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
   * Method under test:
   * {@link ReadWriteFieldMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField5() {
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
   * Method under test: {@link ReadWriteFieldMarker#isRead(Field)}
   */
  @Test
  void testIsRead() {
    // Arrange
    LibraryField field = new LibraryField(1, "Name", "Descriptor");
    field.setProcessingInfo(new FieldOptimizationInfo());

    // Act and Assert
    assertTrue(ReadWriteFieldMarker.isRead(field));
  }

  /**
   * Method under test: {@link ReadWriteFieldMarker#isRead(Field)}
   */
  @Test
  void testIsRead2() {
    // Arrange
    LibraryField field = new LibraryField(1, "Name", "Descriptor");
    LibraryClass clazz = new LibraryClass();
    field.setProcessingInfo(new ProgramFieldOptimizationInfo(clazz, new LibraryField(1, "Name", "Descriptor"), true));

    // Act and Assert
    assertFalse(ReadWriteFieldMarker.isRead(field));
  }

  /**
   * Method under test: {@link ReadWriteFieldMarker#isWritten(Field)}
   */
  @Test
  void testIsWritten() {
    // Arrange
    LibraryField field = new LibraryField(1, "Name", "Descriptor");
    field.setProcessingInfo(new FieldOptimizationInfo());

    // Act and Assert
    assertTrue(ReadWriteFieldMarker.isWritten(field));
  }

  /**
   * Method under test: {@link ReadWriteFieldMarker#isWritten(Field)}
   */
  @Test
  void testIsWritten2() {
    // Arrange
    LibraryField field = new LibraryField(1, "Name", "Descriptor");
    LibraryClass clazz = new LibraryClass();
    field.setProcessingInfo(new ProgramFieldOptimizationInfo(clazz, new LibraryField(1, "Name", "Descriptor"), true));

    // Act and Assert
    assertFalse(ReadWriteFieldMarker.isWritten(field));
  }
}
