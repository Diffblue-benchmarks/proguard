package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMember;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMember;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.MemberVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;

class ClassRenamerDiffblueTest {
  /**
   * Test {@link ClassRenamer#visitAnyClass(Clazz)}.
   *
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link ClassRenamer#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName(
      "Test visitAnyClass(Clazz); when LibraryClass(); then throw UnsupportedOperationException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassRenamer.visitAnyClass(Clazz)"})
  void testVisitAnyClass_whenLibraryClass_thenThrowUnsupportedOperationException() {
    // Arrange
    ClassRenamer classRenamer = new ClassRenamer();

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> classRenamer.visitAnyClass(new LibraryClass()));
  }

  /**
   * Test {@link ClassRenamer#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Then calls {@link ProgramClass#fieldsAccept(MemberVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link ClassRenamer#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then calls fieldsAccept(MemberVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassRenamer.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_thenCallsFieldsAccept() {
    // Arrange
    ClassRenamer classRenamer = new ClassRenamer();
    ProgramClass programClass = mock(ProgramClass.class);
    doNothing().when(programClass).fieldsAccept(Mockito.<MemberVisitor>any());
    doNothing().when(programClass).methodsAccept(Mockito.<MemberVisitor>any());
    doNothing().when(programClass).thisClassConstantAccept(Mockito.<ConstantVisitor>any());

    // Act
    classRenamer.visitProgramClass(programClass);

    // Assert
    verify(programClass).fieldsAccept(isA(MemberVisitor.class));
    verify(programClass).methodsAccept(isA(MemberVisitor.class));
    verify(programClass).thisClassConstantAccept(isA(ConstantVisitor.class));
  }

  /**
   * Test {@link ClassRenamer#visitLibraryClass(LibraryClass)}.
   *
   * <p>Method under test: {@link ClassRenamer#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassRenamer.visitLibraryClass(LibraryClass)"})
  void testVisitLibraryClass() {
    // Arrange
    ClassRenamer classRenamer = new ClassRenamer(null, new KotlinAnnotationCounter());

    LibraryClass libraryClass = new LibraryClass(1, "This Class Name", "Super Class Name");
    libraryClass.setProcessingInfo(null);

    // Act
    classRenamer.visitLibraryClass(libraryClass);

    // Assert that nothing has changed
    assertEquals("This Class Name", libraryClass.getName());
  }

  /**
   * Test {@link ClassRenamer#visitLibraryClass(LibraryClass)}.
   *
   * <p>Method under test: {@link ClassRenamer#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassRenamer.visitLibraryClass(LibraryClass)"})
  void testVisitLibraryClass2() {
    // Arrange
    ClassRenamer classRenamer = new ClassRenamer(null, new KotlinAnnotationCounter());

    LibraryClass libraryClass = new LibraryClass(1, "This Class Name", "Super Class Name");
    libraryClass.setProcessingInfo("Library Class");

    // Act
    classRenamer.visitLibraryClass(libraryClass);

    // Assert
    assertEquals("Library Class", libraryClass.getName());
  }

  /**
   * Test {@link ClassRenamer#visitLibraryClass(LibraryClass)}.
   *
   * <p>Method under test: {@link ClassRenamer#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassRenamer.visitLibraryClass(LibraryClass)"})
  void testVisitLibraryClass3() {
    // Arrange
    ClassVisitor extraClassVisitor = mock(ClassVisitor.class);
    ClassRenamer classRenamer = new ClassRenamer(extraClassVisitor, new KotlinAnnotationCounter());

    LibraryClass libraryClass = new LibraryClass(1, "Library Class", "Super Class Name");
    libraryClass.setProcessingInfo("Library Class");

    // Act
    classRenamer.visitLibraryClass(libraryClass);

    // Assert that nothing has changed
    assertEquals("Library Class", libraryClass.getName());
  }

  /**
   * Test {@link ClassRenamer#visitLibraryClass(LibraryClass)}.
   *
   * <ul>
   *   <li>Given {@link ClassVisitor} {@link ClassVisitor#visitLibraryClass(LibraryClass)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link ClassRenamer#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName(
      "Test visitLibraryClass(LibraryClass); given ClassVisitor visitLibraryClass(LibraryClass) does nothing")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassRenamer.visitLibraryClass(LibraryClass)"})
  void testVisitLibraryClass_givenClassVisitorVisitLibraryClassDoesNothing() {
    // Arrange
    ClassVisitor extraClassVisitor = mock(ClassVisitor.class);
    doNothing().when(extraClassVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    ClassRenamer classRenamer = new ClassRenamer(extraClassVisitor, new KotlinAnnotationCounter());

    LibraryClass libraryClass = new LibraryClass(1, "This Class Name", "Super Class Name");
    libraryClass.setProcessingInfo("Library Class");

    // Act
    classRenamer.visitLibraryClass(libraryClass);

    // Assert
    verify(extraClassVisitor).visitLibraryClass(isA(LibraryClass.class));
    assertEquals("Library Class", libraryClass.getName());
  }

  /**
   * Test {@link ClassRenamer#visitLibraryClass(LibraryClass)}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link ClassRenamer#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass); then throw UnsupportedOperationException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassRenamer.visitLibraryClass(LibraryClass)"})
  void testVisitLibraryClass_thenThrowUnsupportedOperationException() {
    // Arrange
    ClassVisitor extraClassVisitor = mock(ClassVisitor.class);
    doThrow(new UnsupportedOperationException("Library Class"))
        .when(extraClassVisitor)
        .visitLibraryClass(Mockito.<LibraryClass>any());
    ClassRenamer classRenamer = new ClassRenamer(extraClassVisitor, new KotlinAnnotationCounter());

    LibraryClass libraryClass = new LibraryClass(1, "This Class Name", "Super Class Name");
    libraryClass.setProcessingInfo("Library Class");

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> classRenamer.visitLibraryClass(libraryClass));
    verify(extraClassVisitor).visitLibraryClass(isA(LibraryClass.class));
  }

  /**
   * Test {@link ClassRenamer#visitProgramMember(ProgramClass, ProgramMember)}.
   *
   * <ul>
   *   <li>Given {@code Name}.
   *   <li>When {@link ProgramClass}.
   *   <li>Then calls {@link ProgramMember#getName(Clazz)}.
   * </ul>
   *
   * <p>Method under test: {@link ClassRenamer#visitProgramMember(ProgramClass, ProgramMember)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMember(ProgramClass, ProgramMember); given 'Name'; when ProgramClass; then calls getName(Clazz)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassRenamer.visitProgramMember(ProgramClass, ProgramMember)"})
  void testVisitProgramMember_givenName_whenProgramClass_thenCallsGetName() {
    // Arrange
    ClassRenamer classRenamer = new ClassRenamer();
    ProgramClass programClass = mock(ProgramClass.class);
    ProgramMember programMember = mock(ProgramMember.class);
    when(programMember.getProcessingInfo()).thenReturn("Name");
    when(programMember.getName(Mockito.<Clazz>any())).thenReturn("Name");

    // Act
    classRenamer.visitProgramMember(programClass, programMember);

    // Assert
    verify(programMember).getName(isA(Clazz.class));
    verify(programMember, atLeast(1)).getProcessingInfo();
  }

  /**
   * Test {@link ClassRenamer#visitProgramMember(ProgramClass, ProgramMember)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   *   <li>Then calls {@link ProgramClass#getString(int)}.
   * </ul>
   *
   * <p>Method under test: {@link ClassRenamer#visitProgramMember(ProgramClass, ProgramMember)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMember(ProgramClass, ProgramMember); given 'String'; then calls getString(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassRenamer.visitProgramMember(ProgramClass, ProgramMember)"})
  void testVisitProgramMember_givenString_thenCallsGetString() {
    // Arrange
    ClassRenamer classRenamer = new ClassRenamer();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    classRenamer.visitProgramMember(programClass, new ProgramField());

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Test {@link ClassRenamer#visitLibraryMember(LibraryClass, LibraryMember)}.
   *
   * <p>Method under test: {@link ClassRenamer#visitLibraryMember(LibraryClass, LibraryMember)}
   */
  @Test
  @DisplayName("Test visitLibraryMember(LibraryClass, LibraryMember)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassRenamer.visitLibraryMember(LibraryClass, LibraryMember)"})
  void testVisitLibraryMember() {
    // Arrange
    ClassRenamer classRenamer = new ClassRenamer();
    LibraryClass libraryClass = new LibraryClass();
    LibraryField libraryMember = new LibraryField(1, "Name", "Descriptor");

    // Act
    classRenamer.visitLibraryMember(libraryClass, libraryMember);

    // Assert that nothing has changed
    assertEquals("Name", libraryMember.name);
  }

  /**
   * Test {@link ClassRenamer#visitLibraryMember(LibraryClass, LibraryMember)}.
   *
   * <p>Method under test: {@link ClassRenamer#visitLibraryMember(LibraryClass, LibraryMember)}
   */
  @Test
  @DisplayName("Test visitLibraryMember(LibraryClass, LibraryMember)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassRenamer.visitLibraryMember(LibraryClass, LibraryMember)"})
  void testVisitLibraryMember2() {
    // Arrange
    ClassRenamer classRenamer = new ClassRenamer();
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryMember = new LibraryField(1, "Name", "Descriptor");
    libraryMember.setProcessingInfo("Processing Info");

    // Act
    classRenamer.visitLibraryMember(libraryClass, libraryMember);

    // Assert
    assertEquals("Processing Info", libraryMember.name);
  }

  /**
   * Test {@link ClassRenamer#visitLibraryMember(LibraryClass, LibraryMember)}.
   *
   * <p>Method under test: {@link ClassRenamer#visitLibraryMember(LibraryClass, LibraryMember)}
   */
  @Test
  @DisplayName("Test visitLibraryMember(LibraryClass, LibraryMember)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassRenamer.visitLibraryMember(LibraryClass, LibraryMember)"})
  void testVisitLibraryMember3() {
    // Arrange
    ClassVisitor extraClassVisitor = mock(ClassVisitor.class);
    ClassRenamer classRenamer = new ClassRenamer(extraClassVisitor, new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryMember = new LibraryField(1, "Name", "Descriptor");
    libraryMember.setProcessingInfo("Processing Info");

    // Act
    classRenamer.visitLibraryMember(libraryClass, libraryMember);

    // Assert
    assertEquals("Processing Info", libraryMember.name);
  }

  /**
   * Test {@link ClassRenamer#visitLibraryMember(LibraryClass, LibraryMember)}.
   *
   * <p>Method under test: {@link ClassRenamer#visitLibraryMember(LibraryClass, LibraryMember)}
   */
  @Test
  @DisplayName("Test visitLibraryMember(LibraryClass, LibraryMember)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassRenamer.visitLibraryMember(LibraryClass, LibraryMember)"})
  void testVisitLibraryMember4() {
    // Arrange
    ClassRenamer classRenamer = new ClassRenamer();
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryMember = new LibraryField(1, "Processing Info", "Descriptor");
    libraryMember.setProcessingInfo("Processing Info");

    // Act
    classRenamer.visitLibraryMember(libraryClass, libraryMember);

    // Assert that nothing has changed
    assertEquals("Processing Info", libraryMember.name);
  }

  /**
   * Test {@link ClassRenamer#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <ul>
   *   <li>Given {@code Name}.
   *   <li>Then calls {@link Clazz#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link ClassRenamer#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); given 'Name'; then calls getName()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassRenamer.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_givenName_thenCallsGetName() {
    // Arrange
    ClassRenamer classRenamer = new ClassRenamer();
    Clazz clazz = mock(Clazz.class);
    when(clazz.getProcessingInfo()).thenReturn("Name");
    when(clazz.getName()).thenReturn("Name");

    // Act
    classRenamer.visitClassConstant(clazz, new ClassConstant());

    // Assert
    verify(clazz).getName();
    verify(clazz).getProcessingInfo();
  }
}
