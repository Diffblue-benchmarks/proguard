package proguard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.LibraryClass;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.visitor.AllClassVisitor;
import proguard.classfile.visitor.AllFieldVisitor;
import proguard.classfile.visitor.AllMethodVisitor;
import proguard.classfile.visitor.ClassHierarchyTraveler;
import proguard.classfile.visitor.ClassPoolVisitor;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.MemberVisitor;
import proguard.classfile.visitor.MultiClassPoolVisitor;
import proguard.classfile.visitor.MultiClassVisitor;
import proguard.classfile.visitor.NamedClassVisitor;
import proguard.classfile.visitor.NamedFieldVisitor;
import proguard.classfile.visitor.NamedMethodVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.util.ConstantStringFunction;
import proguard.util.WildcardManager;

class ClassSpecificationVisitorFactoryDiffblueTest {
  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)} with {@code
   * classSpecification}, {@code classVisitor}, {@code fieldVisitor}, {@code methodVisitor}, {@code
   * attributeVisitor}, {@code wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification, ClassVisitor,
   * MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'fieldVisitor', 'methodVisitor', 'attributeVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void
      testCreateClassPoolVisitorWithClassSpecificationClassVisitorFieldVisitorMethodVisitorAttributeVisitorWildcardManager() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name");

    ClassVisitor classVisitor = mock(ClassVisitor.class);
    KotlinAnnotationCounter fieldVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();

    // Act
    ClassPoolVisitor actualCreateClassPoolVisitorResult =
        classSpecificationVisitorFactory.createClassPoolVisitor(
            classSpecification,
            classVisitor,
            fieldVisitor,
            methodVisitor,
            attributeVisitor,
            new WildcardManager());
    actualCreateClassPoolVisitorResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassPoolVisitorResult instanceof NamedClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)} with {@code
   * classSpecification}, {@code classVisitor}, {@code fieldVisitor}, {@code methodVisitor}, {@code
   * attributeVisitor}, {@code wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification, ClassVisitor,
   * MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'fieldVisitor', 'methodVisitor', 'attributeVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void
      testCreateClassPoolVisitorWithClassSpecificationClassVisitorFieldVisitorMethodVisitorAttributeVisitorWildcardManager2() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            0,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name");

    ClassVisitor classVisitor = mock(ClassVisitor.class);
    KotlinAnnotationCounter fieldVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();

    // Act
    ClassPoolVisitor actualCreateClassPoolVisitorResult =
        classSpecificationVisitorFactory.createClassPoolVisitor(
            classSpecification,
            classVisitor,
            fieldVisitor,
            methodVisitor,
            attributeVisitor,
            new WildcardManager());
    actualCreateClassPoolVisitorResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassPoolVisitorResult instanceof NamedClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)} with {@code
   * classSpecification}, {@code classVisitor}, {@code fieldVisitor}, {@code methodVisitor}, {@code
   * attributeVisitor}, {@code wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification, ClassVisitor,
   * MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'fieldVisitor', 'methodVisitor', 'attributeVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void
      testCreateClassPoolVisitorWithClassSpecificationClassVisitorFieldVisitorMethodVisitorAttributeVisitorWildcardManager3() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments", 1, 1, "", "Class Name", "Extends Annotation Type", "Extends Class Name");

    ClassVisitor classVisitor = mock(ClassVisitor.class);
    KotlinAnnotationCounter fieldVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();

    // Act
    ClassPoolVisitor actualCreateClassPoolVisitorResult =
        classSpecificationVisitorFactory.createClassPoolVisitor(
            classSpecification,
            classVisitor,
            fieldVisitor,
            methodVisitor,
            attributeVisitor,
            new WildcardManager());
    actualCreateClassPoolVisitorResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassPoolVisitorResult instanceof NamedClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)} with {@code
   * classSpecification}, {@code classVisitor}, {@code fieldVisitor}, {@code methodVisitor}, {@code
   * attributeVisitor}, {@code wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification, ClassVisitor,
   * MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'fieldVisitor', 'methodVisitor', 'attributeVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void
      testCreateClassPoolVisitorWithClassSpecificationClassVisitorFieldVisitorMethodVisitorAttributeVisitorWildcardManager4() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ClassSpecification classSpecification = new ClassSpecification();
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    KotlinAnnotationCounter fieldVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();

    // Act
    ClassPoolVisitor actualCreateClassPoolVisitorResult =
        classSpecificationVisitorFactory.createClassPoolVisitor(
            classSpecification,
            classVisitor,
            fieldVisitor,
            methodVisitor,
            attributeVisitor,
            new WildcardManager());
    actualCreateClassPoolVisitorResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassPoolVisitorResult instanceof AllClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)} with {@code
   * classSpecification}, {@code classVisitor}, {@code fieldVisitor}, {@code methodVisitor}, {@code
   * attributeVisitor}, {@code wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification, ClassVisitor,
   * MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'fieldVisitor', 'methodVisitor', 'attributeVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void
      testCreateClassPoolVisitorWithClassSpecificationClassVisitorFieldVisitorMethodVisitorAttributeVisitorWildcardManager5() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ArrayList<Object> fieldSpecifications = new ArrayList<>();
    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name",
            fieldSpecifications,
            new ArrayList<>());

    ClassVisitor classVisitor = mock(ClassVisitor.class);
    KotlinAnnotationCounter fieldVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();

    // Act
    ClassPoolVisitor actualCreateClassPoolVisitorResult =
        classSpecificationVisitorFactory.createClassPoolVisitor(
            classSpecification,
            classVisitor,
            fieldVisitor,
            methodVisitor,
            attributeVisitor,
            new WildcardManager());
    actualCreateClassPoolVisitorResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassPoolVisitorResult instanceof NamedClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)} with {@code
   * classSpecification}, {@code classVisitor}, {@code fieldVisitor}, {@code methodVisitor}, {@code
   * attributeVisitor}, {@code wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification, ClassVisitor,
   * MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'fieldVisitor', 'methodVisitor', 'attributeVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void
      testCreateClassPoolVisitorWithClassSpecificationClassVisitorFieldVisitorMethodVisitorAttributeVisitorWildcardManager6() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();

    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name");
    classSpecification.addField(
        new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor"));
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    KotlinAnnotationCounter fieldVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();

    // Act
    ClassPoolVisitor actualCreateClassPoolVisitorResult =
        classSpecificationVisitorFactory.createClassPoolVisitor(
            classSpecification,
            classVisitor,
            fieldVisitor,
            methodVisitor,
            attributeVisitor,
            new WildcardManager());
    actualCreateClassPoolVisitorResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassPoolVisitorResult instanceof NamedClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)} with {@code
   * classSpecification}, {@code classVisitor}, {@code fieldVisitor}, {@code methodVisitor}, {@code
   * attributeVisitor}, {@code wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification, ClassVisitor,
   * MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'fieldVisitor', 'methodVisitor', 'attributeVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void
      testCreateClassPoolVisitorWithClassSpecificationClassVisitorFieldVisitorMethodVisitorAttributeVisitorWildcardManager7() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();

    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name");
    classSpecification.addMethod(
        new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor"));
    classSpecification.addField(
        new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor"));
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    KotlinAnnotationCounter fieldVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();

    // Act
    ClassPoolVisitor actualCreateClassPoolVisitorResult =
        classSpecificationVisitorFactory.createClassPoolVisitor(
            classSpecification,
            classVisitor,
            fieldVisitor,
            methodVisitor,
            attributeVisitor,
            new WildcardManager());
    actualCreateClassPoolVisitorResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassPoolVisitorResult instanceof NamedClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)} with {@code
   * classSpecification}, {@code classVisitor}, {@code fieldVisitor}, {@code methodVisitor}, {@code
   * attributeVisitor}, {@code wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification, ClassVisitor,
   * MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'fieldVisitor', 'methodVisitor', 'attributeVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void
      testCreateClassPoolVisitorWithClassSpecificationClassVisitorFieldVisitorMethodVisitorAttributeVisitorWildcardManager8() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();

    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name");
    classSpecification.addMethod(
        new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor"));
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    KotlinAnnotationCounter fieldVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();

    // Act
    ClassPoolVisitor actualCreateClassPoolVisitorResult =
        classSpecificationVisitorFactory.createClassPoolVisitor(
            classSpecification,
            classVisitor,
            fieldVisitor,
            methodVisitor,
            attributeVisitor,
            new WildcardManager());
    actualCreateClassPoolVisitorResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassPoolVisitorResult instanceof NamedClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)} with {@code
   * classSpecification}, {@code classVisitor}, {@code fieldVisitor}, {@code methodVisitor}, {@code
   * attributeVisitor}, {@code wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification, ClassVisitor,
   * MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'fieldVisitor', 'methodVisitor', 'attributeVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void
      testCreateClassPoolVisitorWithClassSpecificationClassVisitorFieldVisitorMethodVisitorAttributeVisitorWildcardManager9() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();

    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name");
    classSpecification.addField(
        new MemberSpecification(0, 1, "Annotation Type", "Name", "Descriptor"));
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    KotlinAnnotationCounter fieldVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();

    // Act
    ClassPoolVisitor actualCreateClassPoolVisitorResult =
        classSpecificationVisitorFactory.createClassPoolVisitor(
            classSpecification,
            classVisitor,
            fieldVisitor,
            methodVisitor,
            attributeVisitor,
            new WildcardManager());
    actualCreateClassPoolVisitorResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassPoolVisitorResult instanceof NamedClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)} with {@code
   * classSpecification}, {@code classVisitor}, {@code fieldVisitor}, {@code methodVisitor}, {@code
   * attributeVisitor}, {@code wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification, ClassVisitor,
   * MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'fieldVisitor', 'methodVisitor', 'attributeVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void
      testCreateClassPoolVisitorWithClassSpecificationClassVisitorFieldVisitorMethodVisitorAttributeVisitorWildcardManager10() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name");

    KotlinAnnotationCounter fieldVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();

    // Act
    ClassPoolVisitor actualCreateClassPoolVisitorResult =
        classSpecificationVisitorFactory.createClassPoolVisitor(
            classSpecification,
            null,
            fieldVisitor,
            methodVisitor,
            attributeVisitor,
            new WildcardManager());
    actualCreateClassPoolVisitorResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassPoolVisitorResult instanceof NamedClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)} with {@code
   * classSpecification}, {@code classVisitor}, {@code fieldVisitor}, {@code methodVisitor}, {@code
   * attributeVisitor}, {@code wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification, ClassVisitor,
   * MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'fieldVisitor', 'methodVisitor', 'attributeVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void
      testCreateClassPoolVisitorWithClassSpecificationClassVisitorFieldVisitorMethodVisitorAttributeVisitorWildcardManager11() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name");

    ClassVisitor classVisitor = mock(ClassVisitor.class);
    KotlinAnnotationCounter fieldVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();

    // Act
    ClassPoolVisitor actualCreateClassPoolVisitorResult =
        classSpecificationVisitorFactory.createClassPoolVisitor(
            classSpecification,
            classVisitor,
            fieldVisitor,
            methodVisitor,
            null,
            new WildcardManager());
    actualCreateClassPoolVisitorResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassPoolVisitorResult instanceof NamedClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)} with {@code
   * classSpecification}, {@code classVisitor}, {@code fieldVisitor}, {@code methodVisitor}, {@code
   * attributeVisitor}, {@code wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification, ClassVisitor,
   * MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'fieldVisitor', 'methodVisitor', 'attributeVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void
      testCreateClassPoolVisitorWithClassSpecificationClassVisitorFieldVisitorMethodVisitorAttributeVisitorWildcardManager12() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ArrayList<Object> fieldSpecifications = new ArrayList<>();
    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name",
            fieldSpecifications,
            new ArrayList<>());

    ClassVisitor classVisitor = mock(ClassVisitor.class);
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();

    // Act
    ClassPoolVisitor actualCreateClassPoolVisitorResult =
        classSpecificationVisitorFactory.createClassPoolVisitor(
            classSpecification,
            classVisitor,
            null,
            methodVisitor,
            attributeVisitor,
            new WildcardManager());
    actualCreateClassPoolVisitorResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassPoolVisitorResult instanceof NamedClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)} with {@code
   * classSpecification}, {@code classVisitor}, {@code fieldVisitor}, {@code methodVisitor}, {@code
   * attributeVisitor}, {@code wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification, ClassVisitor,
   * MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'fieldVisitor', 'methodVisitor', 'attributeVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void
      testCreateClassPoolVisitorWithClassSpecificationClassVisitorFieldVisitorMethodVisitorAttributeVisitorWildcardManager13() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ArrayList<Object> fieldSpecifications = new ArrayList<>();
    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name",
            fieldSpecifications,
            new ArrayList<>());

    ClassVisitor classVisitor = mock(ClassVisitor.class);
    KotlinAnnotationCounter fieldVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();

    // Act
    ClassPoolVisitor actualCreateClassPoolVisitorResult =
        classSpecificationVisitorFactory.createClassPoolVisitor(
            classSpecification,
            classVisitor,
            fieldVisitor,
            methodVisitor,
            null,
            new WildcardManager());
    actualCreateClassPoolVisitorResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassPoolVisitorResult instanceof NamedClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(List, ClassVisitor,
   * MemberVisitor, MemberVisitor, AttributeVisitor)} with {@code classSpecifications}, {@code
   * classVisitor}, {@code fieldVisitor}, {@code methodVisitor}, {@code attributeVisitor}.
   *
   * <p>Method under test: {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(List,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor)}
   */
  @Test
  @DisplayName(
      "Test createClassPoolVisitor(List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor) with 'classSpecifications', 'classVisitor', 'fieldVisitor', 'methodVisitor', 'attributeVisitor'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassPoolVisitor(List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor)"
  })
  void
      testCreateClassPoolVisitorWithClassSpecificationsClassVisitorFieldVisitorMethodVisitorAttributeVisitor() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ArrayList<Object> classSpecifications = new ArrayList<>();
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    KotlinAnnotationCounter fieldVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();

    // Act
    ClassPoolVisitor actualCreateClassPoolVisitorResult =
        classSpecificationVisitorFactory.createClassPoolVisitor(
            classSpecifications,
            classVisitor,
            fieldVisitor,
            methodVisitor,
            new KotlinAnnotationCounter());
    actualCreateClassPoolVisitorResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassPoolVisitorResult instanceof MultiClassPoolVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(List, ClassVisitor,
   * MemberVisitor, MemberVisitor, AttributeVisitor)} with {@code classSpecifications}, {@code
   * classVisitor}, {@code fieldVisitor}, {@code methodVisitor}, {@code attributeVisitor}.
   *
   * <p>Method under test: {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(List,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor)}
   */
  @Test
  @DisplayName(
      "Test createClassPoolVisitor(List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor) with 'classSpecifications', 'classVisitor', 'fieldVisitor', 'methodVisitor', 'attributeVisitor'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassPoolVisitor(List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor)"
  })
  void
      testCreateClassPoolVisitorWithClassSpecificationsClassVisitorFieldVisitorMethodVisitorAttributeVisitor2() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    KotlinAnnotationCounter fieldVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();

    // Act
    ClassPoolVisitor actualCreateClassPoolVisitorResult =
        classSpecificationVisitorFactory.createClassPoolVisitor(
            null, classVisitor, fieldVisitor, methodVisitor, new KotlinAnnotationCounter());
    actualCreateClassPoolVisitorResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassPoolVisitorResult instanceof MultiClassPoolVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(List, ClassVisitor,
   * MemberVisitor, MemberVisitor, AttributeVisitor)} with {@code classSpecifications}, {@code
   * classVisitor}, {@code fieldVisitor}, {@code methodVisitor}, {@code attributeVisitor}.
   *
   * <p>Method under test: {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(List,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor)}
   */
  @Test
  @DisplayName(
      "Test createClassPoolVisitor(List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor) with 'classSpecifications', 'classVisitor', 'fieldVisitor', 'methodVisitor', 'attributeVisitor'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassPoolVisitor(List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor)"
  })
  void
      testCreateClassPoolVisitorWithClassSpecificationsClassVisitorFieldVisitorMethodVisitorAttributeVisitor3() {
    // Arrange
    KeepClassSpecificationVisitorFactory keepClassSpecificationVisitorFactory =
        new KeepClassSpecificationVisitorFactory(true, true, true);
    ArrayList<Object> keepClassSpecifications = new ArrayList<>();
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    KotlinAnnotationCounter fieldVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();

    // Act
    ClassPoolVisitor actualCreateClassPoolVisitorResult =
        keepClassSpecificationVisitorFactory.createClassPoolVisitor(
            keepClassSpecifications,
            classVisitor,
            fieldVisitor,
            methodVisitor,
            new KotlinAnnotationCounter());
    actualCreateClassPoolVisitorResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassPoolVisitorResult instanceof MultiClassPoolVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(List, ClassVisitor,
   * MemberVisitor)} with {@code classSpecifications}, {@code classVisitor}, {@code memberVisitor}.
   *
   * <p>Method under test: {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(List,
   * ClassVisitor, MemberVisitor)}
   */
  @Test
  @DisplayName(
      "Test createClassPoolVisitor(List, ClassVisitor, MemberVisitor) with 'classSpecifications', 'classVisitor', 'memberVisitor'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassPoolVisitor(List, ClassVisitor, MemberVisitor)"
  })
  void testCreateClassPoolVisitorWithClassSpecificationsClassVisitorMemberVisitor() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ArrayList<Object> classSpecifications = new ArrayList<>();
    ClassVisitor classVisitor = mock(ClassVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassPoolVisitorResult =
        classSpecificationVisitorFactory.createClassPoolVisitor(
            classSpecifications, classVisitor, new KotlinAnnotationCounter());
    actualCreateClassPoolVisitorResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassPoolVisitorResult instanceof MultiClassPoolVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(List, ClassVisitor,
   * MemberVisitor)} with {@code classSpecifications}, {@code classVisitor}, {@code memberVisitor}.
   *
   * <p>Method under test: {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(List,
   * ClassVisitor, MemberVisitor)}
   */
  @Test
  @DisplayName(
      "Test createClassPoolVisitor(List, ClassVisitor, MemberVisitor) with 'classSpecifications', 'classVisitor', 'memberVisitor'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassPoolVisitor(List, ClassVisitor, MemberVisitor)"
  })
  void testCreateClassPoolVisitorWithClassSpecificationsClassVisitorMemberVisitor2() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ClassVisitor classVisitor = mock(ClassVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassPoolVisitorResult =
        classSpecificationVisitorFactory.createClassPoolVisitor(
            null, classVisitor, new KotlinAnnotationCounter());
    actualCreateClassPoolVisitorResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassPoolVisitorResult instanceof MultiClassPoolVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(List, ClassVisitor,
   * MemberVisitor)} with {@code classSpecifications}, {@code classVisitor}, {@code memberVisitor}.
   *
   * <p>Method under test: {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(List,
   * ClassVisitor, MemberVisitor)}
   */
  @Test
  @DisplayName(
      "Test createClassPoolVisitor(List, ClassVisitor, MemberVisitor) with 'classSpecifications', 'classVisitor', 'memberVisitor'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassPoolVisitor(List, ClassVisitor, MemberVisitor)"
  })
  void testCreateClassPoolVisitorWithClassSpecificationsClassVisitorMemberVisitor3() {
    // Arrange
    KeepClassSpecificationVisitorFactory keepClassSpecificationVisitorFactory =
        new KeepClassSpecificationVisitorFactory(true, true, true);
    ArrayList<Object> classSpecifications = new ArrayList<>();
    ClassVisitor classVisitor = mock(ClassVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassPoolVisitorResult =
        keepClassSpecificationVisitorFactory.createClassPoolVisitor(
            classSpecifications, classVisitor, new KotlinAnnotationCounter());
    actualCreateClassPoolVisitorResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassPoolVisitorResult instanceof MultiClassPoolVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then {@link ArrayList#ArrayList()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List,
   * List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager); given '42'; when ArrayList() add '42'; then ArrayList() size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor ClassSpecificationVisitorFactory.createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateCombinedClassVisitor_given42_whenArrayListAdd42_thenArrayListSizeIsOne() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();

    ArrayList<Object> attributeNames = new ArrayList<>();
    attributeNames.add("42");
    ArrayList<Object> fieldSpecifications = new ArrayList<>();
    ArrayList<Object> methodSpecifications = new ArrayList<>();
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doNothing().when(classVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    KotlinAnnotationCounter fieldVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateCombinedClassVisitorResult =
        classSpecificationVisitorFactory.createCombinedClassVisitor(
            attributeNames,
            fieldSpecifications,
            methodSpecifications,
            classVisitor,
            fieldVisitor,
            methodVisitor,
            attributeVisitor,
            wildcardManager);
    LibraryClass libraryClass = new LibraryClass();
    actualCreateCombinedClassVisitorResult.visitAnyClass(libraryClass);

    // Assert
    verify(classVisitor).visitLibraryClass(isA(LibraryClass.class));
    assertTrue(
        classSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
            instanceof MultiClassPoolVisitor);
    assertTrue(actualCreateCombinedClassVisitorResult instanceof MultiClassVisitor);
    assertTrue(
        wildcardManager.createMatchedStringFunction("Expression")
            instanceof ConstantStringFunction);
    assertEquals(1, attributeNames.size());
    assertEquals("42", attributeNames.get(0));
    assertNull(libraryClass.getProcessingInfo());
    assertNull(libraryClass.getName());
    assertNull(libraryClass.getSuperName());
    assertNull(libraryClass.getFeatureName());
    assertNull(libraryClass.interfaceNames);
    assertNull(libraryClass.getSuperClass());
    assertNull(libraryClass.fields);
    assertNull(libraryClass.methods);
    assertNull(libraryClass.kotlinMetadata);
    assertEquals(0, libraryClass.getAccessFlags());
    assertEquals(0, libraryClass.getInterfaceCount());
    assertEquals(0, fieldVisitor.getCount());
    assertEquals(0, libraryClass.getProcessingFlags());
    assertEquals(0, libraryClass.interfaceClasses.length);
    assertEquals(0, libraryClass.subClasses.length);
    assertEquals(0, libraryClass.subClassCount);
    assertTrue(libraryClass.getExtraFeatureNames().isEmpty());
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then {@link ArrayList#ArrayList()} size is two.
   * </ul>
   *
   * <p>Method under test: {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List,
   * List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager); given '42'; when ArrayList() add '42'; then ArrayList() size is two")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor ClassSpecificationVisitorFactory.createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateCombinedClassVisitor_given42_whenArrayListAdd42_thenArrayListSizeIsTwo() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();

    ArrayList<Object> attributeNames = new ArrayList<>();
    attributeNames.add("42");
    attributeNames.add("42");
    ArrayList<Object> fieldSpecifications = new ArrayList<>();
    ArrayList<Object> methodSpecifications = new ArrayList<>();
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doNothing().when(classVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    KotlinAnnotationCounter fieldVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateCombinedClassVisitorResult =
        classSpecificationVisitorFactory.createCombinedClassVisitor(
            attributeNames,
            fieldSpecifications,
            methodSpecifications,
            classVisitor,
            fieldVisitor,
            methodVisitor,
            attributeVisitor,
            wildcardManager);
    LibraryClass libraryClass = new LibraryClass();
    actualCreateCombinedClassVisitorResult.visitAnyClass(libraryClass);

    // Assert
    verify(classVisitor).visitLibraryClass(isA(LibraryClass.class));
    assertTrue(
        classSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
            instanceof MultiClassPoolVisitor);
    assertTrue(actualCreateCombinedClassVisitorResult instanceof MultiClassVisitor);
    assertTrue(
        wildcardManager.createMatchedStringFunction("Expression")
            instanceof ConstantStringFunction);
    assertEquals(2, attributeNames.size());
    assertEquals("42", attributeNames.get(0));
    assertEquals("42", attributeNames.get(1));
    assertNull(libraryClass.getProcessingInfo());
    assertNull(libraryClass.getName());
    assertNull(libraryClass.getSuperName());
    assertNull(libraryClass.getFeatureName());
    assertNull(libraryClass.interfaceNames);
    assertNull(libraryClass.getSuperClass());
    assertNull(libraryClass.fields);
    assertNull(libraryClass.methods);
    assertNull(libraryClass.kotlinMetadata);
    assertEquals(0, libraryClass.getAccessFlags());
    assertEquals(0, libraryClass.getInterfaceCount());
    assertEquals(0, fieldVisitor.getCount());
    assertEquals(0, libraryClass.getProcessingFlags());
    assertEquals(0, libraryClass.interfaceClasses.length);
    assertEquals(0, libraryClass.subClasses.length);
    assertEquals(0, libraryClass.subClassCount);
    assertTrue(libraryClass.getExtraFeatureNames().isEmpty());
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <ul>
   *   <li>Then calls {@link ClassVisitor#visitLibraryClass(LibraryClass)}.
   * </ul>
   *
   * <p>Method under test: {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List,
   * List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager); then calls visitLibraryClass(LibraryClass)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor ClassSpecificationVisitorFactory.createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateCombinedClassVisitor_thenCallsVisitLibraryClass() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ArrayList<Object> attributeNames = new ArrayList<>();
    ArrayList<Object> fieldSpecifications = new ArrayList<>();
    ArrayList<Object> methodSpecifications = new ArrayList<>();
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doNothing().when(classVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    KotlinAnnotationCounter fieldVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateCombinedClassVisitorResult =
        classSpecificationVisitorFactory.createCombinedClassVisitor(
            attributeNames,
            fieldSpecifications,
            methodSpecifications,
            classVisitor,
            fieldVisitor,
            methodVisitor,
            attributeVisitor,
            wildcardManager);
    LibraryClass libraryClass = new LibraryClass();
    actualCreateCombinedClassVisitorResult.visitAnyClass(libraryClass);

    // Assert
    verify(classVisitor).visitLibraryClass(isA(LibraryClass.class));
    assertTrue(
        classSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
            instanceof MultiClassPoolVisitor);
    assertTrue(actualCreateCombinedClassVisitorResult instanceof MultiClassVisitor);
    assertTrue(
        wildcardManager.createMatchedStringFunction("Expression")
            instanceof ConstantStringFunction);
    assertNull(libraryClass.getProcessingInfo());
    assertNull(libraryClass.getName());
    assertNull(libraryClass.getSuperName());
    assertNull(libraryClass.getFeatureName());
    assertNull(libraryClass.interfaceNames);
    assertNull(libraryClass.getSuperClass());
    assertNull(libraryClass.fields);
    assertNull(libraryClass.methods);
    assertNull(libraryClass.kotlinMetadata);
    assertEquals(0, libraryClass.getAccessFlags());
    assertEquals(0, libraryClass.getInterfaceCount());
    assertEquals(0, fieldVisitor.getCount());
    assertEquals(0, libraryClass.getProcessingFlags());
    assertEquals(0, libraryClass.interfaceClasses.length);
    assertEquals(0, libraryClass.subClasses.length);
    assertEquals(0, libraryClass.subClassCount);
    assertTrue(attributeNames.isEmpty());
    assertTrue(libraryClass.getExtraFeatureNames().isEmpty());
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <ul>
   *   <li>When {@link ClassVisitor}.
   *   <li>Then {@link ArrayList#ArrayList()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List,
   * List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager); when ClassVisitor; then ArrayList() Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor ClassSpecificationVisitorFactory.createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateCombinedClassVisitor_whenClassVisitor_thenArrayListEmpty() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ArrayList<Object> attributeNames = new ArrayList<>();
    ArrayList<Object> fieldSpecifications = new ArrayList<>();
    ArrayList<Object> methodSpecifications = new ArrayList<>();
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    KotlinAnnotationCounter fieldVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateCombinedClassVisitorResult =
        classSpecificationVisitorFactory.createCombinedClassVisitor(
            attributeNames,
            fieldSpecifications,
            methodSpecifications,
            classVisitor,
            fieldVisitor,
            methodVisitor,
            attributeVisitor,
            wildcardManager);

    // Assert
    assertTrue(
        classSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
            instanceof MultiClassPoolVisitor);
    assertTrue(actualCreateCombinedClassVisitorResult instanceof MultiClassVisitor);
    assertTrue(
        wildcardManager.createMatchedStringFunction("Expression")
            instanceof ConstantStringFunction);
    assertEquals(0, fieldVisitor.getCount());
    assertTrue(attributeNames.isEmpty());
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <ul>
   *   <li>When {@link ClassVisitor}.
   *   <li>Then {@link ArrayList#ArrayList()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List,
   * List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager); when ClassVisitor; then ArrayList() Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor ClassSpecificationVisitorFactory.createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateCombinedClassVisitor_whenClassVisitor_thenArrayListEmpty2() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ArrayList<Object> methodSpecifications = new ArrayList<>();
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateCombinedClassVisitorResult =
        classSpecificationVisitorFactory.createCombinedClassVisitor(
            null,
            null,
            methodSpecifications,
            classVisitor,
            null,
            methodVisitor,
            null,
            wildcardManager);

    // Assert
    assertTrue(
        classSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
            instanceof MultiClassPoolVisitor);
    assertTrue(actualCreateCombinedClassVisitorResult instanceof MultiClassVisitor);
    assertTrue(
        wildcardManager.createMatchedStringFunction("Expression")
            instanceof ConstantStringFunction);
    assertNull(null);
    assertNull(null);
    assertNull(null);
    assertEquals(0, methodVisitor.getCount());
    assertTrue(methodSpecifications.isEmpty());
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <ul>
   *   <li>When {@link ClassVisitor}.
   *   <li>Then return {@link ClassVisitor}.
   * </ul>
   *
   * <p>Method under test: {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List,
   * List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager); when ClassVisitor; then return ClassVisitor")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor ClassSpecificationVisitorFactory.createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateCombinedClassVisitor_whenClassVisitor_thenReturnClassVisitor() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateCombinedClassVisitorResult =
        classSpecificationVisitorFactory.createCombinedClassVisitor(
            null, null, null, classVisitor, null, null, null, wildcardManager);

    // Assert
    assertTrue(
        classSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
            instanceof MultiClassPoolVisitor);
    assertTrue(
        wildcardManager.createMatchedStringFunction("Expression")
            instanceof ConstantStringFunction);
    assertNull(null);
    assertNull(null);
    assertNull(null);
    assertSame(classVisitor, actualCreateCombinedClassVisitorResult);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <ul>
   *   <li>When {@link ClassVisitor}.
   *   <li>Then return {@link MultiClassVisitor}.
   * </ul>
   *
   * <p>Method under test: {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List,
   * List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager); when ClassVisitor; then return MultiClassVisitor")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor ClassSpecificationVisitorFactory.createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateCombinedClassVisitor_whenClassVisitor_thenReturnMultiClassVisitor() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateCombinedClassVisitorResult =
        classSpecificationVisitorFactory.createCombinedClassVisitor(
            null, null, null, classVisitor, null, null, attributeVisitor, wildcardManager);

    // Assert
    assertTrue(
        classSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
            instanceof MultiClassPoolVisitor);
    assertTrue(actualCreateCombinedClassVisitorResult instanceof MultiClassVisitor);
    assertTrue(
        wildcardManager.createMatchedStringFunction("Expression")
            instanceof ConstantStringFunction);
    assertNull(null);
    assertNull(null);
    assertEquals(0, attributeVisitor.getCount());
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link ArrayList#ArrayList()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List,
   * List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager); when 'null'; then ArrayList() Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor ClassSpecificationVisitorFactory.createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateCombinedClassVisitor_whenNull_thenArrayListEmpty() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ArrayList<Object> methodSpecifications = new ArrayList<>();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateCombinedClassVisitorResult =
        classSpecificationVisitorFactory.createCombinedClassVisitor(
            null, null, methodSpecifications, null, null, null, null, wildcardManager);
    LibraryClass libraryClass = new LibraryClass();
    actualCreateCombinedClassVisitorResult.visitAnyClass(libraryClass);

    // Assert
    assertTrue(
        classSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
            instanceof MultiClassPoolVisitor);
    assertTrue(actualCreateCombinedClassVisitorResult instanceof MultiClassVisitor);
    assertTrue(
        wildcardManager.createMatchedStringFunction("Expression")
            instanceof ConstantStringFunction);
    assertNull(libraryClass.getProcessingInfo());
    assertNull(libraryClass.getName());
    assertNull(libraryClass.getSuperName());
    assertNull(libraryClass.getFeatureName());
    assertNull(libraryClass.interfaceNames);
    assertNull(null);
    assertNull(libraryClass.getSuperClass());
    assertNull(libraryClass.fields);
    assertNull(libraryClass.methods);
    assertNull(null);
    assertNull(libraryClass.kotlinMetadata);
    assertNull(null);
    assertNull(null);
    assertEquals(0, libraryClass.getAccessFlags());
    assertEquals(0, libraryClass.getInterfaceCount());
    assertEquals(0, libraryClass.getProcessingFlags());
    assertEquals(0, libraryClass.interfaceClasses.length);
    assertEquals(0, libraryClass.subClasses.length);
    assertEquals(0, libraryClass.subClassCount);
    assertTrue(methodSpecifications.isEmpty());
    assertTrue(libraryClass.getExtraFeatureNames().isEmpty());
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link ClassHierarchyTraveler}.
   * </ul>
   *
   * <p>Method under test: {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List,
   * List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager); when 'null'; then return ClassHierarchyTraveler")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor ClassSpecificationVisitorFactory.createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateCombinedClassVisitor_whenNull_thenReturnClassHierarchyTraveler() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateCombinedClassVisitorResult =
        classSpecificationVisitorFactory.createCombinedClassVisitor(
            null, null, null, null, null, null, attributeVisitor, wildcardManager);
    LibraryClass libraryClass = new LibraryClass();
    actualCreateCombinedClassVisitorResult.visitAnyClass(libraryClass);

    // Assert
    assertTrue(actualCreateCombinedClassVisitorResult instanceof ClassHierarchyTraveler);
    assertTrue(
        classSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
            instanceof MultiClassPoolVisitor);
    assertTrue(
        wildcardManager.createMatchedStringFunction("Expression")
            instanceof ConstantStringFunction);
    assertNull(libraryClass.getProcessingInfo());
    assertNull(libraryClass.getName());
    assertNull(libraryClass.getSuperName());
    assertNull(libraryClass.getFeatureName());
    assertNull(libraryClass.interfaceNames);
    assertNull(null);
    assertNull(libraryClass.getSuperClass());
    assertNull(libraryClass.fields);
    assertNull(libraryClass.methods);
    assertNull(libraryClass.kotlinMetadata);
    assertNull(null);
    assertNull(null);
    assertEquals(0, libraryClass.getAccessFlags());
    assertEquals(0, libraryClass.getInterfaceCount());
    assertEquals(0, attributeVisitor.getCount());
    assertEquals(0, libraryClass.getProcessingFlags());
    assertEquals(0, libraryClass.interfaceClasses.length);
    assertEquals(0, libraryClass.subClasses.length);
    assertEquals(0, libraryClass.subClassCount);
    assertTrue(libraryClass.getExtraFeatureNames().isEmpty());
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link ClassHierarchyTraveler}.
   * </ul>
   *
   * <p>Method under test: {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List,
   * List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager); when 'null'; then return ClassHierarchyTraveler")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor ClassSpecificationVisitorFactory.createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateCombinedClassVisitor_whenNull_thenReturnClassHierarchyTraveler2() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ArrayList<Object> methodSpecifications = new ArrayList<>();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateCombinedClassVisitorResult =
        classSpecificationVisitorFactory.createCombinedClassVisitor(
            null, null, methodSpecifications, null, null, null, attributeVisitor, wildcardManager);
    LibraryClass libraryClass = new LibraryClass();
    actualCreateCombinedClassVisitorResult.visitAnyClass(libraryClass);

    // Assert
    assertTrue(actualCreateCombinedClassVisitorResult instanceof ClassHierarchyTraveler);
    assertTrue(
        classSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
            instanceof MultiClassPoolVisitor);
    assertTrue(
        wildcardManager.createMatchedStringFunction("Expression")
            instanceof ConstantStringFunction);
    assertNull(libraryClass.getProcessingInfo());
    assertNull(libraryClass.getName());
    assertNull(libraryClass.getSuperName());
    assertNull(libraryClass.getFeatureName());
    assertNull(libraryClass.interfaceNames);
    assertNull(null);
    assertNull(libraryClass.getSuperClass());
    assertNull(libraryClass.fields);
    assertNull(libraryClass.methods);
    assertNull(libraryClass.kotlinMetadata);
    assertNull(null);
    assertNull(null);
    assertEquals(0, libraryClass.getAccessFlags());
    assertEquals(0, libraryClass.getInterfaceCount());
    assertEquals(0, attributeVisitor.getCount());
    assertEquals(0, libraryClass.getProcessingFlags());
    assertEquals(0, libraryClass.interfaceClasses.length);
    assertEquals(0, libraryClass.subClasses.length);
    assertEquals(0, libraryClass.subClassCount);
    assertTrue(methodSpecifications.isEmpty());
    assertTrue(libraryClass.getExtraFeatureNames().isEmpty());
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link ClassHierarchyTraveler}.
   * </ul>
   *
   * <p>Method under test: {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List,
   * List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager); when 'null'; then return ClassHierarchyTraveler")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor ClassSpecificationVisitorFactory.createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateCombinedClassVisitor_whenNull_thenReturnClassHierarchyTraveler3() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ArrayList<Object> methodSpecifications = new ArrayList<>();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateCombinedClassVisitorResult =
        classSpecificationVisitorFactory.createCombinedClassVisitor(
            null, null, methodSpecifications, null, null, methodVisitor, null, wildcardManager);
    LibraryClass libraryClass = new LibraryClass();
    actualCreateCombinedClassVisitorResult.visitAnyClass(libraryClass);

    // Assert
    assertTrue(actualCreateCombinedClassVisitorResult instanceof ClassHierarchyTraveler);
    assertTrue(
        classSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
            instanceof MultiClassPoolVisitor);
    assertTrue(
        wildcardManager.createMatchedStringFunction("Expression")
            instanceof ConstantStringFunction);
    assertNull(libraryClass.getProcessingInfo());
    assertNull(libraryClass.getName());
    assertNull(libraryClass.getSuperName());
    assertNull(libraryClass.getFeatureName());
    assertNull(libraryClass.interfaceNames);
    assertNull(null);
    assertNull(libraryClass.getSuperClass());
    assertNull(libraryClass.fields);
    assertNull(libraryClass.methods);
    assertNull(null);
    assertNull(libraryClass.kotlinMetadata);
    assertNull(null);
    assertNull(null);
    assertEquals(0, libraryClass.getAccessFlags());
    assertEquals(0, libraryClass.getInterfaceCount());
    assertEquals(0, methodVisitor.getCount());
    assertEquals(0, libraryClass.getProcessingFlags());
    assertEquals(0, libraryClass.interfaceClasses.length);
    assertEquals(0, libraryClass.subClasses.length);
    assertEquals(0, libraryClass.subClassCount);
    assertTrue(methodSpecifications.isEmpty());
    assertTrue(libraryClass.getExtraFeatureNames().isEmpty());
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List,
   * ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link MultiClassVisitor}.
   * </ul>
   *
   * <p>Method under test: {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List,
   * List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager); when 'null'; then return MultiClassVisitor")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor ClassSpecificationVisitorFactory.createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateCombinedClassVisitor_whenNull_thenReturnMultiClassVisitor() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateCombinedClassVisitorResult =
        classSpecificationVisitorFactory.createCombinedClassVisitor(
            null, null, null, null, null, null, null, wildcardManager);
    LibraryClass libraryClass = new LibraryClass();
    actualCreateCombinedClassVisitorResult.visitAnyClass(libraryClass);

    // Assert
    assertTrue(
        classSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
            instanceof MultiClassPoolVisitor);
    assertTrue(actualCreateCombinedClassVisitorResult instanceof MultiClassVisitor);
    assertTrue(
        wildcardManager.createMatchedStringFunction("Expression")
            instanceof ConstantStringFunction);
    assertNull(libraryClass.getProcessingInfo());
    assertNull(libraryClass.getName());
    assertNull(libraryClass.getSuperName());
    assertNull(libraryClass.getFeatureName());
    assertNull(libraryClass.interfaceNames);
    assertNull(null);
    assertNull(libraryClass.getSuperClass());
    assertNull(libraryClass.fields);
    assertNull(libraryClass.methods);
    assertNull(null);
    assertNull(libraryClass.kotlinMetadata);
    assertNull(null);
    assertNull(null);
    assertEquals(0, libraryClass.getAccessFlags());
    assertEquals(0, libraryClass.getInterfaceCount());
    assertEquals(0, libraryClass.getProcessingFlags());
    assertEquals(0, libraryClass.interfaceClasses.length);
    assertEquals(0, libraryClass.subClasses.length);
    assertEquals(0, libraryClass.subClassCount);
    assertTrue(libraryClass.getExtraFeatureNames().isEmpty());
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification,
   * boolean, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification, boolean,
   * MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor ClassSpecificationVisitorFactory.createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateNonTestingClassVisitor() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    MemberSpecification memberSpecification =
        new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor");

    KotlinAnnotationCounter memberVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateNonTestingClassVisitorResult =
        classSpecificationVisitorFactory.createNonTestingClassVisitor(
            memberSpecification, true, memberVisitor, attributeVisitor, wildcardManager);

    // Assert
    assertTrue(
        classSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
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
   * Test {@link ClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification,
   * boolean, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification, boolean,
   * MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor ClassSpecificationVisitorFactory.createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateNonTestingClassVisitor2() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    MemberSpecification memberSpecification =
        new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor");

    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateNonTestingClassVisitorResult =
        classSpecificationVisitorFactory.createNonTestingClassVisitor(
            memberSpecification, true, null, attributeVisitor, wildcardManager);
    LibraryClass libraryClass = new LibraryClass(1, "This Class Name", "Super Class Name");

    actualCreateNonTestingClassVisitorResult.visitAnyClass(libraryClass);

    // Assert
    assertTrue(
        classSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
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
   * Test {@link ClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification,
   * boolean, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification, boolean,
   * MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor ClassSpecificationVisitorFactory.createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateNonTestingClassVisitor3() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    MemberSpecification memberSpecification =
        new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor");

    KotlinAnnotationCounter memberVisitor = new KotlinAnnotationCounter();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateNonTestingClassVisitorResult =
        classSpecificationVisitorFactory.createNonTestingClassVisitor(
            memberSpecification, true, memberVisitor, null, wildcardManager);
    LibraryClass libraryClass = new LibraryClass(1, "This Class Name", "Super Class Name");

    actualCreateNonTestingClassVisitorResult.visitAnyClass(libraryClass);

    // Assert
    assertTrue(
        classSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
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

  /**
   * Test {@link ClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification,
   * boolean, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification, boolean,
   * MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor ClassSpecificationVisitorFactory.createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateNonTestingClassVisitor4() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    MemberSpecification memberSpecification =
        new MemberSpecification(0, 1, "Annotation Type", "Name", "Descriptor");

    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateNonTestingClassVisitorResult =
        classSpecificationVisitorFactory.createNonTestingClassVisitor(
            memberSpecification, true, null, attributeVisitor, wildcardManager);
    LibraryClass libraryClass = new LibraryClass(1, "This Class Name", "Super Class Name");

    actualCreateNonTestingClassVisitorResult.visitAnyClass(libraryClass);

    // Assert
    assertTrue(
        classSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
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
    assertEquals(0, memberSpecification.requiredSetAccessFlags);
    assertEquals(0, libraryClass.subClassCount);
    assertEquals(1, libraryClass.getAccessFlags());
    assertEquals(1, memberSpecification.requiredUnsetAccessFlags);
    assertTrue(libraryClass.getExtraFeatureNames().isEmpty());
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification,
   * boolean, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification, boolean,
   * MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor ClassSpecificationVisitorFactory.createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateNonTestingClassVisitor5() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    MemberSpecification memberSpecification =
        new MemberSpecification(1, 1, "", "Name", "Descriptor");

    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateNonTestingClassVisitorResult =
        classSpecificationVisitorFactory.createNonTestingClassVisitor(
            memberSpecification, true, null, attributeVisitor, wildcardManager);
    LibraryClass libraryClass = new LibraryClass(1, "This Class Name", "Super Class Name");

    actualCreateNonTestingClassVisitorResult.visitAnyClass(libraryClass);

    // Assert
    assertTrue(
        classSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
            instanceof MultiClassPoolVisitor);
    assertTrue(actualCreateNonTestingClassVisitorResult instanceof NamedFieldVisitor);
    assertTrue(
        wildcardManager.createMatchedStringFunction("Expression")
            instanceof ConstantStringFunction);
    assertEquals("", memberSpecification.annotationType);
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
   * Test {@link ClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification,
   * boolean, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <ul>
   *   <li>Then return {@link AllFieldVisitor}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification, boolean,
   * MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager); then return AllFieldVisitor")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor ClassSpecificationVisitorFactory.createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateNonTestingClassVisitor_thenReturnAllFieldVisitor() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    MemberSpecification memberSpecification = new MemberSpecification();
    KotlinAnnotationCounter memberVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateNonTestingClassVisitorResult =
        classSpecificationVisitorFactory.createNonTestingClassVisitor(
            memberSpecification, true, memberVisitor, attributeVisitor, wildcardManager);
    LibraryClass libraryClass = new LibraryClass(1, "This Class Name", "Super Class Name");

    actualCreateNonTestingClassVisitorResult.visitAnyClass(libraryClass);

    // Assert
    assertTrue(actualCreateNonTestingClassVisitorResult instanceof AllFieldVisitor);
    assertTrue(
        classSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
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
   * Test {@link ClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification,
   * boolean, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return {@link AllMethodVisitor}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification, boolean,
   * MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager); when 'false'; then return AllMethodVisitor")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor ClassSpecificationVisitorFactory.createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateNonTestingClassVisitor_whenFalse_thenReturnAllMethodVisitor() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    MemberSpecification memberSpecification = new MemberSpecification();
    KotlinAnnotationCounter memberVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateNonTestingClassVisitorResult =
        classSpecificationVisitorFactory.createNonTestingClassVisitor(
            memberSpecification, false, memberVisitor, attributeVisitor, wildcardManager);
    LibraryClass libraryClass = new LibraryClass(1, "This Class Name", "Super Class Name");

    actualCreateNonTestingClassVisitorResult.visitAnyClass(libraryClass);

    // Assert
    assertTrue(actualCreateNonTestingClassVisitorResult instanceof AllMethodVisitor);
    assertTrue(
        classSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
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
   * Test {@link ClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification,
   * boolean, MemberVisitor, AttributeVisitor, WildcardManager)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return {@link NamedMethodVisitor}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createNonTestingClassVisitor(MemberSpecification, boolean,
   * MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager); when 'false'; then return NamedMethodVisitor")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassVisitor ClassSpecificationVisitorFactory.createNonTestingClassVisitor(MemberSpecification, boolean, MemberVisitor, AttributeVisitor, WildcardManager)"
  })
  void testCreateNonTestingClassVisitor_whenFalse_thenReturnNamedMethodVisitor() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    MemberSpecification memberSpecification =
        new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor");

    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();
    WildcardManager wildcardManager = new WildcardManager();

    // Act
    ClassVisitor actualCreateNonTestingClassVisitorResult =
        classSpecificationVisitorFactory.createNonTestingClassVisitor(
            memberSpecification, false, null, attributeVisitor, wildcardManager);
    LibraryClass libraryClass = new LibraryClass(1, "This Class Name", "Super Class Name");

    actualCreateNonTestingClassVisitorResult.visitAnyClass(libraryClass);

    // Assert
    assertTrue(
        classSpecificationVisitorFactory.createClassPoolVisitor(null, null, null)
            instanceof MultiClassPoolVisitor);
    assertTrue(actualCreateNonTestingClassVisitorResult instanceof NamedMethodVisitor);
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
   * Test {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification,
   * ClassPoolVisitor, WildcardManager)} with {@code classSpecification}, {@code classPoolVisitor},
   * {@code wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassPoolVisitor,
   * WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager) with 'classSpecification', 'classPoolVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager)"
  })
  void testCreateClassTesterWithClassSpecificationClassPoolVisitorWildcardManager() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name");

    ClassPoolVisitor classPoolVisitor = mock(ClassPoolVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassTesterResult =
        classSpecificationVisitorFactory.createClassTester(
            classSpecification, classPoolVisitor, new WildcardManager());
    actualCreateClassTesterResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassTesterResult instanceof MultiClassPoolVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification,
   * ClassPoolVisitor, WildcardManager)} with {@code classSpecification}, {@code classPoolVisitor},
   * {@code wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassPoolVisitor,
   * WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager) with 'classSpecification', 'classPoolVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager)"
  })
  void testCreateClassTesterWithClassSpecificationClassPoolVisitorWildcardManager2() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            0,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name");

    ClassPoolVisitor classPoolVisitor = mock(ClassPoolVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassTesterResult =
        classSpecificationVisitorFactory.createClassTester(
            classSpecification, classPoolVisitor, new WildcardManager());
    actualCreateClassTesterResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassTesterResult instanceof MultiClassPoolVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification,
   * ClassPoolVisitor, WildcardManager)} with {@code classSpecification}, {@code classPoolVisitor},
   * {@code wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassPoolVisitor,
   * WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager) with 'classSpecification', 'classPoolVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager)"
  })
  void testCreateClassTesterWithClassSpecificationClassPoolVisitorWildcardManager3() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments", 1, 1, "", "Class Name", "Extends Annotation Type", "Extends Class Name");

    ClassPoolVisitor classPoolVisitor = mock(ClassPoolVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassTesterResult =
        classSpecificationVisitorFactory.createClassTester(
            classSpecification, classPoolVisitor, new WildcardManager());
    actualCreateClassTesterResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassTesterResult instanceof MultiClassPoolVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification,
   * ClassPoolVisitor, WildcardManager)} with {@code classSpecification}, {@code classPoolVisitor},
   * {@code wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassPoolVisitor,
   * WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager) with 'classSpecification', 'classPoolVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager)"
  })
  void testCreateClassTesterWithClassSpecificationClassPoolVisitorWildcardManager4() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ClassSpecification classSpecification = new ClassSpecification();
    ClassPoolVisitor classPoolVisitor = mock(ClassPoolVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassTesterResult =
        classSpecificationVisitorFactory.createClassTester(
            classSpecification, classPoolVisitor, new WildcardManager());
    actualCreateClassTesterResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassTesterResult instanceof MultiClassPoolVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification,
   * ClassPoolVisitor, WildcardManager)} with {@code classSpecification}, {@code classPoolVisitor},
   * {@code wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassPoolVisitor,
   * WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager) with 'classSpecification', 'classPoolVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager)"
  })
  void testCreateClassTesterWithClassSpecificationClassPoolVisitorWildcardManager5() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ArrayList<Object> fieldSpecifications = new ArrayList<>();
    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name",
            fieldSpecifications,
            new ArrayList<>());

    ClassPoolVisitor classPoolVisitor = mock(ClassPoolVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassTesterResult =
        classSpecificationVisitorFactory.createClassTester(
            classSpecification, classPoolVisitor, new WildcardManager());
    actualCreateClassTesterResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassTesterResult instanceof MultiClassPoolVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification,
   * ClassPoolVisitor, WildcardManager)} with {@code classSpecification}, {@code classPoolVisitor},
   * {@code wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassPoolVisitor,
   * WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager) with 'classSpecification', 'classPoolVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager)"
  })
  void testCreateClassTesterWithClassSpecificationClassPoolVisitorWildcardManager6() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();

    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name");
    classSpecification.addField(
        new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor"));
    ClassPoolVisitor classPoolVisitor = mock(ClassPoolVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassTesterResult =
        classSpecificationVisitorFactory.createClassTester(
            classSpecification, classPoolVisitor, new WildcardManager());
    actualCreateClassTesterResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassTesterResult instanceof MultiClassPoolVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification,
   * ClassPoolVisitor, WildcardManager)} with {@code classSpecification}, {@code classPoolVisitor},
   * {@code wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassPoolVisitor,
   * WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager) with 'classSpecification', 'classPoolVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager)"
  })
  void testCreateClassTesterWithClassSpecificationClassPoolVisitorWildcardManager7() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();

    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name");
    classSpecification.addMethod(
        new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor"));
    classSpecification.addField(
        new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor"));
    ClassPoolVisitor classPoolVisitor = mock(ClassPoolVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassTesterResult =
        classSpecificationVisitorFactory.createClassTester(
            classSpecification, classPoolVisitor, new WildcardManager());
    actualCreateClassTesterResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassTesterResult instanceof MultiClassPoolVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification,
   * ClassPoolVisitor, WildcardManager)} with {@code classSpecification}, {@code classPoolVisitor},
   * {@code wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassPoolVisitor,
   * WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager) with 'classSpecification', 'classPoolVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager)"
  })
  void testCreateClassTesterWithClassSpecificationClassPoolVisitorWildcardManager8() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();

    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name");
    classSpecification.addField(
        new MemberSpecification(0, 1, "Annotation Type", "Name", "Descriptor"));
    ClassPoolVisitor classPoolVisitor = mock(ClassPoolVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassTesterResult =
        classSpecificationVisitorFactory.createClassTester(
            classSpecification, classPoolVisitor, new WildcardManager());
    actualCreateClassTesterResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassTesterResult instanceof MultiClassPoolVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification,
   * ClassPoolVisitor, WildcardManager)} with {@code classSpecification}, {@code classPoolVisitor},
   * {@code wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassPoolVisitor,
   * WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager) with 'classSpecification', 'classPoolVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager)"
  })
  void testCreateClassTesterWithClassSpecificationClassPoolVisitorWildcardManager9() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();

    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name");
    classSpecification.addField(new MemberSpecification());
    ClassPoolVisitor classPoolVisitor = mock(ClassPoolVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassTesterResult =
        classSpecificationVisitorFactory.createClassTester(
            classSpecification, classPoolVisitor, new WildcardManager());
    actualCreateClassTesterResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassTesterResult instanceof MultiClassPoolVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification,
   * ClassPoolVisitor, WildcardManager)} with {@code classSpecification}, {@code classPoolVisitor},
   * {@code wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassPoolVisitor,
   * WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager) with 'classSpecification', 'classPoolVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager)"
  })
  void testCreateClassTesterWithClassSpecificationClassPoolVisitorWildcardManager10() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();

    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name");
    classSpecification.addMethod(new MemberSpecification());
    classSpecification.addField(
        new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor"));
    ClassPoolVisitor classPoolVisitor = mock(ClassPoolVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassTesterResult =
        classSpecificationVisitorFactory.createClassTester(
            classSpecification, classPoolVisitor, new WildcardManager());
    actualCreateClassTesterResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassTesterResult instanceof MultiClassPoolVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification,
   * ClassPoolVisitor, WildcardManager)} with {@code classSpecification}, {@code classPoolVisitor},
   * {@code wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassPoolVisitor,
   * WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager) with 'classSpecification', 'classPoolVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager)"
  })
  void testCreateClassTesterWithClassSpecificationClassPoolVisitorWildcardManager11() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ClassSpecification classSpecification = new ClassSpecification();
    ClassPoolVisitor classPoolVisitor = mock(ClassPoolVisitor.class);
    doNothing().when(classPoolVisitor).visitClassPool(Mockito.<ClassPool>any());

    // Act
    ClassPoolVisitor actualCreateClassTesterResult =
        classSpecificationVisitorFactory.createClassTester(
            classSpecification, classPoolVisitor, new WildcardManager());
    ClassPool classPool = new ClassPool();
    classPool.addClass("Name", new LibraryClass(1, "This Class Name", "Super Class Name"));
    actualCreateClassTesterResult.visitClassPool(classPool);

    // Assert
    verify(classPoolVisitor).visitClassPool(isA(ClassPool.class));
    assertTrue(actualCreateClassTesterResult instanceof MultiClassPoolVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification,
   * ClassVisitor, WildcardManager)} with {@code classSpecification}, {@code classVisitor}, {@code
   * wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassVisitor,
   * WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassTester(ClassSpecification, ClassVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassTester(ClassSpecification, ClassVisitor, WildcardManager)"
  })
  void testCreateClassTesterWithClassSpecificationClassVisitorWildcardManager() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name");

    ClassVisitor classVisitor = mock(ClassVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassTesterResult =
        classSpecificationVisitorFactory.createClassTester(
            classSpecification, classVisitor, new WildcardManager());
    actualCreateClassTesterResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassTesterResult instanceof NamedClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification,
   * ClassVisitor, WildcardManager)} with {@code classSpecification}, {@code classVisitor}, {@code
   * wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassVisitor,
   * WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassTester(ClassSpecification, ClassVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassTester(ClassSpecification, ClassVisitor, WildcardManager)"
  })
  void testCreateClassTesterWithClassSpecificationClassVisitorWildcardManager2() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            0,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name");

    ClassVisitor classVisitor = mock(ClassVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassTesterResult =
        classSpecificationVisitorFactory.createClassTester(
            classSpecification, classVisitor, new WildcardManager());
    actualCreateClassTesterResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassTesterResult instanceof NamedClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification,
   * ClassVisitor, WildcardManager)} with {@code classSpecification}, {@code classVisitor}, {@code
   * wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassVisitor,
   * WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassTester(ClassSpecification, ClassVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassTester(ClassSpecification, ClassVisitor, WildcardManager)"
  })
  void testCreateClassTesterWithClassSpecificationClassVisitorWildcardManager3() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments", 1, 1, "", "Class Name", "Extends Annotation Type", "Extends Class Name");

    ClassVisitor classVisitor = mock(ClassVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassTesterResult =
        classSpecificationVisitorFactory.createClassTester(
            classSpecification, classVisitor, new WildcardManager());
    actualCreateClassTesterResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassTesterResult instanceof NamedClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification,
   * ClassVisitor, WildcardManager)} with {@code classSpecification}, {@code classVisitor}, {@code
   * wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassVisitor,
   * WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassTester(ClassSpecification, ClassVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassTester(ClassSpecification, ClassVisitor, WildcardManager)"
  })
  void testCreateClassTesterWithClassSpecificationClassVisitorWildcardManager4() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ClassSpecification classSpecification = new ClassSpecification();
    ClassVisitor classVisitor = mock(ClassVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassTesterResult =
        classSpecificationVisitorFactory.createClassTester(
            classSpecification, classVisitor, new WildcardManager());
    actualCreateClassTesterResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassTesterResult instanceof AllClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification,
   * ClassVisitor, WildcardManager)} with {@code classSpecification}, {@code classVisitor}, {@code
   * wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassVisitor,
   * WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassTester(ClassSpecification, ClassVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassTester(ClassSpecification, ClassVisitor, WildcardManager)"
  })
  void testCreateClassTesterWithClassSpecificationClassVisitorWildcardManager5() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ArrayList<Object> fieldSpecifications = new ArrayList<>();
    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name",
            fieldSpecifications,
            new ArrayList<>());

    ClassVisitor classVisitor = mock(ClassVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassTesterResult =
        classSpecificationVisitorFactory.createClassTester(
            classSpecification, classVisitor, new WildcardManager());
    actualCreateClassTesterResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassTesterResult instanceof NamedClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification,
   * ClassVisitor, WildcardManager)} with {@code classSpecification}, {@code classVisitor}, {@code
   * wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassVisitor,
   * WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassTester(ClassSpecification, ClassVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassTester(ClassSpecification, ClassVisitor, WildcardManager)"
  })
  void testCreateClassTesterWithClassSpecificationClassVisitorWildcardManager6() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();

    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name");
    classSpecification.addField(
        new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor"));
    ClassVisitor classVisitor = mock(ClassVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassTesterResult =
        classSpecificationVisitorFactory.createClassTester(
            classSpecification, classVisitor, new WildcardManager());
    actualCreateClassTesterResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassTesterResult instanceof NamedClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification,
   * ClassVisitor, WildcardManager)} with {@code classSpecification}, {@code classVisitor}, {@code
   * wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassVisitor,
   * WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassTester(ClassSpecification, ClassVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassTester(ClassSpecification, ClassVisitor, WildcardManager)"
  })
  void testCreateClassTesterWithClassSpecificationClassVisitorWildcardManager7() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();

    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name");
    classSpecification.addMethod(
        new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor"));
    classSpecification.addField(
        new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor"));
    ClassVisitor classVisitor = mock(ClassVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassTesterResult =
        classSpecificationVisitorFactory.createClassTester(
            classSpecification, classVisitor, new WildcardManager());
    actualCreateClassTesterResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassTesterResult instanceof NamedClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification,
   * ClassVisitor, WildcardManager)} with {@code classSpecification}, {@code classVisitor}, {@code
   * wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassVisitor,
   * WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassTester(ClassSpecification, ClassVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassTester(ClassSpecification, ClassVisitor, WildcardManager)"
  })
  void testCreateClassTesterWithClassSpecificationClassVisitorWildcardManager8() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();

    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name");
    classSpecification.addField(
        new MemberSpecification(0, 1, "Annotation Type", "Name", "Descriptor"));
    ClassVisitor classVisitor = mock(ClassVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassTesterResult =
        classSpecificationVisitorFactory.createClassTester(
            classSpecification, classVisitor, new WildcardManager());
    actualCreateClassTesterResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassTesterResult instanceof NamedClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification,
   * ClassVisitor, WildcardManager)} with {@code classSpecification}, {@code classVisitor}, {@code
   * wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassVisitor,
   * WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassTester(ClassSpecification, ClassVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassTester(ClassSpecification, ClassVisitor, WildcardManager)"
  })
  void testCreateClassTesterWithClassSpecificationClassVisitorWildcardManager9() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();

    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name");
    classSpecification.addField(new MemberSpecification());
    ClassVisitor classVisitor = mock(ClassVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassTesterResult =
        classSpecificationVisitorFactory.createClassTester(
            classSpecification, classVisitor, new WildcardManager());
    actualCreateClassTesterResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassTesterResult instanceof NamedClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification,
   * ClassVisitor, WildcardManager)} with {@code classSpecification}, {@code classVisitor}, {@code
   * wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassVisitor,
   * WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassTester(ClassSpecification, ClassVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassTester(ClassSpecification, ClassVisitor, WildcardManager)"
  })
  void testCreateClassTesterWithClassSpecificationClassVisitorWildcardManager10() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();

    ClassSpecification classSpecification =
        new ClassSpecification(
            "Comments",
            1,
            1,
            "Annotation Type",
            "Class Name",
            "Extends Annotation Type",
            "Extends Class Name");
    classSpecification.addMethod(new MemberSpecification());
    classSpecification.addField(
        new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor"));
    ClassVisitor classVisitor = mock(ClassVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassTesterResult =
        classSpecificationVisitorFactory.createClassTester(
            classSpecification, classVisitor, new WildcardManager());
    actualCreateClassTesterResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassTesterResult instanceof NamedClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification,
   * ClassVisitor, WildcardManager)} with {@code classSpecification}, {@code classVisitor}, {@code
   * wildcardManager}.
   *
   * <p>Method under test: {@link
   * ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassVisitor,
   * WildcardManager)}
   */
  @Test
  @DisplayName(
      "Test createClassTester(ClassSpecification, ClassVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'wildcardManager'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ClassPoolVisitor ClassSpecificationVisitorFactory.createClassTester(ClassSpecification, ClassVisitor, WildcardManager)"
  })
  void testCreateClassTesterWithClassSpecificationClassVisitorWildcardManager11() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory =
        new ClassSpecificationVisitorFactory();
    ClassSpecification classSpecification = new ClassSpecification();
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doNothing().when(classVisitor).visitLibraryClass(Mockito.<LibraryClass>any());

    // Act
    ClassPoolVisitor actualCreateClassTesterResult =
        classSpecificationVisitorFactory.createClassTester(
            classSpecification, classVisitor, new WildcardManager());
    ClassPool classPool = new ClassPool();
    classPool.addClass("Name", new LibraryClass(1, "This Class Name", "Super Class Name"));
    actualCreateClassTesterResult.visitClassPool(classPool);

    // Assert
    verify(classVisitor).visitLibraryClass(isA(LibraryClass.class));
    assertTrue(actualCreateClassTesterResult instanceof AllClassVisitor);
  }
}
