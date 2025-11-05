package proguard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;

class MemberSpecificationDiffblueTest {
  /**
   * Methods under test:
   * <ul>
   *   <li>{@link MemberSpecification#equals(Object)}
   *   <li>{@link MemberSpecification#hashCode()}
   * </ul>
   */
  @Test
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    MemberSpecification memberSpecification = new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor");
    MemberSpecification memberSpecification2 = new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor");

    // Act and Assert
    assertEquals(memberSpecification, memberSpecification2);
    int expectedHashCodeResult = memberSpecification.hashCode();
    assertEquals(expectedHashCodeResult, memberSpecification2.hashCode());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link MemberSpecification#equals(Object)}
   *   <li>{@link MemberSpecification#hashCode()}
   * </ul>
   */
  @Test
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual2() {
    // Arrange
    MemberSpecification memberSpecification = new MemberSpecification(1, 1, null, "Name", "Descriptor");
    MemberSpecification memberSpecification2 = new MemberSpecification(1, 1, null, "Name", "Descriptor");

    // Act and Assert
    assertEquals(memberSpecification, memberSpecification2);
    int expectedHashCodeResult = memberSpecification.hashCode();
    assertEquals(expectedHashCodeResult, memberSpecification2.hashCode());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link MemberSpecification#equals(Object)}
   *   <li>{@link MemberSpecification#hashCode()}
   * </ul>
   */
  @Test
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual3() {
    // Arrange
    MemberSpecification memberSpecification = new MemberSpecification(1, 1, "Annotation Type", null, "Descriptor");
    MemberSpecification memberSpecification2 = new MemberSpecification(1, 1, "Annotation Type", null, "Descriptor");

    // Act and Assert
    assertEquals(memberSpecification, memberSpecification2);
    int expectedHashCodeResult = memberSpecification.hashCode();
    assertEquals(expectedHashCodeResult, memberSpecification2.hashCode());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link MemberSpecification#equals(Object)}
   *   <li>{@link MemberSpecification#hashCode()}
   * </ul>
   */
  @Test
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual4() {
    // Arrange
    MemberSpecification memberSpecification = new MemberSpecification(1, 1, "Annotation Type", "Name", null);
    MemberSpecification memberSpecification2 = new MemberSpecification(1, 1, "Annotation Type", "Name", null);

    // Act and Assert
    assertEquals(memberSpecification, memberSpecification2);
    int expectedHashCodeResult = memberSpecification.hashCode();
    assertEquals(expectedHashCodeResult, memberSpecification2.hashCode());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link MemberSpecification#equals(Object)}
   *   <li>{@link MemberSpecification#hashCode()}
   * </ul>
   */
  @Test
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    MemberSpecification memberSpecification = new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor");

    // Act and Assert
    assertEquals(memberSpecification, memberSpecification);
    int expectedHashCodeResult = memberSpecification.hashCode();
    assertEquals(expectedHashCodeResult, memberSpecification.hashCode());
  }

  /**
   * Method under test: {@link MemberSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    MemberSpecification memberSpecification = new MemberSpecification(0, 1, "Annotation Type", "Name", "Descriptor");

    // Act and Assert
    assertNotEquals(memberSpecification, new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor"));
  }

  /**
   * Method under test: {@link MemberSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    MemberSpecification memberSpecification = new MemberSpecification(1, 0, "Annotation Type", "Name", "Descriptor");

    // Act and Assert
    assertNotEquals(memberSpecification, new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor"));
  }

  /**
   * Method under test: {@link MemberSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    MemberSpecification memberSpecification = new MemberSpecification(1, 1, "Name", "Name", "Descriptor");

    // Act and Assert
    assertNotEquals(memberSpecification, new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor"));
  }

  /**
   * Method under test: {@link MemberSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    MemberSpecification memberSpecification = new MemberSpecification(1, 1, null, "Name", "Descriptor");

    // Act and Assert
    assertNotEquals(memberSpecification, new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor"));
  }

  /**
   * Method under test: {@link MemberSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    MemberSpecification memberSpecification = new MemberSpecification(1, 1, "Annotation Type", "Annotation Type",
        "Descriptor");

    // Act and Assert
    assertNotEquals(memberSpecification, new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor"));
  }

  /**
   * Method under test: {@link MemberSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    MemberSpecification memberSpecification = new MemberSpecification(1, 1, "Annotation Type", null, "Descriptor");

    // Act and Assert
    assertNotEquals(memberSpecification, new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor"));
  }

  /**
   * Method under test: {@link MemberSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual7() {
    // Arrange
    MemberSpecification memberSpecification = new MemberSpecification(1, 1, "Annotation Type", "Name",
        "Annotation Type");

    // Act and Assert
    assertNotEquals(memberSpecification, new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor"));
  }

  /**
   * Method under test: {@link MemberSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual8() {
    // Arrange
    MemberSpecification memberSpecification = new MemberSpecification(1, 1, "Annotation Type", "Name", null);

    // Act and Assert
    assertNotEquals(memberSpecification, new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor"));
  }

  /**
   * Method under test: {@link MemberSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual9() {
    // Arrange, Act and Assert
    assertNotEquals(new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor"),
        mock(MemberValueSpecification.class));
  }

  /**
   * Method under test: {@link MemberSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor"), null);
  }

  /**
   * Method under test: {@link MemberSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new MemberSpecification(1, 1, "Annotation Type", "Name", "Descriptor"),
        "Different type to MemberSpecification");
  }

  /**
   * Method under test: {@link MemberSpecification#MemberSpecification()}
   */
  @Test
  void testNewMemberSpecification() {
    // Arrange and Act
    MemberSpecification actualMemberSpecification = new MemberSpecification();

    // Assert
    assertNull(actualMemberSpecification.annotationType);
    assertNull(actualMemberSpecification.descriptor);
    assertNull(actualMemberSpecification.name);
    assertNull(actualMemberSpecification.attributeNames);
    assertEquals(0, actualMemberSpecification.requiredSetAccessFlags);
    assertEquals(0, actualMemberSpecification.requiredUnsetAccessFlags);
  }

  /**
   * Method under test:
   * {@link MemberSpecification#MemberSpecification(int, int, String, String, String)}
   */
  @Test
  void testNewMemberSpecification2() {
    // Arrange and Act
    MemberSpecification actualMemberSpecification = new MemberSpecification(1, 1, "Annotation Type", "Name",
        "Descriptor");

    // Assert
    assertEquals("Annotation Type", actualMemberSpecification.annotationType);
    assertEquals("Descriptor", actualMemberSpecification.descriptor);
    assertEquals("Name", actualMemberSpecification.name);
    assertNull(actualMemberSpecification.attributeNames);
    assertEquals(1, actualMemberSpecification.requiredSetAccessFlags);
    assertEquals(1, actualMemberSpecification.requiredUnsetAccessFlags);
  }
}
