package proguard.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class BenchmarkDiffblueTest {
  /**
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link Benchmark}
   *   <li>{@link Benchmark#getElapsedTimeMs()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals(0, (new Benchmark()).getElapsedTimeMs());
  }
}
