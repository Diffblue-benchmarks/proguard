package proguard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class MemberValueSpecificationDiffblueTest {
  /**
   * Method under test: {@link MemberValueSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    MemberValueSpecification memberValueSpecification = new MemberValueSpecification(1, 1, "Annotation Type", "Name",
        "Descriptor", new Number[]{Integer.valueOf(1)});

    // Act and Assert
    assertNotEquals(memberValueSpecification, new MemberValueSpecification());
  }

  /**
   * Method under test: {@link MemberValueSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsWrongType_thenThrowException() {
    // Arrange, Act and Assert
    assertThrows(ClassCastException.class,
        () -> (new MemberValueSpecification()).equals("Different type to MemberValueSpecification"));
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link MemberValueSpecification#equals(Object)}
   *   <li>{@link MemberValueSpecification#hashCode()}
   * </ul>
   */
  @Test
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    MemberValueSpecification memberValueSpecification = new MemberValueSpecification();
    MemberValueSpecification memberValueSpecification2 = new MemberValueSpecification();

    // Act and Assert
    assertEquals(memberValueSpecification, memberValueSpecification2);
    int expectedHashCodeResult = memberValueSpecification.hashCode();
    assertEquals(expectedHashCodeResult, memberValueSpecification2.hashCode());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link MemberValueSpecification#equals(Object)}
   *   <li>{@link MemberValueSpecification#hashCode()}
   * </ul>
   */
  @Test
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    MemberValueSpecification memberValueSpecification = new MemberValueSpecification();

    // Act and Assert
    assertEquals(memberValueSpecification, memberValueSpecification);
    int expectedHashCodeResult = memberValueSpecification.hashCode();
    assertEquals(expectedHashCodeResult, memberValueSpecification.hashCode());
  }

  /**
   * Method under test: {@link MemberValueSpecification#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new MemberValueSpecification(), null);
  }

  /**
   * Method under test:
   * {@link MemberValueSpecification#MemberValueSpecification()}
   */
  @Test
  void testNewMemberValueSpecification() {
    // Arrange and Act
    MemberValueSpecification actualMemberValueSpecification = new MemberValueSpecification();

    // Assert
    assertNull(actualMemberValueSpecification.values);
    assertNull(actualMemberValueSpecification.annotationType);
    assertNull(actualMemberValueSpecification.descriptor);
    assertNull(actualMemberValueSpecification.name);
    assertNull(actualMemberValueSpecification.attributeNames);
    assertEquals(0, actualMemberValueSpecification.requiredSetAccessFlags);
    assertEquals(0, actualMemberValueSpecification.requiredUnsetAccessFlags);
  }

  /**
   * Method under test:
   * {@link MemberValueSpecification#MemberValueSpecification(int, int, String, String, String, Number[])}
   */
  @Test
  void testNewMemberValueSpecification2() {
    // Arrange
    Integer valueOfResult = Integer.valueOf(1);

    // Act
    MemberValueSpecification actualMemberValueSpecification = new MemberValueSpecification(1, 1, "Annotation Type",
        "Name", "Descriptor", new Number[]{valueOfResult});

    // Assert
    assertEquals("Annotation Type", actualMemberValueSpecification.annotationType);
    assertEquals("Descriptor", actualMemberValueSpecification.descriptor);
    assertEquals("Name", actualMemberValueSpecification.name);
    assertNull(actualMemberValueSpecification.attributeNames);
    Number[] numberArray = actualMemberValueSpecification.values;
    assertEquals(1, numberArray.length);
    assertEquals(1, actualMemberValueSpecification.requiredSetAccessFlags);
    assertEquals(1, actualMemberValueSpecification.requiredUnsetAccessFlags);
    assertSame(valueOfResult, numberArray[0]);
  }
}
