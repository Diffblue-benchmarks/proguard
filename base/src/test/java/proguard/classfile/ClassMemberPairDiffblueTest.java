package proguard.classfile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ClassMemberPairDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ClassMemberPair#ClassMemberPair(Clazz, Member)}
   *   <li>{@link ClassMemberPair#toString()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({
      "void proguard.classfile.ClassMemberPair.<init>(proguard.classfile.Clazz, proguard.classfile.Member)",
      "java.lang.String proguard.classfile.ClassMemberPair.toString()"})
  void testGettersAndSetters() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act and Assert
    assertEquals("null.NameDescriptor",
        (new ClassMemberPair(clazz, new LibraryField(1, "Name", "Descriptor"))).toString());
  }

  /**
   * Test {@link ClassMemberPair#getName()}.
   * <ul>
   *   <li>Then return {@code Name}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassMemberPair#getName()}
   */
  @Test
  @DisplayName("Test getName(); then return 'Name'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"java.lang.String proguard.classfile.ClassMemberPair.getName()"})
  void testGetName_thenReturnName() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act and Assert
    assertEquals("Name", (new ClassMemberPair(clazz, new LibraryField(1, "Name", "Descriptor"))).getName());
  }

  /**
   * Test {@link ClassMemberPair#equals(Object)}, and {@link ClassMemberPair#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ClassMemberPair#equals(Object)}
   *   <li>{@link ClassMemberPair#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.classfile.ClassMemberPair.equals(java.lang.Object)",
      "int proguard.classfile.ClassMemberPair.hashCode()"})
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
   * Test {@link ClassMemberPair#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassMemberPair#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.classfile.ClassMemberPair.equals(java.lang.Object)",
      "int proguard.classfile.ClassMemberPair.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    LibraryClass clazz = new LibraryClass();
    ClassMemberPair classMemberPair = new ClassMemberPair(clazz, new LibraryField(1, "Name", "Descriptor"));
    LibraryClass clazz2 = new LibraryClass();

    // Act and Assert
    assertNotEquals(classMemberPair, new ClassMemberPair(clazz2, new LibraryField(1, "Name", "Descriptor")));
  }

  /**
   * Test {@link ClassMemberPair#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassMemberPair#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.classfile.ClassMemberPair.equals(java.lang.Object)",
      "int proguard.classfile.ClassMemberPair.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act and Assert
    assertNotEquals(new ClassMemberPair(clazz, new LibraryField(1, "Name", "Descriptor")), null);
  }

  /**
   * Test {@link ClassMemberPair#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ClassMemberPair#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean proguard.classfile.ClassMemberPair.equals(java.lang.Object)",
      "int proguard.classfile.ClassMemberPair.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    LibraryClass clazz = new LibraryClass();

    // Act and Assert
    assertNotEquals(new ClassMemberPair(clazz, new LibraryField(1, "Name", "Descriptor")),
        "Different type to ClassMemberPair");
  }
}
