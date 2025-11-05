package proguard;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import proguard.classfile.ClassPool;
import proguard.classfile.attribute.visitor.AttributeVisitor;
import proguard.classfile.visitor.ClassPoolVisitor;
import proguard.classfile.visitor.ClassVisitor;
import proguard.classfile.visitor.MemberVisitor;
import proguard.classfile.visitor.MultiClassPoolVisitor;
import proguard.fixer.kotlin.KotlinAnnotationCounter;

class KeepClassSpecificationVisitorFactoryDiffblueTest {
  /**
   * Method under test:
   * {@link KeepClassSpecificationVisitorFactory#createClassPoolVisitor(List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor)}
   */
  @Test
  void testCreateClassPoolVisitor() {
    // Arrange
    KeepClassSpecificationVisitorFactory keepClassSpecificationVisitorFactory = new KeepClassSpecificationVisitorFactory(
        true, true, true);
    ArrayList<Object> keepClassSpecifications = new ArrayList<>();
    ClassVisitor classVisitor = mock(ClassVisitor.class);
    KotlinAnnotationCounter fieldVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();

    // Act
    ClassPoolVisitor actualCreateClassPoolVisitorResult = keepClassSpecificationVisitorFactory.createClassPoolVisitor(
        keepClassSpecifications, classVisitor, fieldVisitor, methodVisitor, new KotlinAnnotationCounter());
    actualCreateClassPoolVisitorResult.visitClassPool(new ClassPool());

    // Assert that nothing has changed
    assertTrue(actualCreateClassPoolVisitorResult instanceof MultiClassPoolVisitor);
  }

  /**
   * Method under test:
   * {@link KeepClassSpecificationVisitorFactory#createClassPoolVisitor(KeepClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor)}
   */
  @Test
  void testCreateClassPoolVisitor2() {
    // Arrange
    KeepClassSpecificationVisitorFactory keepClassSpecificationVisitorFactory = new KeepClassSpecificationVisitorFactory(
        true, true, true);
    ClassSpecification condition = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    KeepClassSpecification keepClassSpecification = new KeepClassSpecification(true, true, true, true, true, true, true,
        condition, new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name", "Extends Annotation Type",
            "Extends Class Name"));

    ClassVisitor classVisitor = mock(ClassVisitor.class);
    KotlinAnnotationCounter fieldVisitor = new KotlinAnnotationCounter();
    KotlinAnnotationCounter methodVisitor = new KotlinAnnotationCounter();

    // Act
    ClassPoolVisitor actualCreateClassPoolVisitorResult = keepClassSpecificationVisitorFactory.createClassPoolVisitor(
        keepClassSpecification, classVisitor, fieldVisitor, methodVisitor, new KotlinAnnotationCounter());
    actualCreateClassPoolVisitorResult.visitClassPool(new ClassPool());

    // Assert
    assertTrue(actualCreateClassPoolVisitorResult instanceof MultiClassPoolVisitor);
  }
}
