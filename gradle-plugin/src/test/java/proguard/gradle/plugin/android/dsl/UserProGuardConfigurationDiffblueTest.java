package proguard.gradle.plugin.android.dsl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class UserProGuardConfigurationDiffblueTest {
  /**
   * Test {@link UserProGuardConfiguration#UserProGuardConfiguration(String)}.
   * <p>
   * Method under test: {@link UserProGuardConfiguration#UserProGuardConfiguration(String)}
   */
  @Test
  @DisplayName("Test new UserProGuardConfiguration(String)")
  @Tag("MaintainedByDiffblue")
  @MethodsUnderTest({"void proguard.gradle.plugin.android.dsl.UserProGuardConfiguration.<init>(java.lang.String)"})
  void testNewUserProGuardConfiguration() {
    // Arrange and Act
    UserProGuardConfiguration actualUserProGuardConfiguration = new UserProGuardConfiguration("foo.txt");

    // Assert
    assertEquals("foo.txt", actualUserProGuardConfiguration.getFilename());
    assertEquals("foo.txt", actualUserProGuardConfiguration.getPath());
    assertEquals("foo.txt", actualUserProGuardConfiguration.toString());
  }
}
