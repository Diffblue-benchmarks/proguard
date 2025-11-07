package proguard.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
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
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.InnerClassesAttribute;
import proguard.classfile.attribute.InnerClassesInfo;
import proguard.classfile.attribute.visitor.InnerClassesInfoVisitor;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.testutils.cpa.NamedClass;

class NameMarkerDiffblueTest {
  /**
   * Test {@link NameMarker#visitAnyClass(Clazz)}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NameMarker#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName("Test visitAnyClass(Clazz); when LibraryClass(); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NameMarker.visitAnyClass(Clazz)"})
  void testVisitAnyClass_whenLibraryClass_thenThrowUnsupportedOperationException() {
    // Arrange
    NameMarker nameMarker = new NameMarker();

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> nameMarker.visitAnyClass(new LibraryClass()));
  }

  /**
   * Test {@link NameMarker#visitProgramClass(ProgramClass)}.
   * <ul>
   *   <li>Then {@link NamedClass#NamedClass(String)} with {@code Member Name} ProcessingInfo is {@code Member Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NameMarker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then NamedClass(String) with 'Member Name' ProcessingInfo is 'Member Name'")
  @Tag("MaintainedByDiffblue")
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
   * <p>
   * Method under test: {@link NameMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NameMarker.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    ProgramField programField = mock(ProgramField.class);
    when(programField.getProcessingInfo()).thenReturn(new LibraryField(1, "Name", "Descriptor"));
    when(programField.getName(Mockito.<Clazz>any())).thenReturn("Name");
    doNothing().when(programField).setProcessingInfo(Mockito.<Object>any());

    // Act
    nameMarker.visitProgramField(programClass, programField);

    // Assert
    verify(programField).getName(isA(Clazz.class));
    verify(programField, atLeast(1)).getProcessingInfo();
    verify(programField).setProcessingInfo(isA(Object.class));
  }

  /**
   * Test {@link NameMarker#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Given {@code Processing Info}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NameMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); given 'Processing Info'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NameMarker.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_givenProcessingInfo() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    ProgramField programField = mock(ProgramField.class);
    when(programField.getProcessingInfo()).thenReturn("Processing Info");
    when(programField.getName(Mockito.<Clazz>any())).thenReturn("Name");
    doNothing().when(programField).setProcessingInfo(Mockito.<Object>any());

    // Act
    nameMarker.visitProgramField(programClass, programField);

    // Assert
    verify(programField).getName(isA(Clazz.class));
    verify(programField, atLeast(1)).getProcessingInfo();
    verify(programField).setProcessingInfo(isA(Object.class));
  }

  /**
   * Test {@link NameMarker#visitProgramField(ProgramClass, ProgramField)}.
   * <ul>
   *   <li>Given {@code String}.</li>
   *   <li>Then calls {@link ProgramClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NameMarker#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName("Test visitProgramField(ProgramClass, ProgramField); given 'String'; then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NameMarker.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_givenString_thenCallsGetString() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    nameMarker.visitProgramField(programClass, new ProgramField());

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Test {@link NameMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <p>
   * Method under test: {@link NameMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NameMarker.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn(new LibraryField(1, "Name", "Descriptor"));
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");
    doNothing().when(programMethod).setProcessingInfo(Mockito.<Object>any());

    // Act
    nameMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod).getName(isA(Clazz.class));
    verify(programMethod, atLeast(1)).getProcessingInfo();
    verify(programMethod).setProcessingInfo(isA(Object.class));
  }

  /**
   * Test {@link NameMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code Processing Info}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NameMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given 'Processing Info'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NameMarker.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenProcessingInfo() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    ProgramMethod programMethod = mock(ProgramMethod.class);
    when(programMethod.getProcessingInfo()).thenReturn("Processing Info");
    when(programMethod.getName(Mockito.<Clazz>any())).thenReturn("Name");
    doNothing().when(programMethod).setProcessingInfo(Mockito.<Object>any());

    // Act
    nameMarker.visitProgramMethod(programClass, programMethod);

    // Assert
    verify(programMethod).getName(isA(Clazz.class));
    verify(programMethod, atLeast(1)).getProcessingInfo();
    verify(programMethod).setProcessingInfo(isA(Object.class));
  }

  /**
   * Test {@link NameMarker#visitProgramMethod(ProgramClass, ProgramMethod)}.
   * <ul>
   *   <li>Given {@code String}.</li>
   *   <li>Then calls {@link ProgramClass#getString(int)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NameMarker#visitProgramMethod(ProgramClass, ProgramMethod)}
   */
  @Test
  @DisplayName("Test visitProgramMethod(ProgramClass, ProgramMethod); given 'String'; then calls getString(int)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NameMarker.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_givenString_thenCallsGetString() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getString(anyInt())).thenReturn("String");

    // Act
    nameMarker.visitProgramMethod(programClass, new ProgramMethod());

    // Assert
    verify(programClass).getString(eq(0));
  }

