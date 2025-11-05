package proguard.optimize.peephole;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

class ReachableCodeMarkerDiffblueTest {
  /**
   * Method under test: {@link ReachableCodeMarker#isReachable(int)}
   */
  @Test
  void testIsReachable() {
    // Arrange, Act and Assert
    assertFalse((new ReachableCodeMarker()).isReachable(2));
    assertFalse((new ReachableCodeMarker()).isReachable(1, 3));
  }

  /**
   * Method under test: default or parameterless constructor of
   * {@link ReachableCodeMarker}
   */
  @Test
  void testNewReachableCodeMarker() {
    // Arrange, Act and Assert
    assertFalse((new ReachableCodeMarker()).isReachable(2));
  }
}
