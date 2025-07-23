package proguard.evaluation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.MemberSpecification;
import proguard.MemberValueSpecification;
import proguard.classfile.LibraryClass;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.visitor.AllFieldVisitor;
import proguard.classfile.visitor.AllMethodVisitor;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.MemberVisitor;
import proguard.classfile.visitor.MultiClassPoolVisitor;
import proguard.classfile.visitor.NamedFieldVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.util.ConstantStringFunction;
import proguard.util.WildcardManager;

class AssumeClassSpecificationVisitorFactoryDiffblueTest {
  /**
   * Test {@link
   * AssumeClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification,
   * boolean, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <p>Method under test: {@link
   * AssumeClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification,
   * boolean, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor AssumeClassSpecificationVisitorFactory.createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateNonTestingClassVisitor() {
    // Arrange
    AssumeClassSpecificationVisitorFactory assumeClassSpecificationVisitorFactory =
        new AssumeClassSpecificationVisitorFactory(new ParticularReferenceValueFactory());
    MemberSpecification memberSpecification =
        new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor");

    KotlinAnnotationCounter memberVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateNonTestingClassVisitorResult =
        assumeClassSpecificationVisitorFactory.createNonTestingClassVisitor(
            memberSpecification, true, memberVisitor, attributeVisitor, wildcardManager);

    // Assert
    assertTrue(
        assumeClassSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
            instanceof MultiClassPoolVisitor);
    assertTrue(actualCreateNonTestingClassVisitorResult instanceof NamedFieldVisitor);
    assertTrue(
        wildcardManager.createMatchedStringFunction("Expression")
            instanceof ConstantStringFunction);
    assertEquals("Annotation Type", memberSpecification.annotationType);
    assertEquals("Descriptor", memberSpecification.descriptor);
    assertEquals("Name", memberSpecification.name);
    assertNull(memberSpecification.attributeNames);
    assertEquals(0, memberVisitor.getCount());
    assertEquals(1, memberSpecification.requiredSetAccessFlags);
    assertEquals(1, memberSpecification.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link
   * AssumeClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification,
   * boolean, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <p>Method under test: {@link
   * AssumeClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification,
   * boolean, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor AssumeClassSpecificationVisitorFactory.createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateNonTestingClassVisitor2() {
    // Arrange
    AssumeClassSpecificationVisitorFactory assumeClassSpecificationVisitorFactory =
        new AssumeClassSpecificationVisitorFactory(new ParticularReferenceValueFactory());
    Integer valueOfResult = Integer.valueOf(1);
    MemberValueSpecification memberSpecification =
        new MemberValueSpecification(
            1, 1, "Annotation Type", "Name", "Descriptor", new Number[] {valueOfResult});

    KotlinAnnotationCounter memberVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateNonTestingClassVisitorResult =
        assumeClassSpecificationVisitorFactory.createNonTestingClassVisitor(
            memberSpecification, true, memberVisitor, attributeVisitor, wildcardManager);
    LibraryClass libraryClass = new LibraryClass(1, "This Class Name", "Super Class Name");

    actualCreateNonTestingClassVisitorResult.visitAnyClass(libraryClass);

    // Assert
    assertTrue(
        assumeClassSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
            instanceof MultiClassPoolVisitor);
    assertTrue(actualCreateNonTestingClassVisitorResult instanceof NamedFieldVisitor);
    assertTrue(
        wildcardManager.createMatchedStringFunction("Expression")
            instanceof ConstantStringFunction);
    assertEquals("Annotation Type", memberSpecification.annotationType);
    assertEquals("Descriptor", memberSpecification.descriptor);
    assertEquals("Name", memberSpecification.name);
    assertEquals("Super Class Name", libraryClass.getSuperName());
    assertEquals("This Class Name", libraryClass.getName());
    assertNull(libraryClass.getProcessingInfo());
    assertNull(libraryClass.getFeatureName());
    assertNull(memberSpecification.attributeNames);
    assertNull(libraryClass.getSuperClass());
    assertNull(libraryClass.kotlinMetadata);
    assertEquals(0, libraryClass.getInterfaceCount());
    assertEquals(0, memberVisitor.getCount());
    assertEquals(0, libraryClass.getProcessingFlags());
    assertEquals(0, libraryClass.fields.length);
    assertEquals(0, libraryClass.interfaceClasses.length);
    assertEquals(0, libraryClass.interfaceNames.length);
    assertEquals(0, libraryClass.methods.length);
    assertEquals(0, libraryClass.subClasses.length);
    assertEquals(0, libraryClass.subClassCount);
    assertEquals(1, memberSpecification.values.length);
    assertEquals(1, memberSpecification.requiredSetAccessFlags);
    assertTrue(libraryClass.getExtraFeatureNames().isEmpty());
    assertSame(valueOfResult, libraryClass.getAccessFlags());
    assertSame(valueOfResult, memberSpecification.requiredUnsetAccessFlags);
  }

  /**
   * Test {@link
   * AssumeClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification,
   * boolean, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <ul>
   *   <li>Then {@link MemberValueSpecification#MemberValueSpecification()} {@link
   *       MemberValueSpecification#values} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AssumeClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification,
   * boolean, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager); then MemberValueSpecification() values is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor AssumeClassSpecificationVisitorFactory.createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateNonTestingClassVisitor_thenMemberValueSpecificationValuesIsNull() {
    // Arrange
    AssumeClassSpecificationVisitorFactory assumeClassSpecificationVisitorFactory =
        new AssumeClassSpecificationVisitorFactory(new ParticularReferenceValueFactory());
    MemberValueSpecification memberSpecification = new MemberValueSpecification();
    KotlinAnnotationCounter memberVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateNonTestingClassVisitorResult =
        assumeClassSpecificationVisitorFactory.createNonTestingClassVisitor(
            memberSpecification, true, memberVisitor, attributeVisitor, wildcardManager);
    LibraryClass libraryClass = new LibraryClass(1, "This Class Name", "Super Class Name");

    actualCreateNonTestingClassVisitorResult.visitAnyClass(libraryClass);

    // Assert
    assertTrue(actualCreateNonTestingClassVisitorResult instanceof AllFieldVisitor);
    assertTrue(
        assumeClassSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
            instanceof MultiClassPoolVisitor);
    assertTrue(
        wildcardManager.createMatchedStringFunction("Expression")
            instanceof ConstantStringFunction);
    assertEquals("Super Class Name", libraryClass.getSuperName());
    assertEquals("This Class Name", libraryClass.getName());
    assertNull(memberSpecification.values);
    assertNull(libraryClass.getProcessingInfo());
    assertNull(libraryClass.getFeatureName());
    assertNull(memberSpecification.annotationType);
    assertNull(memberSpecification.descriptor);
    assertNull(memberSpecification.name);
    assertNull(memberSpecification.attributeNames);
    assertNull(libraryClass.getSuperClass());
    assertNull(libraryClass.kotlinMetadata);
    assertEquals(0, libraryClass.getInterfaceCount());
    assertEquals(0, memberVisitor.getCount());
    assertEquals(0, libraryClass.getProcessingFlags());
    assertEquals(0, libraryClass.fields.length);
    assertEquals(0, libraryClass.interfaceClasses.length);
    assertEquals(0, libraryClass.interfaceNames.length);
    assertEquals(0, libraryClass.methods.length);
    assertEquals(0, libraryClass.subClasses.length);
    assertEquals(0, memberSpecification.requiredSetAccessFlags);
    assertEquals(0, memberSpecification.requiredUnsetAccessFlags);
    assertEquals(0, libraryClass.subClassCount);
    assertEquals(1, libraryClass.getAccessFlags());
    assertTrue(libraryClass.getExtraFeatureNames().isEmpty());
  }

  /**
   * Test {@link
   * AssumeClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification,
   * boolean, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <ul>
   *   <li>Then return {@link AllFieldVisitor}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AssumeClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification,
   * boolean, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager); then return AllFieldVisitor")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor AssumeClassSpecificationVisitorFactory.createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateNonTestingClassVisitor_thenReturnAllFieldVisitor() {
    // Arrange
    AssumeClassSpecificationVisitorFactory assumeClassSpecificationVisitorFactory =
        new AssumeClassSpecificationVisitorFactory(new ParticularReferenceValueFactory());
    MemberSpecification memberSpecification = new MemberSpecification();
    KotlinAnnotationCounter memberVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateNonTestingClassVisitorResult =
        assumeClassSpecificationVisitorFactory.createNonTestingClassVisitor(
            memberSpecification, true, memberVisitor, attributeVisitor, wildcardManager);
    LibraryClass libraryClass = new LibraryClass(1, "This Class Name", "Super Class Name");

    actualCreateNonTestingClassVisitorResult.visitAnyClass(libraryClass);

    // Assert
    assertTrue(actualCreateNonTestingClassVisitorResult instanceof AllFieldVisitor);
    assertTrue(
        assumeClassSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
            instanceof MultiClassPoolVisitor);
    assertTrue(
        wildcardManager.createMatchedStringFunction("Expression")
            instanceof ConstantStringFunction);
    assertEquals("Super Class Name", libraryClass.getSuperName());
    assertEquals("This Class Name", libraryClass.getName());
    assertNull(libraryClass.getProcessingInfo());
    assertNull(libraryClass.getFeatureName());
    assertNull(memberSpecification.annotationType);
    assertNull(memberSpecification.descriptor);
    assertNull(memberSpecification.name);
    assertNull(memberSpecification.attributeNames);
    assertNull(libraryClass.getSuperClass());
    assertNull(libraryClass.kotlinMetadata);
    assertEquals(0, libraryClass.getInterfaceCount());
    assertEquals(0, memberVisitor.getCount());
    assertEquals(0, libraryClass.getProcessingFlags());
    assertEquals(0, libraryClass.fields.length);
    assertEquals(0, libraryClass.interfaceClasses.length);
    assertEquals(0, libraryClass.interfaceNames.length);
    assertEquals(0, libraryClass.methods.length);
    assertEquals(0, libraryClass.subClasses.length);
    assertEquals(0, memberSpecification.requiredSetAccessFlags);
    assertEquals(0, memberSpecification.requiredUnsetAccessFlags);
    assertEquals(0, libraryClass.subClassCount);
    assertEquals(1, libraryClass.getAccessFlags());
    assertTrue(libraryClass.getExtraFeatureNames().isEmpty());
  }

  /**
   * Test {@link
   * AssumeClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification,
   * boolean, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return {@link AllMethodVisitor}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AssumeClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification,
   * boolean, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager); when 'false'; then return AllMethodVisitor")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor AssumeClassSpecificationVisitorFactory.createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateNonTestingClassVisitor_whenFalse_thenReturnAllMethodVisitor() {
    // Arrange
    AssumeClassSpecificationVisitorFactory assumeClassSpecificationVisitorFactory =
        new AssumeClassSpecificationVisitorFactory(new ParticularReferenceValueFactory());
    MemberSpecification memberSpecification = new MemberSpecification();
    KotlinAnnotationCounter memberVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateNonTestingClassVisitorResult =
        assumeClassSpecificationVisitorFactory.createNonTestingClassVisitor(
            memberSpecification, false, memberVisitor, attributeVisitor, wildcardManager);
    LibraryClass libraryClass = new LibraryClass(1, "This Class Name", "Super Class Name");

    actualCreateNonTestingClassVisitorResult.visitAnyClass(libraryClass);

    // Assert
    assertTrue(actualCreateNonTestingClassVisitorResult instanceof AllMethodVisitor);
    assertTrue(
        assumeClassSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
            instanceof MultiClassPoolVisitor);
    assertTrue(
        wildcardManager.createMatchedStringFunction("Expression")
            instanceof ConstantStringFunction);
    assertEquals("Super Class Name", libraryClass.getSuperName());
    assertEquals("This Class Name", libraryClass.getName());
    assertNull(libraryClass.getProcessingInfo());
    assertNull(libraryClass.getFeatureName());
    assertNull(memberSpecification.annotationType);
    assertNull(memberSpecification.descriptor);
    assertNull(memberSpecification.name);
    assertNull(memberSpecification.attributeNames);
    assertNull(libraryClass.getSuperClass());
    assertNull(libraryClass.kotlinMetadata);
    assertEquals(0, libraryClass.getInterfaceCount());
    assertEquals(0, memberVisitor.getCount());
    assertEquals(0, libraryClass.getProcessingFlags());
    assertEquals(0, libraryClass.fields.length);
    assertEquals(0, libraryClass.interfaceClasses.length);
    assertEquals(0, libraryClass.interfaceNames.length);
    assertEquals(0, libraryClass.methods.length);
    assertEquals(0, libraryClass.subClasses.length);
    assertEquals(0, memberSpecification.requiredSetAccessFlags);
    assertEquals(0, memberSpecification.requiredUnsetAccessFlags);
    assertEquals(0, libraryClass.subClassCount);
    assertEquals(1, libraryClass.getAccessFlags());
    assertTrue(libraryClass.getExtraFeatureNames().isEmpty());
  }

  /**
   * Test {@link
   * AssumeClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification,
   * boolean, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AssumeClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification,
   * boolean, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager); when 'null'; then 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor AssumeClassSpecificationVisitorFactory.createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateNonTestingClassVisitor_whenNull_thenNull() {
    // Arrange
    AssumeClassSpecificationVisitorFactory assumeClassSpecificationVisitorFactory =
        new AssumeClassSpecificationVisitorFactory(new ParticularReferenceValueFactory());
    MemberSpecification memberSpecification =
        new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor");

    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateNonTestingClassVisitorResult =
        assumeClassSpecificationVisitorFactory.createNonTestingClassVisitor(
            memberSpecification, true, null, attributeVisitor, wildcardManager);
    LibraryClass libraryClass = new LibraryClass(1, "This Class Name", "Super Class Name");

    actualCreateNonTestingClassVisitorResult.visitAnyClass(libraryClass);

    // Assert
    assertTrue(
        assumeClassSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
            instanceof MultiClassPoolVisitor);
    assertTrue(actualCreateNonTestingClassVisitorResult instanceof NamedFieldVisitor);
    assertTrue(
        wildcardManager.createMatchedStringFunction("Expression")
            instanceof ConstantStringFunction);
    assertEquals("Annotation Type", memberSpecification.annotationType);
    assertEquals("Descriptor", memberSpecification.descriptor);
    assertEquals("Name", memberSpecification.name);
    assertEquals("Super Class Name", libraryClass.getSuperName());
    assertEquals("This Class Name", libraryClass.getName());
    assertNull(libraryClass.getProcessingInfo());
    assertNull(libraryClass.getFeatureName());
    assertNull(memberSpecification.attributeNames);
    assertNull(libraryClass.getSuperClass());
    assertNull(libraryClass.kotlinMetadata);
    assertNull(null);
    assertEquals(0, libraryClass.getInterfaceCount());
    assertEquals(0, attributeVisitor.getCount());
    assertEquals(0, libraryClass.getProcessingFlags());
    assertEquals(0, libraryClass.fields.length);
    assertEquals(0, libraryClass.interfaceClasses.length);
    assertEquals(0, libraryClass.interfaceNames.length);
    assertEquals(0, libraryClass.methods.length);
    assertEquals(0, libraryClass.subClasses.length);
    assertEquals(0, libraryClass.subClassCount);
    assertEquals(1, libraryClass.getAccessFlags());
    assertEquals(1, memberSpecification.requiredSetAccessFlags);
    assertEquals(1, memberSpecification.requiredUnsetAccessFlags);
    assertTrue(libraryClass.getExtraFeatureNames().isEmpty());
  }

  /**
   * Test {@link
   * AssumeClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification,
   * boolean, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AssumeClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification,
   * boolean, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager); when 'null'; then 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor AssumeClassSpecificationVisitorFactory.createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateNonTestingClassVisitor_whenNull_thenNull2() {
    // Arrange
    AssumeClassSpecificationVisitorFactory assumeClassSpecificationVisitorFactory =
        new AssumeClassSpecificationVisitorFactory(new ParticularReferenceValueFactory());
    MemberSpecification memberSpecification =
        new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor");

    KotlinAnnotationCounter memberVisitor = new KotlinAnnotationCounter();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateNonTestingClassVisitorResult =
        assumeClassSpecificationVisitorFactory.createNonTestingClassVisitor(
            memberSpecification, true, memberVisitor, null, wildcardManager);
    LibraryClass libraryClass = new LibraryClass(1, "This Class Name", "Super Class Name");

    actualCreateNonTestingClassVisitorResult.visitAnyClass(libraryClass);

    // Assert
    assertTrue(
        assumeClassSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
            instanceof MultiClassPoolVisitor);
    assertTrue(actualCreateNonTestingClassVisitorResult instanceof NamedFieldVisitor);
    assertTrue(
        wildcardManager.createMatchedStringFunction("Expression")
            instanceof ConstantStringFunction);
    assertEquals("Annotation Type", memberSpecification.annotationType);
    assertEquals("Descriptor", memberSpecification.descriptor);
    assertEquals("Name", memberSpecification.name);
    assertEquals("Super Class Name", libraryClass.getSuperName());
    assertEquals("This Class Name", libraryClass.getName());
    assertNull(libraryClass.getProcessingInfo());
    assertNull(libraryClass.getFeatureName());
    assertNull(memberSpecification.attributeNames);
    assertNull(libraryClass.getSuperClass());
    assertNull(null);
    assertNull(libraryClass.kotlinMetadata);
    assertEquals(0, libraryClass.getInterfaceCount());
    assertEquals(0, memberVisitor.getCount());
    assertEquals(0, libraryClass.getProcessingFlags());
    assertEquals(0, libraryClass.fields.length);
    assertEquals(0, libraryClass.interfaceClasses.length);
    assertEquals(0, libraryClass.interfaceNames.length);
    assertEquals(0, libraryClass.methods.length);
    assertEquals(0, libraryClass.subClasses.length);
    assertEquals(0, libraryClass.subClassCount);
    assertEquals(1, libraryClass.getAccessFlags());
    assertEquals(1, memberSpecification.requiredSetAccessFlags);
    assertEquals(1, memberSpecification.requiredUnsetAccessFlags);
    assertTrue(libraryClass.getExtraFeatureNames().isEmpty());
  }
}
