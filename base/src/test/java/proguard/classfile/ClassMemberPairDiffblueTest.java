package proguard.classfile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;

class ClassMemberPairDiffblueTest {
  /**
   * Method under test: {@link ClassMemberPair#getName()}
   */
  @Test
  void testGetName() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act and Assert
    assertEquals("Name", (new ClassMemberPair(clazz, new LibraryField(1, "Name", "Descriptor"))).getName());
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link ClassMemberPair#equals(Object)}
   *   <li>{@link ClassMemberPair#hashCode()}
   * </ul>
   */
  @Test
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    ClassMemberPair classMemberPair = new ClassMemberPair(clazz, new LibraryField(1, "Name", "Descriptor"));

    // Act and Assert
    assertEquals(classMemberPair, classMemberPair);
    int expectedHashCodeResult = classMemberPair.hashCode();
    assertEquals(expectedHashCodeResult, classMemberPair.hashCode());
  }

  /**
   * Method under test: {@link ClassMemberPair#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    ClassMemberPair classMemberPair = new ClassMemberPair(clazz, new LibraryField(1, "Name", "Descriptor"));
    LibraryClass clazz2 = new LibraryClass();

    // Act and Assert
    assertNotEquals(classMemberPair, new ClassMemberPair(clazz2, new LibraryField(1, "Name", "Descriptor")));
  }

  /**
   * Method under test: {@link ClassMemberPair#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Clazz clazz = mock(Clazz.class);
    ClassMemberPair classMemberPair = new ClassMemberPair(clazz, new LibraryField(1, "Name", "Descriptor"));
    LibraryClass clazz2 = new LibraryClass();

    // Act and Assert
    assertNotEquals(classMemberPair, new ClassMemberPair(clazz2, new LibraryField(1, "Name", "Descriptor")));
  }

  /**
   * Method under test: {@link ClassMemberPair#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act and Assert
    assertNotEquals(new ClassMemberPair(clazz, new LibraryField(1, "Name", "Descriptor")), null);
  }

  /**
   * Method under test: {@link ClassMemberPair#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act and Assert
    assertNotEquals(new ClassMemberPair(clazz, new LibraryField(1, "Name", "Descriptor")),
        "Different type to ClassMemberPair");
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link ClassMemberPair#ClassMemberPair(Clazz, Member)}
   *   <li>{@link ClassMemberPair#toString()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act and Assert
    assertEquals("null.NameDescriptor",
        (new ClassMemberPair(clazz, new LibraryField(1, "Name", "Descriptor"))).toString());
  }
}
