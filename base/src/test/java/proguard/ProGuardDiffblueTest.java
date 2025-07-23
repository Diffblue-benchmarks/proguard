package proguard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ProGuardDiffblueTest {
  /**
   * Test {@link ProGuard#getVersion()}.
   *
   * <p>Method under test: {@link ProGuard#getVersion()}
   */
  @Test
  @DisplayName("Test getVersion()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String ProGuard.getVersion()"})
  void testGetVersion() {
    // Arrange, Act and Assert
    assertEquals("undefined", ProGuard.getVersion());
  }
}
