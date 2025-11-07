package proguard.shrink;

import static org.junit.jupiter.api.Assertions.assertFalse;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import proguard.classfile.LibraryClass;
import proguard.util.Processable;

class SimpleUsageMarkerDiffblueTest {
  /**
   * Test {@link SimpleUsageMarker#isPossiblyUsed(Processable)}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleUsageMarker#isPossiblyUsed(Processable)}
   */
  @Test
  @DisplayName("Test isPossiblyUsed(Processable); when LibraryClass(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SimpleUsageMarker.isPossiblyUsed(Processable)"})
  void testIsPossiblyUsed_whenLibraryClass_thenReturnFalse() {
    // Arrange
    SimpleUsageMarker simpleUsageMarker = new SimpleUsageMarker();

    // Act and Assert
    assertFalse(simpleUsageMarker.isPossiblyUsed(new LibraryClass()));
  }

  /**
   * Test {@link SimpleUsageMarker#isUsed(Processable)}.
   * <ul>
   *   <li>When {@link LibraryClass#LibraryClass()}.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link SimpleUsageMarker#isUsed(Processable)}
   */
  @Test
  @DisplayName("Test isUsed(Processable); when LibraryClass(); then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean SimpleUsageMarker.isUsed(Processable)"})
  void testIsUsed_whenLibraryClass_thenReturnFalse() {
    // Arrange
    SimpleUsageMarker simpleUsageMarker = new SimpleUsageMarker();

    // Act and Assert
    assertFalse(simpleUsageMarker.isUsed(new LibraryClass()));
  }
}
