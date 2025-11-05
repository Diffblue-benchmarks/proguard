package proguard.gradle.plugin.android.dsl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ProGuardConfigurationDiffblueTest {
  /**
   * Method under test: {@link ProGuardConfiguration#getFilename()}
   */
  @Test
  void testGetFilename() {
    // Arrange, Act and Assert
    assertEquals("foo.txt", (new UserProGuardConfiguration("foo.txt")).getFilename());
  }

  /**
   * Method under test: {@link ProGuardConfiguration#getPath()}
   */
  @Test
  void testGetPath() {
    // Arrange, Act and Assert
    assertEquals("foo.txt", (new UserProGuardConfiguration("foo.txt")).getPath());
  }

  /**
   * Method under test: {@link ProGuardConfiguration#toString()}
   */
  @Test
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("foo.txt", (new UserProGuardConfiguration("foo.txt")).toString());
  }
}
