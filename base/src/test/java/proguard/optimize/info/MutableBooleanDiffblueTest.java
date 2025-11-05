package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class MutableBooleanDiffblueTest {
  /**
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link MutableBoolean}
   *   <li>{@link MutableBoolean#reset()}
   *   <li>{@link MutableBoolean#set()}
   *   <li>{@link MutableBoolean#isSet()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange and Act
    MutableBoolean actualMutableBoolean = new MutableBoolean();
    actualMutableBoolean.reset();
    actualMutableBoolean.set();

    // Assert that nothing has changed
    assertTrue(actualMutableBoolean.isSet());
  }
}
