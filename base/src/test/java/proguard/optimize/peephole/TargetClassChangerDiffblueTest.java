package proguard.optimize.peephole;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
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
import proguard.classfile.Field;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryField;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Member;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.Attribute;
import proguard.classfile.attribute.RecordComponentInfo;
import proguard.classfile.attribute.SignatureAttribute;
import proguard.classfile.attribute.annotation.Annotation;
import proguard.classfile.attribute.annotation.AnnotationDefaultAttribute;
import proguard.classfile.attribute.annotation.AnnotationElementValue;
import proguard.classfile.attribute.annotation.AnnotationsAttribute;
import proguard.classfile.attribute.annotation.ArrayElementValue;
import proguard.classfile.attribute.annotation.ParameterAnnotationsAttribute;
import proguard.classfile.attribute.annotation.RuntimeInvisibleAnnotationsAttribute;
import proguard.classfile.attribute.annotation.RuntimeInvisibleParameterAnnotationsAttribute;
import proguard.classfile.attribute.annotation.visitor.AnnotationVisitor;
import proguard.classfile.attribute.annotation.visitor.ElementValueVisitor;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.constant.AnyMethodrefConstant;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.FieldrefConstant;
import proguard.classfile.constant.InterfaceMethodrefConstant;
import proguard.classfile.constant.StringConstant;
import proguard.classfile.constant.visitor.ConstantVisitor;
import proguard.classfile.visitor.MemberVisitor;
import proguard.optimize.info.ClassOptimizationInfo;
import proguard.optimize.info.ProgramClassOptimizationInfo;

