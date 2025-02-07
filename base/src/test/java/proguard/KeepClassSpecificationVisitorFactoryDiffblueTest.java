package proguard;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
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
   * Test {@link KeepClassSpecificationVisitorFactory#createClassPoolVisitor(KeepClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor)} with {@code keepClassSpecification}, {@code classVisitor}, {@code fieldVisitor}, {@code methodVisitor}, {@code attributeVisitor}.
   * <p>
   * Method under test: {@link KeepClassSpecificationVisitorFactory#createClassPoolVisitor(KeepClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor)}
   */
  @Test
  @DisplayName("Test createClassPoolVisitor(KeepClassSpecification, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor) with 'keepClassSpecification', 'classVisitor', 'fieldVisitor', 'methodVisitor', 'attributeVisitor'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.classfile.visitor.ClassPoolVisitor proguard.KeepClassSpecificationVisitorFactory.createClassPoolVisitor(proguard.KeepClassSpecification, proguard.classfile.visitor.ClassVisitor, proguard.classfile.visitor.MemberVisitor, proguard.classfile.visitor.MemberVisitor, proguard.classfile.attribute.visitor.AttributeVisitor)"})
  void testCreateClassPoolVisitorWithKeepClassSpecificationClassVisitorFieldVisitorMethodVisitorAttributeVisitor() {
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

  /**
   * Test {@link KeepClassSpecificationVisitorFactory#createClassPoolVisitor(List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor)} with {@code keepClassSpecifications}, {@code classVisitor}, {@code fieldVisitor}, {@code methodVisitor}, {@code attributeVisitor}.
   * <p>
   * Method under test: {@link KeepClassSpecificationVisitorFactory#createClassPoolVisitor(List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor)}
   */
  @Test
  @DisplayName("Test createClassPoolVisitor(List, ClassVisitor, MemberVisitor, MemberVisitor, AttributeVisitor) with 'keepClassSpecifications', 'classVisitor', 'fieldVisitor', 'methodVisitor', 'attributeVisitor'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "proguard.classfile.visitor.ClassPoolVisitor proguard.KeepClassSpecificationVisitorFactory.createClassPoolVisitor(java.util.List, proguard.classfile.visitor.ClassVisitor, proguard.classfile.visitor.MemberVisitor, proguard.classfile.visitor.MemberVisitor, proguard.classfile.attribute.visitor.AttributeVisitor)"})
  void testCreateClassPoolVisitorWithKeepClassSpecificationsClassVisitorFieldVisitorMethodVisitorAttributeVisitor() {
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

    // Assert
    assertTrue(actualCreateClassPoolVisitorResult instanceof MultiClassPoolVisitor);
  }
}
