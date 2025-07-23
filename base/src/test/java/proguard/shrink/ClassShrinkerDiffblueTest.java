package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;
import proguard.classfile.LibraryMethod;
import proguard.classfile.Method;
import proguard.classfile.ProgramClass;
import proguard.classfile.ProgramField;
import proguard.classfile.ProgramMember;
import proguard.classfile.ProgramMethod;
import proguard.classfile.attribute.Attribute;
import proguard.classfile.attribute.BootstrapMethodInfo;
import proguard.classfile.attribute.BootstrapMethodsAttribute;
import proguard.classfile.attribute.CodeAttribute;
import proguard.classfile.attribute.EnclosingMethodAttribute;
import proguard.classfile.attribute.InnerClassesAttribute;
import proguard.classfile.attribute.InnerClassesInfo;
import proguard.classfile.attribute.LocalVariableInfo;
import proguard.classfile.attribute.LocalVariableTableAttribute;
import proguard.classfile.attribute.LocalVariableTypeInfo;
import proguard.classfile.attribute.LocalVariableTypeTableAttribute;
import proguard.classfile.attribute.RecordAttribute;
import proguard.classfile.attribute.RecordComponentInfo;
import proguard.classfile.attribute.annotation.Annotation;
import proguard.classfile.attribute.annotation.AnnotationElementValue;
import proguard.classfile.attribute.annotation.AnnotationsAttribute;
import proguard.classfile.attribute.annotation.ArrayElementValue;
import proguard.classfile.attribute.annotation.ElementValue;
import proguard.classfile.attribute.annotation.ParameterAnnotationsAttribute;
import proguard.classfile.attribute.annotation.RuntimeInvisibleAnnotationsAttribute;
import proguard.classfile.attribute.annotation.RuntimeInvisibleParameterAnnotationsAttribute;
import proguard.classfile.constant.ClassConstant;
import proguard.classfile.constant.Constant;
import proguard.testutils.cpa.NamedField;

