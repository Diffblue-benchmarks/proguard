package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
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
import proguard.classfile.constant.Constant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.classfile.visitor.AllFieldVisitor;
import proguard.classfile.visitor.AllMemberVisitor;
import proguard.classfile.visitor.AllMethodVisitor;
import proguard.classfile.visitor.ClassAccessFilter;
import proguard.classfile.visitor.ClassCleaner;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.ImplementedClassFilter;
import proguard.classfile.visitor.MemberVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.util.SimpleProcessable;

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
   *   <li>Then calls {@link ClassConstant#accept(Clazz, ConstantVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link ClassRenamer#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then calls accept(Clazz, ConstantVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassRenamer.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_thenCallsAccept() {
    // Arrange
    ClassRenamer classRenamer = new ClassRenamer();

    ClassConstant classConstant = mock(ClassConstant.class);
    doNothing().when(classConstant).accept(Mockito.<Clazz>any(), Mockito.<ConstantVisitor>any());
    ProgramClass programClass =
        new ProgramClass(1, 3, new Constant[] {new ClassConstant(), classConstant}, 1, 1, 1);
    programClass.u2fieldsCount = 0;
    programClass.u2methodsCount = 0;

    // Act
    classRenamer.visitProgramClass(programClass);

    // Assert
    verify(classConstant).accept(isA(Clazz.class), isA(ConstantVisitor.class));
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
   * <p>Method under test: {@link ClassRenamer#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassRenamer.visitLibraryClass(LibraryClass)"})
  void testVisitLibraryClass4() {
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
   * <p>Method under test: {@link ClassRenamer#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassRenamer.visitLibraryClass(LibraryClass)"})
  void testVisitLibraryClass5() {
    // Arrange
    AllFieldVisitor extraClassVisitor = new AllFieldVisitor(new KotlinAnnotationCounter());
    ClassRenamer classRenamer = new ClassRenamer(extraClassVisitor, new KotlinAnnotationCounter());

    LibraryClass libraryClass = new LibraryClass(1, "This Class Name", "Super Class Name");
    libraryClass.setProcessingInfo("Processing Info");

    // Act
    classRenamer.visitLibraryClass(libraryClass);

    // Assert
    assertEquals("Processing Info", libraryClass.getName());
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
  void testVisitLibraryClass6() {
    // Arrange
    AllMemberVisitor extraClassVisitor = new AllMemberVisitor(new KotlinAnnotationCounter());
    ClassRenamer classRenamer = new ClassRenamer(extraClassVisitor, new KotlinAnnotationCounter());

    LibraryClass libraryClass = new LibraryClass(1, "This Class Name", "Super Class Name");
    libraryClass.setProcessingInfo("Processing Info");

    // Act
    classRenamer.visitLibraryClass(libraryClass);

    // Assert
    assertEquals("Processing Info", libraryClass.getName());
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
  void testVisitLibraryClass7() {
    // Arrange
    AllMethodVisitor extraClassVisitor = new AllMethodVisitor(new KotlinAnnotationCounter());
    ClassRenamer classRenamer = new ClassRenamer(extraClassVisitor, new KotlinAnnotationCounter());

    LibraryClass libraryClass = new LibraryClass(1, "This Class Name", "Super Class Name");
    libraryClass.setProcessingInfo("Processing Info");

    // Act
    classRenamer.visitLibraryClass(libraryClass);

    // Assert
    assertEquals("Processing Info", libraryClass.getName());
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
  void testVisitLibraryClass8() {
    // Arrange
    ClassAccessFilter extraClassVisitor = new ClassAccessFilter(1, 1, mock(ClassVisitor.class));
    ClassRenamer classRenamer = new ClassRenamer(extraClassVisitor, new KotlinAnnotationCounter());

    LibraryClass libraryClass = new LibraryClass(1, "This Class Name", "Super Class Name");
    libraryClass.setProcessingInfo("Processing Info");

    // Act
    classRenamer.visitLibraryClass(libraryClass);

    // Assert
    assertEquals("Processing Info", libraryClass.getName());
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
    ClassVisitor rejectedClassVisistor = mock(ClassVisitor.class);
    doNothing().when(rejectedClassVisistor).visitLibraryClass(Mockito.<LibraryClass>any());
    ImplementedClassFilter extraClassVisitor =
        new ImplementedClassFilter(
            new LibraryClass(), true, mock(ClassVisitor.class), rejectedClassVisistor);
    ClassRenamer classRenamer = new ClassRenamer(extraClassVisitor, new KotlinAnnotationCounter());

    LibraryClass libraryClass = new LibraryClass(1, "This Class Name", "Super Class Name");
    libraryClass.setProcessingInfo("Processing Info");

    // Act
    classRenamer.visitLibraryClass(libraryClass);

    // Assert
    verify(rejectedClassVisistor).visitLibraryClass(isA(LibraryClass.class));
    assertEquals("Processing Info", libraryClass.getName());
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
    doThrow(new UnsupportedOperationException())
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
    verify(programClass).getString(0);
  }

  /**
   * Test {@link ClassRenamer#visitProgramMember(ProgramClass, ProgramMember)}.
   *
   * <ul>
   *   <li>When {@link ProgramField#ProgramField()} ProcessingInfo is {@code String}.
   *   <li>Then calls {@link ProgramClass#getString(int)}.
   * </ul>
   *
   * <p>Method under test: {@link ClassRenamer#visitProgramMember(ProgramClass, ProgramMember)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMember(ProgramClass, ProgramMember); when ProgramField() ProcessingInfo is 'String'; then calls getString(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassRenamer.visitProgramMember(ProgramClass, ProgramMember)"})
  void testVisitProgramMember_whenProgramFieldProcessingInfoIsString_thenCallsGetString() {
    // Arrange
    ClassRenamer classRenamer = new ClassRenamer();

    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    ProgramField programMember = new ProgramField();
    programMember.setProcessingInfo("String");

    // Act
    classRenamer.visitProgramMember(programClass, programMember);

    // Assert
    verify(programClass).getString(0);
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
    ClassRenamer classRenamer = new ClassRenamer(mock(ClassVisitor.class), null);
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryMember = new LibraryField(1, "Name", "Descriptor");
    libraryMember.setProcessingInfo("Library Member");

    // Act
    classRenamer.visitLibraryMember(libraryClass, libraryMember);

    // Assert
    assertEquals("Library Member", libraryMember.name);
    assertEquals("Library Member", libraryMember.getProcessingInfo());
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
    ClassRenamer classRenamer = new ClassRenamer(mock(ClassVisitor.class), null);
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo(null);

    LibraryField libraryMember = new LibraryField(1, "Name", "Descriptor");
    libraryMember.setProcessingInfo(libraryField);

    // Act
    classRenamer.visitLibraryMember(libraryClass, libraryMember);

    // Assert that nothing has changed
    Object processingInfo = libraryMember.getProcessingInfo();
    assertTrue(processingInfo instanceof LibraryField);
    assertEquals("Name", libraryMember.name);
    assertSame(libraryField, processingInfo);
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
    ClassVisitor extraClassVisitor = mock(ClassVisitor.class);
    ClassRenamer classRenamer = new ClassRenamer(extraClassVisitor, new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryMember = new LibraryField(1, "Name", "Descriptor");
    libraryMember.setProcessingInfo("Library Member");

    // Act
    classRenamer.visitLibraryMember(libraryClass, libraryMember);

    // Assert
    assertEquals("Library Member", libraryMember.name);
    assertEquals("Library Member", libraryMember.getProcessingInfo());
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
  void testVisitLibraryMember5() {
    // Arrange
    ClassVisitor extraClassVisitor = mock(ClassVisitor.class);
    ClassRenamer classRenamer = new ClassRenamer(extraClassVisitor, new ClassRenamer());
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryMember = new LibraryField(1, "Name", "Descriptor");
    libraryMember.setProcessingInfo("Library Member");

    // Act
    classRenamer.visitLibraryMember(libraryClass, libraryMember);

    // Assert
    assertEquals("Library Member", libraryMember.name);
    assertEquals("Library Member", libraryMember.getProcessingInfo());
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
  void testVisitLibraryMember6() {
    // Arrange
    ClassVisitor extraClassVisitor = mock(ClassVisitor.class);
    ClassRenamer classRenamer = new ClassRenamer(extraClassVisitor, new MemberNameCleaner());
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryMember = new LibraryField(1, "Name", "Descriptor");
    libraryMember.setProcessingInfo("Library Member");

    // Act
    classRenamer.visitLibraryMember(libraryClass, libraryMember);

    // Assert
    assertEquals("Library Member", libraryMember.name);
    assertNull(libraryMember.getProcessingInfo());
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
  void testVisitLibraryMember7() {
    // Arrange
    ClassRenamer classRenamer = new ClassRenamer(mock(ClassVisitor.class), null);
    LibraryClass libraryClass = new LibraryClass();

    SimpleProcessable simpleProcessable = new SimpleProcessable();
    simpleProcessable.setProcessingInfo(null);

    LibraryField libraryMember = new LibraryField(1, "Name", "Descriptor");
    libraryMember.setProcessingInfo(simpleProcessable);

    // Act
    classRenamer.visitLibraryMember(libraryClass, libraryMember);

    // Assert that nothing has changed
    Object processingInfo = libraryMember.getProcessingInfo();
    assertTrue(processingInfo instanceof SimpleProcessable);
    assertEquals("Name", libraryMember.name);
    assertSame(simpleProcessable, processingInfo);
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
  void testVisitLibraryMember8() {
    // Arrange
    ClassVisitor extraClassVisitor = mock(ClassVisitor.class);
    ClassRenamer classRenamer = new ClassRenamer(extraClassVisitor, new ClassCleaner());
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo("Library Member");

    LibraryField libraryMember = new LibraryField(1, "Name", "Descriptor");
    libraryMember.setProcessingInfo(libraryField);

    // Act
    classRenamer.visitLibraryMember(libraryClass, libraryMember);

    // Assert
    assertEquals("Library Member", libraryMember.name);
    assertNull(libraryMember.getProcessingInfo());
  }

  /**
   * Test {@link ClassRenamer#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <ul>
   *   <li>Given {@code Name}.
   *   <li>Then calls {@link LibraryClass#addSubClass(Clazz)}.
   * </ul>
   *
   * <p>Method under test: {@link ClassRenamer#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName(
      "Test visitClassConstant(Clazz, ClassConstant); given 'Name'; then calls addSubClass(Clazz)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassRenamer.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_givenName_thenCallsAddSubClass() {
    // Arrange
    ClassRenamer classRenamer = new ClassRenamer();

    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getProcessingInfo()).thenReturn("Name");
    when(clazz.getName()).thenReturn("Name");
    doNothing().when(clazz).addSubClass(Mockito.<Clazz>any());
    doNothing().when(clazz).setProcessingInfo(Mockito.<Object>any());
    clazz.addSubClass(new LibraryClass());
    clazz.setProcessingInfo("Clazz");

    // Act
    classRenamer.visitClassConstant(clazz, new ClassConstant());

    // Assert
    verify(clazz).addSubClass(isA(Clazz.class));
    verify(clazz).getName();
    verify(clazz).getProcessingInfo();
    verify(clazz).setProcessingInfo(isA(Object.class));
  }
}
