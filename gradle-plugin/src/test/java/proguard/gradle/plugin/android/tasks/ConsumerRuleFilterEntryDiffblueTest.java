package proguard.gradle.plugin.android.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

class ConsumerRuleFilterEntryDiffblueTest {
  /**
   * Method under test: {@link ConsumerRuleFilterEntry#component1()}
   */
  @Test
  void testComponent1() {
    // Arrange, Act and Assert
    assertEquals("Group", (new ConsumerRuleFilterEntry("Group", "Module")).component1());
  }

  /**
   * Method under test: {@link ConsumerRuleFilterEntry#component2()}
   */
  @Test
  void testComponent2() {
    // Arrange, Act and Assert
    assertEquals("Module", (new ConsumerRuleFilterEntry("Group", "Module")).component2());
  }

  /**
   * Method under test: {@link ConsumerRuleFilterEntry#copy(String, String)}
   */
  @Test
  void testCopy() {
    // Arrange
    ConsumerRuleFilterEntry consumerRuleFilterEntry = new ConsumerRuleFilterEntry("Group", "Module");

    // Act and Assert
    assertEquals(consumerRuleFilterEntry, consumerRuleFilterEntry.copy("Group", "Module"));
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link ConsumerRuleFilterEntry#equals(Object)}
   *   <li>{@link ConsumerRuleFilterEntry#hashCode()}
   * </ul>
   */
  @Test
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
   * Methods under test:
   * <ul>
   *   <li>{@link ConsumerRuleFilterEntry#equals(Object)}
   *   <li>{@link ConsumerRuleFilterEntry#hashCode()}
   * </ul>
   */
  @Test
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    ConsumerRuleFilterEntry consumerRuleFilterEntry = new ConsumerRuleFilterEntry("Group", "Module");

    // Act and Assert
    assertEquals(consumerRuleFilterEntry, consumerRuleFilterEntry);
    int expectedHashCodeResult = consumerRuleFilterEntry.hashCode();
    assertEquals(expectedHashCodeResult, consumerRuleFilterEntry.hashCode());
  }

  /**
   * Method under test: {@link ConsumerRuleFilterEntry#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    ConsumerRuleFilterEntry consumerRuleFilterEntry = new ConsumerRuleFilterEntry("group", "Module");

    // Act and Assert
    assertNotEquals(consumerRuleFilterEntry, new ConsumerRuleFilterEntry("Group", "Module"));
  }

  /**
   * Method under test: {@link ConsumerRuleFilterEntry#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    ConsumerRuleFilterEntry consumerRuleFilterEntry = new ConsumerRuleFilterEntry("Group", "group");

    // Act and Assert
    assertNotEquals(consumerRuleFilterEntry, new ConsumerRuleFilterEntry("Group", "Module"));
  }

  /**
   * Method under test: {@link ConsumerRuleFilterEntry#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new ConsumerRuleFilterEntry("Group", "Module"), null);
  }

  /**
   * Method under test: {@link ConsumerRuleFilterEntry#equals(Object)}
   */
  @Test
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new ConsumerRuleFilterEntry("Group", "Module"), "Different type to ConsumerRuleFilterEntry");
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>{@link ConsumerRuleFilterEntry#toString()}
   *   <li>{@link ConsumerRuleFilterEntry#getGroup()}
   *   <li>{@link ConsumerRuleFilterEntry#getModule()}
   * </ul>
   */
  @Test
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
   * Method under test:
   * {@link ConsumerRuleFilterEntry#ConsumerRuleFilterEntry(String, String)}
   */
  @Test
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
