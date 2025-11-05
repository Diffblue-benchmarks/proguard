package proguard.optimize.gson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import proguard.classfile.Clazz;
import proguard.classfile.LibraryClass;

class DuplicateJsonFieldNameCheckerDiffblueTest {
  /**
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of
   * {@link DuplicateJsonFieldNameChecker}
   *   <li>{@link DuplicateJsonFieldNameChecker#visitAnyClass(Clazz)}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange and Act
    DuplicateJsonFieldNameChecker actualDuplicateJsonFieldNameChecker = new DuplicateJsonFieldNameChecker();
    actualDuplicateJsonFieldNameChecker.visitAnyClass(new LibraryClass());

    // Assert that nothing has changed
    assertFalse(actualDuplicateJsonFieldNameChecker.hasDuplicateJsonFieldNames);
  }
}
