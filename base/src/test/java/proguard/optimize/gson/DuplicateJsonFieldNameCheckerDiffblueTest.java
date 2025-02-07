package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;

class DuplicateJsonFieldNameCheckerDiffblueTest {
  /**
   * Test getters and setters.
   * <p>
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link DuplicateJsonFieldNameChecker}
   *   <li>{@link DuplicateJsonFieldNameChecker#visitAnyClass(Clazz)}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.optimize.gson.DuplicateJsonFieldNameChecker.<init>()",
      "void proguard.optimize.gson.DuplicateJsonFieldNameChecker.visitAnyClass(proguard.classfile.Clazz)"})
  void testGettersAndSetters() {
    // Arrange and Act
    DuplicateJsonFieldNameChecker actualDuplicateJsonFieldNameChecker = new DuplicateJsonFieldNameChecker();
    actualDuplicateJsonFieldNameChecker.visitAnyClass(new LibraryClass());

    // Assert
    assertFalse(actualDuplicateJsonFieldNameChecker.hasDuplicateJsonFieldNames);
  }
}
