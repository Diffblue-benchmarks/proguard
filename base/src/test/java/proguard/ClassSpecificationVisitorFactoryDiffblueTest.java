package proguard;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
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
   * Test {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)} with {@code classSpecification}, {@code classVisitor}, {@code fieldVisitor}, {@code methodVisitor}, {@code attributeVisitor}, {@code wildcardManager}.
   * <p>
   * Method under test: {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName("Test createClassPoolVisitor(ClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'fieldVisitor', 'methodVisitor', 'attributeVisitor', 'wildcardManager'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.classfile.visitor.ClassPoolVisitor proguard.ClassSpecificationVisitorFactory.createClassPoolVisitor(proguard.ClassSpecification, proguard.classfile.visitor.ClassVisitor, proguard.classfile.visitor.MemberVisitor, proguard.classfile.visitor.MemberVisitor, proguard.classfile.attribute.visitor.AttributeVisitor, proguard.util.WildcardManager)"})
  void testCreateClassPoolVisitorWithClassSpecificationClassVisitorFieldVisitorMethodVisitorAttributeVisitorWildcardManager() {
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

    // Assert
    assertTrue(actualCreateClassPoolVisitorResult instanceof NamedClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor)} with {@code classSpecifications}, {@code classVisitor}, {@code fieldVisitor}, {@code methodVisitor}, {@code attributeVisitor}.
   * <p>
   * Method under test: {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor)}
   */
  @Test
  @DisplayName("Test createClassPoolVisitor(List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor) with 'classSpecifications', 'classVisitor', 'fieldVisitor', 'methodVisitor', 'attributeVisitor'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.classfile.visitor.ClassPoolVisitor proguard.ClassSpecificationVisitorFactory.createClassPoolVisitor(java.util.List, proguard.classfile.visitor.ClassVisitor, proguard.classfile.visitor.MemberVisitor, proguard.classfile.visitor.MemberVisitor, proguard.classfile.attribute.visitor.AttributeVisitor)"})
  void testCreateClassPoolVisitorWithClassSpecificationsClassVisitorFieldVisitorMethodVisitorAttributeVisitor() {
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

    // Assert
    assertTrue(actualCreateClassPoolVisitorResult instanceof MultiClassPoolVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(List, ClassVisitor, MemberVisitor)} with {@code classSpecifications}, {@code classVisitor}, {@code memberVisitor}.
   * <p>
   * Method under test: {@link ClassSpecificationVisitorFactory#createClassPoolVisitor(List, ClassVisitor, MemberVisitor)}
   */
  @Test
  @DisplayName("Test createClassPoolVisitor(List, ClassVisitor, MemberVisitor) with 'classSpecifications', 'classVisitor', 'memberVisitor'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.classfile.visitor.ClassPoolVisitor proguard.ClassSpecificationVisitorFactory.createClassPoolVisitor(java.util.List, proguard.classfile.visitor.ClassVisitor, proguard.classfile.visitor.MemberVisitor)"})
  void testCreateClassPoolVisitorWithClassSpecificationsClassVisitorMemberVisitor() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory = new ClassSpecificationVisitorFactory();
    ArrayList<Object> classSpecifications = new ArrayList<>();
    ClassVisitor classVisitor = mock(ClassVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassPoolVisitorResult = classSpecificationVisitorFactory
        .createClassPoolVisitor(classSpecifications, classVisitor, new KotlinAnnotationCounter());
    actualCreateClassPoolVisitorResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassPoolVisitorResult instanceof MultiClassPoolVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>Then calls {@link ClassVisitor#visitLibraryClass(LibraryClass)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName("Test createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager); given '42'; then calls visitLibraryClass(LibraryClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.classfile.visitor.ClassVisitor proguard.ClassSpecificationVisitorFactory.createCombinedClassVisitor(java.util.List, java.util.List, java.util.List, proguard.classfile.visitor.ClassVisitor, proguard.classfile.visitor.MemberVisitor, proguard.classfile.visitor.MemberVisitor, proguard.classfile.attribute.visitor.AttributeVisitor, proguard.util.WildcardManager)"})
  void testCreateCombinedClassVisitor_given42_thenCallsVisitLibraryClass() {
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
   * Test {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}.
   * <ul>
   *   <li>Given {@code 42}.</li>
   *   <li>Then calls {@link ClassVisitor#visitLibraryClass(LibraryClass)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName("Test createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager); given '42'; then calls visitLibraryClass(LibraryClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.classfile.visitor.ClassVisitor proguard.ClassSpecificationVisitorFactory.createCombinedClassVisitor(java.util.List, java.util.List, java.util.List, proguard.classfile.visitor.ClassVisitor, proguard.classfile.visitor.MemberVisitor, proguard.classfile.visitor.MemberVisitor, proguard.classfile.attribute.visitor.AttributeVisitor, proguard.util.WildcardManager)"})
  void testCreateCombinedClassVisitor_given42_thenCallsVisitLibraryClass2() {
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
   * Test {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}.
   * <ul>
   *   <li>Then calls {@link ClassVisitor#visitLibraryClass(LibraryClass)}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName("Test createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager); then calls visitLibraryClass(LibraryClass)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.classfile.visitor.ClassVisitor proguard.ClassSpecificationVisitorFactory.createCombinedClassVisitor(java.util.List, java.util.List, java.util.List, proguard.classfile.visitor.ClassVisitor, proguard.classfile.visitor.MemberVisitor, proguard.classfile.visitor.MemberVisitor, proguard.classfile.attribute.visitor.AttributeVisitor, proguard.util.WildcardManager)"})
  void testCreateCombinedClassVisitor_thenCallsVisitLibraryClass() {
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
   * Test {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@link MultiClassVisitor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName("Test createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager); when 'null'; then return MultiClassVisitor")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.classfile.visitor.ClassVisitor proguard.ClassSpecificationVisitorFactory.createCombinedClassVisitor(java.util.List, java.util.List, java.util.List, proguard.classfile.visitor.ClassVisitor, proguard.classfile.visitor.MemberVisitor, proguard.classfile.visitor.MemberVisitor, proguard.classfile.attribute.visitor.AttributeVisitor, proguard.util.WildcardManager)"})
  void testCreateCombinedClassVisitor_whenNull_thenReturnMultiClassVisitor() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory = new ClassSpecificationVisitorFactory();

    // Act
    ClassVisitor actualCreateCombinedClassVisitorResult = classSpecificationVisitorFactory
        .createCombinedClassVisitor(null, null, null, null, null, null, null, new WildcardManager());
    actualCreateCombinedClassVisitorResult.visitAnyClass(new LibraryClass());

    // Assert
    assertTrue(actualCreateCombinedClassVisitorResult instanceof MultiClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}.
   * <ul>
   *   <li>When {@code null}.</li>
   *   <li>Then return {@link MultiClassVisitor}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassSpecificationVisitorFactory#createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager)}
   */
  @Test
  @DisplayName("Test createCombinedClassVisitor(List, List, List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor, WildcardManager); when 'null'; then return MultiClassVisitor")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.classfile.visitor.ClassVisitor proguard.ClassSpecificationVisitorFactory.createCombinedClassVisitor(java.util.List, java.util.List, java.util.List, proguard.classfile.visitor.ClassVisitor, proguard.classfile.visitor.MemberVisitor, proguard.classfile.visitor.MemberVisitor, proguard.classfile.attribute.visitor.AttributeVisitor, proguard.util.WildcardManager)"})
  void testCreateCombinedClassVisitor_whenNull_thenReturnMultiClassVisitor2() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory = new ClassSpecificationVisitorFactory();
    ArrayList<Object> methodSpecifications = new ArrayList<>();

    // Act
    ClassVisitor actualCreateCombinedClassVisitorResult = classSpecificationVisitorFactory
        .createCombinedClassVisitor(null, null, methodSpecifications, null, null, null, null, new WildcardManager());
    actualCreateCombinedClassVisitorResult.visitAnyClass(new LibraryClass());

    // Assert
    assertTrue(actualCreateCombinedClassVisitorResult instanceof MultiClassVisitor);
  }

  /**
   * Test {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager)} with {@code classSpecification}, {@code classPoolVisitor}, {@code wildcardManager}.
   * <p>
   * Method under test: {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager)}
   */
  @Test
  @DisplayName("Test createClassTester(ClassSpecification, ClassPoolVisitor, WildcardManager) with 'classSpecification', 'classPoolVisitor', 'wildcardManager'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.classfile.visitor.ClassPoolVisitor proguard.ClassSpecificationVisitorFactory.createClassTester(proguard.ClassSpecification, proguard.classfile.visitor.ClassPoolVisitor, proguard.util.WildcardManager)"})
  void testCreateClassTesterWithClassSpecificationClassPoolVisitorWildcardManager() {
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
   * Test {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassVisitor, WildcardManager)} with {@code classSpecification}, {@code classVisitor}, {@code wildcardManager}.
   * <p>
   * Method under test: {@link ClassSpecificationVisitorFactory#createClassTester(ClassSpecification, ClassVisitor, WildcardManager)}
   */
  @Test
  @DisplayName("Test createClassTester(ClassSpecification, ClassVisitor, WildcardManager) with 'classSpecification', 'classVisitor', 'wildcardManager'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.classfile.visitor.ClassPoolVisitor proguard.ClassSpecificationVisitorFactory.createClassTester(proguard.ClassSpecification, proguard.classfile.visitor.ClassVisitor, proguard.util.WildcardManager)"})
  void testCreateClassTesterWithClassSpecificationClassVisitorWildcardManager() {
    // Arrange
    ClassSpecificationVisitorFactory classSpecificationVisitorFactory = new ClassSpecificationVisitorFactory();
    ClassSpecification classSpecification = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    ClassVisitor classVisitor = mock(ClassVisitor.class);

    // Act
    ClassPoolVisitor actualCreateClassTesterResult = classSpecificationVisitorFactory
        .createClassTester(classSpecification, classVisitor, new WildcardManager());
    actualCreateClassTesterResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassTesterResult instanceof NamedClassVisitor);
  }
}
