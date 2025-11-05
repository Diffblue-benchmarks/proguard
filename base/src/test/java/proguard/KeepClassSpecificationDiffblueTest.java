package proguard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;

class KeepClassSpecificationDiffblueTest {
  /**
   * Methods under test:
   * <ul>
   *   <li>{@link KeepClassSpecification#equals(Object)}
   *   <li>{@link KeepClassSpecification#hashCode()}
   * </ul>
   */
  @Test
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    ClassSpecification condition = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    KeepClassSpecification keepClassSpecification = new KeepClassSpecification(true, true, true, true, true, true, true,
        condition, new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name", "Extends Annotation Type",
            "Extends Class Name"));
    ClassSpecification condition2 = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    KeepClassSpecification keepClassSpecification2 = new KeepClassSpecification(true, true, true, true, true, true,
        true, condition2, new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
            "Extends Annotation Type", "Extends Class Name"));

    // Act and Assert
    assertEquals(keepClassSpecification, keepClassSpecification2);
    int expectedHashCodeResult = keepClassSpecification.hashCode();
    assertEquals(expectedHashCodeResult, keepClassSpecification2.hashCode());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link KeepClassSpecification#equals(Object)}
   *   <li>{@link KeepClassSpecification#hashCode()}
   * </ul>
   */
  @Test
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    ClassSpecification condition = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    KeepClassSpecification keepClassSpecification = new KeepClassSpecification(true, true, true, true, true, true, true,
        condition, new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name", "Extends Annotation Type",
            "Extends Class Name"));

    // Act and Assert
    assertEquals(keepClassSpecification, keepClassSpecification);
    int expectedHashCodeResult = keepClassSpecification.hashCode();
    assertEquals(expectedHashCodeResult, keepClassSpecification.hashCode());
  }

  /**
   * Method under test: {@link KeepClassSpecification#clone()}
   */
  @Test
  void testClone() {
    // Arrange
    ClassSpecification condition = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    KeepClassSpecification keepClassSpecification = new KeepClassSpecification(true, true, true, true, true, true, true,
        condition, new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name", "Extends Annotation Type",
            "Extends Class Name"));

    // Act
    Object actualCloneResult = keepClassSpecification.clone();

    // Assert
    assertTrue(actualCloneResult instanceof KeepClassSpecification);
    assertEquals(keepClassSpecification, actualCloneResult);
  }

  /**
   * Method under test: {@link KeepClassSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ClassSpecification condition = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    KeepClassSpecification keepClassSpecification = new KeepClassSpecification(false, true, true, true, true, true,
        true, condition, new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
            "Extends Annotation Type", "Extends Class Name"));
    ClassSpecification condition2 = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    // Act and Assert
    assertNotEquals(keepClassSpecification,
        new KeepClassSpecification(true, true, true, true, true, true, true, condition2, new ClassSpecification(
            "Comments", 1, 1, "Annotation Type", "Class Name", "Extends Annotation Type", "Extends Class Name")));
  }

  /**
   * Method under test: {@link KeepClassSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    ClassSpecification condition = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    KeepClassSpecification keepClassSpecification = new KeepClassSpecification(true, false, true, true, true, true,
        true, condition, new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
            "Extends Annotation Type", "Extends Class Name"));
    ClassSpecification condition2 = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    // Act and Assert
    assertNotEquals(keepClassSpecification,
        new KeepClassSpecification(true, true, true, true, true, true, true, condition2, new ClassSpecification(
            "Comments", 1, 1, "Annotation Type", "Class Name", "Extends Annotation Type", "Extends Class Name")));
  }

  /**
   * Method under test: {@link KeepClassSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    ClassSpecification condition = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    KeepClassSpecification keepClassSpecification = new KeepClassSpecification(true, true, false, true, true, true,
        true, condition, new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
            "Extends Annotation Type", "Extends Class Name"));
    ClassSpecification condition2 = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    // Act and Assert
    assertNotEquals(keepClassSpecification,
        new KeepClassSpecification(true, true, true, true, true, true, true, condition2, new ClassSpecification(
            "Comments", 1, 1, "Annotation Type", "Class Name", "Extends Annotation Type", "Extends Class Name")));
  }

  /**
   * Method under test: {@link KeepClassSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    ClassSpecification condition = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    KeepClassSpecification keepClassSpecification = new KeepClassSpecification(true, true, true, false, true, true,
        true, condition, new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
            "Extends Annotation Type", "Extends Class Name"));
    ClassSpecification condition2 = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    // Act and Assert
    assertNotEquals(keepClassSpecification,
        new KeepClassSpecification(true, true, true, true, true, true, true, condition2, new ClassSpecification(
            "Comments", 1, 1, "Annotation Type", "Class Name", "Extends Annotation Type", "Extends Class Name")));
  }

  /**
   * Method under test: {@link KeepClassSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    ClassSpecification condition = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    KeepClassSpecification keepClassSpecification = new KeepClassSpecification(true, true, true, true, false, true,
        true, condition, new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
            "Extends Annotation Type", "Extends Class Name"));
    ClassSpecification condition2 = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    // Act and Assert
    assertNotEquals(keepClassSpecification,
        new KeepClassSpecification(true, true, true, true, true, true, true, condition2, new ClassSpecification(
            "Comments", 1, 1, "Annotation Type", "Class Name", "Extends Annotation Type", "Extends Class Name")));
  }

  /**
   * Method under test: {@link KeepClassSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    ClassSpecification condition = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    KeepClassSpecification keepClassSpecification = new KeepClassSpecification(true, true, true, true, true, false,
        true, condition, new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
            "Extends Annotation Type", "Extends Class Name"));
    ClassSpecification condition2 = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    // Act and Assert
    assertNotEquals(keepClassSpecification,
        new KeepClassSpecification(true, true, true, true, true, true, true, condition2, new ClassSpecification(
            "Comments", 1, 1, "Annotation Type", "Class Name", "Extends Annotation Type", "Extends Class Name")));
  }

  /**
   * Method under test: {@link KeepClassSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual7() {
    // Arrange
    ClassSpecification condition = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    KeepClassSpecification keepClassSpecification = new KeepClassSpecification(true, true, true, true, true, true,
        false, condition, new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
            "Extends Annotation Type", "Extends Class Name"));
    ClassSpecification condition2 = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    // Act and Assert
    assertNotEquals(keepClassSpecification,
        new KeepClassSpecification(true, true, true, true, true, true, true, condition2, new ClassSpecification(
            "Comments", 1, 1, "Annotation Type", "Class Name", "Extends Annotation Type", "Extends Class Name")));
  }

  /**
   * Method under test: {@link KeepClassSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual8() {
    // Arrange
    ClassSpecification condition = new ClassSpecification("Comments", 0, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    KeepClassSpecification keepClassSpecification = new KeepClassSpecification(true, true, true, true, true, true, true,
        condition, new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name", "Extends Annotation Type",
            "Extends Class Name"));
    ClassSpecification condition2 = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    // Act and Assert
    assertNotEquals(keepClassSpecification,
        new KeepClassSpecification(true, true, true, true, true, true, true, condition2, new ClassSpecification(
            "Comments", 1, 1, "Annotation Type", "Class Name", "Extends Annotation Type", "Extends Class Name")));
  }

  /**
   * Method under test: {@link KeepClassSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual9() {
    // Arrange
    ClassSpecification condition = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    KeepClassSpecification condition2 = new KeepClassSpecification(true, true, true, true, true, true, true, condition,
        new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name", "Extends Annotation Type",
            "Extends Class Name"));

    KeepClassSpecification keepClassSpecification = new KeepClassSpecification(true, true, true, true, true, true, true,
        condition2, new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name", "Extends Annotation Type",
            "Extends Class Name"));
    ClassSpecification condition3 = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    // Act and Assert
    assertNotEquals(keepClassSpecification,
        new KeepClassSpecification(true, true, true, true, true, true, true, condition3, new ClassSpecification(
            "Comments", 1, 1, "Annotation Type", "Class Name", "Extends Annotation Type", "Extends Class Name")));
  }

  /**
   * Method under test: {@link KeepClassSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual10() {
    // Arrange
    KeepClassSpecification keepClassSpecification = new KeepClassSpecification(true, true, true, true, true, true, true,
        null, new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name", "Extends Annotation Type",
            "Extends Class Name"));
    ClassSpecification condition = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    // Act and Assert
    assertNotEquals(keepClassSpecification,
        new KeepClassSpecification(true, true, true, true, true, true, true, condition, new ClassSpecification(
            "Comments", 1, 1, "Annotation Type", "Class Name", "Extends Annotation Type", "Extends Class Name")));
  }

  /**
   * Method under test: {@link KeepClassSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual11() {
    // Arrange
    ClassSpecification condition = mock(ClassSpecification.class);
    KeepClassSpecification keepClassSpecification = new KeepClassSpecification(true, true, true, true, true, true, true,
        condition, new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name", "Extends Annotation Type",
            "Extends Class Name"));
    ClassSpecification condition2 = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    // Act and Assert
    assertNotEquals(keepClassSpecification,
        new KeepClassSpecification(true, true, true, true, true, true, true, condition2, new ClassSpecification(
            "Comments", 1, 1, "Annotation Type", "Class Name", "Extends Annotation Type", "Extends Class Name")));
  }

  /**
   * Method under test: {@link KeepClassSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual12() {
    // Arrange
    ClassSpecification condition = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    KeepClassSpecification keepClassSpecification = new KeepClassSpecification(true, true, true, true, true, true, true,
        condition, new ClassSpecification("Comments", 0, 1, "Annotation Type", "Class Name", "Extends Annotation Type",
            "Extends Class Name"));
    ClassSpecification condition2 = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    // Act and Assert
    assertNotEquals(keepClassSpecification,
        new KeepClassSpecification(true, true, true, true, true, true, true, condition2, new ClassSpecification(
            "Comments", 1, 1, "Annotation Type", "Class Name", "Extends Annotation Type", "Extends Class Name")));
  }

  /**
   * Method under test: {@link KeepClassSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    ClassSpecification condition = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    // Act and Assert
    assertNotEquals(
        new KeepClassSpecification(true, true, true, true, true, true, true, condition, new ClassSpecification(
            "Comments", 1, 1, "Annotation Type", "Class Name", "Extends Annotation Type", "Extends Class Name")),
        null);
  }

  /**
   * Method under test: {@link KeepClassSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    ClassSpecification condition = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    // Act and Assert
    assertNotEquals(
        new KeepClassSpecification(true, true, true, true, true, true, true, condition, new ClassSpecification(
            "Comments", 1, 1, "Annotation Type", "Class Name", "Extends Annotation Type", "Extends Class Name")),
        "Different type to KeepClassSpecification");
  }

  /**
   * Method under test:
   * {@link KeepClassSpecification#KeepClassSpecification(boolean, boolean, boolean, boolean, boolean, boolean, boolean, ClassSpecification, ClassSpecification)}
   */
  @Test
  void testNewKeepClassSpecification() {
    // Arrange
    ClassSpecification condition = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    // Act
    KeepClassSpecification actualKeepClassSpecification = new KeepClassSpecification(true, true, true, true, true, true,
        true, condition, new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
            "Extends Annotation Type", "Extends Class Name"));

    // Assert
    assertEquals("Annotation Type", actualKeepClassSpecification.annotationType);
    ClassSpecification classSpecification = actualKeepClassSpecification.condition;
    assertEquals("Annotation Type", classSpecification.annotationType);
    assertEquals("Class Name", actualKeepClassSpecification.className);
    assertEquals("Class Name", classSpecification.className);
    assertEquals("Comments", actualKeepClassSpecification.comments);
    assertEquals("Comments", classSpecification.comments);
    assertEquals("Extends Annotation Type", actualKeepClassSpecification.extendsAnnotationType);
    assertEquals("Extends Annotation Type", classSpecification.extendsAnnotationType);
    assertEquals("Extends Class Name", actualKeepClassSpecification.extendsClassName);
    assertEquals("Extends Class Name", classSpecification.extendsClassName);
    assertNull(actualKeepClassSpecification.memberComments);
    assertNull(classSpecification.memberComments);
    assertNull(actualKeepClassSpecification.attributeNames);
    assertNull(classSpecification.attributeNames);
    assertNull(actualKeepClassSpecification.fieldSpecifications);
    assertNull(classSpecification.fieldSpecifications);
    assertNull(actualKeepClassSpecification.methodSpecifications);
    assertNull(classSpecification.methodSpecifications);
    assertEquals(1, actualKeepClassSpecification.requiredSetAccessFlags);
    assertEquals(1, classSpecification.requiredSetAccessFlags);
    assertEquals(1, actualKeepClassSpecification.requiredUnsetAccessFlags);
    assertEquals(1, classSpecification.requiredUnsetAccessFlags);
    assertTrue(actualKeepClassSpecification.allowObfuscation);
    assertTrue(actualKeepClassSpecification.allowOptimization);
    assertTrue(actualKeepClassSpecification.allowShrinking);
    assertTrue(actualKeepClassSpecification.markClassMembers);
    assertTrue(actualKeepClassSpecification.markClasses);
    assertTrue(actualKeepClassSpecification.markCodeAttributes);
    assertTrue(actualKeepClassSpecification.markConditionally);
    assertTrue(actualKeepClassSpecification.markDescriptorClasses);
  }

  /**
   * Method under test:
   * {@link KeepClassSpecification#KeepClassSpecification(boolean, boolean, boolean, boolean, boolean, boolean, boolean, boolean, ClassSpecification, ClassSpecification)}
   */
  @Test
  void testNewKeepClassSpecification2() {
    // Arrange
    ClassSpecification condition = new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
        "Extends Annotation Type", "Extends Class Name");

    // Act
    KeepClassSpecification actualKeepClassSpecification = new KeepClassSpecification(true, true, true, true, true, true,
        true, true, condition, new ClassSpecification("Comments", 1, 1, "Annotation Type", "Class Name",
            "Extends Annotation Type", "Extends Class Name"));

    // Assert
    assertEquals("Annotation Type", actualKeepClassSpecification.annotationType);
    ClassSpecification classSpecification = actualKeepClassSpecification.condition;
    assertEquals("Annotation Type", classSpecification.annotationType);
    assertEquals("Class Name", actualKeepClassSpecification.className);
    assertEquals("Class Name", classSpecification.className);
    assertEquals("Comments", actualKeepClassSpecification.comments);
    assertEquals("Comments", classSpecification.comments);
    assertEquals("Extends Annotation Type", actualKeepClassSpecification.extendsAnnotationType);
    assertEquals("Extends Annotation Type", classSpecification.extendsAnnotationType);
    assertEquals("Extends Class Name", actualKeepClassSpecification.extendsClassName);
    assertEquals("Extends Class Name", classSpecification.extendsClassName);
    assertNull(actualKeepClassSpecification.memberComments);
    assertNull(classSpecification.memberComments);
    assertNull(actualKeepClassSpecification.attributeNames);
    assertNull(classSpecification.attributeNames);
    assertNull(actualKeepClassSpecification.fieldSpecifications);
    assertNull(classSpecification.fieldSpecifications);
    assertNull(actualKeepClassSpecification.methodSpecifications);
    assertNull(classSpecification.methodSpecifications);
    assertEquals(1, actualKeepClassSpecification.requiredSetAccessFlags);
    assertEquals(1, classSpecification.requiredSetAccessFlags);
    assertEquals(1, actualKeepClassSpecification.requiredUnsetAccessFlags);
    assertEquals(1, classSpecification.requiredUnsetAccessFlags);
    assertTrue(actualKeepClassSpecification.allowObfuscation);
    assertTrue(actualKeepClassSpecification.allowOptimization);
    assertTrue(actualKeepClassSpecification.allowShrinking);
    assertTrue(actualKeepClassSpecification.markClassMembers);
    assertTrue(actualKeepClassSpecification.markClasses);
    assertTrue(actualKeepClassSpecification.markCodeAttributes);
    assertTrue(actualKeepClassSpecification.markConditionally);
    assertTrue(actualKeepClassSpecification.markDescriptorClasses);
  }
}
