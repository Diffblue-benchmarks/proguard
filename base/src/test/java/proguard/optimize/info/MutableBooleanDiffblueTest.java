package proguard.optimize.info;

import static org.junit.jupiter.api.Assertions.assertTrue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class MutableBooleanDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link MutableBoolean}
   *   <li>{@link MutableBoolean#reset()}
   *   <li>{@link MutableBoolean#set()}
   *   <li>{@link MutableBoolean#isSet()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.info.MutableBoolean.<init>()",
      "boolean proguard.optimize.info.MutableBoolean.isSet()", "void proguard.optimize.info.MutableBoolean.reset()",
      "void proguard.optimize.info.MutableBoolean.set()"})
  void testGettersAndSetters() {
    // Arrange and Act
    MutableBoolean actualMutableBoolean = new MutableBoolean();
    actualMutableBoolean.reset();
    actualMutableBoolean.set();

    // Assert
    assertTrue(actualMutableBoolean.isSet());
  }
}
