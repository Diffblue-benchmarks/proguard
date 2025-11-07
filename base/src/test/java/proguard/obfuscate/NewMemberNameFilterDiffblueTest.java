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
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMethod;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMember;
import proguard.classfile.ProgramMethod;
import proguard.classfile.visitor.MemberVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.obfuscate.kotlin.KotlinValueParameterUsageMarker;
import proguard.optimize.KeepMarker;
import proguard.optimize.info.FieldOptimizationInfo;

class NewMemberNameFilterDiffblueTest {
  /**
   * Test {@link NewMemberNameFilter#visitProgramField(ProgramClass, ProgramField)}.
   * <p>
   * Method under test: {@link NewMemberNameFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewMemberNameFilter.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField() {
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
   * Test {@link NewMemberNameFilter#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Then {@link ProgramField#ProgramField()} ProcessingInfo is {@code Processing Info}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NewMemberNameFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); then ProgramField() ProcessingInfo is 'Processing Info'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewMemberNameFilter.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_thenProgramFieldProcessingInfoIsProcessingInfo() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new KotlinAnnotationCounter());
    ProgramClass programClass = new ProgramClass();

    ProgramField programField = new ProgramField();
    programField.setProcessingInfo("Processing Info");

    // Act
    newMemberNameFilter.visitProgramField(programClass, programField);

    // Assert that nothing has changed
    assertEquals("Processing Info", programField.getProcessingInfo());
  }

  /**
   * Test {@link NewMemberNameFilter#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>When {@link ProgramField#ProgramField()}.</li>
   *   <li>Then {@link ProgramField#ProgramField()} ProcessingInfo is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NewMemberNameFilter#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); when ProgramField(); then ProgramField() ProcessingInfo is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewMemberNameFilter.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_whenProgramField_thenProgramFieldProcessingInfoIsNull() {
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
   * Test {@link NewMemberNameFilter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code <init>}.</li>
   *   <li>When {@link ProgramClass} {@link ProgramClass#getString(int)} return {@code <init>}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NewMemberNameFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given '<init>'; when ProgramClass getString(int) return '<init>'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewMemberNameFilter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenInit_whenProgramClassGetStringReturnInit() {
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
   * Test {@link NewMemberNameFilter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code String}.</li>
   *   <li>Then calls {@link ProgramClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NewMemberNameFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given 'String'; then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewMemberNameFilter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenString_thenCallsGetString() {
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
   * Test {@link NewMemberNameFilter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Then calls {@link MemberVisitor#visitProgramMethod(ProgramClass, ProgramMethod)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NewMemberNameFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); then calls visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewMemberNameFilter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_thenCallsVisitProgramMethod() {
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
   * Test {@link NewMemberNameFilter#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>When {@link ProgramMethod} {@link ProgramMember#accept(Clazz, MemberVisitor)} does nothing.</li>
   *   <li>Then calls {@link ProgramMember#accept(Clazz, MemberVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NewMemberNameFilter#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); when ProgramMethod accept(Clazz, MemberVisitor) does nothing; then calls accept(Clazz, MemberVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewMemberNameFilter.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_whenProgramMethodAcceptDoesNothing_thenCallsAccept() {
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
   * Test {@link NewMemberNameFilter#visitLibraryField(LibraryClass, LibraryField)}.
   * <p>
   * Method under test: {@link NewMemberNameFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewMemberNameFilter.visitLibraryField(LibraryClass, LibraryField)"})
  void testVisitLibraryField() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new KotlinAnnotationCounter());
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
   * Test {@link NewMemberNameFilter#visitLibraryField(LibraryClass, LibraryField)}.
   * <p>
   * Method under test: {@link NewMemberNameFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewMemberNameFilter.visitLibraryField(LibraryClass, LibraryField)"})
  void testVisitLibraryField2() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo(null);

    LibraryField libraryField2 = new LibraryField(1, "Name", "Descriptor");
    libraryField2.setProcessingInfo(libraryField);

    // Act
    newMemberNameFilter.visitLibraryField(libraryClass, libraryField2);

    // Assert that nothing has changed
    Object processingInfo = libraryField2.getProcessingInfo();
    assertTrue(processingInfo instanceof LibraryField);
    assertEquals("Name", libraryField2.name);
    assertSame(libraryField, processingInfo);
  }

  /**
   * Test {@link NewMemberNameFilter#visitLibraryField(LibraryClass, LibraryField)}.
   * <p>
   * Method under test: {@link NewMemberNameFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewMemberNameFilter.visitLibraryField(LibraryClass, LibraryField)"})
  void testVisitLibraryField3() {
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
   * Test {@link NewMemberNameFilter#visitLibraryField(LibraryClass, LibraryField)}.
   * <p>
   * Method under test: {@link NewMemberNameFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewMemberNameFilter.visitLibraryField(LibraryClass, LibraryField)"})
  void testVisitLibraryField4() {
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
   * Test {@link NewMemberNameFilter#visitLibraryField(LibraryClass, LibraryField)}.
   * <p>
   * Method under test: {@link NewMemberNameFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewMemberNameFilter.visitLibraryField(LibraryClass, LibraryField)"})
  void testVisitLibraryField5() {
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
   * Test {@link NewMemberNameFilter#visitLibraryField(LibraryClass, LibraryField)}.
   * <p>
   * Method under test: {@link NewMemberNameFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewMemberNameFilter.visitLibraryField(LibraryClass, LibraryField)"})
  void testVisitLibraryField6() {
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
   * Test {@link NewMemberNameFilter#visitLibraryField(LibraryClass, LibraryField)}.
   * <p>
   * Method under test: {@link NewMemberNameFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewMemberNameFilter.visitLibraryField(LibraryClass, LibraryField)"})
  void testVisitLibraryField7() {
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
    assertNull(((FieldOptimizationInfo) processingInfo).getReferencedClass());
    assertNull(((FieldOptimizationInfo) processingInfo).getValue());
    assertTrue(((FieldOptimizationInfo) processingInfo).isKept());
    assertTrue(((FieldOptimizationInfo) processingInfo).isRead());
    assertTrue(((FieldOptimizationInfo) processingInfo).isWritten());
  }

  /**
   * Test {@link NewMemberNameFilter#visitLibraryField(LibraryClass, LibraryField)}.
   * <ul>
   *   <li>When {@link LibraryField#LibraryField(int, String, String)} with u2accessFlags is one and {@code Name} and {@code Descriptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NewMemberNameFilter#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField); when LibraryField(int, String, String) with u2accessFlags is one and 'Name' and 'Descriptor'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewMemberNameFilter.visitLibraryField(LibraryClass, LibraryField)"})
  void testVisitLibraryField_whenLibraryFieldWithU2accessFlagsIsOneAndNameAndDescriptor() {
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
   * Test {@link NewMemberNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <p>
   * Method under test: {@link NewMemberNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewMemberNameFilter.visitLibraryMethod(LibraryClass, LibraryMethod)"})
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
   * Test {@link NewMemberNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <p>
   * Method under test: {@link NewMemberNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewMemberNameFilter.visitLibraryMethod(LibraryClass, LibraryMethod)"})
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
   * Test {@link NewMemberNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <p>
   * Method under test: {@link NewMemberNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewMemberNameFilter.visitLibraryMethod(LibraryClass, LibraryMethod)"})
  void testVisitLibraryMethod3() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new KotlinAnnotationCounter());
    LibraryClass libraryClass = new LibraryClass();

    LibraryMethod libraryMethod = new LibraryMethod(1, "Name", "Descriptor");
    libraryMethod.setProcessingInfo("Processing Info");

    // Act
    newMemberNameFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert that nothing has changed
    assertEquals("Name", libraryMethod.name);
    assertEquals("Processing Info", libraryMethod.getProcessingInfo());
  }

  /**
   * Test {@link NewMemberNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <p>
   * Method under test: {@link NewMemberNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewMemberNameFilter.visitLibraryMethod(LibraryClass, LibraryMethod)"})
  void testVisitLibraryMethod4() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new KotlinAnnotationCounter());

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.setProcessingInfo("Processing Info");
    LibraryMethod libraryMethod = new LibraryMethod(1, "<init>", "Descriptor");

    // Act
    newMemberNameFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert that nothing has changed
    assertEquals("<init>", libraryMethod.name);
  }

  /**
   * Test {@link NewMemberNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <p>
   * Method under test: {@link NewMemberNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewMemberNameFilter.visitLibraryMethod(LibraryClass, LibraryMethod)"})
  void testVisitLibraryMethod5() {
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
   * Test {@link NewMemberNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <p>
   * Method under test: {@link NewMemberNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewMemberNameFilter.visitLibraryMethod(LibraryClass, LibraryMethod)"})
  void testVisitLibraryMethod6() {
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
   * Test {@link NewMemberNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <p>
   * Method under test: {@link NewMemberNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewMemberNameFilter.visitLibraryMethod(LibraryClass, LibraryMethod)"})
  void testVisitLibraryMethod7() {
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
   * Test {@link NewMemberNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <p>
   * Method under test: {@link NewMemberNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewMemberNameFilter.visitLibraryMethod(LibraryClass, LibraryMethod)"})
  void testVisitLibraryMethod8() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new NameMarker());

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.setProcessingInfo("Processing Info");
    LibraryMethod libraryMethod = new LibraryMethod(1, "<init>", "Descriptor");

    // Act
    newMemberNameFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert that nothing has changed
    assertEquals("<init>", libraryMethod.name);
  }

  /**
   * Test {@link NewMemberNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   * <p>
   * Method under test: {@link NewMemberNameFilter#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NewMemberNameFilter.visitLibraryMethod(LibraryClass, LibraryMethod)"})
  void testVisitLibraryMethod9() {
    // Arrange
    NewMemberNameFilter newMemberNameFilter = new NewMemberNameFilter(new NameMarker());
    LibraryClass libraryClass = new LibraryClass();

    LibraryMethod libraryMethod = new LibraryMethod(1, "Name", "Descriptor");
    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");

    libraryMethod.setProcessingInfo(libraryField);

    // Act
    newMemberNameFilter.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert that nothing has changed
    Object processingInfo = libraryMethod.getProcessingInfo();
    assertTrue(processingInfo instanceof LibraryField);
    assertEquals("Name", libraryMethod.name);
    assertSame(libraryField, processingInfo);
  }
}