  /**
   * Test {@link NameMarker#visitLibraryField(LibraryClass, LibraryField)}.
   * <p>
   * Method under test: {@link NameMarker#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField)")
  @Tag("MaintainedByDiffblue")
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
   * <ul>
   *   <li>Given {@code Library Field}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NameMarker#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField); given 'Library Field'")
  @Tag("MaintainedByDiffblue")
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
   * <ul>
   *   <li>When {@link LibraryField#LibraryField(int, String, String)} with u2accessFlags is one and {@code Name} and {@code Descriptor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NameMarker#visitLibraryField(LibraryClass, LibraryField)}
   */
  @Test
  @DisplayName("Test visitLibraryField(LibraryClass, LibraryField); when LibraryField(int, String, String) with u2accessFlags is one and 'Name' and 'Descriptor'")
  @Tag("MaintainedByDiffblue")
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
   * <p>
   * Method under test: {@link NameMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod)")
  @Tag("MaintainedByDiffblue")
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
   * <p>
   * Method under test: {@link NameMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NameMarker.visitLibraryMethod(LibraryClass, LibraryMethod)"})
  void testVisitLibraryMethod2() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass libraryClass = new LibraryClass();

    LibraryMethod libraryMethod = new LibraryMethod(1, "Name", "Descriptor");
    LibraryField libraryField = new LibraryField();
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
   * <ul>
   *   <li>Given {@code Processing Info}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NameMarker#visitLibraryMethod(LibraryClass, LibraryMethod)}
   */
  @Test
  @DisplayName("Test visitLibraryMethod(LibraryClass, LibraryMethod); given 'Processing Info'")
  @Tag("MaintainedByDiffblue")
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
   * <ul>
   *   <li>Then calls {@link InnerClassesAttribute#innerClassEntriesAccept(Clazz, InnerClassesInfoVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NameMarker#visitInnerClassesAttribute(Clazz, InnerClassesAttribute)}
   */
  @Test
  @DisplayName("Test visitInnerClassesAttribute(Clazz, InnerClassesAttribute); then calls innerClassEntriesAccept(Clazz, InnerClassesInfoVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NameMarker.visitInnerClassesAttribute(Clazz, InnerClassesAttribute)"})
  void testVisitInnerClassesAttribute_thenCallsInnerClassEntriesAccept() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass clazz = new LibraryClass();
    InnerClassesAttribute innerClassesAttribute = mock(InnerClassesAttribute.class);
    doNothing().when(innerClassesAttribute)
        .innerClassEntriesAccept(Mockito.<Clazz>any(), Mockito.<InnerClassesInfoVisitor>any());

    // Act
    nameMarker.visitInnerClassesAttribute(clazz, innerClassesAttribute);

    // Assert
    verify(innerClassesAttribute).innerClassEntriesAccept(isA(Clazz.class), isA(InnerClassesInfoVisitor.class));
  }

