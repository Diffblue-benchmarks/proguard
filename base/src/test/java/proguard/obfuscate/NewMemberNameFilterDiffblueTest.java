package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMethod;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;
import proguard.classfile.visitor.MemberVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.obfuscate.kotlin.KotlinValueParameterUsageMarker;
import proguard.optimize.KeepMarker;
import proguard.optimize.info.FieldOptimizationInfo;

class NewMemberNameFilterDiffblueTest {
  /**
   * Method under test:
   * {@link NewMemberNameFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();
    ProgramField programField = new ProgramField();

    // Act
    newMemberNameFilter.visitProgramField(programClass, programField);

    // Assert that nothing has changed
    assertNull(programField.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link NewMemberNameFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField2() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();

    ProgramField programField = new ProgramField();
    programField.setProcessingInfo("Processing Info");

    // Act
    newMemberNameFilter.visitProgramField(programClass, programField);

    // Assert
    assertEquals("Processing Info", programField.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link NewMemberNameFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  void testVisitProgramField3() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new MemberNameCleaner());
    ProgramClass programClass = new ProgramClass();

    ProgramField programField = new ProgramField();
    programField.setProcessingInfo("Processing Info");

    // Act
    newMemberNameFilter.visitProgramField(programClass, programField);

    // Assert
    assertNull(programField.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link NewMemberNameFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new KotlinAnnotationCounter());
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");
    when(programClass.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    newMemberNameFilter.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).getString(eq(0));
    verify(programClass).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link NewMemberNameFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod2() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new KotlinAnnotationCounter());
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("<init>");
    when(programClass.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    newMemberNameFilter.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).getString(eq(0));
    verify(programClass).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link NewMemberNameFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod3() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new KotlinAnnotationCounter());
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn("Processing Info");
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doNothing().when(programMethod).accept(Mockito.<Clazz>any(), Mockito.<MemberVisitor>any());
    when(programMethod.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    newMemberNameFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod).accept(isA(Clazz.class), isA(MemberVisitor.class));
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link NewMemberNameFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  void testVisitProgramMethod4() {
    // Arrange
    MemberVisitor memberVisitor = mock(MemberVisitor.class);
    doNothing().when(memberVisitor).visitProgramMethod(Mockito.<ProgramClass>any(), Mockito.<ProgramMethod>any());
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(memberVisitor);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn("Processing Info");
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn("Processing Info");

    // Act
    newMemberNameFilter.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(memberVisitor).visitProgramMethod(isA(ProgramClass.class), isA(ProgramMethod.class));
    verify(programMethod, atLeast(1)).getProcessingInfo();
  }

  /**
   * Method under test:
   * {@link NewMemberNameFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  void testVisitLibraryField() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();
    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");

    // Act
    newMemberNameFilter.visitLibraryField(libraryClass, libraryField);

    // Assert that nothing has changed
    assertEquals("Name", libraryField.name);
  }

  /**
   * Method under test:
   * {@link NewMemberNameFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  void testVisitLibraryField2() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo("Library Field");

    // Act
    newMemberNameFilter.visitLibraryField(libraryClass, libraryField);

    // Assert
    assertEquals("Library Field", libraryField.getProcessingInfo());
    assertEquals("Name", libraryField.name);
  }

  /**
   * Method under test:
   * {@link NewMemberNameFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  void testVisitLibraryField3() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo(null);

    LibraryField libraryField2 = new LibraryField(1, "Name", "Descriptor");
    libraryField2.setProcessingInfo(libraryField);

    // Act
    newMemberNameFilter.visitLibraryField(libraryClass, libraryField2);

    // Assert
    Object processingInfo = libraryField2.getProcessingInfo();
    assertTrue(processingInfo instanceof LibraryField);
    assertEquals("Name", libraryField2.name);
    assertSame(libraryField, processingInfo);
  }

  /**
   * Method under test:
   * {@link NewMemberNameFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  void testVisitLibraryField4() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new ClassRenamer());
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo("Library Field");

    // Act
    newMemberNameFilter.visitLibraryField(libraryClass, libraryField);

    // Assert
    assertEquals("Library Field", libraryField.name);
    assertEquals("Library Field", libraryField.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link NewMemberNameFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  void testVisitLibraryField5() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new MemberNameCleaner());
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo("Library Field");

    // Act
    newMemberNameFilter.visitLibraryField(libraryClass, libraryField);

    // Assert
    assertEquals("Name", libraryField.name);
    assertNull(libraryField.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link NewMemberNameFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  void testVisitLibraryField6() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new NameMarker());
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo("Library Field");

    // Act
    newMemberNameFilter.visitLibraryField(libraryClass, libraryField);

    // Assert
    assertEquals("Name", libraryField.name);
    assertEquals("Name", libraryField.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link NewMemberNameFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  void testVisitLibraryField7() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new KotlinValueParameterUsageMarker());
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo("Library Field");

    // Act
    newMemberNameFilter.visitLibraryField(libraryClass, libraryField);

    // Assert that nothing has changed
    assertEquals("Library Field", libraryField.getProcessingInfo());
    assertEquals("Name", libraryField.name);
  }

  /**
   * Method under test:
   * {@link NewMemberNameFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  void testVisitLibraryField8() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new KeepMarker());
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo("Library Field");

    // Act
    newMemberNameFilter.visitLibraryField(libraryClass, libraryField);

    // Assert
    Object processingInfo = libraryField.getProcessingInfo();
    assertTrue(processingInfo instanceof FieldOptimizationInfo);
    assertEquals("Name", libraryField.name);
    assertNull(((FieldOptimizationInfo) processingInfo).getReferencedClass());
    assertNull(((FieldOptimizationInfo) processingInfo).getValue());
    assertTrue(((FieldOptimizationInfo) processingInfo).isKept());
    assertTrue(((FieldOptimizationInfo) processingInfo).isRead());
    assertTrue(((FieldOptimizationInfo) processingInfo).isWritten());
  }

  /**
   * Method under test:
   * {@link NewMemberNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();
    LibraryMethod libraryMethod = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    newMemberNameFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert that nothing has changed
    assertEquals("Name", libraryMethod.name);
  }

  /**
   * Method under test:
   * {@link NewMemberNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod2() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new KotlinAnnotationCounter());

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.setProcessingInfo("Processing Info");
    LibraryMethod libraryMethod = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    newMemberNameFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert that nothing has changed
    assertEquals("Name", libraryMethod.name);
  }

  /**
   * Method under test:
   * {@link NewMemberNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod3() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();

    LibraryMethod libraryMethod = new LibraryMethod(1, "Name", "Descriptor");
    libraryMethod.setProcessingInfo("Processing Info");

    // Act
    newMemberNameFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    assertEquals("Name", libraryMethod.name);
    assertEquals("Processing Info", libraryMethod.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link NewMemberNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod4() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new ClassRenamer());
    LibraryClass libraryClass = new LibraryClass();

    LibraryMethod libraryMethod = new LibraryMethod(1, "Name", "Descriptor");
    libraryMethod.setProcessingInfo("Processing Info");

    // Act
    newMemberNameFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    assertEquals("Processing Info", libraryMethod.name);
    assertEquals("Processing Info", libraryMethod.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link NewMemberNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod5() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new MemberNameCleaner());
    LibraryClass libraryClass = new LibraryClass();

    LibraryMethod libraryMethod = new LibraryMethod(1, "Name", "Descriptor");
    libraryMethod.setProcessingInfo("Processing Info");

    // Act
    newMemberNameFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    assertEquals("Name", libraryMethod.name);
    assertNull(libraryMethod.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link NewMemberNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod6() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new NameMarker());
    LibraryClass libraryClass = new LibraryClass();

    LibraryMethod libraryMethod = new LibraryMethod(1, "Name", "Descriptor");
    libraryMethod.setProcessingInfo("Processing Info");

    // Act
    newMemberNameFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    assertEquals("Name", libraryMethod.name);
    assertEquals("Name", libraryMethod.getProcessingInfo());
  }

  /**
   * Method under test:
   * {@link NewMemberNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  void testVisitLibraryMethod7() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new NameMarker());
    LibraryClass libraryClass = new LibraryClass();

    LibraryMethod libraryMethod = new LibraryMethod(1, "Name", "Descriptor");
    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");

    libraryMethod.setProcessingInfo(libraryField);

    // Act
    newMemberNameFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    Object processingInfo = libraryMethod.getProcessingInfo();
    assertTrue(processingInfo instanceof LibraryField);
    assertEquals("Name", libraryMethod.name);
    assertSame(libraryField, processingInfo);
  }
}
