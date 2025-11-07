package proguard.optimize.peephole;

import static org.junit.jupiter.api.Assertions.assertFalse;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ReachableCodeMarkerDiffblueTest {
  /**
   * Test {@link ReachableCodeMarker#isReachable(int)} with {@code offset}.
   * <ul>
   *   <li>When two.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReachableCodeMarker#isReachable(int)}
   */
  @Test
  @DisplayName("Test isReachable(int) with 'offset'; when two; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ReachableCodeMarker.isReachable(int)"})
  void testIsReachableWithOffset_whenTwo_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ReachableCodeMarker()).isReachable(2));
  }

  /**
   * Test {@link ReachableCodeMarker#isReachable(int, int)} with {@code startOffset}, {@code endOffset}.
   * <ul>
   *   <li>When one.</li>
   *   <li>Then return {@code false}.</li>
   * </ul>
   * <p>
   * Method under test: {@link ReachableCodeMarker#isReachable(int, int)}
   */
  @Test
  @DisplayName("Test isReachable(int, int) with 'startOffset', 'endOffset'; when one; then return 'false'")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"boolean ReachableCodeMarker.isReachable(int, int)"})
  void testIsReachableWithStartOffsetEndOffset_whenOne_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse((new ReachableCodeMarker()).isReachable(1, 3));
  }

  /**
   * Test new {@link ReachableCodeMarker} (default constructor).
   * <p>
   * Method under test: default or parameterless constructor of {@link ReachableCodeMarker}
   */
  @Test
  @DisplayName("Test new ReachableCodeMarker (default constructor)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void ReachableCodeMarker.<init>()"})
  void testNewReachableCodeMarker() {
    // Arrange, Act and Assert
    assertFalse((new ReachableCodeMarker()).isReachable(2));
  }
}