class TargetClassChangerDiffblueTest {
  /**
   * Test {@link TargetClassChanger#visitAnyClass(Clazz)}.
   *
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName(
      "Test visitAnyClass(Clazz); when LibraryClass(); then throw UnsupportedOperationException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitAnyClass(Clazz)"})
  void testVisitAnyClass_whenLibraryClass_thenThrowUnsupportedOperationException() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> targetClassChanger.visitAnyClass(new LibraryClass()));
  }

  /**
   * Test {@link TargetClassChanger#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then throw UnsupportedOperationException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_thenThrowUnsupportedOperationException() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenThrow(new UnsupportedOperationException("foo"));
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    ProgramClass programClass = mock(ProgramClass.class);
    when(programClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    doNothing().when(programClass).attributesAccept(Mockito.<AttributeVisitor>any());
    doNothing().when(programClass).constantPoolEntriesAccept(Mockito.<ConstantVisitor>any());
    doNothing().when(programClass).fieldsAccept(Mockito.<MemberVisitor>any());
    doNothing().when(programClass).methodsAccept(Mockito.<MemberVisitor>any());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> targetClassChanger.visitProgramClass(programClass));
    verify(programClass).attributesAccept(isA(AttributeVisitor.class));
    verify(programClass).constantPoolEntriesAccept(isA(ConstantVisitor.class));
    verify(programClass).fieldsAccept(isA(MemberVisitor.class));
    verify(programClass).methodsAccept(isA(MemberVisitor.class));
    verify(classOptimizationInfo).getTargetClass();
    verify(libraryClass).getProcessingInfo();
    verify(programClass).getProcessingInfo();
  }

  /**
   * Test {@link TargetClassChanger#visitLibraryClass(LibraryClass)}.
   *
   * <p>Method under test: {@link TargetClassChanger#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitLibraryClass(LibraryClass)"})
  void testVisitLibraryClass() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    Clazz clazz = mock(Clazz.class);
    when(clazz.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.addSubClass(clazz);

    // Act
    targetClassChanger.visitLibraryClass(libraryClass);

    // Assert
    verify(clazz).getProcessingInfo();
  }

  /**
   * Test {@link TargetClassChanger#visitLibraryClass(LibraryClass)}.
   *
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} {@link ClassOptimizationInfo#getTargetClass()} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName(
      "Test visitLibraryClass(LibraryClass); given ClassOptimizationInfo getTargetClass() return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitLibraryClass(LibraryClass)"})
  void testVisitLibraryClass_givenClassOptimizationInfoGetTargetClassReturnNull() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(null);
    Clazz clazz = mock(Clazz.class);
    when(clazz.getProcessingInfo()).thenReturn(classOptimizationInfo);

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.addSubClass(clazz);

    // Act
    targetClassChanger.visitLibraryClass(libraryClass);

    // Assert
    verify(classOptimizationInfo).getTargetClass();
    verify(clazz).getProcessingInfo();
  }

  /**
   * Test {@link TargetClassChanger#visitLibraryClass(LibraryClass)}.
   *
   * <ul>
   *   <li>Given {@link Clazz} {@link Clazz#getProcessingInfo()} return {@link
   *       ClassOptimizationInfo} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName(
      "Test visitLibraryClass(LibraryClass); given Clazz getProcessingInfo() return ClassOptimizationInfo (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitLibraryClass(LibraryClass)"})
  void testVisitLibraryClass_givenClazzGetProcessingInfoReturnClassOptimizationInfo() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    Clazz clazz = mock(Clazz.class);
    when(clazz.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.addSubClass(clazz);

    // Act
    targetClassChanger.visitLibraryClass(libraryClass);

    // Assert
    verify(clazz).getProcessingInfo();
  }

  /**
   * Test {@link TargetClassChanger#visitLibraryClass(LibraryClass)}.
   *
   * <ul>
   *   <li>Then calls {@link LibraryClass#getProcessingInfo()}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass); then calls getProcessingInfo()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitLibraryClass(LibraryClass)"})
  void testVisitLibraryClass_thenCallsGetProcessingInfo() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    Clazz clazz = mock(Clazz.class);
    when(clazz.getProcessingInfo()).thenReturn(classOptimizationInfo);

    LibraryClass libraryClass2 = new LibraryClass();
    libraryClass2.addSubClass(clazz);

    // Act
    targetClassChanger.visitLibraryClass(libraryClass2);

    // Assert
    verify(classOptimizationInfo).getTargetClass();
    verify(clazz).getProcessingInfo();
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Test {@link TargetClassChanger#visitProgramField(ProgramClass, ProgramField)}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitProgramField(ProgramClass, ProgramField)}
   */
  @Test
  @DisplayName(
      "Test visitProgramField(ProgramClass, ProgramField); then throw UnsupportedOperationException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitProgramField(ProgramClass, ProgramField)"})
  void testVisitProgramField_thenThrowUnsupportedOperationException() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    ProgramClass programClass = new ProgramClass();
    ProgramField programField = mock(ProgramField.class);
    doThrow(new UnsupportedOperationException("foo"))
        .when(programField)
        .attributesAccept(Mockito.<ProgramClass>any(), Mockito.<AttributeVisitor>any());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> targetClassChanger.visitProgramField(programClass, programField));
    verify(programField).attributesAccept(isA(ProgramClass.class), isA(AttributeVisitor.class));
  }

  /**
   * Test {@link TargetClassChanger#visitProgramMethod(ProgramClass, ProgramMethod)}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitProgramMethod(ProgramClass,
   * ProgramMethod)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMethod(ProgramClass, ProgramMethod); then throw UnsupportedOperationException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitProgramMethod(ProgramClass, ProgramMethod)"})
  void testVisitProgramMethod_thenThrowUnsupportedOperationException() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMethod = mock(ProgramMethod.class);
    doThrow(new UnsupportedOperationException("foo"))
        .when(programMethod)
        .attributesAccept(Mockito.<ProgramClass>any(), Mockito.<AttributeVisitor>any());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> targetClassChanger.visitProgramMethod(programClass, programMethod));
    verify(programMethod).attributesAccept(isA(ProgramClass.class), isA(AttributeVisitor.class));
  }

  /**
   * Test {@link TargetClassChanger#visitStringConstant(Clazz, StringConstant)}.
   *
   * <p>Method under test: {@link TargetClassChanger#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");
    doNothing().when(clazz).addSubClass(Mockito.<Clazz>any());
    clazz.addSubClass(new LibraryClass());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.findField(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new LibraryField(1, "Name", "Descriptor"));
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    StringConstant stringConstant =
        new StringConstant(1, referencedClass, new LibraryField(1, "Name", "Descriptor"));

    // Act
    targetClassChanger.visitStringConstant(clazz, stringConstant);

    // Assert that nothing has changed
    verify(clazz).addSubClass(isA(Clazz.class));
    verify(libraryClass).findField(eq("String"), isNull());
    verify(clazz).getString(eq(1));
    verify(classOptimizationInfo).getTargetClass();
    verify(referencedClass).getProcessingInfo();
    verify(libraryClass).getProcessingInfo();
    Member member = stringConstant.referencedMember;
    assertTrue(member instanceof LibraryField);
    assertEquals("Descriptor", ((LibraryField) member).descriptor);
    assertEquals("Name", ((LibraryField) member).name);
    assertEquals(1, member.getAccessFlags());
  }

  /**
   * Test {@link TargetClassChanger#visitStringConstant(Clazz, StringConstant)}.
   *
   * <p>Method under test: {@link TargetClassChanger#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant2() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");
    doNothing().when(clazz).addSubClass(Mockito.<Clazz>any());
    clazz.addSubClass(new LibraryClass());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.findField(Mockito.<String>any(), Mockito.<String>any()))
        .thenThrow(new UnsupportedOperationException("foo"));
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            targetClassChanger.visitStringConstant(
                clazz,
                new StringConstant(1, referencedClass, new LibraryField(1, "Name", "Descriptor"))));
    verify(clazz).addSubClass(isA(Clazz.class));
    verify(libraryClass).findField(eq("String"), isNull());
    verify(clazz).getString(eq(1));
    verify(classOptimizationInfo).getTargetClass();
    verify(referencedClass).getProcessingInfo();
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Test {@link TargetClassChanger#visitStringConstant(Clazz, StringConstant)}.
   *
   * <p>Method under test: {@link TargetClassChanger#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant3() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");
    doNothing().when(clazz).addSubClass(Mockito.<Clazz>any());
    clazz.addSubClass(new LibraryClass());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act
    targetClassChanger.visitStringConstant(clazz, new StringConstant(1, referencedClass, null));

    // Assert
    verify(clazz).addSubClass(isA(Clazz.class));
    verify(clazz).getString(eq(1));
    verify(classOptimizationInfo).getTargetClass();
    verify(referencedClass).getProcessingInfo();
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Test {@link TargetClassChanger#visitStringConstant(Clazz, StringConstant)}.
   *
   * <p>Method under test: {@link TargetClassChanger#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant4() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");
    doNothing().when(clazz).addSubClass(Mockito.<Clazz>any());
    clazz.addSubClass(new LibraryClass());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.findMethod(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new LibraryMethod(1, "Name", "Descriptor"));
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    StringConstant stringConstant = new StringConstant(1, referencedClass, new LibraryMethod());

    // Act
    targetClassChanger.visitStringConstant(clazz, stringConstant);

    // Assert
    verify(clazz).addSubClass(isA(Clazz.class));
    verify(libraryClass).findMethod(eq("String"), isNull());
    verify(clazz).getString(eq(1));
    verify(classOptimizationInfo).getTargetClass();
    verify(referencedClass).getProcessingInfo();
    verify(libraryClass).getProcessingInfo();
    Member member = stringConstant.referencedMember;
    assertTrue(member instanceof LibraryMethod);
    assertEquals("Descriptor", ((LibraryMethod) member).descriptor);
    assertEquals("Name", ((LibraryMethod) member).name);
    assertEquals(1, member.getAccessFlags());
  }

  /**
   * Test {@link TargetClassChanger#visitStringConstant(Clazz, StringConstant)}.
   *
   * <p>Method under test: {@link TargetClassChanger#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName("Test visitStringConstant(Clazz, StringConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant5() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getString(anyInt())).thenReturn("String");
    doNothing().when(clazz).addSubClass(Mockito.<Clazz>any());
    clazz.addSubClass(new LibraryClass());
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.findMethod(Mockito.<String>any(), Mockito.<String>any()))
        .thenThrow(new UnsupportedOperationException("foo"));
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            targetClassChanger.visitStringConstant(
                clazz, new StringConstant(1, referencedClass, new LibraryMethod())));
    verify(clazz).addSubClass(isA(Clazz.class));
    verify(libraryClass).findMethod(eq("String"), isNull());
    verify(clazz).getString(eq(1));
    verify(classOptimizationInfo).getTargetClass();
    verify(referencedClass).getProcessingInfo();
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Test {@link TargetClassChanger#visitStringConstant(Clazz, StringConstant)}.
   *
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName(
      "Test visitStringConstant(Clazz, StringConstant); given ClassOptimizationInfo (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant_givenClassOptimizationInfo() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    StringConstant stringConstant =
        new StringConstant(1, referencedClass, new LibraryField(1, "Name", "Descriptor"));

    // Act
    targetClassChanger.visitStringConstant(clazz, stringConstant);

    // Assert that nothing has changed
    verify(referencedClass).getProcessingInfo();
    Member member = stringConstant.referencedMember;
    assertTrue(member instanceof LibraryField);
    assertEquals("Descriptor", ((LibraryField) member).descriptor);
    assertEquals("Name", ((LibraryField) member).name);
    assertEquals(1, member.getAccessFlags());
  }

  /**
   * Test {@link TargetClassChanger#visitStringConstant(Clazz, StringConstant)}.
   *
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} {@link ClassOptimizationInfo#getTargetClass()} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName(
      "Test visitStringConstant(Clazz, StringConstant); given ClassOptimizationInfo getTargetClass() return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant_givenClassOptimizationInfoGetTargetClassReturnNull() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(null);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    StringConstant stringConstant =
        new StringConstant(1, referencedClass, new LibraryField(1, "Name", "Descriptor"));

    // Act
    targetClassChanger.visitStringConstant(clazz, stringConstant);

    // Assert that nothing has changed
    verify(classOptimizationInfo).getTargetClass();
    verify(referencedClass).getProcessingInfo();
    Member member = stringConstant.referencedMember;
    assertTrue(member instanceof LibraryField);
    assertEquals("Descriptor", ((LibraryField) member).descriptor);
    assertEquals("Name", ((LibraryField) member).name);
    assertEquals(1, member.getAccessFlags());
  }

  /**
   * Test {@link TargetClassChanger#visitStringConstant(Clazz, StringConstant)}.
   *
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName(
      "Test visitStringConstant(Clazz, StringConstant); given ProgramClassOptimizationInfo (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant_givenProgramClassOptimizationInfo() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());
    StringConstant stringConstant =
        new StringConstant(1, referencedClass, new LibraryField(1, "Name", "Descriptor"));

    // Act
    targetClassChanger.visitStringConstant(clazz, stringConstant);

    // Assert that nothing has changed
    verify(referencedClass).getProcessingInfo();
    Member member = stringConstant.referencedMember;
    assertTrue(member instanceof LibraryField);
    assertEquals("Descriptor", ((LibraryField) member).descriptor);
    assertEquals("Name", ((LibraryField) member).name);
    assertEquals(1, member.getAccessFlags());
  }

  /**
   * Test {@link TargetClassChanger#visitStringConstant(Clazz, StringConstant)}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitStringConstant(Clazz, StringConstant)}
   */
  @Test
  @DisplayName(
      "Test visitStringConstant(Clazz, StringConstant); then throw UnsupportedOperationException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitStringConstant(Clazz, StringConstant)"})
  void testVisitStringConstant_thenThrowUnsupportedOperationException() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            targetClassChanger.visitStringConstant(
                clazz,
                new StringConstant(1, referencedClass, new LibraryField(1, "Name", "Descriptor"))));
    verify(classOptimizationInfo).getTargetClass();
    verify(referencedClass).getProcessingInfo();
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Test {@link TargetClassChanger#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   *
   * <p>Method under test: {@link TargetClassChanger#visitAnyMethodrefConstant(Clazz,
   * AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TargetClassChanger.visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)"
  })
  void testVisitAnyMethodrefConstant() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    InterfaceMethodrefConstant refConstant = new InterfaceMethodrefConstant();

    // Act
    targetClassChanger.visitAnyMethodrefConstant(clazz, refConstant);

    // Assert that nothing has changed
    assertNull(refConstant.referencedMethod);
  }

  /**
   * Test {@link TargetClassChanger#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   *
   * <p>Method under test: {@link TargetClassChanger#visitAnyMethodrefConstant(Clazz,
   * AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TargetClassChanger.visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)"
  })
  void testVisitAnyMethodrefConstant2() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getName())
        .thenThrow(new UnsupportedOperationException("TargetClassChanger:"));
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            targetClassChanger.visitAnyMethodrefConstant(
                clazz,
                new InterfaceMethodrefConstant(
                    1, 1, referencedClass, new LibraryMethod(1, "Name", "Descriptor"))));
    verify(referencedClass).getName();
    verify(classOptimizationInfo).getTargetClass();
    verify(referencedClass).getProcessingInfo();
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Test {@link TargetClassChanger#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   *
   * <p>Method under test: {@link TargetClassChanger#visitAnyMethodrefConstant(Clazz,
   * AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TargetClassChanger.visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)"
  })
  void testVisitAnyMethodrefConstant3() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName()).thenReturn("Name");
    when(clazz.getName(anyInt())).thenReturn("Name");
    when(clazz.getType(anyInt())).thenReturn("Type");
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.findMethod(Mockito.<String>any(), Mockito.<String>any()))
        .thenThrow(new UnsupportedOperationException("TargetClassChanger:"));
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getName()).thenReturn("Name");
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            targetClassChanger.visitAnyMethodrefConstant(
                clazz,
                new InterfaceMethodrefConstant(
                    1, 1, referencedClass, new LibraryMethod(1, "Name", "Descriptor"))));
    verify(referencedClass).getName();
    verify(libraryClass).findMethod(eq("Name"), eq("Type"));
    verify(clazz).getName();
    verify(clazz).getName(eq(1));
    verify(clazz).getType(eq(1));
    verify(classOptimizationInfo).getTargetClass();
    verify(referencedClass).getProcessingInfo();
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Test {@link TargetClassChanger#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   *
   * <p>Method under test: {@link TargetClassChanger#visitAnyMethodrefConstant(Clazz,
   * AnyMethodrefConstant)}
   */
  @Test
  @DisplayName("Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TargetClassChanger.visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)"
  })
  void testVisitAnyMethodrefConstant4() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName()).thenReturn("Name");
    when(clazz.getName(anyInt())).thenReturn("Name");
    when(clazz.getType(anyInt())).thenReturn("Type");
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.findMethod(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new LibraryMethod(1, "Name", "Descriptor"));
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getString(anyInt())).thenReturn("String");
    when(referencedClass.getName()).thenReturn("Name");
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    InterfaceMethodrefConstant refConstant =
        new InterfaceMethodrefConstant(1, 1, referencedClass, new ProgramMethod());

    // Act
    targetClassChanger.visitAnyMethodrefConstant(clazz, refConstant);

    // Assert
    verify(referencedClass).getName();
    verify(referencedClass, atLeast(1)).getString(eq(0));
    verify(libraryClass).findMethod(eq("Name"), eq("Type"));
    verify(clazz, atLeast(1)).getName();
    verify(clazz).getName(eq(1));
    verify(clazz).getType(eq(1));
    verify(classOptimizationInfo).getTargetClass();
    verify(referencedClass).getProcessingInfo();
    verify(libraryClass).getProcessingInfo();
    Method method = refConstant.referencedMethod;
    assertTrue(method instanceof LibraryMethod);
    assertEquals("Descriptor", ((LibraryMethod) method).descriptor);
    assertEquals("Name", ((LibraryMethod) method).name);
    assertEquals(1, method.getAccessFlags());
  }

  /**
   * Test {@link TargetClassChanger#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   *
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitAnyMethodrefConstant(Clazz,
   * AnyMethodrefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant); given ClassOptimizationInfo (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TargetClassChanger.visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)"
  })
  void testVisitAnyMethodrefConstant_givenClassOptimizationInfo() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    InterfaceMethodrefConstant refConstant =
        new InterfaceMethodrefConstant(
            1, 1, referencedClass, new LibraryMethod(1, "Name", "Descriptor"));

    // Act
    targetClassChanger.visitAnyMethodrefConstant(clazz, refConstant);

    // Assert that nothing has changed
    verify(referencedClass).getProcessingInfo();
    Method method = refConstant.referencedMethod;
    assertTrue(method instanceof LibraryMethod);
    assertEquals("Descriptor", ((LibraryMethod) method).descriptor);
    assertEquals("Name", ((LibraryMethod) method).name);
    assertEquals(1, method.getAccessFlags());
  }

  /**
   * Test {@link TargetClassChanger#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   *
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} {@link ClassOptimizationInfo#getTargetClass()} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitAnyMethodrefConstant(Clazz,
   * AnyMethodrefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant); given ClassOptimizationInfo getTargetClass() return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TargetClassChanger.visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)"
  })
  void testVisitAnyMethodrefConstant_givenClassOptimizationInfoGetTargetClassReturnNull() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(null);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    InterfaceMethodrefConstant refConstant =
        new InterfaceMethodrefConstant(
            1, 1, referencedClass, new LibraryMethod(1, "Name", "Descriptor"));

    // Act
    targetClassChanger.visitAnyMethodrefConstant(clazz, refConstant);

    // Assert that nothing has changed
    verify(classOptimizationInfo).getTargetClass();
    verify(referencedClass).getProcessingInfo();
    Method method = refConstant.referencedMethod;
    assertTrue(method instanceof LibraryMethod);
    assertEquals("Descriptor", ((LibraryMethod) method).descriptor);
    assertEquals("Name", ((LibraryMethod) method).name);
    assertEquals(1, method.getAccessFlags());
  }

  /**
   * Test {@link TargetClassChanger#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   *
   * <ul>
   *   <li>Given {@code Name}.
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitAnyMethodrefConstant(Clazz,
   * AnyMethodrefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant); given 'Name'; then throw UnsupportedOperationException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TargetClassChanger.visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)"
  })
  void testVisitAnyMethodrefConstant_givenName_thenThrowUnsupportedOperationException() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getName()).thenReturn("Name");
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            targetClassChanger.visitAnyMethodrefConstant(
                clazz,
                new InterfaceMethodrefConstant(
                    1, 1, referencedClass, new LibraryMethod(1, "Name", "Descriptor"))));
    verify(referencedClass).getName();
    verify(classOptimizationInfo).getTargetClass();
    verify(referencedClass).getProcessingInfo();
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Test {@link TargetClassChanger#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   *
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitAnyMethodrefConstant(Clazz,
   * AnyMethodrefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant); given ProgramClassOptimizationInfo (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TargetClassChanger.visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)"
  })
  void testVisitAnyMethodrefConstant_givenProgramClassOptimizationInfo() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());
    InterfaceMethodrefConstant refConstant =
        new InterfaceMethodrefConstant(
            1, 1, referencedClass, new LibraryMethod(1, "Name", "Descriptor"));

    // Act
    targetClassChanger.visitAnyMethodrefConstant(clazz, refConstant);

    // Assert that nothing has changed
    verify(referencedClass).getProcessingInfo();
    Method method = refConstant.referencedMethod;
    assertTrue(method instanceof LibraryMethod);
    assertEquals("Descriptor", ((LibraryMethod) method).descriptor);
    assertEquals("Name", ((LibraryMethod) method).name);
    assertEquals(1, method.getAccessFlags());
  }

  /**
   * Test {@link TargetClassChanger#visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link LibraryClass#findMethod(String, String)}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitAnyMethodrefConstant(Clazz,
   * AnyMethodrefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant); then calls findMethod(String, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TargetClassChanger.visitAnyMethodrefConstant(Clazz, AnyMethodrefConstant)"
  })
  void testVisitAnyMethodrefConstant_thenCallsFindMethod() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName()).thenReturn("Name");
    when(clazz.getName(anyInt())).thenReturn("Name");
    when(clazz.getType(anyInt())).thenReturn("Type");
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.findMethod(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new LibraryMethod(1, "Name", "Descriptor"));
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getName()).thenReturn("Name");
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    InterfaceMethodrefConstant refConstant =
        new InterfaceMethodrefConstant(
            1, 1, referencedClass, new LibraryMethod(1, "Name", "Descriptor"));

    // Act
    targetClassChanger.visitAnyMethodrefConstant(clazz, refConstant);

    // Assert that nothing has changed
    verify(referencedClass).getName();
    verify(libraryClass).findMethod(eq("Name"), eq("Type"));
    verify(clazz, atLeast(1)).getName();
    verify(clazz).getName(eq(1));
    verify(clazz).getType(eq(1));
    verify(classOptimizationInfo).getTargetClass();
    verify(referencedClass).getProcessingInfo();
    verify(libraryClass).getProcessingInfo();
    Method method = refConstant.referencedMethod;
    assertTrue(method instanceof LibraryMethod);
    assertEquals("Descriptor", ((LibraryMethod) method).descriptor);
    assertEquals("Name", ((LibraryMethod) method).name);
    assertEquals(1, method.getAccessFlags());
  }

  /**
   * Test {@link TargetClassChanger#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   *
   * <p>Method under test: {@link TargetClassChanger#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  @DisplayName("Test visitFieldrefConstant(Clazz, FieldrefConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitFieldrefConstant(Clazz, FieldrefConstant)"})
  void testVisitFieldrefConstant() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getName())
        .thenThrow(new UnsupportedOperationException("TargetClassChanger:"));
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            targetClassChanger.visitFieldrefConstant(
                clazz,
                new FieldrefConstant(
                    1, 1, referencedClass, new LibraryField(1, "Name", "Descriptor"))));
    verify(referencedClass).getName();
    verify(classOptimizationInfo).getTargetClass();
    verify(referencedClass).getProcessingInfo();
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Test {@link TargetClassChanger#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   *
   * <p>Method under test: {@link TargetClassChanger#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  @DisplayName("Test visitFieldrefConstant(Clazz, FieldrefConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitFieldrefConstant(Clazz, FieldrefConstant)"})
  void testVisitFieldrefConstant2() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName()).thenReturn("Name");
    when(clazz.getName(anyInt())).thenReturn("Name");
    when(clazz.getType(anyInt())).thenReturn("Type");
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.findField(Mockito.<String>any(), Mockito.<String>any()))
        .thenThrow(new UnsupportedOperationException("TargetClassChanger:"));
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getName()).thenReturn("Name");
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            targetClassChanger.visitFieldrefConstant(
                clazz,
                new FieldrefConstant(
                    1, 1, referencedClass, new LibraryField(1, "Name", "Descriptor"))));
    verify(referencedClass).getName();
    verify(libraryClass).findField(eq("Name"), eq("Type"));
    verify(clazz).getName();
    verify(clazz).getName(eq(1));
    verify(clazz).getType(eq(1));
    verify(classOptimizationInfo).getTargetClass();
    verify(referencedClass).getProcessingInfo();
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Test {@link TargetClassChanger#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   *
   * <p>Method under test: {@link TargetClassChanger#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  @DisplayName("Test visitFieldrefConstant(Clazz, FieldrefConstant)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitFieldrefConstant(Clazz, FieldrefConstant)"})
  void testVisitFieldrefConstant3() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName()).thenReturn("Name");
    when(clazz.getName(anyInt())).thenReturn("Name");
    when(clazz.getType(anyInt())).thenReturn("Type");
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.findField(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new LibraryField(1, "Name", "Descriptor"));
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getString(anyInt())).thenReturn("String");
    when(referencedClass.getName()).thenReturn("Name");
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    FieldrefConstant refConstant = new FieldrefConstant(1, 1, referencedClass, new ProgramField());

    // Act
    targetClassChanger.visitFieldrefConstant(clazz, refConstant);

    // Assert
    verify(referencedClass).getName();
    verify(referencedClass, atLeast(1)).getString(eq(0));
    verify(libraryClass).findField(eq("Name"), eq("Type"));
    verify(clazz, atLeast(1)).getName();
    verify(clazz).getName(eq(1));
    verify(clazz).getType(eq(1));
    verify(classOptimizationInfo).getTargetClass();
    verify(referencedClass).getProcessingInfo();
    verify(libraryClass).getProcessingInfo();
    Field field = refConstant.referencedField;
    assertTrue(field instanceof LibraryField);
    assertEquals("Descriptor", ((LibraryField) field).descriptor);
    assertEquals("Name", ((LibraryField) field).name);
    assertEquals(1, field.getAccessFlags());
  }

  /**
   * Test {@link TargetClassChanger#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   *
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitFieldrefConstant(Clazz, FieldrefConstant); given ClassOptimizationInfo (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitFieldrefConstant(Clazz, FieldrefConstant)"})
  void testVisitFieldrefConstant_givenClassOptimizationInfo() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    FieldrefConstant refConstant =
        new FieldrefConstant(1, 1, referencedClass, new LibraryField(1, "Name", "Descriptor"));

    // Act
    targetClassChanger.visitFieldrefConstant(clazz, refConstant);

    // Assert that nothing has changed
    verify(referencedClass).getProcessingInfo();
    Field field = refConstant.referencedField;
    assertTrue(field instanceof LibraryField);
    assertEquals("Descriptor", ((LibraryField) field).descriptor);
    assertEquals("Name", ((LibraryField) field).name);
    assertEquals(1, field.getAccessFlags());
  }

  /**
   * Test {@link TargetClassChanger#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   *
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} {@link ClassOptimizationInfo#getTargetClass()} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitFieldrefConstant(Clazz, FieldrefConstant); given ClassOptimizationInfo getTargetClass() return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitFieldrefConstant(Clazz, FieldrefConstant)"})
  void testVisitFieldrefConstant_givenClassOptimizationInfoGetTargetClassReturnNull() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(null);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    FieldrefConstant refConstant =
        new FieldrefConstant(1, 1, referencedClass, new LibraryField(1, "Name", "Descriptor"));

    // Act
    targetClassChanger.visitFieldrefConstant(clazz, refConstant);

    // Assert that nothing has changed
    verify(classOptimizationInfo).getTargetClass();
    verify(referencedClass).getProcessingInfo();
    Field field = refConstant.referencedField;
    assertTrue(field instanceof LibraryField);
    assertEquals("Descriptor", ((LibraryField) field).descriptor);
    assertEquals("Name", ((LibraryField) field).name);
    assertEquals(1, field.getAccessFlags());
  }

  /**
   * Test {@link TargetClassChanger#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   *
   * <ul>
   *   <li>Given {@code Name}.
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitFieldrefConstant(Clazz, FieldrefConstant); given 'Name'; then throw UnsupportedOperationException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitFieldrefConstant(Clazz, FieldrefConstant)"})
  void testVisitFieldrefConstant_givenName_thenThrowUnsupportedOperationException() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getName()).thenReturn("Name");
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            targetClassChanger.visitFieldrefConstant(
                clazz,
                new FieldrefConstant(
                    1, 1, referencedClass, new LibraryField(1, "Name", "Descriptor"))));
    verify(referencedClass).getName();
    verify(classOptimizationInfo).getTargetClass();
    verify(referencedClass).getProcessingInfo();
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Test {@link TargetClassChanger#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   *
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitFieldrefConstant(Clazz, FieldrefConstant); given ProgramClassOptimizationInfo (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitFieldrefConstant(Clazz, FieldrefConstant)"})
  void testVisitFieldrefConstant_givenProgramClassOptimizationInfo() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());
    FieldrefConstant refConstant =
        new FieldrefConstant(1, 1, referencedClass, new LibraryField(1, "Name", "Descriptor"));

    // Act
    targetClassChanger.visitFieldrefConstant(clazz, refConstant);

    // Assert that nothing has changed
    verify(referencedClass).getProcessingInfo();
    Field field = refConstant.referencedField;
    assertTrue(field instanceof LibraryField);
    assertEquals("Descriptor", ((LibraryField) field).descriptor);
    assertEquals("Name", ((LibraryField) field).name);
    assertEquals(1, field.getAccessFlags());
  }

  /**
   * Test {@link TargetClassChanger#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link LibraryClass#findField(String, String)}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitFieldrefConstant(Clazz, FieldrefConstant); then calls findField(String, String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitFieldrefConstant(Clazz, FieldrefConstant)"})
  void testVisitFieldrefConstant_thenCallsFindField() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = mock(LibraryClass.class);
    when(clazz.getName()).thenReturn("Name");
    when(clazz.getName(anyInt())).thenReturn("Name");
    when(clazz.getType(anyInt())).thenReturn("Type");
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.findField(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new LibraryField(1, "Name", "Descriptor"));
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getName()).thenReturn("Name");
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);
    FieldrefConstant refConstant =
        new FieldrefConstant(1, 1, referencedClass, new LibraryField(1, "Name", "Descriptor"));

    // Act
    targetClassChanger.visitFieldrefConstant(clazz, refConstant);

    // Assert that nothing has changed
    verify(referencedClass).getName();
    verify(libraryClass).findField(eq("Name"), eq("Type"));
    verify(clazz, atLeast(1)).getName();
    verify(clazz).getName(eq(1));
    verify(clazz).getType(eq(1));
    verify(classOptimizationInfo).getTargetClass();
    verify(referencedClass).getProcessingInfo();
    verify(libraryClass).getProcessingInfo();
    Field field = refConstant.referencedField;
    assertTrue(field instanceof LibraryField);
    assertEquals("Descriptor", ((LibraryField) field).descriptor);
    assertEquals("Name", ((LibraryField) field).name);
    assertEquals(1, field.getAccessFlags());
  }

  /**
   * Test {@link TargetClassChanger#visitFieldrefConstant(Clazz, FieldrefConstant)}.
   *
   * <ul>
   *   <li>Then {@link FieldrefConstant#FieldrefConstant()} {@link FieldrefConstant#referencedField}
   *       is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitFieldrefConstant(Clazz, FieldrefConstant)}
   */
  @Test
  @DisplayName(
      "Test visitFieldrefConstant(Clazz, FieldrefConstant); then FieldrefConstant() referencedField is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitFieldrefConstant(Clazz, FieldrefConstant)"})
  void testVisitFieldrefConstant_thenFieldrefConstantReferencedFieldIsNull() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    FieldrefConstant refConstant = new FieldrefConstant();

    // Act
    targetClassChanger.visitFieldrefConstant(clazz, refConstant);

    // Assert that nothing has changed
    assertNull(refConstant.referencedField);
  }

  /**
   * Test {@link TargetClassChanger#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName(
      "Test visitClassConstant(Clazz, ClassConstant); given ClassOptimizationInfo (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_givenClassOptimizationInfo() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());

    // Act
    targetClassChanger.visitClassConstant(clazz, new ClassConstant(1, referencedClass));

    // Assert
    verify(referencedClass).getProcessingInfo();
  }

  /**
   * Test {@link TargetClassChanger#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <ul>
   *   <li>Given {@link ClassOptimizationInfo} {@link ClassOptimizationInfo#getTargetClass()} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName(
      "Test visitClassConstant(Clazz, ClassConstant); given ClassOptimizationInfo getTargetClass() return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_givenClassOptimizationInfoGetTargetClassReturnNull() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(null);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act
    targetClassChanger.visitClassConstant(clazz, new ClassConstant(1, referencedClass));

    // Assert
    verify(classOptimizationInfo).getTargetClass();
    verify(referencedClass).getProcessingInfo();
  }

  /**
   * Test {@link TargetClassChanger#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <ul>
   *   <li>Given {@link ProgramClassOptimizationInfo} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName(
      "Test visitClassConstant(Clazz, ClassConstant); given ProgramClassOptimizationInfo (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_givenProgramClassOptimizationInfo() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());

    // Act
    targetClassChanger.visitClassConstant(clazz, new ClassConstant(1, referencedClass));

    // Assert
    verify(referencedClass).getProcessingInfo();
  }

  /**
   * Test {@link TargetClassChanger#visitClassConstant(Clazz, ClassConstant)}.
   *
   * <ul>
   *   <li>Then calls {@link LibraryClass#getProcessingInfo()}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitClassConstant(Clazz, ClassConstant)}
   */
  @Test
  @DisplayName("Test visitClassConstant(Clazz, ClassConstant); then calls getProcessingInfo()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitClassConstant(Clazz, ClassConstant)"})
  void testVisitClassConstant_thenCallsGetProcessingInfo() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    LibraryClass libraryClass = mock(LibraryClass.class);
    when(libraryClass.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(libraryClass);
    Clazz referencedClass = mock(Clazz.class);
    when(referencedClass.getProcessingInfo()).thenReturn(classOptimizationInfo);

    // Act
    targetClassChanger.visitClassConstant(clazz, new ClassConstant(1, referencedClass));

    // Assert
    verify(classOptimizationInfo).getTargetClass();
    verify(referencedClass).getProcessingInfo();
    verify(libraryClass).getProcessingInfo();
  }

  /**
   * Test {@link TargetClassChanger#visitSignatureAttribute(Clazz, SignatureAttribute)} with {@code
   * clazz}, {@code signatureAttribute}.
   *
   * <p>Method under test: {@link TargetClassChanger#visitSignatureAttribute(Clazz,
   * SignatureAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitSignatureAttribute(Clazz, SignatureAttribute) with 'clazz', 'signatureAttribute'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitSignatureAttribute(Clazz, SignatureAttribute)"})
  void testVisitSignatureAttributeWithClazzSignatureAttribute() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    Clazz clazz2 = mock(Clazz.class);
    when(clazz2.getProcessingInfo()).thenReturn(new ClassOptimizationInfo());
    SignatureAttribute signatureAttribute = new SignatureAttribute(1, 1);

    signatureAttribute.referencedClasses = new Clazz[] {clazz2};

    // Act
    targetClassChanger.visitSignatureAttribute(clazz, signatureAttribute);

    // Assert
    verify(clazz2).getProcessingInfo();
  }

  /**
   * Test {@link TargetClassChanger#visitSignatureAttribute(Clazz, SignatureAttribute)} with {@code
   * clazz}, {@code signatureAttribute}.
   *
   * <p>Method under test: {@link TargetClassChanger#visitSignatureAttribute(Clazz,
   * SignatureAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitSignatureAttribute(Clazz, SignatureAttribute) with 'clazz', 'signatureAttribute'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitSignatureAttribute(Clazz, SignatureAttribute)"})
  void testVisitSignatureAttributeWithClazzSignatureAttribute2() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    Clazz clazz2 = mock(Clazz.class);
    when(clazz2.getProcessingInfo()).thenReturn(new ProgramClassOptimizationInfo());
    SignatureAttribute signatureAttribute = new SignatureAttribute(1, 1);

    signatureAttribute.referencedClasses = new Clazz[] {clazz2};

    // Act
    targetClassChanger.visitSignatureAttribute(clazz, signatureAttribute);

    // Assert
    verify(clazz2).getProcessingInfo();
  }

  /**
   * Test {@link TargetClassChanger#visitSignatureAttribute(Clazz, SignatureAttribute)} with {@code
   * clazz}, {@code signatureAttribute}.
   *
   * <ul>
   *   <li>Then calls {@link ClassOptimizationInfo#getTargetClass()}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitSignatureAttribute(Clazz,
   * SignatureAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitSignatureAttribute(Clazz, SignatureAttribute) with 'clazz', 'signatureAttribute'; then calls getTargetClass()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitSignatureAttribute(Clazz, SignatureAttribute)"})
  void testVisitSignatureAttributeWithClazzSignatureAttribute_thenCallsGetTargetClass() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    ClassOptimizationInfo classOptimizationInfo = mock(ClassOptimizationInfo.class);
    when(classOptimizationInfo.getTargetClass()).thenReturn(null);
    Clazz clazz2 = mock(Clazz.class);
    when(clazz2.getProcessingInfo()).thenReturn(classOptimizationInfo);
    SignatureAttribute signatureAttribute = new SignatureAttribute(1, 1);

    signatureAttribute.referencedClasses = new Clazz[] {clazz2};

    // Act
    targetClassChanger.visitSignatureAttribute(clazz, signatureAttribute);

    // Assert
    verify(classOptimizationInfo).getTargetClass();
    verify(clazz2).getProcessingInfo();
  }

  /**
   * Test {@link TargetClassChanger#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}.
   *
   * <ul>
   *   <li>Then calls {@link RuntimeInvisibleAnnotationsAttribute#annotationsAccept(Clazz,
   *       AnnotationVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitAnyAnnotationsAttribute(Clazz,
   * AnnotationsAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute); then calls annotationsAccept(Clazz, AnnotationVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TargetClassChanger.visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)"
  })
  void testVisitAnyAnnotationsAttribute_thenCallsAnnotationsAccept() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    RuntimeInvisibleAnnotationsAttribute annotationsAttribute =
        mock(RuntimeInvisibleAnnotationsAttribute.class);
    doNothing()
        .when(annotationsAttribute)
        .annotationsAccept(Mockito.<Clazz>any(), Mockito.<AnnotationVisitor>any());

    // Act
    targetClassChanger.visitAnyAnnotationsAttribute(clazz, annotationsAttribute);

    // Assert
    verify(annotationsAttribute).annotationsAccept(isA(Clazz.class), isA(AnnotationVisitor.class));
  }

  /**
   * Test {@link TargetClassChanger#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}.
   *
   * <ul>
   *   <li>Then calls {@link Annotation#elementValuesAccept(Clazz, ElementValueVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitAnyAnnotationsAttribute(Clazz,
   * AnnotationsAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute); then calls elementValuesAccept(Clazz, ElementValueVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TargetClassChanger.visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)"
  })
  void testVisitAnyAnnotationsAttribute_thenCallsElementValuesAccept() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = mock(Annotation.class);
    doNothing()
        .when(annotation)
        .elementValuesAccept(Mockito.<Clazz>any(), Mockito.<ElementValueVisitor>any());

    // Act
    targetClassChanger.visitAnyAnnotationsAttribute(
        clazz, new RuntimeInvisibleAnnotationsAttribute(1, 1, new Annotation[] {annotation}));

    // Assert
    verify(annotation).elementValuesAccept(isA(Clazz.class), isA(ElementValueVisitor.class));
  }

  /**
   * Test {@link TargetClassChanger#visitAnyParameterAnnotationsAttribute(Clazz, Method,
   * ParameterAnnotationsAttribute)}.
   *
   * <ul>
   *   <li>Then calls {@link Annotation#elementValuesAccept(Clazz, ElementValueVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitAnyParameterAnnotationsAttribute(Clazz,
   * Method, ParameterAnnotationsAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute); then calls elementValuesAccept(Clazz, ElementValueVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TargetClassChanger.visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)"
  })
  void testVisitAnyParameterAnnotationsAttribute_thenCallsElementValuesAccept() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    Annotation annotation = mock(Annotation.class);
    doNothing()
        .when(annotation)
        .elementValuesAccept(Mockito.<Clazz>any(), Mockito.<ElementValueVisitor>any());

    // Act
    targetClassChanger.visitAnyParameterAnnotationsAttribute(
        clazz,
        method,
        new RuntimeInvisibleParameterAnnotationsAttribute(
            1, 1, new int[] {1, 1, 3, 1}, new Annotation[][] {new Annotation[] {annotation}}));

    // Assert
    verify(annotation).elementValuesAccept(isA(Clazz.class), isA(ElementValueVisitor.class));
  }

  /**
   * Test {@link TargetClassChanger#visitAnnotationDefaultAttribute(Clazz, Method,
   * AnnotationDefaultAttribute)}.
   *
   * <ul>
   *   <li>Then calls {@link AnnotationDefaultAttribute#defaultValueAccept(Clazz,
   *       ElementValueVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitAnnotationDefaultAttribute(Clazz, Method,
   * AnnotationDefaultAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute); then calls defaultValueAccept(Clazz, ElementValueVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TargetClassChanger.visitAnnotationDefaultAttribute(Clazz, Method, AnnotationDefaultAttribute)"
  })
  void testVisitAnnotationDefaultAttribute_thenCallsDefaultValueAccept() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    AnnotationDefaultAttribute annotationDefaultAttribute = mock(AnnotationDefaultAttribute.class);
    doNothing()
        .when(annotationDefaultAttribute)
        .defaultValueAccept(Mockito.<Clazz>any(), Mockito.<ElementValueVisitor>any());

    // Act
    targetClassChanger.visitAnnotationDefaultAttribute(clazz, method, annotationDefaultAttribute);

    // Assert
    verify(annotationDefaultAttribute)
        .defaultValueAccept(isA(Clazz.class), isA(ElementValueVisitor.class));
  }

  /**
   * Test {@link TargetClassChanger#visitRecordComponentInfo(Clazz, RecordComponentInfo)}.
   *
   * <ul>
   *   <li>Then calls {@link AnnotationDefaultAttribute#accept(Clazz, RecordComponentInfo,
   *       AttributeVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitRecordComponentInfo(Clazz,
   * RecordComponentInfo)}
   */
  @Test
  @DisplayName(
      "Test visitRecordComponentInfo(Clazz, RecordComponentInfo); then calls accept(Clazz, RecordComponentInfo, AttributeVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TargetClassChanger.visitRecordComponentInfo(Clazz, RecordComponentInfo)"
  })
  void testVisitRecordComponentInfo_thenCallsAccept() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    AnnotationDefaultAttribute annotationDefaultAttribute = mock(AnnotationDefaultAttribute.class);
    doNothing()
        .when(annotationDefaultAttribute)
        .accept(
            Mockito.<Clazz>any(),
            Mockito.<RecordComponentInfo>any(),
            Mockito.<AttributeVisitor>any());

    // Act
    targetClassChanger.visitRecordComponentInfo(
        clazz, new RecordComponentInfo(1, 1, 1, new Attribute[] {annotationDefaultAttribute}));

    // Assert
    verify(annotationDefaultAttribute)
        .accept(isA(Clazz.class), isA(RecordComponentInfo.class), isA(AttributeVisitor.class));
  }

  /**
   * Test {@link TargetClassChanger#visitRecordComponentInfo(Clazz, RecordComponentInfo)}.
   *
   * <ul>
   *   <li>Then calls {@link RecordComponentInfo#attributesAccept(Clazz, AttributeVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitRecordComponentInfo(Clazz,
   * RecordComponentInfo)}
   */
  @Test
  @DisplayName(
      "Test visitRecordComponentInfo(Clazz, RecordComponentInfo); then calls attributesAccept(Clazz, AttributeVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TargetClassChanger.visitRecordComponentInfo(Clazz, RecordComponentInfo)"
  })
  void testVisitRecordComponentInfo_thenCallsAttributesAccept() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    RecordComponentInfo recordComponentInfo = mock(RecordComponentInfo.class);
    doNothing()
        .when(recordComponentInfo)
        .attributesAccept(Mockito.<Clazz>any(), Mockito.<AttributeVisitor>any());

    // Act
    targetClassChanger.visitRecordComponentInfo(clazz, recordComponentInfo);

    // Assert
    verify(recordComponentInfo).attributesAccept(isA(Clazz.class), isA(AttributeVisitor.class));
  }

  /**
   * Test {@link TargetClassChanger#visitAnnotation(Clazz, Annotation)} with {@code clazz}, {@code
   * annotation}.
   *
   * <ul>
   *   <li>Then calls {@link Annotation#elementValuesAccept(Clazz, ElementValueVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  @DisplayName(
      "Test visitAnnotation(Clazz, Annotation) with 'clazz', 'annotation'; then calls elementValuesAccept(Clazz, ElementValueVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetClassChanger.visitAnnotation(Clazz, Annotation)"})
  void testVisitAnnotationWithClazzAnnotation_thenCallsElementValuesAccept() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = mock(Annotation.class);
    doNothing()
        .when(annotation)
        .elementValuesAccept(Mockito.<Clazz>any(), Mockito.<ElementValueVisitor>any());

    // Act
    targetClassChanger.visitAnnotation(clazz, annotation);

    // Assert
    verify(annotation).elementValuesAccept(isA(Clazz.class), isA(ElementValueVisitor.class));
  }

  /**
   * Test {@link TargetClassChanger#visitAnnotationElementValue(Clazz, Annotation,
   * AnnotationElementValue)}.
   *
   * <ul>
   *   <li>Then calls {@link AnnotationElementValue#annotationAccept(Clazz, AnnotationVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitAnnotationElementValue(Clazz, Annotation,
   * AnnotationElementValue)}
   */
  @Test
  @DisplayName(
      "Test visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue); then calls annotationAccept(Clazz, AnnotationVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TargetClassChanger.visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)"
  })
  void testVisitAnnotationElementValue_thenCallsAnnotationAccept() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    AnnotationElementValue annotationElementValue = mock(AnnotationElementValue.class);
    doNothing()
        .when(annotationElementValue)
        .annotationAccept(Mockito.<Clazz>any(), Mockito.<AnnotationVisitor>any());

    // Act
    targetClassChanger.visitAnnotationElementValue(clazz, annotation, annotationElementValue);

    // Assert
    verify(annotationElementValue).annotationAccept(isA(Clazz.class), isA(AnnotationVisitor.class));
  }

  /**
   * Test {@link TargetClassChanger#visitArrayElementValue(Clazz, Annotation, ArrayElementValue)}.
   *
   * <ul>
   *   <li>Then calls {@link ArrayElementValue#elementValuesAccept(Clazz, Annotation,
   *       ElementValueVisitor)}.
   * </ul>
   *
   * <p>Method under test: {@link TargetClassChanger#visitArrayElementValue(Clazz, Annotation,
   * ArrayElementValue)}
   */
  @Test
  @DisplayName(
      "Test visitArrayElementValue(Clazz, Annotation, ArrayElementValue); then calls elementValuesAccept(Clazz, Annotation, ElementValueVisitor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TargetClassChanger.visitArrayElementValue(Clazz, Annotation, ArrayElementValue)"
  })
  void testVisitArrayElementValue_thenCallsElementValuesAccept() {
    // Arrange
    TargetClassChanger targetClassChanger = new TargetClassChanger();
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    ArrayElementValue arrayElementValue = mock(ArrayElementValue.class);
    doNothing()
        .when(arrayElementValue)
        .elementValuesAccept(
            Mockito.<Clazz>any(), Mockito.<Annotation>any(), Mockito.<ElementValueVisitor>any());

    // Act
    targetClassChanger.visitArrayElementValue(clazz, annotation, arrayElementValue);

    // Assert
    verify(arrayElementValue)
        .elementValuesAccept(
            isA(Clazz.class), isA(Annotation.class), isA(ElementValueVisitor.class));
  }
}