  /**
   * Test {@link NameMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}.
   * <ul>
   *   <li>Given {@code Class Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NameMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}
   */
  @Test
  @DisplayName("Test visitInnerClassesInfo(Clazz, InnerClassesInfo); given 'Class Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NameMarker.visitInnerClassesInfo(Clazz, InnerClassesInfo)"})
  void testVisitInnerClassesInfo_givenClassName() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getClassName(anyInt())).thenReturn("Class Name");
    when(clazz.getName()).thenReturn("Name");

    // Act
    nameMarker.visitInnerClassesInfo(clazz, new InnerClassesInfo(1, 1, 1, 1));

    // Assert
    verify(clazz).getClassName(eq(1));
    verify(clazz).getName();
  }

  /**
   * Test {@link NameMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}.
   * <ul>
   *   <li>Then calls {@link LibraryClass#constantPoolEntryAccept(int, ConstantVisitor)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NameMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}
   */
  @Test
  @DisplayName("Test visitInnerClassesInfo(Clazz, InnerClassesInfo); then calls constantPoolEntryAccept(int, ConstantVisitor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NameMarker.visitInnerClassesInfo(Clazz, InnerClassesInfo)"})
  void testVisitInnerClassesInfo_thenCallsConstantPoolEntryAccept() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    doNothing().when(clazz).constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    when(clazz.getClassName(anyInt())).thenReturn("Name");
    when(clazz.getName()).thenReturn("Name");

    // Act
    nameMarker.visitInnerClassesInfo(clazz, new InnerClassesInfo(1, 1, 1, 1));

    // Assert
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(clazz).getClassName(eq(1));
    verify(clazz).getName();
  }

  /**
   * Test {@link NameMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}.
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NameMarker#visitInnerClassesInfo(Clazz, InnerClassesInfo)}
   */
  @Test
  @DisplayName("Test visitInnerClassesInfo(Clazz, InnerClassesInfo); then throw UnsupportedOperationException")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NameMarker.visitInnerClassesInfo(Clazz, InnerClassesInfo)"})
  void testVisitInnerClassesInfo_thenThrowUnsupportedOperationException() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass clazz = mock(LibraryClass.class);
    doThrow(new UnsupportedOperationException("Name")).when(clazz)
        .constantPoolEntryAccept(anyInt(), Mockito.<ConstantVisitor>any());
    when(clazz.getClassName(anyInt())).thenReturn("Name");
    when(clazz.getName()).thenReturn("Name");

    // Act and Assert
    assertThrows(UnsupportedOperationException.class,
        () -> nameMarker.visitInnerClassesInfo(clazz, new InnerClassesInfo(1, 1, 1, 1)));
    verify(clazz).constantPoolEntryAccept(eq(1), isA(ConstantVisitor.class));
    verify(clazz).getClassName(eq(1));
    verify(clazz).getName();
  }

  /**
   * Test {@link NameMarker#visitClassConstant(Clazz, ClassConstant)}.
   * <ul>
   *   <li>Then {@link ClassConstant#ClassConstant()} {@link ClassConstant#referencedClass} {@link LibraryClass}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NameMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then ClassConstant() referencedClass LibraryClass")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NameMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_thenClassConstantReferencedClassLibraryClass() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass clazz = new LibraryClass();
    ClassConstant classConstant = new ClassConstant();
    classConstant.referencedClass = new LibraryClass();

    // Act
    nameMarker.visitClassConstant(clazz, classConstant);

    // Assert that nothing has changed
    assertTrue(classConstant.referencedClass instanceof LibraryClass);
  }

  /**
   * Test {@link NameMarker#visitClassConstant(Clazz, ClassConstant)}.
   * <ul>
   *   <li>Then {@link ClassConstant#ClassConstant()} {@link ClassConstant#referencedClass} {@link NamedClass}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NameMarker#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then ClassConstant() referencedClass NamedClass")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NameMarker.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_thenClassConstantReferencedClassNamedClass() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass clazz = new LibraryClass();

    NamedClass namedClass = new NamedClass("Member Name");
    namedClass.addSubClass(new LibraryClass());
    ClassConstant classConstant = new ClassConstant();
    classConstant.referencedClass = namedClass;

    // Act
    nameMarker.visitClassConstant(clazz, classConstant);

    // Assert
    Clazz clazz2 = classConstant.referencedClass;
    assertTrue(clazz2 instanceof NamedClass);
    assertEquals("Member Name", clazz2.getProcessingInfo());
  }

  /**
   * Test {@link NameMarker#keepClassName(Clazz)}.
   * <p>
   * Method under test: {@link NameMarker#keepClassName(Clazz)}
   */
  @Test
  @DisplayName("Test keepClassName(Clazz)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NameMarker.keepClassName(Clazz)"})
  void testKeepClassName() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    ClassConstant classConstant = mock(ClassConstant.class);
    doNothing().when(classConstant).addProcessingFlags((int[]) Mockito.any());
    classConstant.addProcessingFlags(2, 1, 2, 1);
    ClassConstant classConstant2 = mock(ClassConstant.class);
    when(classConstant2.getName(Mockito.<Clazz>any())).thenReturn("Name");
    ProgramClass clazz = new ProgramClass(1, 3, new Constant[]{classConstant, classConstant2}, 1, 1, 1);

    // Act
    nameMarker.keepClassName(clazz);

    // Assert
    verify(classConstant2).getName(isA(Clazz.class));
    verify(classConstant).addProcessingFlags((int[]) Mockito.any());
    assertEquals("Name", clazz.getProcessingInfo());
  }

  /**
   * Test {@link NameMarker#keepClassName(Clazz)}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then {@link LibraryClass#LibraryClass()} ProcessingInfo is {@code null}.</li>
   * </ul>
   * <p>
   * Method under test: {@link NameMarker#keepClassName(Clazz)}
   */
  @Test
  @DisplayName("Test keepClassName(Clazz); when LibraryClass(); then LibraryClass() ProcessingInfo is 'null'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void NameMarker.keepClassName(Clazz)"})
  void testKeepClassName_whenLibraryClass_thenLibraryClassProcessingInfoIsNull() {
    // Arrange
    NameMarker nameMarker = new NameMarker();
    LibraryClass clazz = new LibraryClass();

    // Act
    nameMarker.keepClassName(clazz);

    // Assert that nothing has changed
    assertNull(clazz.getProcessingInfo());
  }
}
