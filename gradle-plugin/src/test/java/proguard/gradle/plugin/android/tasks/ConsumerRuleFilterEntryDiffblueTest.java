package proguard.gradle.plugin.android.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ConsumerRuleFilterEntryDiffblueTest {
  /**
   * Test {@link ConsumerRuleFilterEntry#component1()}.
   * <p>
   * Method under test: {@link ConsumerRuleFilterEntry#component1()}
   */
  @Test
  @DisplayName("Test component1()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String ConsumerRuleFilterEntry.component1()"})
  void testComponent1() {
    // Arrange, Act and Assert
    assertEquals("Group", (new ConsumerRuleFilterEntry("Group", "Module")).component1());
  }

  /**
   * Test {@link ConsumerRuleFilterEntry#component2()}.
   * <p>
   * Method under test: {@link ConsumerRuleFilterEntry#component2()}
   */
  @Test
  @DisplayName("Test component2()")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String ConsumerRuleFilterEntry.component2()"})
  void testComponent2() {
    // Arrange, Act and Assert
    assertEquals("Module", (new ConsumerRuleFilterEntry("Group", "Module")).component2());
  }

  /**
   * Test {@link ConsumerRuleFilterEntry#copy(String, String)}.
   * <p>
   * Method under test: {@link ConsumerRuleFilterEntry#copy(String, String)}
   */
  @Test
  @DisplayName("Test copy(String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"ConsumerRuleFilterEntry ConsumerRuleFilterEntry.copy(String, String)"})
  void testCopy() {
    // Arrange
    ConsumerRuleFilterEntry consumerRuleFilterEntry = new ConsumerRuleFilterEntry("Group", "Module");

    // Act and Assert
    assertEquals(consumerRuleFilterEntry, consumerRuleFilterEntry.copy("Group", "Module"));
  }

  /**
   * Test {@link ConsumerRuleFilterEntry#equals(Object)}, and {@link ConsumerRuleFilterEntry#hashCode()}.
   * <ul>
   *   <li>When other is equal.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ConsumerRuleFilterEntry#equals(Object)}
   *   <li>{@link ConsumerRuleFilterEntry#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ConsumerRuleFilterEntry.equals(Object)", "int ConsumerRuleFilterEntry.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    ConsumerRuleFilterEntry consumerRuleFilterEntry = new ConsumerRuleFilterEntry("Group", "Module");
    ConsumerRuleFilterEntry consumerRuleFilterEntry2 = new ConsumerRuleFilterEntry("Group", "Module");

    // Act and Assert
    assertEquals(consumerRuleFilterEntry, consumerRuleFilterEntry2);
    int expectedHashCodeResult = consumerRuleFilterEntry.hashCode();
    assertEquals(expectedHashCodeResult, consumerRuleFilterEntry2.hashCode());
  }

  /**
   * Test {@link ConsumerRuleFilterEntry#equals(Object)}, and {@link ConsumerRuleFilterEntry#hashCode()}.
   * <ul>
   *   <li>When other is same.</li>
   *   <li>Then return equal.</li>
   * </ul>
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ConsumerRuleFilterEntry#equals(Object)}
   *   <li>{@link ConsumerRuleFilterEntry#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ConsumerRuleFilterEntry.equals(Object)", "int ConsumerRuleFilterEntry.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    ConsumerRuleFilterEntry consumerRuleFilterEntry = new ConsumerRuleFilterEntry("Group", "Module");

    // Act and Assert
    assertEquals(consumerRuleFilterEntry, consumerRuleFilterEntry);
    int expectedHashCodeResult = consumerRuleFilterEntry.hashCode();
    assertEquals(expectedHashCodeResult, consumerRuleFilterEntry.hashCode());
  }

  /**
   * Test {@link ConsumerRuleFilterEntry#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConsumerRuleFilterEntry#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ConsumerRuleFilterEntry.equals(Object)", "int ConsumerRuleFilterEntry.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ConsumerRuleFilterEntry consumerRuleFilterEntry = new ConsumerRuleFilterEntry("group", "Module");

    // Act and Assert
    assertNotEquals(consumerRuleFilterEntry, new ConsumerRuleFilterEntry("Group", "Module"));
  }

  /**
   * Test {@link ConsumerRuleFilterEntry#equals(Object)}.
   * <ul>
   *   <li>When other is different.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConsumerRuleFilterEntry#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ConsumerRuleFilterEntry.equals(Object)", "int ConsumerRuleFilterEntry.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    ConsumerRuleFilterEntry consumerRuleFilterEntry = new ConsumerRuleFilterEntry("Group", "group");

    // Act and Assert
    assertNotEquals(consumerRuleFilterEntry, new ConsumerRuleFilterEntry("Group", "Module"));
  }

  /**
   * Test {@link ConsumerRuleFilterEntry#equals(Object)}.
   * <ul>
   *   <li>When other is {@code null}.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConsumerRuleFilterEntry#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ConsumerRuleFilterEntry.equals(Object)", "int ConsumerRuleFilterEntry.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new ConsumerRuleFilterEntry("Group", "Module"), null);
  }

  /**
   * Test {@link ConsumerRuleFilterEntry#equals(Object)}.
   * <ul>
   *   <li>When other is wrong type.</li>
   *   <li>Then return not equal.</li>
   * </ul>
   * <p>
   * Method under test: {@link ConsumerRuleFilterEntry#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ConsumerRuleFilterEntry.equals(Object)", "int ConsumerRuleFilterEntry.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new ConsumerRuleFilterEntry("Group", "Module"), "Different type to ConsumerRuleFilterEntry");
  }

  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>{@link ConsumerRuleFilterEntry#toString()}
   *   <li>{@link ConsumerRuleFilterEntry#getGroup()}
   *   <li>{@link ConsumerRuleFilterEntry#getModule()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"String ConsumerRuleFilterEntry.getGroup()", "String ConsumerRuleFilterEntry.getModule()",
      "String ConsumerRuleFilterEntry.toString()"})
  void testGettersAndSetters() {
    // Arrange
    ConsumerRuleFilterEntry consumerRuleFilterEntry = new ConsumerRuleFilterEntry("Group", "Module");

    // Act
    String actualToStringResult = consumerRuleFilterEntry.toString();
    String actualGroup = consumerRuleFilterEntry.getGroup();

    // Assert
    assertEquals("ConsumerRuleFilterEntry(group=Group, module=Module)", actualToStringResult);
    assertEquals("Group", actualGroup);
    assertEquals("Module", consumerRuleFilterEntry.getModule());
  }

  /**
   * Test {@link ConsumerRuleFilterEntry#ConsumerRuleFilterEntry(String, String)}.
   * <p>
   * Method under test: {@link ConsumerRuleFilterEntry#ConsumerRuleFilterEntry(String, String)}
   */
  @Test
  @DisplayName("Test new ConsumerRuleFilterEntry(String, String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ConsumerRuleFilterEntry.<init>(String, String)"})
  void testNewConsumerRuleFilterEntry() {
    // Arrange and Act
    ConsumerRuleFilterEntry actualConsumerRuleFilterEntry = new ConsumerRuleFilterEntry("Group", "Module");

    // Assert
    assertEquals("Group", actualConsumerRuleFilterEntry.component1());
    assertEquals("Group", actualConsumerRuleFilterEntry.getGroup());
    assertEquals("Module", actualConsumerRuleFilterEntry.component2());
    assertEquals("Module", actualConsumerRuleFilterEntry.getModule());
  }
}
