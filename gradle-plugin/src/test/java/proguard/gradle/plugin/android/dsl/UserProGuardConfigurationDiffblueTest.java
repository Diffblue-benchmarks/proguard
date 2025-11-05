package proguard.gradle.plugin.android.dsl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class UserProGuardConfigurationDiffblueTest {
  /**
   * Method under test:
   * {@link UserProGuardConfiguration#UserProGuardConfiguration(String)}
   */
  @Test
  void testNewUserProGuardConfiguration() {
    // Arrange and Act
    UserProGuardConfiguration actualUserProGuardConfiguration = new UserProGuardConfiguration("foo.txt");

    // Assert
    assertEquals("foo.txt", actualUserProGuardConfiguration.getFilename());
    assertEquals("foo.txt", actualUserProGuardConfiguration.getPath());
    assertEquals("foo.txt", actualUserProGuardConfiguration.toString());
  }
}
