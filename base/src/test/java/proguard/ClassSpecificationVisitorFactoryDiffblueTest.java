package proguard;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import proguard.classfile.ClassPool;
import proguard.classfile.LibraryClass;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.visitor.ClassPoolVisitor;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.MemberVisitor;
import proguard.classfile.visitor.MultiClassPoolVisitor;
import proguard.classfile.visitor.MultiClassVisitor;
import proguard.classfile.visitor.NamedClassVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;
import proguard.util.WildcardManager;

class ClassSpecificationVisitorFactoryDiffblueTest {
  /**
   * Method under test:
   * {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(List, ClassVisitor, MemberVisitor)}
   */
  @Test
  void testCreateClassPoolVisitor() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory = new ClassSpecificationVisitorFactory();
    ArrayList<Object> classSpecifications = new ArrayList<>();
    ClassVisitor classVisitor = mock(ClassVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassPoolVisitorResult = classSpecificationVisitorFactory
        .createClassPoolVisitor(classSpecifications, classVisitor, new KotlinAnnotationCounter());
    actualCreateClassPoolVisitorResult.visitClassPool(new ClassPool());

    // Assert that nothing has changed
    assertTrue(actualCreateClassPoolVisitorResult instanceof MultiClassPoolVisitor);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor)}
   */
  @Test
  void testCreateClassPoolVisitor2() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory = new ClassSpecificationVisitorFactory();
    ArrayList<Object> classSpecifications = new ArrayList<>();
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    KotlinAnnotationCounter fieldVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();

    // Act
    ClassPoolVisitor actualCreateClassPoolVisitorResult = classSpecificationVisitorFactory.createClassPoolVisitor(
        classSpecifications, classVisitor, fieldVisitor, methodVisitor, new KotlinAnnotationCounter());
    actualCreateClassPoolVisitorResult.visitClassPool(new ClassPool());

    // Assert that nothing has changed
    assertTrue(actualCreateClassPoolVisitorResult instanceof MultiClassPoolVisitor);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  void testCreateClassPoolVisitor3() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory = new ClassSpecificationVisitorFactory();
    ClassSpecification classSpecification = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    ClassVisitor classVisitor = mock(ClassVisitor.class);
    KotlinAnnotationCounter fieldVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();

    // Act
    ClassPoolVisitor actualCreateClassPoolVisitorResult = classSpecificationVisitorFactory.createClassPoolVisitor(
        classSpecification, classVisitor, fieldVisitor, methodVisitor, attributeVisitor, new WildcardManager());
    actualCreateClassPoolVisitorResult.visitClassPool(new ClassPool());

    // Assert that nothing has changed
    assertTrue(actualCreateClassPoolVisitorResult instanceof NamedClassVisitor);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  void testCreateCombinedClassVisitor() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory = new ClassSpecificationVisitorFactory();

    // Act
    ClassVisitor actualCreateCombinedClassVisitorResult = classSpecificationVisitorFactory
        .createCombinedClassVisitor(null, null, null, null, null, null, null, new WildcardManager());
    actualCreateCombinedClassVisitorResult.visitAnyClass(new LibraryClass());

    // Assert that nothing has changed
    assertTrue(actualCreateCombinedClassVisitorResult instanceof MultiClassVisitor);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  void testCreateCombinedClassVisitor2() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory = new ClassSpecificationVisitorFactory();
    ArrayList<Object> methodSpecifications = new ArrayList<>();

    // Act
    ClassVisitor actualCreateCombinedClassVisitorResult = classSpecificationVisitorFactory
        .createCombinedClassVisitor(null, null, methodSpecifications, null, null, null, null, new WildcardManager());
    actualCreateCombinedClassVisitorResult.visitAnyClass(new LibraryClass());

    // Assert that nothing has changed
    assertTrue(actualCreateCombinedClassVisitorResult instanceof MultiClassVisitor);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  void testCreateCombinedClassVisitor3() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory = new ClassSpecificationVisitorFactory();
    ArrayList<Object> attributeNames = new ArrayList<>();
    ArrayList<Object> fieldSpecifications = new ArrayList<>();
    ArrayList<Object> methodSpecifications = new ArrayList<>();
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doNothing().when(classVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    KotlinAnnotationCounter fieldVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();

    // Act
    ClassVisitor actualCreateCombinedClassVisitorResult = classSpecificationVisitorFactory.createCombinedClassVisitor(
        attributeNames, fieldSpecifications, methodSpecifications, classVisitor, fieldVisitor, methodVisitor,
        attributeVisitor, new WildcardManager());
    actualCreateCombinedClassVisitorResult.visitAnyClass(new LibraryClass());

    // Assert
    verify(classVisitor).visitLibraryClass(isA(LibraryClass.class));
    assertTrue(actualCreateCombinedClassVisitorResult instanceof MultiClassVisitor);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  void testCreateCombinedClassVisitor4() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory = new ClassSpecificationVisitorFactory();

    ArrayList<Object> attributeNames = new ArrayList<>();
    attributeNames.add("42");
    ArrayList<Object> fieldSpecifications = new ArrayList<>();
    ArrayList<Object> methodSpecifications = new ArrayList<>();
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    doNothing().when(classVisitor).visitLibraryClass(Mockito.<LibraryClass>any());
    KotlinAnnotationCounter fieldVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter attributeVisitor = new KotlinAnnotationCounter();

    // Act
    ClassVisitor actualCreateCombinedClassVisitorResult = classSpecificationVisitorFactory.createCombinedClassVisitor(
        attributeNames, fieldSpecifications, methodSpecifications, classVisitor, fieldVisitor, methodVisitor,
        attributeVisitor, new WildcardManager());
    actualCreateCombinedClassVisitorResult.visitAnyClass(new LibraryClass());

    // Assert
    verify(classVisitor).visitLibraryClass(isA(LibraryClass.class));
    assertTrue(actualCreateCombinedClassVisitorResult instanceof MultiClassVisitor);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  void testCreateCombinedClassVisitor5() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory = new ClassSpecificationVisitorFactory();

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

    // Act
    ClassVisitor actualCreateCombinedClassVisitorResult = classSpecificationVisitorFactory.createCombinedClassVisitor(
        attributeNames, fieldSpecifications, methodSpecifications, classVisitor, fieldVisitor, methodVisitor,
        attributeVisitor, new WildcardManager());
    actualCreateCombinedClassVisitorResult.visitAnyClass(new LibraryClass());

    // Assert
    verify(classVisitor).visitLibraryClass(isA(LibraryClass.class));
    assertTrue(actualCreateCombinedClassVisitorResult instanceof MultiClassVisitor);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager)}
   */
  @Test
  void testCreateClassTester() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory = new ClassSpecificationVisitorFactory();
    ClassSpecification classSpecification = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    ClassPoolVisitor classPoolVisitor = mock(ClassPoolVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassTesterResult = classSpecificationVisitorFactory
        .createClassTester(classSpecification, classPoolVisitor, new WildcardManager());
    actualCreateClassTesterResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassTesterResult instanceof MultiClassPoolVisitor);
  }

  /**
   * Method under test:
   * {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassVisitor, WildcardManager)}
   */
  @Test
  void testCreateClassTester2() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory = new ClassSpecificationVisitorFactory();
    ClassSpecification classSpecification = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    ClassVisitor classVisitor = mock(ClassVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassTesterResult = classSpecificationVisitorFactory
        .createClassTester(classSpecification, classVisitor, new WildcardManager());
    actualCreateClassTesterResult.visitClassPool(new ClassPool());

    // Assert that nothing has changed
    assertTrue(actualCreateClassTesterResult instanceof NamedClassVisitor);
  }
}
