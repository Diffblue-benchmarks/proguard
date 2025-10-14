package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
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
import proguard.classfile.LibraryMethod;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.InnerClassesAttribute;
import proguard.classfile.attribute.InnerClassesInfo;
import proguard.classfile.attribute.visitor.InnerClassesInfoVisitor;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.testutils.cpa.NamedClass;
import proguard.util.SimpleFeatureNamedProcessable;
import proguard.util.SimpleProcessable;

class NameMarkerDiffblueTest {
  /**
   * Test {@link NameMarker#visitAnyClass(Clazz)}.
   *
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link NameMarker#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName(
      "Test visitAnyClass(Clazz); when LibraryClass(); then throw UnsupportedOperationException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NameMarker.visitAnyClass(Clazz)"})
  void testVisitAnyClass_whenLibraryClass_thenThrowUnsupportedOperationException() {
    // Arrange
    NameMarker nameMarker = new NameMarker();

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> nameMarker.visitAnyClass(new LibraryClass()));
  }

  /**
   * Test {@link NameMarker#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Then {@link NamedClass#NamedClass(String)} with {@code Member Name} ProcessingInfo is
   *       {@code Member Name}.
   * </ul>
   *
   * <p>Method under test: {@link NameMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName(
      "Test visitProgramClass(ProgramClass); then NamedClass(String) with 'Member Name' ProcessingInfo is 'Member Name'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NameMarker.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_thenNamedClassWithMemberNameProcessingInfoIsMemberName() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    NamedClass programClass = new NamedClass("Member Name");

    // Act
    nameMarker.visitProgramClass(programClass);

    // Assert
    assertEquals("Member Name", programClass.getProcessingInfo());
  }

  /**
   * Test {@link NameMarker#visitProgramField(ProgramClass, ProgramField)}.
   *
   * <p>Method under test: {@link NameMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NameMarker.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField() {
    // Arrange
    NameMarker nameMarker = new NameMarker();

    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    SimpleFeatureNamedProcessable simpleFeatureNamedProcessable =
        new SimpleFeatureNamedProcessable();
    simpleFeatureNamedProcessable.addProcessingFlags(2, 1, 2, 1);

    ProgramField programField = new ProgramField();
    programField.setProcessingInfo(simpleFeatureNamedProcessable);

    // Act
    nameMarker.visitProgramField(programClass, programField);

    // Assert
    verify(programClass).getString(0);
  }

  /**
   * Test {@link NameMarker#visitProgramField(ProgramClass, ProgramField)}.
   *
   * <ul>
   *   <li>Given {@code Processing Info}.
   * </ul>
   *
   * <p>Method under test: {@link NameMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); given 'Processing Info'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NameMarker.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_givenProcessingInfo() {
    // Arrange
    NameMarker nameMarker = new NameMarker();

    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    ProgramField programField = new ProgramField();
    programField.setProcessingInfo("Processing Info");

    // Act
    nameMarker.visitProgramField(programClass, programField);

    // Assert
    verify(programClass).getString(0);
  }

  /**
   * Test {@link NameMarker#visitProgramField(ProgramClass, ProgramField)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   *   <li>When {@link ProgramField#ProgramField()}.
   *   <li>Then calls {@link ProgramClass#getString(int)}.
   * </ul>
   *
   * <p>Method under test: {@link NameMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName(
      "Test visitProgramField(ProgramClass, ProgramField); given 'String'; when ProgramField(); then calls getString(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NameMarker.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_givenString_whenProgramField_thenCallsGetString() {
    // Arrange
    NameMarker nameMarker = new NameMarker();

    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    nameMarker.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(programClass).getString(0);
  }

  /**
   * Test {@link NameMarker#visitProgramField(ProgramClass, ProgramField)}.
   *
   * <ul>
   *   <li>Then {@link ProgramField#ProgramField()} ProcessingInfo {@link LibraryField}.
   * </ul>
   *
   * <p>Method under test: {@link NameMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName(
      "Test visitProgramField(ProgramClass, ProgramField); then ProgramField() ProcessingInfo LibraryField")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NameMarker.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_thenProgramFieldProcessingInfoLibraryField() {
    // Arrange
    NameMarker nameMarker = new NameMarker();

    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    ProgramField programField = new ProgramField();
    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    programField.setProcessingInfo(libraryField);

    // Act
    nameMarker.visitProgramField(programClass, programField);

    // Assert
    verify(programClass).getString(0);
    Object processingInfo = programField.getProcessingInfo();
    assertTrue(processingInfo instanceof LibraryField);
    assertEquals("String", ((LibraryField) processingInfo).getProcessingInfo());
  }

  /**
   * Test {@link NameMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <p>Method under test: {@link NameMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NameMarker.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod() {
    // Arrange
    NameMarker nameMarker = new NameMarker();

    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    SimpleFeatureNamedProcessable simpleFeatureNamedProcessable =
        new SimpleFeatureNamedProcessable();
    simpleFeatureNamedProcessable.addProcessingFlags(2, 1, 2, 1);

    ProgramMethod programMethod = new ProgramMethod();
    programMethod.setProcessingInfo(simpleFeatureNamedProcessable);

    // Act
    nameMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programClass).getString(0);
  }

  /**
   * Test {@link NameMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>Given {@code Processing Info}.
   * </ul>
   *
   * <p>Method under test: {@link NameMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given 'Processing Info'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NameMarker.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenProcessingInfo() {
    // Arrange
    NameMarker nameMarker = new NameMarker();

    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    ProgramMethod programMethod = new ProgramMethod();
    programMethod.setProcessingInfo("Processing Info");

    // Act
    nameMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programClass).getString(0);
  }

  /**
   * Test {@link NameMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   *   <li>When {@link ProgramMethod#ProgramMethod()}.
   *   <li>Then calls {@link ProgramClass#getString(int)}.
   * </ul>
   *
   * <p>Method under test: {@link NameMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); given 'String'; when ProgramMethod(); then calls getString(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NameMarker.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenString_whenProgramMethod_thenCallsGetString() {
    // Arrange
    NameMarker nameMarker = new NameMarker();

    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    nameMarker.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).getString(0);
  }

  /**
   * Test {@link NameMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>Then {@link ProgramMethod#ProgramMethod()} ProcessingInfo {@link LibraryField}.
   * </ul>
   *
   * <p>Method under test: {@link NameMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); then ProgramMethod() ProcessingInfo LibraryField")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NameMarker.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_thenProgramMethodProcessingInfoLibraryField() {
    // Arrange
    NameMarker nameMarker = new NameMarker();

    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    ProgramMethod programMethod = new ProgramMethod();
    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    programMethod.setProcessingInfo(libraryField);

    // Act
    nameMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programClass).getString(0);
    Object processingInfo = programMethod.getProcessingInfo();
    assertTrue(processingInfo instanceof LibraryField);
    assertEquals("String", ((LibraryField) processingInfo).getProcessingInfo());
  }

  /**
   * Test {@link NameMarker#visitLibraryField(LibraryClass, LibraryField)}.
   *
   * <p>Method under test: {@link NameMarker#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NameMarker.visitLibraryField(LibraryClass, LibraryField)"})
  void testVisitLibraryField() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo(null);

    LibraryField libraryField2 = new LibraryField(1, "Name", "Descriptor");
    libraryField2.setProcessingInfo(libraryField);

    // Act
    nameMarker.visitLibraryField(libraryClass, libraryField2);

    // Assert
    Object processingInfo = libraryField2.getProcessingInfo();
    assertTrue(processingInfo instanceof LibraryField);
    assertEquals("Name", ((LibraryField) processingInfo).getProcessingInfo());
    assertSame(libraryField, processingInfo);
  }

  /**
   * Test {@link NameMarker#visitLibraryField(LibraryClass, LibraryField)}.
   *
   * <p>Method under test: {@link NameMarker#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NameMarker.visitLibraryField(LibraryClass, LibraryField)"})
  void testVisitLibraryField2() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass libraryClass = new LibraryClass();

    SimpleProcessable simpleProcessable = new SimpleProcessable();
    simpleProcessable.setProcessingInfo(null);

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo(simpleProcessable);

    // Act
    nameMarker.visitLibraryField(libraryClass, libraryField);

    // Assert that nothing has changed
    Object processingInfo = libraryField.getProcessingInfo();
    assertTrue(processingInfo instanceof SimpleProcessable);
    assertSame(simpleProcessable, processingInfo);
  }

  /**
   * Test {@link NameMarker#visitLibraryField(LibraryClass, LibraryField)}.
   *
   * <ul>
   *   <li>Given {@code Library Field}.
   * </ul>
   *
   * <p>Method under test: {@link NameMarker#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField); given 'Library Field'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NameMarker.visitLibraryField(LibraryClass, LibraryField)"})
  void testVisitLibraryField_givenLibraryField() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass libraryClass = new LibraryClass();

    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryField.setProcessingInfo("Library Field");

    // Act
    nameMarker.visitLibraryField(libraryClass, libraryField);

    // Assert
    assertEquals("Name", libraryField.getProcessingInfo());
  }

  /**
   * Test {@link NameMarker#visitLibraryField(LibraryClass, LibraryField)}.
   *
   * <ul>
   *   <li>When {@link LibraryField#LibraryField(int, String, String)} with u2accessFlags is one and
   *       {@code Name} and {@code Descriptor}.
   * </ul>
   *
   * <p>Method under test: {@link NameMarker#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName(
      "Test visitLibraryField(LibraryClass, LibraryField); when LibraryField(int, String, String) with u2accessFlags is one and 'Name' and 'Descriptor'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NameMarker.visitLibraryField(LibraryClass, LibraryField)"})
  void testVisitLibraryField_whenLibraryFieldWithU2accessFlagsIsOneAndNameAndDescriptor() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass libraryClass = new LibraryClass();
    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");

    // Act
    nameMarker.visitLibraryField(libraryClass, libraryField);

    // Assert
    assertEquals("Name", libraryField.getProcessingInfo());
  }

  /**
   * Test {@link NameMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   *
   * <p>Method under test: {@link NameMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NameMarker.visitLibraryMethod(LibraryClass, LibraryMethod)"})
  void testVisitLibraryMethod() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass libraryClass = new LibraryClass();
    LibraryMethod libraryMethod = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    nameMarker.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    assertEquals("Name", libraryMethod.getProcessingInfo());
  }

  /**
   * Test {@link NameMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   *
   * <p>Method under test: {@link NameMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NameMarker.visitLibraryMethod(LibraryClass, LibraryMethod)"})
  void testVisitLibraryMethod2() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass libraryClass = new LibraryClass();

    LibraryMethod libraryMethod = new LibraryMethod(1, "Name", "Descriptor");
    LibraryField libraryField = new LibraryField(1, "Name", "Descriptor");
    libraryMethod.setProcessingInfo(libraryField);

    // Act
    nameMarker.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    Object processingInfo = libraryMethod.getProcessingInfo();
    assertTrue(processingInfo instanceof LibraryField);
    assertEquals("Name", ((LibraryField) processingInfo).getProcessingInfo());
    assertSame(libraryField, processingInfo);
  }

  /**
   * Test {@link NameMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   *
   * <p>Method under test: {@link NameMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NameMarker.visitLibraryMethod(LibraryClass, LibraryMethod)"})
  void testVisitLibraryMethod3() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass libraryClass = new LibraryClass();

    SimpleFeatureNamedProcessable simpleFeatureNamedProcessable =
        new SimpleFeatureNamedProcessable();
    simpleFeatureNamedProcessable.addProcessingFlags(2, 1, 2, 1);

    LibraryMethod libraryMethod = new LibraryMethod(1, "Name", "Descriptor");
    libraryMethod.setProcessingInfo(simpleFeatureNamedProcessable);

    // Act
    nameMarker.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert that nothing has changed
    Object processingInfo = libraryMethod.getProcessingInfo();
    assertTrue(processingInfo instanceof SimpleFeatureNamedProcessable);
    assertSame(simpleFeatureNamedProcessable, processingInfo);
  }

  /**
   * Test {@link NameMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}.
   *
   * <ul>
   *   <li>Given {@code Processing Info}.
   * </ul>
   *
   * <p>Method under test: {@link NameMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod); given 'Processing Info'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NameMarker.visitLibraryMethod(LibraryClass, LibraryMethod)"})
  void testVisitLibraryMethod_givenProcessingInfo() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass libraryClass = new LibraryClass();

    LibraryMethod libraryMethod = new LibraryMethod(1, "Name", "Descriptor");
    libraryMethod.setProcessingInfo("Processing Info");

    // Act
    nameMarker.visitLibraryMethod(libraryClass, libraryMethod);

    // Assert
    assertEquals("Name", libraryMethod.getProcessingInfo());
  }

  /**
   * Test {@link NameMarker#visitInnerClassesAttribute(Clazz, InnerClassesAttribute)}.
   *
   * <ul>
   *   <li>Then calls {@link InnerClassesAttribute#innerClassEntriesAccept(Clazz,
   *       InnerClassesInfoVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link NameMarker#visitInnerClassesAttribute(Clazz,
   * InnerClassesAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitInnerClassesAttribute(Clazz, InnerClassesAttribute); then calls innerClassEntriesAccept(Clazz, InnerClassesInfoVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NameMarker.visitInnerClassesAttribute(Clazz, InnerClassesAttribute)"})
  void testVisitInnerClassesAttribute_thenCallsInnerClassEntriesAccept() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass clazz = new LibraryClass();

    InnerClassesAttribute innerClassesAttribute = mock(InnerClassesAttribute.class);
    doNothing()
        .when(innerClassesAttribute)
        .innerClassEntriesAccept(Mockito.<Clazz>any(), Mockito.<InnerClassesInfoVisitor>any());

    // Act
    nameMarker.visitInnerClassesAttribute(clazz, innerClassesAttribute);

    // Assert
    verify(innerClassesAttribute)
        .innerClassEntriesAccept(isA(Clazz.class), isA(InnerClassesInfoVisitor.class));
  }

  /**
   * Test {@link NameMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}.
   *
   * <ul>
   *   <li>Given {@code Class Name}.
   *   <li>Then calls {@link LibraryClass#getClassName(int)}.
   * </ul>
   *
   * <p>Method under test: {@link NameMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}
   */
  @Test
  @DisplayName(
      "Test visitInnerClassesInfo(Clazz, InnerClassesInfo); given 'Class Name'; then calls getClassName(int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NameMarker.visitInnerClassesInfo(Clazz, InnerClassesInfo)"})
  void testVisitInnerClassesInfo_givenClassName_thenCallsGetClassName() {
    // Arrange
    NameMarker nameMarker = new NameMarker();

    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getClassName(anyInt())).thenReturn("Class Name");
    when(clazz.getName()).thenReturn("Name");
    InnerClassesInfo innerClassesInfo = new InnerClassesInfo(1, 1, 1, 1);

    // Act
    nameMarker.visitInnerClassesInfo(clazz, innerClassesInfo);

    // Assert
    verify(clazz).getClassName(1);
    verify(clazz).getName();
  }

  /**
   * Test {@link NameMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link NameMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}
   */
  @Test
  @DisplayName(
      "Test visitInnerClassesInfo(Clazz, InnerClassesInfo); then throw UnsupportedOperationException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NameMarker.visitInnerClassesInfo(Clazz, InnerClassesInfo)"})
  void testVisitInnerClassesInfo_thenThrowUnsupportedOperationException() {
    // Arrange
    NameMarker nameMarker = new NameMarker();

    LibraryClass clazz = mock(LibraryClass.class);
    doThrow(new UnsupportedOperationException())
        .when(clazz)
        .constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    when(clazz.getClassName(anyInt())).thenReturn("Name");
    when(clazz.getName()).thenReturn("Name");
    InnerClassesInfo innerClassesInfo = new InnerClassesInfo(1, 1, 1, 1);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> nameMarker.visitInnerClassesInfo(clazz, innerClassesInfo));
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(clazz).getClassName(1);
    verify(clazz).getName();
  }

  /**
   * Test {@link NameMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}.
   *
   * <ul>
   *   <li>When {@link LibraryClass} {@link LibraryClass#constantPoolEntryAccept(int,
   *       ConstantVisitor)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link NameMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}
   */
  @Test
  @DisplayName(
      "Test visitInnerClassesInfo(Clazz, InnerClassesInfo); when LibraryClass constantPoolEntryAccept(int, ConstantVisitor) does nothing")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NameMarker.visitInnerClassesInfo(Clazz, InnerClassesInfo)"})
  void testVisitInnerClassesInfo_whenLibraryClassConstantPoolEntryAcceptDoesNothing() {
    // Arrange
    NameMarker nameMarker = new NameMarker();

    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    when(clazz.getClassName(anyInt())).thenReturn("Name");
    when(clazz.getName()).thenReturn("Name");
    InnerClassesInfo innerClassesInfo = new InnerClassesInfo(1, 1, 1, 1);

    // Act
    nameMarker.visitInnerClassesInfo(clazz, innerClassesInfo);

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(clazz).getClassName(1);
    verify(clazz).getName();
  }

  /**
   * Test {@link NameMarker#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <p>Method under test: {@link NameMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NameMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant(1, new LibraryClass());

    // Act
    nameMarker.visitClassConstant(clazz, classConstant);

    // Assert that nothing has changed
    assertTrue(classConstant.referencedClass instanceof LibraryClass);
  }

  /**
   * Test {@link NameMarker#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <p>Method under test: {@link NameMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void NameMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant2() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant(1, new NamedClass("Member Name"));

    // Act
    nameMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Clazz clazz2 = classConstant.referencedClass;
    assertTrue(clazz2 instanceof NamedClass);
    assertEquals("Member Name", clazz2.getProcessingInfo());
  }
}