class ClassShrinkerDiffblueTest {
  /**
   * Test {@link ClassShrinker#visitAnyClass(Clazz)}.
   *
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitAnyClass(Clazz)}
   */
  @Test
  @DisplayName(
      "Test visitAnyClass(Clazz); when LibraryClass(); then throw UnsupportedOperationException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassShrinker.visitAnyClass(Clazz)"})
  void testVisitAnyClass_whenLibraryClass_thenThrowUnsupportedOperationException() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> classShrinker.visitAnyClass(new LibraryClass()));
  }

  /**
   * Test {@link ClassShrinker#visitProgramClass(ProgramClass)}.
   *
   * <p>Method under test: {@link ClassShrinker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassShrinker.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    ProgramClass programClass =
        new ProgramClass(1, 1, new Constant[] {new ClassConstant()}, 1, 1, 1);

    // Act
    classShrinker.visitProgramClass(programClass);

    // Assert that nothing has changed
    assertEquals(0, programClass.subClassCount);
  }

  /**
   * Test {@link ClassShrinker#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Given {@link LibraryClass#LibraryClass()}.
   *   <li>Then first element is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName(
      "Test visitProgramClass(ProgramClass); given LibraryClass(); then first element is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassShrinker.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_givenLibraryClass_thenFirstElementIsNull() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());

    ProgramClass programClass =
        new ProgramClass(1, 1, new Constant[] {new ClassConstant()}, 1, 1, 1);
    programClass.addSubClass(new LibraryClass());

    // Act
    classShrinker.visitProgramClass(programClass);

    // Assert
    Clazz[] clazzArray = programClass.subClasses;
    assertNull(clazzArray[0]);
    assertEquals(0, programClass.subClassCount);
    assertEquals(1, clazzArray.length);
  }

  /**
   * Test {@link ClassShrinker#visitProgramClass(ProgramClass)}.
   *
   * <ul>
   *   <li>Then first element is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitProgramClass(ProgramClass)}
   */
  @Test
  @DisplayName("Test visitProgramClass(ProgramClass); then first element is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassShrinker.visitProgramClass(ProgramClass)"})
  void testVisitProgramClass_thenFirstElementIsNull() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new ShortestUsageMarker());

    ProgramClass programClass =
        new ProgramClass(1, 1, new Constant[] {new ClassConstant()}, 1, 1, 1);
    programClass.addSubClass(new LibraryClass());

    // Act
    classShrinker.visitProgramClass(programClass);

    // Assert
    Clazz[] clazzArray = programClass.subClasses;
    assertNull(clazzArray[0]);
    assertEquals(0, programClass.subClassCount);
    assertEquals(1, clazzArray.length);
  }

  /**
   * Test {@link ClassShrinker#visitLibraryClass(LibraryClass)}.
   *
   * <ul>
   *   <li>Given {@link ClassShrinker#ClassShrinker(SimpleUsageMarker)} with usageMarker is {@link
   *       ShortestUsageMarker} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName(
      "Test visitLibraryClass(LibraryClass); given ClassShrinker(SimpleUsageMarker) with usageMarker is ShortestUsageMarker (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassShrinker.visitLibraryClass(LibraryClass)"})
  void testVisitLibraryClass_givenClassShrinkerWithUsageMarkerIsShortestUsageMarker() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new ShortestUsageMarker());

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.addSubClass(new LibraryClass());

    // Act
    classShrinker.visitLibraryClass(libraryClass);

    // Assert
    Clazz[] clazzArray = libraryClass.subClasses;
    assertNull(clazzArray[0]);
    assertEquals(0, libraryClass.subClassCount);
    assertEquals(1, clazzArray.length);
  }

  /**
   * Test {@link ClassShrinker#visitLibraryClass(LibraryClass)}.
   *
   * <ul>
   *   <li>Then first element is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName("Test visitLibraryClass(LibraryClass); then first element is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassShrinker.visitLibraryClass(LibraryClass)"})
  void testVisitLibraryClass_thenFirstElementIsNull() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());

    LibraryClass libraryClass = new LibraryClass();
    libraryClass.addSubClass(new LibraryClass());

    // Act
    classShrinker.visitLibraryClass(libraryClass);

    // Assert
    Clazz[] clazzArray = libraryClass.subClasses;
    assertNull(clazzArray[0]);
    assertEquals(0, libraryClass.subClassCount);
    assertEquals(1, clazzArray.length);
  }

  /**
   * Test {@link ClassShrinker#visitLibraryClass(LibraryClass)}.
   *
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.
   *   <li>Then {@link LibraryClass#LibraryClass()} {@link LibraryClass#subClassCount} is zero.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitLibraryClass(LibraryClass)}
   */
  @Test
  @DisplayName(
      "Test visitLibraryClass(LibraryClass); when LibraryClass(); then LibraryClass() subClassCount is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassShrinker.visitLibraryClass(LibraryClass)"})
  void testVisitLibraryClass_whenLibraryClass_thenLibraryClassSubClassCountIsZero() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass libraryClass = new LibraryClass();

    // Act
    classShrinker.visitLibraryClass(libraryClass);

    // Assert that nothing has changed
    assertEquals(0, libraryClass.subClassCount);
  }

  /**
   * Test {@link ClassShrinker#visitProgramMember(ProgramClass, ProgramMember)}.
   *
   * <p>Method under test: {@link ClassShrinker#visitProgramMember(ProgramClass, ProgramMember)}
   */
  @Test
  @DisplayName("Test visitProgramMember(ProgramClass, ProgramMember)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassShrinker.visitProgramMember(ProgramClass, ProgramMember)"})
  void testVisitProgramMember() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    ProgramClass programClass = new ProgramClass();
    NamedField programMember = new NamedField("Field Name", "Field Descriptor");

    // Act
    classShrinker.visitProgramMember(programClass, programMember);

    // Assert that nothing has changed
    assertEquals(0, programMember.u2attributesCount);
  }

  /**
   * Test {@link ClassShrinker#visitProgramMember(ProgramClass, ProgramMember)}.
   *
   * <ul>
   *   <li>Then first element is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitProgramMember(ProgramClass, ProgramMember)}
   */
  @Test
  @DisplayName("Test visitProgramMember(ProgramClass, ProgramMember); then first element is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassShrinker.visitProgramMember(ProgramClass, ProgramMember)"})
  void testVisitProgramMember_thenFirstElementIsNull() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    ProgramClass programClass = new ProgramClass();
    ProgramField programMember =
        new ProgramField(
            1, 1, 1, 1, new Attribute[] {new BootstrapMethodsAttribute()}, new LibraryClass());

    // Act
    classShrinker.visitProgramMember(programClass, programMember);

    // Assert
    Attribute[] attributeArray = programMember.attributes;
    assertNull(attributeArray[0]);
    assertEquals(0, programMember.u2attributesCount);
    assertEquals(1, attributeArray.length);
  }

  /**
   * Test {@link ClassShrinker#visitProgramMember(ProgramClass, ProgramMember)}.
   *
   * <ul>
   *   <li>Then {@link ProgramMethod#ProgramMethod()} {@link ProgramMember#u2attributesCount} is
   *       zero.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitProgramMember(ProgramClass, ProgramMember)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMember(ProgramClass, ProgramMember); then ProgramMethod() u2attributesCount is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassShrinker.visitProgramMember(ProgramClass, ProgramMember)"})
  void testVisitProgramMember_thenProgramMethodU2attributesCountIsZero() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    ProgramClass programClass = new ProgramClass();
    ProgramMethod programMember = new ProgramMethod();

    // Act
    classShrinker.visitProgramMember(programClass, programMember);

    // Assert that nothing has changed
    assertEquals(0, programMember.u2attributesCount);
  }

  /**
   * Test {@link ClassShrinker#visitProgramMember(ProgramClass, ProgramMember)}.
   *
   * <ul>
   *   <li>When {@link ProgramField#ProgramField()}.
   *   <li>Then {@link ProgramField#ProgramField()} {@link ProgramMember#u2attributesCount} is zero.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitProgramMember(ProgramClass, ProgramMember)}
   */
  @Test
  @DisplayName(
      "Test visitProgramMember(ProgramClass, ProgramMember); when ProgramField(); then ProgramField() u2attributesCount is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassShrinker.visitProgramMember(ProgramClass, ProgramMember)"})
  void testVisitProgramMember_whenProgramField_thenProgramFieldU2attributesCountIsZero() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    ProgramClass programClass = new ProgramClass();
    ProgramField programMember = new ProgramField();

    // Act
    classShrinker.visitProgramMember(programClass, programMember);

    // Assert that nothing has changed
    assertEquals(0, programMember.u2attributesCount);
  }

  /**
   * Test {@link ClassShrinker#visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute)}.
   *
   * <p>Method under test: {@link ClassShrinker#visitBootstrapMethodsAttribute(Clazz,
   * BootstrapMethodsAttribute)}
   */
  @Test
  @DisplayName("Test visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassShrinker.visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute)"
  })
  void testVisitBootstrapMethodsAttribute() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    BootstrapMethodInfo bootstrapMethodInfo = new BootstrapMethodInfo();
    BootstrapMethodsAttribute bootstrapMethodsAttribute =
        new BootstrapMethodsAttribute(1, 0, new BootstrapMethodInfo[] {bootstrapMethodInfo});

    // Act
    classShrinker.visitBootstrapMethodsAttribute(clazz, bootstrapMethodsAttribute);

    // Assert that nothing has changed
    assertEquals(0, bootstrapMethodsAttribute.u2bootstrapMethodsCount);
    BootstrapMethodInfo[] bootstrapMethodInfoArray = bootstrapMethodsAttribute.bootstrapMethods;
    assertEquals(1, bootstrapMethodInfoArray.length);
    assertSame(bootstrapMethodInfo, bootstrapMethodInfoArray[0]);
  }

  /**
   * Test {@link ClassShrinker#visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute)}.
   *
   * <p>Method under test: {@link ClassShrinker#visitBootstrapMethodsAttribute(Clazz,
   * BootstrapMethodsAttribute)}
   */
  @Test
  @DisplayName("Test visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassShrinker.visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute)"
  })
  void testVisitBootstrapMethodsAttribute2() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    BootstrapMethodsAttribute bootstrapMethodsAttribute =
        new BootstrapMethodsAttribute(1, 1, new BootstrapMethodInfo[] {new BootstrapMethodInfo()});

    // Act
    classShrinker.visitBootstrapMethodsAttribute(clazz, bootstrapMethodsAttribute);

    // Assert
    BootstrapMethodInfo[] bootstrapMethodInfoArray = bootstrapMethodsAttribute.bootstrapMethods;
    assertNull(bootstrapMethodInfoArray[0]);
    assertEquals(0, bootstrapMethodsAttribute.u2bootstrapMethodsCount);
    assertEquals(1, bootstrapMethodInfoArray.length);
  }

  /**
   * Test {@link ClassShrinker#visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute)}.
   *
   * <ul>
   *   <li>Then first element is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitBootstrapMethodsAttribute(Clazz,
   * BootstrapMethodsAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute); then first element is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassShrinker.visitBootstrapMethodsAttribute(Clazz, BootstrapMethodsAttribute)"
  })
  void testVisitBootstrapMethodsAttribute_thenFirstElementIsNull() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    BootstrapMethodsAttribute bootstrapMethodsAttribute =
        new BootstrapMethodsAttribute(1, 1, new BootstrapMethodInfo[] {new BootstrapMethodInfo()});

    // Act
    classShrinker.visitBootstrapMethodsAttribute(clazz, bootstrapMethodsAttribute);

    // Assert
    BootstrapMethodInfo[] bootstrapMethodInfoArray = bootstrapMethodsAttribute.bootstrapMethods;
    assertNull(bootstrapMethodInfoArray[0]);
    assertEquals(0, bootstrapMethodsAttribute.u2bootstrapMethodsCount);
    assertEquals(1, bootstrapMethodInfoArray.length);
  }

  /**
   * Test {@link ClassShrinker#visitRecordAttribute(Clazz, RecordAttribute)}.
   *
   * <ul>
   *   <li>Given {@link ClassShrinker#ClassShrinker(SimpleUsageMarker)} with usageMarker is {@link
   *       ShortestUsageMarker} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitRecordAttribute(Clazz, RecordAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitRecordAttribute(Clazz, RecordAttribute); given ClassShrinker(SimpleUsageMarker) with usageMarker is ShortestUsageMarker (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassShrinker.visitRecordAttribute(Clazz, RecordAttribute)"})
  void testVisitRecordAttribute_givenClassShrinkerWithUsageMarkerIsShortestUsageMarker() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    RecordAttribute recordAttribute =
        new RecordAttribute(1, 1, new RecordComponentInfo[] {new RecordComponentInfo()});

    // Act
    classShrinker.visitRecordAttribute(clazz, recordAttribute);

    // Assert
    RecordComponentInfo[] recordComponentInfoArray = recordAttribute.components;
    assertNull(recordComponentInfoArray[0]);
    assertEquals(0, recordAttribute.u2componentsCount);
    assertEquals(1, recordComponentInfoArray.length);
  }

  /**
   * Test {@link ClassShrinker#visitRecordAttribute(Clazz, RecordAttribute)}.
   *
   * <ul>
   *   <li>Then first element is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitRecordAttribute(Clazz, RecordAttribute)}
   */
  @Test
  @DisplayName("Test visitRecordAttribute(Clazz, RecordAttribute); then first element is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassShrinker.visitRecordAttribute(Clazz, RecordAttribute)"})
  void testVisitRecordAttribute_thenFirstElementIsNull() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    RecordAttribute recordAttribute =
        new RecordAttribute(1, 1, new RecordComponentInfo[] {new RecordComponentInfo()});

    // Act
    classShrinker.visitRecordAttribute(clazz, recordAttribute);

    // Assert
    RecordComponentInfo[] recordComponentInfoArray = recordAttribute.components;
    assertNull(recordComponentInfoArray[0]);
    assertEquals(0, recordAttribute.u2componentsCount);
    assertEquals(1, recordComponentInfoArray.length);
  }

  /**
   * Test {@link ClassShrinker#visitRecordAttribute(Clazz, RecordAttribute)}.
   *
   * <ul>
   *   <li>Then {@link RecordAttribute#RecordAttribute()} {@link RecordAttribute#u2componentsCount}
   *       is zero.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitRecordAttribute(Clazz, RecordAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitRecordAttribute(Clazz, RecordAttribute); then RecordAttribute() u2componentsCount is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassShrinker.visitRecordAttribute(Clazz, RecordAttribute)"})
  void testVisitRecordAttribute_thenRecordAttributeU2componentsCountIsZero() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    RecordAttribute recordAttribute = new RecordAttribute();

    // Act
    classShrinker.visitRecordAttribute(clazz, recordAttribute);

    // Assert that nothing has changed
    assertEquals(0, recordAttribute.u2componentsCount);
  }

  /**
   * Test {@link ClassShrinker#visitInnerClassesAttribute(Clazz, InnerClassesAttribute)}.
   *
   * <p>Method under test: {@link ClassShrinker#visitInnerClassesAttribute(Clazz,
   * InnerClassesAttribute)}
   */
  @Test
  @DisplayName("Test visitInnerClassesAttribute(Clazz, InnerClassesAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassShrinker.visitInnerClassesAttribute(Clazz, InnerClassesAttribute)"})
  void testVisitInnerClassesAttribute() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    InnerClassesAttribute innerClassesAttribute =
        new InnerClassesAttribute(1, 1, new InnerClassesInfo[] {new InnerClassesInfo(1, 1, 1, 1)});

    // Act
    classShrinker.visitInnerClassesAttribute(clazz, innerClassesAttribute);

    // Assert
    InnerClassesInfo[] innerClassesInfoArray = innerClassesAttribute.classes;
    assertNull(innerClassesInfoArray[0]);
    assertEquals(0, innerClassesAttribute.u2classesCount);
    assertEquals(1, innerClassesInfoArray.length);
  }

  /**
   * Test {@link ClassShrinker#visitInnerClassesAttribute(Clazz, InnerClassesAttribute)}.
   *
   * <ul>
   *   <li>Then first element is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitInnerClassesAttribute(Clazz,
   * InnerClassesAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitInnerClassesAttribute(Clazz, InnerClassesAttribute); then first element is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassShrinker.visitInnerClassesAttribute(Clazz, InnerClassesAttribute)"})
  void testVisitInnerClassesAttribute_thenFirstElementIsNull() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    InnerClassesAttribute innerClassesAttribute =
        new InnerClassesAttribute(1, 1, new InnerClassesInfo[] {new InnerClassesInfo(1, 1, 1, 1)});

    // Act
    classShrinker.visitInnerClassesAttribute(clazz, innerClassesAttribute);

    // Assert
    InnerClassesInfo[] innerClassesInfoArray = innerClassesAttribute.classes;
    assertNull(innerClassesInfoArray[0]);
    assertEquals(0, innerClassesAttribute.u2classesCount);
    assertEquals(1, innerClassesInfoArray.length);
  }

  /**
   * Test {@link ClassShrinker#visitInnerClassesAttribute(Clazz, InnerClassesAttribute)}.
   *
   * <ul>
   *   <li>Then {@link InnerClassesAttribute#InnerClassesAttribute()} {@link
   *       InnerClassesAttribute#u2classesCount} is zero.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitInnerClassesAttribute(Clazz,
   * InnerClassesAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitInnerClassesAttribute(Clazz, InnerClassesAttribute); then InnerClassesAttribute() u2classesCount is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassShrinker.visitInnerClassesAttribute(Clazz, InnerClassesAttribute)"})
  void testVisitInnerClassesAttribute_thenInnerClassesAttributeU2classesCountIsZero() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    InnerClassesAttribute innerClassesAttribute = new InnerClassesAttribute();

    // Act
    classShrinker.visitInnerClassesAttribute(clazz, innerClassesAttribute);

    // Assert that nothing has changed
    assertEquals(0, innerClassesAttribute.u2classesCount);
  }

  /**
   * Test {@link ClassShrinker#visitEnclosingMethodAttribute(Clazz, EnclosingMethodAttribute)}.
   *
   * <p>Method under test: {@link ClassShrinker#visitEnclosingMethodAttribute(Clazz,
   * EnclosingMethodAttribute)}
   */
  @Test
  @DisplayName("Test visitEnclosingMethodAttribute(Clazz, EnclosingMethodAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassShrinker.visitEnclosingMethodAttribute(Clazz, EnclosingMethodAttribute)"
  })
  void testVisitEnclosingMethodAttribute() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    EnclosingMethodAttribute enclosingMethodAttribute = new EnclosingMethodAttribute(1, 1, 1);

    // Act
    classShrinker.visitEnclosingMethodAttribute(clazz, enclosingMethodAttribute);

    // Assert that nothing has changed
    assertEquals(1, enclosingMethodAttribute.u2nameAndTypeIndex);
  }

  /**
   * Test {@link ClassShrinker#visitEnclosingMethodAttribute(Clazz, EnclosingMethodAttribute)}.
   *
   * <p>Method under test: {@link ClassShrinker#visitEnclosingMethodAttribute(Clazz,
   * EnclosingMethodAttribute)}
   */
  @Test
  @DisplayName("Test visitEnclosingMethodAttribute(Clazz, EnclosingMethodAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassShrinker.visitEnclosingMethodAttribute(Clazz, EnclosingMethodAttribute)"
  })
  void testVisitEnclosingMethodAttribute2() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    EnclosingMethodAttribute enclosingMethodAttribute = new EnclosingMethodAttribute(1, 1, 1);

    enclosingMethodAttribute.referencedMethod = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    classShrinker.visitEnclosingMethodAttribute(clazz, enclosingMethodAttribute);

    // Assert
    assertNull(enclosingMethodAttribute.referencedMethod);
    assertEquals(0, enclosingMethodAttribute.u2nameAndTypeIndex);
  }

  /**
   * Test {@link ClassShrinker#visitEnclosingMethodAttribute(Clazz, EnclosingMethodAttribute)}.
   *
   * <p>Method under test: {@link ClassShrinker#visitEnclosingMethodAttribute(Clazz,
   * EnclosingMethodAttribute)}
   */
  @Test
  @DisplayName("Test visitEnclosingMethodAttribute(Clazz, EnclosingMethodAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassShrinker.visitEnclosingMethodAttribute(Clazz, EnclosingMethodAttribute)"
  })
  void testVisitEnclosingMethodAttribute3() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    EnclosingMethodAttribute enclosingMethodAttribute = new EnclosingMethodAttribute(1, 1, 1);

    enclosingMethodAttribute.referencedMethod = new LibraryMethod(1, "Name", "Descriptor");

    // Act
    classShrinker.visitEnclosingMethodAttribute(clazz, enclosingMethodAttribute);

    // Assert
    assertNull(enclosingMethodAttribute.referencedMethod);
    assertEquals(0, enclosingMethodAttribute.u2nameAndTypeIndex);
  }

  /**
   * Test {@link ClassShrinker#visitLocalVariableTableAttribute(Clazz, Method, CodeAttribute,
   * LocalVariableTableAttribute)}.
   *
   * <p>Method under test: {@link ClassShrinker#visitLocalVariableTableAttribute(Clazz, Method,
   * CodeAttribute, LocalVariableTableAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitLocalVariableTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTableAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassShrinker.visitLocalVariableTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTableAttribute)"
  })
  void testVisitLocalVariableTableAttribute() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LocalVariableTableAttribute localVariableTableAttribute = new LocalVariableTableAttribute();

    // Act
    classShrinker.visitLocalVariableTableAttribute(
        clazz, method, codeAttribute, localVariableTableAttribute);

    // Assert that nothing has changed
    assertEquals(0, localVariableTableAttribute.u2localVariableTableLength);
  }

  /**
   * Test {@link ClassShrinker#visitLocalVariableTableAttribute(Clazz, Method, CodeAttribute,
   * LocalVariableTableAttribute)}.
   *
   * <p>Method under test: {@link ClassShrinker#visitLocalVariableTableAttribute(Clazz, Method,
   * CodeAttribute, LocalVariableTableAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitLocalVariableTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTableAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassShrinker.visitLocalVariableTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTableAttribute)"
  })
  void testVisitLocalVariableTableAttribute2() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LocalVariableTableAttribute localVariableTableAttribute =
        new LocalVariableTableAttribute(
            1, 1, new LocalVariableInfo[] {new LocalVariableInfo(1, 3, 1, 1, 1)});

    // Act
    classShrinker.visitLocalVariableTableAttribute(
        clazz, method, codeAttribute, localVariableTableAttribute);

    // Assert
    LocalVariableInfo[] localVariableInfoArray = localVariableTableAttribute.localVariableTable;
    assertNull(localVariableInfoArray[0]);
    assertEquals(0, localVariableTableAttribute.u2localVariableTableLength);
    assertEquals(1, localVariableInfoArray.length);
  }

  /**
   * Test {@link ClassShrinker#visitLocalVariableTableAttribute(Clazz, Method, CodeAttribute,
   * LocalVariableTableAttribute)}.
   *
   * <ul>
   *   <li>Then first element is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitLocalVariableTableAttribute(Clazz, Method,
   * CodeAttribute, LocalVariableTableAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitLocalVariableTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTableAttribute); then first element is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassShrinker.visitLocalVariableTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTableAttribute)"
  })
  void testVisitLocalVariableTableAttribute_thenFirstElementIsNull() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LocalVariableTableAttribute localVariableTableAttribute =
        new LocalVariableTableAttribute(
            1, 1, new LocalVariableInfo[] {new LocalVariableInfo(1, 3, 1, 1, 1)});

    // Act
    classShrinker.visitLocalVariableTableAttribute(
        clazz, method, codeAttribute, localVariableTableAttribute);

    // Assert
    LocalVariableInfo[] localVariableInfoArray = localVariableTableAttribute.localVariableTable;
    assertNull(localVariableInfoArray[0]);
    assertEquals(0, localVariableTableAttribute.u2localVariableTableLength);
    assertEquals(1, localVariableInfoArray.length);
  }

  /**
   * Test {@link ClassShrinker#visitLocalVariableTypeTableAttribute(Clazz, Method, CodeAttribute,
   * LocalVariableTypeTableAttribute)}.
   *
   * <p>Method under test: {@link ClassShrinker#visitLocalVariableTypeTableAttribute(Clazz, Method,
   * CodeAttribute, LocalVariableTypeTableAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitLocalVariableTypeTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTypeTableAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassShrinker.visitLocalVariableTypeTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTypeTableAttribute)"
  })
  void testVisitLocalVariableTypeTableAttribute() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LocalVariableTypeTableAttribute localVariableTypeTableAttribute =
        new LocalVariableTypeTableAttribute();

    // Act
    classShrinker.visitLocalVariableTypeTableAttribute(
        clazz, method, codeAttribute, localVariableTypeTableAttribute);

    // Assert that nothing has changed
    assertEquals(0, localVariableTypeTableAttribute.u2localVariableTypeTableLength);
  }

  /**
   * Test {@link ClassShrinker#visitLocalVariableTypeTableAttribute(Clazz, Method, CodeAttribute,
   * LocalVariableTypeTableAttribute)}.
   *
   * <p>Method under test: {@link ClassShrinker#visitLocalVariableTypeTableAttribute(Clazz, Method,
   * CodeAttribute, LocalVariableTypeTableAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitLocalVariableTypeTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTypeTableAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassShrinker.visitLocalVariableTypeTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTypeTableAttribute)"
  })
  void testVisitLocalVariableTypeTableAttribute2() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LocalVariableTypeTableAttribute localVariableTypeTableAttribute =
        new LocalVariableTypeTableAttribute(
            1, 1, new LocalVariableTypeInfo[] {new LocalVariableTypeInfo(1, 3, 1, 1, 1)});

    // Act
    classShrinker.visitLocalVariableTypeTableAttribute(
        clazz, method, codeAttribute, localVariableTypeTableAttribute);

    // Assert
    LocalVariableTypeInfo[] localVariableTypeInfoArray =
        localVariableTypeTableAttribute.localVariableTypeTable;
    assertNull(localVariableTypeInfoArray[0]);
    assertEquals(0, localVariableTypeTableAttribute.u2localVariableTypeTableLength);
    assertEquals(1, localVariableTypeInfoArray.length);
  }

  /**
   * Test {@link ClassShrinker#visitLocalVariableTypeTableAttribute(Clazz, Method, CodeAttribute,
   * LocalVariableTypeTableAttribute)}.
   *
   * <ul>
   *   <li>Then first element is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitLocalVariableTypeTableAttribute(Clazz, Method,
   * CodeAttribute, LocalVariableTypeTableAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitLocalVariableTypeTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTypeTableAttribute); then first element is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassShrinker.visitLocalVariableTypeTableAttribute(Clazz, Method, CodeAttribute, LocalVariableTypeTableAttribute)"
  })
  void testVisitLocalVariableTypeTableAttribute_thenFirstElementIsNull() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    CodeAttribute codeAttribute = new CodeAttribute(1);
    LocalVariableTypeTableAttribute localVariableTypeTableAttribute =
        new LocalVariableTypeTableAttribute(
            1, 1, new LocalVariableTypeInfo[] {new LocalVariableTypeInfo(1, 3, 1, 1, 1)});

    // Act
    classShrinker.visitLocalVariableTypeTableAttribute(
        clazz, method, codeAttribute, localVariableTypeTableAttribute);

    // Assert
    LocalVariableTypeInfo[] localVariableTypeInfoArray =
        localVariableTypeTableAttribute.localVariableTypeTable;
    assertNull(localVariableTypeInfoArray[0]);
    assertEquals(0, localVariableTypeTableAttribute.u2localVariableTypeTableLength);
    assertEquals(1, localVariableTypeInfoArray.length);
  }

  /**
   * Test {@link ClassShrinker#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}.
   *
   * <p>Method under test: {@link ClassShrinker#visitAnyAnnotationsAttribute(Clazz,
   * AnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassShrinker.visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)"
  })
  void testVisitAnyAnnotationsAttribute() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    RuntimeInvisibleAnnotationsAttribute annotationsAttribute =
        new RuntimeInvisibleAnnotationsAttribute();

    // Act
    classShrinker.visitAnyAnnotationsAttribute(clazz, annotationsAttribute);

    // Assert that nothing has changed
    assertEquals(0, annotationsAttribute.u2annotationsCount);
  }

  /**
   * Test {@link ClassShrinker#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}.
   *
   * <p>Method under test: {@link ClassShrinker#visitAnyAnnotationsAttribute(Clazz,
   * AnnotationsAttribute)}
   */
  @Test
  @DisplayName("Test visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassShrinker.visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)"
  })
  void testVisitAnyAnnotationsAttribute2() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    RuntimeInvisibleAnnotationsAttribute annotationsAttribute =
        new RuntimeInvisibleAnnotationsAttribute(1, 1, new Annotation[] {new Annotation()});

    // Act
    classShrinker.visitAnyAnnotationsAttribute(clazz, annotationsAttribute);

    // Assert
    Annotation[] annotationArray = annotationsAttribute.annotations;
    assertNull(annotationArray[0]);
    assertEquals(0, annotationsAttribute.u2annotationsCount);
    assertEquals(1, annotationArray.length);
  }

  /**
   * Test {@link ClassShrinker#visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)}.
   *
   * <ul>
   *   <li>Then first element is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitAnyAnnotationsAttribute(Clazz,
   * AnnotationsAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute); then first element is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassShrinker.visitAnyAnnotationsAttribute(Clazz, AnnotationsAttribute)"
  })
  void testVisitAnyAnnotationsAttribute_thenFirstElementIsNull() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    RuntimeInvisibleAnnotationsAttribute annotationsAttribute =
        new RuntimeInvisibleAnnotationsAttribute(1, 1, new Annotation[] {new Annotation()});

    // Act
    classShrinker.visitAnyAnnotationsAttribute(clazz, annotationsAttribute);

    // Assert
    Annotation[] annotationArray = annotationsAttribute.annotations;
    assertNull(annotationArray[0]);
    assertEquals(0, annotationsAttribute.u2annotationsCount);
    assertEquals(1, annotationArray.length);
  }

  /**
   * Test {@link ClassShrinker#visitAnyParameterAnnotationsAttribute(Clazz, Method,
   * ParameterAnnotationsAttribute)}.
   *
   * <p>Method under test: {@link ClassShrinker#visitAnyParameterAnnotationsAttribute(Clazz, Method,
   * ParameterAnnotationsAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassShrinker.visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)"
  })
  void testVisitAnyParameterAnnotationsAttribute() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    RuntimeInvisibleParameterAnnotationsAttribute parameterAnnotationsAttribute =
        new RuntimeInvisibleParameterAnnotationsAttribute();

    // Act
    classShrinker.visitAnyParameterAnnotationsAttribute(
        clazz, method, parameterAnnotationsAttribute);

    // Assert that nothing has changed
    assertNull(parameterAnnotationsAttribute.u2parameterAnnotationsCount);
  }

  /**
   * Test {@link ClassShrinker#visitAnyParameterAnnotationsAttribute(Clazz, Method,
   * ParameterAnnotationsAttribute)}.
   *
   * <ul>
   *   <li>Then first element is {@link Annotation#Annotation()}.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitAnyParameterAnnotationsAttribute(Clazz, Method,
   * ParameterAnnotationsAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute); then first element is Annotation()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassShrinker.visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)"
  })
  void testVisitAnyParameterAnnotationsAttribute_thenFirstElementIsAnnotation() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    Annotation annotation = new Annotation();
    RuntimeInvisibleParameterAnnotationsAttribute parameterAnnotationsAttribute =
        new RuntimeInvisibleParameterAnnotationsAttribute(
            1, 1, new int[] {0, 1, 3, 1}, new Annotation[][] {new Annotation[] {annotation}});

    // Act
    classShrinker.visitAnyParameterAnnotationsAttribute(
        clazz, method, parameterAnnotationsAttribute);

    // Assert that nothing has changed
    Annotation[][] annotationArray = parameterAnnotationsAttribute.parameterAnnotations;
    Annotation[] annotationArray2 = annotationArray[0];
    assertEquals(1, annotationArray2.length);
    assertEquals(1, annotationArray.length);
    assertSame(annotation, annotationArray2[0]);
    assertArrayEquals(
        new int[] {0, 1, 3, 1}, parameterAnnotationsAttribute.u2parameterAnnotationsCount);
  }

  /**
   * Test {@link ClassShrinker#visitAnyParameterAnnotationsAttribute(Clazz, Method,
   * ParameterAnnotationsAttribute)}.
   *
   * <ul>
   *   <li>Then first element is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitAnyParameterAnnotationsAttribute(Clazz, Method,
   * ParameterAnnotationsAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute); then first element is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassShrinker.visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)"
  })
  void testVisitAnyParameterAnnotationsAttribute_thenFirstElementIsNull() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    RuntimeInvisibleParameterAnnotationsAttribute parameterAnnotationsAttribute =
        new RuntimeInvisibleParameterAnnotationsAttribute(
            1, 1, new int[] {1, 1, 3, 1}, new Annotation[][] {new Annotation[] {new Annotation()}});

    // Act
    classShrinker.visitAnyParameterAnnotationsAttribute(
        clazz, method, parameterAnnotationsAttribute);

    // Assert
    Annotation[][] annotationArray = parameterAnnotationsAttribute.parameterAnnotations;
    Annotation[] annotationArray2 = annotationArray[0];
    assertNull(annotationArray2[0]);
    assertEquals(1, annotationArray2.length);
    assertEquals(1, annotationArray.length);
    assertArrayEquals(
        new int[] {0, 1, 3, 1}, parameterAnnotationsAttribute.u2parameterAnnotationsCount);
  }

  /**
   * Test {@link ClassShrinker#visitAnyParameterAnnotationsAttribute(Clazz, Method,
   * ParameterAnnotationsAttribute)}.
   *
   * <ul>
   *   <li>Then first element is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitAnyParameterAnnotationsAttribute(Clazz, Method,
   * ParameterAnnotationsAttribute)}
   */
  @Test
  @DisplayName(
      "Test visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute); then first element is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassShrinker.visitAnyParameterAnnotationsAttribute(Clazz, Method, ParameterAnnotationsAttribute)"
  })
  void testVisitAnyParameterAnnotationsAttribute_thenFirstElementIsNull2() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    LibraryMethod method = new LibraryMethod(1, "Name", "Descriptor");

    RuntimeInvisibleParameterAnnotationsAttribute parameterAnnotationsAttribute =
        new RuntimeInvisibleParameterAnnotationsAttribute(
            1, 1, new int[] {1, 1, 3, 1}, new Annotation[][] {new Annotation[] {new Annotation()}});

    // Act
    classShrinker.visitAnyParameterAnnotationsAttribute(
        clazz, method, parameterAnnotationsAttribute);

    // Assert
    Annotation[][] annotationArray = parameterAnnotationsAttribute.parameterAnnotations;
    Annotation[] annotationArray2 = annotationArray[0];
    assertNull(annotationArray2[0]);
    assertEquals(1, annotationArray2.length);
    assertEquals(1, annotationArray.length);
    assertArrayEquals(
        new int[] {0, 1, 3, 1}, parameterAnnotationsAttribute.u2parameterAnnotationsCount);
  }

  /**
   * Test {@link ClassShrinker#visitRecordComponentInfo(Clazz, RecordComponentInfo)}.
   *
   * <p>Method under test: {@link ClassShrinker#visitRecordComponentInfo(Clazz,
   * RecordComponentInfo)}
   */
  @Test
  @DisplayName("Test visitRecordComponentInfo(Clazz, RecordComponentInfo)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassShrinker.visitRecordComponentInfo(Clazz, RecordComponentInfo)"})
  void testVisitRecordComponentInfo() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    RecordComponentInfo recordComponentInfo =
        new RecordComponentInfo(1, 1, 1, new Attribute[] {new BootstrapMethodsAttribute()});

    // Act
    classShrinker.visitRecordComponentInfo(clazz, recordComponentInfo);

    // Assert
    Attribute[] attributeArray = recordComponentInfo.attributes;
    assertNull(attributeArray[0]);
    assertEquals(0, recordComponentInfo.u2attributesCount);
    assertEquals(1, attributeArray.length);
  }

  /**
   * Test {@link ClassShrinker#visitRecordComponentInfo(Clazz, RecordComponentInfo)}.
   *
   * <ul>
   *   <li>Then first element is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitRecordComponentInfo(Clazz,
   * RecordComponentInfo)}
   */
  @Test
  @DisplayName(
      "Test visitRecordComponentInfo(Clazz, RecordComponentInfo); then first element is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassShrinker.visitRecordComponentInfo(Clazz, RecordComponentInfo)"})
  void testVisitRecordComponentInfo_thenFirstElementIsNull() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    RecordComponentInfo recordComponentInfo =
        new RecordComponentInfo(1, 1, 1, new Attribute[] {new BootstrapMethodsAttribute()});

    // Act
    classShrinker.visitRecordComponentInfo(clazz, recordComponentInfo);

    // Assert
    Attribute[] attributeArray = recordComponentInfo.attributes;
    assertNull(attributeArray[0]);
    assertEquals(0, recordComponentInfo.u2attributesCount);
    assertEquals(1, attributeArray.length);
  }

  /**
   * Test {@link ClassShrinker#visitRecordComponentInfo(Clazz, RecordComponentInfo)}.
   *
   * <ul>
   *   <li>Then {@link RecordComponentInfo#RecordComponentInfo()} {@link
   *       RecordComponentInfo#u2attributesCount} is zero.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitRecordComponentInfo(Clazz,
   * RecordComponentInfo)}
   */
  @Test
  @DisplayName(
      "Test visitRecordComponentInfo(Clazz, RecordComponentInfo); then RecordComponentInfo() u2attributesCount is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassShrinker.visitRecordComponentInfo(Clazz, RecordComponentInfo)"})
  void testVisitRecordComponentInfo_thenRecordComponentInfoU2attributesCountIsZero() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    RecordComponentInfo recordComponentInfo = new RecordComponentInfo();

    // Act
    classShrinker.visitRecordComponentInfo(clazz, recordComponentInfo);

    // Assert that nothing has changed
    assertEquals(0, recordComponentInfo.u2attributesCount);
  }

  /**
   * Test {@link ClassShrinker#visitAnnotation(Clazz, Annotation)} with {@code clazz}, {@code
   * annotation}.
   *
   * <p>Method under test: {@link ClassShrinker#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  @DisplayName("Test visitAnnotation(Clazz, Annotation) with 'clazz', 'annotation'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassShrinker.visitAnnotation(Clazz, Annotation)"})
  void testVisitAnnotationWithClazzAnnotation() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation(1, 1, new ElementValue[] {new AnnotationElementValue()});

    // Act
    classShrinker.visitAnnotation(clazz, annotation);

    // Assert
    ElementValue[] elementValueArray = annotation.elementValues;
    assertNull(elementValueArray[0]);
    assertEquals(0, annotation.u2elementValuesCount);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link ClassShrinker#visitAnnotation(Clazz, Annotation)} with {@code clazz}, {@code
   * annotation}.
   *
   * <ul>
   *   <li>Then {@link Annotation#Annotation()} {@link Annotation#u2elementValuesCount} is zero.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  @DisplayName(
      "Test visitAnnotation(Clazz, Annotation) with 'clazz', 'annotation'; then Annotation() u2elementValuesCount is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassShrinker.visitAnnotation(Clazz, Annotation)"})
  void testVisitAnnotationWithClazzAnnotation_thenAnnotationU2elementValuesCountIsZero() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();

    // Act
    classShrinker.visitAnnotation(clazz, annotation);

    // Assert that nothing has changed
    assertEquals(0, annotation.u2elementValuesCount);
  }

  /**
   * Test {@link ClassShrinker#visitAnnotation(Clazz, Annotation)} with {@code clazz}, {@code
   * annotation}.
   *
   * <ul>
   *   <li>Then first element is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitAnnotation(Clazz, Annotation)}
   */
  @Test
  @DisplayName(
      "Test visitAnnotation(Clazz, Annotation) with 'clazz', 'annotation'; then first element is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ClassShrinker.visitAnnotation(Clazz, Annotation)"})
  void testVisitAnnotationWithClazzAnnotation_thenFirstElementIsNull() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation(1, 1, new ElementValue[] {new AnnotationElementValue()});

    // Act
    classShrinker.visitAnnotation(clazz, annotation);

    // Assert
    ElementValue[] elementValueArray = annotation.elementValues;
    assertNull(elementValueArray[0]);
    assertEquals(0, annotation.u2elementValuesCount);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link ClassShrinker#visitAnnotationElementValue(Clazz, Annotation,
   * AnnotationElementValue)}.
   *
   * <p>Method under test: {@link ClassShrinker#visitAnnotationElementValue(Clazz, Annotation,
   * AnnotationElementValue)}
   */
  @Test
  @DisplayName("Test visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassShrinker.visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)"
  })
  void testVisitAnnotationElementValue() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    AnnotationElementValue annotationElementValue = new AnnotationElementValue(1, new Annotation());

    // Act
    classShrinker.visitAnnotationElementValue(clazz, annotation, annotationElementValue);

    // Assert that nothing has changed
    assertEquals(0, annotationElementValue.annotationValue.u2elementValuesCount);
  }

  /**
   * Test {@link ClassShrinker#visitAnnotationElementValue(Clazz, Annotation,
   * AnnotationElementValue)}.
   *
   * <ul>
   *   <li>Then first element is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitAnnotationElementValue(Clazz, Annotation,
   * AnnotationElementValue)}
   */
  @Test
  @DisplayName(
      "Test visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue); then first element is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassShrinker.visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)"
  })
  void testVisitAnnotationElementValue_thenFirstElementIsNull() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    AnnotationElementValue annotationElementValue =
        new AnnotationElementValue(
            1, new Annotation(1, 1, new ElementValue[] {new AnnotationElementValue()}));

    // Act
    classShrinker.visitAnnotationElementValue(clazz, annotation, annotationElementValue);

    // Assert
    Annotation annotation2 = annotationElementValue.annotationValue;
    ElementValue[] elementValueArray = annotation2.elementValues;
    assertNull(elementValueArray[0]);
    assertEquals(0, annotation2.u2elementValuesCount);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link ClassShrinker#visitAnnotationElementValue(Clazz, Annotation,
   * AnnotationElementValue)}.
   *
   * <ul>
   *   <li>Then first element is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitAnnotationElementValue(Clazz, Annotation,
   * AnnotationElementValue)}
   */
  @Test
  @DisplayName(
      "Test visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue); then first element is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassShrinker.visitAnnotationElementValue(Clazz, Annotation, AnnotationElementValue)"
  })
  void testVisitAnnotationElementValue_thenFirstElementIsNull2() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    AnnotationElementValue annotationElementValue =
        new AnnotationElementValue(
            1, new Annotation(1, 1, new ElementValue[] {new AnnotationElementValue()}));

    // Act
    classShrinker.visitAnnotationElementValue(clazz, annotation, annotationElementValue);

    // Assert
    Annotation annotation2 = annotationElementValue.annotationValue;
    ElementValue[] elementValueArray = annotation2.elementValues;
    assertNull(elementValueArray[0]);
    assertEquals(0, annotation2.u2elementValuesCount);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link ClassShrinker#visitArrayElementValue(Clazz, Annotation, ArrayElementValue)}.
   *
   * <p>Method under test: {@link ClassShrinker#visitArrayElementValue(Clazz, Annotation,
   * ArrayElementValue)}
   */
  @Test
  @DisplayName("Test visitArrayElementValue(Clazz, Annotation, ArrayElementValue)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassShrinker.visitArrayElementValue(Clazz, Annotation, ArrayElementValue)"
  })
  void testVisitArrayElementValue() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new ShortestUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    ArrayElementValue arrayElementValue =
        new ArrayElementValue(1, 1, new ElementValue[] {new AnnotationElementValue()});

    // Act
    classShrinker.visitArrayElementValue(clazz, annotation, arrayElementValue);

    // Assert
    ElementValue[] elementValueArray = arrayElementValue.elementValues;
    assertNull(elementValueArray[0]);
    assertEquals(0, arrayElementValue.u2elementValuesCount);
    assertEquals(1, elementValueArray.length);
  }

  /**
   * Test {@link ClassShrinker#visitArrayElementValue(Clazz, Annotation, ArrayElementValue)}.
   *
   * <ul>
   *   <li>Then {@link ArrayElementValue#ArrayElementValue()} {@link
   *       ArrayElementValue#u2elementValuesCount} is zero.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitArrayElementValue(Clazz, Annotation,
   * ArrayElementValue)}
   */
  @Test
  @DisplayName(
      "Test visitArrayElementValue(Clazz, Annotation, ArrayElementValue); then ArrayElementValue() u2elementValuesCount is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassShrinker.visitArrayElementValue(Clazz, Annotation, ArrayElementValue)"
  })
  void testVisitArrayElementValue_thenArrayElementValueU2elementValuesCountIsZero() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    ArrayElementValue arrayElementValue = new ArrayElementValue();

    // Act
    classShrinker.visitArrayElementValue(clazz, annotation, arrayElementValue);

    // Assert that nothing has changed
    assertEquals(0, arrayElementValue.u2elementValuesCount);
  }

  /**
   * Test {@link ClassShrinker#visitArrayElementValue(Clazz, Annotation, ArrayElementValue)}.
   *
   * <ul>
   *   <li>Then first element is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ClassShrinker#visitArrayElementValue(Clazz, Annotation,
   * ArrayElementValue)}
   */
  @Test
  @DisplayName(
      "Test visitArrayElementValue(Clazz, Annotation, ArrayElementValue); then first element is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ClassShrinker.visitArrayElementValue(Clazz, Annotation, ArrayElementValue)"
  })
  void testVisitArrayElementValue_thenFirstElementIsNull() {
    // Arrange
    ClassShrinker classShrinker = new ClassShrinker(new SimpleUsageMarker());
    LibraryClass clazz = new LibraryClass();
    Annotation annotation = new Annotation();
    ArrayElementValue arrayElementValue =
        new ArrayElementValue(1, 1, new ElementValue[] {new AnnotationElementValue()});

    // Act
    classShrinker.visitArrayElementValue(clazz, annotation, arrayElementValue);

    // Assert
    ElementValue[] elementValueArray = arrayElementValue.elementValues;
    assertNull(elementValueArray[0]);
    assertEquals(0, arrayElementValue.u2elementValuesCount);
    assertEquals(1, elementValueArray.length);
  }
}
